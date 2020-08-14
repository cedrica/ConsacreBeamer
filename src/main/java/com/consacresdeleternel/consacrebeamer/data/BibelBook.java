package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.consacresdeleternel.consacrebeamer.maincontainer.customlistview.CustomListBasicObject;

@XmlRootElement(name = "BIBLEBOOK")
public class BibelBook extends CustomListBasicObject{
	private List<Chapter> chapters = new ArrayList<Chapter>();

	
	private int bookNumber;
	
	@XmlAttribute(name="bnumber")
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	@XmlElement(name = "CHAPTER")
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
