package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.HelpMenuEvent;

import javafx.event.EventType;

public enum HelpMenuEnum {
	DOCUMENTATION("csb.helpMenu.documentation", "",HelpMenuEvent.DOCUMENTATION), 
	NEW_FEATURES("csb.helpMenu.newFeatures", "",HelpMenuEvent.NEW_FEATURES), 
	VISIT_HOMEPAGE("csb.helpMenu.visitHomePage", "",HelpMenuEvent.VISIT_HOMEPAGE), 
	INFO("csb.helpMenu.info", "", HelpMenuEvent.INFO);

	private String name;
	private String iconName;
	private EventType<HelpMenuEvent> eventType;

	HelpMenuEnum(String name, String iconName, EventType<HelpMenuEvent> eventType) {
		this.name = name;
		this.iconName = iconName;
		this.eventType = eventType;
	}

	public String getName() {
		return name;
	}

	public String getIconName() {
		return iconName;
	}

	public EventType<HelpMenuEvent> getEventType() {
		return eventType;
	}
}
