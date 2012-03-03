package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder.MetaModel_;


public class TChecker extends MappingBuilder {
	
	public TChecker() {}

	
	public class LhsMM extends MetaModelBuilder {
		public MetaModel_ getMetaModel() {
			return mm(li(
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
	}
	
	
	public class RhsMM extends MetaModelBuilder {
		public MetaModel_ getMetaModel() {
			return mm(li(
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
	}
	
	
	@Test
	public void testCompleteMapping() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
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
		
		assertTrue(Checker.isComplete(m, lhsMM, rhsMM));
	}
	
	
	@Test
	public void testC2CIncompleteMapping() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
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
					// C3 and C3 are UNMAPPED !
					r2r("r1","r1", 
							c2c("c2c1"), 
							c2c("c2c2")
					),
			
					r2r("r2","r2", 
							c2c("c2c1"), 	
							c2c("c2c2")
							)
				)
			);
		
		assertFalse(Checker.isComplete(m, lhsMM, rhsMM));
	}
	
	
	@Test
	public void testA2AIncompleteMapping() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
		PSOMapping m =
			m(li(
					c2c("C1", "C1", 
							li(
								// a1 and a1 are UNMAPPED!
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
		
		assertFalse(Checker.isComplete(m, lhsMM, rhsMM));
	}
	
	
	@Test
	public void testR2RIncompleteMapping1() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
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
					// r2 and r2 are UNMAPPED!
					r2r("r3","r3", 
							c2c("c2c2"), 	
							c2c("c2c3")
					)
				)
			);
		
		assertFalse(Checker.isComplete(m, lhsMM, rhsMM));
	}
	
	
	@Test
	public void testR2RIncompleteMapping2() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
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
					)
					// r3 and r3 are UNMAPPED!
				)
			);
		
		assertFalse(Checker.isComplete(m, lhsMM, rhsMM));
	}
	
}
