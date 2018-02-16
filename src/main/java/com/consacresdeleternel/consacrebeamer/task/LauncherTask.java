package com.consacresdeleternel.consacrebeamer.task;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LauncherTask extends Task<Pair<MainContainerView, MainContainerManger>> {
	Stage primaryStage;

	public LauncherTask(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	protected Pair<MainContainerView, MainContainerManger> call() throws Exception {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		MainContainerManger mainContainerManger = container.instance().select(MainContainerManger.class).get();
		MainContainerView mainContainerView = container.instance().select(MainContainerView.class).get();
		primaryStage.setOnCloseRequest(evt -> {
			weld.shutdown();
		});
		return new Pair<MainContainerView, MainContainerManger>(mainContainerView, mainContainerManger);
	}

}
