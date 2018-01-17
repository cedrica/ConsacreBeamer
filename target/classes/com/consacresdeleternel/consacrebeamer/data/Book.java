package com.consacresdeleternel.consacrebeamer.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Book {
	@Id
	private long id;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="book")
	private List<Song> songs;

	
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
