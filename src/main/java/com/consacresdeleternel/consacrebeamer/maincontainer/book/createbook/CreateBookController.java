package com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.CreateOrEditNewSongEvent;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class CreateBookController implements Initializable {

	@FXML
	CreateBookView rootNode;
	@FXML
	TextField tfBookName;
	@FXML
	CheckBox cbSelectAll;
	@FXML
	CheckListView<Song> clvSongs;
	@FXML
	VBox vbSongsContainer;
	@FXML
	Button btnEdit;
	@FXML
	Button btnDelete;

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
		clvSongs.setCellFactory(new SongListCellFactory(clvSongs));
		clvSongs.itemsProperty().bind(rootNode.songItemsProperty());

		clvSongs.getCheckModel().getCheckedItems().addListener(new  ListChangeListener<Song>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Song> c) {
				btnEdit.setDisable(isOneChecked());				
			}
		});

	}

	private boolean isOneChecked() {
		return clvSongs.getCheckModel().getCheckedItems().size() == 0;
	}

	@FXML
	public void onEditSong() {
		rootNode.fireEvent(new CreateOrEditNewSongEvent(CreateOrEditNewSongEvent.EDIT_SONG));
	}

	@FXML
	public void onRemoveSongs() {
		rootNode.fireEvent(new CreateOrEditNewSongEvent(CreateOrEditNewSongEvent.REMOVE_SONG));
	}

	@FXML
	public void onSelectAll() {
		if (cbSelectAll.isSelected()) {
			clvSongs.getCheckModel().checkAll();
		} else {
			clvSongs.getCheckModel().clearChecks();
		}
	}

}
