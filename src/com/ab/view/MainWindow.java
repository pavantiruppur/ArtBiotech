package com.ab.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ab.components.Components;

public class MainWindow {

	static MainWindow instance; 
	
	public synchronized static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	JFrame frame;
	JPanel mainPanel;
	
	JPanel header;
	JPanel body;
	JPanel footer;
	
	private MainWindow() {}
	
	public void loadFrame() {
		frame = Components.getFrame();
		
		mainPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(0,1);
		
		frame.setVisible(true);
	}
}
