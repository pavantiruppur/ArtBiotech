package com.ab.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.communication.SerialComm;
import com.ab.view.MainWindow;

@SuppressWarnings("serial")
public class LidCalibrationChamber extends JPanel {
	
	public static List<LidCalibrationChamber> chambers = new ArrayList<>();
	public static Map<String, LidCalibrationChamber> chamberMap = new HashMap<>();

	String chamberName;
	ImageIcon chamberIcon;
	JLabel pvValue;
	JLabel svValue;
	public Double pv = 33.7;
	Double sv = 0.0;
	Double oldSvValue = sv;
	String operation;
	
	public Integer flowRate = 50;
	
	Boolean isEditable = false;
	
	JLabel chamberBgLbl;
	JLabel downArrowLbl;
	JLabel upArrowLbl;

	public LidCalibrationChamber(String operation, String chamberName, String chamberIcon) {
		this.chamberName = chamberName;
		this.chamberIcon = new ImageIcon(getClass().getClassLoader().getResource(chamberIcon));
		chambers.add(this);
		chamberMap.put(chamberName, this);
		this.operation = operation;
		load();
	}

	public static LidCalibrationChamber getChamberByName(String chamberName) {
		return chamberMap.get(chamberName);
	}
	
	private void load() {

		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setBackground(MainWindow.getInstance().body.getBackground());
		setPreferredSize(new Dimension(chamberIcon.getIconWidth() + (chamberIcon.getIconWidth() / 4), chamberIcon.getIconHeight() + 200));

		Icon upArrow = new ImageIcon(getClass().getClassLoader().getResource("resources/ms-adjust-up.png"));
		upArrowLbl = new JLabel();
		upArrowLbl.setHorizontalAlignment(JLabel.CENTER);
		upArrowLbl.setVerticalAlignment(JLabel.CENTER);
		upArrowLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 50));
		upArrowLbl.setIcon(upArrow);
		upArrowLbl.addMouseListener(new MouseListener() {
			
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
				if(!isEditable) {
					return;
				}
				sv += 0.1;
				chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , sv) +"\u00b0c</font></html>");
				
			}
		});
		upArrowLbl.setEnabled(isEditable);
		add(upArrowLbl);

		chamberBgLbl = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(chamberIcon.getImage(), 0, 0, null);
				super.paintComponent(g);
			}
		};
		chamberBgLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), chamberIcon.getIconHeight()));
		chamberBgLbl.setOpaque(false);
		chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ pv +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , sv) +"\u00b0c</font></html>");
		chamberBgLbl.setFont(Components.getDPHeavyFont(33f));
		chamberBgLbl.addMouseListener(new MouseListener() {
			
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
				for(LidCalibrationChamber chamber : LidCalibrationChamber.chambers) {
					if(!chamber.chamberName.equals(chamberName) && chamber.isEditable) {
						chamber.sv = chamber.oldSvValue;
						chamber.isEditable = !chamber.isEditable;
						chamber.upArrowLbl.setEnabled(chamber.isEditable);
						chamber.downArrowLbl.setEnabled(chamber.isEditable);
						chamber.chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , chamber.pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , chamber.sv) +"\u00b0c</font></html>");
					}
				}
				isEditable = !isEditable;
				if(!isEditable) {
					oldSvValue = sv;
					pv += sv;
					sv = 0.0;
					SerialComm.sendChamberData(operation, chamberName, oldSvValue);
					chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , sv) +"\u00b0c</font></html>");
				}
				upArrowLbl.setEnabled(isEditable);
				downArrowLbl.setEnabled(isEditable);
			}
		});
		add(chamberBgLbl);
		
		Icon downArrow = new ImageIcon(getClass().getClassLoader().getResource("resources/ms-adjust-down.png"));
		downArrowLbl = new JLabel();
		downArrowLbl.setHorizontalAlignment(JLabel.CENTER);
		downArrowLbl.setVerticalAlignment(JLabel.CENTER);
		downArrowLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 60));
		downArrowLbl.setIcon(downArrow);
		downArrowLbl.addMouseListener(new MouseListener() {
			
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
				if(!isEditable) {
					return;
				}
				sv -= 0.1;
				chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , sv) +"\u00b0c</font></html>");
				
			}
		});
		downArrowLbl.setEnabled(isEditable);
		add(downArrowLbl);
	}
	
	public void refreshPV() {
		chamberBgLbl.setText("<html><div style='font-size: 15px'><br></div><font color=#373636>&nbsp;"+ String.format("%4.1f" , pv) +"\u00b0c</font><br><br><font color=#848282>&nbsp;"+ String.format("%4.1f" , sv) +"\u00b0c</font></html>");
	}
	
	public static void refreshAllPV() {
		for(LidCalibrationChamber ch : chambers) {
			if(LidTempChamber.getChamberByName(ch.chamberName) == null){
				continue;
			}
			ch.pv = LidTempChamber.getChamberByName(ch.chamberName).pv;
			ch.refreshPV();
		}
	}
}
