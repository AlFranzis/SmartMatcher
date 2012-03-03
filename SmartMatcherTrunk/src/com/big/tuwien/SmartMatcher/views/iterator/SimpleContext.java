package com.big.tuwien.SmartMatcher.views.iterator;

import com.big.tuwien.SmartMatcher.Element;

public class SimpleContext implements Context {
	private Element e;
	
	
	public SimpleContext(){}
	
	
	public SimpleContext(Element e) {
		setContextElement(e);
	}
	
	
	public void setContextElement(Element e) {
		this.e = e;
	}
	
	
	public Element getContextElement() {
		return e;
	}
}
