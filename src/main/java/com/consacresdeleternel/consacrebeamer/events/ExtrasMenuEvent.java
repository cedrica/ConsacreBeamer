package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ExtrasMenuEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final EventType<ExtrasMenuEvent> SONGS_CATALOG = new EventType<>("songCatalogExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> OPTIONS = new EventType<>("optionsExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> CREATE_NEW_BOOK = new EventType<>("createNewBookExtrasMenuEvent");
	public static final EventType<ExtrasMenuEvent> SHOW_LIBRARY = new EventType<>("libraryExtrasMenuEvent");

	public static final EventType<ExtrasMenuEvent> SHOW_SCHEDULE_LIST  = new EventType<>("showScheduleLIstExtrasMenuEvent");

	public ExtrasMenuEvent(EventType<ExtrasMenuEvent> eventType) {
		super(eventType);
	}

}
