package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.consts.BibelBooksConsts;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.util.Pair;

public class BibelParserTxtImpl implements BibelParser{
	
	@Override
	public List<BibelBook> readBibelBooksFromFile(File bibel) {
		List<BibelBook> books = new ArrayList();
		if(bibel == null) return books;
		String absPath = bibel.getAbsolutePath();
		String bibelPath = "/bibel"+StringUtils.substringAfter(absPath, "bibel");
		try {
			List<String> allLines = FileUtil.readLines(bibel.getName());
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
			Language language = findLanguage(bibelPath);
			
			TreeMap<String, String> sorted = new TreeMap(bibelMap);
			for (String bookNumber  : sorted.keySet()) {
				BibelBook bibelBook = new BibelBook();
				try {
					String name = "";
					if(Language.FR.equals(language)) {
						name = Helper.findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelBooksConsts.BIBEL_BOOK_NAMES_FR);
					} else if(Language.EN.equals(language)) {
						name = Helper.findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelBooksConsts.BIBEL_BOOK_NAMES_EN);
					} else if(Language.DE.equals(language)) {
						name = Helper.findBibelBookNameByNumber(Integer.parseInt(bookNumber) -1, BibelBooksConsts.BIBEL_BOOK_NAMES_DE);
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

	private Language findLanguage(String bibelPath) {
		if(bibelPath.contains("en")) {
			return Language.EN;
		} else if(bibelPath.contains("de")) {
			return Language.DE;
		} else if(bibelPath.contains("fr")) {
			return Language.FR;
		}
		return Language.FR;
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


}
