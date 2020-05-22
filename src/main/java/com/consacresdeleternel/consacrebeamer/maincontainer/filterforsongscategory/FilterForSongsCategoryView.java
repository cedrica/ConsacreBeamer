package com.consacresdeleternel.consacrebeamer.maincontainer.filterforsongscategory;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.scene.layout.TilePane;
import com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview.AlbumListViewViewModel;

public class FilterForSongsCategoryView implements Initializable {

	@FXML FilterForSongsCategoryViewModel rootNode;
	@FXML CheckBox checkBoxSelectAll;
	@FXML TextField tfSearch;
	@FXML CustomTextField ctfSearchSongs;
	@FXML TilePane tpSongs;
	@FXML ListView<Book> lvAlbums;
	@FXML AlbumListViewViewModel albumListViewViewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
