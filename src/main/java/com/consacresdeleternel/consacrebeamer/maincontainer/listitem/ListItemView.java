package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;

public class ListItemView extends HBox{
	private StringProperty itemName = new SimpleStringProperty();
	public  ListItemView(){
		Helper.load(this, Localization.getDefault());
	}
	public final StringProperty itemNameProperty() {
		return this.itemName;
	}
	
	public final String getItemName() {
		return this.itemNameProperty().get();
	}
	
	public final void setItemName(final String itemName) {
		this.itemNameProperty().set(itemName);
	}
	
	
}
