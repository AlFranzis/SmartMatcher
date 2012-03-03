package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;

public abstract class AbstractSwapper implements Mutator {

	
	@Override
	public PSOMapping mutate(PSOMapping m) {
		return swap(m);
	}
	
	
	public abstract PSOMapping swap(PSOMapping m);

}
