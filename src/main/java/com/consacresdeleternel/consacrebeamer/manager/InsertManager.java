package com.consacresdeleternel.consacrebeamer.manager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;
import com.consacresdeleternel.consacrebeamer.events.InsertMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelWidzardViewModel;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;
import com.consacresdeleternel.consacrebeamer.service.XmlBibelParserImpl;
import com.consacresdeleternel.consacrebeamer.tasks.LoadBibelWidzard;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

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
		loadBibelWidzard.valueProperty().addListener((obs, oldVal, traductions) -> {
			BibelWidzardViewModel bibelWidzardViewModel = createBible(traductions);
			bibelWidzardViewModel.getCustomListView().addEventFilter(BibelEvent.LOAD_TRADUCTION, event ->{
				File traduction = event.getTraduction();
				List<BibelBook> reloadedBibelBooks = new XmlBibelParserImpl().readBibelBooksFromFile(traduction);
				bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
				bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(reloadedBibelBooks));
				bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
			});
			bibelWidzardViewModel.getCustomListView().addEventFilter(BibelEvent.LOAD_LANGUAGE, event ->{
				List<File> newTraductions = Arrays.asList(FileUtil.loadXmlBibelFiles(event.getLanguage()));
				bibelWidzardViewModel.getCustomListView().setTraductions(FXCollections.observableList(newTraductions));
			});
			
			Dialog<ButtonType> dialogStage = managerProvider.getDialogManager().showBibelWidzard(bibelWidzardViewModel,
					mainContainerView.getScene().getWindow());
			dialogStage.show();
		});
		managerProvider.getTaskManager().addTask(loadBibelWidzard);
	}
	
	
	public BibelWidzardViewModel createBible(List<File> traductions) {
		BibelWidzardViewModel bibelWidzardViewModel = new BibelWidzardViewModel();
		bibelWidzardViewModel.getCustomListView().setListTitel(Localization.asKey("csb.bibelWidzardView.buchs"));
		bibelWidzardViewModel.getChapterView().setTitle(Localization.asKey("csb.bibelWidzardView.chapters"));
		bibelWidzardViewModel.getVerseView().setTitle(Localization.asKey("csb.bibelWidzardView.verses"));
		bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
		bibelWidzardViewModel.getCustomListView().setTraductions(FXCollections.observableList(traductions));
		//bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(bibelBooks));
		//bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
		return bibelWidzardViewModel;
	}
	
}
