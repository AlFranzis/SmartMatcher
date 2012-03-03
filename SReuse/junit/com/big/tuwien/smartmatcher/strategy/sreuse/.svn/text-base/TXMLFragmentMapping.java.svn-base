package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortXmlOpsByName;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.TFragmentizer2;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentsSet3;

public class TXMLFragmentMapping {
	
	public TXMLFragmentMapping() {
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
	public void testSimpleXMLFragmentMappingCreation() throws Exception {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		Element lhsC1 =  lhsMM.clazz("C1");
		Element lhsA1 =  lhsMM.qatt("C1.a1");
		Element lhsC2 =  lhsMM.clazz("C2");
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		Element rhsC1 =  rhsMM.clazz("C1");
		Element rhsA1 =  rhsMM.qatt("C1.a1");
		Element rhsC2 =  rhsMM.clazz("C2");
	
		XMLElementResolverFactory resolverFac = 
						new XMLElementResolverFactory();
		
		// construct mapping with 2 C2Cs, 1 A2A
		FragmentMapping fm = new FragmentMapping();
		Operator op1 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op1.setId("OP1");
		Operator op2 = OperatorBuilder.a2a(op1, lhsA1, rhsA1);
		op2.setId("OP2");
		Operator op3 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op3.setId("OP3");
		// only set root operators !
		fm.setOperators(asSet(op1, op3));
		
		XMLFragmentMapping xmlFM = 
			XMLFragmentMapping.getInstance(fm, resolverFac);
		
		// ****************
		// CHECKS
		// ****************
		
		assertEquals(2, xmlFM.getOperators().size());
		assertEquals(3, xmlFM.getFlattenedOperators().size());
		
		// CHECKS for operators
		Set<XMLOperator> xmlFmOps = xmlFM.getFlattenedOperators();
		List<XMLOperator> sXmlFmOps = sortXmlOpsByName(xmlFmOps);
		
		XMLOperator fmC2C1 = sXmlFmOps.get(1);
		assertEquals(0, fmC2C1.getParents().size());
		assertEquals(1, fmC2C1.getChildren().size());
		
		XMLOperator fmC2C2 = sXmlFmOps.get(2);
		assertEquals(0, fmC2C2.getParents().size(), 0);
		assertEquals(0, fmC2C2.getChildren().size(), 0);
		
		XMLOperator fmA2A1 = sXmlFmOps.get(0);
		assertEquals(1, fmA2A1.getParents().size());
		assertEquals(0, fmA2A1.getChildren().size());
	}
	
	
	@Test
	public void testComplexXMLFragmentMappingCreation() throws Exception {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		Element lhsC1 =  lhsMM.clazz("C1");
		Element lhsA1 =  lhsMM.qatt("C1.a1");
		Element lhsC2 =  lhsMM.clazz("C2");
		Element lhsR1 = lhsMM.qref("C1_r1_C2");
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		Element rhsC1 =  rhsMM.clazz("C1");
		Element rhsA1 =  rhsMM.qatt("C1.a1");
		Element rhsC2 =  rhsMM.clazz("C2");
		Element rhsR1 = rhsMM.qref("C1_r1_C2");
	
		XMLElementResolverFactory resolverFac = 
						new XMLElementResolverFactory();
		
		// construct mapping with 2 C2Cs, 1 A2A, 1 R2R
		FragmentMapping fm = new FragmentMapping();
		Operator op1 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op1.setId("OP1");
		Operator op2 = OperatorBuilder.a2a(op1, lhsA1, rhsA1);
		op2.setId("OP2");
		Operator op3 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op3.setId("OP3");
		Operator op4 = OperatorBuilder.r2r(op1, op3, lhsR1, rhsR1);
		op4.setId("OP4");
		// only set root operators !
		fm.setOperators(asSet(op1, op3));
		
		// ****************
		// CHECKS
		// ****************
		
		assertEquals(2, fm.getOperators().size());
		assertEquals(4, fm.getFlattenedOperators().size());
		
//		TFragmentizer2.printFragmentMapping(fm);
//		
//		System.out.println("\n");
		
		XMLFragmentMapping xmlFM = 
			XMLFragmentMapping.getInstance(fm, resolverFac);
		
		assertEquals(2, xmlFM.getOperators().size());
		assertEquals(4, xmlFM.getFlattenedOperators().size());
		
//		TFragmentizer2.printFragmentMapping2(xmlFM);
		
		// CHECKS for operators
		Set<XMLOperator> xmlFmOps = xmlFM.getFlattenedOperators();
		List<XMLOperator> sXmlFmOps = sortXmlOpsByName(xmlFmOps);
		
		XMLOperator fmC2C1 = sXmlFmOps.get(1);
		assertEquals(0, fmC2C1.getParents().size());
		assertEquals(2, fmC2C1.getChildren().size());
		
		XMLOperator fmC2C2 = sXmlFmOps.get(2);
		assertEquals(0, fmC2C2.getParents().size(), 0);
		assertEquals(1, fmC2C2.getChildren().size(), 0);
		
		XMLOperator fmA2A1 = sXmlFmOps.get(0);
		assertEquals(1, fmA2A1.getParents().size());
		assertEquals(0, fmA2A1.getChildren().size());
		
		XMLOperator fmR2R1 = sXmlFmOps.get(3);
		assertEquals(2, fmR2R1.getParents().size());
		assertEquals(0, fmR2R1.getChildren().size());
	}
	
	
	@Test
	public void testMultipleComplexXMLFragmentMappingCreation() throws Exception {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		Element lhsC1 =  lhsMM.clazz("C1");
		Element lhsA1 =  lhsMM.qatt("C1.a1");
		Element lhsC2 =  lhsMM.clazz("C2");
		Element lhsR1 = lhsMM.qref("C1_r1_C2");
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		Element rhsC1 =  rhsMM.clazz("C1");
		Element rhsA1 =  rhsMM.qatt("C1.a1");
		Element rhsC2 =  rhsMM.clazz("C2");
		Element rhsR1 = rhsMM.qref("C1_r1_C2");
	
		XMLElementResolverFactory resolverFac = 
						new XMLElementResolverFactory();
		
		// construct mapping 1 with 2 C2Cs and 1 A2A
		FragmentMapping fm1 = new FragmentMapping();
		Operator op1 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op1.setId("OP1");
		Operator op2 = OperatorBuilder.a2a(op1, lhsA1, rhsA1);
		op2.setId("OP2");
		Operator op3 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op3.setId("OP3");
		// set root operators
		fm1.setOperators(asSet(op1, op3));
		
		// construct mapping 2 with 2 C2Cs, 1 A2A, 1 R2R
		FragmentMapping fm2 = new FragmentMapping();
		Operator op5 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op5.setId("OP5");
		Operator op6 = OperatorBuilder.a2a(op5, lhsA1, rhsA1);
		op6.setId("OP6");
		Operator op7 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op7.setId("OP7");
		Operator op8 = OperatorBuilder.r2r(op5, op7, lhsR1, rhsR1);
		op8.setId("OP8");
		// set root operators !
		fm2.setOperators(asSet(op5, op7));
		
		// ***************************************
		// CHECKS
		// **************************************
		
		TFragmentizer2.printFragmentMapping(fm1);
		
		System.out.println("\n");
		
		XMLFragmentMapping xmlFM1 = 
			XMLFragmentMapping.getInstance(fm1, resolverFac);
		
		assertEquals(2, xmlFM1.getOperators().size());
		assertEquals(3, xmlFM1.getFlattenedOperators().size());
		
		TFragmentizer2.printFragmentMapping2(xmlFM1);
		
		System.out.println("\n");
		
		TFragmentizer2.printFragmentMapping(fm2);
		
		System.out.println("\n");
		
		XMLFragmentMapping xmlFM2 = 
			XMLFragmentMapping.getInstance(fm2, resolverFac);
		
		assertEquals(2, xmlFM2.getOperators().size());
		assertEquals(4, xmlFM2.getFlattenedOperators().size());
		
		TFragmentizer2.printFragmentMapping2(xmlFM2);
		
		
		// CHECKS for operators of fm1
		Set<XMLOperator> xmlFm1Ops = xmlFM1.getFlattenedOperators();
		List<XMLOperator> sXmlFm1Ops = sortXmlOpsByName(xmlFm1Ops);
		
		XMLOperator fm1C2C1 = sXmlFm1Ops.get(1);
		assertEquals(0, fm1C2C1.getParents().size());
		assertEquals(1, fm1C2C1.getChildren().size());
		
		XMLOperator fm1C2C2 = sXmlFm1Ops.get(2);
		assertEquals(0, fm1C2C2.getParents().size(), 0);
		// C2C C2-C2 has no children
		assertEquals(0, fm1C2C2.getChildren().size(), 0);
		
		XMLOperator fm1A2A1 = sXmlFm1Ops.get(0);
		assertEquals(1, fm1A2A1.getParents().size());
		assertEquals(0, fm1A2A1.getChildren().size());
		
		
		// CHECKS for operators of fm2
		Set<XMLOperator> xmlFm2Ops = xmlFM2.getFlattenedOperators();
		List<XMLOperator> sXmlFm2Ops = sortXmlOpsByName(xmlFm2Ops);
		
		XMLOperator fm2C2C1 = sXmlFm2Ops.get(1);
		assertEquals(0, fm2C2C1.getParents().size());
		assertEquals(2, fm2C2C1.getChildren().size());
		
		XMLOperator fm2C2C2 = sXmlFm2Ops.get(2);
		assertEquals(0, fm2C2C2.getParents().size());
		assertEquals(1, fm2C2C2.getChildren().size());
		
		XMLOperator fm2A2A1 = sXmlFm2Ops.get(0);
		assertEquals(1, fm2A2A1.getParents().size());
		assertEquals(0, fm2A2A1.getChildren().size());
		
		XMLOperator fm2R2R1 = sXmlFm2Ops.get(3);
		assertEquals(2, fm2R2R1.getParents().size());
		assertEquals(0, fm2R2R1.getChildren().size());
	}
	
	
	@Test
	public void testMultipleFragmentMappings() {
		
		// 7 Fragments on LHS, 7 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet3().getFragments();
		
		Set<Fragment> lhsFrags = fragments.e0;
		assertEquals(7, lhsFrags.size());
		
		TFragmentizer2.printFragments(lhsFrags);
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		XMLFragments lhsXmlFrags = XMLFragments.getInstance(fragments.e0, resolverFac);
		assertEquals(7, lhsXmlFrags.getFragments().size());
		
		TFragmentizer2.printFragments2(lhsXmlFrags.getFragments());
	}
	
}
