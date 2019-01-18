package com.consacresdeleternel.consacrebeamer.tasks;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LauncherTask extends Task<Pair<MainContainerView, MainContainerManger>> {
	Stage primaryStage;

	@Override
	protected Pair<MainContainerView, MainContainerManger> call() throws Exception {
		Thread.sleep(6000);
		return new Pair<MainContainerView, MainContainerManger>(new MainContainerView(), new MainContainerManger());
	}
	

}
