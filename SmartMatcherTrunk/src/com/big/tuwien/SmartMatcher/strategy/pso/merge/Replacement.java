/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static java.util.Collections.disjoint;

import java.util.Collection;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class Replacement<T extends Correspondence> 
						extends Merge<T> {
	protected T from;
	protected T to;

	public Replacement(Side side, T o, T n) {
		super(side);
		
		if(o.equals(n)) 
			throw new PSORuntimeException("No valid replacement as " +
					"old and new correspondence are equal");
		
		Collection<Element> oLhs = o.elements().e0;
		Collection<Element> oRhs = o.elements().e1;
		Collection<Element> nLhs = o.elements().e0;
		Collection<Element> nRhs = o.elements().e1;
		
		if(disjoint(oLhs, nLhs) && disjoint(oRhs, nRhs))
			throw new PSORuntimeException("No valid replacement as " +
					"old and new correspondence are disjoint");
		
		this.from = o;
		this.to = n;
	}
	
	
	@Override
	public String toString() {
		return "Replacement(" + side + "," + from + "," + to + ")";
	}
	
	
	@Override
	public Class<? extends Correspondence> getType() {
		return from.getType();
	}
	
}