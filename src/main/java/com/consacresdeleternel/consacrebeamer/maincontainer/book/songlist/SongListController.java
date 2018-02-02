package com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;
import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Attachment;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.SongListCellFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SongListController implements Initializable {

	@FXML
	SongListView rootNode;
	@FXML
	CheckListView<Song> clvSongList;
	@FXML
	VBox vbSonglistViewer;
	@FXML
	CustomTextField ctfSongName;
	@FXML
	CheckBox checkBoxSelect;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clvSongList.setCellFactory(new SongListCellFactory(clvSongList));
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
	}

	@FXML
	public void onSelectAll() {
		if (checkBoxSelect.isSelected()) {
			clvSongList.getCheckModel().checkAll();
		} else {
			clvSongList.getCheckModel().clearChecks();
		}
	}

	@FXML public void onCloseHiddenSidePane() {
		rootNode.fireEvent(new BookEvent(BookEvent.SHOW_SONG_LIST));
	}

}
