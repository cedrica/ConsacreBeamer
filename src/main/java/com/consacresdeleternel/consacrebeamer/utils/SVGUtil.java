package com.consacresdeleternel.consacrebeamer.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class SVGUtil {

	public static void setSVGGraphicInJFXButton(Button btn, String svgFilePath) {
		BufferedImageTranscoder trans = new BufferedImageTranscoder();
		DoubleProperty width = new SimpleDoubleProperty();
		DoubleProperty height = new SimpleDoubleProperty();

		try (InputStream file = SVGUtil.class.getResourceAsStream(svgFilePath)) {
			TranscoderInput transIn = new TranscoderInput(file);
			try {
				trans.transcode(transIn, null);
				// Use WritableImage if you want to further modify the image (by
				// using a PixelWriter)
				WritableImage img = SwingFXUtils.toFXImage(trans.getBufferedImage(), null);

				ImageView iv = new ImageView(img);
				width.bind(btn.widthProperty());
				height.bind(btn.heightProperty());
				iv.fitWidthProperty().bind(width);
				iv.fitHeightProperty().bind(height);
				// iv.setFitWidth(btn.getBoundsInParent().getWidth());
				// iv.setFitHeight(btn.getBoundsInParent().getWidth());
				btn.setGraphic(iv);
			} catch (TranscoderException ex) {
			}
		} catch (IOException io) {
		}

	}

	public static void setSVGImageInJFXButton(Button btn, Image img) {
		ImageView iv = new ImageView(img);
		iv.fitWidthProperty().bind(btn.widthProperty());
		iv.fitHeightProperty().bind(btn.heightProperty());
		btn.setGraphic(iv);

	}

	public static WritableImage getSvgGraphicAsImage(String svgFilePath) {
		BufferedImageTranscoder trans = new BufferedImageTranscoder();

		try (InputStream file = SVGUtil.class.getResourceAsStream(svgFilePath)) {
			TranscoderInput transIn = new TranscoderInput(file);
			try {
				trans.transcode(transIn, null);
				// Use WritableImage if you want to further modify the image (by
				// using a PixelWriter)
				WritableImage img = SwingFXUtils.toFXImage(trans.getBufferedImage(), null);

				return img;
			} catch (TranscoderException ex) {
			}
		} catch (IOException io) {
		}
		return null;

	}

	public static void establishBindingsAndSVGOnButton(ImageView iv, Button btn, String pathOffImage, String pathOnImage, boolean spanOnButton ) {
		WritableImage imgOn = SVGUtil.getSvgGraphicAsImage(pathOnImage);
		WritableImage imgOff = SVGUtil.getSvgGraphicAsImage(pathOffImage);

		if(spanOnButton){
			iv.fitWidthProperty().bind(btn.widthProperty());
			iv.fitHeightProperty().bind(btn.heightProperty());
		}
		
		iv.imageProperty().bind(Bindings.when(btn.hoverProperty()).then(imgOn).otherwise(imgOff));
		btn.setGraphic(iv);
	}
}
