package com.consacresdeleternel.consacrebeamer.bibel;

import com.consacresdeleternel.consacrebeamer.Chapter;
import com.consacresdeleternel.consacrebeamer.customlistview.CustomListBasicObject;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class BibelEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomListBasicObject customListBasicObject;
	private Chapter chapter;
	
	public static final EventType<BibelEvent> CUSTOM_LIST = new EventType<BibelEvent>("custonListEvent");
	public static final EventType<BibelEvent> CHAPTER = new EventType<BibelEvent>("chapterEvent");
	public static final EventType<BibelEvent> VERSE = new EventType<BibelEvent>("verseEvent");
	
	public BibelEvent(CustomListBasicObject source, EventTarget target, EventType<BibelEvent> eventType) {
		super(source, target, eventType);
		this.customListBasicObject = source;
	}
	
	
	public BibelEvent(Chapter chapter, EventTarget target, EventType<BibelEvent> eventType) {
		super(chapter, target, eventType);
		this.chapter = chapter;
	}


	public CustomListBasicObject getCustomListBasicObject() {
		return customListBasicObject;
	}


	public Chapter getChapter() {
		return chapter;
	}

}
