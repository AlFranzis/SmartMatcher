package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class TMetaModelBuilder extends MetaModelBuilder {
	
	@Test
	public void modelContent() {
		MetaModel mm = 
			mm(li(
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
		
		assertEquals(7, mm.getElements().size());
		
		assertNotNull(mm.getClassByName("C1"));
		assertNotNull(mm.getClassByName("C2"));
		assertNotNull(mm.getAttributeByQName("C1.a1"));
		assertNotNull(mm.getAttributeByQName("C1.a2"));
		assertNotNull(mm.getAttributeByQName("C2.a3"));
		assertNotNull(mm.getAttributeByQName("C2.a4"));
		assertNotNull(mm.getReferenceByQName("C1_r1_C2"));
		
		assertNull(mm.getAttributeByQName("C1.a3"));
		assertNull(mm.getAttributeByQName("C2.a1"));
		assertNull(mm.getReferenceByQName("C2_r1_C1"));
			
	}
	
	
	@Test
	public void modelContent2() {
		MetaModel_ mm = 
			mm(li(
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
		
		assertNotNull(mm.get("C1"));
		assertNotNull(mm.get("C2"));
		assertNotNull(mm.get("a1"));
		assertNotNull(mm.get("a4"));
		assertNotNull(mm.get("r1"));
			
	}
	
	
	@Test
	public void structuralAttributeConsistency() {
		MetaModel mm = 
			mm(li(
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
		
		assertTrue(mm.getClassByName("C1").getRepresentedBy() instanceof ClassElement);
		ClassElement c1 = (ClassElement) mm.getClassByName("C1").getRepresentedBy();
		assertEquals(2, c1.getAttributes().size());
		assertEquals("a1", c1.getAttributes().get(0).getRepresents().getName());
		assertEquals("a2", c1.getAttributes().get(1).getRepresents().getName());
	}
	
	
	@Test
	public void structuralReferenceConsistency() {
		MetaModel mm = 
			mm(li(
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
		
		Element r = mm.getReferenceByName("r1");
		assertTrue(r.getRepresentedBy() instanceof ReferenceElement);
		ReferenceElement r1 = (ReferenceElement) r.getRepresentedBy();
		assertEquals("C1", r1.getContainedIn().getRepresents().getName());
		assertEquals("C2", r1.getPointsTo().getRepresents().getName());
	}
	
	
	
	
	
}
