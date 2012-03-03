package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;

public class PSOC2CGenerator {
	protected MetaModel lhsMM;
	protected MetaModel rhsMM;
	
	
	public PSOC2CGenerator(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
	}

	
	public List<PSOC2C> generate(PSOMapping m) {
		List<Element> lhsClasses = new Vector<Element>(lhsMM.getClasses());
		List<Element> rhsClasses = new Vector<Element>(rhsMM.getClasses());
		
		// random shuffle classes to get random mappings
		Collections.shuffle(lhsClasses);
		Collections.shuffle(rhsClasses);
		
		List<PSOC2C> c2cs = new Vector<PSOC2C>();
		int len = Math.min(lhsClasses.size(), rhsClasses.size());
		
		for(int i = 0; i < len; i++) {
			PSOC2C c2c = new PSOC2C(m, lhsClasses.get(i), rhsClasses.get(i));
			m.add(c2c);
			c2cs.add(c2c);
		}
		
		return c2cs;
	}
	
}
