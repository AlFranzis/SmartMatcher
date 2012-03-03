/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;

public class ReferenceDecorator extends ElementDecorator {	
	
	public ReferenceDecorator(ClassElement containedIn, ClassElement pointsTo, 
																Element ref) {
		super(ref);
		
		setRepresentedBy(
			new ReferenceElementDecorator(
				containedIn,
				pointsTo,
				this
			)
		);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof ReferenceDecorator))
			return false;
		
		ReferenceDecorator that = (ReferenceDecorator) other;
		
		// are both decorating the same original element ?
		if(!this.getOriginal().equals(that.getOriginal()))
			return false;
	
		return true;
	}
	

	@Override 
	public int hashCode() {
		return 57 * getOriginal().hashCode();
	}
	
}