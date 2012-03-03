/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import java.util.List;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

public class Swarm {
	private static Logger logger = Logger.getLogger(Swarm.class);
	
	private UpdateStrategy uStrategy;
	private List<Particle> particles;
	private T2<PSOMapping,Double> globalBest = Tuples.t(null, 0.0);
	
	
	public Swarm(List<Particle> particles, UpdateStrategy uStrategy) {
		this.uStrategy = uStrategy;
		this.particles = particles;
	}
	
	
	public T2<PSOMapping,Double> getGlobalBest() {
		return globalBest;
	}
	
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	
	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}
	
	
	public void evaluate() {
		for(Particle p : particles) {
			p.evaluate();
			
			// check if global best is beaten
			if(p.fitness > globalBest.e1) {
				globalBest = Tuples.t(p.m, p.fitness);
				logger.debug("Global best improved: " + globalBest.e1);
			}
				
		}
	}
	
	
	public void update() {
		uStrategy.update(this);
	}
	
	
	@Override
	public String toString() {
		return "Swarm(" + particles + ")";
	}
}