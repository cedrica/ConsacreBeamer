package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.converter.BookConverter;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.factory.BookCellFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CopyRightsController implements Initializable {

	@FXML
	CopyRightsView rootNode;
	@FXML
	TextField tfTempo;
	@FXML
	TextField tfKey;
	@FXML
	TextField tfAdditionalInfo;
	@FXML
	TextField tfBibleReferenz;
	@FXML
	TextField tfCCLiNr;
	@FXML
	TextField tfRight;
	@FXML
	TextField tfNationalCopyr;
	@FXML
	TextField tfCopyright;
	@FXML
	TextField tfTranslation;
	@FXML
	TextField tfMusik;
	@FXML
	TextField tfTextAutor;
	@FXML
	TextField tfOriginalTitle;
	@FXML
	TextField tfTitle;
	@FXML
	ComboBox<Book> cbSongBuch;
	private ValidationSupport validationSupport;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerBindings();
		registerValidation();
	}

	private void registerValidation() {
		validationSupport = new ValidationSupport();
		Platform.runLater(() -> {
			validationSupport.registerValidator(tfTitle,
					Validator.createEmptyValidator(Localization.asKey("csb.CopyRightsView.titleIsRequired")));
			validationSupport.registerValidator(cbSongBuch,
					Validator.createEmptyValidator(Localization.asKey("csb.CopyRightsView.BuchIsRequired")));
			validationSupport.registerValidator(tfCopyright,
					Validator.createEmptyValidator(Localization.asKey("csb.CopyRightsView.CopyRightIsRequired")));
			rootNode.invalidProperty().bind(validationSupport.invalidProperty());
		});
		cbSongBuch.setConverter(new BookConverter());
		cbSongBuch.setCellFactory(new BookCellFactory());
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
		rootNode.songBuchProperty().bindBidirectional(cbSongBuch.valueProperty());
		rootNode.additionalInfoProperty().bindBidirectional(tfAdditionalInfo.textProperty());
		rootNode.keyProperty().bindBidirectional(tfKey.textProperty());
		rootNode.tempoProperty().bindBidirectional(tfTempo.textProperty());
		cbSongBuch.itemsProperty().bind(rootNode.bookItemsProperty());
	}

}
