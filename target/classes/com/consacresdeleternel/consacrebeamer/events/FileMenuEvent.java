package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class FileMenuEvent extends Event{

	private static final long serialVersionUID = 1L;
	public static final EventType<FileMenuEvent> NEW_SONG   = new EventType<>("NEW_SONG");
	public static final EventType<FileMenuEvent> INSERT_SLIDE   = new EventType<>("INSERT_SLIDE");
	public static final EventType<FileMenuEvent> SAVE   = new EventType<>("SAVE");
	public static final EventType<FileMenuEvent> SAVE_AS   = new EventType<>("SAVE_AS");
	public static final EventType<FileMenuEvent> CLOSE_SLIDE   = new EventType<>("CLOSE_SLIDE");
	public static final EventType<FileMenuEvent> NEW_SCHEDULE   = new EventType<>("NEW_SCHEDULE");
	public static final EventType<FileMenuEvent> EXIT_APPLICATION   = new EventType<>("EXIT_APPLICATION");
	
	public FileMenuEvent(EventType<FileMenuEvent> eventType) {
		super(eventType);
	}

}
