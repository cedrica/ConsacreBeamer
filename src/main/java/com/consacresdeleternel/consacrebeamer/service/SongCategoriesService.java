package com.consacresdeleternel.consacrebeamer.service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.consacresdeleternel.consacrebeamer.catalog.SongCategoryStore;
import com.consacresdeleternel.consacrebeamer.data.SongCategory;

public class SongCategoriesService {
	private static String SONGCATEGORYSTORE_XML = "categories.xml";

	public List<SongCategory> songCategories() {

		try {
			ClassLoader classLoader = SongCategoriesService.class.getClassLoader();
			InputStream is = classLoader.getResourceAsStream(SONGCATEGORYSTORE_XML);
			//File file = new File(SONGCATEGORYSTORE_XML);
			JAXBContext jaxbContext = JAXBContext.newInstance(SongCategoryStore.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SongCategoryStore songCategoryStrore = (SongCategoryStore) jaxbUnmarshaller.unmarshal(is);
			return songCategoryStrore.getSongCategories();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SongCategory createSongCategoryById(int songCategoryId) {
		SongCategoriesService songCategoriesService = new SongCategoriesService();
		return (songCategoriesService.songCategories() != null)? 
				songCategoriesService.songCategories().stream().filter(song -> song.getId() == songCategoryId).collect(Collectors.toList()).get(0):null;
		
	}

}
