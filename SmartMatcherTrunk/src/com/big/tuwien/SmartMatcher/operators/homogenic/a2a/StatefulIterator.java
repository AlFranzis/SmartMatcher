package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collection;
import java.util.Iterator;

public class StatefulIterator<T> implements Iterator<T> {
	private Collection<T> elements;
	private Iterator<T> it;
	private T current;
	
	
	public StatefulIterator(Collection<T> elements) {
		this.elements = elements;
		this.it = this.elements.iterator();
	}
	
	
	public boolean hasNext() {
		return it.hasNext();
	}

	
	public T next() {
		T next =  it.next();
		this.current = next;
		return next;
	}
	
	
	public T current() {
		return this.current;
	}
	
	
	public boolean hasCurrent() {
		return this.current != null;
	}

	
	public void reset() {
		this.it = this.elements.iterator();
		this.current = null;
	}
	
	
	public void remove() {
		throw new UnsupportedOperationException("StatfulIterator does not support remove()");
	}

}
