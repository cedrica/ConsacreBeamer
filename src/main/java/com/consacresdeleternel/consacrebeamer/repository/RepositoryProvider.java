package com.consacresdeleternel.consacrebeamer.repository;

public class RepositoryProvider  {

	private SongRepository songRepository;
	private ScheduleRepository scheduleRepository;
	private BookRepository bookRepository;
	
	public RepositoryProvider() {
		this.scheduleRepository = new ScheduleRepository();
		this.bookRepository = new BookRepository();
		this.songRepository = new SongRepository();
	}
	public SongRepository getSongRepository() {
		return this.songRepository;
	}

	public BookRepository getBookRepository() {
		return this.bookRepository;
	}

	public ScheduleRepository getScheduleRepository() {
		return this.scheduleRepository;
	}

}
