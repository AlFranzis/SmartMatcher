package com.big.tuwien.SmartMatcher.strategy.pso;

import static com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.getAttributes;
import static com.big.tuwien.SmartMatcher.strategy.pso.SwapperUtil.getReferences;

import java.util.Set;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

/**
 * Methods to check consistency of mappings.
 * @author alex
 *
 */
public class Checker {
	
	public static int reqR2RMappings(C2<PSOC2C> cxt) {
		return reqR2RMappings(cxt.op1, cxt.op2);
	}
	
	public static int reqR2RMappings(PSOC2C c2c1, PSOC2C c2c2) {
		int lhsRefs = getReferences(c2c1.lhs(), c2c2.lhs()).size();
		int rhsRefs = getReferences(c2c1.rhs(), c2c2.rhs()).size();
		
		return lhsRefs <= rhsRefs ? lhsRefs : rhsRefs;
	}
	
	
	public static int reqC2CMappings(MetaModel lhsMM, MetaModel rhsMM) {
		int lhsClasses = lhsMM.getClasses().size();
		int rhsClasses = rhsMM.getClasses().size();
		
		return lhsClasses <= rhsClasses ? lhsClasses : rhsClasses;
	}
	
	
	public static int reqA2AMappings(C1<PSOC2C> cxt) {
		return reqA2AMappings(cxt.op1);
	}
	
	
	public static int reqA2AMappings(PSOC2C c2c) {
		int lhsAtts = getAttributes(c2c.lhs()).size();
		int rhsAtts = getAttributes(c2c.rhs()).size();
		
		return lhsAtts <= rhsAtts ? lhsAtts : rhsAtts;
	}
	
	
	/**
	 * Checks if a mapping is complete.
	 * A mappings is complete if it contains
	 * a maximum amount of possible mappings
	 * @param m
	 * @param lhsMM
	 * @param rhsMM
	 * @return
	 */
	public static boolean isComplete(PSOMapping m, MetaModel lhsMM, 
											MetaModel rhsMM) {
		// check needed C2Cs
		if(m.descendents(PSOC2C.class).size() 
				!= reqC2CMappings(lhsMM, rhsMM))
			return false;
		
		// check needed A2As for every C2C
		Set<PSOC2C> c2cs = m.descendents(PSOC2C.class);
		for(PSOC2C c2c : c2cs) {
			int reqA2As = reqA2AMappings(c2c);
			if(reqA2As > 0) {
				C1<PSOC2C> cxt = getContext(m, c2c);
				if(cxt == null)
					return false;
				
				if(cxt.descendents(PSOA2A.class).size() != reqA2As)
					return false;
					
			}	
		}
		
		// check needed R2Rs for every C2C pair
		for(PSOC2C c2c1 : c2cs) {
			for(PSOC2C c2c2 : c2cs) {
				int reqR2Rs = reqR2RMappings(c2c1, c2c2);
				if(reqR2Rs > 0) {
					C2<PSOC2C> cxt = getContext(m, c2c1, c2c2);
					if(cxt == null)
						return false;
					
					if(cxt.descendents(PSOR2R.class).size() != reqR2Rs)
						return false;
						
				}	
			}	
		}
		
		return true;
	}
	
	
	private static C1<PSOC2C> getContext(PSOMapping m, PSOC2C c2c) {
		Set<C1> cxts = m.contexts(C1.class);
		for(C1<PSOC2C> cxt : cxts) {
			PSOC2C cxtC2C = cxt.op1;
			if(cxtC2C.flatEquals(c2c))
				return cxt;
		}
		return null;
	}
	
	
	private static C2<PSOC2C> getContext(PSOMapping m, PSOC2C c2c1, 
														PSOC2C c2c2) {
		Set<C2> cxts = m.contexts(C2.class);
		for(C2<PSOC2C> cxt : cxts) {
			PSOC2C cxtC2C1 = cxt.op1;
			PSOC2C cxtC2C2 = cxt.op2;
			if(cxtC2C1.flatEquals(c2c1) && cxtC2C2.flatEquals(c2c2)
				|| cxtC2C1.flatEquals(c2c2) && cxtC2C2.flatEquals(c2c1))
				return cxt;
		}
		return null;
	}
}
