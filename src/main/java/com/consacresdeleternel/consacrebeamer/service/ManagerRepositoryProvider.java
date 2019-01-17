package com.consacresdeleternel.consacrebeamer.service;

import com.consacresdeleternel.consacrebeamer.manager.ManagerProvider;
import com.consacresdeleternel.consacrebeamer.manager.ManagerProviderImpl;
import com.consacresdeleternel.consacrebeamer.repository.RepositoryProvider;

public class ManagerRepositoryProvider {

	private  ManagerProvider managerProvider;
	private  RepositoryProvider repositoryProvider;
	
	public ManagerRepositoryProvider() {
		managerProvider = (ManagerProvider)new ManagerProviderImpl();
		System.out.println("je suis passer");
	}
	public  ManagerProvider getManagerProvider() {
		return managerProvider;
	}
	public  RepositoryProvider getRepositoryProvider() {
		return repositoryProvider;
	}

}
