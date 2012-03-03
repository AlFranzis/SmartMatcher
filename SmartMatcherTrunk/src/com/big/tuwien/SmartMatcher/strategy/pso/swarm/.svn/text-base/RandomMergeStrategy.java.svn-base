package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.A2AMerger;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.C2CMerger;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.Merger;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.R2RMerger;

public class RandomMergeStrategy {
	private List<Merger> mergers;
	
	
	public RandomMergeStrategy(MetaModel lhsMM, MetaModel rhsMM) {
		this(Arrays.<Merger>asList(
				new C2CMerger(lhsMM, rhsMM),
				new A2AMerger(lhsMM, rhsMM),
				new R2RMerger(lhsMM, rhsMM)
		));
	}
	
	
	public RandomMergeStrategy(List<Merger> mergers) {
		this.mergers = mergers;
	}
	
	
	public PSOMapping merge(PSOMapping m, PSOMapping best) {
		if(mergers.isEmpty())
			throw new PSORuntimeException("No mergers defined");
		
		Collections.shuffle(mergers);
		Merger merger = mergers.get(0);
		
		PSOMapping merged = merger.merge(m, best);
		
 		return merged;
	}
	
}
