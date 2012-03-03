package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.ElementDecoratorFactory.decoratorMap;
import static com.google.common.collect.Collections2.filter;
import static java.lang.Math.abs;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.google.common.base.Predicate;

public class Fragmentizer {
	private Map<Integer,Bucket> preBuckets = 
								new HashMap<Integer, Bucket>();
	
	private Map<Integer,Bucket> buckets = 
								new HashMap<Integer, Bucket>();
	
	
	public T2<Set<Fragment>,Set<Fragment>> 
			fragmentize(Set
			<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
																	bubbles) {
		Set<Operator> ops = new Dismantler().dismantle2(bubbles);
		
		// fill pre-buckets
		addToPreBuckets(ops);

		// STEP 1
		// create bucket 1 and add root operators
		Bucket bucket1 = new Bucket(1);
		buckets.put(1, bucket1);
		for(Operator op : ops) {
			Frag f = new Frag(asList(op));
			bucket1.addFragment(f);
		}

		//STEP 2
		fragmentize(2);

		T2<Set<Fragment>,Set<Fragment>> fragments = extract();

		return fragments;	
	}
	
	
	private T2<Set<Fragment>,Set<Fragment>> extract() {
		// order ranks ascending
		SortedSet<Integer> ranks = new TreeSet<Integer>(buckets.keySet());
		
		Map<Frag,T2<Fragment,Fragment>> fMap = 
							new HashMap<Frag, T2<Fragment,Fragment>>();
		Set<Fragment> lhsFragments = new CopyOnWriteArraySet<Fragment>();
		Set<Fragment> rhsFragments = new CopyOnWriteArraySet<Fragment>();
		
		for(Integer rank : ranks) {
			Bucket b = buckets.get(rank);
			for(Frag f : b.getFragments()) {
				T2<Fragment,Fragment> fragments = convert(f);
			
				// set parent fragments
				Set<Fragment> lhsParents = new CopyOnWriteArraySet<Fragment>();
				Set<Fragment> rhsParents = new CopyOnWriteArraySet<Fragment>();
				for(Frag fancestor : f.getAncestors()) {
					assert fMap.containsKey(fancestor) 
							: "Unknown ancestor fragment";
					T2<Fragment,Fragment> frAncestors = fMap.get(fancestor);
					lhsParents.add(frAncestors.e0);
					rhsParents.add(frAncestors.e1);
				}
				fragments.e0.setParents(lhsParents);
				if(lhsParents.isEmpty())
					fragments.e0.setRoot(true);
				fragments.e1.setParents(rhsParents);
				if(rhsParents.isEmpty())
					fragments.e1.setRoot(true);
				
				fMap.put(f, fragments);
				lhsFragments.add(fragments.e0);
				rhsFragments.add(fragments.e1);
			}
		}
		
		return Tuples.t(lhsFragments, rhsFragments);
	}
	
	
	private T2<Fragment,Fragment> convert(Frag f) {
		// ops is FLAT structure
		Set<Operator> ops = f.getOperators();
		
		// decOps is DEEP structure
		Set<Operator> decOps = OpDecoratorFactory.decorate(ops);
		
		T2<Set<Element>,Set<Element>> elements = elements(decOps);
		
		Map<Element,ElementDecorator> lhsDecMap = decoratorMap(elements.e0);
		Map<Element,ElementDecorator> rhsDecMap = decoratorMap(elements.e1);
		
		replaceOpsRolesByDecorators(decOps, lhsDecMap, rhsDecMap);
		
		FragmentMapping mapping = new FragmentMapping();
		mapping.setOperators(decOps);
		
		Fragment lhsFragment = new Fragment();
		Fragment rhsFragment = new Fragment();
	
		lhsFragment.setMappings(asArraySet(mapping));
		rhsFragment.setMappings(asArraySet(mapping));
		
		Predicate<ElementDecorator> isClassDec = 
			new Predicate<ElementDecorator>() {
				@Override
				public boolean apply(ElementDecorator dec) {
					return dec instanceof ClassDecorator;
				}
			};
			
		Set<Element> lhsClasses = 
				new CopyOnWriteArraySet<Element>(filter(lhsDecMap.values(), 
													isClassDec));
		Set<Element> rhsClasses = 
			new CopyOnWriteArraySet<Element>(filter(rhsDecMap.values(), 
													isClassDec));
	
		lhsFragment.setClasses(lhsClasses);
		rhsFragment.setClasses(rhsClasses);
		
		return Tuples.t(lhsFragment, rhsFragment);
	}
	
	
	/*
	 * Replaces operator element roles by element decorators.
	 */
	private static void replaceOpRolesByDecorators(Operator op, 
			Map<Element,ElementDecorator> lhsDecMap, 
			Map<Element,ElementDecorator> rhsDecMap) {

		Map<String,Element> _lhsRoles = new HashMap<String, Element>();
		for(Entry<String,Element> role : op.getLhsRoles().entrySet()) {
			String rolename = role.getKey();
			Element e = role.getValue();
			Element dec = lhsDecMap.get(e);
			if(dec == null) {
				throw new ReuseRuntimeException(
						"LHS element decorator map does not contain " +
						"decorator for element " + e
				);
			}
			_lhsRoles.put(rolename, dec);
		}
		op.setLhsRoles(_lhsRoles);

		Map<String,Element> _rhsRoles = new HashMap<String, Element>();
		for(Entry<String,Element> role : op.getRhsRoles().entrySet()) {
			String rolename = role.getKey();
			Element e = role.getValue();
			Element dec = rhsDecMap.get(e);
			if(dec == null) {
				throw new ReuseRuntimeException(
						"RHS element decorator map does not contain " +
						"decorator for element " + e
				);
			}
			_rhsRoles.put(rolename, dec);
		}
		op.setRhsRoles(_rhsRoles);
	}
	
	
	
	private static void replaceOpsRolesByDecorators(Set<Operator> ops,
						Map<Element,ElementDecorator> lhsDecMap, 
						Map<Element,ElementDecorator> rhsDecMap) {
		Set<Operator> converted = new CopyOnWriteArraySet<Operator>();
		replaceOpsRolesByDecorators(ops, converted, lhsDecMap, rhsDecMap);
	}
	
	
	/*
	 * Replaces the role elements of the operators with decorators.
	 * ops is a DEEP structure.
	 * converted contains all operator whose roles have already been replaced.
	 */
	private static void replaceOpsRolesByDecorators(Set<Operator> ops,
							Set<Operator> converted,
							Map<Element,ElementDecorator> lhsDecMap, 
							Map<Element,ElementDecorator> rhsDecMap) {
		for(Operator op : ops) {
			// op already converted ?
			if(converted.contains(op))
				continue;
			
			replaceOpRolesByDecorators(op, lhsDecMap, rhsDecMap);
			converted.add(op);
			
			// recursive replacement for children
			Set<Operator> children = op.getChildren();
			replaceOpsRolesByDecorators(children, converted, 
										lhsDecMap, rhsDecMap);	
		}
	}
	
	
	/*
	 * Returns all LHS and RHS elements mapped by the given operators;
	 * Input operators are given in DEEP structure  manner, not FLATTENED!
	 */
	private static T2<Set<Element>,Set<Element>> elements(Set<Operator> ops) {
		Set<Element> lhsElements = new CopyOnWriteArraySet<Element>();
		Set<Element> rhsElements = new CopyOnWriteArraySet<Element>();
		for(Operator op : ops) {
			lhsElements.addAll(op.getLhsRoles().values());
			rhsElements.addAll(op.getRhsRoles().values());
			Set<Operator> children = op.getChildren();
			T2<Set<Element>,Set<Element>> cElements = elements(children);
			lhsElements.addAll(cElements.e0);
			rhsElements.addAll(cElements.e1);
		}
		return Tuples.t(lhsElements,rhsElements);
	}
	
	
	private void fragmentize(int step) {
		Bucket previous = buckets.get(step - 1);
		Set<Frag> pFrags = previous.getFragments();
		
		Bucket bucket = new Bucket(step);
		buckets.put(step, bucket);
		
		if(preBuckets.containsKey(step)) {
			Bucket preBucket = preBuckets.get(step);
			Set<Frag> preFrags = preBucket.getFragments();
			for(Frag pf : pFrags) {
				for(Frag preFrag : preFrags) {
					if(pf.diffOne(preFrag)) {
						bucket.addFragment(preFrag);
						pf.addDescendent(preFrag);
					}
				}
			}
		}
		
		// nothing left to unite
		if(pFrags.size() <= 1)
			return;
		
		for(Frag pf1 : pFrags) {
			for(Frag pf2 : pFrags) {
				if(pf1 == pf2)
					continue;
				
				if(pf1.diffOne(pf2)) {
					Frag united = pf1.union(pf2);
					if(!bucket.contains(united)) {
						pf1.addDescendent(united);
						pf2.addDescendent(united);
						bucket.addFragment(united);
					} else {
						Frag uf = bucket.get(united);
						pf1.addDescendent(uf);
						pf2.addDescendent(uf);
					}
				}
			}
		}
		
		// next STEP
		fragmentize(step + 1);
	}
	
	
	/*
	 * Explodes the given root operators and adds them 
	 * to the pre-buckets.
	 */
	private void addToPreBuckets(Set<Operator> ops) {
		for(Operator op : ops) {
			assert !op.hasParents() 
					: "Given operator is not a root operator";
			
			if(!op.hasChildren())
				continue;
			
			addToPreBuckets(op);
		}
	}
	
	
	private void addToPreBuckets(Operator op) {
		Set<Operator> pathOps = ancestors(op);
		pathOps.add(op);
		
		int rank = pathOps.size();
		Bucket b = preBuckets.get(rank);
		if(b == null) {
			b = new Bucket(rank);
			preBuckets.put(rank, b);
		}
		
		Frag f = new Frag(pathOps);
		if(!b.contains(f))
			b.addFragment(f);
		
		for(Operator child : op.getChildren()) {
			addToPreBuckets(child);
		}
	}
	
	
	/*
	 * Return all ancestors of an operator
	 */
	private Set<Operator> ancestors(Operator op) {
		if(!op.hasParents())
			return new CopyOnWriteArraySet<Operator>();
		else {
			Set<Operator> ancestors = new CopyOnWriteArraySet<Operator>(
												op.getParents());
			for(Operator p : op.getParents()) {
				ancestors.addAll(ancestors(p));
			}
			return ancestors;
		}
	}
	
	
	public static class Frag {
		/* FLATTENED */
		private Set<Operator> operators;
		private Set<Frag> descendents = new CopyOnWriteArraySet<Frag>();
		private Set<Frag> ancestors = new CopyOnWriteArraySet<Frag>();
		
		public Frag(Collection<Operator> ops) {
			this.operators = new CopyOnWriteArraySet<Operator>(ops);
		}
		
		public boolean diffOne(Frag f) {
			Set<Operator> oops = f.getOperators();
			
			if(abs(oops.size() - size()) > 1)
				return false;
			
			boolean diff = false;
			Set<Operator> ops = new CopyOnWriteArraySet<Operator>(operators);
			for(Operator oop : oops) {
				boolean found = false;
				for(Operator op : ops) {
					if(oop.equals(op)) {
						found = true;
						break;
					}
				}
				// equal operator found
				if(found) {
					// remove operator so it cannot be used twice
					ops.remove(oop);
				// no equal operator found -> remember difference
				} else if(diff == false){
					diff = true;
				// more than one operator difference
				} else {
					return false;
				}
			}
			
			return diff;
		}
		
		public Frag union(Frag f) {
			if(!diffOne(f))
				throw new ReuseRuntimeException(
						"Union only works for fragments with diff 1");
			
			Set<Operator> united = new CopyOnWriteArraySet<Operator>(operators);
			united.addAll(f.getOperators());
			
			assert united.size() == size() + 1 
					: "Unexpected result of fragment union";
	
			return new Frag(united);
		}
	
		public int size() {
			return operators.size();
		}
		
		public Set<Operator> getOperators() {
			return operators;
		}
		
		public void addDescendent(Frag dc) {
			descendents.add(dc);
			dc.ancestors.add(this);
		}
		
		public Set<Frag> getDescendents() {
			return descendents;
		}
		
		public Set<Frag> getAncestors() {
			return ancestors;
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(other == null) return false;
			if(!(other instanceof Frag)) return false;
			
			Frag that = (Frag) other;
			
			return this.operators.equals(that.operators);
		}
		
		@Override
		public int hashCode() {
			return Frag.class.hashCode() 
				+ 99 * this.operators.hashCode();
		}
		
		@Override
		public String toString() {
			return "Frag(" + operators + ")";
		}
	}
	
	
	public static class Bucket {
		private int rank;
		private Set<Frag> fragments = new CopyOnWriteArraySet<Frag>();
		
		public Bucket(int rank) {
			this.rank = rank;
		}
		
		public int getRank() {
			return rank;
		}
		
		public boolean addFragment(Frag f) {
			return fragments.add(f);
		}
		
		public boolean contains(Frag f) {
			return fragments.contains(f);
		}
		
		public Frag get(Frag f) {
			for(Frag _f : fragments) {
				if(_f.equals(f))
					return _f;
			}
			
			return null;
		}
		
		public Set<Frag> getFragments() {
			return fragments;
		}
		
		@Override
		public String toString() {
			return "Bucket(" + rank + "," + fragments + ")";
		}
	}

}
