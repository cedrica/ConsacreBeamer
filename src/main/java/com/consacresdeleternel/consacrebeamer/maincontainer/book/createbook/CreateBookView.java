package com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

public class CreateBookView extends BorderPane {
	private StringProperty bookName = new SimpleStringProperty();
	private ObjectProperty<Boolean> invalid = new SimpleObjectProperty<>();
	private ObjectProperty<Boolean> editMode = new SimpleObjectProperty<>(false);
	private ObjectProperty<Book> book = new SimpleObjectProperty<>();

	public CreateBookView() {
		Helper.load(this, Localization.getDefault());
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

	public final ObjectProperty<Boolean> invalidProperty() {
		return this.invalid;
	}

	public final Boolean getInvalid() {
		return this.invalidProperty().get();
	}

	public final void setInvalid(final Boolean invalid) {
		this.invalidProperty().set(invalid);
	}

	public final ObjectProperty<Boolean> editModeProperty() {
		return this.editMode;
	}

	public final Boolean getEditMode() {
		return this.editModeProperty().get();
	}

	public final void setEditMode(final Boolean editMode) {
		this.editModeProperty().set(editMode);
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
