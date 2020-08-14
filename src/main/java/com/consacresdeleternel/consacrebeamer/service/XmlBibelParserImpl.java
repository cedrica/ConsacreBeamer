package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.consts.BibelBooksConsts;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;
import com.consacresdeleternel.consacrebeamer.data.XMLBible;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;

public class XmlBibelParserImpl implements BibelParser {

	private final static Logger LOG = Logger.getLogger(XmlBibelParserImpl.class.getSimpleName()); 
	@Override
	public List<BibelBook> readBibelBooksFromFile(File bible) {
		List<BibelBook> books = new ArrayList();
		if(bible == null) return books;
		try {
			ClassLoader classLoader = XmlBibelParserImpl.class.getClassLoader();
			JAXBContext jaxbContext = JAXBContext.newInstance(XMLBible.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			XMLBible xmlBible = (XMLBible) jaxbUnmarshaller.unmarshal(bible);
			books =  xmlBible.getBibelBooks();

			Language language = Language.FR; // DEFAULT
			if(bible.getName().contains("English")) {
				language = Language.EN;
			} else if(bible.getName().contains("German")) {
				language = Language.DE;
			}
			for (BibelBook bibelBook : books) {
				String name = "";
				try {
					if(Language.FR.equals(language)) {
						name = Helper.findBibelBookNameByNumber(bibelBook.getBookNumber() - 1, BibelBooksConsts.BIBEL_BOOK_NAMES_FR);
					} else if(Language.EN.equals(language)) {
						name = Helper.findBibelBookNameByNumber(bibelBook.getBookNumber() - 1, BibelBooksConsts.BIBEL_BOOK_NAMES_EN);
					} else if(Language.DE.equals(language)) {
						name = Helper.findBibelBookNameByNumber(bibelBook.getBookNumber() - 1, BibelBooksConsts.BIBEL_BOOK_NAMES_DE);
					}
				} catch (BookNotFoundException e) {
					LOG.log(Level.SEVERE, e.getMessage(),e);
				}
				bibelBook.setName(name);
			}
		} catch (JAXBException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return books;
	}

}