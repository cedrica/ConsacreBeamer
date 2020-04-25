package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;

import javafx.event.EventType;

public enum FileMenuEnum {
	NEW_SONG("csb.fileMenu.newSong", "/icons/icons8-add-file-40.png", FileMenuEvent.NEW_SONG), 
	SEPARATOR("csb.mainContainerView.separator", "", null), 
	INSERT_SLIDE("csb.fileMenu.insertSlide","/icons/icons8-add-folder-50.png", FileMenuEvent.INSERT_SLIDE), 
	CLOSE_SLIDE("csb.fileMenu.closeSlide", "/icons/icons8-delete-file-40.png",FileMenuEvent.CLOSE_SLIDE), 
	SEPARATOR2("csb.mainContainerView.separator", "", null), 
	SAVE("csb.save", "/icons/icons8-save-48.png",FileMenuEvent.SAVE), 
	SAVE_AS("csb.fileMenu.saveAs", "/icons/icons8-save-as-50.png",FileMenuEvent.SAVE_AS), 
	NEW_SCHEDULE("csb.fileMenu.newSchedule","/icons/icons8-health-calendar-40.png",FileMenuEvent.NEW_SCHEDULE), 
	PRINT("csb.fileMenu.print","/icons/icons8-print-40.png",FileMenuEvent.PRINT), 
	EXIT("csb.fileMenu.exit","/icons/icons8-shutdown-40.png",FileMenuEvent.EXIT_APPLICATION);

	private String name;
	private String iconName;
	private EventType<FileMenuEvent> eventType;

	FileMenuEnum(String name, String iconName, EventType<FileMenuEvent> eventType) {
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

	public EventType<FileMenuEvent> getEventType() {
		return eventType;
	}
}
