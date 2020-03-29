package com.consacresdeleternel.consacrebeamer.bibel;

import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.TestHelper;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelWidzardViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BibelWidzardViewModelTest extends ApplicationTest {
	BibelWidzardViewModel bibelWidzardViewModel;
	@Override
	public void start(Stage stage) throws Exception {
		bibelWidzardViewModel = new BibelWidzardViewModel();
		bibelWidzardViewModel.getCustomListView().setListTitel("Livres");
		List<BibelBook> books = TestHelper.createBibleBooks();
		bibelWidzardViewModel.getCustomListView().setSearchVisible(true);
		bibelWidzardViewModel.getCustomListView().setBibelBooks(FXCollections.observableList(books));
		bibelWidzardViewModel.getCustomListView().setSelectIndex(0);
		bibelWidzardViewModel.getChapterView().setTitle("chapter");
		bibelWidzardViewModel.getVerseView().setTitle("Verses");
		stage.setScene(new Scene(bibelWidzardViewModel));
		stage.show();
	}


	@Test
	public void assertVersenumber() {
		Platform.runLater(() -> {
			Button button =  (Button) bibelWidzardViewModel.getChapterView().lookup("#button_1");
			clickOn(button);
		});
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
