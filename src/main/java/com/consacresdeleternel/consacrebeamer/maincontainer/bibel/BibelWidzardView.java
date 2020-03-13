package com.consacresdeleternel.consacrebeamer.bibel;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Wizard.Flow;
import org.controlsfx.dialog.WizardPane;

import com.consacresdeleternel.consacrebeamer.Verse;
import com.consacresdeleternel.consacrebeamer.chapterview.ChapterOrVersePageViewModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class BibelWidzardView implements Initializable{

	@FXML BibelWidzardViewModel rootNode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WizardPane chapter = new WizardPane();
		chapter.setContent(new ChapterOrVersePageViewModel<Character>());
		WizardPane verse = new WizardPane();
		verse.setContent(new ChapterOrVersePageViewModel<Verse>());
		rootNode.setFlow(new Flow() {
			public Optional<WizardPane> advance(WizardPane currentPage) {
		         return Optional.of(getNext(currentPage));
		     }

		     public boolean canAdvance(WizardPane currentPage) {
		         return currentPage != verse;
		     }
		          
		     private WizardPane getNext(WizardPane currentPage) {
		         if ( currentPage == null ) {
		             return chapter;
		         } else if ( currentPage == chapter) {
		             return verse;
		         }
		         return null;
		     }
		});
	}

}
