package com.consacresdeleternel.consacrebeamer;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestHelperTest {

	@Test
	public void createBibelBooks(){
		List<BibelBook> res = TestHelper.createBibleBooks();
		assertTrue(res.size() > 0);
	}
}
