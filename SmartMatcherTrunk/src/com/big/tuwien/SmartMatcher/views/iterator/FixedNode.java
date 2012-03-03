package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;


public class FixedNode implements NodeIt<Element> {
	protected Element current;
	protected Role role;
	protected List<NodeIt<Element>> dependencies = new Vector<NodeIt<Element>>();
	
	@Override
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException {}
	
	
	public void setCurrent(Element current) throws TransitionException {
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
	public Element next() {
		throw new NoSuchElementException("Fixed node iterator has only one element");
	}
	
	
	public Element current() {
		return this.current;
	}
	
	
	@Override
	public void reset() {}
	
	
	@Override
	public boolean hasCurrent() {
		return this.current != null;
	}
	
	
	public void addDependency(NodeIt<Element> t) {
		this.dependencies.add(t);
	}
	
	
	protected void informDependencies() throws TransitionException {
		for(NodeIt<Element> t : dependencies) {
			t.updateContext(this, new SimpleContext(current));
		}
	}
}
