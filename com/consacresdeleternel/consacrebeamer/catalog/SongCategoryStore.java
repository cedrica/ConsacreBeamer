package com.consacresdeleternel.consacrebeamer.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.consacresdeleternel.consacrebeamer.data.SongCategory;
@XmlRootElement
public class SongCategoryStore {
	
	private List<SongCategory> songCategories = new ArrayList<>();
	
	@XmlElement(name="songCategory", type=SongCategory.class)
	public List<SongCategory> getSongCategories() {
		return songCategories;
	}

	public void setSongCategories(List<SongCategory> songCategories) {
		this.songCategories = songCategories;
	}
}
