package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.CreateOrEditNewSongView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateOrEditNewSongViewTest extends ApplicationTest {
	private CreateOrEditNewSongView creteOrEditNewSongView;

	@Override
	public void start(Stage stage) throws Exception {
		creteOrEditNewSongView = new CreateOrEditNewSongView();
		stage.setScene(new Scene(creteOrEditNewSongView, 800, 600));
		stage.show();
	}

	@Test
	public void testLookAnfill() {
	}
}
