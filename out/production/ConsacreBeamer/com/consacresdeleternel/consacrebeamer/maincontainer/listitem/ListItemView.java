package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class ListItemView extends HBox {
	private StringProperty itemName = new SimpleStringProperty();
	private ObjectProperty<Integer> position = new SimpleObjectProperty<>();
	private ObjectProperty<Object> itemObject = new SimpleObjectProperty<>();
	private ObjectProperty<ToggleButton> toggle = new SimpleObjectProperty<>();

	public ListItemView() {
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

	public final ObjectProperty<Integer> positionProperty() {
		return this.position;
	}

	public final Integer getPosition() {
		return this.positionProperty().get();
	}

	public final void setPosition(final Integer position) {
		this.positionProperty().set(position);
	}

	public final ObjectProperty<Object> itemObjectProperty() {
		return this.itemObject;
	}

	public final Object getItemObject() {
		return this.itemObjectProperty().get();
	}

	public final void setItemObject(final Object itemObject) {
		this.itemObjectProperty().set(itemObject);
	}

	public final ObjectProperty<ToggleButton> toggleProperty() {
		return this.toggle;
	}

	public final ToggleButton getToggle() {
		return this.toggleProperty().get();
	}

	public final void setToggle(final ToggleButton toggle) {
		this.toggleProperty().set(toggle);
	}

}
