package com.big.tuwien.SmartMatcher.views.dom;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * This class considers the classes and references of a metamodel as a
 * mathematical graph with nodes (classes) and edges (references).
 * It provides methods which return the graph distance of two nodes (elements).
 * @author alex
 *
 */
public abstract class LinkGraph {
	/*
	 * Graph edges: node -> adjacent nodes
	 */
	protected Map<Element, Set<Element>> edges = new HashMap<Element, Set<Element>>();
	/*
	 * Graph nodes
	 */
	protected List<Element> nodes;
	/*
	 * Maps the nodes to rows / columns in the distance matrix
	 */
	protected BiMap<Element,Integer> indices = new HashBiMap<Element,Integer>();
	
	/*
	 * Distance matrix which stores the min. distance between all nodes
	 */
	protected int matrix[][];

	
	public LinkGraph() {}
	

	/**
	 * Builds the graph structure (nodes, edges);
	 */
	public abstract void build();
	
	
	/**
	 * Sets the nodes (metamodel classes) of the graph
	 * @param nodes
	 */
	public void setNodes(List<Element> nodes) {
		this.nodes = nodes;
	}

	
	/**
	 * Returns the minimal distance between 2 elements.
	 * Returns -1 if no path exists.
	 * @param e1
	 * @param e2
	 * @return
	 */
	public int getMinDistance(Element e1, Element e2) {
		int i1 = indices.get(e1);
		int i2 = indices.get(e2);
		
		return matrix[i1][i2];
	}

	
	/*
	 * Fills the distance matrix
	 */
	protected void distances() {
		int size = nodes.size();
		this.matrix = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			Element e1 = nodes.get(i);
			indices.put(e1, i);
		}
		
		for(Element e1 : nodes) {
			// distances contains minimal path length from e1 to all other nodes
			Map<Element,Integer> distances = dijkstra(e1);
			int e1Index = indices.get(e1);
			for(Element e : distances.keySet()) {
				int d = distances.get(e);
				matrix[e1Index][indices.get(e)] = d < Integer.MAX_VALUE ? d : -1;
			}
		}
	}
	
	
	/**
	 * Dijkstra shortest path algorithm
	 * @param e1 Node for which the shortest paths to all other nodes is calculated.
	 * @return Map<Element,Integer> that contains the minimum distances from
	 * e1 to every other node in the graph. The distance is 
	 * {@link Integer#MAX_VALUE} if no path exists between 2 nodes.
	 */
	protected Map<Element,Integer> dijkstra(Element e1) {
		/*
		 * Distance map: stores the distance from e1 to every other node
		 */
		Map<Element,Integer> dist = new HashMap<Element,Integer>();
		
		// initialize
		for(Element e : nodes) 
			dist.put(e, Integer.MAX_VALUE);
	
		dist.put(e1, 0);
		
		Set<Element> q = new HashSet<Element>(nodes);
		
		while( !q.isEmpty() ) {
			Element u = getMinDistanceElement(q, dist);
			q.remove(u);
			
			Set<Element> neighbours = new HashSet<Element>(edges.get(u));
			neighbours.retainAll(q);
			//neighbours.add(e1);
			for(Element n : neighbours) {
				if(dist.get(u) < Integer.MAX_VALUE) {
					int alt = dist.get(u) + 1;
					if(alt < dist.get(n))
						dist.put(n, alt);
				}
			}
		}
		
		for(Element e : nodes) {
			if(edges.get(e).contains(e))
				dist.put(e, 1);
			else if(dist.get(e) == 0)
				dist.put(e, Integer.MAX_VALUE);
		}
		
		return dist;
	}

	
	/*
	 * Returns the element with the minimal distance in elements.
	 */
	private Element getMinDistanceElement(Set<Element> elements, Map<Element,Integer> distances) {
		Element minElement = null;
		Integer min = Integer.MAX_VALUE;
		
		if(!elements.isEmpty()) 
			minElement = elements.iterator().next();
		else
			return null;
		
		for(Element e : elements) {
			int d = distances.get(e);
			if( d < min ) {
				minElement = e;
				min = distances.get(e); 
			}
		}
		return minElement;
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		BiMap<Integer,Element> _indices = indices.inverse();
		
		for(int i = 0; i < matrix.length; i++) {
			sb.append("  " + _indices.get(i).getName());		
		}
		sb.append("\n");
		
		for(int i = 0; i < matrix.length; i++) {
			sb.append(_indices.get(i).getName() + "  ");
			
			for(int j = 0; j < matrix.length; j++) {
				sb.append(matrix[i][j] + "  ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}