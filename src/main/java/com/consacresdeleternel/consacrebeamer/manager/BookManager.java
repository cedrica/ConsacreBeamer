package com.consacresdeleternel.consacrebeamer.manager;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.BookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist.SongListView;
import com.consacresdeleternel.consacrebeamer.repository.BookRepository;

import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

@Singleton
public class BookManager {
	private static final Logger LOG = Logger.getLogger(ExtrasMenuManager.class);
	@Inject
	private DialogManager dialogManager;
	@Inject
	BookRepository bookRepository;

	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(BookEvent.REMOVE_BOOK, evt -> {
			evt.consume();
		});
		mainContainerView.addEventHandler(BookEvent.EDIT_BOOK, evt -> handleEditBook(mainContainerView, evt));
		mainContainerView.addEventHandler(BookEvent.SHOW_SONG_LIST, evt -> handleShowSongList(mainContainerView, evt));
	}

	private void handleShowSongList(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		if (mainContainerView.getHiddenSidesPane().getPinnedSide() != null) {
			mainContainerView.getHiddenSidesPane().setPinnedSide(null);
			mainContainerView.getHiddenSidesPane().setVisible(false);
		} else {
			mainContainerView.getHiddenSidesPane().setPinnedSide(Side.TOP);
			SongListView songListView = new SongListView();
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
		Dialog<ButtonType> customDialog = dialogManager.showEditBookDialog(createBookView,
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = customDialog.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			LOG.info("Edit mode");
			book.setTitle(createBookView.getBookName());
			book.setSongs(createBookView.getSongItems());
			book = bookRepository.save(book);
			if (book == null) {
				Dialogs.error(Localization.asKey("csb.ExtrasMenu.bookcouldNotBeCreated"),
						mainContainerView.getScene().getWindow());
				return;
			}
			mainContainerView.fireEvent(new BookEvent(BookEvent.RELOAD_BOOKS));
			List<Book> books = bookRepository.findAll();
			mainContainerView.getFlowPane().getChildren().clear();
			books.stream().forEach(b -> {
				BookView bookView = new BookView();
				bookView.setBook(b);
				bookView.setBookName(b.getTitle());
				mainContainerView.getFlowPane().getChildren().add(bookView);
			});
			Dialogs.success(Localization.asKey("csb.ExtrasMenu.bookSuccessfullyCreated"),
					mainContainerView.getScene().getWindow());

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