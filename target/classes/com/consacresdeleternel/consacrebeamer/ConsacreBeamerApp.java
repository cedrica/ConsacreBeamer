package com.consacresdeleternel.consacrebeamer;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

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
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		MainContainerManger mainContainerManger = container.instance().select(MainContainerManger.class).get();
		MainContainerView mainContainerView = container.instance().select(MainContainerView.class).get();
		mainContainerView.addEventHandler(FileMenuEvent.EXIT_APPLICATION, evt ->{
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
		primaryStage.setOnCloseRequest(evt ->{
			weld.shutdown();
		});
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
