package com.consacresdeleternel.consacrebeamer.maincontainer.schedule;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Schedule;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class ScheduleListView extends BorderPane {

	private ListProperty<Schedule> scheduleItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Schedule> selectedSchedule = new SimpleObjectProperty<>();
	private StringProperty searchedScheduleName = new SimpleStringProperty();
	private BooleanProperty invalid = new SimpleBooleanProperty();

	public ScheduleListView() {
		Helper.load(this, Localization.getDefault());
	}

	public final ListProperty<Schedule> scheduleItemsProperty() {
		return this.scheduleItems;
	}

	public final ObservableList<Schedule> getScheduleItems() {
		return this.scheduleItemsProperty().get();
	}

	public final void setScheduleItems(final ObservableList<Schedule> scheduleItems) {
		this.scheduleItemsProperty().set(scheduleItems);
	}

	public final ObjectProperty<Schedule> selectedScheduleProperty() {
		return this.selectedSchedule;
	}

	public final Schedule getSelectedSchedule() {
		return this.selectedScheduleProperty().get();
	}

	public final void setSelectedSchedule(final Schedule selectedSchedule) {
		this.selectedScheduleProperty().set(selectedSchedule);
	}

	public final StringProperty searchedScheduleNameProperty() {
		return this.searchedScheduleName;
	}

	public final String getSearchedScheduleName() {
		return this.searchedScheduleNameProperty().get();
	}

	public final void setSearchedScheduleName(final String searchedScheduleName) {
		this.searchedScheduleNameProperty().set(searchedScheduleName);
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
