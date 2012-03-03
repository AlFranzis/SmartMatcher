package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.Checker;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;

public class Helpers {
	
	/**
	 * Consistency check if mapping is complete.
	 * @param m
	 */
	public static void check(PSOMapping m, MetaModel lhsMM, MetaModel rhsMM) {
		if(!Checker.isComplete(m, lhsMM, rhsMM))
			throw new PSORuntimeException(
					"Incomplete mapping " + m);
	}
}
