package com.consacresdeleternel.consacrebeamer.tasks;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LauncherTask extends Task<Pair<MainContainerView, MainContainerManger>> {
	Stage primaryStage;
    private CdiContainer container;
    
	public LauncherTask(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	protected Pair<MainContainerView, MainContainerManger> call() throws Exception {
		initializeCdiContainer();
		MainContainerManger mainContainerManger = BeanProvider.getContextualReference(MainContainerManger.class, true);
		MainContainerView mainContainerView = BeanProvider.getContextualReference(MainContainerView.class, false);
		primaryStage.setOnCloseRequest(evt -> {
			container.shutdown();
		});
		return new Pair<MainContainerView, MainContainerManger>(mainContainerView, mainContainerManger);
	}
	
    private void initializeCdiContainer() {
    	container = CdiContainerLoader.getCdiContainer();
    	container.boot();

        ContextControl contextControl = container.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        contextControl.startContext(TransactionScoped.class);
    }

}
