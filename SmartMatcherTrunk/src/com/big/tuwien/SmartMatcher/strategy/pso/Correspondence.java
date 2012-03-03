package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Collection;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;


public interface Correspondence {
	
	public Set<Correspondence> descendents();
	
	
	public <T extends Correspondence> Set<T> descendents(Class<T> ctype);
	
	
	public Context getContext();
	
	
	public void destroy();
	
	
	public boolean add(Correspondence c);
	
	
	public boolean remove(Correspondence c);
	
	
	//public Correspondence clone(Context context);
	
	
//	public Correspondence clone(Context context, 
//			Map<Correspondence, Correspondence> cloneMap, 
//			Predicate<Correspondence> p);
	
	
	/**
	 * Returns the LHS and RHS elements of a correspondence.
	 * @return Tuple (T2) that contains the LHS and RHS elements.
	 */
	public T2<Collection<Element>,Collection<Element>> elements();
	
	
	public Class<? extends Correspondence> getType();
	
	
	public boolean flatEquals(Correspondence other);
	
	
	public int flatHashCode();
}
