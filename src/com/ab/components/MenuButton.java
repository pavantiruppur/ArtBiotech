package com.ab.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MenuButton extends JLabel {

	private static List<MenuButton> menuButtons = new ArrayList<>();
	
	Icon onIcon;
	Icon offIcon;
	
	public MenuButton(String onIcon, String offIcon) {
		this.onIcon = new ImageIcon(onIcon); 
		this.offIcon = new ImageIcon(offIcon);
		menuButtons.add(this);
		setIcon(this.offIcon);
		MenuMouseListner ml = new MenuMouseListner();
		super.addMouseListener(ml);
	}
	
	class MenuMouseListner implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			for(MenuButton menuBt :menuButtons) {
				menuBt.setIcon(menuBt.offIcon);
			}
			setIcon(onIcon);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}