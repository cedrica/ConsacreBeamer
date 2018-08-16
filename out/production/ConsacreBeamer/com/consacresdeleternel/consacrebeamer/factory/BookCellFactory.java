package com.consacresdeleternel.consacrebeamer.factory;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BookCellFactory implements Callback<ListView<Book>, ListCell<Book>> {
	@Override
	public ListCell<Book> call(ListView<Book> param) {
		return new ListCell<Book>() {
			@Override
			protected void updateItem(Book item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setText(item.getTitle());
				} else {
					setText(null);
					setGraphic(null);
				}
			}
		};
	}

}
