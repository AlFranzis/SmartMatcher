package com.big.tuwien.SmartMatcher;


/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 28-Apr-2008 16:22:17
 */
public class RHS_MMElement extends Element {
	
	public RHS_MMElement() {}
	
	
	@Override
	public Element copy() {
		RHS_MMElement copy = new RHS_MMElement();
		copy.name = this.name;
		copy.id = this.id;
		copy.representedBy = this.representedBy.copy(copy);
		return copy;
	}
	
	
	@Override
	public Element shallowCopy() {
		RHS_MMElement copy = new RHS_MMElement();
		copy.setName(this.getName());
		copy.setId(this.getId());
		copy.setAbstract(this.isAbstract());
		
		return copy;
	}

}