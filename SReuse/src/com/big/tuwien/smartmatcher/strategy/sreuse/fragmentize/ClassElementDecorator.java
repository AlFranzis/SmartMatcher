/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

class ClassElementDecorator extends ClassElement {
	private List<AttributeElement> visibleAtts = new Vector<AttributeElement>();
	private List<ReferenceElement> visibleRefs = new Vector<ReferenceElement>();
	
	public ClassElementDecorator(
			Element e, 
			List<AttributeElement> visibleAtts, 
			List<ReferenceElement> visibleRefs) {
		super(e);
		this.visibleAtts.addAll(visibleAtts);
		this.visibleRefs.addAll(visibleRefs);
	}
	
	public void addVisibleAttribute(AttributeElement ae) {
		this.visibleAtts.add(ae);
	}
	
	public void addVisibleRef(ReferenceElement re) {
		this.visibleRefs.add(re);
	}
	
	@Override
	public List<ReferenceElement> getReferences() {
	    return visibleRefs;
	}
	
	@Override
	public List<AttributeElement> getAttributes() {
	    return visibleAtts;
	}
	
	@Override
	public String toString() {
		return represents.toString();
	}
	
}