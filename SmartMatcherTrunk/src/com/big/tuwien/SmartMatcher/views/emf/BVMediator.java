package com.big.tuwien.SmartMatcher.views.emf;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewListener;
import com.big.tuwien.SmartMatcher.views.emf.EventFilteredBVMediator.EventFilter;

interface BVMediator {

	public void addBubble(Bubble<? extends Operator> b);

	public void changeState(Bubble<?> b, Bubble.STATE state);

	public void addFilter(EventFilter nf);

	public void addListener(BubbleViewListener bvl);

}