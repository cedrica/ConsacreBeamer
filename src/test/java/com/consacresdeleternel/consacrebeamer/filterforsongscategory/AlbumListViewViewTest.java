package com.consacresdeleternel.consacrebeamer.filterforsongscategory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.TestHelper;
import com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview.AlbumListViewViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AlbumListViewViewTest  extends ApplicationTest{

	private AlbumListViewViewModel albumListViewViewModel;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		albumListViewViewModel = new AlbumListViewViewModel();
		albumListViewViewModel.setBooks(FXCollections.observableArrayList(TestHelper.mockSomeBooks(5)));
		stage.setScene(new Scene(albumListViewViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void select3CB() {
		Platform.runLater(() -> {
			CheckBox cb1 = (CheckBox)albumListViewViewModel.lookup("#cb_1");
			CheckBox cb2 = (CheckBox)albumListViewViewModel.lookup("#cb_2");
			CheckBox cb3 = (CheckBox)albumListViewViewModel.lookup("#cb_3");
			cb1.setSelected(true);
			cb2.setSelected(true);
			cb3.setSelected(true);
			assertEquals(3, albumListViewViewModel.getSelectedAlbums().size());
		});
	}
	

	@Test
	public void select2CB() {
		Platform.runLater(() -> {
			CheckBox cb1 = (CheckBox)albumListViewViewModel.lookup("#cb_1");
			CheckBox cb2 = (CheckBox)albumListViewViewModel.lookup("#cb_2");
			cb1.setSelected(true);
			cb2.setSelected(true);
			assertEquals(2, albumListViewViewModel.getSelectedAlbums().size());
		});
	}
	
	
}
