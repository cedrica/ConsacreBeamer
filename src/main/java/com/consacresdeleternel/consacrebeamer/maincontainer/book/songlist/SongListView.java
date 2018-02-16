package com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.BorderPane;

public class SongListView extends BorderPane {
	private ObjectProperty<FilteredList<Song>> songItems = new SimpleObjectProperty<>(
			new FilteredList<>(FXCollections.observableArrayList()));
	private ListProperty<Song> selectedSongItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Boolean> selectAll = new SimpleObjectProperty<>(false);
	private StringProperty songTitle = new SimpleStringProperty();

	public SongListView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ObjectProperty<Boolean> selectAllProperty() {
		return this.selectAll;
	}

	public final Boolean getSelectAll() {
		return this.selectAllProperty().get();
	}

	public final void setSelectAll(final Boolean selectAll) {
		this.selectAllProperty().set(selectAll);
	}

	public final ListProperty<Song> selectedSongItemsProperty() {
		return this.selectedSongItems;
	}

	public final ObservableList<Song> getSelectedSongItems() {
		return this.selectedSongItemsProperty().get();
	}

	public final void setSelectedSongItems(final ObservableList<Song> selectedSongItems) {
		this.selectedSongItemsProperty().set(selectedSongItems);
	}

	public final ObjectProperty<FilteredList<Song>> songItemsProperty() {
		return this.songItems;
	}

	public final FilteredList<Song> getSongItems() {
		return this.songItemsProperty().get();
	}

	public final void setSongItems(final FilteredList<Song> songItems) {
		this.songItemsProperty().set(songItems);
	}

	public final StringProperty songTitleProperty() {
		return this.songTitle;
	}
	

	public final String getSongTitle() {
		return this.songTitleProperty().get();
	}
	

	public final void setSongTitle(final String songTitle) {
		this.songTitleProperty().set(songTitle);
	}
	

}
