package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.flatEqual;
import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class PSOMapping implements Context, Correspondence {
	protected Set<Correspondence> children;
	
	
	public PSOMapping() {
		// children = new HashSet<Correspondence>();
		
		// use Array-based set implementation as children
		// might be modified -> HashSet-Implementation may
		// not find modified children anymore!
		children = new CopyOnWriteArraySet<Correspondence>();
	}
	
	
	public PSOMapping(Set<Correspondence> children) {
		// this.children = new HashSet<Correspondence>(children);
		this.children = new CopyOnWriteArraySet<Correspondence>(children);
		
	}
	
	
//	public Multimap<Context,Correspondence> partition() {
//		Multimap<Context,Correspondence> contextMap = 
//							new HashMultimap<Context,Correspondence>();
//		for(Correspondence c : get()) {
//			contextMap.put(c.getContext(), c);
//		}
//		return contextMap;
//	}
	
	
	public Set<Context> contexts() {
		Set<Context> cxts = new HashSet<Context>();
		for(Correspondence c : descendents()) {
			Context cxt = c.getContext();
			if(!cxts.contains(cxt))
				cxts.add(cxt);
		}
		return Collections.unmodifiableSet(cxts);
	}
	
	
	public <T extends Context> Set<T> contexts(Class<T> ctype) {
		Set<T> cxts = new HashSet<T>();
		for(Context cxt : contexts()) {
			if(ctype.isInstance(cxt)) {
				@SuppressWarnings("unchecked")
				T tcxt = (T) cxt;
				cxts.add(tcxt);
			}
				
		}
		return Collections.unmodifiableSet(cxts);
	}
	
	
//	public <T extends Correspondence> T random(Class<T> ctype) {
//		throw new IllegalArgumentException("Not implemented");
//	}
	
	
//	public Set<Correspondence> get() {
//		throw new IllegalArgumentException("Not implemented");
//	}
	
	
//	public PSOMapping remove(Collection<? extends Correspondence> c) {
//		return remove(c.toArray(new Correspondence[0]));
//	}
//	
//	
//	public PSOMapping remove(Correspondence c) {
//		return remove(new Correspondence[]{c});
//	}
//	
//	
//	public PSOMapping remove(Correspondence... c) {
//		return clone(Arrays.asList(c));
//	}
	
	//TODO implementation is just a temp hack
	public boolean remove(Correspondence c) {
		if(descendents().contains(c)) {
			children.remove(c);
			c.destroy();
			return true;
		} else
			return false;
	}
	
	
	
//	public PSOMapping add(Collection<Correspondence> c) {
//		return add(c.toArray(new Correspondence[0]));
//	}
//	
//	
//	public PSOMapping add(Correspondence c) {
//		return add(new Correspondence[]{c});
//	}
//	
//	
//	public PSOMapping add(Correspondence... cps) {
//		PSOMapping clone = clone();
//		for(Correspondence c : cps) {
//			clone.children.add(c);
//		}
//		
//		return clone;
//	}
	
	public boolean add(Correspondence c) {
		return this.children.add(c);
	}
	
	
//	protected PSOMapping clone(Collection<Correspondence> without) {
//		PSOMapping clone = new PSOMapping();
//		Set<Correspondence> clonedChildren = new HashSet<Correspondence>();
//		for( Correspondence child : Collections2.filter(children, not(in(without))) ) {
//			Correspondence clonedChild = child.clone(clone);
//			clonedChildren.add(clonedChild);
//		}
//		clone.children = clonedChildren;
//		return clone;
//	}
//	
//	
//	public Correspondence clone(Context context, 
//			Map<Correspondence, Correspondence> cloneMap, 
//			Predicate<Correspondence> p) {
//		if(!p.apply(this)) throw new IllegalArgumentException("Predicate filters this node");
//		if(cloneMap.containsKey(this)) throw new IllegalArgumentException("Node already cloned");
//		
//		PSOMapping clone = new PSOMapping();
//		Set<Correspondence> clonedChildren = new HashSet<Correspondence>();
//		for(Correspondence child : children) {
//			if(p.apply(child)) {
//				// child has not been cloned before
//				if(!cloneMap.containsKey(child)) {	
//					Correspondence clonedChild = child.clone(clone, cloneMap, p);
//					clonedChildren.add(clonedChild);
//				// child has already been cloned
//				} else {	
//					clonedChildren.add(cloneMap.get(child));
//				}
//			}
//			
//		}
//		clone.children = clonedChildren;
//		cloneMap.put(this, clone);
//		return clone;
//	}
//	
//	
//	@Override
//	public Correspondence clone(Context context) {
//		// PSOMapping has no context
//		return clone();
//	}
//	
//	
//	public PSOMapping clone() {
//		return clone(Collections.<Correspondence>emptyList());
//	}
	
	
	@Override
	public List<Correspondence> asList() {
		return Arrays.<Correspondence>asList(this);
	}
	
	
	@Override
	public boolean contains(Correspondence c) {
		return asList().contains(c);
	}


	@Override
	public Context getContext() {
		return EmptyContext.getInstance();
	}
	
	
	public void destroy() {
		for(Correspondence child : children) {
			child.destroy();
		}
	}
	
	
	@Override
	public T2<Collection<Element>, Collection<Element>> elements() {		
		Collection<Element> lhsElems = new ArrayList<Element>();
		Collection<Element> rhsElems = new ArrayList<Element>();
		//TODO probably wrong: children or descendents ?!
		for(Correspondence c : children) {
			T2<Collection<Element>, Collection<Element>> es = c.elements();
			lhsElems.addAll(es.e0);
			rhsElems.addAll(es.e1);
		}
		
		return t(
				lhsElems,
				rhsElems
			);
	}


	@Override
	public Set<Correspondence> descendents() {
		Set<Correspondence> ds = new HashSet<Correspondence>(children);
		for(Correspondence c : children) {
			ds.addAll(c.descendents());	
		}
		return ds;
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
		return dcs;
	}
	
	
	public <T extends Correspondence> Set<T> descendents(
									Predicate<? super Correspondence> p) {
		return Collections.unmodifiableSet(
				new HashSet<T>(
					Collections2.<T>filter((Collection<T>)descendents(), p)
				)
				);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOMapping))
			return false;
		
		PSOMapping that = (PSOMapping) other;
		return this.children.equals(that.children);
	}
	
	
	@Override
	public int hashCode() {
		return (7 * PSOMapping.class.hashCode()) 
					+ (13 * children.hashCode());
	}
	
	
	@Override
	public String toString() {
		return "Mapping(" 
				+ Arrays.toString(
					descendents().toArray(new Correspondence[0])
				) + ")";
	}


	@Override
	public boolean flatEquals(Correspondence other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOMapping))
			return false;
		
		PSOMapping that = (PSOMapping) other;
		return flatEqual(children, that.children);
	}
	
	
	@Override
	public boolean flatEquals(Context other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOMapping))
			return false;
		
		PSOMapping that = (PSOMapping) other;
		return flatEqual(children, that.children);
	}
	
	
	@Override
	public int flatHashCode() {
		throw new IllegalArgumentException("Method not implemented");
	}
	
	
	@Override
	public Class<? extends Correspondence> getType() {
		return PSOMapping.class;
	}

}
