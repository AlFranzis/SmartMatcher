package com.big.tuwien.SmartMatcher.views.bubble;

import com.big.tuwien.SmartMatcher.operators.Operator;


/**
 * Creates instance of Bubbles.
 * Provided by the {@link BubbleView} by calling {@link BubbleView#getBubbleFactory()}.
 * Creating bubbles with a factory ensures that all bubbles inform the bubble view
 * about bubble events.
 * @author alex
 *
 */
public interface BubbleFactory {
	
	public Bubble<? extends Operator> createBubbleInstance(Operator op);
	
	public Bubble<? extends Operator> createBubbleInstance(Class<? extends Operator> operator);
	
}
