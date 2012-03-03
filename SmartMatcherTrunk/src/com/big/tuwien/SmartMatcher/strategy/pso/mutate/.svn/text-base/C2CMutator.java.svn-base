package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.google.common.base.Predicates.and;
import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSOA2AGenerator2;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSOR2RGenerator2;

public class C2CMutator implements Mutator {
	private static Logger logger = Logger.getLogger(C2CMutator.class);
	
	
	private PSOA2AGenerator2 a2aGenerator = new PSOA2AGenerator2();
	private PSOR2RGenerator2 r2rGenerator = new PSOR2RGenerator2();
	
	private MetaModel mm;
	private boolean lhs;
	
	
	public C2CMutator(MetaModel mm, boolean lhs) {
		this.mm = mm;
		this.lhs = lhs;
	}
	
	
	public PSOMapping mutate(PSOMapping m) {
		Element pickedClass = pick(mm, m);
		
		if(pickedClass == null) {
			logger.info("No C2C mutation possible as meta-modelcontains " 
					+ "no unmapped class, mm: " + mm);
			return m;
		}
		
		PSOC2C pickedC2C = pick(m);
		
		if(pickedC2C == null) {
			logger.info("No C2C mutation possible as mappings " 
					+ "contains no C2Cs, m: " + m);
			return m;
		}
		
		// clone old PSOMapping without the picked C2C
		Cloner cloner = new Cloner(m, Cloner.without(pickedC2C));
		PSOMapping newMapping = cloner.getClone();
		
		PSOC2C mutated;
		if(lhs)
			mutated = new PSOC2C(newMapping, pickedClass, pickedC2C.rhs());
		else
			mutated = new PSOC2C(newMapping, pickedC2C.lhs(), pickedClass);
		
		newMapping.add(mutated);
		
		//create A2As
		a2aGenerator.generate(mutated);
		
		// check for compatible R2Rs
		Set<PSOR2R> compatibles = compatibles(pickedC2C, mutated);
		
		// add clones of compatible R2Rs to swapped C2C pair
		for(PSOR2R r2r : compatibles) {
			PSOC2C partnerC2C = cxtPartner(r2r, pickedC2C);
			PSOC2C partnerC2Cclone = (PSOC2C) cloner
										.getCloneMap().get(partnerC2C);
			C2<PSOC2C> newCxt = C2.c(partnerC2Cclone, mutated);
			PSOR2R clone = new PSOR2R(newCxt, r2r.lhs(), r2r.rhs());
			mutated.add(clone);
		}

		// create external R2Rs
		Set<PSOC2C> otherC2Cs = newMapping.descendents(
									and(
										Cloner.without(mutated), 
										Cloner.type(PSOC2C.class)
									));
		r2rGenerator.generate(Arrays.asList(mutated), new ArrayList<PSOC2C>(otherC2Cs));
		
		return newMapping;
	}
	
	
	/**
	 * Randomly picks a C2C from a mapping.
	 * @param m
	 * @return
	 */
	protected PSOC2C pick(PSOMapping m) {
		List<PSOC2C> c2cs = new Vector<PSOC2C>(m.descendents(PSOC2C.class));
		
		if(c2cs.isEmpty()) return null;
		
		shuffle(c2cs);
		PSOC2C picked = c2cs.get(0);
		return picked;
	}
	
	
	/**
	 * Randomly picks an unmapped class from the meta-model.
	 * @param mm
	 * @param m
	 * @return
	 */
	protected Element pick(MetaModel mm, PSOMapping m) {
		List<Element> unmappedLHSClasses = 
			new Vector<Element>(
					SwapperUtil.pickUnmatched(m, 
					mm, ClassElement.class)
				);

		if(unmappedLHSClasses.isEmpty()) 
			return null;

		shuffle(unmappedLHSClasses);
		Element pickedClass = unmappedLHSClasses.get(0);
		return pickedClass;
	}
	
	
	/*
	 * Returns all R2Rs in context of oldC2C that are compatible
	 * (= can be used in context) with newC2C
	 */
	private Set<PSOR2R> compatibles(PSOC2C oldC2C, PSOC2C newC2C) {
		Set<PSOR2R> r2rs = oldC2C.descendents(PSOR2R.class);
		Set<PSOR2R> compatibles = new HashSet<PSOR2R>();
		
		for(PSOR2R r2r : r2rs) {
			PSOC2C c2c1 = cxtPartner(r2r, oldC2C);
			
			if(r2r.compatible(Tuples.t(c2c1, newC2C)))
					compatibles.add(r2r);
			
		}
		
		return r2rs;
	}
	
	
	/*
	 * Returns the context partner of c2c.
	 */
	private PSOC2C cxtPartner(PSOR2R r2r, PSOC2C c2c) {
		List<Correspondence> cxtC2Cs = new Vector<Correspondence>(
												r2r.getContext().asList());
		if(!cxtC2Cs.remove(c2c))
			throw new PSORuntimeException("R2R has invalid C2<C2C> context");
		
		PSOC2C otherC2C = (PSOC2C) cxtC2Cs.get(0);
		
		return otherC2C;
	}
	
		
}
