package com.consacresdeleternel.consacrebeamer.maincontainer.book;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.StackPane;

public class BookView extends StackPane {
	private ObjectProperty<Book> book = new SimpleObjectProperty<>();

	private StringProperty bookName = new SimpleStringProperty();

	public BookView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<Book> bookProperty() {
		return this.book;
	}

	public final Book getBook() {
		return this.bookProperty().get();
	}

	public final void setBook(final Book book) {
		this.bookProperty().set(book);
	}

	public final StringProperty bookNameProperty() {
		return this.bookName;
	}

	public final String getBookName() {
		return this.bookNameProperty().get();
	}

	public final void setBookName(final String bookName) {
		this.bookNameProperty().set(bookName);
	}

}
