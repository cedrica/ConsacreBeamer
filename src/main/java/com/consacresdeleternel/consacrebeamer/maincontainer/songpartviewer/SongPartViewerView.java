package com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

public class SongPartViewerView extends BorderPane {
	private StringProperty text = new SimpleStringProperty();

	public SongPartViewerView() {
		Helper.load(this, Localization.getDefault());
	}

	public final StringProperty textProperty() {
		return this.text;
	}

	public final String getText() {
		return this.textProperty().get();
	}

	public final void setText(final String text) {
		this.textProperty().set(text);
	}

}
