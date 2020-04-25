package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.listitem.ListItemView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainContainerViewTest extends ApplicationTest {
	MainContainerView mainContainerView;

	@Override
	public void start(Stage stage) throws Exception {
		mainContainerView = new MainContainerView();
		stage.setScene(new Scene(mainContainerView, 800, 600));
		stage.show();
	}

	@Test
	public void testLookAnfill() {
		Platform.runLater(() -> {
			for (int i = 0; i < 40; i++) {
				 ListItemView listItemView = new ListItemView();
				 listItemView.setItemName("ich bin ein test"+i);
				 mainContainerView.getListViewContainer().getChildren().add(listItemView);
			}
		});
	}
}
