package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import utils.Point3D;

public class DGraph implements graph, Serializable{
	
	public static class Edge implements edge_data, Serializable{
		
		int dest;
		int src;
		double weight;
		int tag;
		String info;
		
		public Edge(int dest, int src, double weight) {
			this.dest=dest;
			this.src=src;
			this.weight=weight;
			}
		
		public int getDest() {
			return dest;
		}

		public void setDest(int dest) {
			this.dest = dest;
		}

		public int getSrc() {
			return src;
		}

		public void setSrc(int src) {
			this.src = src;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		@Override
		public int getTag() {
			return tag;
		}

		@Override
		public void setTag(int t) {
			this.tag = t;			
		}

	
	}
	
	public static class Node implements node_data, Comparable<node_data>, Serializable{
		
		Point3D location;
		int key;
		int tag;
		double weight;
		String info;
		Map<Integer, edge_data> adjList; //key is destination
		
		public Node(int key, double weight) {
			adjList = new HashMap<Integer, edge_data>();
			this.key = key;
			this.weight = weight;			
		}
		
		
		public edge_data getEdge(int keyDest) {
			return adjList.get(keyDest);
		}
		public void setEdge(int keyDest, Edge e) {
			adjList.put(keyDest, e);
		}
		public edge_data removeEdge(int keyDest) {
			edge_data e = adjList.remove(keyDest);
			if(e!=null) {
				edgeCount--;
			}
			return e;
		}
		public void removeAllEdges() {
			ArrayList <edge_data> edges = new ArrayList<edge_data>(adjList.values());
			Iterator <edge_data> iter = edges.iterator();
			while(iter.hasNext()) {
				edge_data e = adjList.remove(iter.next().getDest());  //more than 1 edge to same destination?
				edgeCount--;
			}			
		}
		@Override
		public int getKey() {
			return key;
		}

		@Override
		public Point3D getLocation() {
			return location;
		}

		@Override
		public void setLocation(Point3D p) {
			this.location = p;			
		}

		@Override
		public double getWeight() {
			return weight;
		}

		@Override
		public void setWeight(double w) {
			this.weight = w;
			
		}

		@Override
		public String getInfo() {
			return info;
		}

		@Override
		public void setInfo(String s) {
			this.info = s;
		}

		@Override
		public int getTag() {
			return tag;
		}

		@Override
		public void setTag(int t) {
			this.tag = t;
			
		}


		
		@Override
		public int compareTo(node_data o) {
			if(this.getWeight()>o.getWeight())
				return 1;
			else if(this.getWeight()<o.getWeight())
				return -1;
			else
				return 0;
		}
		
	}

	//map <src, map<dest, edge_data>>
	Map<Integer, node_data> graph; //key is src/key
		
	static int vertixCount;
	static int edgeCount;
	
	
	
	public DGraph() {
		graph = new HashMap<Integer, node_data>();
		vertixCount = 0;
		edgeCount = 0;
	}
	
	
	@Override
	public node_data getNode(int key) {
		node_data nD =graph.get(key);
		return nD;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		node_data nD = graph.get(src);
		if(nD==null) {
			return null;
		}
		edge_data eD = ((Node) nD).getEdge(dest);
		return eD;		
	}

	@Override
	public void addNode(node_data n) {
		int key = n.getKey();
		graph.put(key, (Node) n);
		vertixCount++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(this.getEdge(src, dest)!=null) {
			return;
		}
		node_data source = graph.get(src);
		//node_data destination =graph.get(dest);
		if(source==null || graph.get(dest)==null) {  //2nd condition raises runtime by a factor 2-3
			return;
		}
		Edge edge = new Edge(dest, src, w);
		((Node) source).setEdge(dest, edge);
		edgeCount++;
	}

	@Override
	public Collection<node_data> getV() {
		Collection<node_data> nodeCol = graph.values();
		return nodeCol;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		node_data nD = graph.get(node_id);		
		Collection<edge_data> edgeCol = ((Node) nD).adjList.values();
		return edgeCol;
	}

	@Override
	public node_data removeNode(int key) {
		node_data nD = graph.remove(key);		
		if(nD==null) {
			return null;
		}
		((Node) nD).removeAllEdges();
		for(Map.Entry<Integer, node_data> entry : graph.entrySet()) {
			node_data vertix = entry.getValue();
			((Node) vertix).removeEdge(key);
		}
		vertixCount--;
		return nD;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		node_data nD = graph.get(src);
		edge_data eD = ((Node) nD).removeEdge(dest);
		return eD;
	}


	@Override
	public int nodeSize() {
		return vertixCount;
	}

	@Override
	public int edgeSize() {
		return edgeCount;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		Collection<node_data> nodes = this.getV();
		for(node_data n : nodes) {
			int key = n.getKey();
			Collection<edge_data> edges = this.getE(key);
			System.out.print(key);
			for(edge_data e : edges) {
				System.out.print(" -> " + e.getDest());
			}
			System.out.println();
		}
		return null;
	}

	public Map<Integer, node_data> getGraph() {
		return this.graph;
	}
	
}
