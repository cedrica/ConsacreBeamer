package com.consacresdeleternel.consacrebeamer.factory;

import java.io.File;

import com.consacresdeleternel.consacrebeamer.common.Helper;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BibleTraductionFactory implements Callback<ListView<File>, ListCell<File>> {
	public BibleTraductionFactory() {
	}
	@Override
	public ListCell<File> call(ListView<File> param) {
		return new ListCell<File>() {
			@Override
			protected void updateItem(File item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					String traductionName = Helper.retrieveTraductionNameFormFileName(item);
					setText(traductionName);
					setItem(item);
					setGraphic(null);
				} else {
					setText("");
					setGraphic(null);
				}
			}

		};

	}
}
