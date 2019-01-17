package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.customcomponent.CustomAutoCompleteTextFields;
import com.consacresdeleternel.consacrebeamer.data.SongCategory;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.events.SongEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.repository.BookRepository;
import com.consacresdeleternel.consacrebeamer.repository.SongRepository;
import com.consacresdeleternel.consacrebeamer.service.SongCategoriesService;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBookTask;
import com.consacresdeleternel.consacrebeamer.tasks.LoadSongTask;
import com.consacresdeleternel.consacrebeamer.utils.JFXUtilities;

import javafx.collections.FXCollections;

@Singleton
public class MainContainerManger {
	 private static final Logger LOG =
			 LoggerFactory.getLogger(MainContainerManger.class);

	@Inject
	private FileMenuManager fileMenuManager;
	@Inject
	private ExtrasMenuManager extrasMenuManager;
	@Inject
	private BookManager bookManager;
	@Inject
	private BookRepository bookRepository;
	@Inject
	private ValueObjectManager valueObjectManager;
	@Inject
	private TaskManager taskManager;
	@Inject
	private PresentationManager presentationManager;
	@Inject
	private SongRepository songRepository;
	@Inject
	private ScheduleManager scheduleManager;

	public void init(MainContainerView mainContainerView) {
		JFXUtilities.bindMaskerPane(mainContainerView.getMaskerPane(), taskManager);
		loadBooks();
		loadSongs(mainContainerView);
		loadSonCategories();
		mainContainerView.addEventHandler(BookEvent.RELOAD_BOOKS, evt -> loadBooks());
		mainContainerView.addEventHandler(SongEvent.RELOAD_SONGS, evt -> loadSongs(mainContainerView));
		fileMenuManager.init(mainContainerView);
		extrasMenuManager.init(mainContainerView);
		bookManager.init(mainContainerView);
		presentationManager.init(mainContainerView);
		scheduleManager.init(mainContainerView);
	}

	private void loadSongs(MainContainerView mainContainerView) {
		LoadSongTask loadSongTask = new LoadSongTask(songRepository);
		new Thread(loadSongTask).start();
		taskManager.addTask(loadSongTask);
		loadSongTask.valueProperty().addListener((obs, oldVal, newVal) -> {
			valueObjectManager.setSongItems(FXCollections.observableList(newVal));
			new CustomAutoCompleteTextFields(mainContainerView.getSearchTextField(), newVal);
		});
	}
	
	private void loadSonCategories() {
		valueObjectManager.setSongCategoryItems(
				FXCollections.observableArrayList(new SongCategoriesService().songCategories()));
	}

	private void loadBooks() {
		LoadBookTask loadBookTask = new LoadBookTask(bookRepository);
		new Thread(loadBookTask).start();
		taskManager.addTask(loadBookTask);
		loadBookTask.valueProperty().addListener((obs, oldVal, newVal) -> {
			valueObjectManager.setBookItems(FXCollections.observableList(newVal));
		});
	}
}
