package com.consacresdeleternel.consacrebeamer.manager;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

@Singleton
public class BookManager {
	private static final Logger LOG = Logger.getLogger(ExtrasMenuManager.class);
	@Inject
	private DialogManager dialogManager;

	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(BookEvent.REMOVE_BOOK, evt -> {
			evt.consume();
		});
		mainContainerView.addEventHandler(BookEvent.EDIT_BOOK, evt -> handleEditBook(mainContainerView, evt));
	}

	
	
	
	/*
	 * 								EDIT BOOK
	 *-------------------------------------------------------------------------------*/
	private void handleEditBook(MainContainerView mainContainerView, BookEvent evt) {
		evt.consume();
		Book book = evt.getBook();
		CreateBookView createBookView = createEditBookViewFromBook(book);
		Dialog<ButtonType> customDialog = dialogManager.showEditBookDialog(createBookView,
				mainContainerView.getScene().getWindow());
		Optional<ButtonType> showAndWait = customDialog.showAndWait();
		if (showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY) {
			LOG.info("Edit mode");
		}
	}

	private CreateBookView createEditBookViewFromBook(Book book) {
		CreateBookView createBookView = new CreateBookView();
		createBookView.setBook(book);
		createBookView.setBookName(book.getTitle());
		createBookView.setEditMode(true);
		return createBookView;
	}
	/*
	 * 								EDIT BOOK (END)
	 *-------------------------------------------------------------------------------*/

}
