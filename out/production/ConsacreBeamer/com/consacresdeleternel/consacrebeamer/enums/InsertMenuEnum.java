package com.consacresdeleternel.consacrebeamer.enums;

import com.consacresdeleternel.consacrebeamer.events.InsertMenuEvent;

import javafx.event.EventType;

public enum InsertMenuEnum {
	SONG("csb.insertMenuEnum.insertSong", "/icons/icons8-search-40.png", InsertMenuEvent.INSERT_SONG), BIBLE(
			"csb.insertMenuEnum.insertBibleVerse", "/icons/icons8-holy-bible-40.png",
			InsertMenuEvent.INSERT_BIBLE_VERSE), SEPARATOR("csb.mainContainerView.separator", "", null), IMAGE(
					"csb.insertMenuEnum.insertImage", "/icons/icons8-picture-40.png",
					InsertMenuEvent.INSERT_IMAGE), VIDEO("csb.insertMenuEnum.insertVideo",
							"/icons/icons8-hd-1080p-40.png", InsertMenuEvent.INSERT_VIDEO), MUSIC(
									"csb.insertMenuEnum.insertMusic", "/icons/icons8-music-40.png",
									InsertMenuEvent.INSERT_MUSIC), POWER_POINT("csb.insertMenuEnum.insertPPT",
											"/icons/icons8-microsoft-powerpoint-40.png",
											InsertMenuEvent.INSERT_POWER_POINT), PDF("csb.insertMenuEnum.insertPDF",
													"/icons/icons8-pdf-40.png", InsertMenuEvent.INSERT_PDF), SEPARATOR2(
															"csb.mainContainerView.separator", "",
															null), ADD_FOLDER("csb.insertMenuEnum.insertFolder",
																	"/icons/icons8-add-folder-40.png",
																	InsertMenuEvent.INSERT_FOLDER);

	private String name;
	private String iconName;
	private EventType<InsertMenuEvent> eventType;

	InsertMenuEnum(String name, String iconName, EventType<InsertMenuEvent> eventType) {
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

	public EventType<InsertMenuEvent> getEventType() {
		return eventType;
	}
}
