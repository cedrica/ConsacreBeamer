package com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class SongPartViewerController implements Initializable {

	@FXML SongPartViewerView rootNode;
	@FXML WebView webView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.textProperty().addListener((obs,oldVal,newVal)->{
			webView.getEngine().loadContent(newVal);
		});
	}

}
