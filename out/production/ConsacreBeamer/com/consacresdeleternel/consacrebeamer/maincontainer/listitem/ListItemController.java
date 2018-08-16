package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import java.net.URL;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.events.CreateOrEditNewSongEvent;
import com.consacresdeleternel.consacrebeamer.events.ListItemViewEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ListItemController implements Initializable {

	@FXML
	ListItemView rootNode;
	@FXML
	ToggleButton tgbItemName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tgbItemName.textProperty().bind(rootNode.itemNameProperty());
		rootNode.setToggle(tgbItemName);
	}

	@FXML
	public void onSelectItem() {
		rootNode.fireEvent(
				new CreateOrEditNewSongEvent(CreateOrEditNewSongEvent.SELECT_SONG, rootNode.getItemObject()));
	}

	@FXML
	public void onShowContextMenu(MouseEvent evt) {
		if (evt.getButton() == MouseButton.SECONDARY) {
			rootNode.fireEvent(new ListItemViewEvent(ListItemViewEvent.SHOW_LIST_ITEM_CONTEXT_MENU));
		}
	}
}
