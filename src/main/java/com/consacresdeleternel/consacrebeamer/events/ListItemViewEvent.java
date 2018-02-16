package com.consacresdeleternel.consacrebeamer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ListItemViewEvent extends Event {

	private static final long serialVersionUID = 1L;
	public static final EventType<ListItemViewEvent> SHOW_LIST_ITEM_CONTEXT_MENU = new EventType<>(
			"showListItemContextMenuListItemViewEvent");

	public ListItemViewEvent(EventType<ListItemViewEvent> eventType) {
		super(eventType);
	}
}
