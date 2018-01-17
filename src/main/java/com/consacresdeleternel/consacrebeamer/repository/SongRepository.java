package com.consacresdeleternel.consacrebeamer.repository;

import com.consacresdeleternel.consacrebeamer.data.Song;

public class SongRepository extends BasicRepository<Song>{ 
	public SongRepository(){
		super(Song.class);
	}
}
