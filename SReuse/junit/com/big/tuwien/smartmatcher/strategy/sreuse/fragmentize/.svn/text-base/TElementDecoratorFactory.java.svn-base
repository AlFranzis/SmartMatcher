package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortByName;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortByName3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TElementDecoratorFactory {

	public class MM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public MM() {
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
	
	
	@Test
	public void testDecorationOfSingleClass() {
		MetaModel mm = new MM().getMetaModel();
		Set<Element> es = asSet(
					mm.getClassByName("C1")
				);
		
		// decorate
		Set<Element> classes = ElementDecoratorFactory.decorate(es);
		
		// CHECKS
		assertEquals(1, classes.size());
		
		Element clazz1 = classes.iterator().next();
		assertEquals("C1", clazz1.getName());
		
		ClassElement ce = (ClassElement) clazz1.getRepresentedBy();
		assertEquals(0, ce.getAttributes().size());
		assertEquals(0, ce.getReferences().size());
	}
	
	
	@Test
	public void testDecorationOfSingleClassWithAttributes() {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		Set<Element> es = asSet(
				mm.clazz("C1"), 
				mm.qatt("C1.a1")
				);
		
		// decorate
		Set<Element> classes = ElementDecoratorFactory.decorate(es);
		
		// CHECKS
		assertEquals(1, classes.size());
		
		// class c1 checks
		Element c1 = classes.iterator().next();
		assertEquals("C1", c1.getName());
		assertSame(c1.getRepresentedBy().getRepresents(), c1);
		
		ClassElement ce = (ClassElement) c1.getRepresentedBy();
		
		assertEquals(1, ce.getAttributes().size());
		
		// attributes checks
		List<AttributeElement> sAtts = sortByName3(ce.getAttributes());
		
		Element a1 = sAtts.get(0).getRepresents();
		assertEquals("a1", a1.getName());
		assertSame(a1.getRepresentedBy().getRepresents(), a1);
		
		ClassElement a1Container = a1.getRepresentedBy().getContainedIn();
		assertEquals(a1Container, ce);
		
		// reference checks
		assertEquals(0, ce.getReferences().size());
	}
	
	@Test
	public void testDecorationOf2ClassesWithAttributesAndReference() {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		Set<Element> es = asSet(
				mm.clazz("C1"), 
				mm.qatt("C1.a1"),
				mm.qatt("C1.a2"),
				mm.qatt("C2.a3"),
				mm.clazz("C2"),
				mm.qref("C1_r1_C2")
				);
		
		// decorate
		Set<Element> classes = ElementDecoratorFactory.decorate(es);
		
		List<Element> aClasses = sortByName(classes);
		
		// CHECKS
		
		assertEquals(2, aClasses.size());
		
		Element c1 = aClasses.get(0);
		assertEquals("C1", c1.getName());
		
		ClassElement ce1 = (ClassElement) c1.getRepresentedBy();
		assertEquals(2, ce1.getAttributes().size());
		
		List<AttributeElement> sAtts = sortByName3(ce1.getAttributes());
		Element a1 = sAtts.get(0).getRepresents();
		assertEquals("a1", a1.getName());
		ClassElement a1Container = a1.getRepresentedBy().getContainedIn();
		assertEquals(a1Container, ce1);
		
		
		assertEquals(1, ce1.getReferences().size());
		
		Element c2 = aClasses.get(1);
		assertEquals("C2", c2.getName());
		
		ClassElement ce2 = (ClassElement) c2.getRepresentedBy();
		assertEquals(1, ce2.getAttributes().size());
		assertEquals(0, ce2.getReferences().size());
		
		Element pointedTo = ce1.getReferences().get(0).getPointsTo().getRepresents();
		assertSame(pointedTo, c2);
	}
		
}
