package com.big.tuwien.SmartMatcher.strategy.pso;

import static java.util.Collections.disjoint;

import java.util.Collection;

import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.base.Predicate;

/**
 * This class contains utility predicates.
 * @author alex
 *
 */
public class PredicatesUtil {
	
	/**
	 * Returns a predicate that checks for an element if it is mapped by a 
	 * collection of correspondences.
	 * @param cs
	 * @return
	 */
	public final static Predicate<Element> isMapped(final Collection<? extends Correspondence> cs) { 
		return new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				for(Correspondence c : cs) {
					T2<Collection<Element>,Collection<Element>> es = c.elements();
					if((es.e0.contains(e)) || es.e1.contains(e)) {
						return true;
					}
				}
				return false;
			}
		};
	}
	
	
	/**
	 * Returns a predicate that checks for an element if it is mapped by any
	 * of the given correspondences.
	 * @param cs
	 * @return
	 */
	public final static Predicate<EcoreElement> isMapped2(
			final Collection<? extends Correspondence> cs) {
		return new Predicate<EcoreElement>() {
			Predicate<Element> p = isMapped(cs);
			
			@Override
			public boolean apply(EcoreElement e) {
				return p.apply(e.getRepresents());
			}
		};
	}
	
	
	public final static Predicate<Element> isMapped(PSOMapping m) { 
		return isMapped(m.descendents());
	}
	
	
	public final static Predicate<Element> isType(
						final Class<? extends EcoreElement> ctype) {
		Predicate<Element> isElemType = new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				return (e.getRepresentedBy().getClass().equals(ctype)) 
								? true : false;
			}
		};
		return isElemType;
	}
		
	
	/**
	 * Returns a predicate that checks for a correspondence if it is disjoint (= shares
	 * no elements) with any of the given correspondences.
	 * @param cs
	 * @return
	 */
	public final static Predicate<Correspondence> disjointFrom(
			final Collection<? extends Correspondence> cs) { 
		return new Predicate<Correspondence>() {
			@Override
			public boolean apply(Correspondence c) {
				for(Correspondence _c : cs) {
					T2<Collection<Element>,Collection<Element>> _es = _c.elements();
					T2<Collection<Element>,Collection<Element>> es = c.elements();
					if(!disjoint(_es.e0, es.e0) || !disjoint(_es.e1, es.e1)) {
						return false;
					}
				}
				return true;
			}
		};
	}
	
}
