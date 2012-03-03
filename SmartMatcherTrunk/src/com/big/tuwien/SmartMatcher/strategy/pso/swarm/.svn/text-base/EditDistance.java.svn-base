package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;



public class EditDistance implements Fitness {
	
	public double fitness(PSOMapping m) {
		Levenshtein ls = new Levenshtein();
		Set<Correspondence> cs = m.descendents();
		int size = cs.size();
		
		if(size == 0) return 0;
		
		double sum = 0;
		for(Correspondence c : cs) {
			Element lhs = c.elements().e0.iterator().next();
			Element rhs = c.elements().e1.iterator().next();
			sum += ls.getSimilarity(lhs.getName(), rhs.getName());
		}
		
		return sum / size;
	}

	
	@Override
	public double fitness(Particle p) {
		return fitness(p.m);
	}
}
