package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import static com.big.tuwien.SmartMatcher.strategy.pso.C1.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;

public class PSOA2AGenerator2 {
	
	public List<PSOA2A> generate(PSOC2C contextC2C) {
		Element lhsClass = contextC2C.lhs();
		Element rhsClass = contextC2C.rhs();
		
		List<AttributeElement> lhsAttributes = new Vector<AttributeElement>(
												((ClassElement)lhsClass.getRepresentedBy()).getAttributes()
												);
		List<AttributeElement> rhsAttributes = new Vector<AttributeElement>(
												((ClassElement)rhsClass.getRepresentedBy()).getAttributes()
												);
		// random shuffle attributes to get random mappings
		Collections.shuffle(lhsAttributes);
		Collections.shuffle(rhsAttributes);
		
		List<PSOA2A> a2as = new ArrayList<PSOA2A>();
		
		int len = Math.min(lhsAttributes.size(), rhsAttributes.size());
		C1<PSOC2C> context = c(contextC2C);
		for(int i = 0; i < len; i++) {
			PSOA2A a2a = new PSOA2A(context, 
								lhsAttributes.get(i).getRepresents(), 
								rhsAttributes.get(i).getRepresents()
								);
			contextC2C.add(a2a);
			a2as.add(a2a);
		}
		
		return a2as;
	}
}
