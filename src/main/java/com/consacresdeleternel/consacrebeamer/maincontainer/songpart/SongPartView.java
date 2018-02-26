package com.consacresdeleternel.consacrebeamer.maincontainer.songpart;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class SongPartView extends StackPane {
	private ObjectProperty<Image> backgroundImage = new SimpleObjectProperty<>();
	private StringProperty text = new SimpleStringProperty();
	private StringProperty html = new SimpleStringProperty();
	private ObjectProperty<Integer> index = new SimpleObjectProperty<>();
	private ObjectProperty<ToggleButton> tbSelectedText = new SimpleObjectProperty<>();
	
	public SongPartView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<Image> backgroundImageProperty() {
		return this.backgroundImage;
	}

	public final Image getBackgroundImage() {
		return this.backgroundImageProperty().get();
	}

	public final void setBackgroundImage(final Image backgroundImage) {
		this.backgroundImageProperty().set(backgroundImage);
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

	public final StringProperty htmlProperty() {
		return this.html;
	}

	public final String getHtml() {
		return this.htmlProperty().get();
	}

	public final void setHtml(final String html) {
		this.htmlProperty().set(html);
	}

	public final ObjectProperty<Integer> indexProperty() {
		return this.index;
	}

	public final Integer getIndex() {
		return this.indexProperty().get();
	}

	public final void setIndex(final Integer index) {
		this.indexProperty().set(index);
	}

	public final ObjectProperty<ToggleButton> tbSelectedTextProperty() {
		return this.tbSelectedText;
	}
	

	public final ToggleButton getTbSelectedText() {
		return this.tbSelectedTextProperty().get();
	}
	

	public final void setTbSelectedText(final ToggleButton tbSelectedText) {
		this.tbSelectedTextProperty().set(tbSelectedText);
	}
	

}
