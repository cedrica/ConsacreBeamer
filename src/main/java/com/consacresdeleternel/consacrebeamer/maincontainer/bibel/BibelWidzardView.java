package com.consacresdeleternel.consacrebeamer.maincontainer.bibel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.maincontainer.chapterview.ChapterOrVersePageViewModel;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListViewModel;
import com.consacresdeleternel.consacrebeamer.maincontainer.textofverse.TextOfVerseViewModel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class BibelWidzardView implements Initializable{

	@FXML BibelWidzardViewModel rootNode;
	@FXML CustomListViewModel customListViewModel;
	@FXML ChapterOrVersePageViewModel<Chapter> chapterViewModel;
	@FXML ChapterOrVersePageViewModel<Verse> verseViewModel;
	@FXML TextOfVerseViewModel textOfVerseViewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		verseViewModel.setIsVerse(true);
		chapterViewModel.setIsVerse(false);
		rootNode.setCustomListView(customListViewModel);
		rootNode.setChapterView(chapterViewModel);
		rootNode.setVerseView(verseViewModel);
		registerHandler();
		registerBindings();
	}

	private Chapter selectedChapter = null;
	private void registerHandler() {
		customListViewModel.bibelBooksProperty().addListener((obs, oldVal, books) -> {
			if(books != null && !books.isEmpty())
				chapterViewModel.setChaptersOrVerses(FXCollections.observableList(((BibelBook) books.get(0)).getChapters()));
		});
		
		customListViewModel.addEventHandler(BibelEvent.CUSTOM_LIST, evt ->{
			evt.consume();
			configureChapters(evt);
		});
		
		verseViewModel.addEventHandler(BibelEvent.CUSTOM_LIST, evt ->{
			evt.consume();
			configureChapters(evt);
		});
		
		chapterViewModel.addEventHandler(BibelEvent.CHAPTER, evt ->{
			evt.consume();
			chapterViewModel.setVisible(false);
			verseViewModel.setVisible(true);
			selectedChapter = evt.getChapter();
			List<Verse> verses = selectedChapter.getVerses();
			verseViewModel.setChaptersOrVerses(FXCollections.observableList(verses));
		});

		verseViewModel.addEventHandler(BibelEvent.VERSE, evt ->{
			evt.consume();
			verseViewModel.setVisible(false);
			textOfVerseViewModel.setVisible(true);
			Verse selectedVerse = evt.getVerse();
			textOfVerseViewModel.setSelectedChapter(selectedChapter);
			textOfVerseViewModel.setSelectedVerse(selectedVerse);
			
		});
	}

	private void registerBindings() {
		customListViewModel.selectedBibelProperty().addListener((obs, oldVal, newVal) -> {
			verseViewModel.setSelectedBibel(newVal);
		});
	}

	private void configureChapters(BibelEvent evt) {
		BibelBook bibelBook = (BibelBook) evt.getCustomListBasicObject();
		chapterViewModel.setVisible(true);
		verseViewModel.setVisible(false);
		chapterViewModel.setChaptersOrVerses(FXCollections.observableList(bibelBook.getChapters()));
	}

}
