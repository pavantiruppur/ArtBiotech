package com.ab.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.view.MainWindow;

@SuppressWarnings("serial")
public class Chamber extends JPanel {

	String chamberName;
	ImageIcon chamberIcon;
	JLabel pvValue;
	JLabel svValue;
	Double pv = 99.7;
	Double sv = 34.7;
	
	Boolean isEditable = false;
	
	JLabel chamberBgLbl;
	JLabel downArrowLbl;
	JLabel upArrowLbl;

	public Chamber(String chamberName, String chamberIcon) {
		this.chamberName = chamberName;
		this.chamberIcon = new ImageIcon(chamberIcon);
		load();
	}

	private void load() {

		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setBackground(MainWindow.getInstance().body.getBackground());
		setPreferredSize(new Dimension(chamberIcon.getIconWidth() + (chamberIcon.getIconWidth() / 2), chamberIcon.getIconHeight() + 200));

		Icon upArrow = new ImageIcon("E:/My_Workspace/ArtBiotech/resources/ms-adjust-up.png");
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
				pv += 0.1;
				chamberBgLbl.setText("<html>&nbsp;"+ String.format("%4.1f" , pv) +"oC<br>&nbsp;"+ sv +"oC</html>");
				
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
		chamberBgLbl.setText("<html>&nbsp;"+ pv +"oC<br>&nbsp;"+ sv +"oC</html>");
		chamberBgLbl.setFont(new Font(Font.SERIF, Font.BOLD, 30));
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
				isEditable = !isEditable;
				upArrowLbl.setEnabled(isEditable);
				downArrowLbl.setEnabled(isEditable);
			}
		});
		add(chamberBgLbl);
		
		Icon downArrow = new ImageIcon("E:/My_Workspace/ArtBiotech/resources/ms-adjust-down.png");
		downArrowLbl = new JLabel();
		downArrowLbl.setHorizontalAlignment(JLabel.CENTER);
		downArrowLbl.setVerticalAlignment(JLabel.CENTER);
		downArrowLbl.setPreferredSize(new Dimension(chamberIcon.getIconWidth(), 100));
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
				pv -= 0.1;
				chamberBgLbl.setText("<html>&nbsp;"+ String.format("%4.1f" , pv) +"oC<br>&nbsp;"+ sv +"oC</html>");
				
			}
		});
		downArrowLbl.setEnabled(isEditable);
		add(downArrowLbl);
	}
}
