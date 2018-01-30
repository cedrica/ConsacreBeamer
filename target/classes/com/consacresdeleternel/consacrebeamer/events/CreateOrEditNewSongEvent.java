package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class CreateOrEditNewSongEvent extends Event {
	private static final long serialVersionUID = 1L;
	public static final EventType<CreateOrEditNewSongEvent> SELECT_SONG = new EventType<>(
			"selectSongCreateOrEditNewSongEvent");
	public static final EventType<CreateOrEditNewSongEvent> UPDATE_LIST_ITEM_AND_SONG_PART = new EventType<>(
			"updateSongCreateOrEditNewSongEvent");
	public static final EventType<CreateOrEditNewSongEvent> EDIT_SONG =  new EventType<>(
			"editSongCreateOrEditNewSongEvent");
	public static final EventType<CreateOrEditNewSongEvent> REMOVE_SONG = new EventType<>(
			"removeSongCreateOrEditNewSongEvent");

	private Object itemObject;

	public CreateOrEditNewSongEvent(EventType<CreateOrEditNewSongEvent> createOrEditNewSongEvent) {
		super(createOrEditNewSongEvent);
	}

	public CreateOrEditNewSongEvent(EventType<CreateOrEditNewSongEvent> createOrEditNewSongEvent, Object itemObject) {
		super(createOrEditNewSongEvent);
		this.itemObject = itemObject;
	}

	public Object getItemObject() {
		return itemObject;
	}

}
