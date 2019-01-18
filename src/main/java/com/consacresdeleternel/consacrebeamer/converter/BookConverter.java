package com.consacresdeleternel.consacrebeamer.converter;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.util.StringConverter;

public class BookConverter extends StringConverter<Book> {

	@Override
	public Book fromString(String string) {
		return null;
	}

	@Override
	public String toString(Book book) {
		return (book != null)? book.getTitle():"";
	}

}
