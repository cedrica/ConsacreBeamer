package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import java.net.URL;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.events.ListItemViewEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class ListItemController implements Initializable {

	@FXML ListItemView rootNode;
	@FXML Button itemName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemName.textProperty().bind(rootNode.itemNameProperty());
		itemName.setOnMouseClicked(evt ->{
			if(evt.getButton() == MouseButton.SECONDARY){
				rootNode.fireEvent(new ListItemViewEvent(ListItemViewEvent.SHOW_LIST_ITEM_CONTEXT_MENU));				
			}
		});
	}
}
