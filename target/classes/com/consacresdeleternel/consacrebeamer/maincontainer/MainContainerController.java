package com.consacresdeleternel.consacrebeamer.maincontainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.enums.EditMenuEnum;
import com.consacresdeleternel.consacrebeamer.enums.ExtrasMenuEnum;
import com.consacresdeleternel.consacrebeamer.enums.FileMenuEnum;
import com.consacresdeleternel.consacrebeamer.enums.HelpMenuEnum;
import com.consacresdeleternel.consacrebeamer.enums.InsertMenuEnum;
import com.consacresdeleternel.consacrebeamer.enums.PresentationMenuEnum;
import com.consacresdeleternel.consacrebeamer.events.EditMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.ExtrasMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.HelpMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.InsertMenuEvent;
import com.consacresdeleternel.consacrebeamer.events.PresentationMenuEvent;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;

public class MainContainerController implements Initializable {

	@FXML
	MainContainerView rootNode;
	@FXML
	VBox vblistViewContainer;
	@FXML
	VBox vbPreviewContainer;
	@FXML
	Button btnFile;
	@FXML
	SplitPane splitPane;
	@FXML
	FlowPane flowPane;
	@FXML
	ContextMenu contextMenuFile;
	@FXML
	ContextMenu contextMenuEdit;
	@FXML
	ContextMenu contextMenuInsert;
	@FXML
	ContextMenu contextMenuPresentation;
	@FXML
	ContextMenu contextMenuExtras;
	@FXML
	ContextMenu contextMenuHelp;
	@FXML
	Button btnEdit;
	@FXML
	Button btnInsert;
	@FXML
	Button btnPresentation;
	@FXML
	Button btnExtras;
	@FXML
	Button btnHelp;
	@FXML
	MaskerPane maskerPane;
	@FXML
	HiddenSidesPane hiddenSidesPane;
	@FXML CustomTextField ctfSearchSong;
	@FXML Image searchImage;
	@FXML ImageView imvSearch;
	@FXML Button btnSearch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<MenuItem> fileMenuItems = createMenuItemsFromFileEnum();
		contextMenuFile.getItems().addAll(fileMenuItems);
		contextMenuFile.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnFile.setContextMenu(contextMenuFile);

		/*--------------------*/
		List<MenuItem> editMenuItems = createMenuItemsFromEditMenuEnum();
		contextMenuEdit.getItems().addAll(editMenuItems);
		contextMenuEdit.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnEdit.setContextMenu(contextMenuEdit);

		/*--------------------*/
		List<MenuItem> insertMenuItems = createMenuItemsFromInsertMenuEnum();
		contextMenuInsert.getItems().addAll(insertMenuItems);
		contextMenuInsert.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnInsert.setContextMenu(contextMenuInsert);

		/*--------------------*/
		List<MenuItem> presentationMenuItems = createMenuItemsFromPresentationMenuEnum();
		contextMenuPresentation.getItems().addAll(presentationMenuItems);
		contextMenuPresentation.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnPresentation.setContextMenu(contextMenuPresentation);

		/*--------------------*/
		List<MenuItem> extrasMenuItems = createMenuItemsFromExtrasMenuEnum();
		contextMenuExtras.getItems().addAll(extrasMenuItems);
		contextMenuExtras.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnExtras.setContextMenu(contextMenuExtras);

		/*--------------------*/
		List<MenuItem> helpMenuItems = createMenuItemsFromHelpMenuEnum();
		contextMenuHelp.getItems().addAll(helpMenuItems);
		contextMenuHelp.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);
		btnHelp.setContextMenu(contextMenuHelp);

		rootNode.setListViewContainer(vblistViewContainer);
		rootNode.setFlowPane(flowPane);
		rootNode.setMaskerPane(maskerPane);
		rootNode.setHiddenSidesPane(hiddenSidesPane);
		hiddenSidesPane.minHeightProperty().bind(rootNode.prefHeightProperty());
		hiddenSidesPane.minWidthProperty().bind(rootNode.prefWidthProperty());
		rootNode.setSearchTextField(ctfSearchSong);
		
		ctfSearchSong.textProperty().addListener((obs, oldVal, newVal)->{
			if(ctfSearchSong.getText().isEmpty()) {
				btnSearch.setGraphic(Helper.setImageView("/icons/search-icon.png"));
			}else {
				btnSearch.setGraphic(Helper.setImageView("/icons/icons8-delete-500.png"));
			}
		});
		
	}

	private List<MenuItem> createMenuItemsFromHelpMenuEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (HelpMenuEnum fe : HelpMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			menu.setOnAction(evt -> fireHelpMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private List<MenuItem> createMenuItemsFromExtrasMenuEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (ExtrasMenuEnum fe : ExtrasMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			menu.setOnAction(evt -> fireExtrasMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private List<MenuItem> createMenuItemsFromPresentationMenuEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (PresentationMenuEnum fe : PresentationMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			menu.setOnAction(evt -> firePresentationMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private List<MenuItem> createMenuItemsFromInsertMenuEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (InsertMenuEnum fe : InsertMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			menu.setOnAction(evt -> fireInsertMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private List<MenuItem> createMenuItemsFromEditMenuEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (EditMenuEnum fe : EditMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			menu.setOnAction(evt -> fireEditMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private List<MenuItem> createMenuItemsFromFileEnum() {
		List<MenuItem> menuItems = new ArrayList<>();
		for (FileMenuEnum fe : FileMenuEnum.values()) {
			MenuItem menu = new MenuItem(Localization.asKey(fe.getName()));
			if (fe.getName().endsWith("separator")) {
				SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
				menuItems.add(separatorMenuItem);
				continue;
			}
			if (!fe.getIconName().trim().isEmpty()) {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(fe.getIconName())));
				imageView.setFitHeight(15);
				imageView.setFitWidth(15);
				menu.setGraphic(imageView);
			}
			if (fe == FileMenuEnum.SAVE_AS) {
				menu.disableProperty().bindBidirectional(rootNode.saveAsProperty());
			}
			menu.setOnAction(evt -> fireFileMenuEvent(fe.getEventType()));
			menuItems.add(menu);
		}
		return menuItems;
	}

	private void fireFileMenuEvent(EventType<FileMenuEvent> fme) {
		rootNode.fireEvent(new FileMenuEvent(fme));
	}

	private void fireEditMenuEvent(EventType<EditMenuEvent> fme) {
		rootNode.fireEvent(new EditMenuEvent(fme));
	}

	private void fireInsertMenuEvent(EventType<InsertMenuEvent> fme) {
		rootNode.fireEvent(new InsertMenuEvent(fme));
	}

	private void firePresentationMenuEvent(EventType<PresentationMenuEvent> fme) {
		rootNode.fireEvent(new PresentationMenuEvent(fme));
	}

	private void fireExtrasMenuEvent(EventType<ExtrasMenuEvent> fme) {
		rootNode.fireEvent(new ExtrasMenuEvent(fme));
	}

	private void fireHelpMenuEvent(EventType<HelpMenuEvent> fme) {
		rootNode.fireEvent(new HelpMenuEvent(fme));
	}

	@FXML
	public void onNewSong() {
	}

	@FXML
	public void onShowContextMenuFile() {
		contextMenuFile.show(btnFile, Side.BOTTOM, 0, 0);
	}

	@FXML
	public void onShowContextMenuEdit() {
		contextMenuEdit.show(btnEdit, Side.BOTTOM, 0, 0);
	}

	@FXML
	public void onShowContextMenuInsert() {
		contextMenuInsert.show(btnInsert, Side.BOTTOM, 0, 0);
	}

	@FXML
	public void onShowContextMenuPresentation() {
		contextMenuPresentation.show(btnPresentation, Side.BOTTOM, 0, 0);
	}

	@FXML
	public void onShowContextMenuExtras() {
		contextMenuExtras.show(btnExtras, Side.BOTTOM, 0, 0);
	}

	@FXML
	public void onShowContextMenuHelp() {
		contextMenuHelp.show(btnHelp, Side.BOTTOM, 0, 0);
	}

	@FXML public void onBtnSearch() {
		if(!ctfSearchSong.getText().isEmpty()) {
			ctfSearchSong.setText("");
		}
	}


}
