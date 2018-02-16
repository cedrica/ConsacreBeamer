package com.consacresdeleternel.consacrebeamer.maincontainer.songpart;

import java.net.URL;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.events.SongPartEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

public class SongPartController implements Initializable {

	@FXML
	SongPartView rootNode;
	@FXML
	WebView wvTextPart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// rootNode.backgroundImageProperty().addListener((obs,oldVal,newVal)->{
		// ImageView imageView = new ImageView(newVal);
		// imageView.fitWidthProperty().bind(rootNode.widthProperty());
		// imageView.fitHeightProperty().bind(rootNode.heightProperty());
		// lblBackgroundImage.setGraphic(imageView);
		// });

		rootNode.textProperty().addListener((obs, oldVal, newVal) -> {
			wvTextPart.getEngine().loadContent(newVal);
		});
	}

	@FXML
	public void onShowDiaShow(MouseEvent evt) {
		if (evt.getButton() == MouseButton.PRIMARY) {
			rootNode.fireEvent(new SongPartEvent(SongPartEvent.SHOW_SONG_PART));
		}
	}

}
