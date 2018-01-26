package com.consacresdeleternel.consacrebeamer.common;

import java.io.IOException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Helper {
	public static void load(Object o, ResourceBundle resourceBundle){
		try {
			String name = o.getClass().getName();
			String path = name.replace(".", "/").concat(".fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setResources(resourceBundle);
			fxmlLoader.setRoot(o);
			fxmlLoader.setLocation(Helper.class.getResource("/"+path));
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void load(Object o){
		
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
	public static void makeDraggable(final Stage stage, final Node byNode) {
	    final Delta dragDelta = new Delta();
	    byNode.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	            // record a delta distance for the drag and drop operation.
	            dragDelta.x = stage.getX() - mouseEvent.getScreenX();
	            dragDelta.y = stage.getY() - mouseEvent.getScreenY();
	            byNode.setCursor(Cursor.MOVE);
	        }
	    });
	    byNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	            byNode.setCursor(Cursor.HAND);
	        }
	    });
	    byNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
	            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
	        }
	    });
	    byNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	            if (!mouseEvent.isPrimaryButtonDown()) {
	                byNode.setCursor(Cursor.HAND);
	            }
	        }
	    });
	    byNode.setOnMouseExited(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	            if (!mouseEvent.isPrimaryButtonDown()) {
	                byNode.setCursor(Cursor.DEFAULT);
	            }
	        }
	    });
	}
}
