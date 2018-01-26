package com.consacresdeleternel.consacrebeamer.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.consacresdeleternel.consacrebeamer.maincontainer.MainContainerView;

@Singleton
public class MainContainerManger {
	private static final Logger LOG = Logger.getLogger(MainContainerManger.class);

	@Inject
	private FileMenuManager fileMenuManager;
	public void init(MainContainerView mainContainerView) {
		fileMenuManager.init(mainContainerView);
	}
}
