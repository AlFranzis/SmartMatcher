package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortOpsByName;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;

public class TFragmentMappingBuilder extends EasyFragmentMappingBuilder {
	private LhsMM lhsMM = new LhsMM();
	private RhsMM rhsMM = new RhsMM();
	
	
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
	public void testFragmentMappingBuildingWithStdNotation() {
		FragmentBuilder lhsFb = 
			new FragmentBuilder(lhsMM.getMetaModel());
		
		Fragment_ f1 = lhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		FragmentBuilder rhsFb = 
			new FragmentBuilder(rhsMM.getMetaModel());
		
		Fragment_ f2 = rhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		init(f1, f2);
		
		// this is the standard notation to build a fragment mapping
		
		FragmentMapping fm =
			fm(li(
					op("C2C", 
						map("lhsFocusElement", "C1"),
						map("rhsFocusElement", "C1"),
						li(
							op("A2A", 
								map("lhsFocusElement", "C1.a1"),
								map("rhsFocusElement", "C1.a1")
							)
						)
					).as("C2C1"),
					op("C2C", 
						map("lhsFocusElement", "C2"),
						map("rhsFocusElement", "C2"),
						li(
							op("A2A", 
								map("lhsFocusElement", "C2.a3"),
								map("rhsFocusElement", "C2.a3")
							)
						)
					).as("C2C2"),
					op("R2R", 
						li(op("C2C1"), op("C2C2")),
						map("lhsFocusElement", "C1_r1_C2"),
						map("rhsFocusElement", "C1_r1_C2")
					)
			));
		
		Set<Operator> fops = fm.getFlattenedOperators();
		assertEquals(5, fops.size());
		
		Set<Operator> ops = fm.getOperators();
		// 2 root operators
		assertEquals(2, ops.size());
		
		List<Operator> sops = sortOpsByName(ops);
		Operator c2c1 = sops.get(0);
		assertEquals("C2C", c2c1.getName());
		assertEquals(0, c2c1.getParents().size());
		
		List<Operator> sC2C1Children = sortOpsByName(c2c1.getChildren());
		assertEquals(2, sC2C1Children.size());
		
		Operator a2a1 = sC2C1Children.get(0);
		assertEquals("A2A", a2a1.getName());
		assertEquals(1, a2a1.getParents().size());
		assertTrue(a2a1.getParents().contains(c2c1));
		
		
		Operator c2c2 = sops.get(1);
		assertEquals("C2C", c2c2.getName());
		assertEquals(0, c2c2.getParents().size());
		
		List<Operator> sC2C2Children = sortOpsByName(c2c2.getChildren());
		assertEquals(2, sC2C2Children.size());
		
		Operator a2a2 = sC2C2Children.get(0);
		assertEquals("A2A", a2a2.getName());
		assertEquals(1, a2a2.getParents().size());
		assertTrue(a2a2.getParents().contains(c2c2));
		
		Operator r2r1 = sC2C1Children.get(1);
		// R2R of C2C1 and C2C2 is the same
		assertSame(r2r1, sC2C2Children.get(1));
		assertEquals("R2R", r2r1.getName());
		assertEquals(2, r2r1.getParents().size());
		
		for(Operator c2c : r2r1.getParents()) {
			assertTrue(c2c == c2c1 || c2c == c2c2);
		}
		assertTrue(r2r1.getParents().contains(c2c2));
		assertTrue(r2r1.getParents().contains(c2c1));
	}
	
	
	@Test
	public void testFragmentMappingBuildingWithEasyNotation() {
		FragmentBuilder lhsFb = 
			new FragmentBuilder(lhsMM.getMetaModel());
		
		Fragment_ f1 = lhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		FragmentBuilder rhsFb = 
			new FragmentBuilder(rhsMM.getMetaModel());
		
		Fragment_ f2 = rhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		init(f1, f2);
		
		// this is the easy (simpler) notation to build a fragment mapping
		// same mapping as in test case above
		FragmentMapping fm =
			fm(li(
				c2c("C1", "C1",
				li(
					a2a("C1.a1", "C1.a1")	
				)
				).as("C2C1"),
				c2c("C2","C2",
				li(
					a2a("C2.a3", "C2.a3")
				)
				).as("C2C2"),
				r2r(li(op("C2C1"), op("C2C2")), 
					"C1_r1_C2", "C1_r1_C2")
			));
		
		Set<Operator> fops = fm.getFlattenedOperators();
		assertEquals(5, fops.size());
		
		Set<Operator> ops = fm.getOperators();
		// 2 root operators
		assertEquals(2, ops.size());
		
		List<Operator> sops = sortOpsByName(ops);
		Operator c2c1 = sops.get(0);
		assertEquals("C2C", c2c1.getName());
		assertEquals(0, c2c1.getParents().size());
		
		List<Operator> sC2C1Children = sortOpsByName(c2c1.getChildren());
		assertEquals(2, sC2C1Children.size());
		
		Operator a2a1 = sC2C1Children.get(0);
		assertEquals("A2A", a2a1.getName());
		assertEquals(1, a2a1.getParents().size());
		assertTrue(a2a1.getParents().contains(c2c1));
		
		
		Operator c2c2 = sops.get(1);
		assertEquals("C2C", c2c2.getName());
		assertEquals(0, c2c2.getParents().size());
		
		List<Operator> sC2C2Children = sortOpsByName(c2c2.getChildren());
		assertEquals(2, sC2C2Children.size());
		
		Operator a2a2 = sC2C2Children.get(0);
		assertEquals("A2A", a2a2.getName());
		assertEquals(1, a2a2.getParents().size());
		assertTrue(a2a2.getParents().contains(c2c2));
		
		Operator r2r1 = sC2C1Children.get(1);
		// R2R of C2C1 and C2C2 is the same
		assertSame(r2r1, sC2C2Children.get(1));
		assertEquals("R2R", r2r1.getName());
		assertEquals(2, r2r1.getParents().size());
		
		for(Operator c2c : r2r1.getParents()) {
			assertTrue(c2c == c2c1 || c2c == c2c2);
		}
		assertTrue(r2r1.getParents().contains(c2c2));
		assertTrue(r2r1.getParents().contains(c2c1));
	}
	
	
	
}
