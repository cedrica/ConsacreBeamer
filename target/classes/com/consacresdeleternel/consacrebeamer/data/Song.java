package com.consacresdeleternel.consacrebeamer.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Song {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String songTitle;
	private String songBody;
	private String copyRightTitle;
	private String originalTitle;
	private String autor;
	private String musik;
	private String traduction;
	private String copyRight;
	private String nationalCopy;
	private String rights;
	private String ccliNumber;
	private String bibleVerse;
	private String songBook;
	private String additionalInfo;
	private String songKey;
	private String tempo;
	@Transient
	private String songHtml;
	private String textFileReference;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongHtml() {
		return songHtml;
	}

	public void setSongHtml(String songHtml) {
		this.songHtml = songHtml;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getSongBody() {
		return songBody;
	}

	public void setSongBody(String songBody) {
		this.songBody = songBody;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCopyRightTitle() {
		return copyRightTitle;
	}

	public void setCopyRightTitle(String copyRightTitle) {
		this.copyRightTitle = copyRightTitle;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getMusik() {
		return musik;
	}

	public void setMusik(String musik) {
		this.musik = musik;
	}

	public String getTraduction() {
		return traduction;
	}

	public void setTraduction(String traduction) {
		this.traduction = traduction;
	}

	public String getNationalCopy() {
		return nationalCopy;
	}

	public void setNationalCopy(String nationalCopy) {
		this.nationalCopy = nationalCopy;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getCcliNumber() {
		return ccliNumber;
	}

	public void setCcliNumber(String ccliNumber) {
		this.ccliNumber = ccliNumber;
	}

	public String getBibleVerse() {
		return bibleVerse;
	}

	public void setBibleVerse(String bibleVerse) {
		this.bibleVerse = bibleVerse;
	}

	public String getSongBook() {
		return songBook;
	}

	public void setSongBook(String songBook) {
		this.songBook = songBook;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getSongKey() {
		return songKey;
	}

	public void setSongKey(String songKey) {
		this.songKey = songKey;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getTextFileReference() {
		return textFileReference;
	}

	public void setTextFileReference(String textFileReference) {
		this.textFileReference = textFileReference;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", songTitle=" + songTitle + ", songBody=" + songBody + ", copyRightTitle="
				+ copyRightTitle + ", originalTitle=" + originalTitle + ", autor=" + autor + ", musik=" + musik
				+ ", traduction=" + traduction + ", copyRight=" + copyRight + ", nationalCopy=" + nationalCopy
				+ ", rights=" + rights + ", ccliNumber=" + ccliNumber + ", bibleVerse=" + bibleVerse + ", songBook="
				+ songBook + ", additionalInfo=" + additionalInfo + ", songKey=" + songKey + ", tempo=" + tempo
				+ ", textFileReference=" + textFileReference + ", book=" + book + "]";
	}

}
