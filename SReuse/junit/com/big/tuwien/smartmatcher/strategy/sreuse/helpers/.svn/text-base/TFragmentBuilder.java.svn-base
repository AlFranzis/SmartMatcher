package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getAttributes;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getReferences;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortByName;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;

public class TFragmentBuilder extends FragmentBuilder {

	public class MM extends MetaModelBuilder {
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
	public void testPerfectSyntax() {
		init(new MM().getMetaModel());
		
//		Fragment f = 
//			f(
//				c("C1",
//				li(
//					"a1",
//					"a2"
//				)),
//				c("C2",
//				li(
//					"a4"
//				))
//			);
	}
	
	
	@Test
	public void testSimpleFragmentBuilding() {
		init(new MM().getMetaModel());
		
		Fragment f = f("C1", "C1.a1", "C1_r1_C2", "C2");
		Set<Element> classes = f.getClasses();
		assertEquals(2, classes.size());
		
		List<Element> sclasses = sortByName(classes);
		
		Element c1 = sclasses.get(0);
		assertEquals("C1", c1.getName());
		
		List<Element> c1Atts = getAttributes(c1);
		assertEquals(1, c1Atts.size());
		Element a1 = c1Atts.get(0);
		assertEquals(a1.getName(), "a1");
		
		List<Element> c1Refs = getReferences(c1);
		assertEquals(1, c1Refs.size());
		Element r1 = c1Refs.get(0);
		assertEquals(r1.getName(), "r1");
		
		Element c2 = sclasses.get(1);
		assertEquals("C2", c2.getName());
		
		List<Element> c2Atts = getAttributes(c2);
		assertEquals(0, c2Atts.size());
		
		List<Element> c2Refs = getReferences(c2);
		assertEquals(0, c2Refs.size());
	}
}
