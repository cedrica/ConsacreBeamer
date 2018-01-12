package com.consacresdeleternel.consacrebeamer;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text.TextView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextViewTest extends ApplicationTest {
	private TextView textView;

	@Override
	public void start(Stage stage) throws Exception {
		textView = new TextView();
		stage.setScene(new Scene(textView, 800, 600));
		stage.show();
	}

	@Test
	public void testLookAnfill() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
