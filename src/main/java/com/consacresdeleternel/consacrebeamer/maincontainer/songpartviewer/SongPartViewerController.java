package com.consacresdeleternel.consacrebeamer.maincontainer.songpartviewer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SongPartViewerController implements Initializable {

	@FXML
	SongPartViewerView rootNode;
	@FXML VBox vbRows;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.textProperty().addListener((obs, oldVal, newVal) -> {
			final String regex = "<p>(.+?)</p>";

			final Pattern pattern = Pattern.compile(regex);
			final Matcher matcher = pattern.matcher(newVal);
		
			vbRows.getChildren().clear();
			boolean treffer = false;
			while (matcher.find()) {
				treffer = true;
				String parragraph = matcher.group(0);
				Document doc = Jsoup.parse(parragraph);
				String text = doc.body().text();
				if(text == null || text.trim().isEmpty()){
					treffer = false;
					break;
				}
				Label row = new Label(text);
				row.setStyle("-fx-font-size:40px;");
				vbRows.getChildren().add(row);
//				for (int i = 1; i <= matcher.groupCount(); i++) {
//					System.out.println("Group " + i + ": " + matcher.group(i));
//				}
			}
			if(!treffer){

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
