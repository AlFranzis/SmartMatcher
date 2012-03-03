/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import java.util.List;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public abstract class Picker<S extends Correspondence> {
	public abstract List<Element> pickUnmatchedLHS(Context context);
	
	public abstract List<Element> pickUnmatchedRHS(Context context);
	
	public abstract List<Element> pickUnmatched(Side side, Context context);
}