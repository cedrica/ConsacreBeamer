package com.consacresdeleternel.consacrebeamer.common;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Dialogs {
	/**
	 * Loads the icon and style used in the parent window/scene and sets it to
	 * the dialogStage
	 *
	 * @param parentWindow
	 *            Window to receive icon from
	 * @author c.watzl
	 */
	private static void inheritStyle(final Window dialogWindow, final Window parentWindow) {

		if (parentWindow != null) {
			final Stage parentStage = (Stage) parentWindow;
			// Set the Current Application Icon
			final Stage dialogStage = (Stage) dialogWindow;
			dialogStage.getIcons().setAll(parentStage.getIcons());

			// Inherit the parent style
			dialogWindow.getScene().getStylesheets().setAll(parentWindow.getScene().getStylesheets());
		}
	}

	public static Stage dialogStage(Parent content, Modality modality, String title, Window owner) {

		Stage stage = new Stage();
		stage.setScene(new Scene(content));
		stage.setTitle(title);
		stage.initModality(modality);
		stage.initOwner(owner);

		inheritStyle(stage, owner);

		return stage;

	}

	public static Dialog<ButtonType> customDialog(Node content, Modality modality, String title, Window owner) {
		Dialog<ButtonType> customDialog = new Dialog<>();
		customDialog.initModality(modality);
		customDialog.setTitle(title);
		customDialog.getDialogPane().setContent(content);
		customDialog.initOwner(owner);
		Window dialogWindow = customDialog.getDialogPane().getScene().getWindow();
		dialogWindow.setOnCloseRequest(evt -> {
			dialogWindow.hide();
		});
		return customDialog;
	}
}
