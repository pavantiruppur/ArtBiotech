package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.components.CalibrationChamber;
import com.ab.components.Chamber;
import com.ab.components.Components;

@SuppressWarnings("serial")
public class CalibratoinSettingView extends JPanel {

	static CalibratoinSettingView instance; 
	
	public synchronized static CalibratoinSettingView getInstance() {
		if (instance == null) {
			instance = new CalibratoinSettingView();
		}
		return instance;
	}
	
	private CalibratoinSettingView() {
		init();
	}
	
	public void init() {

		setPreferredSize(new Dimension(700, 320));
		setBackground(new Color(207,205,205));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel settingHeadPanel = new JPanel();
		settingHeadPanel.setBackground(new Color(207,205,205));
		settingHeadPanel.setPreferredSize(new Dimension(650, 50));
		JLabel setTitleLbl = new JLabel("Temperature Calibration"); 
		setTitleLbl.setPreferredSize(new Dimension(650, 40));
		setTitleLbl.setHorizontalAlignment(JLabel.RIGHT);
		setTitleLbl.setFont(Components.getDPMediumFont(24f));
		settingHeadPanel.add(setTitleLbl);
		add(settingHeadPanel);
		
		JPanel chamberPanel = new JPanel();
		chamberPanel.setPreferredSize(new Dimension(700, 280));
		chamberPanel.setBackground(new Color(207,205,205));
		
		JPanel chamber1 = new CalibrationChamber("A2", "C1", "resources/ms-tempbg-c1.png");
		chamberPanel.add(chamber1);
		
		JPanel chamber2 = new CalibrationChamber("A2", "C2", "resources/ms-tempbg-c2.png");
		chamberPanel.add(chamber2);
		
		JPanel chamber3 = new CalibrationChamber("A2", "C3", "resources/ms-tempbg-c3.png");
		chamberPanel.add(chamber3);
		
		JPanel chamber4 = new CalibrationChamber("A2", "C4", "resources/ms-tempbg-c4.png");
		chamberPanel.add(chamber4);
		
		add(chamberPanel);
	}
	
	@Override
	public void repaint() {
		CalibrationChamber.refreshAllPV();
		super.repaint();
	}
}
