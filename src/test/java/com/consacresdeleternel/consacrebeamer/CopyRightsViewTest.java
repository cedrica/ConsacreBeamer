package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights.CopyRightsView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CopyRightsViewTest extends ApplicationTest {
	CopyRightsView copyRightsView;

	@Override
	public void start(Stage stage) throws Exception {
		copyRightsView = new CopyRightsView();
		stage.setScene(new Scene(copyRightsView, 800, 600));
		stage.show();
	}

	@Test
	public void testLookAnfill() {
	}
}
