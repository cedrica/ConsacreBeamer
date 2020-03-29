package com.consacresdeleternel.consacrebeamer.maincontainer.textofverse;


import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class TextOfVerseViewModel extends ScrollPane{
	private ObjectProperty<Chapter> selectedChapter = new SimpleObjectProperty<>();
	private ObjectProperty<Verse> selectedVerse = new SimpleObjectProperty<>();
	public TextOfVerseViewModel() {
		Helper.load(this, Localization.getDefault());
	}
	public final ObjectProperty<Chapter> selectedChapterProperty() {
		return this.selectedChapter;
	}
	
	public final Chapter getSelectedChapter() {
		return this.selectedChapterProperty().get();
	}
	
	public final void setSelectedChapter(final Chapter selectedChapter) {
		this.selectedChapterProperty().set(selectedChapter);
	}
	
	public final ObjectProperty<Verse> selectedVerseProperty() {
		return this.selectedVerse;
	}
	
	public final Verse getSelectedVerse() {
		return this.selectedVerseProperty().get();
	}
	
	public final void setSelectedVerse(final Verse selectedVerse) {
		this.selectedVerseProperty().set(selectedVerse);
	}
	
	

}
