package com.consacresdeleternel.consacrebeamer.maincontainer.book;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.scene.layout.StackPane;

public class BookView extends StackPane{
	public  BookView() {
		Helper.load(this, Localization.getDefault());
	}

}
