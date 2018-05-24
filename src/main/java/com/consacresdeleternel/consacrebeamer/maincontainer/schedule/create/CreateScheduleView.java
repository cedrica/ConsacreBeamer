package com.consacresdeleternel.consacrebeamer.maincontainer.schedule.create;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;

public class CreateScheduleView extends VBox {

	private StringProperty scheduleName = new SimpleStringProperty();
	private BooleanProperty invalid = new SimpleBooleanProperty();

	public CreateScheduleView() {
		Helper.load(this, Localization.getDefault());
	}

	public final StringProperty scheduleNameProperty() {
		return this.scheduleName;
	}

	public final String getScheduleName() {
		return this.scheduleNameProperty().get();
	}

	public final void setScheduleName(final String scheduleName) {
		this.scheduleNameProperty().set(scheduleName);
	}

	public final BooleanProperty invalidProperty() {
		return this.invalid;
	}

	public final boolean isInvalid() {
		return this.invalidProperty().get();
	}

	public final void setInvalid(final boolean invalid) {
		this.invalidProperty().set(invalid);
	}

}
