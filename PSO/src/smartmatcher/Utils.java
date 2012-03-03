package smartmatcher;

import java.util.Arrays;
import java.util.Map;

import net.sourceforge.jswarm_pso.Particle;
import net.sourceforge.jswarm_pso.Swarm;

public class Utils {
	
	/**
	 * Transform the given array of reals into an array of integers
	 * by truncation.
	 * @param rs
	 * @return
	 */
	public static int[] discretize(double[] rs) {
		int[] ds = new int[rs.length];
		for(int i = 0; i < rs.length; i++) {
			// convert double to int by truncation
			ds[i] = new Double(rs[i]).intValue();
		} 
		return ds; 
	}
	
	
	public static void printParticles(Swarm sw) {
		for (int i = 0; i < sw.getParticles().length; i++) {
			Particle p = sw.getParticle(i);
			printParticle(i, p);
		}
	}
	
	
	public static void printParticle(int id, Particle p) {
		String pos = Arrays.toString(p.getPosition());
		String pbest = Arrays.toString(p.getBestPosition());
		String vel = Arrays.toString(p.getVelocity());
		System.out.println("id: " + id + " position: " + pos + " fitness: " + p.getFitness() + " pbest: " + pbest + " velocity: " + vel);
	}
	
	
	public static void printMapping(Map<Construct,Block> mapping) {
		System.out.println("Construct -> Block");
		for(Map.Entry<Construct,Block> en : mapping.entrySet()) {
			System.out.println(en.getKey().getId() + " -> " + en.getValue().getId());
		}
	}
}
