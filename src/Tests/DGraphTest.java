package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.DGraph.Node;
import dataStructure.edge_data;
import dataStructure.node_data;

class DGraphTest {
	
	DGraph dG;
	
	@BeforeEach
	public void createGraph() {
		dG = new DGraph();
		DGraph.Node nG = new DGraph.Node(1, 2.5);
		DGraph.Node nG1 = new DGraph.Node(2, 7);
		DGraph.Node nG2 = new DGraph.Node(3, 14);

		dG.addNode(nG2);
		dG.addNode(nG1);
		dG.addNode(nG);
		dG.connect(1, 2, 3);
		dG.connect(3, 1, 2);
		dG.connect(2, 3, 3);
		
	}

	@Test
	void getNodetest() {
		
		node_data nD = dG.getNode(2);
		assertEquals(7, nD.getWeight());
			
	}
	
	@Test
	void getEdgetest() {
		edge_data eD = dG.getEdge(1, 2);
		assertEquals(3, eD.getWeight());
	}
	
	@Test
	void addNodetest() {
		DGraph.Node nG = new DGraph.Node(4, 1);
		int before = dG.nodeSize();
		dG.addNode(nG);
		int after = dG.nodeSize();
		assertEquals(before+1, after);
	}
	
	@Test
	void connecttest() {
		DGraph.Node nG = new DGraph.Node(4, 1);
		dG.addNode(nG);
		int before = dG.edgeSize();
		dG.connect(4, 1, 1);
		dG.connect(4, 5, 5);
		dG.connect(12, 13, 50);
		assertEquals(before+1, dG.edgeSize());
	}
	@Test
	void removeNodetest() {
		assertNull(dG.removeNode(420));
		
		int before = dG.edgeSize();
		dG.removeNode(3);
		assertEquals(before-2, dG.edgeSize());		
	}

}
