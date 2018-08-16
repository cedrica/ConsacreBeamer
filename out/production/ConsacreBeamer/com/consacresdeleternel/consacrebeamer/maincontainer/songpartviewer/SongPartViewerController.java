package com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer;

import java.net.URL;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SongPartViewerController implements Initializable {

	@FXML
	SongPartViewerView rootNode;
	@FXML
	VBox vbRows;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.textProperty().addListener((obs, oldVal, newVal) -> {
			Document doc = Jsoup.parse(newVal);
			Elements elements = doc.select("p");
			vbRows.getChildren().clear();
			for (Element element : elements) {
				doc = Jsoup.parse(element.toString());
				String text = doc.body().text();
				if(text.trim().isEmpty()){
					continue;
				}
				Label row = new Label(text);
				row.setStyle("-fx-font-size:40px;");
				vbRows.getChildren().add(row);
			}
			if(vbRows.getChildren().size() == 0){
				newVal = newVal.replaceAll("<br>", " ##br## ");
				Document doc2 = Jsoup.parse(newVal);
				String text2 = doc2.body().text();
				String[] split = text2.split("##br##");
				for (String t : split) {
					Label row = new Label(t);
					row.setStyle("-fx-font-size:40px;");
					vbRows.getChildren().add(row);
				}
			}
		});
	}

}
