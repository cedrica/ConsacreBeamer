package com.consacresdeleternel.consacrebeamer.manager;

public class ManagerProviderImpl implements ManagerProvider {
	
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
	
	public  ManagerProviderImpl() {
		this.bookManager = new BookManager();
		this.dialogManager = new DialogManager();
		this.extrasMenuManager = new ExtrasMenuManager();
		this.fileMenuManager = new FileMenuManager();
		this.mainContainerManger = new MainContainerManger();
		this.presentationManager = new PresentationManager();
		this.scheduleManager = new ScheduleManager();
		this.songManager = new SongManager();
		this.valueObjectManager = new ValueObjectManager();
	}

	@Override
	public BookManager getBookManager() {
		return this.bookManager;
	}

	@Override
	public ExtrasMenuManager getExtrasMenuManager() {
		// TODO Auto-generated method stub
		return this.extrasMenuManager;
	}

	@Override
	public FileMenuManager getFileMenuManager() {
		// TODO Auto-generated method stub
		return this.fileMenuManager;
	}

	@Override
	public MainContainerManger getMainContainerManger() {
		// TODO Auto-generated method stub
		return this.mainContainerManger;
	}

	@Override
	public PresentationManager getPresentationManager() {
		// TODO Auto-generated method stub
		return this.presentationManager;
	}

	@Override
	public ScheduleManager getScheduleManager() {
		// TODO Auto-generated method stub
		return this.scheduleManager;
	}

	@Override
	public SongManager getSongManager() {
		// TODO Auto-generated method stub
		return this.songManager;
	}

	@Override
	public ValueObjectManager getValueObjectManager() {
		// TODO Auto-generated method stub
		return this.valueObjectManager;
	}

	@Override
	public DialogManager getDialogManager() {
		// TODO Auto-generated method stub
		return this.dialogManager;
	}

	@Override
	public TaskManager getTaskManager() {
		// TODO Auto-generated method stub
		return this.taskManager;
	}
}
