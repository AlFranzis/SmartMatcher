package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAttributeElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLClassElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLReferenceElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.EqualNameMatcher;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.Match;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.MatchResult;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.Matcher;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentQueryHelpers.StdClassMatcher;


public class TFragmentQueryHelpers {
	public class XMLElement_ extends XMLElement {
		public XMLElement_(String name) {
			setName(name);
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
	
	
	public class XMLClassElement_ extends XMLClassElement {
		public XMLClassElement_(String name) {
			setName(name);
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
	
	
	public class XMLAttributeElement_ extends XMLAttributeElement {
		public XMLAttributeElement_(String name) {
			setName(name);
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
	
	
	public class XMLReferenceElement_ extends XMLReferenceElement {
		public XMLReferenceElement_(String name, 
				XMLClassElement containedIn, XMLClassElement pointsTo) {
			setName(name);
			setContainedIn(containedIn);
			setPointsTo(pointsTo);
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
	
	
	@Test
	public void testMatchFunction() {
		XMLElement c1 = new XMLElement_("C1");
		XMLElement c2 = new XMLElement_("C2");
		XMLElement c3 = new XMLElement_("C3");
		XMLElement c4 = new XMLElement_("C4");
		
		Set<XMLElement> set = asSet(c1, c2, c3, c4);
		Set<XMLElement> subset = asSet(c1, c2, c3);
		Matcher<XMLElement> matcher = new EqualNameMatcher();
		
		MatchResult<XMLElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		Set<Match<XMLElement>> matches = res.getMatches();
		
		assertEquals(1, matches.size());
		
		Match<XMLElement> m = matches.iterator().next();
		
		Match<XMLElement> controlConfig = 
						new Match<XMLElement>(
								map(c1, c1).map(c2, c2).map(c3, c3));
		assertEquals(controlConfig, m);
	}
	
	
	@Test
	public void testMatchFunction2() {
		XMLElement c1 = new XMLElement_("C1");
		XMLElement c2 = new XMLElement_("C2");
		XMLElement c3 = new XMLElement_("C3");
		XMLElement c41 = new XMLElement_("C4");
		XMLElement c42 = new XMLElement_("C4");
		
		Set<XMLElement> set = asSet(c1, c2, c3, c41, c42);
		Set<XMLElement> subset = asSet(c1, c2, c41);
		Matcher<XMLElement> matcher = new EqualNameMatcher();
		
		MatchResult<XMLElement> res =
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		Set<Match<XMLElement>> matches = res.getMatches();
		
		// 2 result configs
		assertEquals(2, matches.size());
			
		Match<XMLElement> mCfg1 = new Match<XMLElement>(
				map(c1, c1).map(c2, c2).map(c41, c41));
			
		Match<XMLElement> mCfg2 = new Match<XMLElement>(
			map(c1, c1).map(c2, c2).map(c41, c42));
		Set<Match<XMLElement>> mCfgs = 
				asSet(mCfg1, mCfg2);

		assertEquals(mCfgs, matches);
	}
	
	
	@Test
	public void testMatchFunction3() {
		XMLElement c1 = new XMLElement_("C1");
		XMLElement c21 = new XMLElement_("C2");
		XMLElement c22 = new XMLElement_("C2");
		XMLElement c3 = new XMLElement_("C3");
		XMLElement c41 = new XMLElement_("C4");
		XMLElement c42 = new XMLElement_("C4");
		
		Set<XMLElement> set = asSet(c1, c21, c22, c3, c41, c42);
		Set<XMLElement> subset = asSet(c1, c21, c41);
		Matcher matcher = new EqualNameMatcher();
		
		MatchResult<XMLElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		boolean complete = res.isComplete();
		
		assertTrue(complete);
		
		Set<Match<XMLElement>> configs = res.getMatches();
		
		assertEquals(4, configs.size());
			
		Set<Match<XMLElement>> controlCfgs = 
			Helpers.<Match<XMLElement>>asSet(
				new Match<XMLElement>(map(c1, c1).map(c21, c21).map(c41, c41)),
				new Match<XMLElement>(map(c1, c1).map(c21, c21).map(c41, c42)),
				new Match<XMLElement>(map(c1, c1).map(c21, c22).map(c41, c41)),
				new Match<XMLElement>(map(c1, c1).map(c21, c22).map(c41, c42))
			);

		assertEquals(controlCfgs, configs);
	}
	
	
	@Test
	public void testIncompleteMatch() {
		XMLElement c1 = new XMLElement_("C1");
		XMLElement c2 = new XMLElement_("C2");
		XMLElement c3 = new XMLElement_("C3");
		XMLElement c4 = new XMLElement_("C4");
		XMLElement c5 = new XMLElement_("C5");
		
		Set<XMLElement> set = asSet(c1, c2, c3, c4);
		Set<XMLElement> subset = asSet(c1, c2, c5);
		Matcher matcher = new EqualNameMatcher();
		
		MatchResult<XMLElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		boolean complete = res.isComplete();
		
		assertFalse(complete);
		
		Set<Match<XMLElement>> configs = res.getMatches();
		
		System.out.println(configs);
	}
	
	
	@Test
	public void testClassMatchingOfClassesWithoutAttributes() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLClassElement c2 = new XMLClassElement_("C2");
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		Set<XMLClassElement> set = asSet(c1, c2, c3, c4);
		Set<XMLClassElement> subset = asSet(c1, c2, c3);
		Matcher<XMLClassElement> matcher = new StdClassMatcher();
		
		MatchResult<XMLClassElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		Set<Match<XMLClassElement>> configs = res.getMatches();
		
		assertEquals(1, configs.size());
		
		Match<XMLClassElement> config = configs.iterator().next();
		
		Match<XMLClassElement> controlConfig = 
						new Match<XMLClassElement>(
							map(c1, c1).map(c2, c2).map(c3, c3));
		assertEquals(controlConfig, config);
	}
	
	
	@Test
	public void testClassMatchingOfClassesWithAttributes() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		c1.setAttributes(asSet(a1));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		c2.setAttributes(asSet(a1));
		
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		Set<XMLClassElement> set = asSet(c1, c2, c3, c4);
		Set<XMLClassElement> subset = asSet(c1, c2, c3);
		Matcher<XMLClassElement> matcher = new StdClassMatcher();
		
		MatchResult<XMLClassElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		Set<Match<XMLClassElement>> configs = res.getMatches();
		
		assertEquals(1, configs.size());
		
		Match<XMLClassElement> config = configs.iterator().next();
		
		Match<XMLClassElement> controlConfig = 
						new Match<XMLClassElement>(map(c1, c1).map(c2, c2).map(c3, c3));
		assertEquals(controlConfig, config);
	}
	
	
	@Test
	public void testClassMatchingOfClassesWithAttributes2() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		c1.setAttributes(asSet(a1));
		
		XMLClassElement c1m = new XMLClassElement_("C1");
		XMLAttributeElement a2 = new XMLAttributeElement_("a2");
		c1m.setAttributes(asSet(a2));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		c2.setAttributes(asSet(a1));
		
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		Set<XMLClassElement> set = asSet(c1, c2, c3, c4);
		Set<XMLClassElement> subset = asSet(c1m, c2, c3);
		Matcher<XMLClassElement> matcher = new StdClassMatcher();
		
		MatchResult<XMLClassElement> res = 
			FragmentQueryHelpers.subisomorph(set, subset, matcher);
		
		boolean complete = res.isComplete();
		
		assertFalse(complete);
	}
	
	
	@Test
	public void testFragmentMatching() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		c1.setAttributes(asSet(a1));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		c2.setAttributes(asSet(a1));
		
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		XMLReferenceElement r1 = new XMLReferenceElement_("r1", c1, c1);
		XMLReferenceElement r2 = new XMLReferenceElement_("r2", c1, c2);
		c1.setReferences(asSet(r1, r2));
		
		XMLFragment f = new XMLFragment();
		f.setClasses(asSet(c1, c2, c3, c4));
		
		XMLFragment subf = new XMLFragment();
		subf.setClasses(asSet(c1, c2, c3, c4));
		
		MatchResult<XMLElement> matchRes = FragmentQueryHelpers.subisomorph(f, subf);
		assertTrue(matchRes.isComplete());
		
		System.out.println(matchRes.getMatches());
	}
	
	
	@Test
	public void testFragmentMatching2() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		XMLAttributeElement a2 = new XMLAttributeElement_("a2");
		c1.setAttributes(asSet(a1, a2));
		
		XMLClassElement c1m = new XMLClassElement_("C1");
		c1m.setAttributes(asSet(a1));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		c2.setAttributes(asSet(a1));
		
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		XMLReferenceElement r1 = new XMLReferenceElement_("r1", c1, c1);
		XMLReferenceElement r1m = new XMLReferenceElement_("r1", c1m, c1m);
		XMLReferenceElement r2 = new XMLReferenceElement_("r2", c1, c2);
		c1.setReferences(asSet(r1, r2));
		
		c1m.setReferences(asSet(r1m));
		
		XMLFragment f = new XMLFragment();
		f.setClasses(asSet(c1, c2, c3, c4));
		
		XMLFragment subf = new XMLFragment();
		subf.setClasses(asSet(c1m, c2, c3));
		
		MatchResult<XMLElement> matchRes = FragmentQueryHelpers.subisomorph(f, subf);
		assertTrue(matchRes.isComplete());
		
		System.out.println(matchRes.getMatches());
	}
	
	
	@Test
	public void testFragmentMatching3() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		XMLAttributeElement a2 = new XMLAttributeElement_("a2");
		c1.setAttributes(asSet(a1, a2));
		
		XMLClassElement c1m = new XMLClassElement_("C1");
		c1m.setAttributes(asSet(a1));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		c2.setAttributes(asSet(a1));
		
		XMLClassElement c3 = new XMLClassElement_("C3");
		XMLClassElement c4 = new XMLClassElement_("C4");
		
		XMLReferenceElement r1 = new XMLReferenceElement_("r1", c1, c1);
		XMLReferenceElement r1m = new XMLReferenceElement_("r1", c1m, c1m);
		XMLReferenceElement r2 = new XMLReferenceElement_("r2", c1, c2);
		c1.setReferences(asSet(r1, r2));
		
		c1m.setReferences(asSet(r1m));
		
		XMLFragment f = new XMLFragment();
		f.setClasses(asSet(c1, c2, c3, c4));
		
		XMLFragment subf = new XMLFragment();
		subf.setClasses(asSet(c1m, c2, c3));
		
		MatchResult<XMLElement> matchRes = FragmentQueryHelpers.subisomorph(f, subf);
		assertTrue(matchRes.isComplete());
		
		System.out.println(matchRes.getMatches());
	}
	
	
	@Test
	public void testMatchingAtts() {
		XMLClassElement c1 = new XMLClassElement_("C1");
		XMLAttributeElement a1 = new XMLAttributeElement_("a1");
		XMLAttributeElement a2 = new XMLAttributeElement_("a2");
		c1.setAttributes(asSet(a1, a2));
		
		XMLClassElement c1m = new XMLClassElement_("C1");
		c1m.setAttributes(asSet(a1));
		
		XMLClassElement c2 = new XMLClassElement_("C2");
		XMLAttributeElement a3 = new XMLAttributeElement_("a3");
		XMLAttributeElement a4 = new XMLAttributeElement_("a4");
		c2.setAttributes(asSet(a3, a4));
		
		XMLClassElement c2m = new XMLClassElement_("C2");
		c2m.setAttributes(asSet(a3, a4));
		
		Match<XMLClassElement> cMatch = new Match<XMLClassElement>(
												map(c1m, c1).map(c2m, c2)
											);
		
		Matcher<XMLElement> attMatcher = new EqualNameMatcher();
		MatchResult<XMLAttributeElement> aMatchRes = 
			FragmentQueryHelpers.matchAtts(cMatch, attMatcher);
		assertTrue(aMatchRes.isComplete());
		
		System.out.println(aMatchRes.getMatches());
	}
	
	
	
	
}
