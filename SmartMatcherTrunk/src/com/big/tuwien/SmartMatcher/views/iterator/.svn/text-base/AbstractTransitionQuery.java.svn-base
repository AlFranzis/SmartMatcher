package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.Collection;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public abstract class AbstractTransitionQuery implements TransitionQuery<Element> {
	protected DOMView domView;
	
	@Override
	public void setDOMView(DOMView domView) {
		this.domView = domView;
		
	}

	@Override
	public abstract Collection<Element> transition(Context context) throws TransitionException;

}
