package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.events.ScheduleEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.repository.ScheduleRepository;

@Singleton
public class ScheduleManager {
	@Inject
	private ScheduleRepository scheduleRepository;
	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(ScheduleEvent.DELETE_SCHEDULE, evt ->{
			evt.consume();
			Schedule schedule = evt.getSchedule();
			if(schedule != null){
				scheduleRepository.remove(schedule);
			}
		});
	}

}
