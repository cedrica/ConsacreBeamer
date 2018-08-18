package com.consacresdeleternel.consacrebeamer.maincontainer.launcher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class LauncherController implements Initializable {

	@FXML
	LauncherView rootNode;
	@FXML
	ProgressBar progressBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.setProgressBar(progressBar);
	}

}
