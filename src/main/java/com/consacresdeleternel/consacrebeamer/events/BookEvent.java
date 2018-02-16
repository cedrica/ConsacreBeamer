package com.consacresdeleternel.consacrebeamer.events;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Song;

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
	public static final EventType<BookEvent> RELOAD_BOOKS = new EventType<>(
			"loadBooksBookBookEvent");
	public static final EventType<BookEvent> SHOW_SONG_LIST =  new EventType<>(
			"showSongListBooksBookBookEvent");
	public static final EventType<BookEvent> DELETE_SONGS =  new EventType<>(
			"deleteSongBookEvent");
	private Book book;
	private List<Song> songs;
	
	public BookEvent(EventType<BookEvent> eventType) {
		super(eventType);
	}
	
	public BookEvent(EventType<BookEvent> eventType, Book book) {
		super(eventType);
		this.book = book;
	}

	public BookEvent(EventType<BookEvent> eventType, List<Song> songs) {
		super(eventType);
		this.songs = songs;
	}

	public Book getBook() {
		return book;
	}

	public List<Song> getSongs() {
		return songs;
	}
	
	

}
