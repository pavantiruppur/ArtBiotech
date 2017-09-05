package com.ab.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

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
		
		
		
		frame.setVisible(true);
	}
}
