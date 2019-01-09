package com.consacresdeleternel.consacrebeamer.tasks;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;
import com.consacresdeleternel.consacrebeamer.manager.MainContainerManger;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LauncherTask extends Task<Pair<MainContainerView, MainContainerManger>> {
	Stage primaryStage;
    private WeldContainer container;
    
	public LauncherTask(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	protected Pair<MainContainerView, MainContainerManger> call() throws Exception {
//		initializeCdiContainer();
		Weld weld = new Weld();
        container = weld.initialize();
        MainContainerManger mainContainerManger =
                container.instance().select(MainContainerManger.class).get();
        MainContainerView mainContainerView =
                container.instance().select(MainContainerView.class).get();
        
//		MainContainerManger mainContainerManger = BeanProvider.getContextualReference(MainContainerManger.class, true);
//		MainContainerView mainContainerView = BeanProvider.getContextualReference(MainContainerView.class, false);
		primaryStage.setOnCloseRequest(evt -> {
	        weld.shutdown();
		});
		return new Pair<MainContainerView, MainContainerManger>(mainContainerView, mainContainerManger);
	}
	
//    private void initializeCdiContainer() {
//    	container = CdiContainerLoader.getCdiContainer();
//    	container.boot();
//
//        ContextControl contextControl = container.getContextControl();
//        contextControl.startContext(ApplicationScoped.class);
//        contextControl.startContext(TransactionScoped.class);
//    }

}
