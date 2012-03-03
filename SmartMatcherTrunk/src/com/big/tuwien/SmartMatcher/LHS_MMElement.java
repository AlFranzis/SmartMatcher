package com.big.tuwien.SmartMatcher;



public class LHS_MMElement extends Element {

	
	public LHS_MMElement() {}

	
	@Override
	public Element copy() {
		LHS_MMElement copy = new LHS_MMElement();
		copy.name = this.name;
		copy.id = this.id;
		copy.representedBy = this.representedBy.copy(copy);
		return copy;
	}


	@Override
	public Element shallowCopy() {
		LHS_MMElement copy = new LHS_MMElement();
		copy.setName(this.getName());
		copy.setId(this.getId());
		copy.setAbstract(this.isAbstract());
		
		return copy;
	}
	
	

}