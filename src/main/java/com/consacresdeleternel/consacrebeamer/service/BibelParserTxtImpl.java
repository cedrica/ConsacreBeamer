package com.consacresdeleternel.consacrebeamer.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.consts.BibelConsts;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;

import javafx.util.Pair;

public class BibelParserTxtImpl implements BibelParserTxt{
	
	public static final String BIBEL_FR = "src/main/resources/bibel/French__Louis_Segond_(1910)__ls1910__LTR.txt";
	public static final String BIBEL_DE = "src/main/resources/bibel/German__Elberfelder_(1905)__elberfelder1905__LTR.txt";
	public static final String BIBEL_EN = "src/main/resources/bibel/English__Darby__darby__LTR.txt";


	@Override
	public List<BibelBook> readBooksFromFiles(Language language) {
		List<BibelBook> books = new ArrayList();
		String fileLocation = BIBEL_FR;
		if(language == Language.DE) {
			fileLocation = BIBEL_DE;
		} else if(language == Language.FR) {
			fileLocation = BIBEL_FR;
		} else if(language == Language.EN) {
			fileLocation = BIBEL_EN;
		}
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
				try {
					String name = "";
					if(Language.FR.equals(language)) {
						name = findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelConsts.BIBEL_BOOK_NAMES_FR);
					} else if(Language.EN.equals(language)) {
						name = findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelConsts.BIBEL_BOOK_NAMES_EN);
					} else if(Language.DE.equals(language)) {
						name = findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelConsts.BIBEL_BOOK_NAMES_DE);
					}
					bibelBook.setName(name);
				} catch (BookNotFoundException e) {
					e.printStackTrace();
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

	@Override
	public List<Chapter> constructChapters(List<Map<String, List<Pair<String, String>>>> chapterMaps) {
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
	
	@Override
	public List<Verse> constructVerses(List<Pair<String, String>> list) {
		List<Verse> verses = new ArrayList<Verse>();
		for (Pair<String, String> pair : list) {
			Verse verse = new Verse();
			verse.setVerseNumber(Integer.parseInt(pair.getKey()));
			byte ptext[];
			try {
				ptext = pair.getValue().getBytes("Big5");
				String textInUTF8 = new String(ptext, "utf-8"); 
				verse.setText(textInUTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			verses.add(verse);
		}
		return verses;
	}
	
	@Override
	public String findBibelBookNameByNumber(int bookNum, List<String> bibelBookNames) throws BookNotFoundException {
		if (bibelBookNames != null && !bibelBookNames.isEmpty())
			return bibelBookNames.get(bookNum);
		else {
			throw new BookNotFoundException("No Bible Book for number "+ bookNum);
		}
			
	}

	@Override
	public String retrieveVerseTextFromLine(String line) {
		//z.B. Line == 01O||1||1||Au commencement, Dieu crÃ©a les cieux et la terre.
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No Verse could be retrieved from line: "+ line);
		}
		return lineParts[3];
	}

	@Override
	public String retrieveVerseNum(String line) {
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No versenumber could be retrieved from line: "+ line);
		}
		return lineParts[2];
	}

	@Override
	public String retrieveChapterNum(String line) {
		String[] lineParts = line.split(Pattern.quote("||"));
		if(lineParts == null || lineParts.length < 4) {
			throw new RuntimeException("No chapternumber could be retrieved from line: "+ line);
		}
		return lineParts[1];
	}

	@Override
	public String retrievebookNum(String line) {
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


}
