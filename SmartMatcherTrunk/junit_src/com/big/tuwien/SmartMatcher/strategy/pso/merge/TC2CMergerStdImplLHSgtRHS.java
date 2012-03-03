package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;


/**
 * Tests the standard implementation of {@link C2CMerger} 
 * for LHS and RHS meta-models where the LHS has more classes than the RHS.
 * No methods of the merger are overwritten. 
 * @author alex
 *
 */ 
public class TC2CMergerStdImplLHSgtRHS extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TC2CMergerStdImplLHSgtRHS() {
		DOMConfigurator.configure("junit_log4j.xml");
		lhsMM = new LhsMM();
		rhsMM = new RhsMM();
	}

	
	public class LhsMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LhsMM() {
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
					).as("C5")	,
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel_ getMetaModel() {
			return mm;
		}
	}
	
	
	public class RhsMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RhsMM() {
			mm = mm(li(
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
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		public MetaModel_ getMetaModel() {
			return mm;
		}
	}
	
	
	public class Mapping1 extends MappingBuilder {
		public PSOMapping getMapping() {
			init(lhsMM.getMetaModel(), rhsMM.getMetaModel());
			
			PSOMapping m =
				m(li(
						c2c("C1", "C1", 
								li(
									a2a("a1","a1"), 
									a2a("a2","a2")
								)
							).as("c2c1"),
						c2c("C2", "C2", 
								li(
									a2a("a3","a3"), 
									a2a("a4","a4")
								)
							).as("c2c2"),
						c2c("C3", "C3",
								li(
									a2a("a5", "a5"),
									a2a("a6", "a6"),
									a2a("a7", "a7")
								)
							).as("c2c3"),
						r2r("r1","r1", 
								c2c("c2c1"), 
								c2c("c2c2")
						),
				
						r2r("r2","r2", 
								c2c("c2c1"), 	
								c2c("c2c2")
								),
						r2r("r3","r3", 
								c2c("c2c2"), 	
								c2c("c2c3")
						)
					)
				);
			
			return m;
		}
	}
	
	
	public class Mapping2 extends MappingBuilder {
		public PSOMapping getMapping() {
			init(lhsMM.getMetaModel(), rhsMM.getMetaModel());
			
			PSOMapping m =
				m(li(
						c2c("C2", "C1", 
								li(
									a2a("a3","a1"), 
									a2a("a4","a2")
								)
							).as("c2c1"),
						c2c("C3", "C2", 
								li(
									a2a("a5","a3"), 
									a2a("a6","a4")
								)
							).as("c2c2"),
						c2c("C4", "C3",
								li(
									a2a("a8", "a5")
								)
							).as("c2c3"),
						r2r("r3","r1", 
								c2c("c2c1"), 
								c2c("c2c2")
						)
					)
				);
			
			return m;
		}
	}
	
	
	@Test
	public void testPickingImplementation() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel());
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
		
		// ********************************************
		// CHECKS
		
		assertTrue(!merged.equals(m1));
		
		// a complete mapping of the given meta-models 
		// must a least have 5 A2As
		assertTrue(merged.descendents(PSOA2A.class).size() >= 5);
		
		assertTrue(merged.descendents(PSOR2R.class).size() > 0);
		
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
	}
	
	
	@Test
	public void testRepeatedMerging() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel());
		
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		PSOMapping merged2 = replacer.merge(merged, m2);
		
		PSOMapping merged3 = replacer.merge(merged2, m2);
		
		assertTrue(merged3.equals(m2));
	}
	
	
	@Test
	public void testIdenticalMerging() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel());
		
		
		// try to perform merge -> no merging possible for identical mappings
		PSOMapping merged = replacer.merge(m1, m1);
		
		assertEquals(m1, merged);
	}
	
	
}
