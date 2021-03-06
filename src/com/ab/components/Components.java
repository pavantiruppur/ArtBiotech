package com.ab.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Components {
	
	static Font fontTblHeader = new Font(Font.DIALOG, Font.BOLD, 16);
	static Font fontPrBt = new Font(Font.DIALOG, Font.BOLD, 18);
	static Font fontTbl = new Font(Font.DIALOG, Font.BOLD, 15);
	static Font fontTxt = new Font(Font.DIALOG, Font.PLAIN, 14);
	static Font fontBt = new Font(Font.DIALOG, Font.BOLD, 15);
	static Font fontSmall = new Font(Font.DIALOG, Font.BOLD, 12);
	
	static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	static Font DP_MEDIUM_FONT = null;
	static Font DP_HEAVY_FONT = null;
	static {
		try {
			DP_MEDIUM_FONT = Font.createFont(Font.TRUETYPE_FONT, Components.class.getClassLoader().getResourceAsStream("resources/fonts/Device-Paralucent-Medium.otf"));
			ge.registerFont(DP_MEDIUM_FONT);
			DP_HEAVY_FONT = Font.createFont(Font.TRUETYPE_FONT, Components.class.getClassLoader().getResourceAsStream("resources/fonts/Device-Paralucent-Heavy.otf"));
			ge.registerFont(DP_HEAVY_FONT);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JFrame getFrame(){
		JFrame frame = new JFrame();
		frame.setSize(800,480);
		frame.setResizable(false);
		frame.setTitle("ArtBiotech ");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
	    frame.setUndecorated(true);
		return frame;
	}
	
	public static JPanel getContentPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(null);
//		panel.setOpaque(false);
//		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}
	
	public static JPanel getRightPanelContent(){
		JPanel panel = new JPanel();
		panel.setBackground(null);
		Components.setFlowLayoutPadding(panel, 3);
		panel.setPreferredSize(new Dimension(300,575));
//		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	
	public static Font getDPMediumFont(float size) {
		return DP_MEDIUM_FONT.deriveFont(size);
	}
	
	public static Font getDPHeavyFont(float size) {
		return DP_HEAVY_FONT.deriveFont(size);
	}
	
	public static JPanel setBorderLayout(JPanel panel){
		panel.setLayout(new BorderLayout());
		return panel;
	}
	
	public static JPanel setFlowLayout(JPanel panel){
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		panel.setLayout(flowLayout);
		return panel;
	}
	
	public static JPanel setFlowLayoutPadding(JPanel panel,int padding){
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT,padding,3);
		panel.setLayout(flowLayout);
		return panel;
	}
	
	public static JPanel setFlowLayoutPadding(int pos, JPanel panel,int padding,int topPadding){
		FlowLayout flowLayout = new FlowLayout(pos,padding,topPadding);
		panel.setLayout(flowLayout);
		return panel;
	}
	
	public static JLabel getLabel(String text, JComponent parent, int width){
		JLabel label = new JLabel(text);
		label.setPreferredSize(new Dimension(width,30));
		label.setFont(fontTbl);
		parent.add(label);
		return label;
	}
	
	public static JLabel getLabel(String text, JComponent parent, int width, int height){
		JLabel label = new JLabel(text);
		label.setFont(fontBt);
		label.setPreferredSize(new Dimension(width,height));
		parent.add(label);
		return label;
	}
	
	public static JLabel getLabelWithTxtFont(String text, JComponent parent, int width, int height){
		JLabel label = new JLabel(text);
		label.setFont(fontSmall);
		label.setPreferredSize(new Dimension(width,height));
		parent.add(label);
		return label;
	}
	
	public static JTabbedPane getTabPane(){
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setBackground(null);
		tabPane.setFont(fontTblHeader);
		return tabPane;
	}
	
	public static JFileChooser getFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("TestFiles", "dat"));
		return fileChooser;
	}
	
	public static JTextField getTextField(JComponent parent,int width){
		JTextField textField = new JTextField();
//		textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		parent.add(textField);
		textField.setFont(fontTxt);
		textField.setPreferredSize(new Dimension(width, 25));
		return textField;
	}
	
	public static JRadioButton getRadioButton(String text, ButtonGroup btnGrp,JComponent parent,int width){
		JRadioButton radioButton = new JRadioButton(text);
		radioButton.setBackground(null);
		radioButton.setPreferredSize(new Dimension(width, 25));
		btnGrp.add(radioButton);
		parent.add(radioButton);
		return radioButton;
	}
	
	public static JPasswordField getPasswordField(JComponent parent,int width){
		JPasswordField passwordField = new JPasswordField();
//		textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		parent.add(passwordField);
		passwordField.setPreferredSize(new Dimension(width, 25));
		return passwordField;
	}
	
	
	public static JButton getButton(String name, JComponent parent){
		JButton button = new JButton(name);
		parent.add(button);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setPreferredSize(new Dimension(100,30));
		return button;
	}
	
	public static JButton getButton(String name, JComponent parent, int width, int height){
		JButton button = new JButton(name);
		parent.add(button);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	
	public static JButton getPrimaryButton(String name, JComponent parent, int width, int height){
		JButton button = new JButton(name);
		parent.add(button);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setFont(fontPrBt);
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	
	public static JScrollPane getScrollPane(JComponent obj){
		JScrollPane scrollPane = new JScrollPane(obj);
		return scrollPane;
	}
	
	public static JTable getTable(String[][] data, String[] col, int rowHeight){
		JTable table = new JTable(data,col);
		getTableHeader(table);
		table.setFont(fontTbl);
		table.setRowHeight(rowHeight);
		return table;
	}
	
	public static JTable getTable(Object[][] data, String[] col, int rowHeight){
		JTable table = new JTable(data,col);
		getTableHeader(table);
		table.setFont(fontTbl);
		table.setRowHeight(rowHeight);
		return table;
	}
	
	public static JTableHeader getTableHeader(JTable table){
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.gray);
		header.setForeground(Color.WHITE);
		header.setFont(fontTblHeader);
		return header;
	}
	
	public static JMenuBar getMenuBar(){
		JMenuBar menuBar=new JMenuBar();
		return menuBar;
	}
	
	public static JToolBar getToolBar(){
		JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		return toolBar;
	}
	
	public static JMenu getMainMenu(JMenuBar menuBar, String name){
		JMenu mainMenu=new JMenu(name);
		menuBar.add(mainMenu);
		return mainMenu;
	}

	public static JMenu getSubMainMenu(JMenu menuBar, String name){
		JMenu mainMenu=new JMenu(name);
		menuBar.add(mainMenu);
		return mainMenu;
	}
	public static JMenuItem getSubMenu(JMenu mainMenu, String name){
		JMenuItem subMenu=new JMenuItem(name);
		mainMenu.add(subMenu);
		return subMenu;
	}
	
	public static JPanel getStatusBar(){
		JPanel statusBar = new JPanel();
		statusBar.setBackground(null);
		statusBar.setSize(6, 23);
		return statusBar;
	}
	
	public static void addLeafNode(DefaultMutableTreeNode node, String name){
		DefaultMutableTreeNode child =new DefaultMutableTreeNode(name);
		node.add(child);
	}
	
	public static DefaultTreeCellRenderer setImageIconForTreeNode(){
		DefaultTreeCellRenderer cellRender = new DefaultTreeCellRenderer();
		cellRender.setIcon(new ImageIcon("C://Users//Pavan//Desktop//LilacLogo.jpg"));
		return cellRender;
	}
	
	public static JSeparator getSeparator(int pos,JPanel parentPanel, int width, int height){
		//pos = SwingComponent.vertical || horizontal
		JSeparator separatorp = new JSeparator(pos);
		separatorp.setPreferredSize(new Dimension(width, height));
		parentPanel.add(separatorp);
		return separatorp;
	}
}
