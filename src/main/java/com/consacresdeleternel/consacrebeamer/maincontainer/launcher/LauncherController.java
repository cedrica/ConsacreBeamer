package com.consacresdeleternel.consacrebeamer.maincontainer.launcher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class LauncherController implements Initializable {

	@FXML
	LauncherView rootNode;
	@FXML
	ProgressBar progressBar;
	@FXML WebView webview;

     
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.setProgressBar(progressBar);
		rootNode.setWebView(webview);
	    WebEngine webEngine = webview.getEngine();
        webEngine.load(this.getClass().getResource("/html/launch.html").toString());
	}

}
