package com.consacresdeleternel.consacrebeamer.manager;

public class ManagerProvider {
	
	private BookManager bookManager;
	private DialogManager dialogManager;
	private ExtrasMenuManager extrasMenuManager;
	private FileMenuManager fileMenuManager;
	private MainContainerManger mainContainerManger;
	private PresentationManager presentationManager;
	private ScheduleManager scheduleManager;
	private SongManager songManager;
	private ValueObjectManager valueObjectManager;
	private TaskManager taskManager;
	
	public  ManagerProvider() {
		this.bookManager = new BookManager();
		this.dialogManager = new DialogManager();
		this.extrasMenuManager = new ExtrasMenuManager();
		this.fileMenuManager = new FileMenuManager();
		this.mainContainerManger = new MainContainerManger();
		this.presentationManager = new PresentationManager();
		this.scheduleManager = new ScheduleManager();
		this.songManager = new SongManager();
		this.valueObjectManager = new ValueObjectManager();
		this.taskManager = new TaskManager();
	}

	
	public BookManager getBookManager() {
		return this.bookManager;
	}

	
	public ExtrasMenuManager getExtrasMenuManager() {
		// TODO Auto-generated method stub
		return this.extrasMenuManager;
	}

	
	public FileMenuManager getFileMenuManager() {
		// TODO Auto-generated method stub
		return this.fileMenuManager;
	}

	
	public MainContainerManger getMainContainerManger() {
		// TODO Auto-generated method stub
		return this.mainContainerManger;
	}

	
	public PresentationManager getPresentationManager() {
		// TODO Auto-generated method stub
		return this.presentationManager;
	}

	
	public ScheduleManager getScheduleManager() {
		// TODO Auto-generated method stub
		return this.scheduleManager;
	}

	
	public SongManager getSongManager() {
		
		return this.songManager;
	}

	
	public ValueObjectManager getValueObjectManager() {
		// TODO Auto-generated method stub
		return this.valueObjectManager;
	}

	
	public DialogManager getDialogManager() {
		// TODO Auto-generated method stub
		return this.dialogManager;
	}

	
	public TaskManager getTaskManager() {
		// TODO Auto-generated method stub
		return this.taskManager;
	}
}
