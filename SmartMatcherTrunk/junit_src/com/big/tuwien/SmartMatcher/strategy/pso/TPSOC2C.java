package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class TPSOC2C {
	
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
	public void test1() {
		MetaModel lhsMM = new LhsMM().getMetaModel();
		MetaModel rhsMM = new RhsMM().getMetaModel();
		
		Element lhsC1 = lhsMM.getClasses().get(0);
		Element rhsC1 = rhsMM.getClasses().get(0);
		
		PSOMapping m1 = new PSOMapping();
		PSOC2C c2c1 = new PSOC2C(m1, lhsC1, rhsC1);
		m1.add(c2c1);
		
		PSOMapping m2 = new PSOMapping();
		PSOC2C c2c2 = new PSOC2C(m2, lhsC1, rhsC1);
		m2.add(c2c2);
		
		assertTrue(m1.equals(m2));
		assertTrue(m2.equals(m1));
	}
	
	
	@Test
	public void test2() {
		MetaModel lhsMM = new LhsMM().getMetaModel();
		MetaModel rhsMM = new RhsMM().getMetaModel();
		
		Element lhsC1 = lhsMM.getClasses().get(0);
		
		Element lhsA1 = ((ClassElement)lhsC1.getRepresentedBy()).
								getAttributes().get(0).getRepresents();
		
		Element rhsC1 = rhsMM.getClasses().get(0);
		
		Element rhsA1 = ((ClassElement)rhsC1.getRepresentedBy()).
								getAttributes().get(0).getRepresents();
		
		PSOMapping m1 = new PSOMapping();
		PSOC2C c2c1 = new PSOC2C(m1, lhsC1, rhsC1);
		
		PSOA2A a2a1 = new PSOA2A(C1.c(c2c1), lhsA1, rhsA1);
		c2c1.add(a2a1);
		
		m1.add(c2c1);
		
	
		
		PSOMapping m2 = new PSOMapping();
		PSOC2C c2c2 = new PSOC2C(m2, lhsC1, rhsC1);
		
		m2.add(c2c2);
		
		PSOA2A a2a2 = new PSOA2A(C1.c(c2c2), lhsA1, rhsA1);
		c2c2.add(a2a2);
		
		assertTrue(a2a1.equals(a2a2));
		assertTrue(a2a2.equals(a2a1));
		
		assertTrue(c2c1.equals(c2c2));
		assertTrue(c2c2.equals(c2c1));
		
		assertTrue(m1.equals(m2));
		assertTrue(m2.equals(m1));
	}

}
