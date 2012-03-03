package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.big.tuwien.SmartMatcher.strategy.pso.PredicatesUtil.isMapped;
import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.getAttributes;
import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;
import static com.google.common.base.Predicates.not;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.collect.Collections2;

public class SwapperUtil {
	
	/**
	 * Picks a random pair with compatible context out of the given mapping.
	 * @param <T>
	 * @param m Mapping to pick the pair from.
	 * @param ctype Expected type of the picked pair members.
	 * @return Random correspondence pair. Null is returned if no pair 
	 * with same context can be found.
	 */
	public static <T extends Correspondence> T2<T,T> pick(PSOMapping m, Class<T> ctype) {
		Set<T> cs = m.descendents(ctype);
		
		List<T> shuffled = new Vector<T>(cs);
		Collections.shuffle(shuffled);
		
		int s = shuffled.size();
		for(int i = 0; i < s; i++) {
			for(int j = i + 1; j < s; j++) {
				T c1 = shuffled.get(i);
				T c2 = shuffled.get(j);
				if(c1.getContext().equals(c2.getContext()))
					return t(c1, c2);
			}
		}
		
		return null;
	}
	
	
	/**
	 * Picks a random sample of correspondences with size n out of the given mapping.
	 * If n is greater than the available number of correspondences, than all available 
	 * correspondences are picked.
	 * @param <T>
	 * @param m Mapping to pick the sample from.
	 * @param ctype Expected type of the picked sample members.
	 * @return Random sample of correspondences.
	 */
	public static <T extends Correspondence> Collection<T> pickN(PSOMapping m, Class<T> ctype, int n) {
		Set<T> cs = m.descendents(ctype);
		
		List<T> shuffled = new Vector<T>(cs);
		Collections.shuffle(shuffled);
		int s = shuffled.size();
		
		Collection<T> picked = new ArrayList<T>();
		for(int i = 0; i < s && i < n; i++) {
			picked.add(shuffled.get(i));
		}
		
		return picked;
	}
	
	
	public static Collection<Element> pickUnmatched(final PSOMapping m, MetaModel mm) {
//		Predicate<Element> isMatched = new Predicate<Element>() {
//			@Override
//			public boolean apply(Element e) {
//				T2<Collection<Element>,Collection<Element>> matched = m.elements();
//				if(matched.e0.contains(e)) return true;
//				if(matched.e1.contains(e)) return true;
//				return false;
//			}
//		};
//		
//		return Collections2.filter(mm.getElements(), not(isMatched));
		return Collections2.filter(mm.getElements(), not(isMapped(m)));
	}
	
	
	public static <T extends EcoreElement> Collection<Element> pickUnmatched(final PSOMapping m, MetaModel mm, final Class<T> ctype) {
		Collection<Element> unmatched = pickUnmatched(m, mm);
		
//		Predicate<Element> isElemType = new Predicate<Element>() {
//			@Override
//			public boolean apply(Element e) {
//				return (e.getRepresentedBy().getClass().equals(ctype)) ? true : false;
//			}
//		};
//		
//		return Collections2.filter(unmatched, isElemType);
		return Collections2.filter(unmatched, PredicatesUtil.isType(ctype));
	}
	
	
	public static Collection<Element> pickUnmatchedAttributes(
									final PSOMapping m, Element cxtClass) {
		Collection<Element> atts = getAttributes(cxtClass);
		return Collections2.filter(atts, not(isMapped(m)));
	}
	
	
	public static Collection<Element> pickUnmatchedRefs(
				final PSOMapping m, Element cxtClass1, Element cxtClass2) {
		Collection<Element> refs = getReferences(cxtClass1, cxtClass2);
		return Collections2.filter(refs, not(isMapped(m)));
	}
	
	
	public static Collection<Element> pickUnmatchedClasses(final PSOMapping m,
			MetaModel mm) {
		Collection<Element> classes = mm.getClasses();
		return Collections2.filter(classes, not(isMapped(m)));
	}
	
	
	/**
	 * Returns all references between 2 classes.
	 * @param clazz1
	 * @param clazz2
	 * @return
	 */
	public static List<Element> getReferences(Element clazz1, Element clazz2) {
		List<Element> refs = new Vector<Element>();
		
		List<ReferenceElement> clazz1Refs = ((ClassElement) clazz1.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz1Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz2))
				refs.add(ref.getRepresents());
		}
		
		List<ReferenceElement> clazz2Refs = ((ClassElement) clazz2.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz2Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz1))
				refs.add(ref.getRepresents());
		}
		
		return refs;
	}
}
