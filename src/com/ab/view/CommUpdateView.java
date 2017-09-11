package com.ab.view;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ab.Handler;
import com.ab.ModelAndView;
import com.ab.annoation.View;
import com.ab.annoation.ViewAction;
import com.ab.components.Chamber;

@View(name = "CommUpdateView")
public class CommUpdateView {

	private static CommUpdateView instance;
	
	public synchronized static CommUpdateView getInstance() {
		if(instance == null) {
			instance = new CommUpdateView();
		}
		
		return instance;
	}
	
	private CommUpdateView() {}
	
	@SuppressWarnings({ "unchecked" })
	@ViewAction(action = "updateData")
	public void updateData(ModelAndView modelAndView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Map<String, List<Byte>> dataSet = (Map<String, List<Byte>>) modelAndView.getModel();
		for(String key : dataSet.keySet()) {
			String dataStr = "";
			for(Byte b : dataSet.get(key)) {
				dataStr += Character.toString ((char) ((int)b));;
			}
			ModelAndView data = new ModelAndView(key, dataStr);
			Handler.handleView("CommUpdateView." + key, data);
		}
	}
	
	@ViewAction(action = "c1")
	public void loadC1(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C1");
		try{
			c1.pv = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshPV();
	}
	
	@ViewAction(action = "c2")
	public void loadC2(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C2");
		try{
			c1.pv = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshPV();
	}
	
	@ViewAction(action = "c3")
	public void loadC3(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C3");
		try{
			c1.pv = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshPV();
	}
	
	@ViewAction(action = "c4")
	public void loadC4(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C4");
		try{
			c1.pv = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshPV();
	}
	
	// Co2
	@ViewAction(action = "b1")
	public void loadB1(ModelAndView modelAndView) {
		MainWindow mainWindow = MainWindow.getInstance();
		try{
			mainWindow.co2Value = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mainWindow.refreshCo2();
	}
	
	//O2
	@ViewAction(action = "b2")
	public void loadB2(ModelAndView modelAndView) {
		MainWindow mainWindow = MainWindow.getInstance();
		try{
			mainWindow.o2Value = Double.parseDouble((String)((ModelAndView)modelAndView.getModel()).getModel());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mainWindow.refreshO2();
	}
	
	@ViewAction(action = "b3")
	public void loadB3(ModelAndView modelAndView) {
		String hasError = (String)((ModelAndView)modelAndView.getModel()).getModel();
		MainWindow mainWindow = MainWindow.getInstance();
		mainWindow.gasEmptyAlarmLbl.setVisible(hasError.indexOf(2) == '1' ? true : false);
	}
	
	@ViewAction(action = "d1")
	public void loadD1(ModelAndView modelAndView) {
		System.out.println("**************");
		}
	
	@ViewAction(action = "d2")
	public void loadD2(ModelAndView modelAndView) {
		System.out.println("**************");
		}
	
	@ViewAction(action = "d3")
	public void loadD3(ModelAndView modelAndView) {
		System.out.println("**************");
		}
	
	@ViewAction(action = "d4")
	public void loadD4(ModelAndView modelAndView) {
		System.out.println("**************");
		}
	
	@ViewAction(action = "f1")
	public void loadF1(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C1");
		try{
			String flRate = (String)((ModelAndView)modelAndView.getModel()).getModel();
			c1.flowRate = Integer.parseInt(flRate.split("\\.")[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshflowRate();
	}
	
	@ViewAction(action = "f2")
	public void loadF2(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C2");
		try{
			String flRate = (String)((ModelAndView)modelAndView.getModel()).getModel();
			c1.flowRate = Integer.parseInt(flRate.split("\\.")[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshflowRate();
	}
	
	@ViewAction(action = "f3")
	public void loadF3(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C3");
		try{
			String flRate = (String)((ModelAndView)modelAndView.getModel()).getModel();
			c1.flowRate = Integer.parseInt(flRate.split("\\.")[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshflowRate();
	}
	
	@ViewAction(action = "f4")
	public void loadF4(ModelAndView modelAndView) {
		Chamber c1 = Chamber.getChamberByName("C4");
		try{
			String flRate = (String)((ModelAndView)modelAndView.getModel()).getModel();
			c1.flowRate = Integer.parseInt(flRate.split("\\.")[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		c1.refreshflowRate();
	}
	
}
