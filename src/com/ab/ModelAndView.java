package com.ab;

public class ModelAndView {

	Object model;
	String viewName;
	
	public ModelAndView() {}
	
	public ModelAndView(String viewName, Object model) {
		this.model = model;
		this.viewName = viewName;
	}
	
	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
