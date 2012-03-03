package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class that allows easy building of {@link Map} literals.
 * @author alex
 *
 */
public class Literals {
	
	public static <S, T> MapBuilder<S, T> map(S key, T value) {
		return new MapBuilder<S, T>().map(key, value);
	}

	public static class MapBuilder<S, T> extends HashMap<S, T> {
		private static final long serialVersionUID = 873854358L;

		
		public MapBuilder() {}

		
		public MapBuilder<S, T> map(S key, T value) {
			put(key, value);
			return this;
		}
		
	}
}
