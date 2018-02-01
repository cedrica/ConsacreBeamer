package com.consacresdeleternel.consacrebeamer.maincontainer.book.createbook;

import org.controlsfx.control.CheckListView;

import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class SongListCellFactory implements Callback<ListView<Song>, ListCell<Song>> {
	private CheckListView<Song> checkListView;

	public SongListCellFactory(CheckListView<Song> checkListView) {
		this.checkListView = checkListView;
	}

	public ListCell<Song> call(ListView<Song> listView) {
		return new CheckBoxListCell<Song>(item -> checkListView.getItemBooleanProperty(item),
				new StringConverter<Song>() {
					@Override
					public Song fromString(String string) {
						return null;
					}

					@Override
					public String toString(Song song) {
						return song.getSongTitle();
					}
				});
	}

}
