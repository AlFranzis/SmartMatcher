package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.big.tuwien.SmartMatcher.strategy.pso.PredicatesUtil.isMapped;
import static com.google.common.base.Predicates.not;
import static java.util.Collections.shuffle;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.collect.Collections2;

public class R2RMutator implements Mutator {
	private static Logger logger = Logger.getLogger(R2RMutator.class);
	
	
	private boolean lhs;
	
	
	public R2RMutator(boolean lhs) {
		this.lhs = lhs;
	}
	
	
	public PSOMapping mutate(PSOMapping m) {
		T2<PSOR2R,Element> picked = pick(m, lhs);
		
		if(picked == null) {
			logger.info("No R2R mutation possible for given mapping, m: " + m);
			return m;
		}
		
		PSOR2R pickedR2R = picked.e0;
		Element pickedRef = picked.e1;
		C2<PSOC2C> pickedC2Cs = pickedR2R.getContext();
		
		// clone old PSOMapping without the picked R2R
		Cloner cloner = new Cloner(m, Cloner.without(pickedR2R));
		PSOMapping newMapping = cloner.getClone();
		
		Map<Correspondence,Correspondence> cloneMap = cloner.getCloneMap();
		
		PSOC2C pickedC2C1clone = (PSOC2C) cloneMap.get(pickedC2Cs.op1);
		PSOC2C pickedC2C2clone = (PSOC2C) cloneMap.get(pickedC2Cs.op2);
		
		C2<PSOC2C> newCxt = C2.c(pickedC2C1clone, pickedC2C2clone);
		
		PSOR2R mutated;
		if(lhs)
			mutated = new PSOR2R(newCxt, pickedRef, pickedR2R.rhs());
		else
			mutated = new PSOR2R(newCxt, pickedR2R.lhs(), pickedRef);
		
		pickedC2C1clone.add(mutated);
		pickedC2C2clone.add(mutated);
		
		return newMapping;
	}
	
	
	protected T2<PSOR2R,Element> pick(PSOMapping m, boolean lhs) {
		List<PSOR2R> r2rs = new Vector<PSOR2R>(
				m.descendents(PSOR2R.class)
				);

		if(r2rs.isEmpty()) return null;
		
		// randomize
		shuffle(r2rs);
		
		for(PSOR2R r2r : r2rs) {
			Element unmappedRef = pick(r2r.getContext(), lhs);
			
			if(unmappedRef == null) continue;
			
			return Tuples.t(r2r, unmappedRef);
		}
	
		return null;
	}
	
	
	/*
	 * Randomly picks an unmapped references that belongs to one of the classes
	 * mapped by the C2Cs.
	 * @param c2c
	 * @param lhs If true picks the reference from the LHS classes,
	 * if false from the RHS classes.
	 * @return An unmapped reference or null if no unmapped
	 * reference exists.
	 */
	private Element pick(final C2<PSOC2C> c2c, boolean lhs) {
		List<Element> refs;
		if(lhs)
			refs = SwapperUtil.getReferences(c2c.op1.lhs(), c2c.op2.lhs());
		else 
			refs = SwapperUtil.getReferences(c2c.op1.rhs(), c2c.op2.rhs());
		
		List<Element> unmapped = 
						new Vector<Element>(
							Collections2.filter(
								refs, 
								not(isMapped(c2c.op1.descendents(PSOR2R.class)))
							));
	
		if(unmapped.isEmpty()) return null;
		
		Collections.shuffle(unmapped);
		Element pickedRef = unmapped.get(0);
		
		return pickedRef;
	}
		
}
