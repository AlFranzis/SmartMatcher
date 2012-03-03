package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;

public class AttributeElementDecorator extends AttributeElement {

	public AttributeElementDecorator(ClassElement containedIn, Element e) {
		super(e);
		setContainedIn(containedIn);
	}
	
	
	@Override
	public String toString() {
		return represents.toString();
	}
	
}
