package com.big.tuwien.SmartMatcher.views.bubble;

import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class Level {
	private int id;
	private Set<Bubble<? extends Operator>> bubbles = new HashSet<Bubble<? extends Operator>>();
	private SetMultimap<Element,Bubble<? extends Operator>> elements2bubbles = new HashMultimap<Element, Bubble<? extends Operator>>();
	private SetMultimap<Bubble.STATE,Bubble<? extends Operator>> state2bubbles = new HashMultimap<Bubble.STATE,Bubble<? extends Operator>>();
	
	
	public Level(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * Called if the state of the bubble has changed, etc.
	 */
	public void bubbleEvent(int eventType, Bubble<? extends Operator> b) {
		switch(eventType) {
		// bubble state changed event
		case MappingEvent.BUBBLE_STATE_CHANGED:
			setBubbleState(b);
			break;
		default:
			throw new IllegalArgumentException("Unknown event type: " + eventType);
		}
		
	}
	
	
	/*
	 * Sets all data structures that manage the state of a known bubble;
	 */
	private void setBubbleState(Bubble<? extends Operator> b) {
		Entry<Bubble.STATE,Bubble<? extends Operator>> remove = null;
		for(Entry<Bubble.STATE,Bubble<? extends Operator>> e : this.state2bubbles.entries()) {
			if(e.getValue().equals(b)) {
				remove = e;
				break;
			}
		}
		if(remove != null) state2bubbles.remove(remove.getKey(), remove.getValue());
		
		this.state2bubbles.put(b.getState(), b);
	}
	
	
	public void addBubble(Bubble<? extends Operator> b) {
		if(this.bubbles.contains(b)) {
			throw new IllegalArgumentException("The given bubble is already added: " + b);
		}
		
		this.bubbles.add(b);
		this.state2bubbles.put(b.getState(), b);
		
		// Map<? extends Role,Element> roles2elements = b.getAppliedConfiguration().getRoles();
		// for(Entry<? extends Role, Element> en : roles2elements.entrySet()) {
			// elements2bubbles.put(en.getValue(), b);
		// }
	}
	
	
	public void addBubbles(Set<Bubble<? extends Operator>> bubbles) {
		for(Bubble<? extends Operator> b : bubbles) {
			addBubble(b);
		}
	}
	
	
	public Set<Bubble<? extends Operator>> getBubbles() {
		return this.bubbles;
	}
	
	
	public Set<Bubble<? extends Operator>> getBubblesForElement(Element e) {
		return elements2bubbles.get(e);
	}
	
	
	public Set<Bubble<? extends Operator>> getBubblesForOpInState(String op, Bubble.STATE... states) {
		Set<Bubble<? extends Operator>> bs = new HashSet<Bubble<? extends Operator>>();
		Set<Bubble<? extends Operator>> _bs = getBubblesInState(states);
		for(Bubble<? extends Operator> b : _bs) {
			if(b.getOperatorName().equals(op))
				bs.add(b);
		}
		
		return bs;
	}
	
	
	public <T extends Bubble<? extends Operator>> Set<T> getBubblesForOpInState(Class<T> type, Bubble.STATE... states) {
		Set<T> bs = new HashSet<T>();
		Set<Bubble<? extends Operator>> _bs = getBubblesInState(states);
		for(Bubble<? extends Operator> b : _bs) {
			if(type.isInstance(b)) {
				bs.add((T)b);
			}
		}
		
		return bs;
	}
	
	
	/**
	 * Returns all bubbles that are in one of the given states
	 * @param states
	 * @return
	 */
	public Set<Bubble<? extends Operator>> getBubblesInState(Bubble.STATE... states) {
		Set<Bubble<? extends Operator>> bs = new HashSet<Bubble<? extends Operator>>();
		for(Bubble.STATE s : states) {
			bs.addAll(this.state2bubbles.get(s));
		}
		return bs;
	}
	
	
	public Set<Bubble<? extends Operator>> getBubbles(Constraint<Bubble<? extends Operator>> constraint) {
		return Constraints.constrainedSet(this.bubbles, constraint);
	}
	
	
	
	public boolean removeBubble(Bubble<? extends Operator> b) {
		boolean somethingRemoved = this.bubbles.remove(b);
		
		if(somethingRemoved) {
			for(Entry<Bubble.STATE,Bubble<? extends Operator>> en : this.state2bubbles.entries()) {
				if(b.equals(en.getValue())) {
					this.state2bubbles.remove(en.getKey(), en.getValue());
				}
			}

			for(Entry<Element,Bubble<? extends Operator>> en : this.elements2bubbles.entries()) {
				if(b.equals(en.getValue())) {
					this.elements2bubbles.remove(en.getKey(), en.getValue());
				}
			}
		}
		return somethingRemoved;
	}
	
	
	public String toString() {
		return "Level :: " + "id = " + id + ", bubbles = " + this.bubbles;
	}
	
	
}
