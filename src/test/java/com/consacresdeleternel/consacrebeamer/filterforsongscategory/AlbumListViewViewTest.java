package com.consacresdeleternel.consacrebeamer.filterforsongscategory;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview.AlbumListViewViewModel;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlbumListViewViewTest  extends ApplicationTest{

	private AlbumListViewViewModel albumListViewViewModel;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		albumListViewViewModel = new AlbumListViewViewModel();
		stage.setScene(new Scene(albumListViewViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void selectedItemName() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
