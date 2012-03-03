package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public interface Mapping {
	public Set<Bubble<? extends Operator>> getBubbles();
	
	public <T extends Operator> List<Bubble<T>> getBubbles(Class<T> operator);
}
