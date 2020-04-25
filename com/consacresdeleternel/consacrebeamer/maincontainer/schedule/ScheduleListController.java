package com.consacresdeleternel.consacrebeamer.maincontainer.schedule;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Schedule;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.ExtrasMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.ScheduleEvent;
import com.consacresdeleternel.consacrebeamer.factory.ScheduleListCellFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScheduleListController implements Initializable{

	@FXML ScheduleListView rootNode;
	@FXML ListView<Schedule> clvScheduleList;
	@FXML VBox vbSchedulelistViewer;
	@FXML CustomTextField ctfScheduleName;
	@FXML Button btnChooseSchedule;
	@FXML Button btnDeleteSchedule;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clvScheduleList.setCellFactory(new ScheduleListCellFactory(rootNode));
		clvScheduleList.itemsProperty().bind(rootNode.scheduleItemsProperty());
		rootNode.selectedScheduleProperty().addListener((obs, oldVal, newVal) -> {
			vbSchedulelistViewer.getChildren().clear();
			for (Song song  : newVal.getSongs()) {
				vbSchedulelistViewer.getChildren().add(new Text(song.getSongTitle()));
			}
		});		
	}

	@FXML public void onSearchSchedule() {}

	@FXML public void onCloseHiddenSidePane() {
		rootNode.fireEvent(new ExtrasMenuEvent(ExtrasMenuEvent.SHOW_SCHEDULE_LIST));
	}

	@FXML public void onDeleteSchedule() {
		rootNode.fireEvent(new ScheduleEvent(ScheduleEvent.DELETE_SCHEDULE, rootNode.getSelectedSchedule()));
	}

	@FXML public void onChooseSchedule() {
		
	}

}
