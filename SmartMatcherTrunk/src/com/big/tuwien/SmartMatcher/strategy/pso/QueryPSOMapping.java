package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class QueryPSOMapping  {
	private PSOMapping mapping;
	
	
	public static QueryPSOMapping instance(PSOMapping m) {
		return new QueryPSOMapping(m);
	}
	
	
	protected QueryPSOMapping(PSOMapping m) {
		this.mapping = m;
	}
	
	
	public Set<Correspondence> descendants(Template t) {
		return Collections.unmodifiableSet(
			new HashSet<Correspondence>(
					Collections2.filter(mapping.descendents(), t)
					));
	}
	
	
	public boolean contains(Template t) {
		for(Correspondence c : mapping.descendents()) {
			if(t.apply(c)) return true;;
		}
		return false;
	}
	
	
	public interface Template extends Predicate<Correspondence> {}
	
}
