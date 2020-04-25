package com.consacresdeleternel.consacrebeamer.manager;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.events.InsertMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelWidzardViewModel;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;
import com.consacresdeleternel.consacrebeamer.service.BibelParserTxtImpl;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBibelWidzard;

import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class InsertManager {
	private ManagerProvider managerProvider;

	
	public void init(MainContainerView mainContainerView, ManagerProvider managerProvider,RepositoryProvider repositoryProvider) {
		this.managerProvider = managerProvider;
		mainContainerView.addEventHandler(InsertMenuEvent.SHOW_BIBEL,
				evt -> handleShowBibel(mainContainerView, evt));
	}

	private void handleShowBibel(MainContainerView mainContainerView, InsertMenuEvent evt) {
		evt.consume();
		LoadBibelWidzard loadBibelWidzard = new LoadBibelWidzard();
		new Thread(loadBibelWidzard).start();
		loadBibelWidzard.valueProperty().addListener((obs, oldVal, bibelBooks) -> {
			BibelWidzardViewModel bibelWidzardViewModel = createBible(bibelBooks);
			bibelWidzardViewModel.getCustomListView().addEventFilter(BibelEvent.LOAD_LANGUAGE, event ->{
				List<BibelBook> reloadedBibelBooks = new BibelParserTxtImpl().readBibelBooksFromFiles(event.getLanguage() );
				bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
				bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(reloadedBibelBooks));
				bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
			});
			Dialog<ButtonType> dialogStage = managerProvider.getDialogManager().showBibelWidzard(bibelWidzardViewModel,
					mainContainerView.getScene().getWindow());
			dialogStage.show();
		});
		managerProvider.getTaskManager().addTask(loadBibelWidzard);
	}
	
	
	public BibelWidzardViewModel createBible(List<BibelBook> bibelBooks) {
		BibelWidzardViewModel bibelWidzardViewModel = new BibelWidzardViewModel();
		bibelWidzardViewModel.getCustomListView().setListTitel(Localization.asKey("csb.bibelWidzardView.buchs"));
		bibelWidzardViewModel.getChapterView().setTitle(Localization.asKey("csb.bibelWidzardView.chapters"));
		bibelWidzardViewModel.getVerseView().setTitle(Localization.asKey("csb.bibelWidzardView.verses"));
		bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
		bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(bibelBooks));
		bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
		return bibelWidzardViewModel;
	}
	
}
