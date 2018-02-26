package com.consacresdeleternel.consacrebeamer.manager;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer.SongPartViewerView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

@Singleton
public class ValueObjectManager {
	private ObjectProperty<Map<Node, BooleanProperty>> saveAsBinder = new SimpleObjectProperty<>(new HashMap<>());

	private ListProperty<Book> bookItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Song> songItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<SongPartViewerView> songPartViewerView = new SimpleObjectProperty<>();

	public final ListProperty<Book> bookItemsProperty() {
		return this.bookItems;
	}

	public final ObservableList<Book> getBookItems() {
		return this.bookItemsProperty().get();
	}

	public final void setBookItems(final ObservableList<Book> bookItems) {
		this.bookItemsProperty().set(bookItems);
	}

	public final ObjectProperty<Map<Node, BooleanProperty>> saveAsBinderProperty() {
		return this.saveAsBinder;
	}

	public final Map<Node, BooleanProperty> getSaveAsBinder() {
		return this.saveAsBinderProperty().get();
	}

	public final void setSaveAsBinder(final Map<Node, BooleanProperty> saveAsBinder) {
		this.saveAsBinderProperty().set(saveAsBinder);
	}

	public final ObjectProperty<SongPartViewerView> songPartViewerViewProperty() {
		return this.songPartViewerView;
	}

	public final SongPartViewerView getSongPartViewerView() {
		return this.songPartViewerViewProperty().get();
	}

	public final void setSongPartViewerView(final SongPartViewerView songPartViewerView) {
		this.songPartViewerViewProperty().set(songPartViewerView);
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
