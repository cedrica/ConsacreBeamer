package com.consacresdeleternel.consacrebeamer;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConsacreBeamerApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		// try {
		// XMLSlideShow ppt = new XMLSlideShow();
		// // getting the slide master object
		// XSLFSlideMaster slideMaster = ppt.getSlideMasters()[0];
		//
		// // select a layout from specified list
		// XSLFSlideLayout slidelayout =
		// slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		//
		// // creating a slide with title and content layout
		// XSLFSlide slide = ppt.createSlide(slidelayout);
		// // selection of title place holder
		// XSLFTextShape title = slide.getPlaceholder(0);
		//
		// // setting the title in it
		// title.setText("");
		//
		// // selection of body placeholder
		// XSLFTextShape body = slide.getPlaceholder(1);
		//
		// // clear the existing text in the slide
		// body.clearText();
		//
		// // adding new paragraph
		// body.addNewTextParagraph().addNewTextRun().setText("this is my first
		// slide body"
		// + "this is my first slide body"
		// + "this is my first slide body"
		// + "this is my first slide body"
		// + "this is my first slide body"
		// + "this is my first slide body");
		// File createTempFile = File.createTempFile("temp", ".pptx");
		//
		// try (FileOutputStream out = new
		// FileOutputStream(createTempFile.getAbsolutePath())) {
		// ppt.write(out);
		// }
		// String cmd = "C:\\Program Files (x86)\\Microsoft
		// Office\\Office14\\PPTVIEW.EXE "
		// + createTempFile.getAbsolutePath();
		// System.out.println(cmd);
		// Runtime.getRuntime().exec(cmd);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

		// Ask the test to render into the SVG Graphics2D implementation.
		// TestSVGGen test = new TestSVGGen();
		// test.paint(svgGenerator);

		// Finally, stream out SVG to the standard output using
		// UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		Writer out;
		try {
			out = new OutputStreamWriter(System.out, "UTF-8");
			svgGenerator.stream(out, useCSS);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		MainContainerManger mainContainerManger = container.instance().select(MainContainerManger.class).get();
		MainContainerView mainContainerView = container.instance().select(MainContainerView.class).get();
		mainContainerView.addEventHandler(FileMenuEvent.EXIT_APPLICATION, evt -> {
			Platform.exit();
		});
		mainContainerManger.init(mainContainerView);
		Scene scene = new Scene(mainContainerView);
		scene.getStylesheets().add("/css/Material.css");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/consacre-beamer-icon.PNG")));
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);
		primaryStage.setMaximized(true);
		primaryStage.setOnCloseRequest(evt -> {
			weld.shutdown();
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
