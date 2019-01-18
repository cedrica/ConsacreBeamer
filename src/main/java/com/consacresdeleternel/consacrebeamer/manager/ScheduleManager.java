package com.consacresdeleternel.consacrebeamer.manager;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.events.ScheduleEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;

public class ScheduleManager {
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,
			RepositoryProvider repositoryProvider) {
		mainContainerView.addEventHandler(ScheduleEvent.DELETE_SCHEDULE, evt -> {
			evt.consume();
			Schedule schedule = evt.getSchedule();
			if (schedule != null) {
				repositoryProvider.getScheduleRepository().remove(schedule);
			}
		});
	}

}
