package com.consacresdeleternel.consacrebeamer.service;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.consacresdeleternel.consacrebeamer.catalog.SongCategoryStore;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.SongCategory;

public class SongCategoriesService {
	private static String SONGCATEGORYSTORE_XML = "categories.xml";

	public List<SongCategory> songCategories() {

		try {
			ClassLoader classLoader = SongCategoriesService.class.getClassLoader();
			File file = new File(classLoader.getResource(SONGCATEGORYSTORE_XML).getFile());
			//File file = new File(SONGCATEGORYSTORE_XML);
			JAXBContext jaxbContext = JAXBContext.newInstance(SongCategoryStore.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SongCategoryStore songCategoryStrore = (SongCategoryStore) jaxbUnmarshaller.unmarshal(file);
			return songCategoryStrore.getSongCategories();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static SongCategory createSongCategoryByName(String songCategoryName) {
		if(songCategoryName == null)
			return null;
		return new SongCategory(Localization.asKey(songCategoryName));
	}

}
