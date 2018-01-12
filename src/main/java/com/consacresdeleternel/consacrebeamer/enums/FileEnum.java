package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;

import javafx.event.EventType;

public enum FileEnum  {
	NEW_SONG("csb.mainContainerView.newSong","/icons/icons8-add-file-40.png",FileMenuEvent.NEW_SONG),
	SEPARATOR("csb.mainContainerView.separator","",null),
	INSERT_SLIDE("csb.mainContainerView.insertSlide","/icons/icons8-add-folder-50.png",FileMenuEvent.NEW_SONG),
	CLOSE_SLIDE("csb.mainContainerView.closeSlide","/icons/icons8-delete-file-40.png",FileMenuEvent.NEW_SONG),
	SEPARATOR2("csb.mainContainerView.separator","",null),
	SAVE("csb.mainContainerView.save","/icons/icons8-save-48.png",FileMenuEvent.NEW_SONG),
	SAVE_AS("csb.mainContainerView.saveAs","/icons/icons8-save-as-50.png",FileMenuEvent.NEW_SONG),
	NEW_SCHEDULE("csb.mainContainerView.newSchedule","/icons/icons8-health-calendar-40.png",FileMenuEvent.NEW_SONG),
	PRINT("csb.mainContainerView.print","/icons/icons8-print-40.png",FileMenuEvent.NEW_SONG),
	EXIT("csb.mainContainerView.exit","/icons/icons8-shutdown-40.png",FileMenuEvent.EXIT_APPLICATION);
	
	private String name;
	private String iconName;
	private EventType<FileMenuEvent> eventType;
	FileEnum(String name, String iconName, EventType<FileMenuEvent>  eventType) {
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

	public EventType<FileMenuEvent>  getEventType() {
		return eventType;
	}
}
