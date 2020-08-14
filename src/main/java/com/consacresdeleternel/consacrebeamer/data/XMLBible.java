package com.consacresdeleternel.consacrebeamer.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XMLBIBLE")
public class XMLBible {
	
	private List<BibelBook> bibelBooks = new ArrayList<>();

	@XmlElement(name = "BIBLEBOOK")
	public List<BibelBook> getBibelBooks() {
		return bibelBooks;
	}

	public void setBibelBooks(List<BibelBook> bibelBooks) {
		this.bibelBooks = bibelBooks;
	}
	

}
