package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.a2a;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.c2c;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.r2r;

import java.util.Set;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.Fragmentizer;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class FragmentsSet1 implements FragmentsSet {
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
					c("C1*", 
						li(
							a("a1*"),
							a("a2*")
						)
					).as("C1*"),
					c("C2*", 
						li(
							a("a3*"),
							a("a4*")
						)
					).as("C2*"),
					c("C3*", 
						li(
							a("a5*"),
							a("a6*"),
							a("a7*")
						)
					).as("C3*"),
					c("C4*", 
						li(
							a("a8*")
						)
					).as("C4*"),
					c("C5*", 
						li(
							a("a9*"),
							a("a10*")
						)
					).as("C5*"),
					r("r1*", c("C1*"), c("C2*")),
					r("r2*", c("C2*"), c("C1*")),
					r("r3*", c("C2*"), c("C3*"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	public 
	Set<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>  
	getBubbles() {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		C2CBubble c2c1 = 
			c2c(
				lhsMM.clazz("C1"), 
				rhsMM.clazz("C1*")
			);
		
		C2CBubble c2c2 = 
			c2c(
				lhsMM.clazz("C2"), 
				rhsMM.clazz("C2*")
			);
		
		A2ABubble a2a1 = 
			a2a(
				c2c1, 
				lhsMM.qatt("C1.a1"), 
				rhsMM.qatt("C1*.a1*")
			);
		
		A2ABubble a2a2 = 
			a2a(
				c2c2, 
				lhsMM.qatt("C2.a3"), 
				rhsMM.qatt("C2*.a3*")
			);
		
		R2RBubble r2r = 
			r2r(
				c2c1, 
				c2c2, 
				lhsMM.ref("r1"), 
				rhsMM.ref("r1*")
			);
		
		Set<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>> 
			bubbles = 
		Helpers.
		<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
						asSet(c2c1, a2a1, a2a2, c2c2, r2r);
		
		return bubbles;
	}
	
	
	public T2<Set<Fragment>,Set<Fragment>> getFragments() {
		Fragmentizer f = new Fragmentizer();	
		// fragmentize mapping
		T2<Set<Fragment>,Set<Fragment>> frags = 
							f.fragmentize(getBubbles());
		
		// 12 Fragments on LHS, 12 Fragments on RHS
		
		return frags;
	}
}
