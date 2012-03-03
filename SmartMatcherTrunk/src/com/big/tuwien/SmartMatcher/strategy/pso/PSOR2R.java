package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.List;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class PSOR2R extends AbstractCorrespondence {
	protected C2<PSOC2C> context;
	
	
	public PSOR2R(C2<PSOC2C> context, Element lhs, Element rhs) {
		this.context = context;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	
	public C2<PSOC2C> getContext() {
		return context;
	}
	
	
	public void destroy() {
		context.op1.remove(this);
		context.op2.remove(this);
	}
	
	
	public boolean compatible(T2<PSOC2C,PSOC2C> c2cs) {
		ReferenceElement lhsRef = (ReferenceElement) lhs.getRepresentedBy();
		List<Element> lhsClasses = Arrays.asList(c2cs.e0.lhs, c2cs.e1.lhs);
		ReferenceElement rhsRef = (ReferenceElement) rhs.getRepresentedBy();
		List<Element> rhsClasses = Arrays.asList(c2cs.e0.rhs, c2cs.e1.rhs);
		return 
			lhsClasses.contains(lhsRef.getContainedIn().getRepresents())
			&& lhsClasses.contains(lhsRef.getPointsTo().getRepresents())
			&& rhsClasses.contains(rhsRef.getContainedIn().getRepresents())
			&& rhsClasses.contains(rhsRef.getPointsTo().getRepresents());
	}
	
	
//	public PSOR2R clone(Context context) {
//		if( context == null || !(context instanceof C2) )
//			throw new IllegalArgumentException("Invalid context type: " + context.getClass());
//		C2<PSOC2C> c2 = (C2<PSOC2C>) context;
//	
//		return new PSOR2R(c2, lhs, rhs);
//	}
//	
//	
//	public PSOR2R clone(Context context, 
//			Map<Correspondence,Correspondence> cloneMap, 
//			Predicate<Correspondence> p) {
//		if(!p.apply(this)) throw new IllegalArgumentException("Predicate filters this node");
//		if(cloneMap.containsKey(this)) throw new IllegalArgumentException("Node already cloned");
//		
//		PSOR2R clone = clone(context);
//		cloneMap.put(this, clone);
//		return clone;
//	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOR2R))
			return false;
		
		PSOR2R that = (PSOR2R) other;
		return lhs.equals(that.lhs) 
				&& rhs.equals(that.rhs);
	}
	
	
	@Override
	public int hashCode() {
		return (47 * PSOR2R.class.hashCode()) 
					+ (3 * lhs.hashCode())
					+ (53 * rhs.hashCode());
	}
	
	
	@Override
	public String toString() {
		return "R2R(" + lhs + "," + rhs + ")";
	}


	@Override
	public Class<? extends Correspondence> getType() {
		return PSOR2R.class;
	}
	
}
