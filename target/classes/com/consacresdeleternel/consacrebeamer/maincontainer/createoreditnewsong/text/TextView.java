package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.BorderPane;

public class TextView extends BorderPane{
	private StringProperty title = new SimpleStringProperty();
	private StringProperty songText = new SimpleStringProperty();
	private BooleanProperty  invalid = new SimpleBooleanProperty();
	
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
	

}
