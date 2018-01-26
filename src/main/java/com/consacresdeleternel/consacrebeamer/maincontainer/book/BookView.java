package com.consacresdeleternel.consacrebeamer.maincontainer.book;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;

public class BookView extends StackPane{
	private ObjectProperty<Book> book = new SimpleObjectProperty<>();
	
	public  BookView() {
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
	

}
