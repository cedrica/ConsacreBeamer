package com.consacresdeleternel.consacrebeamer.factory;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook.CreateBookView;
import com.consacresdeleternel.consacrebeamer.maincontainer.book.songlist.SongListView;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SongListCellFactory implements Callback<ListView<Song>, ListCell<Song>> {
	private Node rootNode;

	public SongListCellFactory(Node rootNode) {
		this.rootNode = rootNode;
	}

	public ListCell<Song> call(ListView<Song> listView) {
		return new ListCell<Song>() {
			@Override
			protected void updateItem(Song item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setGraphic(null);
				} else {
					CheckBox checkBox = new CheckBox();
					if (rootNode instanceof SongListView) {
						((SongListView) rootNode).selectAllProperty().addListener((obs, oldVal, newVal) -> {
							checkBox.setSelected(newVal);
						});
						checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
							if (newVal) {
								((SongListView) rootNode).getSelectedSongItems().add(item);
							} else {
								((SongListView) rootNode).getSelectedSongItems().remove(item);
							}
						});
					} else if (rootNode instanceof CreateBookView) {
					}

					checkBox.setText(item.getSongTitle());
					setGraphic(checkBox);
				}
			}
		};
	}

}
