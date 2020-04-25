package com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.factory.SongListCellFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateBookController implements Initializable {

	@FXML
	CreateBookView rootNode;
	@FXML
	TextField tfBookName;
	@FXML
	CheckListView<Song> clvSongs;
	@FXML
	VBox vbSongsContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.bookNameProperty().bindBidirectional(tfBookName.textProperty());
		rootNode.invalidProperty().bind(tfBookName.textProperty().isEmpty());
		vbSongsContainer.visibleProperty().bind(rootNode.editModeProperty());
		rootNode.editModeProperty().addListener((obs, noldVal, newVal) -> {
			if (newVal) {
				rootNode.setPrefHeight(300);
				rootNode.setPrefWidth(400);
			} else {
				rootNode.setCenter(null);
			}
		});
		clvSongs.setCellFactory(new SongListCellFactory(rootNode));
		clvSongs.itemsProperty().bind(rootNode.songItemsProperty());

		clvSongs.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		});

	}


}
