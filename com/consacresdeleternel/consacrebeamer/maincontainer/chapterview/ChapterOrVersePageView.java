package com.consacresdeleternel.consacrebeamer.maincontainer.chapterview;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class ChapterOrVersePageView<T> implements Initializable{

	@FXML TilePane tpChaptersOrVerses;
	@FXML Label lblTitle;
	@FXML ChapterOrVersePageViewModel<T> rootNode;
	@FXML Button btnGoBack;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnGoBack.visibleProperty().bind(rootNode.isVerseProperty());
		rootNode.chaptersOrVersesProperty().addListener((obs, oldVal,newVal) ->{
			ObservableList<Button> chaptersOrVerses = generateChapterButtonsFormChapter(newVal);
			tpChaptersOrVerses.getChildren().clear();
			tpChaptersOrVerses.getChildren().addAll(chaptersOrVerses);
		});
	}

	private ObservableList<Button> generateChapterButtonsFormChapter(ObservableList<T> chaptersOrVerses) {
		ObservableList<Button> chapterOrVerseBtns = FXCollections.observableList(new ArrayList<Button>());
		for (T chapterOrVerse : chaptersOrVerses) {
			if(chapterOrVerse instanceof Chapter) {
				Button button = new Button(((Chapter)chapterOrVerse).getChapterNumber()+"");
				button.setId("button_"+((Chapter)chapterOrVerse).getChapterNumber());
				button.setOnAction(evt -> handleButton(evt, chapterOrVerse));
				chapterOrVerseBtns.add(button);
			} else if(chapterOrVerse instanceof Verse) {
				Button button = new Button(((Verse)chapterOrVerse).getVerseNumber()+"");
				button.setId("button_"+((Verse)chapterOrVerse).getVerseNumber());
				button.setOnAction(evt -> handleButton(evt, chapterOrVerse));
				chapterOrVerseBtns.add(button);
			}
		}
		return chapterOrVerseBtns;
	}

	private void handleButton(ActionEvent evt, T chapterOrVerse) {
		evt.consume();
		if(chapterOrVerse instanceof Chapter)
			rootNode.fireEvent(new BibelEvent(((Chapter)chapterOrVerse), evt.getTarget(), BibelEvent.CHAPTER));
		else if (chapterOrVerse instanceof Verse)
			rootNode.fireEvent(new BibelEvent(((Verse)chapterOrVerse), evt.getTarget(), BibelEvent.VERSE));
	}

	@FXML public void goBackToChapter() {
		rootNode.fireEvent(new BibelEvent(rootNode.getSelectedBibel(), rootNode, BibelEvent.CUSTOM_LIST));	
	}
	
}
