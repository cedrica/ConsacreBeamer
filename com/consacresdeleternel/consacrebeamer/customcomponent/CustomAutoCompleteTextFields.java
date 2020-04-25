package com.consacresdeleternel.consacrebeamer.customcomponent;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.consacresdeleternel.consacrebeamer.data.Song;
import com.consacresdeleternel.consacrebeamer.events.SongEvent;
import com.consacresdeleternel.consacrebeamer.utils.FileUtil;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CustomAutoCompleteTextFields {
	private final List<Song> entries;
	// popup GUI
	private ContextMenu entriesPopup;

	public CustomAutoCompleteTextFields(TextField textField, List<Song> items) {
		this.entries = items;
		this.entriesPopup = new ContextMenu();

		registerListener(textField);
	}

	/**
	 * "Suggestion" specific listners
	 * @param textField 
	 */
	private void registerListener(TextField textField) {
		// Add "suggestions" by changing text
		textField.setOnKeyReleased(evt  -> {
			if(evt.getCode() != KeyCode.DOWN) {
				String enteredText = textField.getText();
				if (enteredText == null || enteredText.isEmpty()) {
					entriesPopup.hide();
				} else {
					List<Song> filteredEntries = entries.stream()
							.filter(e -> e.getSongTitle().startsWith(enteredText)).collect(Collectors.toList());
					if (!filteredEntries.isEmpty()) {
						populatePopup(filteredEntries, textField);
						if (!entriesPopup.isShowing()) { // optional
							entriesPopup.show(textField, Side.BOTTOM, 0, 0);
						}
						// no suggestions -> hide
					} else {
						entriesPopup.hide();
					}
				}
			}
		});

		// Hide always by focus-in (optional) and out
		textField.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
			entriesPopup.hide();
		});
	}

	/**
	 * Populate the entry set with the given search results. Display is limited
	 * to 10 entries, for performance.
	 * 
	 * @param searchResult
	 *            The set of matching strings.
	 */
	private void populatePopup(List<Song> searchResult, TextField textField) {
		// List of "suggestions"
		List<MenuItem> menuItems = new LinkedList<>();
		// List size - 10 or founded suggestions count
		int maxEntries = 10;
		int count = Math.min(searchResult.size(), maxEntries);
		// Build list as set of labels
		for (int i = 0; i < count; i++) {
			final Song result = searchResult.get(i);
			MenuItem item = new MenuItem(result.getSongTitle());
			item.setUserData(result);
			menuItems.add(item);
			item.setOnAction(actionEvent -> {
				textField.setText(result.getSongTitle());
				entriesPopup.hide();
				result.setSongHtml(FileUtil.readTxtFileToString(result.getTextFileReference()));
				textField.fireEvent(new SongEvent(SongEvent.ADD_SEARCHED_SONG, result));
			});
		}

		// "Refresh" context menu
		entriesPopup.getItems().clear();
		entriesPopup.getItems().addAll(menuItems);
	}

}
