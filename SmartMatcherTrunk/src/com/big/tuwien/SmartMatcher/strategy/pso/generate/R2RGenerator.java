package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import static com.google.common.base.Predicates.not;

public class R2RGenerator {
	
	
	public List<Bubble<R2R>> generateR2Rss(Bubble<C2C> contextC2C1, Bubble<C2C> contextC2C2) {
		Element lhsClass1 = contextC2C1.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass1 = contextC2C1.getConfiguration().getRHSElements().iterator().next();
		Element lhsClass2 = contextC2C2.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass2 = contextC2C2.getConfiguration().getRHSElements().iterator().next();
		
		List<Element> lhsRefs = getReferences(lhsClass1, lhsClass2);
		List<Element> rhsRefs = getReferences(rhsClass1, rhsClass2);
		
		return generateRandomR2Rss(contextC2C1, contextC2C2, lhsRefs, rhsRefs);
	}
	
	
	public List<Bubble<R2R>> generateR2Rss(Bubble<C2C> contextC2C1, Bubble<C2C> contextC2C2, final List<Bubble<R2R>> preset) {
		Element lhsClass1 = contextC2C1.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass1 = contextC2C1.getConfiguration().getRHSElements().iterator().next();
		Element lhsClass2 = contextC2C2.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass2 = contextC2C2.getConfiguration().getRHSElements().iterator().next();
		
		Predicate<Element> inPreset = new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				for(Bubble<R2R> r2r : preset) {
					Element lhs = ((HomogenicConfiguration<R2R>)r2r.getConfiguration()).getLHSFocusElement();
					Element rhs = ((HomogenicConfiguration<R2R>)r2r.getConfiguration()).getRHSFocusElement();
					if(e.equals(lhs) || e.equals(rhs)) return true;
				}
				return false;
			}	
		};
		
		List<Element> lhsRefs = getReferences(lhsClass1, lhsClass2);
		lhsRefs = new Vector<Element>(Collections2.filter(lhsRefs, not(inPreset)));
		
		List<Element> rhsRefs = getReferences(rhsClass1, rhsClass2);
		lhsRefs = new Vector<Element>(Collections2.filter(rhsRefs, not(inPreset)));
		
		return generateRandomR2Rss(contextC2C1, contextC2C2, lhsRefs, rhsRefs);
	}
	
	
	protected List<Bubble<R2R>> generateRandomR2Rss(Bubble<C2C> contextC2C1, Bubble<C2C> contextC2C2, List<Element> lhsRefs, List<Element> rhsRefs) {
		// random shuffle references to get random mappings
		Collections.shuffle(lhsRefs);
		Collections.shuffle(rhsRefs);
			
		List<Bubble<R2R>> r2rs = new ArrayList<Bubble<R2R>>();
		
		int len = Math.min(lhsRefs.size(), rhsRefs.size());
		for(int i = 0; i < len; i++) {
			R2RBubble r2r = new R2RBubble();
			R2RConfiguration r2rConfig = new R2RConfiguration();
			r2rConfig.setLHSFocusElement(lhsRefs.get(i));
			r2rConfig.setRHSFocusElement(rhsRefs.get(i));
			r2r.setConfiguration(r2rConfig);
			r2r.setContext1((C2CBubble) contextC2C1);
			r2r.setContext2((C2CBubble) contextC2C2);
			r2rs.add(r2r);
		}
		
		return r2rs;
	}
	
	
	public List<Bubble<R2R>> generate(List<Bubble<C2C>> contextC2Cs1, List<Bubble<C2C>> contextC2Cs2) {
		List<Bubble<R2R>> r2rs = new Vector<Bubble<R2R>>();
		for(Bubble<C2C> c2c1 : contextC2Cs1) {
			for(Bubble<C2C> c2c2 : contextC2Cs2) {
				List<Bubble<R2R>> _r2rs = generateR2Rss(c2c1, c2c2);
				r2rs.addAll(_r2rs);
			}
		}
		
		return r2rs;	
	}
	
	
	protected List<Element> getReferences(Element clazz1, Element clazz2) {
		List<Element> refs = new Vector<Element>();
		
		List<ReferenceElement> clazz1Refs = ((ClassElement) clazz1.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz1Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz2))
				refs.add(ref.getRepresents());
		}
		
		List<ReferenceElement> clazz2Refs = ((ClassElement) clazz2.getRepresentedBy()).getReferences();
		for(ReferenceElement ref : clazz2Refs) {
			if(ref.getPointsTo().getRepresents().equals(clazz1))
				refs.add(ref.getRepresents());
		}
		
		return refs;
	}
}
