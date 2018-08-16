package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class InsertMenuEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<InsertMenuEvent> SEARCH_SONG = new EventType<>("searchSongInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_PDF = new EventType<>("insertPdfInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_FOLDER = new EventType<>("insertFolderInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_POWER_POINT = new EventType<>(
			"insertPowerPointInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_MUSIC = new EventType<>("insertMusicInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_VIDEO = new EventType<>("insertVideoInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_IMAGE = new EventType<>("insertImageInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_BIBLE_VERSE = new EventType<>(
			"insertBibleVerseInsertMenuEvent");
	public static final EventType<InsertMenuEvent> INSERT_SONG = new EventType<>("insertSongInsertMenuEvent");

	public InsertMenuEvent(EventType<InsertMenuEvent> eventType) {
		super(eventType);
	}

}
