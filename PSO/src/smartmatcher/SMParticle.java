package smartmatcher;

import net.sourceforge.jswarm_pso.Particle;

/**
 * Represents a possible PSO solution.
 * @author alex
 *
 */
public class SMParticle extends Particle {
	
	
	public SMParticle(int dimensions) {
		super(dimensions);
	}


	@Override
	public SMParticle selfFactory() {
		return new SMParticle(this.getDimention());
	}
	
	
	/**
	 * Returns the discrete position of this particle.
	 * The discrete position is calculated by using truncation.
	 * @return
	 */
	public int[] getDiscretePosition() {
		double[] ps = getPosition();
		return Utils.discretize(ps);
	}
	
}

