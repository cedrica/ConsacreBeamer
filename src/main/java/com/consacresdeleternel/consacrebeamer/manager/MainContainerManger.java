package com.consacresdeleternel.consacrebeamer.manager;

import java.util.Optional;

import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.CreateOrEditNewSongView;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;

@Singleton
public class MainContainerManger {
	private static final Logger LOG = Logger.getLogger(MainContainerManger.class);
	
	public void init(MainContainerView mainContainerView){
		mainContainerView.addEventHandler(FileMenuEvent.NEW_SONG, evt ->{
			LOG.info("New song was added");
			CreateOrEditNewSongView createOrEditNewSongView = new CreateOrEditNewSongView();
			Dialog<ButtonType> dialogStage = Dialogs.customDialog(createOrEditNewSongView, Modality.APPLICATION_MODAL, Localization.asKey("csb.createNewSongView.title"), mainContainerView.getScene().getWindow());
			dialogStage.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY,ButtonType.CANCEL);
			Button lookupButton = (Button) dialogStage.getDialogPane().lookupButton(ButtonType.APPLY);
			lookupButton.disableProperty().bind(createOrEditNewSongView.getTextView().invalidProperty().or(createOrEditNewSongView.getCopyRightsView().invalidProperty()));
			Optional<ButtonType> showAndWait = dialogStage.showAndWait();
			if(showAndWait.isPresent() && showAndWait.get() == ButtonType.APPLY){
				LOG.info(createOrEditNewSongView.getTextView().getSongText());
			}
		});
		
	}

}
