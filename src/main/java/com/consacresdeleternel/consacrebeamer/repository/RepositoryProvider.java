package com.consacresdeleternel.consacrebeamer.repository;

public interface RepositoryProvider {
	public SongRepository getSongRepository();
	public BookRepository getBookRepository();
	public ScheduleRepository getScheduleRepository();
}
