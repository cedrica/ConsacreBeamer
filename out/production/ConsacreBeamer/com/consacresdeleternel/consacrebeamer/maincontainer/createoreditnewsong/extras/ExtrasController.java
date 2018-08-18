package com.consacresdeleternel.consacrebeamer.maincontainer.createoreditnewsong.extras;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.consacresdeleternel.consacrebeamer.common.Localization;
import com.consacresdeleternel.consacrebeamer.data.Attachment;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

public class ExtrasController implements Initializable {

	@FXML
	ExtrasView rootNode;
	@FXML
	FlowPane fpAttachments;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void onUploadFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Localization.asKey("csb.ExtrasView.uploadAttachements"));
		List<File> files = fileChooser.showOpenMultipleDialog(rootNode.getScene().getWindow());
		if (files != null && !files.isEmpty()) {
			for (File file : files) {
				Label lblFileName = new Label(file.getName());
				lblFileName.setStyle("-fx-underline:true;");
				fpAttachments.getChildren().add(lblFileName);
			}
			List<Attachment> attachments = files.stream().map(f -> {
				Attachment attachment = new Attachment();
				attachment.setAttachement(f);
				return attachment;
			}).collect(Collectors.toList());
			rootNode.setAttachments(FXCollections.observableList(attachments));
		}

	}

}
