package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.T;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.ISuggestionRequest;
import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class CopyRightsController implements Initializable{

	@FXML CopyRightsView rootNode;
	@FXML TextField tfTempo;
	@FXML TextField tfKey;
	@FXML TextField tfAdditionalInfo;
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
	@FXML TextField tfSongBuch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerBindings();
		TextFields.bindAutoCompletion(tfSongBuch,new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<T>>() {
			@Override
			public Collection<T> call(ISuggestionRequest param) {
				return null;
			}
		});
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
		rootNode.songBuchProperty().bindBidirectional(tfSongBuch.textProperty());
		rootNode.additionalInfoProperty().bindBidirectional(tfAdditionalInfo.textProperty());
		rootNode.keyProperty().bindBidirectional(tfKey.textProperty());
		rootNode.tempoProperty().bindBidirectional(tfTempo.textProperty());
		rootNode.invalidProperty().bind(tfTitle.textProperty().isEmpty().or(rootNode.textAutorProperty().isEmpty()).or(rootNode.copyrightProperty().isEmpty()));
	}

	@FXML public void onSetBackground() {}
}
