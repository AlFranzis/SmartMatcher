package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.AbstractBubble;

public class C2RBubble extends AbstractBubble<C2R> {
	protected C2RConfiguration configuration;
	protected C2CBubble context1;
	protected C2CBubble context2;
	
	
	public void setConfiguration(C2RConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	public void setContext1(C2CBubble context1) {
		this.context1 = context1;
		((C2RConfiguration) configuration).setContext1((C2CConfiguration)context1.getConfiguration());
	}
	
	
	public void setContext2(C2CBubble context2) {
		this.context2 = context2;
		((C2RConfiguration) configuration).setContext2((C2CConfiguration)context2.getConfiguration());
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
	
	
	public C2RConfiguration getAppliedConfiguration() {
		return null;
	}
	
	
	
	
	public C2RConfiguration getConfiguration() {
		return this.configuration;
	}
	
	
	public String getOperatorName() {
		return C2R.NAME;
	}
	
	
	public String toString() {
		return "C2R-Bubble :: id = " + id + ", state = " + state + ", c2c-context1 = " + this.context1.getId() 
		+ ", c2c-context2 = " + this.context2.getId() + ", configuration = " + configuration;
	}
}
