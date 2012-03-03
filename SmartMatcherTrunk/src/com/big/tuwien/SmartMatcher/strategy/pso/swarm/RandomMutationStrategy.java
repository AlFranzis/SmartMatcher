package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.A2AMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.A2ASwapper;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.C2CMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.C2CSwapper;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.Mutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.R2RMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.R2RSwapper;

public class RandomMutationStrategy {
	private static Logger logger = Logger
						.getLogger(RandomMutationStrategy.class);

	private List<Mutator> mutators;
	
	
	public RandomMutationStrategy(MetaModel lhsMM, MetaModel rhsMM) {
		this(Arrays.asList(
			new C2CSwapper(), 
			new A2ASwapper(),
			new R2RSwapper(),
			new C2CMutator(lhsMM, true),
			new A2AMutator(true),
			new R2RMutator(true),
			new C2CMutator(lhsMM, false),
			new A2AMutator(false),
			new R2RMutator(false)
		));
	}
	
	
	public RandomMutationStrategy(List<Mutator> mutators) {
		this.mutators = mutators;
	}
	
	
	public Particle mutate(Particle p) {
		PSOMapping mutated = mutate(p.m);
		Particle clone = p.clone();
		clone.m = mutated;
 		return clone;
	}
	
	
	public PSOMapping mutate(PSOMapping m) {
		if(mutators.isEmpty())
			throw new PSORuntimeException("No mutators defined");
		
		Collections.shuffle(mutators);
		Iterator<Mutator> mutatorIt = mutators.iterator();
				
		Mutator selMutator = mutatorIt.next();
		PSOMapping mutated = selMutator.mutate(m);
		while(mutated.equals(m)) {
			selMutator = mutatorIt.next();
			mutated = selMutator.mutate(m);
		}
		logger.debug("Mutated mapping with " + selMutator);
 		return mutated;
	}
		
}
