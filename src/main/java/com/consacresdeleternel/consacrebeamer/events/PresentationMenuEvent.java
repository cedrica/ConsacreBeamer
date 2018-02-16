package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class PresentationMenuEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<PresentationMenuEvent> OPTIONS = new EventType<>("optionsPresentationMenuEvent");
	public static final EventType<PresentationMenuEvent> START_PRESENTATION = new EventType<>(
			"startPresentationPresentationMenuEvent");

	public PresentationMenuEvent(EventType<PresentationMenuEvent> eventType) {
		super(eventType);
	}

}
