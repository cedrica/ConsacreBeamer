package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.common.Helper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class TextController implements Initializable {
	private static final Logger LOG = Logger.getLogger(TextController.class);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		webView = (WebView) htmlEditor.lookup(".web-view");
		rootNode.titleProperty().bindBidirectional(tfTitle.textProperty());
		registerListener();
		rootNode.invalidProperty().bind(tfTitle.textProperty().isEmpty().or(rootNode.songTextProperty().isEmpty()));
	}

	private void registerListener() {
		webView.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
			rootNode.setSonghtmlBase64(Helper.convertStringToBase64(htmlEditor.getHtmlText()));
			rootNode.setSonghtmlText(htmlEditor.getHtmlText());
			rootNode.setSongText(Helper.html2text(htmlEditor.getHtmlText()));
		});
		radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (radioGroup.getSelectedToggle() != null) {
					LOG.info(radioGroup.getSelectedToggle());
				}
			}
		});
	}

	@FXML
	public void onSetBackground() {
	}


}
