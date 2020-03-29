package com.consacresdeleternel.consacrebeamer.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.events.ExtrasMenuEvent;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;
import com.consacresdeleternel.consacrebeamer.exceptions.DuplicateFoundException;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.BookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.schedule.ScheduleListView;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class ExtrasMenuManager {

	private ManagerProvider managerProvider;
	private RepositoryProvider repositoryProvider;
	private static final Logger LOG = LoggerFactory.getLogger(ExtrasMenuManager.class);
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,RepositoryProvider repositoryProvider){
		this.managerProvider = managerProvider;
		this.repositoryProvider = repositoryProvider;
		mainContainerView.addEventHandler(ExtrasMenuEvent.CREATE_NEW_BOOK,
				evt -> handleCreateBook(mainContainerView, evt));
		mainContainerView.addEventHandler(ExtrasMenuEvent.SHOW_LIBRARY,
				evt -> handleShowLibrary(mainContainerView, evt));

		mainContainerView.addEventHandler(ExtrasMenuEvent.SHOW_SCHEDULE_LIST,
				evt -> handleShowScheduleList(mainContainerView, evt));
	}

	private void handleShowScheduleList(MainContainerView mainContainerView, ExtrasMenuEvent evt) {
		evt.consume();
		if (mainContainerView.getHiddenSidesPane().getPinnedSide() != null) {
			mainContainerView.getHiddenSidesPane().setPinnedSide(null);
			mainContainerView.getHiddenSidesPane().setVisible(false);
		} else {
			mainContainerView.getHiddenSidesPane().setPinnedSide(Side.TOP);
			List<Schedule> scheduleList = repositoryProvider.getScheduleRepository().findAll();
			if (scheduleList == null) {
				scheduleList = new ArrayList<>();
			}
			ScheduleListView scheduleListView = new ScheduleListView();
			scheduleListView.setScheduleItems(FXCollections.observableArrayList(scheduleList));
			mainContainerView.getHiddenSidesPane().setContent(scheduleListView);
			mainContainerView.getHiddenSidesPane().setVisible(true);
		}
	}

	private void handleShowLibrary(MainContainerView mainContainerView, ExtrasMenuEvent evt) {
		evt.consume();
		List<Book> books =  repositoryProvider.getBookRepository().findAll();
		if (books == null || books.isEmpty()) {
			Dialogs.info(Localization.asKey("csb.ExtrasMenu.libraryIsEmpty"), mainContainerView.getScene().getWindow());
			return;
		}

		mainContainerView.getFlowPane().getChildren().clear();
		books.stream().forEach(b -> {
			BookView bookView = new BookView();
			b.getSongs().stream().forEach(s -> {
				s.setSongHtml(FileUtil.readTxtFileToString(s.getTextFileReference()));
			});
			bookView.setBook(b);
			bookView.setBookName(b.getTitle());
			mainContainerView.getFlowPane().getChildren().add(bookView);
		});
	}

	private void handleCreateBook(MainContainerView mainContainerView, ExtrasMenuEvent evt) {
		evt.consume();
		CreateBookView createBookView = new CreateBookView();
		createBookView.setEditMode(false);
		Dialog<ButtonType> customDialog = managerProvider.getDialogManager().showCreateBookView(createBookView,
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = customDialog.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			Book book = new Book();
			book.setTitle(createBookView.getBookName());
			try{
				Book duplicat =  repositoryProvider.getBookRepository().findByTitle(createBookView.getBookName().trim());
				if (duplicat != null) {
					throw new DuplicateFoundException(Localization.asKey("csb.ExtrasMenu.duplicatedBook"));
				}
			} catch (Exception e) {
				e.fillInStackTrace();
			}
			try {
				book =  repositoryProvider.getBookRepository().save(book);
				if (book == null) {
					throw new BookNotFoundException(Localization.asKey("csb.ExtrasMenu.bookcouldNotBeCreated"));
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			mainContainerView.fireEvent(new BookEvent(BookEvent.RELOAD_BOOKS));
			List<Book> books = repositoryProvider.getBookRepository().findAll();
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

}
