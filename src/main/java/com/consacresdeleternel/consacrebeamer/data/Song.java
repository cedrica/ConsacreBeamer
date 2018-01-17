package com.consacresdeleternel.consacrebeamer.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table
public class Song {
	@Id
	private long id;
	private String songTitle;
	@Column(nullable=false,columnDefinition="clob", length=9000)
	@Type(type="text")
	private String songBody;
	private String copyRightTitle;
	private String originalTitle;
	private String autor;
	private String musik;
	private String traduction;
	private String nationalCopy;
	private String rights;
	private String ccliNumber;
	private String bibleVerse;
	private String songBook;
	private String additionalInfo;
	private String songKey;
	private String tempo;
	@ManyToOne(targetEntity=Book.class)
	private Book book;
	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getSongBody() {
		return songBody;
	}

	public void setSongBody(String songBody) {
		this.songBody = songBody;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((bibleVerse == null) ? 0 : bibleVerse.hashCode());
		result = prime * result + ((ccliNumber == null) ? 0 : ccliNumber.hashCode());
		result = prime * result + ((copyRightTitle == null) ? 0 : copyRightTitle.hashCode());
		result = prime * result + ((musik == null) ? 0 : musik.hashCode());
		result = prime * result + ((nationalCopy == null) ? 0 : nationalCopy.hashCode());
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((rights == null) ? 0 : rights.hashCode());
		result = prime * result + ((songBody == null) ? 0 : songBody.hashCode());
		result = prime * result + ((songBook == null) ? 0 : songBook.hashCode());
		result = prime * result + ((songKey == null) ? 0 : songKey.hashCode());
		result = prime * result + ((songTitle == null) ? 0 : songTitle.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
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
		if (ccliNumber == null) {
			if (other.ccliNumber != null)
				return false;
		} else if (!ccliNumber.equals(other.ccliNumber))
			return false;
		if (copyRightTitle == null) {
			if (other.copyRightTitle != null)
				return false;
		} else if (!copyRightTitle.equals(other.copyRightTitle))
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
		if (songBook == null) {
			if (other.songBook != null)
				return false;
		} else if (!songBook.equals(other.songBook))
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
		if (traduction == null) {
			if (other.traduction != null)
				return false;
		} else if (!traduction.equals(other.traduction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Song [songTitle=" + songTitle + ", songBody=" + songBody + ", copyRightTitle=" + copyRightTitle
				+ ", originalTitle=" + originalTitle + ", autor=" + autor + ", musik=" + musik + ", traduction="
				+ traduction + ", nationalCopy=" + nationalCopy + ", rights=" + rights + ", ccliNumber=" + ccliNumber
				+ ", bibleVerse=" + bibleVerse + ", songBook=" + songBook + ", additionalInfo=" + additionalInfo
				+ ", songKey=" + songKey + ", tempo=" + tempo + "]";
	}

}
