package com.big.tuwien.SmartMatcher.strategy;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class Pair<S extends Operator> {
	private Bubble<S> first;
	private Bubble<S> second;
	
	
	public Pair(Bubble<S> first, Bubble<S> second) {
		this.first = first;
		this.second = second;
	}
	
	
	public Bubble<S> getFirst() {
		return this.first;
	}
	
	
	public Bubble<S> getSecond() {
		return this.second;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || !(other instanceof Pair)) return false;
		
		Pair<?> that = (Pair<?>) other;
		return this.first.equals(that.first) && this.second.equals(that.second);
	}
	
	
	public void reverse() {
		Bubble<S> tmp = first;
		first = second;
		second = tmp;
	}
	
	
	public List<Bubble<S>> toList() {
		List<Bubble<S>> l = new Vector<Bubble<S>>();
		l.add(first);
		l.add(second);
		return l;
		
	}
	
	
	public int hashCode() {
		return this.getClass().hashCode() + 54654 * this.first.hashCode() 
				+  6472 * this.second.hashCode();
	}
	
	public String toString() {
		return "Pair :: first : " + first + ", second : " + second; 
	}
}