package com.consacresdeleternel.consacrebeamer.manager;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.BookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist.SongListView;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBookTask;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Side;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class BookManager {
	private static final Logger LOG = LoggerFactory.getLogger(ExtrasMenuManager.class);

	private ManagerProvider managerProvider;
	private RepositoryProvider repositoryProvider;
	
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,RepositoryProvider repositoryProvider) {
		this.managerProvider = managerProvider;
		this.repositoryProvider = repositoryProvider;
		mainContainerView.addEventHandler(BookEvent.REMOVE_BOOK, evt -> handleRemoveBook(mainContainerView, evt));
		mainContainerView.addEventHandler(BookEvent.EDIT_BOOK, evt -> handleEditBook(mainContainerView, evt));
		mainContainerView.addEventHandler(BookEvent.SHOW_SONG_LIST, evt -> handleShowSongList(mainContainerView, evt));
		mainContainerView.addEventHandler(BookEvent.DELETE_SONGS, evt -> handleDeleteSong(mainContainerView, evt));
	}

	private void handleRemoveBook(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		Dialog<ButtonType> confirm = Dialogs.confirm(Localization.asKey("csb.dialogs.confirmRemoveBook"),
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = confirm.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.YES) {
			Book book = evt.getBook();
			repositoryProvider.getBookRepository().removeById(book.getId());
			LoadBookTask loadBookTask = new LoadBookTask(repositoryProvider.getBookRepository());
			new Thread(loadBookTask).start();
			managerProvider.getTaskManager().addTask(loadBookTask);
			loadBookTask.valueProperty().addListener((obs, oldVal, newVal) -> {
				managerProvider.getValueObjectManager().setBookItems(FXCollections.observableList(newVal));
				mainContainerView.getFlowPane().getChildren().clear();
				newVal.stream().forEach(b -> {
					BookView bookView = new BookView();
					bookView.setBook(b);
					bookView.setBookName(b.getTitle());
					mainContainerView.getFlowPane().getChildren().add(bookView);
				});
			});
			
		}
	}

	private void handleDeleteSong(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		SongListView songListView = (SongListView) evt.getTarget();
		Dialog<ButtonType> confirm = Dialogs.confirm(Localization.asKey("csb.dialogs.ComfirmDeleteSong"),
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = confirm.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.YES) {
			List<Song> songs = evt.getSongs();
			if (songs != null && !songs.isEmpty()) {
				Long bookId = songs.get(0).getBook().getId();
				Book newBook = repositoryProvider.getBookRepository().findById(bookId);
				songs.stream().forEach(s -> {
					FileUtil.removeFile(s.getTextFileReference());
					newBook.getSongs().remove(s);
					repositoryProvider.getSongRepository().remove(s);
				});
				Dialogs.success(Localization.asKey("csb.dialogs.songsDeletingSuccessfull"),
						mainContainerView.getScene().getWindow());
				songListView.setSongItems(new FilteredList<>(FXCollections.observableList(newBook.getSongs())));

			}

		}

	}

	private void handleShowSongList(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		if (mainContainerView.getHiddenSidesPane().getPinnedSide() != null) {
			mainContainerView.getHiddenSidesPane().setPinnedSide(null);
			mainContainerView.getHiddenSidesPane().setVisible(false);
		} else {
			mainContainerView.getHiddenSidesPane().setPinnedSide(Side.TOP);
			SongListView songListView = new SongListView();
			Book book = evt.getBook();
			songListView.setBookName(book.getTitle());
			songListView.setBook(book);
			songListView.setSongItems(new FilteredList<>(FXCollections.observableList(book.getSongs())));
			mainContainerView.getHiddenSidesPane().setContent(songListView);
			mainContainerView.getHiddenSidesPane().setVisible(true);
		}

	}

	/*
	 * EDIT BOOK
	 * -------------------------------------------------------------------------
	 * ------
	 */
	private void handleEditBook(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		Book book = evt.getBook();
		CreateBookView createBookView = createEditBookViewFromBook(book);
		Dialog<ButtonType> customDialog = managerProvider.getDialogManager().showEditBookDialog(createBookView,
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = customDialog.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			LOG.info("Edit mode");
			repositoryProvider.getBookRepository().updateName(book.getId(), createBookView.getBookName());
			LoadBookTask loadBookTask = new LoadBookTask(repositoryProvider.getBookRepository());
			new Thread(loadBookTask).start();
			managerProvider.getTaskManager().addTask(loadBookTask);
			loadBookTask.valueProperty().addListener((obs, oldVal, newVal) -> {
				managerProvider.getValueObjectManager().setBookItems(FXCollections.observableList(newVal));
				mainContainerView.getFlowPane().getChildren().clear();
				newVal.stream().forEach(b -> {
					BookView bookView = new BookView();
					bookView.setBook(b);
					bookView.setBookName(b.getTitle());
					mainContainerView.getFlowPane().getChildren().add(bookView);
				});
				Dialogs.success(Localization.asKey("csb.ExtrasMenu.bookSuccessfullyCreated"),
						mainContainerView.getScene().getWindow());
			});

		}
	}

	private CreateBookView createEditBookViewFromBook(Book book) {
		CreateBookView createBookView = new CreateBookView();
		createBookView.setBook(book);
		createBookView.setBookName(book.getTitle());
		createBookView.setSongItems(FXCollections.observableList(book.getSongs()));
		createBookView.setEditMode(true);
		return createBookView;
	}
	/*
	 * EDIT BOOK (END)
	 * -------------------------------------------------------------------------
	 * ------
	 */

}
