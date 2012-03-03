package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XMLFragments implements XMLAny {
	private List<XMLFragment> fragments;
	
	
	public static XMLFragments getInstance(Collection<Fragment> frags, XMLElementResolverFactory resolverFactory) {
		List<XMLFragment> xmlFrags = new ArrayList<XMLFragment>();
		for(Fragment f : frags) {
			XMLFragment xmlFrag = XMLFragment.getInstance(f, resolverFactory);
			xmlFrags.add(xmlFrag);
		}
		
		XMLFragments xmlFragments = new XMLFragments();
		xmlFragments.setFragments(xmlFrags);
		return xmlFragments;
	}

	
	public List<XMLFragment> getFragments() {
		return fragments;
	}

	
	public void setFragments(List<XMLFragment> fragments) {
		this.fragments = fragments;
	}
	
}
