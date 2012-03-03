package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.Collection;

import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public interface TransitionQuery<T> {
	public void setDOMView(DOMView view);
	public Collection<T> transition(Context context) throws TransitionException;
}
