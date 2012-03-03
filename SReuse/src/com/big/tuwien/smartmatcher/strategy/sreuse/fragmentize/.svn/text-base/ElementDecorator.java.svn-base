package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;

public abstract class ElementDecorator extends Element {
	private Element original;
	private int hashCode = hash();
	
	public ElementDecorator(Element original, EcoreElement representedBy) {
		this(original);
		setRepresentedBy(representedBy);
	}
	
	
	public static int hash() {
		double random = Math.random() * 100000;
		int hashCode = (int) random;
		return hashCode;
	}
	
	
	public ElementDecorator(Element original) {
		this.original = original;
		setName(original.getName());
	}
	
	
	public void setRepresentedBy(EcoreElement representedBy) {
		this.representedBy = representedBy;
	}


	
	@Override
	public Element copy() {
		throw new ReuseRuntimeException("Method not implemented");
	}

	
	@Override
	public Element shallowCopy() {
		throw new ReuseRuntimeException("Method not implemented");
	}
	
	
	public Element getOriginal() {
		return original;
	}
	
	
	@Override
	public int hashCode() {
		return hashCode;
	}
	
	
	@Override
	public boolean equals(Object other) {
		return this == other;
	}
}
