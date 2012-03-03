package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public abstract class AbstractCorrespondence implements Correspondence {
	protected Set<Correspondence> children = new HashSet<Correspondence>();
	
	protected Context context;
	
	protected Element lhs;
	protected Element rhs;
	
	
	public Element lhs() {
		return lhs;
	}
	
	
	public Element rhs() {
		return rhs;
	}
	
	
	@Override
	public abstract void destroy();

	
	@Override
	public T2<Collection<Element>, Collection<Element>> elements() {
		return t(
				(Collection<Element>)Arrays.asList(lhs),
				(Collection<Element>)Arrays.asList(rhs)
			);
	}

	
	@Override
	public Context getContext() {
		return context;
	}
	
	
	public boolean add(Correspondence c) {
		return children.add(c);
	}
	
	
	public boolean remove(Correspondence c) {
		return children.remove(c);
	}

	
	@Override
	public Set<Correspondence> descendents() {
		Set<Correspondence> dcs = new HashSet<Correspondence>(children);
		for(Correspondence c : children) {
			dcs.addAll(c.descendents());	
		}
		return Collections.unmodifiableSet(dcs);
	}
	
	
	@Override
	public <T extends Correspondence> Set<T> descendents(Class<T> ctype) {
		Set<T> dcs = new HashSet<T>();
		for(Correspondence c : descendents()) {
			if(ctype.isInstance(c)) {
				@SuppressWarnings("unchecked")
				T tc = (T) c;
				dcs.add(tc);
			}
				
		}
		return Collections.unmodifiableSet(dcs);
	}
	
	
	@Override
	public boolean flatEquals(Correspondence other) {
		if(this == other) return true;
		if(other == null || !this.getClass().isInstance(other)) return false;
		
		AbstractCorrespondence that = (AbstractCorrespondence) other;
		
		return lhs.equals(that.lhs) 
			&& rhs.equals(that.rhs);
	}
	
	
	@Override
	public int flatHashCode() {
		return 137   
		+ (19 * lhs.hashCode())
		+ (29 * rhs.hashCode());
	}
	
}
