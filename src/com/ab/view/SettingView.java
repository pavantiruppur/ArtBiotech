package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.ab.components.LeftMenuButton;

@SuppressWarnings("serial")
public class SettingView extends JPanel {

	static SettingView instance; 
	public JPanel body = null;
	
	public synchronized static SettingView getInstance() {
		if (instance == null) {
			instance = new SettingView();
		}
		return instance;
	}
	
	private SettingView() {
		init();
	}
	
	public void init() {

		body = new JPanel();
		body.setPreferredSize(new Dimension(700, 320));
		body.setBackground(new Color(207,205,205));
		body.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		setPreferredSize(new Dimension(800, 320));
		setBackground(new Color(207,205,205));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel leftMenu = new JPanel();
		leftMenu.setPreferredSize(new Dimension(100, 320));
		leftMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 12 ,12));
		leftMenu.setBackground(new Color(207,205,205));
		
		JPanel extraSpace = new JPanel();
		extraSpace.setPreferredSize(new Dimension(100, 10));
		extraSpace.setBackground(new Color(207,205,205));
		leftMenu.add(extraSpace);
		
		LeftMenuButton calibMenuBt = new LeftMenuButton("resources/temp-calibration-a.png", 
				"resources/temp-calibration-n.png", 
				true, CalibratoinSettingView.getInstance());
		leftMenu.add(calibMenuBt);
		
		LeftMenuButton adjustTempMenuBt = new LeftMenuButton("resources/lid-temp-set-a.png", 
				"resources/lid-temp-set-n.png", 
				false, LidSetTempSettingView.getInstance());
		leftMenu.add(adjustTempMenuBt);
		
		LeftMenuButton lidTempMenuBt = new LeftMenuButton("resources/lid-temp-calib-a.png", 
				"resources/lid-temp-calib-n.png", 
				false, LidCalibrationSettingView.getInstance());
		leftMenu.add(lidTempMenuBt);
		
		LeftMenuButton gasMenuBt = new LeftMenuButton("resources/adjust-gas-a.png", 
				"resources/adjust-gas-n.png", 
				false, new JPanel());
		leftMenu.add(gasMenuBt);
		
		LeftMenuButton runDiagMenuBt = new LeftMenuButton("resources/run-diagnostics-a.png", 
				"resources/run-diagnostics-n.png", 
				false, new JPanel());
		leftMenu.add(runDiagMenuBt);
		
		add(leftMenu);

		body.add(CalibratoinSettingView.getInstance());
		add(body);
	}
}
