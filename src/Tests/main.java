package Tests;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.DGraph.Node;

public class main {

	public static void main(String[] args) {

		DGraph dG = new DGraph();
		DGraph.Node n1 = new DGraph.Node(1,1);
		DGraph.Node n2 = new DGraph.Node(2,1);
		DGraph.Node n3 = new DGraph.Node(3,1);
		DGraph.Node n4 = new DGraph.Node(4,1);
		DGraph.Node n5 = new DGraph.Node(5,1);

//		dG.addNode(n1);
//		dG.addNode(n5);
//		dG.addNode(n4);
//		dG.addNode(n3);
//		dG.addNode(n2);
//		dG.connect(1, 2, 1);
//		dG.connect(2, 4, 8);
//		dG.connect(1, 3, 3);
//		dG.connect(3, 4, 4);
//		dG.connect(4, 5, 4);
//		dG.connect(5, 1, 3);



		for(int i=1;i<=150;i++) {
			DGraph.Node n = new DGraph.Node(i, 1);
			dG.addNode(n);	
			if(i>1) {
				for(int j=0; j<50; j++) {
					int rand = (int) ((Math.random() * (((150-1) - 1) + 1)) + 1);
					dG.connect(i, rand, 1);
				}
			}
		}
		for(int i=0;i<15;i++) {
			dG.connect(i, i+15, 3);
		}
//
		dG.connect(1, 150, 1);
		dG.connect(1, 149, 1);
		dG.connect(1, 148, 1);

		

		dG.toString();
//		System.out.println(dG.edgeSize());

		Graph_Algo alg = new Graph_Algo();
		alg.init(dG);
		System.out.println(alg.isConnected());

		System.out.println(alg.shortestPathDist(50, 67));

		LinkedList <node_data> path  = (LinkedList)alg.shortestPath(50, 67);
		if(path!=null) {
			for(node_data n : path) {
				System.out.println(n.getKey());
			}
		}
		//alg.save("graph.txt");
		Graph_Algo alg2 = new Graph_Algo();
		alg2.init("graph.txt");
		//System.out.println(alg2);
	}

}
