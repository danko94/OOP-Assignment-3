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
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFrame;


import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener, MouseListener
{
	LinkedList<Point3D> points = new LinkedList<Point3D>();
	
	public Window()
	{
		initGUI();
	}
	
	private void initGUI() 
	{
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("Item 1");
		item1.addActionListener(this);
		
		MenuItem item2 = new MenuItem("Item 2");
		item2.addActionListener(this);
		
		menu.add(item1);
		menu.add(item2);
		
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
		
		if(str.equals("Item 1"))
		{
			Point3D p1 = new Point3D(100,100);
			Point3D p2 = new Point3D(50,300);
			Point3D p3 = new Point3D(400,150);
			
			points.add(p1);
			points.add(p2);
			points.add(p3);
			
			repaint();
		}
		if(str.equals("Item 2"))
		{
			DGraph graph = new DGraph();
			
			for(int i=1;i<=150;i++) {
				DGraph.Node n = new DGraph.Node(i, 1);
				int x = (int) ((Math.random() * (((500-1) - 1) + 1)) + 1);
				int y = (int) ((Math.random() * (((500-1) - 1) + 1)) + 1);

				Point3D p = new Point3D(x,y);
				graph.addNode(n);	
				if(i>1) {
					for(int j=0; j<50; j++) {
						int rand = (int) ((Math.random() * (((150-1) - 1) + 1)) + 1);
						graph.connect(i, rand, 1);
					}
				}
			}
			for(int i=0;i<15;i++) {
				graph.connect(i, i+15, 3);
		}
			graph.connect(1, 150, 1);
			
			
			Collection<node_data> nodes = graph.getV();
			for(node_data n : nodes) {
				
			}
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
