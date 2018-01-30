package com.consacresdeleternel.consacrebeamer;

import java.io.IOException;

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

	private Process startDerby;

	@Override
	public void start(Stage primaryStage) {

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		MainContainerManger mainContainerManger = container.instance().select(MainContainerManger.class).get();
		MainContainerView mainContainerView = container.instance().select(MainContainerView.class).get();
		mainContainerView.addEventHandler(FileMenuEvent.EXIT_APPLICATION, evt -> {
			startDerby.destroy();
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
		
//		try {
//			startDerby = Runtime.getRuntime().exec("cmd /C start "+System.getProperty("user.dir")+"/db-derby-10.13.1.1-bin/bin/startNetworkServer.bat"); //+"C:/Users/ca.leumaleu/Desktop/db-derby-10.13.1.1-bin/bin/startNetworkServer.bat");
//		} catch (IOException e) {
//			e.printStackTrace();
//			startDerby.destroy();
//		}
		primaryStage.setOnCloseRequest(evt -> {
			weld.shutdown();
//			startDerby.destroy();
//			try {
//				Runtime.getRuntime().exec("cmd /C start "+System.getProperty("user.dir")+"/db-derby-10.13.1.1-bin/bin/stopNetworkServer.bat");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
