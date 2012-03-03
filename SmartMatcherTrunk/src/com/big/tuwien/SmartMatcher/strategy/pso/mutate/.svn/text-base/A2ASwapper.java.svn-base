package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class A2ASwapper extends AbstractSwapper {
	private static Logger logger = Logger.getLogger(A2ASwapper.class);
	
	
	public PSOMapping swap(PSOMapping m) {
		T2<PSOA2A,PSOA2A> picked = pick(m);
		
		if(picked == null) {
			logger.info("No A2A swapping possible for given mapping, m: " + m);
			return m;
		}
		
		// clone old PSOMapping without the picked A2As
		Cloner cloner = new Cloner(m, 
						Cloner.without(picked.e0, picked.e1));
		
		PSOMapping newMapping = cloner.getClone();
	
		Correspondence cxt1Clone = cloner.getCloneMap().
							get(picked.e0.getContext().op1);
		Correspondence cxt2Clone = cloner.getCloneMap().
							get(picked.e1.getContext().op1);
		
		assert cxt1Clone.equals(cxt2Clone);
		PSOC2C cxtC2C = (PSOC2C) cxt1Clone;
		
		C1<PSOC2C> cxtClone = C1.c(cxtC2C);
		
		T2<PSOA2A,PSOA2A> swapped = t(
					new PSOA2A(cxtClone, picked.e0.lhs(), picked.e1.rhs()), 
					new PSOA2A(cxtClone, picked.e1.lhs(), picked.e0.rhs())
				);
		
		cxtC2C.add(swapped.e0);
		cxtC2C.add(swapped.e1);
		
		return newMapping;
	}
	
	
	public T2<PSOA2A,PSOA2A> pick(PSOMapping m) {
		return SwapperUtil.pick(m, PSOA2A.class);
	}
	
}
