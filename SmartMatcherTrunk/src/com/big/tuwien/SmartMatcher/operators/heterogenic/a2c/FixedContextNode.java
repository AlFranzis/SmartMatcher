package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;

public class FixedContextNode implements NodeIt<C2CConfiguration> {
	protected C2CConfiguration current;
	protected Role role;
	protected List<NodeIt<Element>> dependencies = new Vector<NodeIt<Element>>();
	
	
	@Override
	public C2CConfiguration current() {
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
	public C2CConfiguration next() {
		throw new NoSuchElementException("Fixed node iterator has only one element");
	}

	
	@Override
	public void reset() {}

	
	@Override
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException {}
	
	
	public void setCurrent(C2CConfiguration current) throws TransitionException {
		this.current = current;
		informDependencies();
	}
	
	
	public void addDependency(NodeIt<Element> t) {
		this.dependencies.add(t);
	}
	
	
	protected void informDependencies() throws TransitionException {
		for(NodeIt<Element> t : dependencies) {
			t.updateContext(this, new C2CContext(current));
		}
	}

}
