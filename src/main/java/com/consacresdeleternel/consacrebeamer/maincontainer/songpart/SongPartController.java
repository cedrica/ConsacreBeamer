package com.consacresdeleternel.consacrebeamer.maincontainer.songpart;

import java.net.URL;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.events.SongPartEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.scene.control.ToggleButton;

public class SongPartController implements Initializable {

	@FXML
	SongPartView rootNode;
	@FXML
	WebView wvTextPart;
	@FXML ToggleButton tbSelectText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbSelectText.selectedProperty().addListener((obs, oldVal, newVal)->{
			if(newVal){
				rootNode.setStyle("-fx-border-color:lightblue;-fx-border-width:5px");
				rootNode.fireEvent(new SongPartEvent(SongPartEvent.SHOW_SONG_PART));
			}else{
				rootNode.setStyle(null);
			}
		});
		rootNode.setTbSelectedText(tbSelectText);
		rootNode.textProperty().addListener((obs, oldVal, newVal) -> {
			wvTextPart.getEngine().loadContent(newVal);
		});
	}

	@FXML
	public void onShowDiaShow(ActionEvent evt) {
		rootNode.fireEvent(new SongPartEvent(SongPartEvent.SHOW_SONG_PART));
	}

}
