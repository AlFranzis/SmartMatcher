package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Helper class that allows easy building of {@link Map} literals.
 * @author alex
 *
 */
public class Sets {
	
	
	public static <S> SetBuilder<S> set(S m) {
		return new SetBuilder<S>().adds(m);
	}
	
	
	public static <S> SetBuilder<S> set(Set<S> set) {
		return new SetBuilder<S>().adds(set);
	}
	

	public static class SetBuilder<S> extends HashSet<S> {
		private static final long serialVersionUID = 873854358L;

		
		public SetBuilder() {}

		
		public SetBuilder<S> adds(S m) {
			add(m);
			return this;
		}
		
		
		public SetBuilder<S> adds(Set<S> set) {
			addAll(set);
			return this;
		}
		
		
		public SetBuilder<S> intersect(Set<S> set) {
			retainAll(set);
			return this;
		}
		
		
		public SetBuilder<S> remove(Set<S> set) {
			removeAll(set);
			return this;
		}
		
	}
}
