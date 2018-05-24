package com.consacresdeleternel.consacrebeamer.maincontainer.schedule.create;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CreateScheduleController implements Initializable{

	@FXML CreateScheduleView createSchedulView;
	@FXML TextField tfScheduleName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createSchedulView.scheduleNameProperty().bindBidirectional(tfScheduleName.textProperty());
		createSchedulView.invalidProperty().bind(tfScheduleName.textProperty().isEmpty());
	}
	


}
