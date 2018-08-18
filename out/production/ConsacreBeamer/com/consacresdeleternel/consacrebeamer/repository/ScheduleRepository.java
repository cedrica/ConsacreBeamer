package com.consacresdeleternel.consacrebeamer.repository;

import com.consacresdeleternel.consacrebeamer.data.Schedule;

public class ScheduleRepository  extends BasicRepository<Schedule> {
//	private static final Logger LOG = Logger.getLogger(ScheduleRepository.class);

	public ScheduleRepository() {
		super(Schedule.class);
	}
}
