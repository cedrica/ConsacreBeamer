package com.consacresdeleternel.consacrebeamer.tasks;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.repository.SongRepository;

import javafx.concurrent.Task;

public class LoadSongTask extends Task<List<Song>> {
	private SongRepository songRepository;

	public LoadSongTask(SongRepository songRepository) {
		this.songRepository = songRepository;
	}

	@Override
	protected List<Song> call() throws Exception {
		return songRepository.findAll();
	}

}
