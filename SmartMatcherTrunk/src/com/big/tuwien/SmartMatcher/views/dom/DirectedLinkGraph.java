package com.big.tuwien.SmartMatcher.views.dom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

/**
 * Implements {@link LinkGraph} as graph with directed edges.
 * @author alex
 *
 */
public class DirectedLinkGraph extends LinkGraph {
	
	
	
	/**
	 * Constructs the directed graph model.
	 */
	public void build() {
		edges();
		distances();
	}
	
	
	/**
	 * Returns the minimal distance between 2 elements.
	 * Returns -1 if no path exists.
	 * @param e1
	 * @param e2
	 * @return
	 */
//	public int getMinDistance(Element e1, Element e2) {
//		int i1 = indices.get(e1);
//		int i2 = indices.get(e2);
//		
//		int d = matrix[i1][i2];
//		return d == Integer.MAX_VALUE ? -1 : d;
//	}
	
	
	/*
	 * Fills the directed edges for each node
	 */
	private void edges() {
		for(Element clazz : nodes) {
			Set<Element> refs = new HashSet<Element>();
			edges.put(clazz, refs);
		}
		
		for(Element clazz : nodes) {
			Set<Element> referenced = edges.get(clazz);
			List<ReferenceElement> refs = ((ClassElement) clazz.getRepresentedBy()).getReferences();
			for(ReferenceElement ref : refs) {
				referenced.add(ref.getPointsTo().getRepresents());
			}
			edges.put(clazz, referenced);
		}
	}
	
	/*
	private void distances() {
		int size = nodes.size();
		matrix = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			Element e1 = nodes.get(i);
			indices.put(e1, i);
			
			for(int j = 0; j < size; j++) {
				Element e2 = nodes.get(j);
				matrix[i][j] = reachability(e1, e2);
			}
		}
	}
	*/
	
	
//	public void setNodes(List<Element> nodes) {
//		this.nodes = nodes;
//	}
//	
	
	/*
	 * Returns the minimal directed path length from e1 to e2. Returns
	 * -1 if there is no path.
	 * The implementation is based on a simple algorithm
	 * which marks all nodes (= classes) that are reachable from the starting 
	 * node e1.
	 */
//	public int reachability(Element e1, Element e2) {
//		/*
//		 * Stores the nodes whose edges should be explored further
//		 */
//		List<Element> s = new Vector<Element>();
//		s.add(e1);
//		
//		/*
//		 * Distance map which stores the found minimal distance
//		 */
//		Map<Element,Integer> distanceMap = new HashMap<Element,Integer>();
//		
//		/*
//		 * Stores all visited nodes
//		 */
//		List<Element> marked = new Vector<Element>();
//		
//		if(! e1.equals(e2)) {
//			marked.add(e1);
//		}
//		
//		int dist = 1;	// distance (= path length) to e1
//		while( !s.isEmpty() ) {
//			Element i = s.remove(0);
//			for(Element j : edges.get(i)) {
//				if( !marked.contains(j) ) {
//					distanceMap.put(j, dist);
//					marked.add(j);
//					s.add(j);
//				} else {
//					if(distanceMap.get(j) > dist)	// new found path is shorter than old one
//						distanceMap.put(j, dist);
//				}
//			}
//			dist++;
//		}
//		
//		return (distanceMap.containsKey(e2)) ? distanceMap.get(e2) : -1;
//	}
	
}
