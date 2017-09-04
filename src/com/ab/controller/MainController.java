package com.ab.controller;

import com.ab.annoation.Controller;

@Controller
public class MainController {

	private static MainController instance;
	
	public synchronized static MainController getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		
		return instance;
	}
	
	private MainController() {}
	
	public String startUpWindow() {
		return "loadMainWindow";
	}
}
