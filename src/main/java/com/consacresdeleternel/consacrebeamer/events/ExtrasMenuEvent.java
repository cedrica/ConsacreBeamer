package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ExtrasMenuEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final EventType<ExtrasMenuEvent> SEARCH_SONG = new EventType<>("searchSongExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> OPTIONS = new EventType<>("optionsExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> CREATE_NEW_BOOK = new EventType<>("createNewBookExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> SHOW_LIBRARY  = new EventType<>("libraryExtrasMenuEvent");

	public ExtrasMenuEvent(EventType<ExtrasMenuEvent> eventType) {
		super(eventType);
	}

}
