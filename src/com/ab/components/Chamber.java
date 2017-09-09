package com.ab.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.view.MainWindow;

@SuppressWarnings("serial")
public class Chamber extends JPanel {
	
	public static List<Chamber> chambers = new ArrayList<>();

	String chamberName;
	ImageIcon chamberIcon;
	JLabel pvValue;
	JLabel svValue;
	Double pv = 34.7;
	Double sv = 34.7;
	Double oldSvValue = sv;
	
	Integer flowRate = 50;
	
	Boolean isEditable = false;
	
	JLabel chamberBgLbl;
	JLabel downArrowLbl;
	JLabel upArrowLbl;
	JLabel flowRateLbl;

	public Chamber(String chamberName, String chamberIcon) {
		this.chamberName = chamberName;
		this.chamberIcon = new ImageIcon(getClass().getClassLoader().getResource(chamberIcon));
		Chamber.chambers.add(this);
		load();
	}

	private void load() {

		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setBackground(MainWindow.getInstance().body.getBackground());
		setPreferredSize(new Dimension(chamberIcon.getIconWidth() + (chamberIcon.getIconWidth() / 2), chamberIcon.getIconHeight() + 200));

		Icon upArrow = new ImageIcon(getClass().getClassLoader().getResource("resources/ms-adjust-up.png"));
		upArrowLbl = new JLabel();
		upArrowLbl.setHorizontalAlignment(JLabel.CENTER);
		upArrowLbl.setVerticalAlignment(JLabel.CENTER);
		upArrowLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 80));
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
				for(Chamber chamber : Chamber.chambers) {
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
				}
				upArrowLbl.setEnabled(isEditable);
				downArrowLbl.setEnabled(isEditable);
			}
		});
		add(chamberBgLbl);
		
		flowRateLbl = new JLabel();
		flowRateLbl.setHorizontalAlignment(JLabel.CENTER);
		flowRateLbl.setVerticalAlignment(JLabel.CENTER);
		flowRateLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 33));
		flowRateLbl.setFont(Components.getDPHeavyFont(20f));
		flowRateLbl.setText("<html><font color=#373636>"+flowRate + "</font>" + " <font color=#a2a1a1>ml/min</font></html>");
		add(flowRateLbl);
		
		Icon downArrow = new ImageIcon(getClass().getClassLoader().getResource("resources/ms-adjust-down.png"));
		downArrowLbl = new JLabel();
		downArrowLbl.setHorizontalAlignment(JLabel.CENTER);
		downArrowLbl.setVerticalAlignment(JLabel.CENTER);
		downArrowLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 40));
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
}
