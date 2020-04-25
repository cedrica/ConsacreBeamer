package com.consacresdeleternel.consacrebeamer.factory;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.SongCategory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SongCategoryCellFactory implements Callback<ListView<SongCategory>, ListCell<SongCategory>> {
	@Override
	public ListCell<SongCategory> call(ListView<SongCategory> param) {
		return new ListCell<SongCategory>() {
			@Override
			protected void updateItem(SongCategory item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setText((item != null)? Localization.asKey(item.getName()):"");
				} else {
					setText(null);
					setGraphic(null);
				}
			}
		};
	}

}
