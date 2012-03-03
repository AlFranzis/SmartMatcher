package smartmatcher;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;


public class TPSO {
	private static Logger logger = Logger.getLogger(TPSO.class);
	
	
	public TPSO() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	public SMSwarm generateSwarm(BlockManager bm, int nrParticles) {
		ParticleFitness pFitness = new ParticleFitness(bm, new BlockConstructSimilarity());
		int dimensions = bm.constructs();
		
		SMSwarm swarm = new SMSwarm(
				nrParticles,
				dimensions,
				pFitness
				);
		
		swarm.setMinPosition(0);
		double maxPosition = bm.blocks() - 0.01;
		swarm.setMaxPosition(maxPosition);
		
		// swarm.setMaxMinVelocity(0.3);
		// swarm.setInertia(0.9);
		
		return swarm;
	}
	
	
	@Test
	public void test1() {
		System.out.println("********** Test1 *************"); 
		BlockManager bManager = new DummyBlockManager();
		
		// default: 25 particles
		//int nrParticles = Swarm.DEFAULT_NUMBER_OF_PARTICLES;
		int nrParticles = 5;
		
		SMSwarm swarm = generateSwarm(bManager, nrParticles);
		
		int epochs = 60;
		
		swarm.init();
		
		for( int ep = 1; ep <= epochs; ep++ ) {
			System.out.println("**** Epoche " + ep + " ****");
			
			swarm.evaluate();
			Map<Construct,Block> blocks = bManager.getBlocks(swarm.getBestPosition());
			System.out.println("Best global mapping:");
			Utils.printMapping(blocks);
				
			System.out.println("Particles:");
			Utils.printParticles(swarm);
			
			// System.out.println(swarm.toStringStats());
			
			// update particle position and velocity
			swarm.update();
		}

		System.out.println("Final solution:");
		System.out.println(swarm.toStringStats());
		Map<Construct,Block> bestMapping = bManager.getBlocks(swarm.getBestPosition());
		System.out.println("Best mapping found:");
		Utils.printMapping(bestMapping);
		
	}
	
	
	@Test
	public void test2() {
		System.out.println("********** Test2 *************");
		
		BlockManager bManager = new DummyBlockManager();
		int nrParticles = 1;
	
		SMSwarm swarm = generateSwarm(bManager, nrParticles);
		
		int epochs = 20;
		
		for( int ep = 1; ep <= epochs; ep++ ) {
			System.out.println("**** Epoche " + ep + " ****");
			swarm.evolve();
			Map<Construct,Block> blocks = bManager.getBlocks(swarm.getBestPosition());
			System.out.println("Best global mapping:");
			Utils.printMapping(blocks);
			
			System.out.println("Particles:");
			Utils.printParticles(swarm);
			
			// System.out.println(swarm.toStringStats());
		}

		System.out.println("Final: " + swarm.toStringStats());
		
	}
	
	
	@Test
	public void test3() {
		System.out.println("********** Test3 *************"); 
		BlockManager bManager = new DummyBlockManager();
		
		// default: 25 particles
		//int nrParticles = Swarm.DEFAULT_NUMBER_OF_PARTICLES;
		int nrParticles = 5;
		
		SMSwarm swarm = generateSwarm(bManager, nrParticles);
		
		int epochs = 60;
		SwarmExecution.Builder builder = new SwarmExecution.Builder(swarm);
		SwarmExecution swexec = builder.blockManager(bManager)
									.epochs(epochs).build();
		swexec.execute();
	}
	
	
	
	
	
	public class A2ABlock extends Block {
		
		public A2ABlock(int id, Clazz c, Attribute a) {
			super(id);
			this.sourceConstructs.add(c);
			this.sourceConstructs.add(a);
		}
	}
}
