package com.consacresdeleternel.consacrebeamer.bibel;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.Chapter;
import com.consacresdeleternel.consacrebeamer.Verse;
import com.consacresdeleternel.consacrebeamer.bibel.BibelEvent;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomListViewModelTest extends ApplicationTest{

	private CustomListViewModel customListViewModel;
	
	private String[] names = {"Genese","Exode","Levitiques","Nombres","Deuteronome","Job","Esdras","Samuel","Juges"};
	private int[] chapNum = {12,33,44,13,53,23,44,23,43};
	private int[] verses = {34,33,44,13,53,23,22,43,22};
	
	@Override
	public void start(Stage stage) throws Exception {
		customListViewModel = new CustomListViewModel();
		List<BibelBook> books = createBibleBooks();
		customListViewModel.setBibelBooks(FXCollections.observableArrayList(books));
		customListViewModel.setListTitel("Livres");
		customListViewModel.setSearchVisible(true);
		stage.setScene(new Scene(customListViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void selectedItemName() {
		Platform.runLater(() ->{
			customListViewModel.addEventHandler(BibelEvent.CUSTOM_LIST, event ->{
				event.consume();
				assertEquals("Levitiques", event.getCustomListBasicObject().getName());
			});
			customListViewModel.setSelectIndex(2);
		});
	}
	
	@Test
	public void filterBySearchName() {
		Platform.runLater(() -> {
			TextField tfSerach = (TextField) customListViewModel.lookup("#tfSearch");
			clickOn(tfSerach).write("Levitiques");
			assertEquals("Levitiques", customListViewModel.getSearch());
		});
	}

	//TODO
	//This function belongs to an Helper class and muss be tested too
	// at the moment it construct is wrong
	private List<BibelBook> createBibleBooks() {
		List<BibelBook> bibelBooks = new ArrayList<BibelBook>();
		for (String name: names) {
			BibelBook bibelBook = new BibelBook();
			bibelBook.setName(name);
			for (int i = 0; i < chapNum.length; i++) {
				Chapter chapter = new Chapter();
				chapter.setChapterNumber(1);
				for (int j = 0; j < verses.length; j++) {
					Verse verse = new Verse();
					verse.setVerseNumber(j);
					verse.setVerseNumber(j);
					chapter.addVerse(verse);
					bibelBook.addChapters(chapter);
				}
			}
			bibelBooks.add(bibelBook);
		}
		return bibelBooks;
	}

}
