package com.consacresdeleternel.consacrebeamer;

import java.util.ArrayList;
import java.util.List;

import com.consacresdeleternel.consacrebeamer.data.Chapter;
import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListBasicObject;

public class BibelBook extends CustomListBasicObject{
	private List<Chapter> chapters = new ArrayList<Chapter>();

	public List<Chapter> getChapters() {
		return chapters;
	}
	public void addChapters(Chapter chapter) {
		if(this.chapters == null) {
			this.chapters = new ArrayList<Chapter>();
		}
		this.chapters.add(chapter);
	}
	

}
