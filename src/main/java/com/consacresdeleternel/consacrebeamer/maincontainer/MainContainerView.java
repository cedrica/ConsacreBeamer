package com.consacresdeleternel.consacrebeamer.maincontainer;

import javax.inject.Singleton;

import com.consacresdeleternel.consacrebeamer.common.Helper;
import com.consacresdeleternel.consacrebeamer.common.Localization;

import javafx.scene.layout.BorderPane;

@Singleton
public class MainContainerView extends BorderPane{
	public  MainContainerView(){
		Helper.load(this, Localization.getDefault());
	}
}
