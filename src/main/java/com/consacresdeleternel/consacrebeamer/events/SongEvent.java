package com.consacresdeleternel.consacrebeamer.events;

import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.event.Event;
import javafx.event.EventType;

public class SongEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<SongEvent> RELOAD_SONGS = new EventType<>("reloadSongsSongEvent");
	public static final EventType<SongEvent> ADD_SEARCHED_SONG =  new EventType<>("addSearchedSongSongEvent");
	private Song song;


	public SongEvent(EventType<SongEvent> eventType) {
		super(eventType);
	}

	public SongEvent(EventType<SongEvent> eventType, Song song) {
		super(eventType);
		this.song = song;
	}

	public Song getSong() {
		return song;
	}
	
}
