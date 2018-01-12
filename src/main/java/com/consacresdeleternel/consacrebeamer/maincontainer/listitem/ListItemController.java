package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ListItemController implements Initializable {

	@FXML ListItemView rootNode;
	@FXML Label lblItemName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblItemName.textProperty().bind(rootNode.itemNameProperty());
	}

}
