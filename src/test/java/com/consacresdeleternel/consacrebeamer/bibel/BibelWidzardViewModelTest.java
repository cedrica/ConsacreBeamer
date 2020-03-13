package com.consacresdeleternel.consacrebeamer.bibel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelWidzardViewModel;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BibelWidzardViewModelTest extends ApplicationTest {
	BibelWidzardViewModel bibelWidzardViewModel;
	Dialog dialog = new Dialog();
	@Override
	public void start(Stage stage) throws Exception {
	
		dialog.getDialogPane().setContent(bibelWidzardViewModel);
		bibelWidzardViewModel = new BibelWidzardViewModel();
		stage.setScene(new Scene(new VBox((Parent)bibelWidzardViewModel), 800, 600));
		stage.show();
	}


	@Test
	public void assertVersenumber() {
		Platform.runLater(() ->{
			bibelWidzardViewModel.addEventHandler(BibelEvent.CHAPTER, event ->{
				event.consume();
				assertEquals(10, event.getChapter().getVerses().size());
			});
		});
	}

}
