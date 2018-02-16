package com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Attachment;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.factory.SongListCellFactory;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SongListController implements Initializable {

	@FXML
	SongListView rootNode;
	@FXML
	ListView<Song> clvSongList;
	@FXML
	VBox vbSonglistViewer;
	@FXML
	CustomTextField ctfSongName;
	@FXML
	CheckBox checkBoxSelect;
	@FXML
	Button btnImportSong;
	@FXML
	Button btnExportSong;
	@FXML
	Button btnEditSong;
	@FXML
	Button btnDeleteSong;
	@FXML
	Button btnAddToListView;
	@FXML
	Label lblBookName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clvSongList.setCellFactory(new SongListCellFactory(rootNode));
		clvSongList.itemsProperty().bind(rootNode.songItemsProperty());
		clvSongList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			vbSonglistViewer.getChildren().clear();
			for (Attachment file : newVal.getAttachements()) {
				Image image = new Image(file.getAttachement().toURI().toString());
				ImageView imageView = new ImageView(image);
				imageView.getStyleClass().add("card");
				vbSonglistViewer.getChildren().add(imageView);
			}
		});
		btnDeleteSong.disableProperty().bind(Bindings.createBooleanBinding(() -> {
			return rootNode.getSelectedSongItems().isEmpty();
		}, rootNode.selectedSongItemsProperty()));
		btnAddToListView.disableProperty().bind(Bindings.createBooleanBinding(() -> {
			return rootNode.getSelectedSongItems().isEmpty();
		}, rootNode.selectedSongItemsProperty()));
		btnEditSong.disableProperty().bind(Bindings.createBooleanBinding(() -> {
			return rootNode.getSelectedSongItems().size() != 1;
		}, rootNode.selectedSongItemsProperty()));
		rootNode.searchedSongNameProperty().bind(ctfSongName.textProperty());
		lblBookName.textProperty().bind(rootNode.bookNameProperty());

	}

	@FXML
	public void onSelectAll() {
		rootNode.setSelectAll(checkBoxSelect.isSelected());
	}

	@FXML
	public void onCloseHiddenSidePane() {
		rootNode.fireEvent(new BookEvent(BookEvent.SHOW_SONG_LIST));
	}

	@FXML
	public void onDeleteSong() {
		rootNode.fireEvent(new BookEvent(BookEvent.DELETE_SONGS, rootNode.getSelectedSongItems()));
	}

	@FXML
	public void onExportSong() {
	}

	@FXML
	public void onImportSong() {
	}

	@FXML
	public void onEditSong() {
	}

	@FXML
	public void onSearchSong() {
		rootNode.getSongItems().setPredicate(p -> p.getSongTitle().startsWith(rootNode.getSearchedSongName()));
	}

	@FXML
	public void onAddToListView() {
		rootNode.fireEvent(new BookEvent(BookEvent.SHOW_SELECTED_SONGS, rootNode.getSelectedSongItems()));
		rootNode.fireEvent(new BookEvent(BookEvent.SHOW_SONG_LIST));
	}

}
