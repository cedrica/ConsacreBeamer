package com.consacresdeleternel.consacrebeamer.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class SVGUtil {

	public static void setSVGGraphicInJFXButton(Button btn, String svgFilePath) {
		BufferedImageTranscoder trans = new BufferedImageTranscoder();
		try (InputStream file = SVGUtil.class.getResourceAsStream(svgFilePath)) {
			TranscoderInput transIn = new TranscoderInput(file);
			try {
				trans.transcode(transIn, null);
				WritableImage img = SwingFXUtils.toFXImage(trans.getBufferedImage(), null);
				ImageView imageView = new ImageView(img);
				imageView.setFitHeight(btn.getPrefHeight());
				imageView.setFitWidth(btn.getPrefWidth());
				btn.setGraphic(imageView);
			} catch (TranscoderException ex) {
				ex.printStackTrace();
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
