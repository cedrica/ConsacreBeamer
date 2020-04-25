package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class HelpMenuEvent extends Event {

	private static final long serialVersionUID = 1L;
	public static final EventType<HelpMenuEvent> DOCUMENTATION = new EventType<>("documentationHelpMenuEvent");
	public static final EventType<HelpMenuEvent> NEW_FEATURES = new EventType<>("newFeaturesHelpMenuEvent");
	public static final EventType<HelpMenuEvent> VISIT_HOMEPAGE = new EventType<>("visitHomepageHelpMenuEvent");
	public static final EventType<HelpMenuEvent> INFO = new EventType<>("infoHelpMenuEvent");;

	public HelpMenuEvent(EventType<HelpMenuEvent> eventType) {
		super(eventType);
	}

}
