package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

public class ReferenceElementDecorator extends ReferenceElement {
//	private ClassElement containedIn;
//	private ClassElement pointsTo;
	
	public ReferenceElementDecorator(ClassElement containedIn, 
								ClassElement pointsTo, Element e) {
		super(e);
//		this.containedIn = containedIn;
//		this.pointsTo = pointsTo;
		setContainedIn(containedIn);
		setPointsTo(pointsTo);
	}

	
//	@Override
//	public ClassElement getContainedIn() {
//		return containedIn;
//	}
//	
//	
//	@Override
//	public ClassElement getPointsTo() {
//		return pointsTo;
//	}
	
	@Override
	public String toString() {
		return represents.toString();
	}
	
}
