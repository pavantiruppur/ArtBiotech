package com.ab.controller;

import com.ab.ModelAndView;
import com.ab.annoation.Controller;
import com.ab.annoation.ControllerAction;
import com.ab.communication.SerialComm;

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
	
	@ControllerAction(action = "startCommPort")
	public ModelAndView startCommPort() {
		
		try {
			(new SerialComm()).connect("/dev/ttyACM0");//"/dev/ttyACM0"//"COM8"
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
