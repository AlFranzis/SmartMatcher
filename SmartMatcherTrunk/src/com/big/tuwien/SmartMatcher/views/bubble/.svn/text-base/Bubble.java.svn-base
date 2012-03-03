package com.big.tuwien.SmartMatcher.views.bubble;

import java.util.Set;

import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;

public interface Bubble<T extends Operator> {
	
	public int getId();
	
	public void setId(int id);
	
	public static enum STATE {
		applied,
		deadEnd,
		eval2FALSE,
		eval2TRUE,
		empty,
		inputBubble
	}
	
	public void setConfiguration(Configuration<T> configuration);
	
	public void apply();

	public void transform();

	public boolean isCorrect();
	
	public Set<Bubble<? extends Operator>> getCxt();
	
	public void setCxt(Set<Bubble<? extends Operator>> cxt);

	public Configuration<T> getAppliedConfiguration();

	public Configuration<T> getConfiguration();
	
	public boolean isInputBubble();
	
	public void setInputBubble(boolean isInput);
	
	public STATE getState();
	
	public void setState(STATE state);
	
	public String getOperatorName();
	
	public void addListener(BubbleListener l);
	
	public void removeListener(BubbleListener l);

}