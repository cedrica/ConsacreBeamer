package com.consacresdeleternel.consacrebeamer.customlistview;

import com.consacresdeleternel.consacrebeamer.Helper;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class CustomListViewModel extends VBox{
	private StringProperty listTitel = new SimpleStringProperty();
	private ListProperty<CustomListBasicObject> bibelBooks = new SimpleListProperty<CustomListBasicObject>();
	private BooleanProperty selectAllVisible = new SimpleBooleanProperty();
	private ObjectProperty<Integer> selectIndex = new SimpleObjectProperty<Integer>();
	private BooleanProperty searchVisible = new SimpleBooleanProperty();
	private StringProperty search = new SimpleStringProperty();
	
	
	public CustomListViewModel() {
		Helper.load(this);
	}
	
	public final StringProperty listTitelProperty() {
		return this.listTitel;
	}
	
	public final String getListTitel() {
		return this.listTitelProperty().get();
	}
	
	public final void setListTitel(final String listTitel) {
		this.listTitelProperty().set(listTitel);
	}
	
	public final ListProperty<CustomListBasicObject> bibelBooksProperty() {
		return this.bibelBooks;
	}
	
	public final ObservableList<CustomListBasicObject> getBibelBooks() {
		return this.bibelBooksProperty().get();
	}
	
	public final void setBibelBooks(final ObservableList<CustomListBasicObject> bibelBooks) {
		this.bibelBooksProperty().set(bibelBooks);
	}

	public final BooleanProperty selectAllVisibleProperty() {
		return this.selectAllVisible;
	}
	

	public final boolean isSelectAllVisible() {
		return this.selectAllVisibleProperty().get();
	}
	

	public final void setSelectAllVisible(final boolean selectAllVisible) {
		this.selectAllVisibleProperty().set(selectAllVisible);
	}

	public final BooleanProperty searchVisibleProperty() {
		return this.searchVisible;
	}
	

	public final boolean isSearchVisible() {
		return this.searchVisibleProperty().get();
	}
	

	public final void setSearchVisible(final boolean searchVisible) {
		this.searchVisibleProperty().set(searchVisible);
	}
	

	public final StringProperty searchProperty() {
		return this.search;
	}
	

	public final String getSearch() {
		return this.searchProperty().get();
	}
	

	public final void setSearch(final String search) {
		this.searchProperty().set(search);
	}

	public final ObjectProperty<Integer> selectIndexProperty() {
		return this.selectIndex;
	}
	

	public final Integer getSelectIndex() {
		return this.selectIndexProperty().get();
	}
	

	public final void setSelectIndex(final Integer selectIndex) {
		this.selectIndexProperty().set(selectIndex);
	}

}
