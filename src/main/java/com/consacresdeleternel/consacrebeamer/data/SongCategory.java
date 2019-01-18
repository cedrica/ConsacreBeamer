package com.consacresdeleternel.consacrebeamer.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songCategory")
public class SongCategory {
	private int id;
	private String name;
	
	public SongCategory(String name) {
		this.name = name;
	}
	public SongCategory() {
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
