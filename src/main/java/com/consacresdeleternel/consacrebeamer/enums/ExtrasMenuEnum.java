package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.ExtrasMenuEvent;

import javafx.event.EventType;

public enum ExtrasMenuEnum {
 
	CREATE_NEW_BOOK("csb.extrasMenu.createNewBook", "/icons/icons8-book2-40.png",ExtrasMenuEvent.CREATE_NEW_BOOK), 
	LIBRARY("csb.extrasMenu.library", "",ExtrasMenuEvent.SHOW_LIBRARY),
	SONGS_CATALOG("csb.extrasMenu.songscatalog", "/icons/catalog.png",ExtrasMenuEvent.SONGS_CATALOG),
	BACKGROUND_MANAGER("csb.extrasMenu.backgroundManager", "",null), 
	SCHEDULE_LIST("csb.extrasMenu.showScheduleList", "",ExtrasMenuEvent.SHOW_SCHEDULE_LIST), 
	OPTIONS("csb.extrasMenu.options", "", ExtrasMenuEvent.OPTIONS);

	private String name;
	private String iconName;
	private EventType<ExtrasMenuEvent> eventType;

	ExtrasMenuEnum(String name, String iconName, EventType<ExtrasMenuEvent> eventType) {
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

	public EventType<ExtrasMenuEvent> getEventType() {
		return eventType;
	}
}
