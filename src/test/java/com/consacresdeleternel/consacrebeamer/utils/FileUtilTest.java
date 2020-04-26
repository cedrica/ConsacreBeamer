package com.consacresdeleternel.consacrebeamer.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class FileUtilTest {
	
	@Test
	public void readLines() throws IOException{
		List<String> lines = FileUtil.readLines("/bibel/bibel_en.txt");
		assertEquals(lines.size(), 3);
		assertEquals(lines.get(2).trim(), "et alors");
		assertEquals(lines.get(1).trim(), "un test");
		assertEquals(lines.get(0).trim(), "je suis");
	}

}