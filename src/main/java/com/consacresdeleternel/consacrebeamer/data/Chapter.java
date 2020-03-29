package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
	private int chapterNumber;
	private List<Verse> verses = new ArrayList<Verse>();
	public int getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	public List<Verse> getVerses() {
		return verses;
	}
	public void addVerse(Verse verse) {
		this.verses.add(verse);
	}
	public boolean removeVerse(Verse verse) {
		return this.verses.remove(verse);
	}
	
}
