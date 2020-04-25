package com.consacresdeleternel.consacrebeamer.maincontainer.chapterview;

import com.consacresdeleternel.consacrebeamer.Helper;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListBasicObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class ChapterOrVersePageViewModel<T> extends BorderPane {
	private StringProperty title = new SimpleStringProperty();
	private ListProperty<T> chaptersOrVerses = new SimpleListProperty<>();
	private BooleanProperty isVerse = new SimpleBooleanProperty(false);
	private ObjectProperty<CustomListBasicObject> selectedBibel = new SimpleObjectProperty<CustomListBasicObject>();
	
	public ChapterOrVersePageViewModel () {
		Helper.load(this);
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

	public final BooleanProperty isVerseProperty() {
		return this.isVerse;
	}
	
	public final boolean isIsVerse() {
		return this.isVerseProperty().get();
	}
	
	public final void setIsVerse(final boolean isVerse) {
		this.isVerseProperty().set(isVerse);
	}
	public final ListProperty<T> chaptersOrVersesProperty() {
		return this.chaptersOrVerses;
	}
	
	public final ObservableList<T> getChaptersOrVerses() {
		return this.chaptersOrVersesProperty().get();
	}
	
	public final void setChaptersOrVerses(final ObservableList<T> chaptersOrVerses) {
		this.chaptersOrVersesProperty().set(chaptersOrVerses);
	}
	
	public final ObjectProperty<CustomListBasicObject> selectedBibelProperty() {
		return this.selectedBibel;
	}
	

	public final CustomListBasicObject getSelectedBibel() {
		return this.selectedBibelProperty().get();
	}
	

	public final void setSelectedBibel(final CustomListBasicObject selectedBibel) {
		this.selectedBibelProperty().set(selectedBibel);
	}
	
	
}
