package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping;


/**
 * Tests the {@link R2RReplacer} for LHS and RHS metamodels that have EQUAL
 * number of classes.
 * @author alex
 *
 */
public class TR2RMergerStdImplLHSgtRHS extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TR2RMergerStdImplLHSgtRHS() {
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
					r("r3", c("C2"), c("C3")),
					r("r4", c("C3"), c("C2"))
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
						r2r("r1","r2", 
								c2c("c2c1"), 
								c2c("c2c2")
						),
				
						r2r("r2","r1", 
								c2c("c2c1"), 	
								c2c("c2c2")
						),
						r2r("r4","r3", 
								c2c("c2c2"), 	
								c2c("c2c3")
						)
					)
				);
			
			return m;
		}
	}
	
	
	@Test
	public void testPickingImpl() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		R2RMerger replacer = new R2RMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel());
		
		// perform merge
		PSOMapping merged = replacer.merge(m1, m2);
		
		// ********************************************
		// CHECKS
		
		// check that merged mapping contains 3 C2Cs
		assertEquals(3, merged.descendents(PSOC2C.class).size());
		
		// merged mapping contains correct C2Cs
		Set<Correspondence> c2cs = QueryPSOMapping.instance(merged).descendants(
				cs(li(
						c2c("C1","C1"),
						c2c("C2","C2"),
						c2c("C3","C3")
				)));
		assertEquals(3, c2cs.size());
		
		// check that merged mapping contains 7 A2As
		assertEquals(7, merged.descendents(PSOA2A.class).size());
		
		// merged mapping contains correct A2As
		Set<Correspondence> a2as = QueryPSOMapping.instance(merged).descendants(
				cs(li(
					c2c("C1", "C1", 
					li(
						a2a("a1","a1"), 
						a2a("a2","a2")
					)),
					c2c("C2", "C2", 
					li(
						a2a("a3","a3"), 
						a2a("a4","a4")
					)),
					c2c("C3", "C3",
					li(
							a2a("a5", "a5"),
							a2a("a6", "a6"),
							a2a("a7", "a7")
					))
				)));
		assertEquals(3, a2as.size());	
	}
	
	
	@Test
	public void testRepeatedMerging() {
	
		PSOMapping m1 = new Mapping1().getMapping();
		PSOMapping m2 = new Mapping2().getMapping();
		
		R2RMerger replacer = new R2RMerger(
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
		
		R2RMerger replacer = new R2RMerger(
									lhsMM.getMetaModel(), 
									rhsMM.getMetaModel());
		
		// try to perform merge -> no merging possible for identical mappings
		PSOMapping merged = replacer.merge(m1, m1);
		
		assertEquals(m1, merged);
	}
	
}
