package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class MappingImpl implements Mapping {
	protected Set<Bubble<? extends Operator>> bubbles = new HashSet<Bubble<? extends Operator>>();
	
	
	@Override
	public Set<Bubble<? extends Operator>> getBubbles() {
		return new HashSet<Bubble<? extends Operator>>(bubbles);
	}
	
	
	@Override
	public <T extends Operator> List<Bubble<T>> getBubbles(Class<T> operator) {
		throw new IllegalArgumentException("Method not implemented");
	}
	
	
	public void addBubble(Bubble<Operator> b) {
		bubbles.add(b);
	}
	
	public void addBubbles(Collection<Bubble<? extends Operator>> bs) {
		for(Bubble<? extends Operator> b : bs)
			bubbles.add(b);
	}
	
	
	public boolean removeBubble(Bubble<? extends Operator> b) {
		return bubbles.remove(b);
	}
	
	
	@Override
	public String toString() {
		return("Mapping == " + Arrays.toString(bubbles.toArray(new Bubble[0])));
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(this == other) return true;
		if(!(other instanceof MappingImpl)) return false;
		return this.bubbles.equals(((MappingImpl) other).bubbles);
	}
	
	
	@Override
	public int hashCode() {
		return bubbles.hashCode();
	}

}
