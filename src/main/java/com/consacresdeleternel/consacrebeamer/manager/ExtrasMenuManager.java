package com.consacresdeleternel.consacrebeamer.manager;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.events.ExtrasMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.BookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.repository.BookRepository;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

@Singleton
public class ExtrasMenuManager {
	@Inject
	private BookRepository bookRepository;
	@Inject
	private DialogManager dialogManager;

	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(ExtrasMenuEvent.CREATE_NEW_BOOK,
				evt -> handleCreateBook(mainContainerView, evt));
		mainContainerView.addEventHandler(ExtrasMenuEvent.SHOW_LIBRARY,
				evt -> handleShowLibrary(mainContainerView, evt));
	}

	private void handleShowLibrary(MainContainerView mainContainerView, ExtrasMenuEvent evt) {
		evt.consume();
		List<Book> books = bookRepository.findAll();
		if(books == null || books.isEmpty()){
			Dialogs.info(Localization.asKey("csb.ExtrasMenu.libraryIsEmpty"),
					mainContainerView.getScene().getWindow());
			return;
		}
			
		mainContainerView.getFlowPane().getChildren().clear();
		books.stream().forEach(b -> {
			BookView bookView = new BookView();
			bookView.setBook(b);
			bookView.setBookName(b.getTitle());
			mainContainerView.getFlowPane().getChildren().add(bookView);
		});
	}

	private void handleCreateBook(MainContainerView mainContainerView, ExtrasMenuEvent evt) {
		evt.consume();
		CreateBookView createBookView = new CreateBookView();
		createBookView.setEditMode(false);
		Dialog<ButtonType> customDialog = dialogManager.showCreateBookView(createBookView,
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = customDialog.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			Book book = new Book();
			book.setTitle(createBookView.getBookName());
			Book duplicat = bookRepository.findByTitle(createBookView.getBookName().trim());
			if(duplicat != null){
				Dialogs.error(Localization.asKey("csb.ExtrasMenu.duplicatedBook"),
						mainContainerView.getScene().getWindow());
				return;
			}
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

}
