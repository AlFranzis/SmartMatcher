package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.AbstractBubble;

public class A2CBubble extends AbstractBubble<A2C> {
	protected C2CBubble context;
	
	
	public void apply() {
		
	}
	
	
	public void setContext(C2CBubble context) {
		this.context = context;
		((A2CConfiguration) configuration).setContext((C2CConfiguration)context.getConfiguration());
	}
	
	
	public C2CBubble getContext() {
		return this.context;
	}
	
	public void transform() {
		
	}
	
	
	public boolean isCorrect() {
		return false;
	}


	public String getOperatorName() {
		return A2C.NAME;
	}
	
	
	public String toString() {
		return "A2C-Bubble :: id = " + id + ", state = " + state + ", c2c-context = " + this.context.getId() + ", configuration = " + configuration;
	}
	
}
