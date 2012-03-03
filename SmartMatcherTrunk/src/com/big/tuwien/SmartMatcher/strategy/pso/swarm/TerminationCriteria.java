/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import com.big.tuwien.SmartMatcher.strategy.pso.swarm.Evaluation.EvaluationResult;



public interface TerminationCriteria {
	public boolean terminate(EvaluationResult er);
}