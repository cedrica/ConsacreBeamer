package com.consacresdeleternel.consacrebeamer.maincontainer.customlistview;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.consacresdeleternel.consacrebeamer.converter.BibleBookConverter;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;
import com.consacresdeleternel.consacrebeamer.enums.Language;
import com.consacresdeleternel.consacrebeamer.factory.BibleTraductionFactory;
import com.consacresdeleternel.consacrebeamer.maincontainer.bibel.BibelEvent;
import com.consacresdeleternel.consacrebeamer.service.BibelParser;
import com.consacresdeleternel.consacrebeamer.service.XmlBibelParserImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

public class CustomListView implements Initializable{

	@FXML CustomListViewModel rootNode;
	@FXML Label lblListTitle;
	@FXML CheckBox checkSelectAll;
	@FXML TextField tfSearch;
	@FXML ListView<BibelBook> lvItems;
	private ObservableList<BibelBook> originalData;
	@FXML ComboBox<File> cbBibelTraduction;
	@FXML ToggleButton tgbFR;
	@FXML ToggleButton tgbDE;
	@FXML ToggleButton tgbEN;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.selectIndexProperty().addListener((obs, oldVal,newVal) ->{
			lvItems.getSelectionModel().select(newVal);
		});

		tfSearch.textProperty().addListener((observable,oldValue,search) -> {
			Predicate<BibelBook> predicate = p -> {
		           if (search == null || search.isEmpty()) {
	                    return true;
	                }
	                return p.getName().startsWith(search);
			};
			lvItems.setItems(new SortedList<>(new FilteredList<>(originalData, predicate)));
			lvItems.refresh();
			rootNode.setSearch(search);
		});

		cbBibelTraduction.disableProperty().bind(tgbFR.pressedProperty().or(tgbDE.pressedProperty()).or(tgbEN.pressedProperty()));
		cbBibelTraduction.setCellFactory(new BibleTraductionFactory());
		cbBibelTraduction.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,selectedTraduction) -> {
			BibelParser bibelParser = new XmlBibelParserImpl();
			List<BibelBook> bibleBooks = bibelParser.readBibelBooksFromFile(selectedTraduction);
			originalData = FXCollections.observableList(bibleBooks);
			lvItems.setItems(originalData);
		});
		
		cbBibelTraduction.itemsProperty().bind(rootNode.traductionsProperty());
		cbBibelTraduction.setConverter(new BibleBookConverter());
		lvItems.setCellFactory(new Callback<ListView<BibelBook>, ListCell<BibelBook>>() {

			@Override
			public ListCell<BibelBook> call(ListView<BibelBook> param) {
				// TODO Auto-generated method stub
				return new ListCell<BibelBook>() {
					@Override
					protected void updateItem(BibelBook item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							setText(item.getName());
							setGraphic(null);
							setOnMouseClicked(evt -> {
								evt.consume();
								if(evt.getButton() == MouseButton.PRIMARY) {
									rootNode.setSelectedBibel(item);
									rootNode.fireEvent(new BibelEvent(item, rootNode, BibelEvent.CUSTOM_LIST));	
								}
							});
						} else {
							setText("");
							setGraphic(null);
						}
						
					}
				};
			}
		});
	}

	@FXML public void onFr() {
		rootNode.fireEvent(new BibelEvent(Language.FR, null, BibelEvent.LOAD_LANGUAGE));
	}

	@FXML public void onDe() {
		rootNode.fireEvent(new BibelEvent(Language.DE, null, BibelEvent.LOAD_LANGUAGE));
	}

	@FXML public void onEn() {
		rootNode.fireEvent(new BibelEvent(Language.EN, null, BibelEvent.LOAD_LANGUAGE));
	}
}
