package smartmatcher;

import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;
import net.sourceforge.jswarm_pso.Swarm;

public class SMSwarm extends Swarm {

	public SMSwarm(int numberOfParticles, int dimensions,
			FitnessFunction fitnessFunction) {
		super(numberOfParticles, new SMParticle(dimensions), fitnessFunction);
	}
	
	
	public SMParticle[] getParticles() {
		Particle[] ps = super.getParticles();
		SMParticle[] smps = new SMParticle[ps.length];
		
		for (int i = 0; i < ps.length; i++) {
			smps[i] = (SMParticle) ps[i];
		}
		return smps;
	}
	
	
	public int[] getBestDiscretePosition() {
		return Utils.discretize(super.getBestPosition());
	}
	
}
