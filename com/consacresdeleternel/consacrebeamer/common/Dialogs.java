package com.consacresdeleternel.consacrebeamer.common;

import org.controlsfx.glyphfont.FontAwesome.Glyph;

import com.consacresdeleternel.consacrebeamer.utils.GlyphUtilities;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		customDialog.setResizable(true);
		customDialog.initOwner(owner);
		Window dialogWindow = customDialog.getDialogPane().getScene().getWindow();
		dialogWindow.setOnCloseRequest(evt -> {
			dialogWindow.hide();
		});
		return customDialog;
	}

	public static void error(String message, Window window) {
		Label error = new Label();
		org.controlsfx.glyphfont.Glyph create = GlyphUtilities.create(Glyph.TIMES_CIRCLE, Color.RED);
		create.setContentDisplay(ContentDisplay.LEFT);
		create.setFontSize(50);
		error.setGraphic(create);
		Label lblMessage = new Label();
		VBox vbBox = new VBox(lblMessage);
		vbBox.setAlignment(Pos.CENTER);
		lblMessage.setText(message);
		HBox hBox = new HBox(error, vbBox);
		hBox.setSpacing(10);
		Dialog<ButtonType> customDialog = customDialog(hBox, Modality.APPLICATION_MODAL, null, window);
		customDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		customDialog.showAndWait();
	}

	public static void success(String message, Window window) {
		Label error = new Label();
		org.controlsfx.glyphfont.Glyph create = GlyphUtilities.create(Glyph.CHECK_CIRCLE, Color.GREENYELLOW);
		create.setContentDisplay(ContentDisplay.LEFT);
		create.setFontSize(50);
		error.setGraphic(create);
		Label lblMessage = new Label();
		VBox vbBox = new VBox(lblMessage);
		vbBox.setAlignment(Pos.CENTER);
		lblMessage.setText(message);
		HBox hBox = new HBox(error, vbBox);
		hBox.setSpacing(10);
		Dialog<ButtonType> customDialog = customDialog(hBox, Modality.APPLICATION_MODAL, null, window);
		customDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		customDialog.showAndWait();
	}

	public static void warning(String message, Window window) {
		Label warning = new Label();
		org.controlsfx.glyphfont.Glyph create = GlyphUtilities.create(Glyph.WARNING, Color.YELLOW);
		create.setContentDisplay(ContentDisplay.LEFT);
		create.setFontSize(50);
		warning.setGraphic(create);
		Label lblMessage = new Label();
		VBox vbBox = new VBox(lblMessage);
		vbBox.setAlignment(Pos.CENTER);
		lblMessage.setText(message);
		HBox hBox = new HBox(warning, vbBox);
		hBox.setSpacing(10);
		Dialog<ButtonType> customDialog = customDialog(hBox, Modality.APPLICATION_MODAL, null, window);
		customDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		customDialog.showAndWait();
	}

	public static void info(String message, Window window) {
		Label warning = new Label();
		org.controlsfx.glyphfont.Glyph create = GlyphUtilities.create(Glyph.INFO, Color.LIGHTBLUE);
		create.setContentDisplay(ContentDisplay.LEFT);
		create.setFontSize(50);
		warning.setGraphic(create);
		Label lblMessage = new Label();
		VBox vbBox = new VBox(lblMessage);
		vbBox.setAlignment(Pos.CENTER);
		lblMessage.setText(message);
		HBox hBox = new HBox(warning, vbBox);
		hBox.setSpacing(10);
		Dialog<ButtonType> customDialog = customDialog(hBox, Modality.APPLICATION_MODAL, null, window);
		customDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		customDialog.showAndWait();
	}

	public static Dialog<ButtonType> confirm(String message, Window window) {
		Label warning = new Label();
		org.controlsfx.glyphfont.Glyph create = GlyphUtilities.create(Glyph.INFO, Color.LIGHTBLUE);
		create.setContentDisplay(ContentDisplay.LEFT);
		create.setFontSize(50);
		warning.setGraphic(create);
		Label lblMessage = new Label();
		VBox vbBox = new VBox(lblMessage);
		vbBox.setAlignment(Pos.CENTER);
		lblMessage.setText(message);
		HBox hBox = new HBox(warning, vbBox);
		hBox.setSpacing(10);
		Dialog<ButtonType> customDialog = customDialog(hBox, Modality.APPLICATION_MODAL, null, window);
		customDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		return customDialog;
	}

}
