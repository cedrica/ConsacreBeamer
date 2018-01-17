package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SongPartEvent extends Event {
	private static final long serialVersionUID = 1L;
	public static final EventType<SongPartEvent> SHOW_SONG_PART = new EventType<>("SHOW_SONG_PART");
	
	public SongPartEvent(EventType<SongPartEvent> eventType) {
		super(eventType);
	}

	

}
