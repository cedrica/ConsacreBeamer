package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.events.BookEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.repository.BookRepository;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBookTask;
import com.consacresdeleternel.consacrebeamer.utils.JFXUtilities;

import javafx.collections.FXCollections;

@Singleton
public class MainContainerManger {
	private static final Logger LOG = Logger.getLogger(MainContainerManger.class);

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

	public void init(MainContainerView mainContainerView) {
		JFXUtilities.bindMaskerPane(mainContainerView.getMaskerPane(), taskManager);
		loadBooks();
		mainContainerView.addEventHandler(BookEvent.RELOAD_BOOKS, evt ->loadBooks());
		fileMenuManager.init(mainContainerView);
		extrasMenuManager.init(mainContainerView);
		bookManager.init(mainContainerView);
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
