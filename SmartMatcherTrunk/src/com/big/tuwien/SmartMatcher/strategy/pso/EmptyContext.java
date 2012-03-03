package com.big.tuwien.SmartMatcher.strategy.pso;

import static java.util.Collections.unmodifiableList;

import java.util.Collections;
import java.util.List;

public class EmptyContext implements Context {
	private static EmptyContext instance;
	
	
	public static EmptyContext getInstance() {
		if(instance == null)
			instance = new EmptyContext();
		
		return instance;
	}

	
	private EmptyContext() {}
	
	
	@Override
	public List<Correspondence> asList() {
		return unmodifiableList(Collections.<Correspondence>emptyList());
	}

	
	@Override
	public boolean contains(Correspondence c) {
		return false;
	}
	
	
	@Override
	public boolean flatEquals(Context other) {
		// Empty context is a singleton
		return this == other;
	}
	
	
	public String toString() {
		return "EmptyContext";
	}

}
