package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class TextController implements Initializable {
	private static final Logger LOG = LoggerFactory.getLogger(TextController.class);
	@FXML
	TextView rootNode;
	@FXML
	HTMLEditor htmlEditor;
	@FXML
	TextField tfTitle;
	@FXML
	ToggleGroup radioGroup;
	@FXML
	RadioButton rbOneLanguage;
	@FXML
	RadioButton rbTwoLanguage;
	@FXML
	RadioButton rbTreeLanguage;
	private WebView webView;
	private ValidationSupport validationSupport;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		webView = (WebView) htmlEditor.lookup(".web-view");
		rootNode.titleProperty().bindBidirectional(tfTitle.textProperty());
		registerListener();
		validationSupport = new ValidationSupport();
		Platform.runLater(() -> {
			validationSupport.registerValidator(tfTitle,true,
					Validator.createEmptyValidator(Localization.asKey("csb.CopyRightsView.titleDonotMatchPattern")));
			rootNode.invalidProperty()
					.bind(validationSupport.invalidProperty().or(rootNode.songTextProperty().isEmpty()));
		});
	}

	// private void insertTextIntpHtmlEditor(){
	// WebPage webPage = Accessor.getPageFor(webView.getEngine());
	// webPage.executeCommand("insertText", " $NEWLINE");
	// }

	private void registerListener() {
		webView.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				rootNode.setSongHtml(htmlEditor.getHtmlText());
				rootNode.setSongText(Helper.html2text(htmlEditor.getHtmlText()).replace("<br>", "$newLIne"));

			} else {
				rootNode.setSongHtml(htmlEditor.getHtmlText());
				rootNode.setSongText(Helper.html2text(htmlEditor.getHtmlText()));
			}

		});
		rootNode.editModeProperty().addListener((obs, oldVal, newVal) -> {
			htmlEditor.setHtmlText(rootNode.getSongText());
		});
		radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (radioGroup.getSelectedToggle() != null) {
					LOG.info("toggle");
				}
			}
		});
	}

}
