package com.consacresdeleternel.consacrebeamer.filterforsongscategory;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.TestHelper;
import com.consacresdeleternel.consacrebeamer.maincontainer.filterforsongscategory.FilterForSongsCategoryViewModel;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FilterForSongsCategoryViewTest extends ApplicationTest {

	private FilterForSongsCategoryViewModel filterForSongsCategoryViewModel;

	@Override
	public void start(Stage stage) throws Exception {
		filterForSongsCategoryViewModel = new FilterForSongsCategoryViewModel();
		filterForSongsCategoryViewModel.setAlbums(FXCollections.observableArrayList(TestHelper.mockSomeBooks(5)));
		stage.setScene(new Scene(filterForSongsCategoryViewModel));
		stage.show();
	}

	@Test
	public void setUp() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
