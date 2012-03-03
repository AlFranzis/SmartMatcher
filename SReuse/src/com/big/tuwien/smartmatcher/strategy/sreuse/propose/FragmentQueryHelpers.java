package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAttributeElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLClassElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLReferenceElement;

public class FragmentQueryHelpers {

	public static MatchResult<XMLElement> subisomorph(XMLFragment f, XMLFragment subf) {
		Matcher<XMLElement> classMatcher = new EqualNameMatcher();
		Matcher<XMLElement> attMatcher = new EqualNameMatcher();
		
		// match classes
		MatchResult<XMLClassElement> classMatchRes = 
			subisomorph(f.getClasses(), subf.getClasses(), classMatcher);
		
		// no complete class match
		if(!classMatchRes.isComplete())
			return new MatchResult<XMLElement>(false, null);
		
		Set<Match<XMLElement>> completeMatches = new HashSet<Match<XMLElement>>();
		
		for(Match<XMLClassElement> classMatch : classMatchRes.getMatches()) {
			Set<Match<XMLElement>> matches = new HashSet<Match<XMLElement>>();
			
			// match attributes
			MatchResult<XMLAttributeElement> attMatchRes = 
								matchAtts(classMatch, attMatcher);
			
			// no complete attributes matching -> try next class matching
			if(!attMatchRes.isComplete())
				continue;
			
			// cross product of class match and attributes matches
			for(Match<XMLAttributeElement> attMatch : attMatchRes.getMatches()) {
				Match<XMLElement> match = new Match<XMLElement>(classMatch);
				match.putAll(attMatch);
				matches.add(match);
			}
			
			// match references
			Matcher<XMLReferenceElement> refMatcher = 
										new StdRefMatcher(classMatch);
			Set<XMLReferenceElement> fRefs = getRefs(f.getClasses());
			Set<XMLReferenceElement> subfRefs = getRefs(subf.getClasses());
			MatchResult<XMLReferenceElement> 
				refMatchRes = subisomorph(fRefs, subfRefs, refMatcher);
			
			// no complete references match -> try next classes match
			if(!refMatchRes.isComplete())
				continue;
			
			// cross product matches
			Set<Match<XMLElement>>_matches = new HashSet<Match<XMLElement>>();
			for(Match<XMLReferenceElement> refMatch : refMatchRes.getMatches()) {
				for(Match<XMLElement> match : matches) {
					Match<XMLElement> _match = new Match<XMLElement>(match);
					_match.putAll(refMatch);
					_matches.add(_match);
				}
				
			}
			matches = _matches;
			
			completeMatches.addAll(matches);
		}
		
		return new MatchResult<XMLElement>(
				completeMatches.size() > 0, 
				completeMatches);
	}
	
	
	/**
	 * Match attributes of all given class matches.
	 * @param cMatch
	 * @return
	 */
	public static MatchResult<XMLAttributeElement>
	matchAtts(Match<XMLClassElement> cMatch, Matcher<XMLElement> attMatcher) {
		Set<Match<XMLAttributeElement>> mmerged = 
				new HashSet<Match<XMLAttributeElement>>();
		// match attributes of every class pair
		for(Entry<XMLClassElement,XMLClassElement> cpMatch : cMatch.entrySet()) {
			MatchResult<XMLAttributeElement> aMatchRes =
				subisomorph(
						cpMatch.getValue().getAttributes(),
						cpMatch.getKey().getAttributes(), 
						attMatcher);
			if(aMatchRes.isComplete()) {
				Set<Match<XMLAttributeElement>> _mmerged = 
					new HashSet<Match<XMLAttributeElement>>();
				for(Match<XMLAttributeElement> aMatch : aMatchRes.getMatches()) {
					Match<XMLAttributeElement> _merged = 
						new Match<XMLAttributeElement>(aMatch);
					for(Match<XMLAttributeElement> mm : mmerged) {
						_merged.putAll(mm);
					}
					_mmerged.add(_merged);
				}
				mmerged = _mmerged;
			} else {
				// attributes of class pair did not match completely
				return new MatchResult<XMLAttributeElement>(false, mmerged);
			}
		}
		
		return new MatchResult<XMLAttributeElement>(true, mmerged);
	}
	
	
	/**
	 * Encapsulates the matching result.
	 * @author alex
	 *
	 * @param <T>
	 */
	public static class MatchResult<T extends XMLElement> {
		private boolean complete;
		private Set<Match<T>> matches;
		
		public MatchResult(boolean complete, Set<Match<T>> matches) {
			this.complete = complete;
			this.matches = matches;
		}
		
		public boolean isComplete() {
			return complete;
		}
		
		public Set<Match<T>> getMatches() {
			return matches;
		}
	}
	
	
	/**
	 * Container class to store the matches between elements.
	 * Similar to a map, primary used to make the code easier to understand.
	 * @author alex
	 *
	 * @param <T>
	 */
	public static class Match<T extends XMLElement> {
		private Map<T,T> map = new HashMap<T, T>();
		
		public Match() {}
		
		
		public Match(Map<T,T> m) {
			map.putAll(m);
		}
		
		public Match(Match<? extends T> m) {
			map.putAll(m.map);
		}
		
		public void put(T key, T value) {
			map.put(key, value);
		}
		
		public void putAll(Map<T,T> m) {
			map.putAll(m);
		}
		
		public void putAll(Match<? extends T> m) {
			map.putAll(m.map);
		}
		
		public T get(T key) {
			return map.get(key);
		}
		
		public Set<Entry<T,T>> entrySet() {
			return map.entrySet();
		}
		
		public Map<T,T> toMap() {
			Map<T,T> _map = new HashMap<T, T>(map);
			return _map;
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(other == null || !(other instanceof Match<?>))
				return false;
			Match<?> that = (Match<?>) other;
			return this.map.equals(that.map);
		}
		
		@Override
		public int hashCode() {
			return Match.class.hashCode() * 57 + 3 * map.hashCode();
		}
		
		@Override
		public String toString() {
			return map.toString();
		}
	}
	
	
	public static <T extends XMLElement> MatchResult<T> subisomorph2(
			Set<T> set, Set<T> subset , Matcher<T> matcher, Match<T> config) {
		
		Set<Match<T>> res = new HashSet<Match<T>>();
		
		if(subset.isEmpty()) {
			res.add(config);
			return new MatchResult<T>(true, res);
		}
		
		T sel = subset.iterator().next();
		
		Set<T> matched = matcher.match(set, sel);
		
		boolean complete = false;
		for(T match : matched) {
			Match<T> _config = new Match<T>(config);
			_config.put(sel, match);
			
			Set<T> _set = new HashSet<T>(set);
			_set.remove(match);

			Set<T> _subset = new HashSet<T>(subset);
			_subset.remove(sel);

			// match remaining elements
			MatchResult<T> smatch = 
					subisomorph2(_set, _subset, matcher, _config);
			if(smatch.isComplete() == true) {
				complete = true;
				res.addAll(smatch.getMatches());
			}
		}
		
		return new MatchResult<T>(complete, res);
	}
	
	
	public static <T extends XMLElement> MatchResult<T> 
		subisomorph(Set<T> set, Set<T> subset , Matcher<? super T> matcher) {
		return subisomorph2(set, subset, (Matcher<T>) matcher, new Match<T>());
	}
	
	
	public interface Matcher<T extends XMLElement> {
		public boolean match(T e1, T e2);
		
		public Set<T> match(Set<T> candidates, T element);
	}
	
	
	public abstract static class AbstractMatcher<T extends XMLElement> 
												implements Matcher<T> {

		@Override
		public abstract boolean match(T e1, T e2);
		
		
		public Set<T> match(Set<T> candidates, T element) {
			Set<T> matched = new HashSet<T>();
			for(T cand : candidates) {
				if(match(cand, element))
					matched.add(cand);
			}
			return matched;
		}
	}
	
	
	public static class EqualNameMatcher 
						extends AbstractMatcher<XMLElement> {

		@Override
		public boolean match(XMLElement e1, XMLElement e2) {
			return e1.getName().equalsIgnoreCase(e2.getName());
		}
	}
	
	
	public static class StdClassMatcher 
					extends AbstractMatcher<XMLClassElement> {

		@Override
		public boolean match(XMLClassElement c1, XMLClassElement c2) {
			Matcher<XMLElement> matcher = new EqualNameMatcher();
			
			// class names do not match
			if(!matcher.match(c1, c2))
				return false;
			
			boolean completeMatch = 
				FragmentQueryHelpers.<XMLAttributeElement>subisomorph(
					c1.getAttributes(), 
					c2.getAttributes(),
					matcher).isComplete();
			
			if(!completeMatch)
				completeMatch = 
					FragmentQueryHelpers.<XMLAttributeElement>subisomorph(
						c2.getAttributes(), 
						c1.getAttributes(),
						matcher).isComplete();
			
			// class attributes do not match
			if(!completeMatch)
				return false;
			
			
			// TODO: match references
			
			return true;
		}
	}
	
	
	public static class StdRefMatcher 
							extends AbstractMatcher<XMLReferenceElement> {
		private Match<XMLClassElement> classMatch;
		
		public StdRefMatcher(Match<XMLClassElement> classMatch) {
			this.classMatch = classMatch;
		}
		
		
		/*
		 * Decides if r2 is an isomorphic sub-reference of r1.
		 */
		@Override
		public boolean match(XMLReferenceElement r1, XMLReferenceElement r2) {
			Matcher<XMLElement> matcher = new EqualNameMatcher();

			// reference names do not match
			if(!matcher.match(r1, r2))
				return false;
			
			boolean endpoints = compatibleEndpoints(r1, r2);

			return endpoints;
		}
		
		
		/*
		 * Decides if r2 is an isomorphic sub-reference of r1 by checking
		 * if r1 and r2 have compatible endpoints.
		 */
		private boolean compatibleEndpoints(XMLReferenceElement r1,
											XMLReferenceElement r2) {
			XMLClassElement r1Container = r1.getContainedIn();
			XMLClassElement r1Target = r1.getPointsTo();
			
			XMLClassElement r2Container = r2.getContainedIn();
			XMLClassElement r2Target = r2.getPointsTo();
			XMLClassElement mr2Container = classMatch.get(r2Container);
			XMLClassElement mr2Target = classMatch.get(r2Target);
			
			if(r1Container.equals(mr2Container) && r1Target.equals(mr2Target))
				return true;
			
			if(r1Container.equals(mr2Target) && r1Target.equals(mr2Container))
				return true;
				
			return false;
		}
	}
	
	
	private static Set<XMLReferenceElement> getRefs(Collection<XMLClassElement> cs) {
		Set<XMLReferenceElement> refs = new HashSet<XMLReferenceElement>();
		for(XMLClassElement c : cs) {
			refs.addAll(c.getReferences());
		}
		return refs;
	}
	

}