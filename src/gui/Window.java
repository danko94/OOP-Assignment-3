package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener, MouseListener
{
	LinkedList<Point3D> points = new LinkedList<Point3D>();
	Graph_Algo algo;
	

	public Window()
	{
		initGUI();
	}

	private void initGUI() 
	{
		algo = new Graph_Algo();
		
		this.setTitle("Graph");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu algoMenu = new Menu("Algorithms");
		//Menu menu = new Menu("File");

		menuBar.add(fileMenu);
		menuBar.add(algoMenu);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("Open");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("Save");
		item2.addActionListener(this);

		MenuItem item3 = new MenuItem("Shortest path distance");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("Shortest path");
		item3.addActionListener(this);

		MenuItem item5 = new MenuItem("Shortest Path passing set of nodes");
		item3.addActionListener(this);

		MenuItem item6 = new MenuItem("Strongly connected graph");
		item3.addActionListener(this);

		fileMenu.add(item1);
		fileMenu.add(item2);

		algoMenu.add(item3);
		algoMenu.add(item4);
		algoMenu.add(item5);
		algoMenu.add(item6);
		this.addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Point3D prev = null;

		for (Point3D p : points) 
		{
			g.setColor(Color.BLUE);
			g.fillOval((int)p.x(), (int)p.y(), 10, 10);

			if(prev != null)
			{
				g.setColor(Color.RED);
				g.drawLine((int)p.x(), (int)p.y(), 
						(int)prev.x(), (int)prev.y());
				double dist = Math.sqrt(Math.pow((prev.x()-p.x()),2)+Math.pow((prev.y()-p.y()), 2));

				dist = Math.round(dist*100.0)/100.0;  //round 2 decimals

 
				g.drawString(Double.toString(dist), (int)((p.x()+prev.x())/2)+7,(int)((p.y()+prev.y())/2)+7);
			}

			prev = p;
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("Open"))
		{
			String fileName = JOptionPane.showInputDialog(null,"Enter the name of file",
					"Open file",
					JOptionPane.PLAIN_MESSAGE);

			File temp = new File(fileName);
			if(!temp.exists()) {
				JOptionPane.showInternalMessageDialog(null, "Specified file does not exist!");
			}
			else {
				this.algo.init(fileName);
			}
		}
		if(str.equals("Save"))
		{
			String fileName = JOptionPane.showInputDialog(null,"Enter the name of file",
					"Open file",
					JOptionPane.PLAIN_MESSAGE);
			File temp = new File(fileName);

			if(temp.exists()) {
				int input = JOptionPane.showConfirmDialog(null, "Do you wish to overwrite?", "File exists", JOptionPane.YES_NO_OPTION);
				System.out.println(input);
				if(input==0) {
					this.algo.save(fileName);
				}
			}
		}
		if(str.contentEquals("Shortest path distance")) {
			int src = 0;
			int dest = 1;
			double dist = this.algo.shortestPathDist(src, dest);
			if(dist!=Double.POSITIVE_INFINITY)
				JOptionPane.showConfirmDialog(null, "Shortest distance: " + dist);
			else
				JOptionPane.showConfirmDialog(null, "" + dist);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
		
		points.add(p);
		repaint();
		System.out.println("mousePressed");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}


}
