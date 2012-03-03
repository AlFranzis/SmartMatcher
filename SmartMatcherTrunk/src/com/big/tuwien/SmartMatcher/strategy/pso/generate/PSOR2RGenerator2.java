package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import static com.big.tuwien.SmartMatcher.strategy.pso.C2.c;
import static com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil.getReferences;
import static com.google.common.base.Predicates.not;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class PSOR2RGenerator2 {
	
	
	public List<PSOR2R> generate(C2<PSOC2C> context) {
		Element lhsClass1 = context.op1.lhs();
		Element rhsClass1 = context.op1.rhs();
		Element lhsClass2 = context.op2.lhs();
		Element rhsClass2 = context.op2.rhs();
		
		List<Element> lhsRefs = getReferences(lhsClass1, lhsClass2);
		List<Element> rhsRefs = getReferences(rhsClass1, rhsClass2);
		
		return generateRandomR2Rss(context, lhsRefs, rhsRefs);
	}
	
	
	public List<PSOR2R> generateR2Rss(C2<PSOC2C> context, final Collection<PSOR2R> preset) {
		Element lhsClass1 = context.op1.lhs();
		Element rhsClass1 = context.op1.rhs();
		Element lhsClass2 = context.op2.lhs();
		Element rhsClass2 = context.op2.rhs();
		
		Predicate<Element> inPreset = new Predicate<Element>() {
			@Override
			public boolean apply(Element e) {
				for(PSOR2R r2r : preset) {
					Element lhs = r2r.lhs();
					Element rhs = r2r.rhs();
					if(e.equals(lhs) || e.equals(rhs)) return true;
				}
				return false;
			}	
		};
		
		List<Element> lhsRefs = getReferences(lhsClass1, lhsClass2);
		lhsRefs = new Vector<Element>(Collections2.filter(lhsRefs, not(inPreset)));
		
		List<Element> rhsRefs = getReferences(rhsClass1, rhsClass2);
		rhsRefs = new Vector<Element>(Collections2.filter(rhsRefs, not(inPreset)));
		
		return generateRandomR2Rss(context, lhsRefs, rhsRefs);
	}
	
	
	public List<PSOR2R> generate(List<PSOC2C> contextC2Cs1, List<PSOC2C> contextC2Cs2) {
		List<PSOR2R> r2rs = new Vector<PSOR2R>();
		for(PSOC2C c2c1 : contextC2Cs1) {
			for(PSOC2C c2c2 : contextC2Cs2) {
				C2<PSOC2C> context = c(c2c1,c2c2);
				List<PSOR2R> _r2rs = generate(context);
				r2rs.addAll(_r2rs);
			}
		}
		
		return r2rs;	
	}
	
	
	protected List<PSOR2R> generateRandomR2Rss(C2<PSOC2C> context, List<Element> lhsRefs, List<Element> rhsRefs) {
		// random shuffle references to get random mappings
		Collections.shuffle(lhsRefs);
		Collections.shuffle(rhsRefs);
			
		List<PSOR2R> r2rs = new ArrayList<PSOR2R>();
		int len = Math.min(lhsRefs.size(), rhsRefs.size());
		for(int i = 0; i < len; i++) {
			PSOR2R r2r = new PSOR2R(
								context, 
								lhsRefs.get(i), 
								rhsRefs.get(i)
							);
			context.op1.add(r2r);
			context.op2.add(r2r);
			r2rs.add(r2r);
		}
		
		return r2rs;
	}
	
}
