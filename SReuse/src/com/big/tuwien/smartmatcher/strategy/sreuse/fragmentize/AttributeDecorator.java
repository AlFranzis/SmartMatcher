/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;

public class AttributeDecorator extends ElementDecorator {	
	
	
	public AttributeDecorator(ClassElement containedIn, Element attribute) {
		super(attribute);
		
		setRepresentedBy(
			new AttributeElementDecorator(
				containedIn,
				this
			)
		);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof AttributeDecorator))
			return false;
		
		AttributeDecorator that = (AttributeDecorator) other;
		
		// are both decorating the same original element ?
		if(!this.getOriginal().equals(that.getOriginal()))
			return false;
	
		return true;
	}
	

	@Override 
	public int hashCode() {
		return 71 * getOriginal().hashCode();
	}
	
}