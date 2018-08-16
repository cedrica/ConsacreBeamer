package com.consacresdeleternel.consacrebeamer;

import java.lang.Thread.UncaughtExceptionHandler;

import com.consacresdeleternel.consacrebeamer.events.FileMenuEvent;
import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.maincontainer.launcher.LauncherView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;
import com.consacresdeleternel.consacrebeamer.tasks.LauncherTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ConsacreBeamerApp extends Application {
	private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();
	private static double[] pref_WH, offset_XY;

	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		LauncherTask launcherTask = new LauncherTask(stage);
		new Thread(launcherTask).start();
		launcherTask.valueProperty().addListener((obs, oldVal, newVal) -> {
			MainContainerView mainContainerView = newVal.getKey();
			MainContainerManger mainContainerManger = newVal.getValue();
			mainContainerView.addEventHandler(FileMenuEvent.EXIT_APPLICATION, evt -> {
				Platform.exit();
			});
			mainContainerManger.init(mainContainerView);
			Scene scene = new Scene(mainContainerView);
			scene.getStylesheets().add("/css/Material.css");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/consacre-icon-sans-bg.png")));
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setMinWidth(1000);
			primaryStage.setMinHeight(800);
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.err.println("Fehler ist aufgetreten");
				}
			});
			stage.close();
			primaryStage.show();
		});
		LauncherView launcherView = new LauncherView();
		launcherView.getWebView().setOnMousePressed((MouseEvent p) -> {
			offset_XY = new double[] { p.getSceneX(), p.getSceneY() };
		});

		launcherView.getWebView().setOnMouseDragged((MouseEvent d) -> {
			// Ensures the stage is not dragged past the taskbar
			if (d.getScreenY() < (SCREEN_BOUNDS.getMaxY() - 20))
				stage.setY(d.getScreenY() - offset_XY[1]);
			stage.setX(d.getScreenX() - offset_XY[0]);
		});

		launcherView.getWebView().setOnMouseReleased((MouseEvent r) -> {
			// Ensures the stage is not dragged past top of screen
			if (stage.getY() < 0.0)
				stage.setY(0.0);
		});

	    Timeline task = new Timeline(
	            new KeyFrame(
	                    Duration.ZERO,       
	                    new KeyValue(launcherView.getProgressBar().progressProperty(), 0)
	            ),
	            new KeyFrame(
	                    Duration.seconds(15), 
	                    new KeyValue(launcherView.getProgressBar().progressProperty(), 1)
	            )
	        );
		task.playFromStart();
		Scene scene = new Scene(launcherView);
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(600);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
