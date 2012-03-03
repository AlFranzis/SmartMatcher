package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class R2RSwapper extends AbstractSwapper {
	private static Logger logger = Logger.getLogger(R2RSwapper.class);
	
	
	public PSOMapping swap(PSOMapping m) {
		
		T2<PSOR2R,PSOR2R> picked = pick(m);
		
		if(picked == null) {
			logger.info("No R2R swapping possible for given mapping, m: " + m);
			return m;
		}
		
		// clone old PSOMapping without the picked R2Rs
		Cloner cloner = new Cloner(m, 
						Cloner.without(picked.e0, picked.e1));
		
		PSOMapping newMapping = cloner.getClone();
	
		Correspondence cxt1Clone = cloner.getCloneMap().
							get(picked.e0.getContext().op1);
		Correspondence cxt2Clone = cloner.getCloneMap().
							get(picked.e0.getContext().op2);
		
		PSOC2C cxtC2C1 = (PSOC2C) cxt1Clone;
		PSOC2C cxtC2C2 = (PSOC2C) cxt2Clone;
		
		C2<PSOC2C> cxtClone = C2.c(cxtC2C1, cxtC2C2);
		
		T2<PSOR2R,PSOR2R> swapped = t(
					new PSOR2R(cxtClone, picked.e0.lhs(), picked.e1.rhs()), 
					new PSOR2R(cxtClone, picked.e1.lhs(), picked.e0.rhs())
				);
		
		cxtC2C1.add(swapped.e0);
		cxtC2C1.add(swapped.e1);
		cxtC2C2.add(swapped.e0);
		cxtC2C2.add(swapped.e1);
		
		return newMapping;
	}
	
	
	public T2<PSOR2R,PSOR2R> pick(PSOMapping m) {
		return SwapperUtil.pick(m, PSOR2R.class);
	}
	
}
