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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.ab.communication.SerialComm;
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
	
	public JFrame frame;
	public JPanel mainPanel;
	
	public HomeView home;
	
	public JPanel header;
	public JPanel body;
	public JPanel footer;
	public JLabel gasEmptyAlarmLbl;
	
	public Boolean isGasOn = false;
	public Double co2Value = 6.0;
	public Double o2Value = 5.5;
	
	JLabel o2ValueLbl = null;
	JLabel co2ValueLbl = null;
	
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0 ,0);
	
	private MainWindow() {}
	
	public void loadFrame() {
		frame = Components.getFrame();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(flowLayout);
		

		body = new JPanel();
		body.setPreferredSize(new Dimension(800, 320));
		body.setLayout(flowLayout);
		body.setBackground(new Color(207,205,205));
		
		loadHeader();
		
		loadBody();
		
		loadFooter();
		
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
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/tb-logo.png")); 
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
		Font font = Components.getDPMediumFont(24f);
		timeLabel.setFont(font);
		timeLabel.setForeground(new Color(55, 54, 54));
		final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                String date = dateFormat.format(now);
                String time = timeFormat.format(now);
                timeLabel.setText("<html><body style='text-align: right'>" + date + "<br>" + time + "</html>");
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
		rightSide.add(timeLabel);
		
		// Adding Graph icon as label
		MenuButton graphMenu = new MenuButton("resources/tb-graph-o.png",
				"resources/tb-graph-n.png", false, new JPanel());
		MenuButton settingsMenu = new MenuButton("resources/tb-setting-o.png",
				"resources/tb-setting-n.png", false, SettingView.getInstance());
		MenuButton homeMenu = new MenuButton("resources/tb-home-o.png",
				"resources/tb-home-n.png", true, HomeView.getInstance());
		rightSide.add(graphMenu);
		rightSide.add(settingsMenu);
		rightSide.add(homeMenu);
		
		
		header.add(rightSide);
		
		mainPanel.add(header);
		
	}
	
	private void loadBody() {
		
		body.add(HomeView.getInstance());
		
		mainPanel.add(body);
	}
	
	private void loadFooter() {
		
		footer = new JPanel();
		footer.setLayout(flowLayout);
		footer.setPreferredSize(new Dimension(800, 80));
		footer.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(142,141,141)));
		footer.setBackground(new Color(178,176,176));
		
		// Co2 Panel
		JPanel co2Panel = new JPanel();
		co2Panel.setBackground(footer.getBackground());
		co2Panel.setPreferredSize(new Dimension(180, 80));
		FlowLayout flowLayoutCenter = new FlowLayout(FlowLayout.CENTER, 0 ,0);
		co2Panel.setLayout(flowLayoutCenter);

		ImageIcon co2Icon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-co2-label.png")); 
		JLabel co2ImgLbl = new JLabel();
		co2ImgLbl.setIcon(co2Icon);
		co2Panel.add(co2ImgLbl);

		co2ValueLbl = new JLabel(String.format("%4.1f" , co2Value));
		co2ValueLbl.setFont(Components.getDPHeavyFont(30f));
		co2Panel.add(co2ValueLbl);
		
		ImageIcon co2PercIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-percent-label.png")); 
		JLabel co2PercImgLbl = new JLabel();
		co2PercImgLbl.setIcon(co2PercIcon);
		co2Panel.add(co2PercImgLbl);
		
		footer.add(co2Panel);

		// O2 Panel
		JPanel o2Panel = new JPanel();
		o2Panel.setBackground(footer.getBackground());
		o2Panel.setPreferredSize(new Dimension(180, 80));
		o2Panel.setLayout(flowLayoutCenter);

		ImageIcon o2Icon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-o2-label.png")); 
		JLabel o2ImgLbl = new JLabel();
		o2ImgLbl.setIcon(o2Icon);
		o2Panel.add(o2ImgLbl);

		o2ValueLbl = new JLabel(String.format("%4.1f" , o2Value));
		o2ValueLbl.setFont(Components.getDPHeavyFont(30f));
		o2Panel.add(o2ValueLbl);
		
		ImageIcon o2PercIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-percent-label.png")); 
		JLabel o2PercImgLbl = new JLabel();
		o2PercImgLbl.setIcon(o2PercIcon);
		o2Panel.add(o2PercImgLbl);
		
		footer.add(o2Panel);
		
		JPanel gasPanel = new JPanel();
		gasPanel.setLayout(flowLayoutCenter);
		gasPanel.setBackground(footer.getBackground());
		gasPanel.setPreferredSize(new Dimension(220, 80));
		JLabel gasLbl = new JLabel();
		ImageIcon gasLblIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-gas-label.png")); 
		gasLbl.setIcon(gasLblIcon);
		gasLbl.setPreferredSize(new Dimension(80, 80));
		gasPanel.add(gasLbl);
		
		JLabel gasOnOffLbl = new JLabel();
		ImageIcon gasOnIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-gas-on.png")); 
		ImageIcon gasOffIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-gas-off.png")); 
		gasOnOffLbl.setIcon(gasOnIcon);
		gasOnOffLbl.setDisabledIcon(gasOffIcon);
		gasOnOffLbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				isGasOn = !isGasOn;
				gasOnOffLbl.setEnabled(isGasOn);
				SerialComm.sendGasControl(isGasOn);
			}
		});
		gasOnOffLbl.setEnabled(false);
		gasPanel.add(gasOnOffLbl);
		footer.add(gasPanel);
		
		// Gas Empty alarm
		gasEmptyAlarmLbl = new JLabel("Gas Empty Alarm");
		gasEmptyAlarmLbl.setForeground(new Color(249,  6, 6));
		ImageIcon gasEmptyAlarmIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/bb-alarm-icon.png")); 
		gasEmptyAlarmLbl.setIcon(gasEmptyAlarmIcon);
		gasEmptyAlarmLbl.setFont(Components.getDPMediumFont(20f));
		gasEmptyAlarmLbl.setVisible(false);
		footer.add(gasEmptyAlarmLbl);
		
	}
	
	public void refreshCo2() {
		co2ValueLbl.setText(String.format("%4.1f" , co2Value)); 
	}
	
	public void refreshO2() {
		o2ValueLbl.setText(String.format("%4.1f" , o2Value)); 
	}
}
