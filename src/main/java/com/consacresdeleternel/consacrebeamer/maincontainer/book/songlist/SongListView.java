package com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class SongListView extends BorderPane {
	private ListProperty<Song> songItems = new SimpleListProperty<>(FXCollections.observableArrayList());

	public SongListView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ListProperty<Song> songItemsProperty() {
		return this.songItems;
	}

	public final ObservableList<Song> getSongItems() {
		return this.songItemsProperty().get();
	}

	public final void setSongItems(final ObservableList<Song> songItems) {
		this.songItemsProperty().set(songItems);
	}

}
