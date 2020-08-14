package com.consacresdeleternel.consacrebeamer.maincontainer.bibel;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.maincontainer.chapterview.ChapterOrVersePageViewModel;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListViewModel;
import com.consacresdeleternel.consacrebeamer.maincontainer.textofverse.TextOfVerseViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.HBox;

public class BibelWidzardViewModel extends HBox {
	private ObjectProperty<CustomListViewModel> customListViewModel = new SimpleObjectProperty<>();
	private ObjectProperty<ChapterOrVersePageViewModel<Chapter>> chapterViewModel = new SimpleObjectProperty<>();
	private ObjectProperty<ChapterOrVersePageViewModel<Verse>> verseViewModel = new SimpleObjectProperty<>();
	private ObjectProperty<TextOfVerseViewModel> textOfVerseViewModel = new SimpleObjectProperty<>();
	
	
	public BibelWidzardViewModel(){
		Helper.load(this);
	}


	public final ObjectProperty<CustomListViewModel> customListViewProperty() {
		return this.customListViewModel;
	}
	


	public final CustomListViewModel getCustomListView() {
		return this.customListViewProperty().get();
	}
	


	public final void setCustomListView(final CustomListViewModel customListView) {
		this.customListViewProperty().set(customListView);
	}
	


	public final ObjectProperty<ChapterOrVersePageViewModel<Chapter>> chapterViewProperty() {
		return this.chapterViewModel;
	}
	


	public final ChapterOrVersePageViewModel<Chapter> getChapterView() {
		return this.chapterViewProperty().get();
	}
	


	public final void setChapterView(final ChapterOrVersePageViewModel<Chapter> chapterView) {
		this.chapterViewProperty().set(chapterView);
	}
	


	public final ObjectProperty<ChapterOrVersePageViewModel<Verse>> verseViewProperty() {
		return this.verseViewModel;
	}
	


	public final ChapterOrVersePageViewModel<Verse> getVerseView() {
		return this.verseViewProperty().get();
	}
	


	public final void setVerseView(final ChapterOrVersePageViewModel<Verse> verseView) {
		this.verseViewProperty().set(verseView);
	}


	public final ObjectProperty<TextOfVerseViewModel> textOfVerseViewModelProperty() {
		return this.textOfVerseViewModel;
	}
	


	public final TextOfVerseViewModel getTextOfVerseViewModel() {
		return this.textOfVerseViewModelProperty().get();
	}
	


	public final void setTextOfVerseViewModel(final TextOfVerseViewModel textOfVerseViewModel) {
		this.textOfVerseViewModelProperty().set(textOfVerseViewModel);
	}
	
	
	
	
}
