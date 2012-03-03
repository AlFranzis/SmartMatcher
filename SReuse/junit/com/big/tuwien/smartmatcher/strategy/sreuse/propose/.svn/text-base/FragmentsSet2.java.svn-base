package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.a2a;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.BubbleBuilder.c2c;

import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.Helpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.Fragmentizer;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class FragmentsSet2 implements FragmentsSet {

	public FragmentsSet2() {
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
					c("C1*", 
						li(
							a("a1*"),
							a("a2*")
						)
					).as("C1*"),
					c("C2*", 
						li(
							
						)
					).as("C2*")
				));
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
		
		Set<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>> 
			bubbles = 
		Helpers.
		<Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator>>
						asSet(c2c1, a2a1, c2c2);
		
		return bubbles;
		
	}
	
	
	public T2<Set<Fragment>,Set<Fragment>> getFragments() {
		Fragmentizer f = new Fragmentizer();	
		// fragmentize mapping
		T2<Set<Fragment>,Set<Fragment>> frags = 
							f.fragmentize(getBubbles());
		
		// 5 Fragments on LHS, 5 Fragments on RHS
		
		return frags;
	}
		
}
