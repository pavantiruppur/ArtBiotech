package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.components.CalibrationChamber;
import com.ab.components.Chamber;
import com.ab.components.Components;
import com.ab.components.LidCalibrationChamber;
import com.ab.components.LidTempChamber;

@SuppressWarnings("serial")
public class LidCalibrationSettingView extends JPanel implements MouseListener {

	static LidCalibrationSettingView instance; 
	
	public synchronized static LidCalibrationSettingView getInstance() {
		if (instance == null) {
			instance = new LidCalibrationSettingView();
		}
		return instance;
	}
	
	private LidCalibrationSettingView() {
		init();
	}
	
	public void init() {

		setPreferredSize(new Dimension(700, 320));
		setBackground(new Color(207,205,205));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		addMouseListener(this);
		
		JPanel settingHeadPanel = new JPanel();
		settingHeadPanel.setBackground(new Color(207,205,205));
		settingHeadPanel.setPreferredSize(new Dimension(650, 50));
		JLabel setTitleLbl = new JLabel("Lid Temperature Calibration"); 
		setTitleLbl.setPreferredSize(new Dimension(650, 40));
		setTitleLbl.setHorizontalAlignment(JLabel.RIGHT);
		setTitleLbl.setFont(Components.getDPMediumFont(24f));
		settingHeadPanel.add(setTitleLbl);
		add(settingHeadPanel);
		
		JPanel chamberPanel = new JPanel();
		chamberPanel.setPreferredSize(new Dimension(700, 280));
		chamberPanel.setBackground(new Color(207,205,205));
		
		JPanel chamber1 = new LidCalibrationChamber("A4", "D1", "resources/ms-tempbg-c1.png");
		chamberPanel.add(chamber1);
		
		JPanel chamber2 = new LidCalibrationChamber("A4", "D2", "resources/ms-tempbg-c2.png");
		chamberPanel.add(chamber2);
		
		JPanel chamber3 = new LidCalibrationChamber("A4", "D3", "resources/ms-tempbg-c3.png");
		chamberPanel.add(chamber3);
		
		JPanel chamber4 = new LidCalibrationChamber("A4", "D4", "resources/ms-tempbg-c4.png");
		chamberPanel.add(chamber4);
		
		add(chamberPanel);
	}
	
	@Override
	public void repaint() {
		LidCalibrationChamber.refreshAllPV();
		super.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(LidCalibrationChamber chamber : LidCalibrationChamber.chambers) {
			if(chamber.isEditable) {
				chamber.sv = chamber.oldSvValue;
				chamber.isEditable = !chamber.isEditable;
				chamber.upArrowLbl.setEnabled(chamber.isEditable);
				chamber.downArrowLbl.setEnabled(chamber.isEditable);
				chamber.chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , chamber.pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , chamber.sv) +"\u00b0c</font></html>");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
