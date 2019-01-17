package com.consacresdeleternel.consacrebeamer.manager;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.events.ScheduleEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.service.ManagerRepositoryProvider;

public class ScheduleManager extends ManagerRepositoryProvider{
	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(ScheduleEvent.DELETE_SCHEDULE, evt ->{
			evt.consume();
			Schedule schedule = evt.getSchedule();
			if(schedule != null){
				getRepositoryProvider().getScheduleRepository().remove(schedule);
			}
		});
	}

}
