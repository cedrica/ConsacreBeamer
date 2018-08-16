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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConsacreBeamerApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		LauncherTask launcherTask = new LauncherTask(primaryStage);
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
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setMinWidth(600);
			primaryStage.setMinHeight(600);
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.err.println("Fehler ist aufgetreten");
				}
			});
			primaryStage.show();
		});
		LauncherView launcherView = new LauncherView();
		

        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(launcherView.getProgressBar().progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(launcherView.getProgressBar().progressProperty(), 1)
                )
        );

        // Set the max status
        int maxStatus = 12;
        // Create the Property that holds the current status count
        IntegerProperty statusCountProperty = new SimpleIntegerProperty(1);
        // Create the timeline that loops the statusCount till the maxStatus
        Timeline timelineBar = new Timeline(
                new KeyFrame(
                        // Set this value for the speed of the animation
                        Duration.millis(300),
                        new KeyValue(statusCountProperty, maxStatus)
                )
        );
        // The animation should be infinite
        timelineBar.setCycleCount(Timeline.INDEFINITE);
        timelineBar.play();
        // Add a listener to the statusproperty
        statusCountProperty.addListener((ov, statusOld, statusNewNumber) -> {
            int statusNew = statusNewNumber.intValue();
            // Remove old status pseudo from progress-bar
            launcherView.getProgressBar().pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + statusOld.intValue()), false);
            // Add current status pseudo from progress-bar
            launcherView.getProgressBar().pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + statusNew), true);
        });
        task.playFromStart();
//		launcherView.getProgressBar().progressProperty().bind(launcherTask.progressProperty());
		Scene scene = new Scene(launcherView);
		scene.getStylesheets().add("/css/Material.css");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/consacre-icon-sans-bg.png")));
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);
		primaryStage.setMaximized(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
