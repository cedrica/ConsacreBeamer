package com.consacresdeleternel.consacrebeamer.maincontainer.listitem;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.scene.layout.HBox;

public class ListItemView extends HBox{
	public  ListItemView(){
		Helper.load(this, Localization.getDefault());
	}
}
