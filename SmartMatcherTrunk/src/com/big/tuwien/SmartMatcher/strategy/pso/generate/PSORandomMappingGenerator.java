package com.big.tuwien.SmartMatcher.strategy.pso.generate;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;

public class PSORandomMappingGenerator implements MappingGenerator {
	protected MetaModel lhsMM;
	protected MetaModel rhsMM;
	
	protected PSOC2CGenerator c2cGenerator;
	protected PSOA2AGenerator2 a2aGenerator;
	protected PSOR2RGenerator2 r2rGenerator;
	
	
	public PSORandomMappingGenerator(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
		c2cGenerator = new PSOC2CGenerator(lhsMM, rhsMM);
		a2aGenerator = new PSOA2AGenerator2();
		r2rGenerator = new PSOR2RGenerator2();
	}
	
	
	public PSOMapping generate() {
		PSOMapping m = new PSOMapping();
		
		// generate C2Cs
		List<PSOC2C> c2cs = c2cGenerator.generate(m);
		
		// generate A2As
		for(PSOC2C c2c : c2cs) {
			a2aGenerator.generate(c2c);
		}
		
		// build R2Rs for C2C pairs
		List<PSOC2C> _c2cs = new Vector<PSOC2C>(c2cs);
		for(PSOC2C c2c1 : c2cs) {
			_c2cs.remove(c2c1);
			for(PSOC2C c2c2 : _c2cs) {
				r2rGenerator.generate(C2.c(c2c1, c2c2));
			}
		}
 		
		return m;
	}
}
