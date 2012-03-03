package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.strategy.C2CPair;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext2;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;

public class FixedContextPairNode implements NodeIt<C2CPair> {
	protected C2CPair current;
	protected Role role;
	protected List<NodeIt<?>> dependencies = new Vector<NodeIt<?>>();
	
	
	@Override
	public C2CPair current() {
		return current;
	}

	
	@Override
	public Role getRole() {
		return new Role("ContextC2C");
	}

	
	@Override
	public boolean hasCurrent() {
		return true;
	}

	
	@Override
	public boolean hasNext() {
		return false;
	}

	
	@Override
	public C2CPair next() {
		throw new NoSuchElementException("Fixed node iterator has only one element");
	}

	
	@Override
	public void reset() {}

	
	@Override
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException {}
	
	
	public void setCurrent(C2CPair current) throws TransitionException {
		this.current = current;
		informDependencies();
	}
	
	
	public void addDependency(NodeIt<?> t) {
		this.dependencies.add(t);
	}
	
	
	protected void informDependencies() throws TransitionException {
		for(NodeIt<?> t : dependencies) {
			t.updateContext(this, new C2CContext2(current.getFirst(), current.getSecond()));
		}
	}

}
