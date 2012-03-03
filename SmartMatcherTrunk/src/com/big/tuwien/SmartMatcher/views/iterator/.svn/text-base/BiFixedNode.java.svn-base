package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;


public class BiFixedNode implements NodeIt<Set<Element>> {
	protected Set<Element> current;
	protected Role role;
	protected List<NodeIt<Set<Element>>> dependencies = new Vector<NodeIt<Set<Element>>>();
	
	@Override
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException {}
	
	
	public void setCurrent(Set<Element> current) throws TransitionException {
		this.current = current;
		informDependencies();
	}
	
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	@Override
	public Role getRole() {
		return this.role;
	}
	
	
	@Override
	public boolean hasNext() {
		return false;
	}
	
	
	@Override
	public Set<Element> next() {
		throw new NoSuchElementException("Fixed node iterator has only one element");
	}
	
	
	public Set<Element> current() {
		return this.current;
	}
	
	
	@Override
	public void reset() {}
	
	
	@Override
	public boolean hasCurrent() {
		return this.current != null;
	}
	
	
	public void addDependency(NodeIt<Set<Element>> t) {
		this.dependencies.add(t);
	}
	
	
	protected void informDependencies() throws TransitionException {
		for(NodeIt<Set<Element>> t : dependencies) {
			t.updateContext(this, new SimpleContext2(current));
		}
	}
}
