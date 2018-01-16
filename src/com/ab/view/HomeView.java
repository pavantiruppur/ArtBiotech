package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.ab.components.Chamber;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements MouseListener{

	static HomeView instance; 
	
	public synchronized static HomeView getInstance() {
		if (instance == null) {
			instance = new HomeView();
		}
		return instance;
	}
	
	
	private HomeView() {
		init();
	}
	
	public void init() {

		setPreferredSize(new Dimension(800, 320));
		setBackground(new Color(207,205,205));
		addMouseListener(this);
		
		JPanel chamber1 = new Chamber("A1", "C1", "resources/ms-tempbg-c1.png");
		add(chamber1);
		
		JPanel chamber2 = new Chamber("A1", "C2", "resources/ms-tempbg-c2.png");
		add(chamber2);
		
		JPanel chamber3 = new Chamber("A1", "C3", "resources/ms-tempbg-c3.png");
		add(chamber3);
		
		JPanel chamber4 = new Chamber("A1", "C4", "resources/ms-tempbg-c4.png");
		add(chamber4);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		for(Chamber chamber : Chamber.chambers) {
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
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseReleased(MouseEvent e) {}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent e) {}
}
