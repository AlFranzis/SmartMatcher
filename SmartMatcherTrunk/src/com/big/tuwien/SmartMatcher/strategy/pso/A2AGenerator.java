package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class A2AGenerator {
	
	public List<Bubble<A2A>> generateA2As(Bubble<C2C> contextC2C) {
		Element lhsClass = contextC2C.getConfiguration().getLHSElements().iterator().next();
		Element rhsClass = contextC2C.getConfiguration().getRHSElements().iterator().next();
		
		List<AttributeElement> lhsAttributes = new Vector<AttributeElement>(
												((ClassElement)lhsClass.getRepresentedBy()).getAttributes()
												);
		List<AttributeElement> rhsAttributes = new Vector<AttributeElement>(
												((ClassElement)rhsClass.getRepresentedBy()).getAttributes()
												);
		// random shuffle attributes to get random mappings
		Collections.shuffle(lhsAttributes);
		Collections.shuffle(rhsAttributes);
		
		List<Bubble<A2A>> a2as = new ArrayList<Bubble<A2A>>();
		
		int len = Math.min(lhsAttributes.size(), rhsAttributes.size());
		for(int i = 0; i < len; i++) {
			A2ABubble a2a = new A2ABubble();
			A2AConfiguration a2aConfig = new A2AConfiguration();
			a2aConfig.setLHSFocusElement(lhsAttributes.get(i).getRepresents());
			a2aConfig.setRHSFocusElement(rhsAttributes.get(i).getRepresents());
			a2a.setConfiguration(a2aConfig);
			a2a.setContext((C2CBubble) contextC2C);
			a2as.add(a2a);
		}
		
		return a2as;
	}
}
