package com.consacresdeleternel.consacrebeamer;

import java.util.ArrayList;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;

public class TestHelper {

	public enum BibelBookEnum {
		GENESE(12,new int[]{34,33,44,13,53,23,22,43,22,22,33,44}),
		EXODE(10,new int[]{34,33,44,13,53,23,22,43,22,22}),
		LEVITIQUE(9,new int[]{34,33,44,13,53,23,22,43,22}),
		NOMBRE(12,new int[]{34,33,44,13,53,23,22,43,22,22,33,44}),
		DEUTERONOME(12,new int[]{34,33,44,13,53,23,22,43,22,22,33,44});
		public int chapterNumber; 
		public int[] verses;
		BibelBookEnum(int chapterNumber, int[] verses){
			this.chapterNumber = chapterNumber;
			this.verses = verses;
		}
		public int getChapterNumber() {
			return chapterNumber;
		}
		public int[] getVerses() {
			return verses;
		}
		
		
	}
	
	
	//TODO
		//This function belongs to an Helper class and muss be tested too
		// at the moment it construct is wrong
		public static List<BibelBook> createBibleBooks() {
			List<BibelBook> bibelBooks = new ArrayList<BibelBook>();
			for(BibelBookEnum bibelBookEnum : BibelBookEnum.values()) {
				BibelBook bibelBook = new BibelBook();
				bibelBook.setName(bibelBookEnum.toString());
				for(int chapCount = 1;  chapCount < bibelBookEnum.getChapterNumber(); chapCount++ ) {
					Chapter chapter = new Chapter();
					chapter.setChapterNumber(chapCount);
					for(int verseCount = 1;  verseCount < bibelBookEnum.getVerses()[chapCount - 1]; verseCount++ ) {
						Verse verse = new Verse();
						verse.setText("Loren Ipsun dolore");
						verse.setVerseNumber(verseCount);
						chapter.getVerses().add(verse);
					}
					((BibelBook)bibelBook).getChapters().add(chapter);
				}
				bibelBooks.add(bibelBook);
			}
			return bibelBooks;
		}
}