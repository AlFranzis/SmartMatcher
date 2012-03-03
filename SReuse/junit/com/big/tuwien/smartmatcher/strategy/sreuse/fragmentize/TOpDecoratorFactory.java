package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortOpsByName;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder.a2a;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder.a2c;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder.c2c;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder.r2r;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TOpDecoratorFactory {
	
	public TOpDecoratorFactory() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	public class LHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LHSMM() {
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
							a("a4"),
							a("a5")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a6"),
							a("a7"),
							a("a8")
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
	
	
	public class RHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RHSMM() {
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
	public void testC2CDecoration() {
		EasyMM lhs = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhs = new EasyMM(new RHSMM().getMetaModel());
		
		Operator c2c1 = 
			c2c(lhs.clazz("C1"), 
				rhs.clazz("C1")
			);
		
		Operator a2a1 = 
			a2a(c2c1, 
				lhs.qatt("C1.a1"), 
				rhs.qatt("C1.a1")
			);
		
		c2c1.setChildren(asSet(a2a1));
		
		//decorate
		Set<Operator> ops = OpDecoratorFactory.decorate(
										asSet(c2c1, a2a1));
		
		// **********************
		// CHECKS
		// **********************
		
		List<Operator> sOps = sortOpsByName(ops);
		
		assertEquals(1, sOps.size());
		
		Operator op1 = sOps.get(0);
		assertEquals("C2C", op1.getName());
		assertEquals(c2c1.getRoles(), op1.getRoles());
		
		assertFalse(op1.hasParents());
		
		List<Operator> sChildren = sortOpsByName(op1.getChildren());
		assertEquals(1, sChildren.size());
		
		Operator child1 = sChildren.get(0);
		assertEquals("A2A", child1.getName());
		assertEquals(a2a1.getRoles(), child1.getRoles());
		assertEquals(0, child1.getChildren().size());
		assertEquals(1, child1.getParents().size());
		assertSame(op1, child1.getParents().iterator().next());
	}
	
	
	@Test
	public void test2C2CAndR2RDecoration() {
		EasyMM lhs = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhs = new EasyMM(new RHSMM().getMetaModel());
		
		Operator c2c1 = 
			c2c(lhs.clazz("C1"), 
				rhs.clazz("C1")
			);
		
		Operator a2a1 = 
			a2a(c2c1, 
				lhs.qatt("C1.a1"), 
				rhs.qatt("C1.a1")
			);
		
		Operator a2a2 = 
			a2a(c2c1, 
				lhs.qatt("C1.a2"), 
				rhs.qatt("C1.a2")
			);
		
		Operator c2c2 = 
			c2c(lhs.clazz("C2"), 
				rhs.clazz("C2")
			);
		
		Operator a2a3 = 
			a2a(c2c2, 
				lhs.qatt("C2.a3"), 
				rhs.qatt("C2.a3")
			);
		
		Operator r2r1 = 
			r2r(c2c1,
				c2c2,
				lhs.qref("C1_r1_C2"), 
				rhs.qref("C1_r1_C2")
			);
		
		// decorate
		
		Set<Operator> ops = OpDecoratorFactory.decorate(
				asSet(c2c1, a2a1, a2a2, c2c2, a2a3, r2r1));
		
		// ***********************
		// CHECKS
		// ***********************
		
		List<Operator> sOps = sortOpsByName(ops);
		
		assertEquals(2, sOps.size());
		
		Operator op1 = sOps.get(0);
		assertEquals("C2C", op1.getName());
		assertEquals(c2c1.getRoles(), op1.getRoles());
		assertFalse(op1.hasParents());
		
		List<Operator> sOp1Children = sortOpsByName(op1.getChildren());
		assertEquals(3, sOp1Children.size());
		
		Operator child1 = sOp1Children.get(0);
		assertEquals("A2A", child1.getName());
		assertEquals(a2a1.getRoles(), child1.getRoles());
		assertEquals(asSet(op1), child1.getParents());
		assertFalse(child1.hasChildren());
		
		Operator child2 = sOp1Children.get(1);
		assertEquals("A2A", child2.getName());
		assertEquals(a2a2.getRoles(), child2.getRoles());
		assertEquals(asSet(op1), child2.getParents());
		assertFalse(child2.hasChildren());
		
		Operator op2 = sOps.get(1);
		assertEquals("C2C", op2.getName());
		assertEquals(c2c2.getRoles(), op2.getRoles());
		assertFalse(op2.hasParents());
		
		List<Operator> sOp2Children = sortOpsByName(op2.getChildren());
		assertEquals(2, sOp2Children.size());
		
		Operator child4 = sOp2Children.get(0);
		assertEquals("A2A", child4.getName());
		assertEquals(a2a3.getRoles(), child4.getRoles());
		assertEquals(asSet(op2), child4.getParents());
		
		assertSame(sOp1Children.get(2), sOp2Children.get(1));
		
		Operator child5 = sOp2Children.get(1);
		assertEquals("R2R", child5.getName());
		assertEquals(r2r1.getRoles(), child5.getRoles());
		assertEquals(asSet(op1, op2), child5.getParents());
		assertFalse(child5.hasChildren());	
	}
	
	
	@Test
	public void test2C2CWithA2CAndR2RDecoration() {
		EasyMM lhs = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhs = new EasyMM(new RHSMM().getMetaModel());
		
		Operator c2c1 = 
			c2c(lhs.clazz("C1"), 
				rhs.clazz("C1")
			);
		
		Operator a2a1 = 
			a2a(c2c1, 
				lhs.qatt("C1.a1"), 
				rhs.qatt("C1.a1")
			);
		
		Operator a2a2 = 
			a2a(c2c1, 
				lhs.qatt("C1.a2"), 
				rhs.qatt("C1.a2")
			);
		
		
		Operator c2c2 = 
			c2c(lhs.clazz("C2"), 
				rhs.clazz("C2")
			);
		
		Operator a2a3 = 
			a2a(c2c2, 
				lhs.qatt("C2.a3"), 
				rhs.qatt("C2.a3")
			);
		
		Operator r2r1 = 
			r2r(c2c1,
				c2c2,
				lhs.qref("C1_r1_C2"), 
				rhs.qref("C1_r1_C2")
			);
		
		
		
		Operator a2c1 = 
			a2c(
				c2c2, 
				lhs.qatt("C2.a4"), 
				rhs.qref("C2_r2_C3"), 
				rhs.clazz("C3"), 
				rhs.qatt("C3.a5")
			);
		
		Operator a2a4 = 
			a2a(a2c1, 
				lhs.qatt("C2.a5"), 
				rhs.qatt("C3.a6")
			);
		
		// decorate
		
		Set<Operator> ops = OpDecoratorFactory.decorate(
				asSet(c2c1, a2a1, a2a2, c2c2, a2a3, r2r1, a2c1, a2a4));
		
		// CHECKS
		
		List<Operator> sOps = sortOpsByName(ops);
		
		assertEquals(2, sOps.size());
		
		Operator op1 = sOps.get(0);
		assertEquals("C2C", op1.getName());
		assertEquals(c2c1.getRoles(), op1.getRoles());
		assertFalse(op1.hasParents());
		
		List<Operator> sOp1Children = sortOpsByName(op1.getChildren());
		assertEquals(3, sOp1Children.size());
		
		Operator child1 = sOp1Children.get(0);
		assertEquals("A2A", child1.getName());
		assertEquals(a2a1.getRoles(), child1.getRoles());
		assertEquals(asSet(op1), child1.getParents());
		assertFalse(child1.hasChildren());
		
		Operator child2 = sOp1Children.get(1);
		assertEquals("A2A", child2.getName());
		assertEquals(a2a2.getRoles(), child2.getRoles());
		assertEquals(asSet(op1), child2.getParents());
		assertFalse(child2.hasChildren());
		
		Operator op2 = sOps.get(1);
		assertEquals("C2C", op2.getName());
		assertEquals(c2c2.getRoles(), op2.getRoles());
		assertFalse(op2.hasParents());
		
		List<Operator> sOp2Children = sortOpsByName(op2.getChildren());
		assertEquals(3, sOp2Children.size());
		
		Operator child4 = sOp2Children.get(0);
		assertEquals("A2A", child4.getName());
		assertEquals(a2a3.getRoles(), child4.getRoles());
		assertEquals(asSet(op2), child4.getParents());
		assertFalse(child4.hasChildren());
		
		Operator child6 = sOp2Children.get(1);
		assertEquals("A2C", child6.getName());
		assertEquals(a2c1.getRoles(), child6.getRoles());
		assertEquals(asSet(op2), child6.getParents());
		assertTrue(child6.hasChildren());
		
		List<Operator> child6Children = sortOpsByName(child6.getChildren());
		assertEquals(1, child6Children.size());
		
		Operator child7 = child6Children.get(0);
		assertEquals("A2A", child7.getName());
		assertEquals(a2a4.getRoles(), child7.getRoles());
		assertEquals(asSet(child6), child7.getParents());
		assertFalse(child7.hasChildren());
		
		// it's the same R2R for both C2Cs
		assertSame(sOp1Children.get(2), sOp2Children.get(2));
		
		Operator child5 = sOp2Children.get(2);
		assertEquals("R2R", child5.getName());
		assertEquals(r2r1.getRoles(), child5.getRoles());
		assertEquals(asSet(op1, op2), child5.getParents());
		assertFalse(child5.hasChildren());		
	}
	
	
	@Test
	public void test2C2CWithA2CAndR2RDecorationAndHiddenChildren() {
		EasyMM lhs = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhs = new EasyMM(new RHSMM().getMetaModel());
		
		Operator c2c1 = 
			c2c(lhs.clazz("C1"), 
				rhs.clazz("C1")
			);
		
		Operator a2a1 = 
			a2a(c2c1, 
				lhs.qatt("C1.a1"), 
				rhs.qatt("C1.a1")
			);
		
		Operator a2a2 = 
			a2a(c2c1, 
				lhs.qatt("C1.a2"), 
				rhs.qatt("C1.a2")
			);
		
		
		Operator c2c2 = 
			c2c(lhs.clazz("C2"), 
				rhs.clazz("C2")
			);
		
		Operator a2a3 = 
			a2a(c2c2, 
				lhs.qatt("C2.a3"), 
				rhs.qatt("C2.a3")
			);
		
		Operator r2r1 = 
			r2r(c2c1,
				c2c2,
				lhs.qref("C1_r1_C2"), 
				rhs.qref("C1_r1_C2")
			);
		
		
		
		Operator a2c1 = 
			a2c(
				c2c2, 
				lhs.qatt("C2.a4"), 
				rhs.qref("C2_r2_C3"), 
				rhs.clazz("C3"), 
				rhs.qatt("C3.a5")
			);
		
		Operator a2a4 = 
			a2a(a2c1, 
				lhs.qatt("C2.a5"), 
				rhs.qatt("C3.a6")
			);
		
		// decorate
		
		Set<Operator> ops = OpDecoratorFactory.decorate(
				asSet(c2c1, a2a1, c2c2, a2a3, r2r1));
		
		// CHECKS
		
		List<Operator> sOps = sortOpsByName(ops);
		
		assertEquals(2, sOps.size());
		
		Operator op1 = sOps.get(0);
		assertEquals("C2C", op1.getName());
		assertEquals(c2c1.getRoles(), op1.getRoles());
		assertFalse(op1.hasParents());
		
		List<Operator> sOp1Children = sortOpsByName(op1.getChildren());
		assertEquals(2, sOp1Children.size());
		
		Operator child1 = sOp1Children.get(0);
		assertEquals("A2A", child1.getName());
		assertEquals(a2a1.getRoles(), child1.getRoles());
		assertEquals(asSet(op1), child1.getParents());
		assertFalse(child1.hasChildren());
		
		Operator op2 = sOps.get(1);
		assertEquals("C2C", op2.getName());
		assertEquals(c2c2.getRoles(), op2.getRoles());
		assertFalse(op2.hasParents());
		
		List<Operator> sOp2Children = sortOpsByName(op2.getChildren());
		assertEquals(2, sOp2Children.size());
		
		Operator child4 = sOp2Children.get(0);
		assertEquals("A2A", child4.getName());
		assertEquals(a2a3.getRoles(), child4.getRoles());
		assertEquals(asSet(op2), child4.getParents());
		assertFalse(child4.hasChildren());
		
		// it's the same R2R for both C2Cs
		assertSame(sOp1Children.get(1), sOp2Children.get(1));
		
		Operator child5 = sOp2Children.get(1);
		assertEquals("R2R", child5.getName());
		assertEquals(r2r1.getRoles(), child5.getRoles());
		assertEquals(asSet(op1, op2), child5.getParents());
		assertFalse(child5.hasChildren());		
	}
	
}
