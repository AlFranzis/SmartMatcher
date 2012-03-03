package com.big.tuwien.SmartMatcher.operators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;



public class FilteredIterator<T> implements Iterator<T>{
	private Iterator<T> it;
	private Set<T> blacklisted = new HashSet<T>();
	private T nextConfig;
	private boolean hasNext = false;
	
	
	public FilteredIterator(Iterator<T> it, Collection<T> blacklisted) {
		this.it = it;
		this.blacklisted.addAll(blacklisted);
		checkForNextConfig();
	}
	
	
	private void checkForNextConfig() {
		boolean leave = false;
		while(!leave) {
			if(it.hasNext()) {
				T _config = it.next();
				if(blacklisted.contains(_config)) {
					continue;
				} else {
					this.nextConfig = _config;
					this.hasNext = true;
					leave = true;
				}
			} else {
				this.nextConfig = null;
				this.hasNext = false;
				leave = true;
			}
		}
	}
	
	
	public boolean hasNext() {
		return this.hasNext;
	}

	
	public T next() {
		if(!this.hasNext) throw new NoSuchElementException("Iterator has no more elements");
		
		T temp = this.nextConfig;
		checkForNextConfig();
		return temp;
	}

	
	public void remove() {
		throw new UnsupportedOperationException("Iterator does not support remove()");
	}

}
