package com.consacresdeleternel.consacrebeamer.repository;

import com.consacresdeleternel.consacrebeamer.data.Book;

public class BookRepository extends BasicRepository<Book> {
	public BookRepository() {
		super(Book.class);
	}
}
