package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.ExtPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

/**
 * Tests the {@link A2AReplacer}.
 * @author alex
 *
 */
public class TA2AMergerLHSgtRHS extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TA2AMergerLHSgtRHS() {
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
						c2c("C2", "C3", 
								li(
									a2a("a3","a5"), 
									a2a("a4","a6")
								)
							).as("c2c2"),
						c2c("C3", "C2",
								li(
									a2a("a5", "a3"),
									a2a("a6", "a4")
								)
							).as("c2c3"),
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
						c2c("C1", "C1", 
								li(
									a2a("a1","a2"), 
									a2a("a2","a1")
								)
							).as("c2c1"),
						c2c("C2", "C3", 
								li(
									a2a("a3","a6"), 
									a2a("a4","a7")
								)
							).as("c2c2"),
						c2c("C3", "C2",
								li(
									a2a("a6", "a3"),
									a2a("a7", "a4")
								)
							).as("c2c3"),
						r2r("r3","r3", 
								c2c("c2c2"), 	
								c2c("c2c3")
						)
					)
				);
			
			return m;
		}
	}
	
	
	@Test
	public void testReplace1() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		A2AMerger replacer = new A2AMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected T2<C1<PSOC2C>,Merge<PSOA2A>> pick(ExtPSOMapping m1, 
														ExtPSOMapping m2) {
				PSOC2C m1Cxt = (PSOC2C) QueryPSOMapping.instance(m1)
						.descendants(
						cs(li(
							c2c("C1","C1")
						)))
						.iterator().next();
				
				PSOA2A m1A2A = (PSOA2A) QueryPSOMapping.instance(m1)
						.descendants(
						cs(li(
							a2a("a1","a1")
						)))
						.iterator().next();
				
				PSOA2A m2A2A = (PSOA2A) QueryPSOMapping.instance(m2)
						.descendants(
						cs(li(
							a2a("a1","a2")
						)))
						.iterator().next();
				
				return Tuples.t(C1.c(m1Cxt), 
					(Merge<PSOA2A>) new Replacement<PSOA2A>(Side.LHS(), m1A2A, m2A2A));
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		// ********************************************
		// CHECKS
		
		// check that merged mapping contains 3 C2Cs
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
		// merged mapping contains UNCHANGED C2Cs
		Set<Correspondence> c2cs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1"),
						c2c("C2","C3"),
						c2c("C3","C2")
				)));
		assertEquals(3, c2cs.size());
		
		// check that merged mapping contains 6 A2As
		assertEquals(6, merged.descendents(PSOA2A.class).size());
		
		// merged mapping contains correct A2As
		Set<Correspondence> c2cs2 = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1", 
							li(
							// REPLACEMENT HAPPENED!
							a2a("a1", "a2"),
							a2a("a2", "a1")
							)),
						c2c("C2","C3",
							li(
							a2a("a3", "a5"),
							a2a("a4", "a6")
							)),
						c2c("C3","C2",
							li(
							a2a("a5", "a3"),
							a2a("a6", "a4")
							))
				)));
		assertEquals(3, c2cs2.size());
		
		// check that merged mapping contains 1 R2R
		assertEquals(1, merged.descendents(PSOR2R.class).size());
		
		// merged mapping contains correct R2Rs
		Set<Correspondence> r2rs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
					r2r("r3", "r3")	
				)));
		assertEquals(1, r2rs.size());	
	}
	
	
	@Test
	public void testReplace2() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		A2AMerger replacer = new A2AMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected T2<C1<PSOC2C>,Merge<PSOA2A>> pick(ExtPSOMapping m1, 
														ExtPSOMapping m2) {
				PSOC2C m1Cxt = (PSOC2C) QueryPSOMapping.instance(m1)
						.descendants(
						cs(li(
							c2c("C2","C3")
						)))
						.iterator().next();
				
				PSOA2A m1A2A = (PSOA2A) QueryPSOMapping.instance(m1)
						.descendants(
						cs(li(
							a2a("a4","a6")
						)))
						.iterator().next();
				
				PSOA2A m2A2A = (PSOA2A) QueryPSOMapping.instance(m2)
						.descendants(
						cs(li(
							a2a("a4","a7")
						)))
						.iterator().next();
				
				return Tuples.t(C1.c(m1Cxt), 
					(Merge<PSOA2A>) new Replacement<PSOA2A>(Side.LHS(), m1A2A, m2A2A));
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		// ********************************************
		// CHECKS
		
		// check that merged mapping contains 3 C2Cs
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
		// merged mapping contains UNCHANGED C2Cs
		Set<Correspondence> c2cs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1"),
						c2c("C2","C3"),
						c2c("C3","C2")
				)));
		assertEquals(3, c2cs.size());
		
		// check that merged mapping contains 6 A2As
		assertEquals(6, merged.descendents(PSOA2A.class).size());
		
		// merged mapping contains correct A2As
		Set<Correspondence> c2cs2 = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1", 
							li(
							a2a("a1", "a1"),
							a2a("a2", "a2")
							)),
						c2c("C2","C3",
							li(
							// REPLACEMENT happened
							a2a("a3", "a5"),
							a2a("a4", "a7")
							)),
						c2c("C3","C2",
							li(
							a2a("a5", "a3"),
							a2a("a6", "a4")
							))
				)));
		assertEquals(3, c2cs2.size());
		
		// check that merged mapping contains 1 R2R
		assertEquals(1, merged.descendents(PSOR2R.class).size());
		
		// merged mapping contains correct R2Rs
		Set<Correspondence> r2rs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
					r2r("r3", "r3")	
				)));
		assertEquals(1, r2rs.size());	
	}
	
	
	@Test
	public void testInsert() {
		
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		A2AMerger replacer = new A2AMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected T2<C1<PSOC2C>,Merge<PSOA2A>> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				PSOC2C m1Cxt = (PSOC2C) QueryPSOMapping.instance(m1)
							.descendants(
							cs(li(
								c2c("C3","C2")
							)))
							.iterator().next();
				
				PSOA2A m2A2A = (PSOA2A) QueryPSOMapping.instance(m2)
							.descendants(
							cs(li(
								a2a("a7","a4")
							)))
							.iterator().next();
				return Tuples.t(C1.c(m1Cxt), 
						(Merge<PSOA2A>) new Insert<PSOA2A>(Side.LHS(), m2A2A));
				
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		System.out.println("merged: " + merged);
		
		// ********************************************
		// CHECKS
		
		// check that merged mapping contains 3 C2Cs
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
		// merged mapping contains UNCHANGED C2Cs
		Set<Correspondence> c2cs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1"),
						c2c("C2","C3"),
						c2c("C3","C2")
				)));
		assertEquals(3, c2cs.size());
		
		// check that merged mapping contains 6 A2As
		assertEquals(6, merged.descendents(PSOA2A.class).size());
		
		// merged mapping contains correct A2As
		Set<Correspondence> c2cs2 = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1", 
							li(
							a2a("a1", "a1"),
							a2a("a2", "a2")
							)),
						c2c("C2","C3",
							li(
							a2a("a3", "a5"),
							a2a("a4", "a6")
							)),
						c2c("C3","C2",
							li(
							// INSERT, REMOVAL HAPPENED!
							a2a("a5", "a3"),
							a2a("a7", "a4")
							))
				)));
		assertEquals(3, c2cs2.size());
		
		// check that merged mapping contains 1 R2R
		assertEquals(1, merged.descendents(PSOR2R.class).size());
		
		// merged mapping contains correct R2Rs
		Set<Correspondence> r2rs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
					r2r("r3", "r3")	
				)));
		assertEquals(1, r2rs.size());	
	}
	
	
	@Test
	public void testRemoval() {
		
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		A2AMerger replacer = new A2AMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel()) {
			@Override
			protected T2<C1<PSOC2C>,Merge<PSOA2A>> pick(ExtPSOMapping m1, ExtPSOMapping m2) {
				
				PSOC2C m1Cxt = (PSOC2C) QueryPSOMapping.instance(m1)
									.descendants(
									cs(li(
										c2c("C3","C2")
									)))
									.iterator().next();
				PSOA2A m1A2A = (PSOA2A) QueryPSOMapping.instance(m1)
									.descendants(
									cs(li(
										a2a("a5","a3")
									)))
									.iterator().next();
				
				return Tuples.t(C1.c(m1Cxt), 
						(Merge<PSOA2A>) new Removal<PSOA2A>(Side.LHS(), m1A2A));
			}
			
		};
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		// ********************************************
		// CHECKS
		
		// check that merged mapping contains 3 C2Cs
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
		// merged mapping contains UNCHANGED C2Cs
		Set<Correspondence> c2cs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1"),
						c2c("C2","C3"),
						c2c("C3","C2")
				)));
		assertEquals(3, c2cs.size());
		
		// check that merged mapping contains 7 A2As
		assertEquals(6, merged.descendents(PSOA2A.class).size());
		
		// merged mapping contains correct A2As
		Set<Correspondence> c2cs2 = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1", 
							li(
							a2a("a1", "a1"),
							a2a("a2", "a2")
							)),
						c2c("C2","C3",
							li(
							a2a("a3", "a5"),
							a2a("a4", "a6")
							)),
						c2c("C3","C2",
							li(
							// REMOVAL, INSERT and REPLACEMENT happened
							a2a("a6", "a3"),
							a2a("a7", "a4")
							))
				)));
		assertEquals(3, c2cs2.size());
		
		// check that merged mapping contains 1 R2R
		assertEquals(1, merged.descendents(PSOR2R.class).size());
		
		// merged mapping contains correct R2Rs
		Set<Correspondence> r2rs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
					r2r("r3", "r3")	
				)));
		assertEquals(1, r2rs.size());	
	}
	
}
