package com.consacresdeleternel.consacrebeamer.maincontainer.filteredsongs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.data.UserData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;

public class FilteredSongsView implements Initializable {

	@FXML
	FilteredSongsViewModel rootNode;
	@FXML
	CustomTextField ctfSearchSongs;
	@FXML
	TilePane tpSongs;
	private ObservableList<Button> originalData;
	@FXML
	RadioButton rbWorship;
	@FXML
	RadioButton rbAdoration;
	@FXML
	private ToggleGroup  radioGroup;
	Map<String, Predicate<Button>> predicateAll = new HashMap<>();
	ObservableList<Button> copy_ori = null;
	Predicate<Button> predicate = p -> true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		radioGroup.selectedToggleProperty().addListener((obs, oldVal, selectedToggle) -> {
			predicate = p -> true;
			int category = ((UserData)((RadioButton)selectedToggle).getUserData()).getCategory();
			copy_ori = FXCollections.observableList(new ArrayList<>(originalData));
			Predicate<Button> predicateRb = p ->  ((Song) p.getUserData()).getSongCategoryId() == category;
			predicateAll.put("CATEGORY", predicateRb);
			predicateAll.entrySet().forEach(c -> {
				predicate = predicate.and(c.getValue());
			});
			filterSongsAndResetTitlePane(copy_ori, predicate);
		});

		ctfSearchSongs.textProperty().addListener((obs, oldVal, search) -> {
			predicate = p -> true;
			Predicate<Button> predicateSearch = p -> {
				if (search == null || search.isEmpty()) {
					return true;
				}
				return p.getText().startsWith(search);
			};
			copy_ori = FXCollections.observableList(new ArrayList<>(originalData));
			predicateAll.put("SEARCH", predicateSearch);
			predicateAll.entrySet().forEach(c -> {
				predicate = predicate.and(c.getValue());
			});
			filterSongsAndResetTitlePane(copy_ori, predicate);
		});
		this.rootNode.filteredSongsProperty().addListener((obs, oldVal, filteredSongs) -> {
			this.originalData = FXCollections.observableList(filteredSongs.stream().map(song -> {
				Button button = new Button(song.getSongTitle());
				button.setUserData(song);
				return button;
			}).collect(Collectors.toList()));
			copy_ori = FXCollections.observableList(new ArrayList<>(originalData));
			tpSongs.getChildren().clear();
			tpSongs.getChildren().addAll(originalData);
			this.rootNode.setTitlePaneSongsCount(tpSongs.getChildren().size());
		});
	}

	private void filterSongsAndResetTitlePane(ObservableList<Button> liste, Predicate<Button> predicate) {
		SortedList<Button> sortedList = Helper.sortListByPredicate(liste, predicate);
		tpSongs.getChildren().clear();
		tpSongs.getChildren().addAll(sortedList);
		this.rootNode.setTitlePaneSongsCount(tpSongs.getChildren().size());
	}

}
