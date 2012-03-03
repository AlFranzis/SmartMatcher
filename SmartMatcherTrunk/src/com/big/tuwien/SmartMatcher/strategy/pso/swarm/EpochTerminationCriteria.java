/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import com.big.tuwien.SmartMatcher.strategy.pso.swarm.Evaluation.EvaluationResult;


public class EpochTerminationCriteria implements TerminationCriteria {
	private int epochs;
	private int passedEpochs = 0;

	public EpochTerminationCriteria(int epochs) {
		this.epochs = epochs;
	}
	
	@Override
	public boolean terminate(EvaluationResult er) {
		passedEpochs++;
		
		return passedEpochs >= epochs;
	}
}