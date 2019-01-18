package com.consacresdeleternel.consacrebeamer.manager;


import com.consacresdeleternel.consacrebeamer.events.PresentationMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer.SongPartViewerView;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;

import javafx.stage.Stage;

public class PresentationManager {
	private ManagerProvider managerProvider;
	
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,RepositoryProvider repositoryProvider) {
		this.managerProvider = managerProvider;
		mainContainerView.addEventHandler(PresentationMenuEvent.START_PRESENTATION,
				evt -> handleStartPresentation(mainContainerView, evt));
	}

	private void handleStartPresentation(MainContainerView mainContainerView, PresentationMenuEvent evt) {
		evt.consume();
		SongPartViewerView songPartViewerView = new SongPartViewerView();
		managerProvider.getValueObjectManager().setSongPartViewerView(songPartViewerView);
		Stage dialogStage = managerProvider.getDialogManager().showStartPresentationView(songPartViewerView,
				mainContainerView.getScene().getWindow());
		dialogStage.setFullScreen(true);
		dialogStage.show();
	}
}
