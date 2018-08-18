package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.EditMenuEvent;

import javafx.event.EventType;

public enum EditMenuEnum {
	EDIT_SONG("csb.editMenuEnum.editSong", "/icons/icons8-edit-file-40.png", EditMenuEvent.EDIT_SONG);

	private String name;
	private String iconName;
	private EventType<EditMenuEvent> eventType;

	EditMenuEnum(String name, String iconName, EventType<EditMenuEvent> eventType) {
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

	public EventType<EditMenuEvent> getEventType() {
		return eventType;
	}
}