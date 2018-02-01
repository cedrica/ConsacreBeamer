package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

@Singleton
public class ValueObjectManager {
	private ListProperty<Book> bookItems = new SimpleListProperty<>();

	public final ListProperty<Book> bookItemsProperty() {
		return this.bookItems;
	}

	public final ObservableList<Book> getBookItems() {
		return this.bookItemsProperty().get();
	}

	public final void setBookItems(final ObservableList<Book> bookItems) {
		this.bookItemsProperty().set(bookItems);
	}

}
