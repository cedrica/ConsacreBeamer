package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.events.PresentationMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer.SongPartViewerView;

import javafx.stage.Stage;

@Singleton
public class PresentationManager {
	@Inject
	private DialogManager dialogManager;
	@Inject
	private ValueObjectManager valueObjectManager;

	public void init(MainContainerView mainContainerView) {
		mainContainerView.addEventHandler(PresentationMenuEvent.START_PRESENTATION,
				evt -> handleStartPresentation(mainContainerView, evt));
	}

	private void handleStartPresentation(MainContainerView mainContainerView, PresentationMenuEvent evt) {
		evt.consume();
		SongPartViewerView songPartViewerView = new SongPartViewerView();
		valueObjectManager.setSongPartViewerView(songPartViewerView);
		Stage dialogStage = dialogManager.showStartPresentationView(songPartViewerView,
				mainContainerView.getScene().getWindow());
		dialogStage.setFullScreen(true);
		dialogStage.show();
	}
}
