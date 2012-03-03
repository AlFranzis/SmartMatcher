package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.isRoot;
import static com.google.common.collect.Collections2.filter;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragments;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLMetaModel;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.ReuseProposer.M;

public class TIsomorph {
	
	
	public TIsomorph() {
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
	public void testIsomorphs() {
		// 12 Fragments on LHS, 12 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet1().getFragments();
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		
		Set<Fragment> lhsFrags = fragments.e0;
		Set<XMLFragment> xmlLhsFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(lhsFrags, resolverFac)
							.getFragments());
		
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		
		XMLMetaModel xmlLhsMM = XMLMetaModel.getInstance(lhsMM, resolverFac);
		
		Map<XMLFragment,M> lhsIsos = ReuseProposer.isomorph(xmlLhsMM, xmlLhsFrags);
		assertEquals(12, lhsIsos.size());
		
		
		Set<Fragment> rhsFrags = fragments.e1;
		Set<XMLFragment> xmlRhsFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(rhsFrags, resolverFac)
							.getFragments());
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		XMLMetaModel xmlRhsMM = XMLMetaModel.getInstance(rhsMM, resolverFac);
		
		Map<XMLFragment,M> rhsIsos = ReuseProposer.isomorph(xmlRhsMM, xmlRhsFrags);
		assertEquals(0, rhsIsos.size());
	}
	
	
	@Test
	public void testIsomorphs2() {
		// 12 Fragments on LHS, 12 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet1().getFragments();
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		
		Set<Fragment> lhsFrags = fragments.e0;
		Set<XMLFragment> xmlLhsFrags = new HashSet<XMLFragment>(
							XMLFragments.getInstance(lhsFrags, resolverFac)
							.getFragments());
		
		Set<XMLFragment> xmlLhsRoots = new HashSet<XMLFragment>(
										filter(xmlLhsFrags, isRoot()));
		
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		
		XMLMetaModel xmlLhsMM = XMLMetaModel.getInstance(lhsMM, resolverFac);
		
		Map<XMLFragment,M> lhsIsos = ReuseProposer.isomorph2(xmlLhsMM, xmlLhsRoots);
		assertEquals(12, lhsIsos.size());
	}
	
	
	
}
