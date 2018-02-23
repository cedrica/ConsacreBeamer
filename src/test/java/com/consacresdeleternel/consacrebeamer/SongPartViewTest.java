package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.songpart.SongPartView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SongPartViewTest extends ApplicationTest {
	SongPartView songPartView;

	@Override
	public void start(Stage stage) throws Exception {
		songPartView = new SongPartView();
		songPartView.setStyle("-fx-background-color:blue");
		stage.setScene(new Scene(songPartView, 300, 200));
		stage.show();
	}

	@Test
	public void testLookAnfill() {
		Platform.runLater(() -> {
			// Image image = new
			// Image(SongPartViewTest.class.getResourceAsStream("/icons8-add-file-40.png"));
			// songPartView.setBackgroundImage(image);
			songPartView.setText(
					"Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore"
							+ "Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore"
							+ "Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore Lorem ipsum dolore");
		});
	}
}