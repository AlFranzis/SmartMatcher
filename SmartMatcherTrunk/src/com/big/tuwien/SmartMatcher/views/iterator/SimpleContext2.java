package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;

public class SimpleContext2 implements Context {
	private Set<Element> e;
	
	
	public SimpleContext2(){}
	
	
	public SimpleContext2(Set<Element> e) {
		setContextElement(e);
	}
	
	
	public void setContextElement(Set<Element> e) {
		this.e = e;
	}
	
	
	public Set<Element> getContextElement() {
		return e;
	}
}
