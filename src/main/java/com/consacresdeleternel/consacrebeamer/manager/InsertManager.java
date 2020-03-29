package com.consacresdeleternel.consacrebeamer.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.consts.BibelConsts;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.events.InsertMenuEvent;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelWidzardViewModel;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;

import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

public class InsertManager {
	private ManagerProvider managerProvider;
	private static final String BIBEL_LOCATION = "src/main/resources/bibel/French__Louis_Segond_(1910)__ls1910__LTR.txt";

	
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,RepositoryProvider repositoryProvider) {
		this.managerProvider = managerProvider;
		mainContainerView.addEventHandler(InsertMenuEvent.SHOW_BIBEL,
				evt -> handleShowBibel(mainContainerView, evt));
	}

	private void handleShowBibel(MainContainerView mainContainerView, InsertMenuEvent evt) {
		evt.consume();
		BibelWidzardViewModel bibelWidzardViewModel = createBible();
		
		bibelWidzardViewModel.getCustomListView().addEventFilter(BibelEvent.LOAD_LANGUAGE, event ->{
			List<BibelBook> bibelBooks = readBooksFromFiles(event.getLanguage(), BIBEL_LOCATION );
			bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
			bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(bibelBooks));
			bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
		});
		Dialog<ButtonType> dialogStage = managerProvider.getDialogManager().showBibelWidzard(bibelWidzardViewModel,
				mainContainerView.getScene().getWindow());
		dialogStage.show();
	}

	private List<BibelBook> readBooksFromFiles(Language language, String fileLocation) {
		List<BibelBook> books = new ArrayList();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileLocation));
			Map<String, List<Map<String, List<Pair<String, String>>>>> bibelMap = new HashMap();
			Map<String, List<Pair<String, String>>> chapterMap = null;
			String oldChapterNum = "";
			for (String line : allLines) {
				String bookNumber = retrievebookNum(line);
				String chapterNum = retrieveChapterNum(line);
				String verseNum  = retrieveVerseNum(line);
				String verseText = retrieveVerseTextFromLine(line);
				if(!chapterNum.equals(oldChapterNum)) {
					chapterMap = new HashMap();
					oldChapterNum = chapterNum;

					if( bibelMap.get(bookNumber) == null) {
						bibelMap.put(bookNumber, new ArrayList<Map<String, List<Pair<String, String>>>>());
					}
					List chaptersMaps = bibelMap.get(bookNumber);
					chaptersMaps.add(chapterMap);
					bibelMap.put(bookNumber, chaptersMaps);
				}
				if( chapterMap.get(chapterNum) == null) {
					chapterMap.put(chapterNum, new ArrayList<Pair<String,String>>());
				}
				List verses = chapterMap.get(chapterNum);
				verses.add(new Pair(verseNum, verseText));
				chapterMap.put(chapterNum, verses);
				
			}
			TreeMap<String, String> sorted = new TreeMap(bibelMap);
			for (String bookNumber  : sorted.keySet()) {
				BibelBook bibelBook = new BibelBook();
				if(Language.FR.equals(language)) {
					try {
						String name = findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelConsts.BIBEL_BOOK_NAMES_EN);
						bibelBook.setName(name);
					} catch (BookNotFoundException e) {
						e.printStackTrace();
					}
				} else if(Language.EN.equals(language)) {
					
				}
				List<Chapter> chapters = constructChapters(bibelMap.get(bookNumber));
				bibelBook.getChapters().addAll(chapters);
				books.add(bibelBook);
			}
			return books;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Chapter> constructChapters(List<Map<String, List<Pair<String, String>>>> chapterMaps) {
		List<Chapter> chapters = new ArrayList();
		for (Map<String, List<Pair<String, String>>> chapterMap : chapterMaps) {
			Chapter chapter = new Chapter();
			for (String chapNumber : chapterMap.keySet()) {
				chapter.setChapterNumber(Integer.parseInt(chapNumber));
				List<Verse> verses = constructVerses(chapterMap.get(chapNumber));
				chapter.getVerses().addAll(verses);
			}
			chapters.add(chapter);
		}
		return chapters;
	}

	private List<Verse> constructVerses(List<Pair<String, String>> list) {
		List<Verse> verses = new ArrayList<Verse>();
		for (Pair<String, String> pair : list) {
			Verse verse = new Verse();
			verse.setVerseNumber(Integer.parseInt(pair.getKey()));
			verse.setText(pair.getValue());
			verses.add(verse);
		}
		return verses;
	}
	
	private String findBibelBookNameByNumber(int bookNum, List<String> bibelBookNames) throws BookNotFoundException {
		if (bibelBookNames != null && !bibelBookNames.isEmpty())
			return bibelBookNames.get(bookNum);
		else {
			throw new BookNotFoundException("No Bible Book for number "+ bookNum);
		}
			
	}

	private String retrieveVerseTextFromLine(String line) {
		//z.B. Line == 01O||1||1||Au commencement, Dieu crÃ©a les cieux et la terre.
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No Verse could be retrieved from line: "+ line);
		}
		return lineParts[3];
	}

	private String retrieveVerseNum(String line) {
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No versenumber could be retrieved from line: "+ line);
		}
		return lineParts[2];
	}

	private String retrieveChapterNum(String line) {
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No chapternumber could be retrieved from line: "+ line);
		}
		return lineParts[1];
	}

	private String retrievebookNum(String line) {
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No booknumber could be retrieved from line: "+ line);
		}
		String tmp = lineParts[0];
		if(tmp.charAt(0) == '0') {
			return String.valueOf(tmp.charAt(1));
		} else if(tmp.charAt(0) != '0') {
			return tmp.substring(0,2);
		}
		return "1";
	}

	private BibelWidzardViewModel createBible() {
		BibelWidzardViewModel bibelWidzardViewModel = new BibelWidzardViewModel();
		bibelWidzardViewModel.getCustomListView().setListTitel(Localization.asKey("csb.bibelWidzardView.buchs"));
		bibelWidzardViewModel.getChapterView().setTitle(Localization.asKey("csb.bibelWidzardView.chapters"));
		bibelWidzardViewModel.getVerseView().setTitle(Localization.asKey("csb.bibelWidzardView.verses"));
		List<BibelBook> bibelBooks = readBooksFromFiles(Language.FR, BIBEL_LOCATION );
		bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
		bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(bibelBooks));
		bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
		return bibelWidzardViewModel;
	}
}
