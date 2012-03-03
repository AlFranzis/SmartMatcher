package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.isRoot;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortBubblesByName;
import static com.google.common.collect.Collections2.filter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragments;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.EasyFragmentMappingBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TPropose2 {
	
	
	public TPropose2() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	public class LHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LHSMM() {
			mm =  mm(li(
					c("C1", 
						li(
							a("a1"),
							a("a2")
						)
					).as("C1"),
					c("C2", 
						li(
							a("a3"),
							a("a4")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a5"),
							a("a6"),
							a("a7")
						)
					).as("C3"),
					c("C4", 
						li(
							a("a8")
						)
					).as("C4"),
					c("C5", 
						li(
							a("a9"),
							a("a10")
						)
					).as("C5"),
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	public class RHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RHSMM() {
			mm =  mm(li(
					c("C1*", 
						li(
							a("a1*"),
							a("a2*")
						)
					).as("C1"),
					c("C2*", 
						li(
							a("a3*"),
							a("a4*")
						)
					).as("C2"),
					c("C3*", 
						li(
							a("a5*"),
							a("a6*"),
							a("a7*")
						)
					).as("C3"),
					c("C4*", 
						li(
							a("a8*")
						)
					).as("C4"),
					c("C5*", 
						li(
							a("a9*"),
							a("a10*")
						)
					).as("C5"),
					r("r1*", c("C1"), c("C2")),
					r("r2*", c("C2"), c("C1")),
					r("r3*", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	@Test
	public void testSimplePropose() {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		Fragment_ f1 = new FragmentBuilder(lhsMM).f("C4", "C4.a8");
		f1.setId("F1");
		
		Fragment_ f2 = new FragmentBuilder(rhsMM).f("C4*", "C4*.a8*");
		f2.setId("F2");
		
		EasyFragmentMappingBuilder fmb = 
				new EasyFragmentMappingBuilder(f1, f2);
		FragmentMapping fm = 
			fmb.fm(fmb.li(
				fmb.c2c("C4", "C4*",
					fmb.li(
						fmb.a2a("C4.a8", "C4*.a8*")
					))
				)
			);
		fm.setId("FM1");
		
		f1.setMappings(asSet(fm));
		f2.setMappings(asSet(fm));		
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		
		Set<Fragment> frags = Helpers.asSet2(f1, f2);
		Set<XMLFragment> xmlFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(frags, resolverFac)
							.getFragments());
		
		Set<XMLFragment> xmlRootFrags = new HashSet<XMLFragment>(
							filter(xmlFrags, isRoot()));
		
		BubbleFactory bFac = new BubbleFactoryImpl();
		
		ReuseProposer proposer = new ReuseProposer(resolverFac, bFac);
		Set<Bubble<? extends Operator>> bubbles = 
						proposer.propose(lhsMM, rhsMM, xmlRootFrags);
		
		// **************************
		// CHECKS
		// **************************
		
		Element lhsC4 = lhsMM.clazz("C4");
		Element rhsC4 = rhsMM.clazz("C4*");
		Element lhsA8 = lhsMM.qatt("C4.a8");
		Element rhsA8 = rhsMM.qatt("C4*.a8*");
		
		// proposal contains a C2C and an A2A bubble
		assertEquals(2, bubbles.size());
		
		List<Bubble<? extends Operator>> sbubbles = sortBubblesByName(bubbles);
		
		// check C2C bubble
		Bubble<? extends Operator> c2cBubble = sbubbles.get(1);
		assertEquals("C2C", c2cBubble.getOperatorName());
		// c2c has no context
		assertTrue(c2cBubble.getCxt() == null ||
					c2cBubble.getCxt().isEmpty());
		Configuration<? extends Operator> c2cConfig = 
									c2cBubble.getConfiguration();
		assertTrue(c2cConfig.getRoles().containsValue(lhsC4));
		assertTrue(c2cConfig.getRoles().containsValue(rhsC4));
		
		// check A2A bubble
		Bubble<? extends Operator> a2aBubble = sbubbles.get(0);
		assertEquals("A2A", a2aBubble.getOperatorName());
		// A2A has C2C bubble as context
		assertEquals(1, a2aBubble.getCxt().size());
		assertTrue(a2aBubble.getCxt().contains(c2cBubble));
		Configuration<? extends Operator> a2aConfig = 
								a2aBubble.getConfiguration();
		assertTrue(a2aConfig.getRoles().containsValue(lhsA8));
		assertTrue(a2aConfig.getRoles().containsValue(rhsA8));
	}
	
	
	@Test
	public void testMoreComplexPropose() {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		// LHS fragments
		FragmentBuilder lhsFb = new FragmentBuilder(lhsMM);
		Fragment_ lf1 = lhsFb.f("C1");
		lf1.setId("LF1");
		
		Fragment_ lf2 = lhsFb.f("C2");
		lf2.setId("LF2");
		
		Fragment_ lf3 = lhsFb.f(lhsFb.li(lf1), "C1", "C1.a1");
		lf3.setId("LF3");
		
		Fragment_ lf4 = lhsFb.f(lhsFb.li(lf1, lf2), "C1", "C2");
		lf4.setId("LF4");
		
		Fragment_ lf5 = lhsFb.f(lhsFb.li(lf4), "C1", "C2", "C1_r1_C2");
		lf5.setId("LF5");
		
		Fragment_ lf6 = lhsFb.f(lhsFb.li(lf3, lf5), "C1", "C1.a1", 
												"C2", "C1_r1_C2");
		lf6.setId("LF6");
		
		
		// RHS fragments
		FragmentBuilder rhsFb = new FragmentBuilder(rhsMM);
		Fragment_ rf1 = rhsFb.f("C1*");
		rf1.setId("RF1");
		
		Fragment_ rf2 = rhsFb.f("C2*");
		rf2.setId("RF2");
		
		Fragment_ rf3 = rhsFb.f(rhsFb.li(rf1), "C1*", "C1*.a1*");
		rf3.setId("RF3");
		
		Fragment_ rf4 = rhsFb.f(rhsFb.li(rf1, rf2), "C1*", "C2*");
		rf4.setId("RF4");
		
		Fragment_ rf5 = rhsFb.f(rhsFb.li(rf4), "C1*", "C2*", "C1*_r1*_C2*");
		rf5.setId("RF5");
		
		Fragment_ rf6 = rhsFb.f(rhsFb.li(rf3, rf5), "C1*", "C1*.a1*", 
								"C2*", "C1*_r1*_C2*");
		rf6.setId("RF6");
		
		
		EasyFragmentMappingBuilder fmb = 
				new EasyFragmentMappingBuilder(lf1, rf1);
		FragmentMapping fm1 = 
			fmb.fm(fmb.li(
				fmb.c2c("C1", "C1*")
			));
		fm1.setId("FM1");
		
		lf1.setMappings(asSet(fm1));
		rf1.setMappings(asSet(fm1));	
		
		
		fmb = new EasyFragmentMappingBuilder(lf6, rf6);
		FragmentMapping fm2 = 
			fmb.fm(fmb.li(
				fmb.c2c("C1", "C1*",
					fmb.li(
						fmb.a2a("C1.a1", "C1*.a1*")
					)
				).as("C2C1"),
				fmb.c2c("C2", "C2*").as("C2C2"),
				fmb.r2r(fmb.li(fmb.op("C2C1"), fmb.op("C2C2")), 
						"C1_r1_C2", "C1*_r1*_C2*")
			));
		fm2.setId("FM2");
		
		lf6.setMappings(asSet(fm2));
		rf6.setMappings(asSet(fm2));	
		
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		
		Set<Fragment> frags = Helpers.asSet2(lf1, lf2, lf3, lf4, lf5, lf6,
											rf1, rf2, rf3, rf4, rf5, rf6);
		Set<XMLFragment> xmlFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(frags, resolverFac)
							.getFragments());
		
		Set<XMLFragment> xmlRootFrags = new HashSet<XMLFragment>(
							filter(xmlFrags, isRoot()));
		
		BubbleFactory bFac = new BubbleFactoryImpl();
		
		ReuseProposer proposer = new ReuseProposer(resolverFac, bFac);
		Set<Bubble<? extends Operator>> bubbles = 
						proposer.propose(lhsMM, rhsMM, xmlRootFrags);
		
		System.out.println( bubbles );
		
		// **************************
		// CHECKS
		// **************************
		
		Element lhsC1 = lhsMM.clazz("C1");
		Element rhsC1 = rhsMM.clazz("C1*");
		Element lhsC2 = lhsMM.clazz("C2");
		Element rhsC2 = rhsMM.clazz("C2*");
		Element lhsA1 = lhsMM.qatt("C1.a1");
		Element rhsA1 = rhsMM.qatt("C1*.a1*");
		Element lhsR1 = lhsMM.qref("C1_r1_C2");
		Element rhsR1 = rhsMM.qref("C1*_r1*_C2*");
		
		// proposal contains 2 C2C, 1 A2A, 1 R2R bubbles
		assertEquals(4, bubbles.size());
		
		List<Bubble<? extends Operator>> sbubbles = sortBubblesByName(bubbles);
		
		// check C2C bubble1
		Bubble<? extends Operator> c2cBubble = sbubbles.get(1);
		assertEquals("C2C", c2cBubble.getOperatorName());
		// c2c has no context
		assertTrue(c2cBubble.getCxt() == null || 
						c2cBubble.getCxt().isEmpty());
		Configuration<? extends Operator> c2cConfig = 
								c2cBubble.getConfiguration();
		assertTrue(c2cConfig.getRoles().containsValue(lhsC1));
		assertTrue(c2cConfig.getRoles().containsValue(rhsC1));
		
		// check A2A bubble
		Bubble<? extends Operator> a2aBubble = sbubbles.get(0);
		assertEquals("A2A", a2aBubble.getOperatorName());
		// A2A has C2C bubble as context
		assertEquals(1, a2aBubble.getCxt().size());
		assertTrue(a2aBubble.getCxt().contains(c2cBubble));
		Configuration<? extends Operator> a2aConfig = 
									a2aBubble.getConfiguration();
		assertTrue(a2aConfig.getRoles().containsValue(lhsA1));
		assertTrue(a2aConfig.getRoles().containsValue(rhsA1));
		
		// check C2C bubble
		Bubble<? extends Operator> c2cBubble2 = sbubbles.get(2);
		assertEquals("C2C", c2cBubble2.getOperatorName());
		// c2c has no context
		assertTrue(c2cBubble2.getCxt() == null || 
						c2cBubble2.getCxt().isEmpty());
		Configuration<? extends Operator> c2cConfig2 = 
									c2cBubble2.getConfiguration();
		assertTrue(c2cConfig2.getRoles().containsValue(lhsC2));
		assertTrue(c2cConfig2.getRoles().containsValue(rhsC2));
		
		// check R2R bubble
		Bubble<? extends Operator> r2rBubble = sbubbles.get(3);
		assertEquals("R2R", r2rBubble.getOperatorName());
		assertEquals(2, r2rBubble.getCxt().size());
		Configuration<? extends Operator> r2rConfig = 
								r2rBubble.getConfiguration();
		assertTrue(r2rConfig.getRoles().containsValue(lhsR1));
		assertTrue(r2rConfig.getRoles().containsValue(rhsR1));
	}
	
	
	@Test
	public void testComplexPropose() {
		// 12 Fragments on LHS, 12 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet1().getFragments();
		
		XMLElementResolverFactory resolverFac = 
								new XMLElementResolverFactory();
		
		Set<Fragment> lhsFrags = fragments.e0;
		Set<XMLFragment> xmlLhsFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(lhsFrags, resolverFac)
							.getFragments());
		
		Set<Fragment> rhsFrags = fragments.e1;
		Set<XMLFragment> xmlRhsFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(rhsFrags, resolverFac)
							.getFragments());
		
		Set<XMLFragment> xmlFrags = new HashSet<XMLFragment>(xmlLhsFrags);
		xmlFrags.addAll(xmlRhsFrags);
		
		Set<XMLFragment> xmlRoots = new HashSet<XMLFragment>(
			filter(xmlFrags, isRoot()));
		
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		BubbleFactory bFac = new BubbleFactoryImpl();
		
		ReuseProposer proposer = new ReuseProposer(resolverFac, bFac);
		Set<Bubble<? extends Operator>> bubbles = 
						proposer.propose(lhsMM, rhsMM, xmlRoots);
		
		assertEquals(5, bubbles.size());
		
		List<Bubble<? extends Operator>> sBubbles = sortBubblesByName(bubbles);
		Bubble<? extends Operator> a2a1 = sBubbles.get(0);
		
		System.out.println(bubbles);
	}
}
