package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String songTitle;
	@Transient
	private String songBody;
	private byte[] songBodyAsByteArr;
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
	private String additionalInfo;
	private String songKey;
	private String tempo;
	@Transient
	private String songHtml;
	private String textFileReference;
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@JoinTable(name="Song_Attachment", 
    joinColumns=@JoinColumn(name="songId"),
    inverseJoinColumns=@JoinColumn(name="attachmentId"))
	private List<Attachment> attachements = new ArrayList<>();

	public byte[] getSongBodyAsByteArr() {
		return songBodyAsByteArr;
	}

	public void setSongBodyAsByteArr(byte[] songBodyAsByteArr) {
		this.songBodyAsByteArr = songBodyAsByteArr;
	}

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

	public List<Attachment> getAttachements() {
		return attachements;
	}

	public void setAttachements(List<Attachment> attachements) {
		this.attachements = attachements;
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
		return "Song [id=" + id + ", songTitle=" + songTitle + ", songBody=" + songBody + ", songBodyAsByteArr="
				+ Arrays.toString(songBodyAsByteArr) + ", copyRightTitle=" + copyRightTitle + ", originalTitle="
				+ originalTitle + ", autor=" + autor + ", musik=" + musik + ", traduction=" + traduction
				+ ", copyRight=" + copyRight + ", nationalCopy=" + nationalCopy + ", rights=" + rights + ", ccliNumber="
				+ ccliNumber + ", bibleVerse=" + bibleVerse + ", additionalInfo=" + additionalInfo + ", songKey="
				+ songKey + ", tempo=" + tempo + ", songHtml=" + songHtml + ", textFileReference=" + textFileReference
				+ ", book=" + book + ", attachements=" + attachements + "]";
	}

}
