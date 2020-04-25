package com.consacresdeleternel.consacrebeamer.events;

import com.consacresdeleternel.consacrebeamer.data.Schedule;

import javafx.event.Event;
import javafx.event.EventType;

public class ScheduleEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final EventType<ScheduleEvent> SHOW_SCHEDULE_LIST = new EventType<>("schowScheduleListScheduleEvent");
	public static final EventType<ScheduleEvent> DELETE_SCHEDULE = new EventType<>("deleteScheduleScheduleEvent");

	private Schedule  schedule;

	public ScheduleEvent(EventType<ScheduleEvent> eventType) {
		super(eventType);
	}
	public ScheduleEvent(EventType<ScheduleEvent> eventType,Schedule  schedule) {
		super(eventType);
		this.schedule = schedule;
	}

	public Schedule getSchedule() {
		return schedule;
	}

}
