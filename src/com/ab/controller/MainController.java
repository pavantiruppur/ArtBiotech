package com.ab.controller;

import com.ab.ModelAndView;
import com.ab.annoation.Controller;
import com.ab.annoation.ControllerAction;

@Controller(name = "main")
public class MainController {

	private static MainController instance;
	
	public synchronized static MainController getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		
		return instance;
	}
	
	private MainController() {}
	
	@ControllerAction(action = "startUp")
	public ModelAndView startUpWindow() {
		ModelAndView modelAndView = new ModelAndView("loadMainWindow", null);
		return modelAndView;
	}
}
