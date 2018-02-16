package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class EditMenuEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<EditMenuEvent> EDIT_SONG = new EventType<>("editSongEditMenuEvent");

	public EditMenuEvent(EventType<EditMenuEvent> arg0) {
		super(arg0);
	}

}
