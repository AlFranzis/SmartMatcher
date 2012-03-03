package smartmatcher;

import java.util.Map;

import org.apache.log4j.Logger;

public class SwarmExecution {
	private static Logger logger = Logger.getLogger(SwarmExecution.class);
	private SMSwarm swarm;
	private int epochs;
	private BlockManager bm;
	
	
	private SwarmExecution(Builder builder) {
		this.swarm = builder.swarm;
		this.epochs = builder.epochs;
		this.bm = builder.bm;
	}
	
	
	public void execute() {
		swarm.init();
		
		for( int ep = 1; ep <= epochs; ep++ ) {
			logger.debug("**** Epoche " + ep + " ****");
			
			swarm.evaluate();
			Map<Construct,Block> blocks = bm.getBlocks(swarm.getBestPosition());
			System.out.println("Best global mapping:");
			Utils.printMapping(blocks);
				
			logger.debug("Particles:");
			Utils.printParticles(swarm);
			
			// System.out.println(swarm.toStringStats());
			
			// update particle position and velocity
			swarm.update();
		}
	}
	
	
	public static class Builder {
		private SMSwarm swarm;
		private int epochs;
		private BlockManager bm;
		
		public Builder(SMSwarm swarm) {
			this.swarm = swarm;
		}
		
		
		public Builder epochs(int epochs) {
			this.epochs = epochs;
			return this;
		}
		
		
		public Builder blockManager(BlockManager bm) {
			this.bm = bm;
			return this;
		}
		
		
		public SwarmExecution build() {
			return new SwarmExecution(this);
		}
		
	}
}
