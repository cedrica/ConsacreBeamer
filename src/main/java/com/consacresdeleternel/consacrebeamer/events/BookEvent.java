package com.consacresdeleternel.consacrebeamer.events;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.event.Event;
import javafx.event.EventType;

public class BookEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<BookEvent> EDIT_BOOK = new EventType<>(
			"editBookBookEvent");
	public static final EventType<BookEvent> REMOVE_BOOK = new EventType<>(
			"removeBookBookEvent");
	private Book book;
	
	public BookEvent(EventType<BookEvent> eventType) {
		super(eventType);
	}
	
	public BookEvent(EventType<BookEvent> eventType, Book book) {
		super(eventType);
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
	
	

}
