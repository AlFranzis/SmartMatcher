
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class Insert<T extends Correspondence> extends Merge<T> {
	protected T c;

	public Insert(Side side, T c) {
		super(side);
		this.c = c;
	}
	
	
	@Override
	public String toString() {
		return "Insert(" + side + "," + c + ")";
	}

	
	@Override
	public Class<? extends Correspondence> getType() {
		return c.getType();
	}

}