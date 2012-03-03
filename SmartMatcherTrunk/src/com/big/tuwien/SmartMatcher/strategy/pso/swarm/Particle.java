/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import com.big.tuwien.SmartMatcher.strategy.pso.Cloner;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class Particle {
		public PSOMapping m;
		public double fitness = 0.0;
		public T2<PSOMapping,Double> localBest = Tuples.t(null, 0.0);
		
		
		public Particle(PSOMapping m) {
			this.m = m;
		}
		
		
		public void evaluate() {
			// update fitness
			fitness = fitness(this);
			
			// check if local best is beaten
			if(fitness > localBest.e1) {
				localBest = Tuples.t(m, fitness);
			}
		}
		
//		private double fitness() {
//			return Math.random();
//		}
		
		
		private double fitness(Particle p) {
			return new EditDistance().fitness(p.m);
		}
		
		
		public Particle clone() {
			Particle clone = new Particle(new Cloner(m, Cloner.all())
								.getClone());
			clone.fitness = fitness;
			clone.localBest = Tuples.t(localBest.e0, localBest.e1);
			return clone;
		}
		
		
		@Override
		public String toString() {
			return "P(" + localBest.e1 + "," + m + ")";
		}
	}