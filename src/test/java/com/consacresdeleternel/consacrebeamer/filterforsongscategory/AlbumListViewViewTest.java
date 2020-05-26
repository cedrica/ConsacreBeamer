package com.consacresdeleternel.consacrebeamer.filterforsongscategory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview.AlbumListViewViewModel;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlbumListViewViewTest  extends ApplicationTest{

	private AlbumListViewViewModel albumListViewViewModel;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		albumListViewViewModel = new AlbumListViewViewModel();
		stage.setScene(new Scene(albumListViewViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void selectedItemName() {
		albumListViewViewModel.setBooks(FXCollections.observableArrayList(mockSomeBooks(5)));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Book> mockSomeBooks(int number) {
		List<Book> books = new ArrayList<>();
		for (int i = 1; i <= number; i++) {
			Book book = new Book();
			book.setTitle("Book" + i);
			List<Song> songs = mockNSongs(i*2);
			book.setSongs(songs);
			books.add(book);
		}
		return books;
	}
	
	public Song mockSong(String title) {
		Song song = new Song();
		song.setSongTitle(title);
		return song;
	}
	
	public List<Song> mockNSongs(int n){
		List<Song> songs = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			songs.add(mockSong("Song" + i));
		}
		return songs;
	}
}
