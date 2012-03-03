package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import static com.big.tuwien.SmartMatcher.strategy.pso.swarm.Helpers.check;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.MappingGenerator;
import com.big.tuwien.SmartMatcher.strategy.pso.generate.PSORandomMappingGenerator;
import com.big.tuwien.SmartMatcher.strategy.pso.swarm.Evaluation.EvaluationResult;

public class DPSO {
	private static Logger logger = Logger.getLogger(DPSO.class);
	
	protected MetaModel lhsMM;
	protected MetaModel rhsMM;
	
	protected int swarmSize;
	
	protected Swarm swarm;

	protected Evaluation evaluation;
	protected TerminationCriteria term;
	
	private MappingGenerator generator;
	private UpdateStrategy uStrategy;
	
	
	public DPSO() {
		swarmSize = 10;
		
		term = new EpochTerminationCriteria(300);
		
		evaluation = new Evaluation() {
			
			@Override
			public EvaluationResult evaluate(Swarm s) {
				return null;
			}
			
			@Override
			public EvaluationResult evaluate(Particle p) {
				return null;
			}
		};
	}
	
	
	public DPSO(MetaModel lhsMM, MetaModel rhsMM) {
		this();
		
		this.lhsMM = lhsMM;
		this.rhsMM = rhsMM;
		
		generator = new PSORandomMappingGenerator(lhsMM, rhsMM);
		
		uStrategy = new StdUpdateStrategy(lhsMM, rhsMM);
	}
	
	
	public void setLhsMM(MetaModel lhsMM) {
		this.lhsMM = lhsMM;
	}
	
	
	public void setRhsMM(MetaModel rhsMM) {
		this.rhsMM = rhsMM;
	}
	
	
	public void setGenerator(MappingGenerator generator) {
		this.generator = generator;
	}
	
	
	public void setUpdateStrategy(UpdateStrategy updateStrategy) {
		this.uStrategy = updateStrategy;
	}

	
	public void test() {
		logger.debug("test() entered");
		logger.debug("test() exit");
	}
	
	
	public PSOMapping optimize() {
		// generate initial swarm population
		List<Particle> particles = initialize();

		swarm = new Swarm(particles, uStrategy);
		swarm.evaluate();
		logger.info("Initialized swarm: " + swarm);

		// optimization loop
		EvaluationResult eval = evaluation.evaluate(swarm);
		int runEpoch = 0;
		while(!term.terminate(eval)) {
			swarm.update();
			swarm.evaluate();

			logger.debug("Swarm after epoch " + (++runEpoch) + ": " + swarm);
		}

		logger.debug("FINISHED optimization. Optimized mapping with fitness " 
					+ swarm.getGlobalBest().e1 
					+ ", mapping: " + swarm.getGlobalBest().e0);
		return swarm.getGlobalBest().e0;
	}
	
	
	protected List<Particle> initialize() {
		List<Particle> particles = new Vector<Particle>(swarmSize);
		for(int i = 0; i < swarmSize; i++) {
			PSOMapping m = generator.generate();
			
			check(m, lhsMM, rhsMM);
			
			particles.add(new Particle(m));
		}
		
		return particles;
	}
	
}
