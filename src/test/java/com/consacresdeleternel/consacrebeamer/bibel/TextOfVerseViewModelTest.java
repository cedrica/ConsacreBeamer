package com.consacresdeleternel.consacrebeamer.bibel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.maincontainer.textofverse.TextOfVerseView;
import com.consacresdeleternel.consacrebeamer.maincontainer.textofverse.TextOfVerseViewModel;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class TextOfVerseViewModelTest extends ApplicationTest {

	private TextOfVerseViewModel textOfVerseViewModel;
	
	@Override
	public void start(Stage stage) throws Exception {
		textOfVerseViewModel = new TextOfVerseViewModel();
		List<Chapter> chapters =  mockSomeChapters();
		Chapter selectedChapter = chapters.get(10);
		textOfVerseViewModel.setSelectedVerse(selectedChapter.getVerses().get(20));
		textOfVerseViewModel.setSelectedChapter(selectedChapter);
		stage.setScene(new Scene(textOfVerseViewModel, 200,200));
		stage.show();
		
	}

	@Test
	public void testLook() throws InterruptedException {
	}
	
	@Test
	public void updateHtmlFile() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Verse verse = new Verse();
		verse.setText("Tu aimeras le ddd ton Dieu");
		Chapter chapter = new Chapter();
		chapter.setChapterNumber(2);
		chapter.getVerses().add(verse);
		Method method = TextOfVerseView.class.getDeclaredMethod("updateHtmlFile",Chapter.class, Verse.class);
		method.setAccessible(true);
		Object r = method.invoke(new TextOfVerseView(),chapter,  verse);
		System.out.println(r);
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
			Verse verse = new Verse();
			verse.setVerseNumber(i);
			verse.setText("Lorem ipsun dolore");
			chapter.addVerse(verse);
		}
	}
}
