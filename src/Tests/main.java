package Tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.DGraph.Node;

public class main {

	public static void main(String[] args) {

		DGraph dG = new DGraph();

		DGraph.Node n1 = new DGraph.Node(1, 1);
		DGraph.Node n2 = new DGraph.Node(2, 1);
		DGraph.Node n3 = new DGraph.Node(3, 1);
		DGraph.Node n4 = new DGraph.Node(4, 1);
		DGraph.Node n5 = new DGraph.Node(5, 1);

		dG.addNode(n1);
		dG.addNode(n5);
		dG.addNode(n4);
		dG.addNode(n3);
		dG.addNode(n2);
		dG.connect(1, 2, 5);
		dG.connect(1, 3, 2);
		dG.connect(2, 5, 7);
		dG.connect(3, 4, 1);
		dG.connect(4, 2, 1);
		dG.connect(4, 5, 3);


		long start = System.currentTimeMillis(); for(int i=1;i<=1000;i++) {
			DGraph.Node n = new DGraph.Node(i, 1); dG.addNode(n);

		}

		for(int i=0; i<=1000;i++) { for(int j=0;j<20;j++) { int rand = (int)
				((Math.random() * (((1000-1) - 1) + 1)) + 1); int randw = (int)
				((Math.random() * (((20-1) - 1) + 1)) + 1); dG.connect(i, rand, randw); } }
		dG.connect(1, 1000, 2); //dG.toString();

		long finish = System.currentTimeMillis();
		System.out.println((finish-start)/1000.0);


		// dG.toString();
		// System.out.println(dG.edgeSize());

		Graph_Algo alg = new Graph_Algo();
		alg.init(dG);
		System.out.println(alg.isConnected());
		long start2 = System.currentTimeMillis();
		System.out.println(alg.shortestPathDist(1, 5));
		long finish2 = System.currentTimeMillis();
		System.out.println((finish2 - start2) / 1000.0);

		LinkedList<node_data> path = (LinkedList) alg.shortestPath(1, 5);
		if (path != null) {
			for (node_data n : path) {
				// System.out.println(n.getKey());
			}
		}

		List<Integer> targets = new ArrayList<Integer>();
		targets.add(1);
		//		targets.add(3);
		//		targets.add(4);
		//		targets.add(5);
		//		targets.add(4);
		//		targets.add(1);

		List<node_data> tsp = alg.TSP(targets);
		if (tsp != null) {
			for (node_data n : tsp) {

				System.out.print(n.getKey() + " -> ");
			}
		}
		
		DGraph dG1 = new DGraph();
		DGraph.Node node1 = new DGraph.Node(1,2);
		DGraph.Node node2 = new DGraph.Node(2,2);
		dG1.addNode(node1);
		dG1.addNode(node2);
		dG1.connect(1, 2, 2);
		Graph_Algo ga = new Graph_Algo();
		ga.init(dG1);
		
		ga.save("test.txt");
		//alg.save("graph.txt");
		// Graph_Algo alg2 = new Graph_Algo();
		// alg2.init("graph.txt");
		// System.out.println(alg2);
	}

}
