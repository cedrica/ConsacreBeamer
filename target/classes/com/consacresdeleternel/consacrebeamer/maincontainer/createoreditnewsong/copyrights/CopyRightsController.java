package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CopyRightsController implements Initializable{

	@FXML CopyRightsView rootNode;
	@FXML TextField tfTempo;
	@FXML TextField tfKey;
	@FXML TextField tfAdditionalInfo;
	@FXML TextField tfSongBuchNr;
	@FXML TextField tfBibleReferenz;
	@FXML TextField tfCCLiNr;
	@FXML TextField tfRight;
	@FXML TextField tfNationalCopyr;
	@FXML TextField tfCopyright;
	@FXML TextField tfTranslation;
	@FXML TextField tfMusik;
	@FXML TextField tfTextAutor;
	@FXML TextField tfOriginalTitle;
	@FXML TextField tfTitle;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerBindings();
	}

	private void registerBindings() {
		rootNode.titleProperty().bindBidirectional(tfTitle.textProperty());
		rootNode.originalTitleProperty().bindBidirectional(tfOriginalTitle.textProperty());
		rootNode.textAutorProperty().bindBidirectional(tfTextAutor.textProperty());
		rootNode.musikProperty().bindBidirectional(tfMusik.textProperty());
		rootNode.translationProperty().bindBidirectional(tfTranslation.textProperty());
		rootNode.copyrightProperty().bindBidirectional(tfCopyright.textProperty());
		rootNode.nationalCopyrProperty().bindBidirectional(tfNationalCopyr.textProperty());
		rootNode.rightsProperty().bindBidirectional(tfRight.textProperty());
		rootNode.cCLiNrProperty().bindBidirectional(tfCCLiNr.textProperty());
		rootNode.bibleReferenzProperty().bindBidirectional(tfBibleReferenz.textProperty());
		rootNode.songBuchNrProperty().bindBidirectional(tfSongBuchNr.textProperty());
		rootNode.additionalInfoProperty().bindBidirectional(tfAdditionalInfo.textProperty());
		rootNode.keyProperty().bindBidirectional(tfKey.textProperty());
		rootNode.tempoProperty().bindBidirectional(tfTempo.textProperty());
		rootNode.invalidProperty().bind(tfTitle.textProperty().isEmpty().or(rootNode.textAutorProperty().isEmpty()).or(rootNode.copyrightProperty().isEmpty()));
	}

	@FXML public void onSetBackground() {}

	@FXML public void onCancel() {}

	@FXML public void onOk() {}

}
