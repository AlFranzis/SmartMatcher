package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.a2a;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.c2c;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.r2r;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.EasyFragmentMappingBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TFragmentizer {

	public TFragmentizer() {
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
		
		A2ABubble a2a2 = 
			a2a(
				c2c2, 
				lhsMM.qatt("C2.a3"), 
				rhsMM.qatt("C2.a3")
			);
		
		R2RBubble r2r = 
			r2r(
				c2c1, 
				c2c2, 
				lhsMM.ref("r1"), 
				rhsMM.ref("r1")
			);
		
		Set<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>> 
			bubbles = 
		Helpers.
		<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
						asSet(c2c1, a2a1, a2a2, c2c2, r2r);
		
		Fragmentizer f = new Fragmentizer();	
		// fragmentize mapping
		T2<Set<Fragment>,Set<Fragment>> frags = 
							f.fragmentize(bubbles);
		
		// **************************
		// CHECKS
		// **************************
		
		Set<Fragment> lhsFrags = frags.e0;
		Set<Fragment> rhsFrags = frags.e1;
		
		// System.out.println(FragmentFormatter.format(lhsFrags));
		
		assertEquals(12, lhsFrags.size());
		assertEquals(12, rhsFrags.size());
		
		FragmentBuilder lhsFb = new FragmentBuilder(lhsMM);
		FragmentBuilder rhsFb = new FragmentBuilder(rhsMM);
		
		Fragment_ lhsF1 = lhsFb.f("C1");
		Fragment_ rhsF1 = rhsFb.f("C1");
		
		EasyFragmentMappingBuilder fmb = 
			new EasyFragmentMappingBuilder(lhsF1, rhsF1);
		FragmentMapping fm1 = fmb.fm(fmb.li(fmb.c2c("C1", "C1")));
		
		lhsF1.setMappings(asArraySet(fm1));
		rhsF1.setMappings(asArraySet(fm1));
		
		assertTrue(lhsFrags.contains(lhsF1));
		assertTrue(rhsFrags.contains(rhsF1));
		
		
		Fragment_ lhsF2 = lhsFb.f("C2");
		Fragment_ rhsF2 = rhsFb.f("C2");
		fmb = new EasyFragmentMappingBuilder(lhsF2, rhsF2);
		FragmentMapping fm2 = fmb.fm(fmb.li(fmb.c2c("C2", "C2")));
		lhsF2.setMappings(asArraySet(fm2));
		rhsF2.setMappings(asArraySet(fm2));
		
		assertTrue(lhsFrags.contains(lhsF2));
		assertTrue(rhsFrags.contains(rhsF2));
		
		
		Fragment_ lhsF3 = lhsFb.f("C1", "C1.a1");
		Fragment_ rhsF3 = rhsFb.f("C1", "C1.a1");
		fmb = new EasyFragmentMappingBuilder(lhsF3, rhsF3);
		FragmentMapping fm3 = 
			fmb.fm(fmb.li(
					fmb.c2c("C1", "C1",
						fmb.li(
						fmb.a2a("C1.a1", "C1.a1")
						)
					)));
		lhsF3.setMappings(asArraySet(fm3));
		rhsF3.setMappings(asArraySet(fm3));
		
		assertTrue(lhsFrags.contains(lhsF3));
		assertTrue(rhsFrags.contains(rhsF3));
		
		
		TFragmentizer2.printFragments(lhsFrags);
	
	}
	
	
//	public static class FragmentFormatter {
//		public static String format(Set<Fragment> fragments) {
//			StringBuilder buf = new StringBuilder();
//
//			List<Fragment> roots = new Vector<Fragment>();
//			for(Fragment f : fragments) {
//				if(f.getParents().isEmpty()) {
//					roots.add(f);
//				}
//			}
//
//			String s = format(1, roots);
//			buf.append(s);
//			
//			return buf.toString();
//		}
//
//
//		private static String format(int size, List<Fragment> fs) {
//			StringBuilder buf = new StringBuilder();
//
//			boolean first = true;
//			for(Fragment f : fs) {
//				if(!first) 
//					buf.append(", ");
//				
//				buf.append(format(f));
//				first = false;
//			}
//
//			buf.append("\n");
//
//			Set<Fragment> childrenOfSizeN = new HashSet<Fragment>();
//			for(Fragment f : fs) {
//				for(Fragment child : f.getChildren()) {
//						childrenOfSizeN.add(child);
//				}
//			}
//
//			if(!childrenOfSizeN.isEmpty()) {
//				String s = format(size + 1, new Vector<Fragment>(childrenOfSizeN));
//				buf.append(s);
//			}
//
//			return buf.toString();
//		}
//
//
//		private static String format(Fragment f) {
//			return f.toString();
//		}
//
//	}
	
	
//	public static class OpFlattener {
//		
//		public static List<Operator> flatten(Set<Operator> ops) {
//			List<Operator> flattened = new Vector<Operator>();
//			flatten(flattened, ops);
//			return flattened;
//		}
//		
//		
//		private static void flatten(List<Operator> flattened, Set<Operator> ops) {
//			for(Operator op : ops) {
//				flattened.add(op);
//			}
//			
//			Set<Operator> children = new HashSet<Operator>();
//			for(Operator op : ops) {
//				children.addAll(op.getChildren());
//			}
//			if(!children.isEmpty())
//				flatten(flattened, children);
//		}
//	}
	
}
