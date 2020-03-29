package com.consacresdeleternel.consacrebeamer.manager;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.data.Verse;
import com.consacresdeleternel.consacrebeamer.enums.Language;

import javafx.util.Pair;

public class InsertManagerTest {

	private InsertManager insertManager;
	private List<Pair<String, String>> pairList;
	
	@Before
	public void setUp() {
		insertManager = new InsertManager();

		Pair<String, String> pair1 = new Pair<String, String>("1","je suis");
		Pair<String, String> pair2 = new Pair<String, String>("2","tu es");
		Pair<String, String> pair3 = new Pair<String, String>("3","il est");
		pairList = Arrays.asList(pair1, pair2, pair3);
	}
	
	@Test
	public void constructVerses() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = insertManager.getClass().getDeclaredMethod("constructVerses", List.class);
		method.setAccessible(true);
		Object r = method.invoke(insertManager, pairList);
		assertEquals(3, ((List<Verse>) r).size());
		assertEquals("je suis", ((List<Verse>) r).get(0).getText());
	}
	
	
	@Test
	public void constructChapters() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, List<Pair<String, String>>> chapterMap = new HashMap<String, List<Pair<String,String>>>();
		chapterMap.put("2", pairList);
		Method method = insertManager.getClass().getDeclaredMethod("constructChapters", Map.class);
		method.setAccessible(true);
		Object r = method.invoke(insertManager, chapterMap);
		assertEquals(1, ((List<Chapter>) r).size());
		assertEquals(3, ((List<Chapter>) r).get(0).getVerses().size());
	}
	
	@Test
	public void readBooksFromFiles() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = insertManager.getClass().getDeclaredMethod("readBooksFromFiles", Language.class, String.class);
		method.setAccessible(true);
		Object r = method.invoke(insertManager,Language.FR, "src/main/resources/bibel/French__Louis_Segond_(1910)__ls1910__LTR.txt");
		assertEquals(66, ((List<BibelBook>)r).size());
	}
	
	@Test
	public void retrievebookNum() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = insertManager.getClass().getDeclaredMethod("retrievebookNum", String.class);
		method.setAccessible(true);
		Object r = method.invoke(insertManager,"01O||1||1||Au commencement, Dieu crÃ©a les cieux et la terre");
		assertEquals("1", r.toString());
	}
	
	@Test
	public void retrievebookNum_matthew() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = insertManager.getClass().getDeclaredMethod("retrievebookNum", String.class);
		method.setAccessible(true);
		Object r = method.invoke(insertManager,"41N||1||1||Au commencement, Dieu crÃ©a les cieux et la terre");
		assertEquals("41", r.toString());
	}
}
