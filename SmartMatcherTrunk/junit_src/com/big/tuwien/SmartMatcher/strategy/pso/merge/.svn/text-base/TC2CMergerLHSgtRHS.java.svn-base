package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;


/**
 * Tests the {@link C2CReplacer} for LHS and RHS meta-models where
 * the LHS has more classes than the RHS. 
 * @author alex
 *
 */
public class TC2CMergerLHSgtRHS extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TC2CMergerLHSgtRHS() {
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
	public void testRemoval1() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected Merge<PSOC2C> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				PSOC2C m1C2C = (PSOC2C) QueryPSOMapping.instance(m1).descendants(
											cs(li(
													c2c("C1","C1")
											)))
											.iterator().next();
				
				return new Removal<PSOC2C>(Side.LHS(), m1C2C);
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
		
		// ********************************************
		// CHECKS
		
		assertTrue(merged.equals(m2));
		
	}
	
	
	@Test
	public void testReplacement1() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected Merge<PSOC2C> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				PSOC2C m1C2C = (PSOC2C) QueryPSOMapping.instance(m1).descendants(
											cs(li(
													c2c("C3","C3")
											)))
											.iterator().next();
				
				PSOC2C m2C2C = (PSOC2C) QueryPSOMapping.instance(m2).descendants(
						cs(li(
								c2c("C3","C2")
						)))
						.iterator().next();
				
				return new Replacement<PSOC2C>(Side.LHS(), m1C2C, m2C2C);
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
	}
	
	
	@Test
	public void testReplacement2() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected Merge<PSOC2C> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				PSOC2C m1C2C = (PSOC2C) QueryPSOMapping.instance(m1).descendants(
											cs(li(
													c2c("C2","C2")
											)))
											.iterator().next();
				
				PSOC2C m2C2C = (PSOC2C) QueryPSOMapping.instance(m2).descendants(
						cs(li(
								c2c("C2","C1")
						)))
						.iterator().next();
				
				return new Replacement<PSOC2C>(Side.LHS(), m1C2C, m2C2C);
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
	}
	
	
	@Test
	public void testInsert1() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		C2CMerger replacer = new C2CMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected Merge<PSOC2C> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				PSOC2C m2C2C = (PSOC2C) QueryPSOMapping.instance(m2).descendants(
						cs(li(
								c2c("C4","C3")
						)))
						.iterator().next();
				
				return new Insert<PSOC2C>(Side.LHS(), m2C2C);
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
	}
	
	
	
}
