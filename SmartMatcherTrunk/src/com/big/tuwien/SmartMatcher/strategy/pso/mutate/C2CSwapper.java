package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.google.common.base.Predicates.and;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSOA2AGenerator2;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSOR2RGenerator2;

public class C2CSwapper extends AbstractSwapper {
	private static Logger logger = Logger.getLogger(C2CSwapper.class);
	
	private PSOA2AGenerator2 a2aGenerator = new PSOA2AGenerator2();
	private PSOR2RGenerator2 r2rGenerator = new PSOR2RGenerator2();
	
	
	public PSOMapping swap(PSOMapping m) {
		// pick a C2C-pair that will be swapped
		T2<PSOC2C,PSOC2C> _picked = pick(m);
		
		if(_picked == null) {
			logger.info("No C2C swapping possible for given mapping, m: " + m);
			return m;
		}
		
		final C2<PSOC2C> picked = C2.c(_picked);
		
		// clone old PSOMapping without the picked C2Cs
		PSOMapping newMapping = new Cloner(m, Cloner.without(picked.asList()))
										.getClone();
		
		C2<PSOC2C> swapped = C2.c(
					new PSOC2C(newMapping, picked.op1.lhs(), picked.op2.rhs()), 
					new PSOC2C(newMapping, picked.op2.lhs(), picked.op1.rhs())
				);
		newMapping.add(swapped.op1);
		newMapping.add(swapped.op2);
		
		//create A2As
		a2aGenerator.generate(swapped.op1);
		a2aGenerator.generate(swapped.op2);	
		
		// create R2Rs
		Set<PSOR2R> compatible = compatible(picked, swapped);
		
		// add clones of compatible R2Rs to swapped C2C pair
		for(PSOR2R cr2r : compatible) {
			PSOR2R clone = new PSOR2R(swapped, cr2r.lhs(), cr2r.rhs());
			swapped.op1.add(clone);
			swapped.op2.add(clone);
		}
		
		// generate additional internal R2Rs
		r2rGenerator.generateR2Rss(swapped, compatible);
		
		
		// create external R2Rs
		Set<PSOC2C> otherC2Cs = newMapping.descendents(
									and(
										Cloner.without(swapped.asList()), 
										Cloner.type(PSOC2C.class)
									));
		r2rGenerator.generate(swapped.toList(), new ArrayList<PSOC2C>(otherC2Cs));
		
		return newMapping;
	}
	
	
	public T2<PSOC2C,PSOC2C> pick(PSOMapping m) {
		return SwapperUtil.pick(m, PSOC2C.class);
	}
	
	
	private Set<PSOR2R> compatible(C2<PSOC2C> oldC2, C2<PSOC2C> newC2) {
		Set<PSOR2R> common = commonDescendents(oldC2);
		
		Set<PSOR2R> compatible = compatible(newC2, common);
		return compatible;
	}
	
	
	private Set<PSOR2R> commonDescendents(C2<PSOC2C> c2) {
		Set<PSOR2R> descs1 = new HashSet<PSOR2R>(c2.op1.descendents(PSOR2R.class));
		Set<PSOR2R> descs2 = c2.op2.descendents(PSOR2R.class);
		
		// intersect
		descs1.retainAll(descs2);
		
		return descs1;
	}
	
	
	private Set<PSOR2R> compatible(C2<PSOC2C> c2, Set<PSOR2R> r2rs) {
		Set<PSOR2R> compatible = new HashSet<PSOR2R>();
		
		for(PSOR2R r2r : r2rs) {
			if(r2r.compatible(c2)) compatible.add(r2r);
		}
		
		return compatible;
	}
		
}
