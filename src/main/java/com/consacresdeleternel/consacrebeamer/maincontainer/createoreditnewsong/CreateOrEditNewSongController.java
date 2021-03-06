package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong;

import java.net.URL;
import java.util.ResourceBundle;

import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.copyrights.CopyRightsView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.extras.ExtrasView;
import com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.text.TextView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CreateOrEditNewSongController implements Initializable {

	@FXML
	CreateOrEditNewSongView rootNode;
	@FXML
	TextView textView;
	@FXML
	CopyRightsView copyRightsView;
	@FXML
	ExtrasView extrasView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.setTextView(textView);
		rootNode.setCopyRightsView(copyRightsView);
		rootNode.setExtrasView(extrasView);
		textView.titleProperty().addListener((obs, oldVal,newVal) ->{
			copyRightsView.setTitle(newVal);
		});
	}

}
