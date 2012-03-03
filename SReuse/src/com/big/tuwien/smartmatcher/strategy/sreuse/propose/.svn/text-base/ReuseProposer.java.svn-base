package com.big.tuwien.smartmatcher.strategy.sreuse.propose;


import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.Sets;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLClassElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolver;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLMetaModel;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLOperator;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.Match;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.MatchResult;

public class ReuseProposer {
	private XMLElementResolverFactory resolverFac;
	private BubbleFactory bFactory;
	
	
	public ReuseProposer(XMLElementResolverFactory resolverFac, 
											BubbleFactory bFactory) {
		this.resolverFac = resolverFac;
		this.bFactory = bFactory;
	}
	
	
	/**
	 * Filters all fragments from a set that are sub-isomorphic 
	 * (= contained) to a meta-model.
	 * @param mm Meta-model to compare with.
	 * @param fs List of fragments that are matched with the meta-model.
	 * @return Map that contains all sub-isomorphic fragments
	 * and their matching maps.
	 */
	public static Map<XMLFragment,M> isomorph(final XMLMetaModel mm, 
													Set<XMLFragment> fs) {
		// consider the meta-model as one big fragment
		XMLFragment mmFragment = new XMLFragment() {
			@Override
			public Set<XMLClassElement> getClasses() {
				return new HashSet<XMLClassElement>(mm.getClasses());
			}
		};

		Map<XMLFragment,M> isos = new HashMap<XMLFragment, M>();
		for(XMLFragment f : fs) {
			MatchResult<XMLElement> res = FragmentQueryHelpers
										.subisomorph(mmFragment, f);
			if(res.isComplete())
				isos.put(f, new M(res));
		}

		return isos;
	}
	
	
	public static  Map<XMLFragment,M> isomorph2(
							final XMLMetaModel mm, Set<XMLFragment> roots) {
		return isomorph2(mm, roots, new HashSet<XMLFragment>());
	}
	
	
	public static Map<XMLFragment,M> isomorph2(
			final XMLMetaModel mm, Set<XMLFragment> roots, 
											Set<XMLFragment> pruned) {
		if(roots.isEmpty()) 
			return Collections.emptyMap();

		// contains all sub-isomorphic fragments in the fragments tree
		Map<XMLFragment,M> isos = new HashMap<XMLFragment, M>();

		// remove pruned fragments from root fragments set
//		Set<XMLFragment> _roots = new HashSet<XMLFragment>(roots);
//		_roots.removeAll(pruned);
		Set<XMLFragment> _roots = Sets.set(roots).remove(pruned);

		// find sub-isomorphic root fragments
		Map<XMLFragment,M> isoRoots = isomorph(mm, _roots);
		isos.putAll(isoRoots);

		// add not sub-isomorphic root fragments to pruned set  
//		Set<XMLFragment> _pruned = new HashSet<XMLFragment>(_roots);
//		_pruned.removeAll(isoRoots.keySet());
		Set<XMLFragment> _pruned = Sets.set(_roots).remove(isoRoots.keySet());
		
		pruned.addAll(_pruned);
		
		
		// add all children of not-sub-isomorphic fragments
		// to pruned set
		addFragmentsSubtreesToPruned(_pruned, pruned);

		// recursive call for children fragments
		for(XMLFragment isoRoot : isoRoots.keySet()) {
			Set<XMLFragment> children = isoRoot.getChildren();
			Map<XMLFragment,M> _isos = isomorph2(mm, children, pruned);
			isos.putAll(_isos);
		}

		return isos;
	}
	
	
	/*
	 * Adds all fragments in the set and their descendents to pruned set.
	 */
	private static void addFragmentsSubtreesToPruned(Set<XMLFragment> fs, 
												Set<XMLFragment> pruned) {
		for(XMLFragment f : fs)
			addFragmentSubtreeToPruned(f, pruned);
	}
	
	
	/*
	 * Adds the fragments and all descendents to pruned set.
	 */
	private static void addFragmentSubtreeToPruned(XMLFragment f, 
										Set<XMLFragment> pruned) {
		Set<XMLFragment> dscs = descendents(f);
		pruned.addAll(dscs);
	}
	
	
	/*
	 * Returns all descendents of a fragment.
	 */
	private static Set<XMLFragment> descendents(XMLFragment f) {
		Set<XMLFragment> dscs = new HashSet<XMLFragment>();
		for(XMLFragment cf : f.getChildren()) {
			dscs.add(cf);
			Set<XMLFragment> cdscs = descendents(cf);
			dscs.addAll(cdscs);
		}
		return dscs;
	}
	
	
	public Set<Bubble<? extends Operator>> extract(FM lhs, FM rhs, 
												XMLFragmentMapping fm) {
		checkFragmentContainsAllRole(lhs, rhs, fm);
		
		Set<XMLOperator> ops = fm.getFlattenedOperators();
		
		//TODO: what match to use -> maybe the one with highest quality
		// currently uses simply the first one
		Match<XMLElement> lhsMatch = lhs.getMatches().getMatches()
													.iterator().next();
		Match<XMLElement> rhsMatch = rhs.getMatches().getMatches()
													.iterator().next();
		
		Map<XMLElement, XMLElement> _elements = lhsMatch.toMap();
		_elements.putAll(rhsMatch.toMap());
		
		// map stores binding from (xml) elements that are operator roles
		// to meta-model elements.
		Map<XMLElement,Element> xmlOpRoles2mmElems = 
								new HashMap<XMLElement,Element>();
		
		XMLElementResolver resolver = resolverFac.getResolver();
		// fill elements map using resolverFactory
		for(Entry<XMLElement,XMLElement> e : _elements.entrySet()) {
			XMLElement xmlOpRoleElement = e.getKey(); 
			
			XMLElement xmlMMElement = e.getValue();
			Element mmElement = resolver.revResolve(xmlMMElement);
			
			if(mmElement == null)
				throw new ReuseRuntimeException(
				"XML metamodel element not bound: " + xmlMMElement);
			
			xmlOpRoles2mmElems.put(xmlOpRoleElement, mmElement);
		}
		
		Mantler mantler = new Mantler(xmlOpRoles2mmElems, bFactory);
		// convert the operators into bubble instances
		Set<Bubble<? extends Operator>> bubbles = mantler.mantle(ops);
		return bubbles;
	}
	
	
	public Set<Bubble<? extends Operator>> 
			propose(MetaModel lhsMM, MetaModel rhsMM, 
											Set<XMLFragment> roots) {
		
		XMLMetaModel xmlLhsMM = XMLMetaModel.getInstance(lhsMM, resolverFac);
		XMLMetaModel xmlRhsMM = XMLMetaModel.getInstance(rhsMM, resolverFac);
		
		return propose(xmlLhsMM, xmlRhsMM, roots);
	}
		
		
	public Set<Bubble<? extends Operator>>
			propose(XMLMetaModel lhsMM, XMLMetaModel rhsMM, 
											Set<XMLFragment> roots) {
		Map<XMLFragment,M> lhsIsoFrags = isomorph2(lhsMM, roots);
		Map<XMLFragment,M> rhsIsoFrags = isomorph2(rhsMM, roots);
		
		// collection n*m combinations of lhs and rhs fragments
		List<T2<FM,FM>> fragPairs = new ArrayList<T2<FM,FM>>();
		for(Entry<XMLFragment,M> lhsIso : lhsIsoFrags.entrySet()) {
			for (Entry<XMLFragment,M> rhsIso : rhsIsoFrags.entrySet()) {
				FM fm1 = new FM(lhsIso.getKey(), lhsIso.getValue());
				FM fm2 = new FM(rhsIso.getKey(), rhsIso.getValue());
				T2<FM,FM> fragPair = t(fm1, fm2);
				fragPairs.add(fragPair);
			}
		}

		// sort fragments pairs by their size 
		sort(fragPairs, new Comparator<T2<FM,FM>>() {
			@Override
			public int compare(T2<FM, FM> p1,
					T2<FM, FM> p2) {
				return (size(p1.e0.getF()) + size(p1.e1.getF())) 
				- (size(p2.e0.getF()) + size(p2.e1.getF()));
			}
		});
		// we need descending sort order
		Collections.reverse(fragPairs);

		for(T2<FM,FM> fragPair : fragPairs) {
			Set<XMLFragmentMapping> fms1 = fragPair.e0.getF().getMappings();
			Set<XMLFragmentMapping> fms2 = fragPair.e1.getF().getMappings();
			
			Set<XMLFragmentMapping> commonFMs = 
 				new HashSet<XMLFragmentMapping>(fms1);
			commonFMs.retainAll(fms2);

			if(!commonFMs.isEmpty()) {

				// TODO: idea: maybe sort common mappings by their quality
				// -> introduce similarity field in XMLFragmentMapping
				// then use mapping with max similarity

				XMLFragmentMapping cFM = commonFMs.iterator().next();
				Set<Bubble<? extends Operator>> bubbles = 
							extract(fragPair.e0, fragPair.e1, cFM);
					
				return bubbles;
			}
		}

		return null;
	}
	
	
	static class FM {
		private XMLFragment f;
		private M matches;
		
		public FM(XMLFragment f, M matches) {
			super();
			this.f = f;
			this.matches = matches;
		}

		public XMLFragment getF() {
			return f;
		}

		public M getMatches() {
			return matches;
		}
		
		public String toString() {
			return String.format("FM(%s, %s)", f, matches);
		}
	}
	
	
	static class M {
		private Set<Match<XMLElement>> matches = 
			new HashSet<Match<XMLElement>>();
		
		public M(MatchResult<XMLElement> matchResult) {
			matches.addAll(matchResult.getMatches());
		}
		
		public Set<Match<XMLElement>> getMatches() {
			return matches;
		}
		
		public String toString() {
			return String.format("M(%s)", matches);
		}
	}
	
	
	/*
	 * Returns how many elements a fragment contains.
	 * Sum of classes, attributes and reference count.
	 */
	public static int size(XMLFragment f) {
		int size = 0;
		for(XMLClassElement c : f.getClasses()) {
			size += c.getAttributes().size();
			size += c.getReferences().size();
		}
		return size;
	}
	
	
	/*
	 * Returns all elements a fragment contains.
	 */
	private static Set<XMLElement> elements(XMLFragment f) {
		Set<XMLElement> es = new HashSet<XMLElement>();
		es.addAll(f.getClasses());
		for(XMLClassElement c : f.getClasses()) {
			es.addAll(c.getAttributes());
			es.addAll(c.getReferences());
		}
		return es;
	}
	
	
	private static Set<XMLElement> roles(XMLFragmentMapping fm) {
		Set<XMLElement> roles = new HashSet<XMLElement>();
		for(XMLOperator op : fm.getFlattenedOperators()) {
			roles.addAll(op.getRoles().values());
		}
		return roles;
	}
	
	
	private static void checkFragmentContainsAllRole(FM lhs, FM rhs,
			XMLFragmentMapping fm) {
		Set<XMLElement> lhsFragElements = elements(lhs.getF());
		Set<XMLElement> rhsFragElements = elements(rhs.getF());
		
		Set<XMLElement> roles = roles(fm);
		if(!roles.containsAll(lhsFragElements))
			throw new ReuseRuntimeException("LHS consistency error");
		
		if(!roles.containsAll(rhsFragElements))
			throw new ReuseRuntimeException("RHS consistency error");
	}
	
	
	
	
}
