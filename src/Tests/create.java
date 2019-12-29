package Tests;


import java.util.LinkedList;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.DGraph.Node;

public class create {

	public static void main(String[] args) {



		DGraph dG = new DGraph();

		for(int i=0;i<30;i++) {
			double randw = Math.random()*20+1;
			DGraph.Node n = new DGraph.Node(i, randw);
			dG.addNode(n);
		}
		for(int i=1; i<=30;i++) { 
			int randj = (int)Math.random()*4+1;

			for(int j=0;j<randj;j++) { 
				int rand = (int)((Math.random() * (((30-1) - 1) + 1)) + 1); 
				int randw = (int)((Math.random() * (((20-1) - 1) + 1)) + 1); 
				dG.connect(i, rand, randw); 
				} 
		}
		dG.connect(1, 30, 2); //dG.toString();
		
		Graph_Algo algo = new Graph_Algo();
		algo.init(dG);
		
		algo.save("g.txt");
		LinkedList<Integer> targets = new LinkedList<Integer>();
		targets.add(5);
		targets.add(19);
		//targets.add(5);
		
		if(null!=algo.TSP(targets)){
			System.out.println("hello");
		}
	}

}
