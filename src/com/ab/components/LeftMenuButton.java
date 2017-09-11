package com.ab.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ab.view.MainWindow;
import com.ab.view.SettingView;

@SuppressWarnings("serial")
public class LeftMenuButton extends JLabel {

	private static List<LeftMenuButton> menuButtons = new ArrayList<>();
	
	Icon onIcon;
	Icon offIcon;
	JPanel content;
	
	public LeftMenuButton(String onIcon, String offIcon, Boolean isDefaultOn, JPanel content) {
		this.onIcon = new ImageIcon(getClass().getClassLoader().getResource(onIcon)); 
		this.offIcon = new ImageIcon(getClass().getClassLoader().getResource(offIcon));
		
		this.content = content;
		menuButtons.add(this);
		if (isDefaultOn) {
			setIcon(this.onIcon);
		} else {
			setIcon(this.offIcon);
		}
		MenuMouseListner ml = new MenuMouseListner();
		super.addMouseListener(ml);
	}
	
	class MenuMouseListner implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			for(LeftMenuButton menuBt :menuButtons) {
				menuBt.setIcon(menuBt.offIcon);
			}
			setIcon(onIcon);
			SettingView.getInstance().body.removeAll();
			SettingView.getInstance().body.add(content);
			content.repaint();
			SettingView.getInstance().body.repaint();
			SettingView.getInstance().body.validate();
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}