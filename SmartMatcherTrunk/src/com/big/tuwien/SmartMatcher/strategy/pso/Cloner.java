package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.google.common.base.Predicate;

public class Cloner {
	private PSOMapping clone;
	private Map<Correspondence,Correspondence> cloneMap = 
		new HashMap<Correspondence, Correspondence>();
	
	
	public final static Predicate<Correspondence> all() { 
		return new Predicate<Correspondence>() {
			@Override
			public boolean apply(Correspondence c) {
				return true;
			}
		};
	}
	
	
	public final static Predicate<Correspondence> without(final Collection<? extends Correspondence> cs) { 
		return new Predicate<Correspondence>() {
			@Override
			public boolean apply(Correspondence c) {
				return !cs.contains(c);
			}
		};
	}
	
	
	public final static Predicate<Correspondence> without(final Correspondence... cs) { 
		return without(Arrays.asList(cs));
	}
	
	
	public final static Predicate<Correspondence> type(final Class<? extends Correspondence> ctype) { 
		return new Predicate<Correspondence>() {
			@Override
			public boolean apply(Correspondence c) {
				return ctype.isInstance(c);
			}
		};
	}
	
	
	public Cloner(PSOMapping m, Predicate<Correspondence> p) {
		clone = clone(m, p);
	}
	
	
	public PSOMapping getClone() {
		return clone;
	}
	
	
	public Map<Correspondence,Correspondence> getCloneMap() {
		return Collections.unmodifiableMap(cloneMap);
	}
	
	
	/**
	 * Clones (= deep copy) a mapping.
	 * @param m Mapping to clone.
	 * @param p Only correspondences that apply to this predicate are cloned
	 * @return A clone of m.
	 */
	protected PSOMapping clone(PSOMapping m, Predicate<Correspondence> p) {		
		List<Correspondence> matched = getMatched(m, p);
		
		PSOMapping mClone = new PSOMapping();
		
		// clone C2Cs
		Set<PSOC2C> c2cs = m.descendents(PSOC2C.class);
		c2cs.retainAll(matched);
		for(PSOC2C c2c : c2cs) {
			PSOC2C clone = new PSOC2C(mClone, c2c.lhs, c2c.rhs);
			mClone.add(clone);
			cloneMap.put(c2c, clone);
		}
		
		// clone A2As
		Set<PSOA2A> a2as = m.descendents(PSOA2A.class);
		a2as.retainAll(matched);
		for(PSOA2A a2a : a2as) {
			Context _clonedContext = getContextClone(a2a.getContext(), cloneMap);
			C1<PSOC2C> clonedContext = (C1<PSOC2C>) _clonedContext;
			PSOA2A clone = new PSOA2A(clonedContext, a2a.lhs, a2a.rhs);
			clonedContext.op1.add(clone);
			cloneMap.put(a2a, clone);
		}
		
		// clone R2Rs
		Set<PSOR2R> r2rs = m.descendents(PSOR2R.class);
		r2rs.retainAll(matched);
		for(PSOR2R r2r : r2rs) {
			Context _clonedContext = getContextClone(r2r.getContext(), cloneMap);
			C2<PSOC2C> clonedContext = (C2<PSOC2C>) _clonedContext;
			PSOR2R clone = new PSOR2R(clonedContext, r2r.lhs, r2r.rhs);
			clonedContext.op1.add(clone);
			clonedContext.op2.add(clone);
			cloneMap.put(r2r, clone);
		}
		
		return mClone;
	}
	
	
	private List<Correspondence> getMatched(PSOMapping m, Predicate<Correspondence> p) {
		List<Correspondence> dcs = new Vector<Correspondence>(m.descendents());
		List<Correspondence> matched = new Vector<Correspondence>();
		match(dcs, matched, p);
		return matched;
	}
	
	
	/*
	 * Fills list 'matched' with all members of candidates that apply for predicate p.
	 * If a candidate applies all it's descendants apply too and hence are added to
	 * 'matched'.
	 */
	private void match(List<Correspondence> candidates, List<Correspondence> matched,
			Predicate<Correspondence> p) {
		if(candidates.isEmpty()) return;
		
		List<Correspondence> newCandidates = new Vector<Correspondence>(candidates);
		Correspondence candidate = newCandidates.remove(0);
				
		if(p.apply(candidate)) {
			matched.add(candidate);
		} else {
			matched.removeAll(candidate.descendents());
			newCandidates.removeAll(candidate.descendents());
		}
		
		match(newCandidates, matched, p);
	}
	
	
	private Context getContextClone(Context cxt, Map<Correspondence,Correspondence> cloneMap) {
		Context cContext;
		List<Correspondence> cxtMembers = cxt.asList();
		switch(cxtMembers.size()) {
		case 1:
			Correspondence c = cloneMap.get(cxtMembers.get(0));
			cContext = C1.c(c);
			break;
		case 2:
			Correspondence c1 = cloneMap.get(cxtMembers.get(0));
			Correspondence c2 = cloneMap.get(cxtMembers.get(1));
			cContext = C2.c(c1, c2);
			break;
		default:
			throw new IllegalArgumentException("Method not implemented " +
					"for context type: " + cxt);
		}
		
		return cContext;
	}
	
}
