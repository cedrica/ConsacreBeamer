package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.BibelBook;

public interface BibelParser {
	public List<BibelBook> readBibelBooksFromFile(File file);
}
