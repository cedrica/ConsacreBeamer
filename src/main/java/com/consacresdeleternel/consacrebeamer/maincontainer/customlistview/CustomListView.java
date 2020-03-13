package com.consacresdeleternel.consacrebeamer.customlistview;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.consacresdeleternel.consacrebeamer.bibel.BibelEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class CustomListView implements Initializable{

	@FXML CustomListViewModel rootNode;
	@FXML Label lblListTitle;
	@FXML CheckBox checkSelectAll;
	@FXML TextField tfSearch;
	@FXML ListView<CustomListBasicObject> lvItems;
	private FilteredList<CustomListBasicObject> filteredData = null;
	private ObservableList<CustomListBasicObject> originalData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.selectIndexProperty().addListener((obs, oldVal,newVal) ->{
			lvItems.getSelectionModel().select(newVal);
		});
		rootNode.bibelBooksProperty().addListener((observable,oldValue,data) -> {
			originalData = data;
			lvItems.setItems(data);
		});
		rootNode.searchProperty().bind(tfSearch.textProperty());
		rootNode.searchProperty().addListener((observable,oldValue,search) -> {
			Predicate<CustomListBasicObject> predicate = p -> p != null? p.getName().startsWith(search):false;
			filteredData = new FilteredList<>(originalData, predicate);
			lvItems.setItems(filteredData);
		});
		
		lvItems.setCellFactory(new Callback<ListView<CustomListBasicObject>, ListCell<CustomListBasicObject>>() {

			@Override
			public ListCell<CustomListBasicObject> call(ListView<CustomListBasicObject> param) {
				// TODO Auto-generated method stub
				return new ListCell<CustomListBasicObject>() {
					@Override
					protected void updateItem(CustomListBasicObject item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							setText(item.getName());
							setGraphic(null);
							selectedProperty().addListener((observable,oldValue,newValue) -> {
									rootNode.fireEvent(new BibelEvent(item, rootNode, BibelEvent.CUSTOM_LIST));	
								});
						}
						
					}
				};
			}
		});
	}

}
