package com.consacresdeleternel.consacrebeamer.bibel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.Chapter;
import com.consacresdeleternel.consacrebeamer.Verse;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.chapterview.ChapterOrVersePageViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChapterOrVersePageViewModelTest extends ApplicationTest {
	ChapterOrVersePageViewModel<Chapter> chapterPageViewModel;
	
	@Override
	public void start(Stage stage) throws Exception {
		chapterPageViewModel = new ChapterOrVersePageViewModel();
		stage.setScene(new Scene(chapterPageViewModel, 800, 600));
		stage.show();
	}


	@Test
	public void assertVersenumber() {
		Platform.runLater(() ->{
			chapterPageViewModel.addEventHandler(BibelEvent.CHAPTER, event ->{
				event.consume();
				assertEquals(10, event.getChapter().getVerses().size());
			});
			chapterPageViewModel.setTitle("Genese");
			List<Chapter> chapters = mockSomeChapters();
			chapterPageViewModel.setChaptersOrVerses(FXCollections.observableList(chapters));
			Button chapter4 =  (Button) chapterPageViewModel.lookup("#button_5");
			clickOn(chapter4);
		});
	}


	private List<Chapter> mockSomeChapters() {
		List<Chapter> chapters = new ArrayList<Chapter>();
		for (int i = 1; i < 19; i++) {
			Chapter chapter = new Chapter();
			addXVerse(chapter, i*2);
			chapter.setChapterNumber(i);
			chapters.add(chapter);
		}
		return chapters;
	}


	private void addXVerse(Chapter chapter, int verseCount) {
		for (int i = 0; i < verseCount; i++) {
			chapter.addVerse(new Verse());
		}
	}
}
