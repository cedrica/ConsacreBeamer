package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class CreateOrEditNewSongEvent extends Event{
	private static final long serialVersionUID = 1L;

	public CreateOrEditNewSongEvent(EventType<CreateOrEditNewSongEvent> textEvent) {
		super(textEvent);
	}
	

}
