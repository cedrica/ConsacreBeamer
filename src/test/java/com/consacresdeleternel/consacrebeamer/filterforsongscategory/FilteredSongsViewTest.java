package com.consacresdeleternel.consacrebeamer.filterforsongscategory;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.maincontainer.filteredsongs.FilteredSongsViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FilteredSongsViewTest extends ApplicationTest {

	private FilteredSongsViewModel filteredSongsViewModel;

	@Override
	public void start(Stage stage) throws Exception {
		filteredSongsViewModel = new FilteredSongsViewModel();
		filteredSongsViewModel.setFilteredSongs(FXCollections.observableArrayList(mockNSongs(5)));
		stage.setScene(new Scene(filteredSongsViewModel, 800, 600));
		stage.show();
	}


	@Before
	public void setUp() {
	}
	
	@Test
	public void checkItemCount() {
		Platform.runLater(() -> {
			TextField search = (TextField)filteredSongsViewModel.lookup("#ctfSearchSongs");
			search.setText("Song1");
			assertEquals(filteredSongsViewModel.getTitlePaneSongsCount(), 1);
		});
	}
	

	@Test
	public void worshipSelected() {
		Platform.runLater(() -> {
			RadioButton rbWorship = (RadioButton)filteredSongsViewModel.lookup("#rbWorship");
			rbWorship.setSelected(true);
			assertEquals(filteredSongsViewModel.getTitlePaneSongsCount(), 2);
		});
	}

	@Test
	public void adorationSelected() {
		Platform.runLater(() -> {
			RadioButton rbAdoration = (RadioButton)filteredSongsViewModel.lookup("#rbAdoration");
			rbAdoration.setSelected(true);
			assertEquals(filteredSongsViewModel.getTitlePaneSongsCount(), 3);
		});
	}
	
	public Song mockSong(String title) {
		Song song = new Song();
		song.setSongTitle(title);
		return song;
	}

	public List<Song> mockNSongs(int n) {
		List<Song> songs = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			Song song = mockSong("Song" + i);
			if(i % 2 == 0) {
				song.setSongCategoryId(1);
			} else {
				song.setSongCategoryId(2);	
			}
			songs.add(song);
		}
		return songs;
	}
}