package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.Collection;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public abstract class AbstractTransitionQuery2 implements TransitionQuery<Set<Element>> {
	protected DOMView domView;
	
	@Override
	public void setDOMView(DOMView domView) {
		this.domView = domView;
		
	}

	@Override
	public abstract Collection<Set<Element>> transition(Context context) throws TransitionException;

}
