package com.ab.view;

import com.ab.ModelAndView;
import com.ab.annoation.View;
import com.ab.annoation.ViewAction;

@View(name = "Body")
public class BodyView {

	private static BodyView instance;
	
	public synchronized static BodyView getInstance() {
		if(instance == null) {
			instance = new BodyView();
		}
		
		return instance;
	}
	
	private BodyView() {}
	
	@ViewAction(action = "loadBody")
	public void loadBody(ModelAndView modelAndView) {
		
	}
}
