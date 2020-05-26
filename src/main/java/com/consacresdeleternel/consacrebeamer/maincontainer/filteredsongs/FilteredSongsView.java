package com.consacresdeleternel.consacrebeamer.maincontainer.filteredsongs;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.CustomTextField;

import com.consacresdeleternel.consacrebeamer.data.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
	Predicate<Button> predicateAll = p -> true;
	Predicate<Button> predicateRb = null;
	Predicate<Button> predicateSearch = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rbWorship.selectedProperty().addListener((obs, oldVal, isSelected) -> {
			predicateRb = p -> (isSelected)? ((Song) p.getUserData()).getSongCategoryId() == 1:false;
			predicateAll = predicateAll.and(predicateRb);
			filterSongsAndResetTitlePane(predicateAll);
		});
		rbAdoration.selectedProperty().addListener((obs, oldVal, isSelected) -> {
			predicateRb = p -> (isSelected)? ((Song) p.getUserData()).getSongCategoryId() == 2:false;
			predicateAll = predicateAll.and(predicateRb);
			filterSongsAndResetTitlePane(predicateAll);
		});

		ctfSearchSongs.textProperty().addListener((obs, oldVal, search) -> {
			predicateSearch = p -> {
				if (search == null || search.isEmpty()) {
					return true;
				}
				return p.getText().startsWith(search);
			};
			predicateAll = predicateAll.and(predicateSearch);
			filterSongsAndResetTitlePane(predicateAll);
		});
		this.rootNode.filteredSongsProperty().addListener((obs, oldVal, filteredSongs) -> {
			this.originalData = FXCollections.observableList(filteredSongs.stream().map(song -> {
				Button button = new Button(song.getSongTitle());
				button.setUserData(song);
				return button;
			}).collect(Collectors.toList()));
			tpSongs.getChildren().addAll(originalData);
			this.rootNode.setTitlePaneSongsCount(tpSongs.getChildren().size());
		});
	}

	private void filterSongsAndResetTitlePane(Predicate<Button> predicate) {
		SortedList<Button> sortedList = new SortedList<>(new FilteredList<>(originalData, predicate));
		tpSongs.getChildren().clear();
		tpSongs.getChildren().addAll(sortedList);
		this.rootNode.setTitlePaneSongsCount(tpSongs.getChildren().size());
	}

}
