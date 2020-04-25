package com.consacresdeleternel.consacrebeamer.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CharsetDetectorTest {

	CharsetDetector charsetDetector;
	@Before
	public void setUp() {
		charsetDetector = new CharsetDetector();
	}
	
	@Test
	public void detectCharset() {
		String charset = charsetDetector.charset("Longtemps aprÃ¨s, le roi d'Egypte mourut, et les enfants d'IsraÃ«l gÃ©missaient encore sous la servitude, et poussaient des cris. Ces cris, que leur arrachait la servitude, montÃ¨rent jusqu'Ã  Dieu.");
		System.out.println(charset);
		assertEquals("utf-8", charset);
		
	}
}
