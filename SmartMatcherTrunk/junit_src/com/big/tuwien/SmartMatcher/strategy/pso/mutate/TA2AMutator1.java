package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Set;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;


/**
 * Tests the {@link A2AMutator}.
 * @author alex
 *
 */
public class TA2AMutator1 extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TA2AMutator1() {
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
					c("C4", 
						li(
							a("a8")
						)
					).as("C4"),
					c("C5", 
						li(
							a("a9"),
							a("a10")
						)
					).as("C5")	,
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
	
	
	public class Mapping1 extends MappingBuilder {
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
						c2c("C2", "C3", 
								li(
									a2a("a3","a5"), 
									a2a("a4","a6")
								)
							).as("c2c2"),
						c2c("C3", "C2",
								li(
									a2a("a5", "a3"),
									a2a("a6", "a4")
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
	public void testMutate1() {
	
		final PSOMapping m = new Mapping1().getMapping();
		A2AMutator mutator = new A2AMutator(true) {
			
			@Override
			protected T2<PSOA2A, Element> pick(PSOMapping m,
					boolean lhs) {
				PSOA2A pickedA2A = (PSOA2A) QueryPSOMapping.instance(m)
												.descendants(
													cs(li(
														// A2A to mutate
														a2a("a6","a4")
													)))
													.iterator().next();
				// mutate A2A(a6,a4) to A2A(a7,a4)
				Element unmappedAtt = lhsMM.getMetaModel().getAttributeByName("a7");
				
				return Tuples.t(pickedA2A, unmappedAtt);
			}
		};
		
		// perform A2A mutation
		PSOMapping mutated = mutator.mutate(m);
		
		assertFalse(mutated.equals(m));
		
		Set<Correspondence> result1 = QueryPSOMapping.instance(mutated)
									.descendants(cs(li(
										c2c("C3","C2",anyA2A(2))
									)));
		assertFalse(result1.isEmpty());
		assertEquals(2, result1.iterator().next().descendents(PSOA2A.class).size());
		
		Set<Correspondence> result2 = QueryPSOMapping.instance(mutated)
									.descendants(cs(li(
										c2c("C3","C2",
											li(
												a2a("a5","a3"),
												// mutated A2A
												a2a("a7","a4")
											))
									)));
		assertFalse(result2.isEmpty());
		
		assertEquals(m.descendents(PSOR2R.class), 
				mutated.descendents(PSOR2R.class));
		
	}
	
}
