package com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist;

import javafx.event.Event;
import javafx.event.EventType;

public class HiddenSideEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<HiddenSideEvent> CLOSE = new EventType<>("closeHiddenSideEvent");

	public HiddenSideEvent(EventType<HiddenSideEvent> eventType) {
		super(eventType);
	}

}
