package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.big.tuwien.SmartMatcher.strategy.pso.PredicatesUtil.isMapped2;
import static com.google.common.base.Predicates.not;
import static java.util.Collections.shuffle;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.collect.Collections2;

public class A2AMutator implements Mutator {
	private static Logger logger = Logger.getLogger(A2AMutator.class);
	
	private boolean lhs;
	
	
	public A2AMutator(boolean lhs) {
		this.lhs = lhs;
	}
	
	
	public PSOMapping mutate(PSOMapping m) {
		T2<PSOA2A,Element> picked = pick(m, lhs);
		
		if(picked == null) {
			logger.info("No A2A mutation possible for given mapping, m: " + m);
			return m;
		}
		
		PSOA2A pickedA2A = picked.e0;
		Element pickedAtt = picked.e1;
		PSOC2C pickedC2C = pickedA2A.getContext().op1;
		
		// clone old PSOMapping without the picked A2A
		Cloner cloner = new Cloner(m, Cloner.without(pickedA2A));
		PSOMapping newMapping = cloner.getClone();
		PSOC2C pickedC2Cclone = (PSOC2C) cloner
									.getCloneMap().get(pickedC2C);
		C1<PSOC2C> newCxt = C1.c(pickedC2Cclone);
		
		PSOA2A mutated;
		if(lhs)
			mutated = new PSOA2A(newCxt, pickedAtt, pickedA2A.rhs());
		else
			mutated = new PSOA2A(newCxt, pickedC2C.lhs(), pickedAtt);
		
		pickedC2Cclone.add(mutated);
		
		return newMapping;
	}
	
	
	protected T2<PSOA2A,Element> pick(PSOMapping m, boolean lhs) {
		List<PSOA2A> a2as = new Vector<PSOA2A>(
				m.descendents(PSOA2A.class)
				);

		if(a2as.isEmpty()) return null;
		
		// randomize
		shuffle(a2as);
		
		for(PSOA2A a2a : a2as) {
			Element unmappedAtt = pick(a2a.getContext().op1, lhs);
			if(unmappedAtt == null) continue;
			
			return Tuples.t(a2a, unmappedAtt);
		}
		
		return null;
	}
	
	
	/*
	 * Randomly picks an unmapped attribute from one of the classes
	 * mapped by the C2C.
	 * @param c2c
	 * @param lhs If true picks the attribute from the LHS class,
	 * if false from the RHS.
	 * @return An unmapped attribute or null if no unmapped
	 * attribute exists.
	 */
	private Element pick(final PSOC2C c2c, boolean lhs) {
		List<AttributeElement> attributes;
		if(lhs)
			attributes = ((ClassElement)c2c.lhs().getRepresentedBy()).
							getAttributes();
		else 
			attributes = ((ClassElement)c2c.rhs().getRepresentedBy()).
							getAttributes();
		
		List<AttributeElement> unmapped = 
						new Vector<AttributeElement>(
							Collections2.filter(
								attributes, 
								not(isMapped2(c2c.descendents(PSOA2A.class)))
							));
	
		if(unmapped.isEmpty()) return null;
		
		Collections.shuffle(unmapped);
		Element pickedAtt = unmapped.get(0).getRepresents();
		
		return pickedAtt;
	}
	
}
