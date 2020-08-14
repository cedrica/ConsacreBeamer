package com.consacresdeleternel.consacrebeamer.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.service.BibelParser;
import com.consacresdeleternel.consacrebeamer.service.XmlBibelParserImpl;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.concurrent.Task;

public class LoadBibelWidzard extends Task<List<File>> {
	BibelParser bibelParser = new XmlBibelParserImpl();
	
	@Override
	protected List<File> call() throws Exception {
		return Arrays.asList(FileUtil.loadXmlBibelFiles(Language.FR));
	}


}
