package com.ab.view;

import com.ab.ModelAndView;
import com.ab.annoation.View;
import com.ab.annoation.ViewAction;

@View(name = "Main")
public class MainView {

	private static MainView instance;
	
	public synchronized static MainView getInstance() {
		if(instance == null) {
			instance = new MainView();
		}
		
		return instance;
	}
	
	private MainView() {}
	
	@ViewAction(action = "loadMainWindow")
	public void loadMainWindow(ModelAndView modelAndView) {
		MainWindow mainWindow = MainWindow.getInstance();
		mainWindow.loadFrame();
	}
}
