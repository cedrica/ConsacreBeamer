package com.consacresdeleternel.consacrebeamer.maincontainer.filteredsongs;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class FilteredSongsViewModel extends BorderPane {
	private ListProperty<Song> filteredSongs = new SimpleListProperty<>();
	
	private int titlePaneSongsCount;
	public FilteredSongsViewModel() {
		Helper.load(this);
	}
	public final ListProperty<Song> filteredSongsProperty() {
		return this.filteredSongs;
	}
	
	public final ObservableList<Song> getFilteredSongs() {
		return this.filteredSongsProperty().get();
	}
	
	public final void setFilteredSongs(final ObservableList<Song> filteredSongs) {
		this.filteredSongsProperty().set(filteredSongs);
	}
	public int getTitlePaneSongsCount() {
		return titlePaneSongsCount;
	}
	public void setTitlePaneSongsCount(int titlePaneSongsCount) {
		this.titlePaneSongsCount = titlePaneSongsCount;
	}
	
	
	
}
