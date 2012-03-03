package com.big.tuwien.SmartMatcher.strategy;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class PairManager<T extends Operator> {
	private List<Bubble<T>> bubbles = new Vector<Bubble<T>>();
	private List<Pair<T>> unmarked = new Vector<Pair<T>>();
	
	
	public PairManager() {}
	
	
	public void addBubble(Bubble<T> b) {
		buildPairs(b);
		this.bubbles.add(b);
	}
	
	
	public void removeBubble(Bubble<T> b) {
		bubbles.remove(b);
		List<Pair<T>> cleanedUnmarked = new Vector<Pair<T>>();
		for(Pair<T> p : unmarked) {
			if(!p.getFirst().equals(b) && !p.getSecond().equals(b))
				cleanedUnmarked.add(p);
		}
		unmarked = cleanedUnmarked;
	}
	
	
	private void buildPairs(Bubble<T> b1) {
		for(Bubble<T> b2 : bubbles) {
			Pair<T> p = new Pair<T>(b1, b2);
			this.unmarked.add(p);
		}
	}
	
	
	public boolean containsPairs() {
		return !unmarked.isEmpty();
	}
	
	
	public Pair<T> nextPair() {
		if(!containsPairs()) throw new IllegalStateException("No unmarked pairs");
		return this.unmarked.remove(0);
	}
}
