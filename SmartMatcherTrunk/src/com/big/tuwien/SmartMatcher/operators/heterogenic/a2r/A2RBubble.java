package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.AbstractBubble;

public class A2RBubble extends AbstractBubble<A2R> {
	protected A2RConfiguration configuration;
	protected C2CBubble context1;
	protected C2CBubble context2;
	
	
	public void setConfiguration(A2RConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	public void setContext1(C2CBubble context1) {
		this.context1 = context1;
		((A2RConfiguration) configuration).setContext1((C2CConfiguration)context1.getConfiguration());
	}
	
	
	public void setContext2(C2CBubble context2) {
		this.context2 = context2;
		((A2RConfiguration) configuration).setContext2((C2CConfiguration)context2.getConfiguration());
	}
	
	
	public C2CBubble getContext1() {
		return this.context1;
	}
	
	
	public C2CBubble getContext2() {
		return this.context2;
	}
	
	
	public void apply() {
		
	}
	
	
	public void transform() {
		
	}
	
	
	public boolean isCorrect() {
		return false;
	}
	
	
	public A2RConfiguration getAppliedConfiguration() {
		return null;
	}
	
	
	
	
	public A2RConfiguration getConfiguration() {
		return this.configuration;
	}


	public String getOperatorName() {
		return A2R.NAME;
	}
	
	
	public String toString() {
		return "A2R-Bubble :: id = " + id + ", state = " + state + ", c2c-context1 = " + this.context1.getId() 
		+ ", c2c-context2 = " + this.context2.getId() + ", configuration = " + configuration;
	}
}
