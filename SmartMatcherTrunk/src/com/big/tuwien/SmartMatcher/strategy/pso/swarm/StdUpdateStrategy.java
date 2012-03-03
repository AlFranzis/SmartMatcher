package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import static com.big.tuwien.SmartMatcher.strategy.pso.swarm.Helpers.check;

import java.util.ArrayList;
import java.util.List;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;

public class StdUpdateStrategy implements UpdateStrategy {
	protected MetaModel lhsMM;
	protected MetaModel rhsMM;
	protected RandomMutationStrategy mutStrategy;
	protected RandomMergeStrategy lMergeStrategy;
	protected RandomMergeStrategy gMergeStrategy;
	
	/*
	 * Mutation probability factor
	 */
	protected double pMutation;
	/*
	 * Local merge probability factor
	 */
	protected double pLocalMerge;
	/*
	 * Global merge probability factor
	 */
	protected double pGlobalMerge;
	
	
	public StdUpdateStrategy() {
		
	}
	
	
	public StdUpdateStrategy(MetaModel lhsMM, MetaModel rhsMM) {
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
		
		// mutation probability
		// always mutate
		pMutation = 0.5;
		
		// local merging probability
		// no merging
		pLocalMerge = 1.0;
		
		// global merging probability
		// no merging
		pGlobalMerge = 1.0;
		
		mutStrategy = new RandomMutationStrategy(lhsMM, rhsMM);
		lMergeStrategy = new RandomMergeStrategy(lhsMM, rhsMM);
		gMergeStrategy = lMergeStrategy;
	}
	
	
	public void update(Swarm swarm) {
		List<Particle> updated = new ArrayList<Particle>(swarm.getParticles().size());
		for(Particle p : swarm.getParticles()) {
			p = update(p, swarm);
			updated.add(p);
		}
		swarm.setParticles(updated);
	}
	
	
	public Particle update(Particle p, Swarm swarm) {
		Particle updated = p.clone();
		
		if(toss(pMutation)) {
			updated = mutate(updated);
			if(updated.m.equals(p.m))
				throw new PSORuntimeException();
		}
		
		if(toss(pLocalMerge))
			updated = mergeLocal(updated);
		
		if(toss(pGlobalMerge))
			updated = mergeGlobal(updated, swarm);
		
		check(updated.m, lhsMM, rhsMM);
		
		return updated;	
	}
	
	
	protected Particle mutate(Particle p) {
		return mutStrategy.mutate(p);
	}
	
	
	protected Particle mergeLocal(Particle p) {
		PSOMapping merged = lMergeStrategy.merge(p.m, p.localBest.e0);
		Particle clone = p.clone();
		clone.m = merged;
 		return clone;
	}
	
	
	protected Particle mergeGlobal(Particle p, Swarm swarm) {
		PSOMapping merged = gMergeStrategy.merge(p.m, swarm.getGlobalBest().e0);
		Particle clone = p.clone();
		clone.m = merged;
 		return clone;
	}
	
	
	/*
	 * Random coin toss.
	 */
	private boolean toss(double p) {
		return Math.random() < p;
	}
	
}
