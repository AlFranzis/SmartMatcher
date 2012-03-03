package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.AbstractBubble;

public class A2ABubble extends AbstractBubble<A2A> {
	protected C2CBubble context;
	
	
	public A2ABubble() {}
	
	
	public void setContext(C2CBubble context) {
		this.context = context;
		((A2AConfiguration) configuration).setContext((C2CConfiguration)context.getConfiguration());
	}
	
	
	public C2CBubble getContext() {
		return this.context;
	}
	
	
	public void apply() {
		
	}
	
	
	public void transform() {
		
	}
	
	
	public boolean isCorrect() {
		return false;
	}


	public String getOperatorName() {
		return "A2A";
	}
	
	
	public String toString() {
		return "A2A-Bubble :: id = " + id + ", state = " + state + ", c2c-context = " + this.context.getId() + ", configuration = " + configuration;
	}

}
