package com.consacresdeleternel.consacrebeamer.manager;

public interface ManagerProvider {
	
	public BookManager getBookManager();
	public DialogManager getDialogManager();
	public ExtrasMenuManager getExtrasMenuManager();
	public FileMenuManager getFileMenuManager();
	public MainContainerManger getMainContainerManger();
	public PresentationManager getPresentationManager();
	public ScheduleManager getScheduleManager();
	public SongManager getSongManager();
	public ValueObjectManager getValueObjectManager();
	public TaskManager getTaskManager();
}
