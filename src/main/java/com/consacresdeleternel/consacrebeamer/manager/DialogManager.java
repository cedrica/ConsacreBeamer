package com.consacresdeleternel.consacrebeamer.manager;

import com.consacresdeleternel.consacrebeamer.common.Dialogs;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.CreateOrEditNewSongView;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Window;

public class DialogManager {

	public Dialog<ButtonType> showEditBookDialog(CreateBookView createBookView, Window window) {
		Dialog<ButtonType> customDialog = Dialogs.customDialog(createBookView, Modality.APPLICATION_MODAL,
				Localization.asKey("csb.dialogs.editBook"), window);
		customDialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		Button apply = (Button) customDialog.getDialogPane().lookupButton(ButtonType.APPLY);
		apply.disableProperty().bind(createBookView.invalidProperty());
		return customDialog;
	}

	public Dialog<ButtonType> showCreateBookView(CreateBookView createBookView, Window window) {
		Dialog<ButtonType> customDialog = Dialogs.customDialog(createBookView, Modality.APPLICATION_MODAL, "", window);
		customDialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		Button lookupButton = (Button) customDialog.getDialogPane().lookupButton(ButtonType.APPLY);
		lookupButton.disableProperty().bind(createBookView.invalidProperty());
		return customDialog;
	}

	public Dialog<ButtonType> showCreateOrEditNewSong(CreateOrEditNewSongView createOrEditNewSongView, Window window) {
		Dialog<ButtonType> dialogStage = Dialogs.customDialog(createOrEditNewSongView, Modality.APPLICATION_MODAL,
				Localization.asKey("csb.createNewSongView.title"), window);
		dialogStage.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		Button lookupButton = (Button) dialogStage.getDialogPane().lookupButton(ButtonType.APPLY);
		lookupButton.disableProperty().bind(createOrEditNewSongView.getTextView().invalidProperty()
				.or(createOrEditNewSongView.getCopyRightsView().invalidProperty()));
		return dialogStage;
	}
}
