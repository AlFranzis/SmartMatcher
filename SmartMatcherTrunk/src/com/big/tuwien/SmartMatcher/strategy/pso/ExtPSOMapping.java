/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.base.Predicate;

public class ExtPSOMapping extends PSOMapping {
	private MetaModel lhsMM;
	private MetaModel rhsMM;
	
	
	public ExtPSOMapping(PSOMapping m, MetaModel lhsMM, MetaModel rhsMM) {
		super(m.children);
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}
	
	
	public Correspondence correspondenceFor(Element e) {
		for(Correspondence c : descendents()) {
			T2<Collection<Element>,Collection<Element>> es = c.elements();
			if(es.e0.contains(e) || es.e1.contains(e))
				return c;
		}
		return null;
	}
	
	
//	@Override
//	public Set<Correspondence> descendents() {
//		Set<Correspondence> dcs = super.descendents();
//		
//		T2<Collection<Element>,Collection<Element>> mapped = elements();
//		
//		List<Element> lhsElements = new Vector<Element>(lhsMM.getElements());
//		// remove all mapped
//		lhsElements.removeAll(mapped.e0);
//		
//		List<Element> rhsElements = new Vector<Element>(rhsMM.getElements());
//		// remove all mapped
//		rhsElements.removeAll(mapped.e1);
//		
//		
//		// TODO here
//		for(Element e : lhsElements) {
//			EcoreElement eElement = e.getRepresentedBy();
//			if(eElement instanceof ClassElement) {
//				
//			} else if(eElement instanceof AttributeElement) {
//				
//			} else if(eElement instanceof ReferenceElement) {
//				
//			} else
//				throw new RuntimeException("Unknown type of element: " + e);
//		}
//		
//		
//		return dcs;
//	}
	
	
	
	/**
	 * Returns the correspondence for an element. The correspondence must have
	 * an assumed type.
	 * @param <T> 
	 * @param e Element (lhs or rhs) to get the correspondence for.
	 * @param ctype Assumed type of the correspondence.
	 * @return Correspondence that maps element e. If no correspondence
	 * for element e exists then null is returned.
	 */
	public <T extends Correspondence> T correspondenceFor(Element e, Class<T> ctype) {
		for(T c : descendents(ctype)) {
			T2<Collection<Element>,Collection<Element>> es = c.elements();
			if(es.e0.contains(e) || es.e1.contains(e))
				return c;
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends Correspondence> T lhsAlignedCorrespondenceFor(T c) {
		return (T) correspondenceFor(c.elements().e0.iterator().next());
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends Correspondence> T rhsAlignedCorrespondenceFor(T c) {
		return (T) correspondenceFor(c.elements().e1.iterator().next());
	}
	
	
	public <T extends Correspondence> T alignedCorrespondenceFor(T c, Side side) {
		if(side.equals(Side.LHS())) {
			return lhsAlignedCorrespondenceFor(c);
		} else if(side.equals(Side.RHS())) {
			return rhsAlignedCorrespondenceFor(c);
		} else 
			throw new PSORuntimeException("Unknown side " + side);
	
	}
	
	
	public boolean isMapped(Element e) {
		return correspondenceFor(e) != null;
	}
	
	
	public boolean hasUnmappedLhsElements() {
		throw new RuntimeException("Method not implemented");
	}
	
	
	public final static Predicate<Element> all() {
		return new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				return true;
			}
			
		};
	}
	
	
	public final static Predicate<Element> classes() {
		return new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				return e.getRepresentedBy() instanceof ClassElement;
			}
			
		};
	}
	
	
	public Set<Element> unmappedLhsElements(Predicate<Element> p) {
		throw new RuntimeException("Method not implemented");
	}
	
	
	public boolean hasUnmappedRhsElements() {
		throw new RuntimeException("Method not implemented");
	}
	
	
	public Set<Element> unmappedRhsElements(Predicate<Element> p) {
		throw new RuntimeException("Method not implemented");
	}
	
	
	public boolean isComplete() {
		throw new RuntimeException("Method not implemented");
	}
}