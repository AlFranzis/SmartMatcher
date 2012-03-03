/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.cast2;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getAttributes;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getReferences;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

public class ClassDecorator extends ElementDecorator {	
	
	public ClassDecorator(Element clazz) {
		this(
			clazz, 
			Collections.<Element>emptyList(), 
			Collections.<Element>emptyList()
		);
	}
	
	
	public ClassDecorator(Element clazz, List<Element> visibleAtts, 
										List<Element> visibleRefs) {
		super(clazz);
		
		setRepresentedBy(new ClassElementDecorator(
				// ClassElementDecorator is representedBy this object
				this,
				cast2(visibleAtts, AttributeElement.class),
				cast2(visibleRefs, ReferenceElement.class)
				));
	}
	
	
	public void addVisibleAttribute(Element att) {
		((ClassElementDecorator)getRepresentedBy())
				.addVisibleAttribute(
					(AttributeElement) att.getRepresentedBy()
				);
	}
	
	
	public void addVisibleRef(Element ref) {
		((ClassElementDecorator)getRepresentedBy())
				.addVisibleRef(
					(ReferenceElement) ref.getRepresentedBy()
				);
	}
	
	
	private Set<Element> getVisibleAttributes() {
		List<Element> visibleAttributes = getAttributes(this);
		Set<Element> _visibleAttributes = 
			new HashSet<Element>(visibleAttributes);
		return _visibleAttributes;
	}
	
	
	private Set<Element> getVisibleReferences() {
		List<Element> visibleRefs = getReferences(this);
		Set<Element> _visibleRefs = 
			new HashSet<Element>(visibleRefs);
		return _visibleRefs;
	}
	

	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof ClassDecorator))
			return false;
		
		ClassDecorator that = (ClassDecorator) other;
		
		// are both decorating the same original element ?
		if(!this.getOriginal().equals(that.getOriginal()))
			return false;
		
		// check if both class decorators have the exact same attributes
		if(!getVisibleAttributes().equals(that.getVisibleAttributes()))
			return false;
		
		// check if both class decorators have the exact same references
		if(!getVisibleReferences().equals(that.getVisibleReferences()))
			return false;
		
		return true;
	}
	

	@Override 
	public int hashCode() {
		return 67 * getOriginal().hashCode() 
			+ 45 * getVisibleAttributes().hashCode()
			+ 177 * getVisibleReferences().hashCode();
	}
	
}