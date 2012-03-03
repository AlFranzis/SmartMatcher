package com.big.tuwien.SmartMatcher.views.dom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;


/**
 * Implements {@link LinkGraph} as graph with undirected edges.
 * @author alex
 *
 */
public class UndirectedLinkGraph extends LinkGraph {
	
	public UndirectedLinkGraph() {}
	
	
	/**
	 * Constructs the undirected graph model. Directed unidirectional class references are
	 * transformed into undirected graph edges.
	 */
	public void build() {
		edges();
		distances();
	}
	
	
	/*
	 * Fills the edge list.
	 */
	private void edges() {
		for(Element clazz : nodes) {
			Set<Element> refs = new HashSet<Element>();
			edges.put(clazz, refs);
		}
		
		for(Element clazz : nodes) {
			Set<Element> referenced = edges.get(clazz);
			List<ReferenceElement> refs = ((ClassElement) clazz.getRepresentedBy()).getReferences();
			
			// every directed reference is considered
			// as a undirected (= bidirectional) graph edge
			for(ReferenceElement ref : refs) {
				Element target = ref.getPointsTo().getRepresents();
				edges.get(target).add(clazz);
				referenced.add(target);
			}
			edges.put(clazz, referenced);
		}
	}
	
	
	

}