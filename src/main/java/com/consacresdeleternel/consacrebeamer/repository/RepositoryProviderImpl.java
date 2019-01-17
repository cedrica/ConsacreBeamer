package com.consacresdeleternel.consacrebeamer.repository;

public class RepositoryProviderImpl implements RepositoryProvider {

	private SongRepository songRepository;
	private ScheduleRepository scheduleRepository;
	private BookRepository bookRepository;
	
	public RepositoryProviderImpl() {
		this.scheduleRepository = new ScheduleRepository();
		this.bookRepository = new BookRepository();
		this.scheduleRepository = new ScheduleRepository();
	}
	@Override
	public SongRepository getSongRepository() {
		return this.songRepository;
	}

	@Override
	public BookRepository getBookRepository() {
		return this.bookRepository;
	}

	@Override
	public ScheduleRepository getScheduleRepository() {
		return this.scheduleRepository;
	}

}
