package com.consacresdeleternel.consacrebeamer.events;

import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.event.Event;
import javafx.event.EventType;

public class FileMenuEvent extends Event{

	private static final long serialVersionUID = 1L;
	public static final EventType<FileMenuEvent> NEW_SONG   = new EventType<>("newSongFileMenuEvent");
	public static final EventType<FileMenuEvent> INSERT_SLIDE   = new EventType<>("insertSlideFileMenuEvent");
	public static final EventType<FileMenuEvent> SAVE   = new EventType<>("saveFileMenuEvent");
	public static final EventType<FileMenuEvent> SAVE_AS   = new EventType<>("saveAsFileMenuEvent");
	public static final EventType<FileMenuEvent> CLOSE_SLIDE   = new EventType<>("closeSlideFileMenuEvent");
	public static final EventType<FileMenuEvent> NEW_SCHEDULE   = new EventType<>("newScheduleFileMenuEvent");
	public static final EventType<FileMenuEvent> EXIT_APPLICATION   = new EventType<>("exitApplicationFileMenuEvent");
	public static final EventType<FileMenuEvent> EDIT_SONG = new EventType<>("editSongFileMenuEvent");
	
	private Song song;
	public FileMenuEvent(EventType<FileMenuEvent> eventType) {
		super(eventType);
	}
	
	public FileMenuEvent(EventType<FileMenuEvent> eventType, Song song) {
		super(eventType);
		this.song = song;
	}

	public Song getSong() {
		return song;
	}

}
