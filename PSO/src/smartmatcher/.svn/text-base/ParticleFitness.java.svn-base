package smartmatcher;

import java.util.Arrays;
import java.util.Map;

import net.sourceforge.jswarm_pso.FitnessFunction;

import org.apache.log4j.Logger;

public class ParticleFitness extends FitnessFunction {
	private static Logger logger = Logger.getLogger(ParticleFitness.class);
	private BlockManager bManager;
	private BlockConstructSimilarity bcSim;
	
	
	public ParticleFitness(BlockManager bManager, BlockConstructSimilarity bcSim) {
		this.bManager = bManager;
		this.bcSim = bcSim;
	}
	
	
	@Override
	public double evaluate(double[] position) {
		int dim = position.length;
		
		double sum = 0;
		Map<Construct,Block> bMap = bManager.getBlocks(position); 
		
		if(bMap.size() != dim) throw new PSOException("Number of constructs does not match dimensions");
		
		for(Construct c : bMap.keySet()) {
			Block b = bMap.get(c);
			sum += bcSim.sim(b, c);				
		}
		
		sum = sum / dim;	// normalize
		
		logger.debug("Particle fitness for position " + Arrays.toString(position) + ": " + sum);
		return sum;
	}
	
	
	public void setBlockManager(BlockManager bManager) {
		this.bManager = bManager;
	}
	
	
	public void setBlockConstructSimilarity(BlockConstructSimilarity bcSim) {
		this.bcSim = bcSim;
	}
	
}
