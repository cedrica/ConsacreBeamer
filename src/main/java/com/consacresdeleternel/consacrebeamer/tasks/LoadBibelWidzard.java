package com.consacresdeleternel.consacrebeamer.tasks;

import java.util.List;

import com.consacresdeleternel.consacrebeamer.BibelBook;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.service.BibelParserTxt;
import com.consacresdeleternel.consacrebeamer.service.BibelParserTxtImpl;

import javafx.concurrent.Task;

public class LoadBibelWidzard extends Task<List<BibelBook>> {
	BibelParserTxt bibelParserTxtImpl = new BibelParserTxtImpl();
	
	@Override
	protected List<BibelBook> call() throws Exception {
		return bibelParserTxtImpl.readBibelBooksFromFiles(Language.FR);
	}


}
