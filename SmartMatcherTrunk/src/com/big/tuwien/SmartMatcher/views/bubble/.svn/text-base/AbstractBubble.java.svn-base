package com.big.tuwien.SmartMatcher.views.bubble;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;

public abstract class AbstractBubble<T extends Operator> implements Bubble<T> {
	protected int id = -1;
	protected Configuration<T> configuration;
	// protected Bubble<T> context;
	protected STATE state = STATE.empty;
	protected List<BubbleListener> listeners = new Vector<BubbleListener>();
	protected boolean inputBubble = false;
	
	protected Set<Bubble<? extends Operator>> cxt;
	
	
	public void setInputBubble(boolean isInput) {
		this.inputBubble = isInput;
	}
	
	public boolean isInputBubble() {
		return this.inputBubble;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	public void addListener(BubbleListener l) {
		if(!this.listeners.contains(l)) this.listeners.add(l);	
	}

	
	public abstract void apply();

	
	
	public Configuration<T> getAppliedConfiguration() {
		return null;
	}

	
	public Configuration<T> getConfiguration() {
		return this.configuration;
	}

	
	public abstract String getOperatorName();

	
	public STATE getState() {
		return this.state;
	}

	
	public abstract boolean isCorrect();

	
	public void removeListener(BubbleListener l) {
		this.listeners.remove(l);	
	}

	
	public void setConfiguration(Configuration<T> configuration) {
		this.configuration = configuration;
		
	}

	
	public void setState(STATE state) {
		this.state = state;
		informListeners(MappingEvent.BUBBLE_STATE_CHANGED);
	}

	
	public abstract void transform();
	
	
	/*
	 * Informs all listeners about an event
	 */
	protected void informListeners(int eventType) {
		for(BubbleListener l : listeners) {
			l.bubbleEvent(eventType, this);
		}
	}

	
	public int hashCode() {
		return 657465 * AbstractBubble.class.hashCode() + 
		(this.configuration == null ? 879345 : this.configuration.hashCode()) + 
		(this.getOperatorName() == null ? 2364589 : this.getOperatorName().hashCode()); 
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		
		if(!(other instanceof AbstractBubble)) return false;
		AbstractBubble<? extends Operator> that = (AbstractBubble<? extends Operator>) other;
		return this.getOperatorName().equals(that.getOperatorName())
			&& this.getConfiguration().equals(that.getConfiguration());
	}
	
	
	public String toString() {
		return "Bubble :: id = " + id + ", state = " + state + ", configuration = " + configuration;
	}
	
	
	public Set<Bubble<? extends Operator>> getCxt() {
		return cxt;
	}
	
	
	public void setCxt(Set<Bubble<? extends Operator>> cxt) {
		this.cxt = cxt;
	}

}
