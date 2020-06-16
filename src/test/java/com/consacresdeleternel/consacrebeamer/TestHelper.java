package com.consacresdeleternel.consacrebeamer;

import java.util.ArrayList;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Song;
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
	
	public static List<Book> mockSomeBooks(int number) {
		List<Book> books = new ArrayList<>();
		for (int i = 1; i <= number; i++) {
			Book book = new Book();
			book.setTitle("Book" + i);
			List<Song> songs = mockNSongs(i*2, book.getTitle());
			book.setSongs(songs);
			books.add(book);
		}
		return books;
	}
	
	public static Song mockSong(String title, int songCategoryId) {
		Song song = new Song();
		song.setSongTitle(title);
		song.setSongCategoryId(songCategoryId);
		return song;
	}
	
	public static List<Song> mockNSongs(int n, String bookName){
		List<Song> songs = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			songs.add(mockSong(bookName + "-Song" + i, (i%2 + 1)));
		}
		return songs;
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
