package com.consacresdeleternel.consacrebeamer.repository;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.data.Schedule;

public class ScheduleRepository  extends BasicRepository<Schedule> {
	private static final Logger LOG = Logger.getLogger(ScheduleRepository.class);

	public ScheduleRepository() {
		super(Schedule.class);
	}
}
