package com.consacresdeleternel.consacrebeamer.maincontainer.filterforsongscategory;

import com.consacresdeleternel.consacrebeamer.Helper;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

public class FilterForSongsCategoryViewModel extends HBox
{
	private BooleanProperty selectAllAlbum = new SimpleBooleanProperty(false);
	private ListProperty<Book> albums = new SimpleListProperty<>();
	private ListProperty<Song> songs = new SimpleListProperty<>();
	
	public FilterForSongsCategoryViewModel() {
		Helper.load(this);
	}

	public final BooleanProperty selectAllAlbumProperty() {
		return this.selectAllAlbum;
	}
	

	public final boolean isSelectAllAlbum() {
		return this.selectAllAlbumProperty().get();
	}
	

	public final void setSelectAllAlbum(final boolean selectAllAlbum) {
		this.selectAllAlbumProperty().set(selectAllAlbum);
	}

	public final ListProperty<Book> albumsProperty() {
		return this.albums;
	}
	

	public final ObservableList<Book> getAlbums() {
		return this.albumsProperty().get();
	}
	

	public final void setAlbums(final ObservableList<Book> albums) {
		this.albumsProperty().set(albums);
	}
	

	public final ListProperty<Song> songsProperty() {
		return this.songs;
	}
	

	public final ObservableList<Song> getSongs() {
		return this.songsProperty().get();
	}
	

	public final void setSongs(final ObservableList<Song> songs) {
		this.songsProperty().set(songs);
	}
	
	
	
	
}
