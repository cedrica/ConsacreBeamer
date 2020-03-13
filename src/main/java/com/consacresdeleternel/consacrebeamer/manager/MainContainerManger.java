package com.consacresdeleternel.consacrebeamer.manager;

import com.consacresdeleternel.consacrebeamer.customcomponent.CustomAutoCompleteTextFields;
import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.events.SongEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;
import com.consacresdeleternel.consacrebeamer.service.SongCategoriesService;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBookTask;
import com.consacresdeleternel.consacrebeamer.tasks.LoadSongTask;
import com.consacresdeleternel.consacrebeamer.utils.JFXUtilities;

import javafx.collections.FXCollections;

public class MainContainerManger {

private ManagerProvider managerProvider;
private RepositoryProvider repositoryProvider;
	public void init(MainContainerView mainContainerView){
		this.managerProvider = new ManagerProvider();
		this.repositoryProvider = new RepositoryProvider();
		JFXUtilities.bindMaskerPane(mainContainerView.getMaskerPane(), managerProvider.getTaskManager());
		loadBooks();
		loadSongs(mainContainerView);
		loadSonCategories();
		mainContainerView.addEventHandler(BookEvent.RELOAD_BOOKS, evt -> loadBooks());
		mainContainerView.addEventHandler(SongEvent.RELOAD_SONGS, evt -> loadSongs(mainContainerView));
		managerProvider.getFileMenuManager().init(mainContainerView, managerProvider, repositoryProvider);
		managerProvider.getExtrasMenuManager().init(mainContainerView, managerProvider, repositoryProvider);
		managerProvider.getBookManager().init(mainContainerView, managerProvider, repositoryProvider);
		managerProvider.getPresentationManager().init(mainContainerView, managerProvider, repositoryProvider);
		managerProvider.getScheduleManager().init(mainContainerView, managerProvider, repositoryProvider);
	}

	private void loadSongs(MainContainerView mainContainerView) {
		LoadSongTask loadSongTask = new LoadSongTask(repositoryProvider.getSongRepository());
		new Thread(loadSongTask).start();
		managerProvider.getTaskManager().addTask(loadSongTask);
		loadSongTask.valueProperty().addListener((obs, oldVal, newVal) -> {
			managerProvider.getValueObjectManager().setSongItems(FXCollections.observableList(newVal));
			new CustomAutoCompleteTextFields(mainContainerView.getSearchTextField(), newVal);
		});
	}
	
	private void loadSonCategories() {
		managerProvider.getValueObjectManager().setSongCategoryItems(
				FXCollections.observableArrayList(new SongCategoriesService().songCategories()));
	}

	private void loadBooks() {
		LoadBookTask loadBookTask = new LoadBookTask(repositoryProvider.getBookRepository());
		new Thread(loadBookTask).start();
		managerProvider.getTaskManager().addTask(loadBookTask);
		loadBookTask.valueProperty().addListener((obs, oldVal, newVal) -> {
			managerProvider.getValueObjectManager().setBookItems(FXCollections.observableList(newVal));
		});
	}
}
