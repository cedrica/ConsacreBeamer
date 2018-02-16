package com.consacresdeleternel.consacrebeamer.maincontainer.launcher;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class LauncherView extends BorderPane {
	private ObjectProperty<ProgressBar> progressBar = new SimpleObjectProperty<>();

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

}
