package com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview;

import com.consacresdeleternel.consacrebeamer.Helper;
import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class AlbumListViewViewModel extends BorderPane {
	private ListProperty<Book> selectedAlbums = new SimpleListProperty<>();
	private BooleanProperty selectAllAlbum = new SimpleBooleanProperty(false);
	private ListProperty<Book> Books = new SimpleListProperty<>();
	
	public AlbumListViewViewModel() {
		Helper.load(this);
	}
	public final BooleanProperty selectAllAlbumProperty() {
		return this.selectAllAlbum;
	}
	
	public final boolean isSelectAllBook() {
		return this.selectAllAlbumProperty().get();
	}
	
	public final void setSelectAllAlbum(final boolean selectAllBook) {
		this.selectAllAlbumProperty().set(selectAllBook);
	}
	
	public final ListProperty<Book> albumsProperty() {
		return this.Books;
	}
	
	public final ObservableList<Book> getBooks() {
		return this.albumsProperty().get();
	}
	
	public final void setBooks(final ObservableList<Book> Books) {
		this.albumsProperty().set(Books);
	}
	public final ListProperty<Book> selectedAlbumsProperty() {
		return this.selectedAlbums;
	}
	
	public final ObservableList<Book> getSelectedAlbums() {
		return this.selectedAlbumsProperty().get();
	}
	
	public final void setSelectedAlbums(final ObservableList<Book> selectedAlbums) {
		this.selectedAlbumsProperty().set(selectedAlbums);
	}
	
	
}
