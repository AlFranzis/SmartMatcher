package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class TMappingBuilder extends MappingBuilder {
	private LhsMM lhsMM = new LhsMM();
	private RhsMM rhsMM = new RhsMM();
	
	public TMappingBuilder() {}
	
	
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
						r("r1", c("C1"), c("C2"))
					));
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
						r("r1", c("C1"), c("C2"))
					));
		}
	}
	
	
	@Test
	public void modelContent() {
		
		init(lhsMM.getMetaModel(),rhsMM.getMetaModel());
		PSOMapping m =
			m(li(
					c2c("C1","C1", 
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
			
					r2r("r1","r1", 
							c2c("c2c1"), 
							c2c("c2c2")
					)
				)
			);
		
		assertEquals(7, m.descendents().size());
		assertEquals(2, m.children.size());
		assertTrue(m.children.toString().contains("C2C(C1,C1)"));
		assertTrue(m.children.toString().contains("C2C(C2,C2)"));
		Iterator<Correspondence> c2cs = m.children.iterator();
		assertEquals(3, c2cs.next().descendents().size());
		assertEquals(3, c2cs.next().descendents().size());
	}
	
	
	
}
