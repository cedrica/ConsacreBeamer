package com.consacresdeleternel.consacrebeamer.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.StringUtils;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import com.consacresdeleternel.consacrebeamer.exceptions.BookNotFoundException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Helper {

	public static String findBibelBookNameByNumber(int bookNum, List<String> bibelBookNames) throws BookNotFoundException {
		if (bibelBookNames != null && !bibelBookNames.isEmpty())
			return bibelBookNames.get(bookNum);
		else {
			throw new BookNotFoundException("No Bible Book for number "+ bookNum);
		}
			
	}
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

	public static String retrieveTraductionNameFormFileName(File item) {
		String fileName = item.getName();
		String[] fnames = fileName.split("_");
		if (fnames == null || fnames.length <= 0) {
			throw new RuntimeErrorException(null, "File name muss match a certain standard. But it doesn´t");
		}
		return StringUtils.substringBefore(fnames[2], ".xml");
	}
	
	public static void resetHtmlFile(byte[] data) {
		try {
		    Files.write(Paths.get("src","main","resources", "selected-verse.html"), data);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public static String convertStringToBase64(String text) {
		return (text != null) ? Base64.getEncoder().encodeToString(text.getBytes()) : null;
	}

	public static void makeDraggable(final Stage stage, final Node byNode) {
		final Delta dragDelta = new Delta();
		byNode.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = stage.getX() - mouseEvent.getScreenX();
				dragDelta.y = stage.getY() - mouseEvent.getScreenY();
				byNode.setCursor(Cursor.MOVE);
			}
		});
		byNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				byNode.setCursor(Cursor.HAND);
			}
		});
		byNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.setX(mouseEvent.getScreenX() + dragDelta.x);
				stage.setY(mouseEvent.getScreenY() + dragDelta.y);
			}
		});
		byNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					byNode.setCursor(Cursor.HAND);
				}
			}
		});
		byNode.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					byNode.setCursor(Cursor.DEFAULT);
				}
			}
		});
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

	public static <T> SortedList<T> sortListByPredicate(ObservableList<T> originalData,
			Predicate<T> predicate) {
		return new SortedList<>(new FilteredList<>(originalData, predicate));
	}
}
