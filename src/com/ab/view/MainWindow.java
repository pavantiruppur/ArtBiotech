package com.ab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.ab.components.Components;
import com.ab.components.MenuButton;

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
	
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0 ,0);
	
	private MainWindow() {}
	
	public void loadFrame() {
		frame = Components.getFrame();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(flowLayout);
		
		loadHeader();
		
		body = new JPanel();
		body.setPreferredSize(new Dimension(800, 320));
		body.setBackground(new Color(207,205,205));
		
		mainPanel.add(body);
		
		footer = new JPanel();
		footer.setPreferredSize(new Dimension(800, 80));
		footer.setBackground(new Color(178,176,176));
		
		mainPanel.add(footer);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	public void loadHeader() {
		
		header = new JPanel();
		header.setLayout(flowLayout);
		header.setPreferredSize(new Dimension(800, 80));
		header.setBackground(new Color(216,216,216));
		
		// Adding Logo
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(flowLayout);
		ImageIcon icon = new ImageIcon("E:/My_Workspace/ArtBiotech/resources/tb-logo.png"); 
		JLabel logo = new JLabel();
		logo.setIcon(icon);
		logoPanel.add(logo);
		header.add(logoPanel);
		
		// Adding extra space
		JPanel extraSpacePanel = new JPanel();
		extraSpacePanel.setPreferredSize(new Dimension(100, 80));
		header.add(extraSpacePanel);
		extraSpacePanel.setBackground(extraSpacePanel.getParent().getBackground());
		
		// Adding right side of header (Time, Graph, Settings and Home buttons)
		JPanel rightSide = new JPanel();
		rightSide.setBackground(header.getBackground());
		rightSide.setLayout(flowLayout);
		
		JLabel timeLabel = new JLabel();
		timeLabel.setPreferredSize(new Dimension(158, 80));
		timeLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date now = new Date();
                String date = dateFormat.format(now);
                String time = timeFormat.format(now);
                timeLabel.setText("<html>" + date + "<br>" + time + "</html>");
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
		rightSide.add(timeLabel);
		
		// Adding Graph icon as label
		MenuButton graphMenu = new MenuButton("E:/My_Workspace/ArtBiotech/resources/tb-graph-o.png",
				"E:/My_Workspace/ArtBiotech/resources/tb-graph-n.png");
		MenuButton settingsMenu = new MenuButton("E:/My_Workspace/ArtBiotech/resources/tb-setting-o.png",
				"E:/My_Workspace/ArtBiotech/resources/tb-setting-n.png");
		MenuButton homeMenu = new MenuButton("E:/My_Workspace/ArtBiotech/resources/tb-home-o.png",
				"E:/My_Workspace/ArtBiotech/resources/tb-home-n.png");
		rightSide.add(graphMenu);
		rightSide.add(settingsMenu);
		rightSide.add(homeMenu);
		
		
		header.add(rightSide);
		
		
		
		
		
		mainPanel.add(header);
		
	}
}
