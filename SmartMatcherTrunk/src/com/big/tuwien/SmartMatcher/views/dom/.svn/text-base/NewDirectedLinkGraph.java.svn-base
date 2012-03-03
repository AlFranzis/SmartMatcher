package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

public class NewDirectedLinkGraph extends LinkGraph{
	protected Graph<Element,DefaultEdge> graph;
	
	
	@Override
	public void build() {
		this.graph = new DefaultDirectedGraph<Element, DefaultEdge>(DefaultEdge.class);
		
		for(Element clazz : nodes) {
			graph.addVertex(clazz);
		}
		
		for(Element clazz : nodes) {
			List<ReferenceElement> refs = ((ClassElement) clazz.getRepresentedBy()).getReferences();
			for(ReferenceElement ref : refs) {
				graph.addEdge(clazz, ref.pointsTo.getRepresents());
			}
			
		}
		
		
	}
	
	/**
	 * Returns the minimal distance between 2 elements.
	 * Returns -1 if no path exists.
	 * @param e1
	 * @param e2
	 * @return
	 */
	public int getMinDistance(Element e1, Element e2) {
		double length = new DijkstraShortestPath<Element, DefaultEdge>(graph,e1, e2).getPathLength();
		return (length == Double.POSITIVE_INFINITY || length == 0) ? -1 : new Double(length).intValue();
	}
	
	
	
	

}
