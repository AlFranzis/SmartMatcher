package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class QueryByExample  {
	private PSOMapping mapping;
	
	
	public static QueryByExample instance(PSOMapping m) {
		return new QueryByExample(m);
	}
	
	
	protected QueryByExample(PSOMapping m) {
		this.mapping = m;
	}
	
	
	public <T extends Correspondence> Set<T> descendants(T example, 
										Class<T> ctype, boolean flatEquals) {
		return Collections.unmodifiableSet(
			new HashSet<T>(
					Collections2.filter(mapping.descendents(ctype), 
										equals(example, flatEquals))));
	}
	
	
	public <T extends Context> Set<T> contexts(T example, Class<T> ctype, 
														boolean flatEquals) {
		return Collections.unmodifiableSet(
			new HashSet<T>(
					Collections2.filter(mapping.contexts(ctype), 
							cxtEquals(example, flatEquals))
					));
	}
	
	
	protected Predicate<Correspondence> equals(final Correspondence example, 
												final boolean flatEquals) {
		return new Predicate<Correspondence>() {
			@Override
			public boolean apply(Correspondence c) {
				if(!flatEquals)
					return c.equals(example);
				else 
					return c.flatEquals(example);
			}	
		};
	}
	
	
	protected Predicate<Context> cxtEquals(final Context example, 
											final boolean flatEquals) {
		return new Predicate<Context>() {
			@Override
			public boolean apply(Context cxt) {
				if(!flatEquals)
					return cxt.equals(example);
				else 
					return cxt.flatEquals(example);
			}	
		};
	}
	
}
