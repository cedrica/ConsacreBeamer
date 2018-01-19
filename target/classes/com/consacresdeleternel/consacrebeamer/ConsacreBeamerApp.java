package com.consacresdeleternel.consacrebeamer;

import java.io.IOException;
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
		try {
			Runtime.getRuntime().exec("cmd /C start C:/Users/ca.leumaleu/Desktop/db-derby-10.13.1.1-bin/bin/startNetworkServer.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.setOnCloseRequest(evt -> {
			weld.shutdown();
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
