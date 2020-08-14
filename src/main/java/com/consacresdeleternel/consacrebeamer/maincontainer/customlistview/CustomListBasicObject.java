package com.consacresdeleternel.consacrebeamer.maincontainer.customlistview;

import javax.xml.bind.annotation.XmlElement;

public class CustomListBasicObject {
private String name;

@XmlElement
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
