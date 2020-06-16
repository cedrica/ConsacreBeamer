package com.consacresdeleternel.consacrebeamer.maincontainer.filterforsongscategory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview.AlbumListViewViewModel;
import com.consacresdeleternel.consacrebeamer.maincontainer.filteredsongs.FilteredSongsViewModel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class FilterForSongsCategoryView implements Initializable {

	@FXML FilterForSongsCategoryViewModel rootNode;
	@FXML CheckBox checkBoxSelectAll;
	@FXML TextField tfSearch;
	@FXML CustomTextField ctfSearchSongs;
	@FXML TilePane tpSongs;
	@FXML AlbumListViewViewModel albumListViewViewModel;
	@FXML FilteredSongsViewModel filteredSongsViewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.albumsProperty().addListener((obs, oldVal, books) -> {
			albumListViewViewModel.setBooks(books);	
			List<Song> songs = new ArrayList<>();
			books.forEach(book -> {
				songs.addAll(book.getSongs());
			});
			filteredSongsViewModel.setFilteredSongs(FXCollections.observableList(songs));
		});
		
		albumListViewViewModel.selectedAlbumsProperty().addListener((obs, oldVal, books) -> {
			List<Song> songs = new ArrayList<>();
			books.forEach(book -> {
				songs.addAll(book.getSongs());
			});
			filteredSongsViewModel.setFilteredSongs(FXCollections.observableList(songs));	
		});
		
	}

}
