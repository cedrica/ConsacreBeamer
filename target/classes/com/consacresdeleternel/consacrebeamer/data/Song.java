package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
	@Transient
	@Lob
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
	@JoinColumn(name = "bookId", nullable = false)
	private Book book;
	@OneToMany(orphanRemoval = true)
	@JoinTable(name = "Song_Attachment", joinColumns = @JoinColumn(name = "songId"), inverseJoinColumns = @JoinColumn(name = "attachmentId"))
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((attachements == null) ? 0 : attachements.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((bibleVerse == null) ? 0 : bibleVerse.hashCode());
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((ccliNumber == null) ? 0 : ccliNumber.hashCode());
		result = prime * result + ((copyRight == null) ? 0 : copyRight.hashCode());
		result = prime * result + ((copyRightTitle == null) ? 0 : copyRightTitle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((musik == null) ? 0 : musik.hashCode());
		result = prime * result + ((nationalCopy == null) ? 0 : nationalCopy.hashCode());
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((rights == null) ? 0 : rights.hashCode());
		result = prime * result + ((songBody == null) ? 0 : songBody.hashCode());
		result = prime * result + Arrays.hashCode(songBodyAsByteArr);
		result = prime * result + ((songHtml == null) ? 0 : songHtml.hashCode());
		result = prime * result + ((songKey == null) ? 0 : songKey.hashCode());
		result = prime * result + ((songTitle == null) ? 0 : songTitle.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
		result = prime * result + ((textFileReference == null) ? 0 : textFileReference.hashCode());
		result = prime * result + ((traduction == null) ? 0 : traduction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (additionalInfo == null) {
			if (other.additionalInfo != null)
				return false;
		} else if (!additionalInfo.equals(other.additionalInfo))
			return false;
		if (attachements == null) {
			if (other.attachements != null)
				return false;
		} else if (!attachements.equals(other.attachements))
			return false;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (bibleVerse == null) {
			if (other.bibleVerse != null)
				return false;
		} else if (!bibleVerse.equals(other.bibleVerse))
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (ccliNumber == null) {
			if (other.ccliNumber != null)
				return false;
		} else if (!ccliNumber.equals(other.ccliNumber))
			return false;
		if (copyRight == null) {
			if (other.copyRight != null)
				return false;
		} else if (!copyRight.equals(other.copyRight))
			return false;
		if (copyRightTitle == null) {
			if (other.copyRightTitle != null)
				return false;
		} else if (!copyRightTitle.equals(other.copyRightTitle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (musik == null) {
			if (other.musik != null)
				return false;
		} else if (!musik.equals(other.musik))
			return false;
		if (nationalCopy == null) {
			if (other.nationalCopy != null)
				return false;
		} else if (!nationalCopy.equals(other.nationalCopy))
			return false;
		if (originalTitle == null) {
			if (other.originalTitle != null)
				return false;
		} else if (!originalTitle.equals(other.originalTitle))
			return false;
		if (rights == null) {
			if (other.rights != null)
				return false;
		} else if (!rights.equals(other.rights))
			return false;
		if (songBody == null) {
			if (other.songBody != null)
				return false;
		} else if (!songBody.equals(other.songBody))
			return false;
		if (!Arrays.equals(songBodyAsByteArr, other.songBodyAsByteArr))
			return false;
		if (songHtml == null) {
			if (other.songHtml != null)
				return false;
		} else if (!songHtml.equals(other.songHtml))
			return false;
		if (songKey == null) {
			if (other.songKey != null)
				return false;
		} else if (!songKey.equals(other.songKey))
			return false;
		if (songTitle == null) {
			if (other.songTitle != null)
				return false;
		} else if (!songTitle.equals(other.songTitle))
			return false;
		if (tempo == null) {
			if (other.tempo != null)
				return false;
		} else if (!tempo.equals(other.tempo))
			return false;
		if (textFileReference == null) {
			if (other.textFileReference != null)
				return false;
		} else if (!textFileReference.equals(other.textFileReference))
			return false;
		if (traduction == null) {
			if (other.traduction != null)
				return false;
		} else if (!traduction.equals(other.traduction))
			return false;
		return true;
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
