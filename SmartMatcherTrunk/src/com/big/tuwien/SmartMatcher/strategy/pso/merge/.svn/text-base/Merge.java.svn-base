/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public abstract class Merge<T extends Correspondence> {
	protected Side side;
	
	
	public Merge(Side side) {
		this.side = side;
	}
	
	
	public abstract Class<? extends Correspondence> getType();
	
	
	public Side getSide() {
		return side;
	}
}