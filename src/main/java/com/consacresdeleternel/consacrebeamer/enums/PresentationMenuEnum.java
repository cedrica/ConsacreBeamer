package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.PresentationMenuEvent;

import javafx.event.EventType;

public enum PresentationMenuEnum {
	START_PRESENTATION("csb.presentationMenuEnum.startPresentation", "/icons/icons8-presentation-40.png",PresentationMenuEvent.START_PRESENTATION), 
	DELAY("csb.presentationMenuEnum.delay", "/icons/icons8-next-40.png", null), 
	SEPARATOR("csb.mainContainerView.separator","",null),
	SEND_MESSAGE_TO_ASSEMBLY("csb.presentationMenuEnum.sendMessageToAssembly", "/icons/icons8-chat-room-40.png", null), 
	OPTIONS("csb.presentationMenuEnum.options", "", PresentationMenuEvent.OPTIONS);

	private String name;
	private String iconName;
	private EventType<PresentationMenuEvent> eventType;

	PresentationMenuEnum(String name, String iconName, EventType<PresentationMenuEvent> eventType) {
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

	public EventType<PresentationMenuEvent> getEventType() {
		return eventType;
	}
}
