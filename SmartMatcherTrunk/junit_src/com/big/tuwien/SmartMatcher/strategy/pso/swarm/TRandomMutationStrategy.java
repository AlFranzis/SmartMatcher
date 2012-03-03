package com.big.tuwien.SmartMatcher.strategy.pso.swarm;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.A2AMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.A2ASwapper;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.C2CMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.C2CSwapper;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.R2RMutator;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.R2RSwapper;

public class TRandomMutationStrategy {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TRandomMutationStrategy() {
		DOMConfigurator.configure("junit_log4j.xml");
		lhsMM = new LhsMM();
		rhsMM = new RhsMM();
	}

	
	public class LhsMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LhsMM() {
			mm =  mm(li(
					c("C1", 
						li(
							a("a1"),
							a("a2")
						)
					).as("C1"),
					c("C2", 
						li(
							a("a3"),
							a("a4")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a5"),
							a("a6"),
							a("a7")
						)
					).as("C3"),
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel_ getMetaModel() {
			return mm;
		}
	}
	
	
	public class RhsMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RhsMM() {
			mm = mm(li(
					c("C1", 
						li(
							a("a1"),
							a("a2")
						)
					).as("C1"),
					c("C2", 
						li(
							a("a3"),
							a("a4")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a5"),
							a("a6"),
							a("a7")
						)
					).as("C3"),
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		public MetaModel_ getMetaModel() {
			return mm;
		}
	}

	
	public class Mapping extends MappingBuilder {
		public PSOMapping getMapping() {
			init(lhsMM.getMetaModel(), rhsMM.getMetaModel());
			
			PSOMapping m =
				m(li(
						c2c("C1", "C1", 
								li(
									a2a("a1","a1"), 
									a2a("a2","a2")
								)
							).as("c2c1"),
						c2c("C2", "C2", 
								li(
									a2a("a3","a3"), 
									a2a("a4","a4")
								)
							).as("c2c2"),
						c2c("C3", "C3",
								li(
									a2a("a5", "a5"),
									a2a("a6", "a6"),
									a2a("a7", "a7")
								)
							).as("c2c3"),
						r2r("r1","r1", 
								c2c("c2c1"), 	
								c2c("c2c2")
							),
						r2r("r2","r2", 
								c2c("c2c1"), 	
								c2c("c2c2")
							),
						r2r("r3","r3", 
								c2c("c2c2"), 	
								c2c("c2c3")
						)
					)
				);
			
			return m;
		}
	}
	
	
	@Test
	public void testMutation() {
		PSOMapping m = new Mapping().getMapping();
		
		RandomMutationStrategy mutStrategy = 
			new RandomMutationStrategy(
					Arrays.asList(
							new C2CSwapper(), 
							new A2ASwapper(),
							new R2RSwapper(),
							new C2CMutator(lhsMM.getMetaModel(), true),
							new A2AMutator(true),
							new R2RMutator(true)
					));
		
		PSOMapping mutated = mutStrategy.mutate(m);
		assertTrue(!mutated.equals(m));
	}
}
