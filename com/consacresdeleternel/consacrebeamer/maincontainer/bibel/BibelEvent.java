package com.consacresdeleternel.consacrebeamer.maincontainer.bibel;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListBasicObject;

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
	private Verse verse;
	private Language language;
	
	public static final EventType<BibelEvent> CUSTOM_LIST = new EventType<BibelEvent>("custonListEvent");
	public static final EventType<BibelEvent> CHAPTER = new EventType<BibelEvent>("chapterEvent");
	public static final EventType<BibelEvent> VERSE = new EventType<BibelEvent>("verseEvent");
	public static final EventType<BibelEvent> LOAD_LANGUAGE = new EventType<BibelEvent>("loadLanguageEvent");
	
	public BibelEvent(CustomListBasicObject source, EventTarget target, EventType<BibelEvent> eventType) {
		super(source, target, eventType);
		this.customListBasicObject = source;
	}
	
	
	public BibelEvent(Chapter chapter, EventTarget target, EventType<BibelEvent> eventType) {
		super(chapter, target, eventType);
		this.chapter = chapter;
	}


	public BibelEvent(Verse verse, EventTarget target, EventType<BibelEvent> eventType) {
		super(verse, target, eventType);
		this.verse = verse;
	}
	
	public BibelEvent(Language language, EventTarget target, EventType<BibelEvent> eventType) {
		super(language, target, eventType);
		this.language = language;
	}
	
	public CustomListBasicObject getCustomListBasicObject() {
		return customListBasicObject;
	}


	public Chapter getChapter() {
		return chapter;
	}


	public Verse getVerse() {
		return verse;
	}


	public Language getLanguage() {
		return language;
	}
	

}
