package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.ab.components.Chamber;

@SuppressWarnings("serial")
public class HomeView extends JPanel{

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
		
		JPanel chamber1 = new Chamber("A1", "C1", "resources/ms-tempbg-c1.png");
		add(chamber1);
		
		JPanel chamber2 = new Chamber("A1", "C2", "resources/ms-tempbg-c2.png");
		add(chamber2);
		
		JPanel chamber3 = new Chamber("A1", "C3", "resources/ms-tempbg-c3.png");
		add(chamber3);
		
		JPanel chamber4 = new Chamber("A1", "C4", "resources/ms-tempbg-c4.png");
		add(chamber4);
	}
}
