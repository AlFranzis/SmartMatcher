package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.a2a;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.c2c;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAttributeElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLClassElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLOperator;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLReferenceElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.CombinedFragmentBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.MappingDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TFragmentizer2 extends TFragmentizer {

	public TFragmentizer2() {
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
							
						)
					).as("C2")
				));
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
							
						)
					).as("C2")
				));
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	@Test
	public void testFragmentizer() {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		C2CBubble c2c1 = 
			c2c(
				lhsMM.clazz("C1"), 
				rhsMM.clazz("C1")
			);
		
		C2CBubble c2c2 = 
			c2c(
				lhsMM.clazz("C2"), 
				rhsMM.clazz("C2")
			);
		
		A2ABubble a2a1 = 
			a2a(
				c2c1, 
				lhsMM.qatt("C1.a1"), 
				rhsMM.qatt("C1.a1")
			);
		
		Set<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>> 
			bubbles = 
		Helpers.
		<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
						asSet(c2c1, a2a1, c2c2);
		
		Fragmentizer f = new Fragmentizer();	
		// fragmentize mapping
		T2<Set<Fragment>,Set<Fragment>> frags = 
							f.fragmentize(bubbles);
		
		// ***********************
		// CHECKS
		// ***********************
		
		Set<Fragment> lhsFrags = frags.e0;
		Set<Fragment> rhsFrags = frags.e1;
		
		// System.out.println(FragmentFormatter.format(lhsFrags));
		
		assertEquals(5, lhsFrags.size());
		assertEquals(5, rhsFrags.size());
		
		
		DescBuilder db = new DescBuilder();
		CombinedFragmentBuilder cfb = new CombinedFragmentBuilder(lhsMM, rhsMM);
		
		MappingDesc md1 = 
			db.ops(
				db.c2c("C1", "C1")
			);
		T2<Fragment,Fragment> oracleFragments = cfb.fm(md1);
		assertTrue(lhsFrags.contains(oracleFragments.e0));
		assertTrue(rhsFrags.contains(oracleFragments.e1));
		
		MappingDesc md2 = 
			db.ops(
				db.c2c("C2", "C2")
			);
		oracleFragments = cfb.fm(md2);
		assertTrue(lhsFrags.contains(oracleFragments.e0));
		assertTrue(rhsFrags.contains(oracleFragments.e1));
		
		
		MappingDesc md3 = 
			db.ops(
				db.c2c("C1", "C1", 
					db.a2a("C1.a1", "C1.a1")
				)
			);
		oracleFragments = cfb.fm(md3);
		assertTrue(lhsFrags.contains(oracleFragments.e0));
		assertTrue(rhsFrags.contains(oracleFragments.e1));
		
		MappingDesc md4 = 
			db.ops(
				db.c2c("C1", "C1"),
				db.c2c("C2", "C2")
			);
		oracleFragments = cfb.fm(md4);
		assertTrue(lhsFrags.contains(oracleFragments.e0));
		assertTrue(rhsFrags.contains(oracleFragments.e1));
		
		MappingDesc md5 = 
			db.ops(
				db.c2c("C1", "C1",
					db.a2a("C1.a1", "C1.a1")
				),
				db.c2c("C2", "C2")
			);
		oracleFragments = cfb.fm(md5);
		assertTrue(lhsFrags.contains(oracleFragments.e0));
		assertTrue(rhsFrags.contains(oracleFragments.e1));
	
		printFragments(lhsFrags);	
	}
	
	
	public static void printFragments(Collection<Fragment> frags) {
		for(Fragment frag : frags) {
			System.out.println(frag);
			for(Element clazz : frag.getClasses()) {
				ClassElement c = (ClassElement)clazz.getRepresentedBy();
				System.out.println(" " + c);
				List<AttributeElement> atts = c.getAttributes();
				System.out.println("    " + atts);
				List<ReferenceElement> refs = c.getReferences();
				System.out.println("    " +   refs);
			}
			
			FragmentMapping fm =frag.getMappings().iterator().next();
			System.out.println(" Operators:");
			for(Operator op : fm.getFlattenedOperators()) {
				System.out.println("    " + op);
				System.out.println("    " + op.getName());
				System.out.println("    " + "Parents:");
				for(Operator parent : op.getParents()) {
					System.out.println("       " + parent);
				}
				System.out.println("    " + "Children:");
				for(Operator child : op.getChildren()) {
					System.out.println("       " + child);
				}
				System.out.println("");
			}
			System.out.println("");
			
		}
	}
	
	
	public static void printFragments2(Collection<XMLFragment> frags) {
		for(XMLFragment frag : frags) {
			System.out.println(frag);
			for(XMLClassElement clazz : frag.getClasses()) {
				System.out.println(" " + clazz);
				Set<XMLAttributeElement> atts = clazz.getAttributes();
				System.out.println("    " + atts);
				Set<XMLReferenceElement> refs = clazz.getReferences();
				System.out.println("    " +   refs);
			}
			
			XMLFragmentMapping fm = frag.getMappings().iterator().next();
			System.out.println(" Operators:");
			for(XMLOperator op : fm.getFlattenedOperators()) {
				System.out.println("    " + op);
				System.out.println("    " + op.getName());
				System.out.println("    " + "Parents:");
				for(XMLOperator parent : op.getParents()) {
					System.out.println("       " + parent);
				}
				System.out.println("    " + "Children:");
				for(XMLOperator child : op.getChildren()) {
					System.out.println("       " + child);
				}
				System.out.println("");
			}
			System.out.println("");
			
		}
	}
		
		
	public static void printFragmentMapping2(XMLFragmentMapping fm) {
		System.out.println(" Operators:");
		for(XMLOperator op : fm.getFlattenedOperators()) {
			System.out.println("    " + op);
			System.out.println("    " + op.getName());
			System.out.println("    " + "Parents:");
			for(XMLOperator parent : op.getParents()) {
				System.out.println("       " + parent);
			}
			System.out.println("    " + "Children:");
			for(XMLOperator child : op.getChildren()) {
				System.out.println("       " + child);
			}
			System.out.println("");
		}
	}
	
	
	public static void printFragmentMapping(FragmentMapping fm) {
		System.out.println(" Operators:");
		for(Operator op : fm.getFlattenedOperators()) {
			System.out.println("    " + op);
			System.out.println("    " + op.getName());
			System.out.println("    " + "Parents:");
			for(Operator parent : op.getParents()) {
				System.out.println("       " + parent);
			}
			System.out.println("    " + "Children:");
			for(Operator child : op.getChildren()) {
				System.out.println("       " + child);
			}
			System.out.println("");
		}
	}
	
	
	
	
	
}
