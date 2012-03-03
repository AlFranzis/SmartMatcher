/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;

public abstract class CloneFactory<S extends Correspondence> {
	public abstract S clone(Context cxt, S original);
}