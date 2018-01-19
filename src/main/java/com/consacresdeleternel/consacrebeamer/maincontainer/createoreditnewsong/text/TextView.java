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

public class TextView extends BorderPane{
	private StringProperty title = new SimpleStringProperty();
	private StringProperty songText = new SimpleStringProperty();
	private StringProperty songhtmlText = new SimpleStringProperty();
	private ObjectProperty<byte[]> songHtmlByte = new SimpleObjectProperty<>();
	private StringProperty songhtmlBase64 = new SimpleStringProperty();
	private BooleanProperty  invalid = new SimpleBooleanProperty();
	private ObjectProperty<Boolean> editMode = new SimpleObjectProperty<>(false);
	public  TextView(){
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

	public final StringProperty songhtmlTextProperty() {
		return this.songhtmlText;
	}
	

	public final String getSonghtmlText() {
		return this.songhtmlTextProperty().get();
	}
	

	public final void setSonghtmlText(final String songhtmlText) {
		this.songhtmlTextProperty().set(songhtmlText);
	}

	public final StringProperty songhtmlBase64Property() {
		return this.songhtmlBase64;
	}
	

	public final String getSonghtmlBase64() {
		return this.songhtmlBase64Property().get();
	}
	

	public final void setSonghtmlBase64(final String songhtmlBase64) {
		this.songhtmlBase64Property().set(songhtmlBase64);
	}

	public final ObjectProperty<byte[]> songHtmlByteProperty() {
		return this.songHtmlByte;
	}
	

	public final byte[] getSongHtmlByte() {
		return this.songHtmlByteProperty().get();
	}
	

	public final void setSongHtmlByte(final byte[] songhtmlByte) {
		this.songHtmlByteProperty().set(songhtmlByte);
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
	

}
