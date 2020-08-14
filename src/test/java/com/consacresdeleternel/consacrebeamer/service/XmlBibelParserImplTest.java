package com.consacresdeleternel.consacrebeamer.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.consacresdeleternel.consacrebeamer.consts.BibelFilesConst;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

public class XmlBibelParserImplTest {

	@Test
	public void readBibelBooksFromFile() {
		URL resource = FileUtil.class.getClassLoader().getResource(BibelFilesConst.XML_BIBLE_LOCATION + "fr/Bible_French_Darby.xml");
		File xmlBible = new File(resource.getFile());
		List<BibelBook> bibelBooks = new XmlBibelParserImpl().readBibelBooksFromFile(xmlBible);
		BibelBook genesis = bibelBooks.get(0);
		assertEquals(genesis.getChapters().size(), 50);
		assertEquals(bibelBooks.size(),66);
	}
}
