package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.*;

public class Graph_GUI extends JFrame{
	
	
	public Graph_GUI() {
		
		//GUI
		setTitle("Graph GUI");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Button
		JButton button = new JButton("Exit");


		//button.setBounds(10, 10, 100, 20);
		button.setBackground(Color.GRAY);

		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		button.setFont(font);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * JColorChooser choose = new JColorChooser(); Color c =
				 * choose.showDialog(getContentPane(), "CHOOSE background", Color.CYAN);
				 * button.setBackground(c);
				 */
				JFileChooser filechooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("graphs", "txt");
				filechooser.addChoosableFileFilter(filter);
				int dialogue = filechooser.showDialog(getContentPane(), "open file");
				
				if(dialogue==JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
				}
				
				
			}
		});
		
		//JLabel label = new JLabel("hello world!");
		JCheckBox check = new JCheckBox("i am a check box");
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox source = (JCheckBox)e.getSource();
				if(source.isSelected()) {
					check.setFont(new Font("Calibri", Font.PLAIN, 12));
				}
			}
		});
		String [] list = {"option 1", "option 2"};
		JComboBox<String> combo = new JComboBox<String>(list);
		combo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					combo.addItem(e.getItem().toString());
				}
			}
		});
		
		JProgressBar prog = new JProgressBar();
		prog.setValue(0);
		prog.setBackground(Color.GREEN);
		prog.setForeground(Color.red);

		JSlider slide = new JSlider();
		slide.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = slide.getValue();
				prog.setValue(value);
			}
		});
		
		JToggleButton tog = new JToggleButton("toggle me ;)");
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		JScrollPane sp = new JScrollPane();
		JList<String> jl = new JList<>(ge.getAvailableFontFamilyNames());
		sp.setViewportView(jl);
		
		JTextArea ta = new JTextArea("textext");
		
		JTextPane tp = new JTextPane();
		tp.setContentType("text/html");
		tp.setEditable(false);
	
		
		//Menubar
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("file");
		JMenu submenu = new JMenu("submenu");
		JMenuItem subItem = new JMenuItem("subsub");
		JMenuItem open = new JMenuItem("open this file");
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				System.out.println("hello");
			}
		});
		
		submenu.add(subItem);
		file.add(open);
		file.addSeparator();
		file.add(submenu);
		menubar.add(file);
		setJMenuBar(menubar);
		
		//Layout
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		pane.add(button);
		//pane.add(jl);
		//pane.add(sp);
		pane.add(ta);
		pane.add(tp);
		
		 pane.add(check);
		 pane.add(combo);
		 pane.add(slide);
		 pane.add(prog);
		 pane.add(tog);
		 
		

		
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Graph_GUI g = new Graph_GUI();
				g.setVisible(true);
				
			}
		});
	}
	
	
	
	

}
