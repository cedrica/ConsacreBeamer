package com.consacresdeleternel.consacrebeamer.maincontainer.albumlistview;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.data.Book;
import com.consacresdeleternel.consacrebeamer.data.BookWrapper;

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
	@FXML ListView<BookWrapper> lvBooks;
	@FXML CheckBox checkBoxSelectAll;

	private ObservableList<BookWrapper> originalData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.selectAllAlbumProperty().bind(checkBoxSelectAll.selectedProperty());
		rootNode.albumsProperty().addListener((observable,oldVal,data) -> {
			originalData = FXCollections.observableList(data.stream().map(d -> {
				BookWrapper bookWrapper = new BookWrapper();
				bookWrapper.setBook(d);
				bookWrapper.setSelected(false);
				return bookWrapper;
			}).collect(Collectors.toList()));
			
			lvBooks.setItems(originalData);
		});
		tfSearch.textProperty().addListener((observable,oldValue,search) -> {
			Predicate<BookWrapper> predicate = p -> {
		           if (search == null || search.isEmpty()) {
	                    return true;
	                }
	                return p.getBook().getTitle().startsWith(search);
			};
			lvBooks.setItems(new SortedList<>(new FilteredList<>(originalData, predicate)));
			lvBooks.refresh();
		});
		
		lvBooks.setCellFactory(new Callback<ListView<BookWrapper>, ListCell<BookWrapper>>() {
			private int i = 0;
			private ObservableList<BookWrapper> selectedBooks = FXCollections.observableList(new ArrayList<>());
			Predicate<BookWrapper> predicate = p -> true;
			@Override
			public ListCell<BookWrapper> call(ListView<BookWrapper> param) {
				return new ListCell<BookWrapper>() {
					@Override
					protected void updateItem(BookWrapper item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							CheckBox cbBook = new CheckBox();
							cbBook.setId("cb_" + i++);
							cbBook.setText(item.getBook().getTitle());
							setGraphic(cbBook);
							rootNode.selectAllAlbumProperty().addListener((obs, oldVal, isSelected) ->{
								cbBook.setSelected(isSelected);
								if(isSelected) {
									checkBoxSelectAll.setSelected(true);
									selectedBooks = FXCollections.observableList(new ArrayList<>(originalData));	
								}
								
							});
							cbBook.selectedProperty().addListener((obs, oldVal, selected) ->{
								item.setSelected(selected);
								if(selectedBooks == null || selectedBooks.isEmpty()) {
									selectedBooks = FXCollections.observableList(new ArrayList<>(originalData));
									checkBoxSelectAll.setSelected(false);
								}
								predicate = predicate.and(p -> p.isSelected());
								SortedList<BookWrapper> sortListByPredicate = Helper.sortListByPredicate(selectedBooks, predicate);
								List<Book> books = null;
								if(sortListByPredicate.isEmpty()) {
									books = selectedBooks.stream().map(it -> it.getBook()).collect(Collectors.toList());
									rootNode.setSelectedAlbums(FXCollections.observableList(new ArrayList<>(books)));
								} else {
									books = sortListByPredicate.stream().map(it -> it.getBook()).collect(Collectors.toList());
								}
								rootNode.setSelectedAlbums(FXCollections.observableList(new ArrayList<>(books)));
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
