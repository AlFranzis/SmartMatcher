package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class PSOC2C extends AbstractCorrespondence {
	protected PSOMapping context;

	
	public PSOC2C(PSOMapping context, Set<Correspondence> children, 
						Element lhs, Element rhs) {
		this.children = new HashSet<Correspondence>(children);
		this.context = context;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	
	public PSOC2C(PSOMapping context, Element lhs, Element rhs) {
		this(context, new HashSet<Correspondence>(), lhs, rhs);
	}
	
	
	public PSOMapping getContext() {
		return context;
	}
	
	
	public void setContext(PSOMapping context) {
		this.context = context;
	}
	
	
	public <C extends Context> Multimap<C,Correspondence> descendents(Class<C> contextType) {
		Set<Correspondence> dcs = descendents();
		Multimap<C,Correspondence> contextMap = new HashMultimap<C,Correspondence>();
		for(Correspondence dc : dcs) {
			Context cxt = dc.getContext();
			if(cxt.getClass().equals(contextType)) {
				@SuppressWarnings("unchecked")
				C ct = (C) cxt;
 				contextMap.put(ct, dc);
			}
		}
		return contextMap;
	}
	
	
//	public <T extends Correspondence> T random(Class<T> ctype) {
//		List<T> ds = new LinkedList<T>(descendents(ctype));
//		if(ds.isEmpty()) return null;
//		
//		Collections.shuffle(ds);
//		return ds.get(0);
//	}
	
	
	
	public boolean add(Correspondence child) {
		if(!child.getContext().contains(this))
			throw new IllegalArgumentException("Illegal context of child to add");
		return children.add(child);
	}
	
	
	public void destroy() {
		for(Correspondence child : new Vector<Correspondence>(children)) {
			child.destroy();
		}
		//context.remove(this);
	}
	
	
//	public PSOC2C clone(Context context) {
//		if( context == null || !(context instanceof PSOMapping) )
//			throw new IllegalArgumentException("Invalid context type: " + context.getClass());
//		PSOMapping m = (PSOMapping) context;
//		
//		Set<Correspondence> clonedChildren = new HashSet<Correspondence>();
//		for(Correspondence child : children) {
//			Context cxt = child.getContext();
//			Correspondence clonedChild = child.clone(cxt);
//			clonedChildren.add(clonedChild);
//		}
//		return new PSOC2C(m, clonedChildren, lhs, rhs);
//	}
//	
//	private Context getCloned(Context cxt, Map<Correspondence,Correspondence> cloneMap) {
//		Context cContext;
//		List<Correspondence> cxtMembers = cxt.asList();
//		switch(cxtMembers.size()) {
//		case 1:
//			Correspondence c = cloneMap.get(cxtMembers.get(0));
//			cContext = C1.c(c);
//			break;
//		case 2:
//			Correspondence c1 = cloneMap.get(cxtMembers.get(0));
//			Correspondence c2 = cloneMap.get(cxtMembers.get(1));
//			cContext = C2.c(c1, c2);
//			break;
//		default:
//			throw new IllegalArgumentException("Method not implemented " +
//					"for context type: " + cxt);
//		}
//		
//		return cContext;
//	}
//	
//	//TODO here how to handle not yet cloned context ???
//	public PSOC2C clone(Context context, Map<Correspondence,Correspondence> cloneMap, 
//			Predicate<Correspondence> p) {
//		
//		if( context == null || !(context instanceof PSOMapping) )
//			throw new IllegalArgumentException("Invalid context type: " + context.getClass());
//		PSOMapping m = (PSOMapping) context;
//		
//		if(!p.apply(this)) throw new IllegalArgumentException("Predicate filters this node");
//		if(cloneMap.containsKey(this)) throw new IllegalArgumentException("Node already cloned");
//		
//		PSOC2C clone = new PSOC2C(m, lhs, rhs);
//		Set<Correspondence> clonedChildren = new HashSet<Correspondence>();
//		for(Correspondence child : children) {
//			if(p.apply(child)) {
//				// child has not been cloned before
//				if(!cloneMap.containsKey(child)) {	
//					Context cxt = child.getContext();
//					if(!cloneMap.keySet().containsAll(cxt.asList()))
//						throw new IllegalArgumentException("Assumed that context is already cloned, " +
//								"but it is not");
//					
//					Context clonedContext = getCloned(cxt, cloneMap);
//					Correspondence clonedChild = child.clone(clonedContext, cloneMap, p);
//					clonedChildren.add(clonedChild);
//				// child has already been cloned
//				} else {	
//					clonedChildren.add(cloneMap.get(child));
//				}
//			}
//			
//		}
//		
//		clone.children = clonedChildren;
//		cloneMap.put(this, clone);
//		return clone;
//	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		
		if(other == null || !(other instanceof PSOC2C))
			return false;
		
		PSOC2C that = (PSOC2C) other;
		return lhs.equals(that.lhs) 
				&& rhs.equals(that.rhs) 
				&& children.equals(that.children);
	}
	
	
	@Override
	public int hashCode() {
		return (21 * PSOC2C.class.hashCode()) 
					+ (19 * lhs.hashCode())
					+ (29 * rhs.hashCode())
					+ (5 * children.hashCode());
	}
	
	
	@Override
	public String toString() {
		return "C2C(" + lhs + "," + rhs + ")";
	}
	
	
	@Override
	public Class<? extends PSOC2C> getType() {
		return PSOC2C.class;
	}
	
}
