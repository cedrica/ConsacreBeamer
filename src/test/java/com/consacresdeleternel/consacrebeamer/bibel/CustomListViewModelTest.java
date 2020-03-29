package com.consacresdeleternel.consacrebeamer.bibel;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.TestHelper;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomListViewModelTest extends ApplicationTest{

	private CustomListViewModel customListViewModel;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		customListViewModel = new CustomListViewModel();
		List<BibelBook> books = TestHelper.createBibleBooks();
		customListViewModel.setBibelBooks(FXCollections.observableArrayList(books));
		customListViewModel.setListTitel("Livres");
		customListViewModel.setSearchVisible(true);
		stage.setScene(new Scene(customListViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void selectedItemName() {
		Platform.runLater(() ->{
			customListViewModel.addEventHandler(BibelEvent.CUSTOM_LIST, event ->{
				event.consume();
				assertEquals("Levitiques", event.getCustomListBasicObject().getName());
			});
			customListViewModel.setSelectIndex(2);
		});
	}
	
	@Test
	public void filterBySearchName() {
		Platform.runLater(() -> {
			TextField tfSerach = (TextField) customListViewModel.lookup("#tfSearch");
			clickOn(tfSerach).write("Levitiques");
			assertEquals("Levitiques", customListViewModel.getSearch());
		});
	}

	

}
