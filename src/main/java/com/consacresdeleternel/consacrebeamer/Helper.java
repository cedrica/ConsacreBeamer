package com.consacresdeleternel.consacrebeamer;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Helper {
	public static void load(Object o, ResourceBundle resourceBundle) {
		try {
			String name = o.getClass().getName();
			String path = name.replace(".", "/").concat(".fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setResources(resourceBundle);
			fxmlLoader.setRoot(o);
			fxmlLoader.setLocation(Helper.class.getResource("/" + path));
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load(Object o) {
		try {
			String name = o.getClass().getName();
			String path = name.replace(".", "/").concat(".fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setRoot(o);
			fxmlLoader.setLocation(Helper.class.getResource("/" + path));
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String html2text(String htmlText) {
		if (htmlText == null)
			return "";
		Pattern pattern = Pattern.compile("<[^>]*>");
		Matcher matcher = pattern.matcher(htmlText);
		final StringBuffer sb = new StringBuffer(htmlText.length());
		while (matcher.find()) {
			matcher.appendReplacement(sb, " ");
		}
		matcher.appendTail(sb);
		return sb.toString().trim();
	}

	public static Glyph setIcon(Color color, FontAwesome.Glyph iconTyp, double... size) {
		Glyph font = GlyphFontRegistry.font("FontAwesome").create(iconTyp);
		if (color != null)
			font.setColor(color);
		if (size.length > 0)
			font.setFontSize(size[0]);
		return font;
	}

	public static String convertStringToBase64(String text) {
		return (text != null) ? Base64.getEncoder().encodeToString(text.getBytes()) : null;
	}

	public static BooleanProperty concatProperties(Map<Node, BooleanProperty> map) {
		BooleanProperty result = new SimpleBooleanProperty(true);
		for (Entry<Node, BooleanProperty> entry : map.entrySet()) {
			result.and(entry.getValue());
		}
		return result;
	}

	public static ImageView setImageView(String path) {
		ImageView imageView = new ImageView(new Image(Helper.class.getResourceAsStream(path)));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		return imageView;
	}
}
