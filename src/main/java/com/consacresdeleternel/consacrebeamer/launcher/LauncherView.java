package com.consacresdeleternel.consacrebeamer.launcher;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class LauncherView extends AnchorPane {
	private ObjectProperty<ProgressBar> progressBar = new SimpleObjectProperty<>();
	private WebView webView;

	public LauncherView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<ProgressBar> progressBarProperty() {
		return this.progressBar;
	}

	public final ProgressBar getProgressBar() {
		return this.progressBarProperty().get();
	}

	public final void setProgressBar(final ProgressBar progressBar) {
		this.progressBarProperty().set(progressBar);
	}

	public WebView getWebView() {
		return webView;
	}

	public void setWebView(WebView webView) {
		this.webView = webView;
	}



}
