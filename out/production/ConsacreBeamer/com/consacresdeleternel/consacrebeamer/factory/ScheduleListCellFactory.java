package com.consacresdeleternel.consacrebeamer.factory;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.maincontainer.schedule.ScheduleListView;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;

public class ScheduleListCellFactory implements Callback<ListView<Schedule>, ListCell<Schedule>> {
	private Node rootNode;
	private ToggleGroup toggleGroup = new ToggleGroup();

	public ScheduleListCellFactory(Node rootNode) {
		this.rootNode = rootNode;
	}

	public ListCell<Schedule> call(ListView<Schedule> listView) {
		return new ListCell<Schedule>() {
			@Override
			protected void updateItem(Schedule item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setGraphic(null);
				} else {
					RadioButton radioButton = new RadioButton(item.getName());
					if (rootNode instanceof ScheduleListView) {
						radioButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
							((ScheduleListView) rootNode).setSelectedSchedule(item);
						});
					}
					radioButton.setToggleGroup(toggleGroup);
					setGraphic(radioButton);
				}
			}
		};
	}

}
