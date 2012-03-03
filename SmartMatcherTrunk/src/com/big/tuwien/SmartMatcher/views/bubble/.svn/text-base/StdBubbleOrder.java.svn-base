package com.big.tuwien.SmartMatcher.views.bubble;

import java.util.Comparator;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;

/**
 * This comparator defines a standard ordering for bubbles contained in a collection.
 * C2CBubbles are defined as the smallest elements (=are placed before all other types of bubbles).
 * All other types of bubbles are greater.
 * @author alex
 *
 */
public class StdBubbleOrder implements Comparator<Bubble<? extends Operator>> {

	
	public StdBubbleOrder() {}
	
	
	public int compare(Bubble<? extends Operator> first, Bubble<? extends Operator> second) {
		if(first instanceof C2CBubble) return -1;
		if(second instanceof C2CBubble) return 1;
		return 0;
	}
	
}
