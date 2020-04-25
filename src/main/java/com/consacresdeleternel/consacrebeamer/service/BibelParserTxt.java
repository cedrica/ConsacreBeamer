package com.consacresdeleternel.consacrebeamer.service;

import java.util.List;
import java.util.Map;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;

import javafx.util.Pair;

public interface BibelParserTxt {
	public List<BibelBook> readBooksFromFiles(Language language);
	public List<Chapter> constructChapters(List<Map<String, List<Pair<String, String>>>> chapterMaps);
	public List<Verse> constructVerses(List<Pair<String, String>> list);
	public String findBibelBookNameByNumber(int bookNum, List<String> bibelBookNames) throws BookNotFoundException;
	public String retrieveVerseTextFromLine(String line);
	public String retrieveVerseNum(String line);
	public String retrieveChapterNum(String line);
	public String retrievebookNum(String line);

}
