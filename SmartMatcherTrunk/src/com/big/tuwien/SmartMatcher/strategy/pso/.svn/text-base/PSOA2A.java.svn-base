package com.big.tuwien.SmartMatcher.strategy.pso;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.Element;

public class PSOA2A extends AbstractCorrespondence {
	protected C1<PSOC2C> context;
	
	
	public PSOA2A(C1<PSOC2C> context, Element lhs, Element rhs) {
		this.context = context;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	
	public void destroy() {
		context.op1.remove(this);
	}
	
	
	public C1<PSOC2C> getContext() {
		return context;
	}
	
	
	public boolean compatible(PSOC2C c2c) {
		return getContainingClass(lhs).equals(c2c.lhs) &&
				getContainingClass(rhs).equals(c2c.rhs);
	}
	
	
	private Element getContainingClass(Element att) {
		return ((AttributeElement)att.getRepresentedBy())
				.getContainedIn().getRepresents();
	}
	
	
//	public PSOA2A clone(Context context) {
//		if( context == null || !(context instanceof C1) )
//			throw new IllegalArgumentException("Invalid context type: " + context.getClass());
//		C1<PSOC2C> c1 = (C1<PSOC2C>) context;
//		
//		return new PSOA2A(c1, lhs, rhs);
//	}
//	
//	
//	public PSOA2A clone(Context context, 
//			Map<Correspondence,Correspondence> cloneMap, 
//			Predicate<Correspondence> p) {
//		if(!p.apply(this)) throw new IllegalArgumentException("Predicate filters this node");
//		if(cloneMap.containsKey(this)) throw new IllegalArgumentException("Node already cloned");
//		
//		PSOA2A clone = clone(context);
//		cloneMap.put(this, clone);
//		return clone;
//	}

	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOA2A))
			return false;
		
		PSOA2A that = (PSOA2A) other;
		return lhs.equals(that.lhs) 
				&& rhs.equals(that.rhs);
	}
	
	
	@Override
	public int hashCode() {
		return (31 * PSOA2A.class.hashCode()) 
					+ (49 * lhs.hashCode())
					+ (11 * rhs.hashCode());
	}
	
	
	@Override
	public String toString() {
		return "A2A(" + lhs + "," + rhs + ")";
	}


	@Override
	public Class<? extends Correspondence> getType() {
		return PSOA2A.class;
	}
}
