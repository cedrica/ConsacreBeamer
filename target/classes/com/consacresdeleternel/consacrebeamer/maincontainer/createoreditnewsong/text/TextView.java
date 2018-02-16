package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

public class TextView extends BorderPane {
	private StringProperty title = new SimpleStringProperty();
	private StringProperty songText = new SimpleStringProperty();
	private StringProperty songHtml = new SimpleStringProperty();
	private BooleanProperty invalid = new SimpleBooleanProperty();
	private ObjectProperty<Boolean> editMode = new SimpleObjectProperty<>(false);

	public TextView() {
		Helper.load(this, Localization.getDefault());
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}

	public final StringProperty songTextProperty() {
		return this.songText;
	}

	public final String getSongText() {
		return this.songTextProperty().get();
	}

	public final void setSongText(final String songText) {
		this.songTextProperty().set(songText);
	}

	public final BooleanProperty invalidProperty() {
		return this.invalid;
	}

	public final boolean isInvalid() {
		return this.invalidProperty().get();
	}

	public final void setInvalid(final boolean invalid) {
		this.invalidProperty().set(invalid);
	}

	public final ObjectProperty<Boolean> editModeProperty() {
		return this.editMode;
	}

	public final Boolean isEditMode() {
		return this.editModeProperty().get();
	}

	public final void setEditMode(final Boolean editMode) {
		this.editModeProperty().set(editMode);
	}

	public final StringProperty songHtmlProperty() {
		return this.songHtml;
	}

	public final String getSongHtml() {
		return this.songHtmlProperty().get();
	}

	public final void setSongHtml(final String songHtml) {
		this.songHtmlProperty().set(songHtml);
	}

}
