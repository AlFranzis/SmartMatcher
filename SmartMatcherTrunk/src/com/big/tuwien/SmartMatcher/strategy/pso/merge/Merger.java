package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;

public interface Merger {
	public PSOMapping merge(PSOMapping m1, PSOMapping m2);
}
