package com.consacresdeleternel.consacrebeamer;

import java.lang.Thread.UncaughtExceptionHandler;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.launcher.LauncherView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;
import com.consacresdeleternel.consacrebeamer.task.LauncherTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConsacreBeamerApp extends Application {


	@Override
	public void start(Stage primaryStage) {


		LauncherTask launcherTask = new LauncherTask(primaryStage);
		new Thread(launcherTask).start();
		launcherTask.valueProperty().addListener((obs,oldVal,newVal)->{
			MainContainerView mainContainerView = newVal.getKey();
			MainContainerManger mainContainerManger = newVal.getValue();
			mainContainerView.addEventHandler(FileMenuEvent.EXIT_APPLICATION, evt -> {
				Platform.exit();
			});
			mainContainerManger.init(mainContainerView);
			Scene scene = new Scene(mainContainerView);
			scene.getStylesheets().add("/css/Material.css");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.err.println("Fehler ist aufgetreten");
				}
			});
			primaryStage.show();
		});
		LauncherView launcherView = new LauncherView();
		launcherView.getProgressBar().progressProperty().bind(launcherTask.progressProperty());
		Scene scene = new Scene(launcherView);
		scene.getStylesheets().add("/css/Material.css");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/consacre-beamer-icon.PNG")));
		primaryStage.setWidth(400);
		primaryStage.setHeight(200);
		primaryStage.setMaximized(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
