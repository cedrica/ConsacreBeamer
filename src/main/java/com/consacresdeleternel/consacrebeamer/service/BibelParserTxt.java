package com.consacresdeleternel.consacrebeamer.service;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.enums.Language;

public interface BibelParserTxt {
	public List<BibelBook> readBibelBooksFromFiles(Language language);

}
