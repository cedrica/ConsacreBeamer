package com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.consacresdeleternel.consacrebeamer.data.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class AlbumListViewView implements Initializable{

	@FXML AlbumListViewViewModel rootNode;
	@FXML TextField tfSearch;
	@FXML ListView<Book> lvBooks;
	@FXML CheckBox checkBoxSelectAll;

	private ObservableList<Book> originalData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.albumsProperty().addListener((observable,oldVal,data) -> {
			originalData = FXCollections.observableList(data);
			lvBooks.setItems(originalData);
		});
		tfSearch.textProperty().addListener((observable,oldValue,search) -> {
			Predicate<Book> predicate = p -> {
		           if (search == null || search.isEmpty()) {
	                    return true;
	                }
	                return p.getTitle().startsWith(search);
			};
			lvBooks.setItems(new SortedList<>(new FilteredList<>(originalData, predicate)));
			lvBooks.refresh();
		});
		
		lvBooks.setCellFactory(new Callback<ListView<Book>, ListCell<Book>>() {

			@Override
			public ListCell<Book> call(ListView<Book> param) {
				return new ListCell<Book>() {
					private ObservableList<Book> selectedBooks = FXCollections.observableList(new ArrayList<>());

					@Override
					protected void updateItem(Book item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							CheckBox cbBook = new CheckBox();
							cbBook.setText(item.getTitle());
							setGraphic(cbBook);
							cbBook.selectedProperty().addListener((obs, oldVal, isSelected) ->{
								if(isSelected) {
									selectedBooks.add(item);
								} else {
									selectedBooks.remove(item);
								}
								rootNode.setSelectedAlbums(selectedBooks);
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

}
