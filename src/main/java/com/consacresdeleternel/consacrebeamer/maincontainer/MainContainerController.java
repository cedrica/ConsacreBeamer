package com.consacresdeleternel.consacrebeamer.maincontainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.enums.FileEnum;
import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;

public class MainContainerController implements Initializable {

	@FXML
	MainContainerView rootNode;
	@FXML
	VBox vblistViewContainer;
	@FXML
	VBox vbPreviewContainer;
	@FXML
	ContextMenu contextMenuFile;
	@FXML
	Button btnFile;
	@FXML SplitPane splitPane;
	@FXML FlowPane flowPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<MenuItem> menuItems = createMenuItemsFromFileEnum();
		contextMenuFile.getItems().addAll(menuItems);
		contextMenuFile.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnFile.setContextMenu(contextMenuFile);
		rootNode.setListViewContainer(vblistViewContainer);
		rootNode.setFlowPane(flowPane);
	}

	private List<MenuItem> createMenuItemsFromFileEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (FileEnum fe : FileEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new  SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
			imageView.setFitHeight(15);
			imageView.setFitWidth(15);
			menu.setGraphic(imageView);
			menu.setOnAction(evt -> constructFunction(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private void constructFunction(EventType<FileMenuEvent> fme) {
		rootNode.fireEvent(new FileMenuEvent(fme));
	}

	@FXML
	public void onNewSong() {
	}

	@FXML
	public void onShowContextMenuFile() {
		contextMenuFile.show(btnFile, Side.BOTTOM, 0, 0);
	}

}
