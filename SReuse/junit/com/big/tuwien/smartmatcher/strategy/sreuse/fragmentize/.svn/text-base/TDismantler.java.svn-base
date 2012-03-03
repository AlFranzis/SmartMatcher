package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder;

public class TDismantler {
	
	public TDismantler() {
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
	public void testDismantlingofC2CwithA2C() {
		MetaModel lhsMM = new LHSMM().getMetaModel();
		MetaModel rhsMM = new RHSMM().getMetaModel();
		
		Element lhsC1 = lhsMM.getClassByName("C1");
		Element rhsC1 = rhsMM.getClassByName("C1");
		C2CBubble c2c = BubbleBuilder.c2c(lhsC1, rhsC1);
		
		Element lhsA1 = lhsMM.getAttributeByQName("C1.a1");
		Element rhsC2 = rhsMM.getClassByName("C2");
		Element rhsR1 = rhsMM.getReferenceByName("r1");
		Element rhsA3 = rhsMM.getAttributeByQName("C2.a3");
		A2CBubble a2c = BubbleBuilder.a2c(c2c, lhsA1, rhsC2, rhsA3, rhsR1);
		
		Dismantler dismantler = new Dismantler();
		// convert bubbles to operators
		Set<Operator> ops = dismantler
							.dismantle2(
			Helpers.<Bubble
			<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
			asSet(c2c, a2c));
		
		// *************************
		// CHECKS
		// *************************
		
		assertEquals(1, ops.size());
		
		Operator opC2C = ops.iterator().next();
		assertEquals(c2c.getOperatorName(), opC2C.getName());
		
		// check C2C roles
		Set<Element> opRoles = new HashSet<Element>(opC2C.getRoles().values());
		Set<Element> bRoles = new HashSet<Element>(c2c.getConfiguration()
												.getRoles().values());
		assertEquals(bRoles, opRoles);
		
		// A2C is only child of C2C
		assertEquals(1, opC2C.getChildren().size());
		
		Operator opA2C = opC2C.getChildren().iterator().next();
		assertEquals(a2c.getOperatorName(), opA2C.getName());
		
		// check A2C roles
		opRoles = new HashSet<Element>(opA2C.getRoles().values());
		bRoles = new HashSet<Element>(a2c.getConfiguration()
									.getRoles().values());
		assertEquals(bRoles, opRoles);
	}
	
	
	@Test
	public void testDismantlingofC2CsWithA2AsAndR2R() {
		MetaModel lhsMM = new LHSMM().getMetaModel();
		MetaModel rhsMM = new RHSMM().getMetaModel();
		
		Element lhsC1 = lhsMM.getClassByName("C1");
		Element rhsC1 = rhsMM.getClassByName("C1");
		C2CBubble c2c1 = BubbleBuilder.c2c(lhsC1, rhsC1);
		
		Element lhsC2 = lhsMM.getClassByName("C2");
		Element rhsC2 = rhsMM.getClassByName("C2");
		C2CBubble c2c2 = BubbleBuilder.c2c(lhsC2, rhsC2);
		
		Element lhsA1 = lhsMM.getAttributeByQName("C1.a1");
		Element rhsA1 = rhsMM.getAttributeByQName("C1.a1");
		A2ABubble a2a1 = BubbleBuilder.a2a(c2c1, lhsA1, rhsA1);
		
		Element lhsA2 = lhsMM.getAttributeByQName("C1.a2");
		Element rhsA2 = rhsMM.getAttributeByQName("C1.a2");
		A2ABubble a2a2 = BubbleBuilder.a2a(c2c2, lhsA2, rhsA2);
		
		Element lhsR1 = lhsMM.getReferenceByName("r1");
		Element rhsR1 = rhsMM.getReferenceByName("r1");
		R2RBubble r2r = BubbleBuilder.r2r(c2c1, c2c2, lhsR1, rhsR1);
		
		
		Dismantler dismantler = new Dismantler();
		// convert bubbles to operators
		Set<Operator> ops = dismantler
							.dismantle2(
		Helpers.<Bubble
		<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
			asSet(c2c1, a2a1, a2a2, c2c2, r2r)
			);
		
		// *************************
		// CHECKS
		// *************************
		
		// 2 C2C root operators
		assertEquals(2, ops.size());
		
		Set<Element> c2c1Roles = new HashSet<Element>(c2c1.getConfiguration()
												.getRoles().values());
		Set<Element> c2c2Roles = new HashSet<Element>(c2c2.getConfiguration()
												.getRoles().values());
		for(Operator opC2C : ops) {
			assertEquals("C2C", opC2C.getName());
			
			// check C2C roles
			Set<Element> opRoles = new HashSet<Element>(opC2C.getRoles()
															.values());
			assertTrue(opRoles.equals(c2c1Roles) 
						|| opRoles.equals(c2c2Roles));
			assertTrue(opC2C.getChildren().size() == 2);
			
			// each C2C contains an A2A and a R2R child
			for(Operator cOp : opC2C.getChildren()) {
				assertTrue(cOp.getName().equals("A2A") 
							|| cOp.getName().equals("R2R"));
			}
			
		}
	}
}
