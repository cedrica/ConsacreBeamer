package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CHAPTER")
public class Chapter {
	private int chapterNumber;
	private List<Verse> verses = new ArrayList<Verse>();
	
	@XmlAttribute(name = "cnumber")
	public int getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	@XmlElement(name = "VERS")
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
