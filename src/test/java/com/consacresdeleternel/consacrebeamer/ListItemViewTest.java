package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.listitem.ListItemView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListItemViewTest extends ApplicationTest {
	private ListItemView listItemView;

	@Override
	public void start(Stage stage) throws Exception {
		listItemView = new ListItemView();
		stage.setScene(new Scene(listItemView, 800, 600));
		stage.show();
	}

	@Test
	public void testLookAndFill() {
		Platform.runLater(() -> {
			listItemView.setItemName("Jesus ich liebe dich");
		});

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
