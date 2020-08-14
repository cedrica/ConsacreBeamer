package com.consacresdeleternel.consacrebeamer.maincontainer.customlistview;

import java.io.File;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.data.BibelBook;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class CustomListViewModel extends BorderPane{
	private StringProperty listTitel = new SimpleStringProperty();
	private ListProperty<BibelBook> bibelBooks = new SimpleListProperty<BibelBook>();
	private ListProperty<File> traductions = new SimpleListProperty<File>();
	private BooleanProperty selectAllVisible = new SimpleBooleanProperty();
	private ObjectProperty<Integer> selectIndex = new SimpleObjectProperty<Integer>();
	private ObjectProperty<CustomListBasicObject> selectedBibel = new SimpleObjectProperty<CustomListBasicObject>();
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
	
	public final ListProperty<BibelBook> bibelBooksProperty() {
		return this.bibelBooks;
	}
	
	public final ObservableList<BibelBook> getBibelBooks() {
		return this.bibelBooksProperty().get();
	}
	
	public final void setBibelBooks(final ObservableList<BibelBook> bibelBooks) {
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

	public final ObjectProperty<CustomListBasicObject> selectedBibelProperty() {
		return this.selectedBibel;
	}
	

	public final CustomListBasicObject getSelectedBibel() {
		return this.selectedBibelProperty().get();
	}
	

	public final void setSelectedBibel(final CustomListBasicObject selectedBibel) {
		this.selectedBibelProperty().set(selectedBibel);
	}




	public final ListProperty<File> traductionsProperty() {
		return this.traductions;
	}
	




	public final ObservableList<File> getTraductions() {
		return this.traductionsProperty().get();
	}
	




	public final void setTraductions(final ObservableList<File> traductions) {
		this.traductionsProperty().set(traductions);
	}
	
	
	
	

}
