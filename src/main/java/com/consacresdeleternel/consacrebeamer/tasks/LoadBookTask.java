package com.consacresdeleternel.consacrebeamer.tasks;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.repository.BookRepository;

import javafx.concurrent.Task;

public class LoadBookTask extends Task<List<Book>> {
	private BookRepository bookRepository;

	public LoadBookTask(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	protected List<Book> call() throws Exception {
		return bookRepository.findAll();
	}

}
