package com.big.tuwien.SmartMatcher.views.iterator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public abstract class AbstractNodeIt implements NodeIt<Element> {
	private static Logger logger = Logger.getLogger(AbstractNodeIt.class);
	
	protected Map<NodeIt<Element>,Context> contexts = new HashMap<NodeIt<Element>,Context>();
	protected DOMView domView;
	protected Iterator<Element> it;
	protected Collection<Element> elements;
	protected Element current;
	
	protected List<NodeIt<Element>> dependencies = new Vector<NodeIt<Element>>();
	
	
	public AbstractNodeIt() {}
	
	
	public AbstractNodeIt(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public <R> void updateContext(NodeIt<R> t, Context context) throws TransitionException {
		logger.debug("UpdateContextNotification from NodeIt in role: " + t.getRole() + " with context: " + context);
		// cast, generics problem
		this.contexts.put((NodeIt<Element>) t, context);
		eval();
	}
	
	
	public Element current() {
		return this.current;
	}
	
	
	public boolean hasCurrent() {
		return current != null;
	}
	
	
	public Element next() {
		try {
			this.current = it.next();
			informDependencies();
			return this.current;
		} catch (TransitionException e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	public boolean hasNext() {
		return it.hasNext();
	}
	
	
	public void reset() {
		this.it = this.elements.iterator();
		
		if(it.hasNext()) {
			this.current = it.next();
		} else {
			this.current = null;
		}
		try {
			informDependencies();
		} catch (TransitionException e) {
			throw new IllegalStateException(e);
		}
	}

	
	protected void eval() throws TransitionException {
		try {
			Collection<Element> elements = new Vector<Element>();
			boolean first = true;
			for(NodeIt<Element> t : contexts.keySet()) {
				TransitionQuery<Element> q = getTransitionQuery(t);
				q.setDOMView(this.domView);
				
				Collection<Element> _elements = q.transition(contexts.get(t));
				if(!first) {
					// intersect
					_elements.retainAll(elements);
				}
				first = false;
				elements = _elements;
			}
			
			this.elements = elements;
			this.it = this.elements.iterator();
			
			if(it.hasNext()) {
				this.current = it.next();
			} else {
				this.current = null;
			}
			
			informDependencies();
			
		} catch (Exception e) {
			throw new TransitionException("Transition failed", e);
		}
	}
	
	
	public void addDependency(NodeIt<Element> t) {
		this.dependencies.add(t);
	}
	
	
	protected void informDependencies() throws TransitionException {
		for(NodeIt<Element> t : dependencies) {
			t.updateContext(this,new SimpleContext(current));
		}
	}
	
	
	protected abstract TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent) throws TransitionException;
}
