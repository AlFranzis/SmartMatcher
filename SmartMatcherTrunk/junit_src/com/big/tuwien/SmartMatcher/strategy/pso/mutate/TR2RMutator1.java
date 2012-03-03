package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
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
 * Tests the {@link R2RMutator}.
 * @author alex
 *
 */
public class TR2RMutator1 extends QueryBuilder {
	private LhsMM lhsMM;
	private RhsMM rhsMM;
	 
	
	public TR2RMutator1() {
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
						c2c("C1", "C2", 
								li(
									a2a("a1","a3"), 
									a2a("a2","a4")
								)
							).as("c2c1"),
						c2c("C2", "C3", 
								li(
									a2a("a3","a5"), 
									a2a("a4","a6")
								)
							).as("c2c2"),
						c2c("C3", "C1",
								li(
									a2a("a5", "a1"),
									a2a("a6", "a2")
								)
							).as("c2c3"),
						r2r("r1","r3", 
								c2c("c2c1"), 
								c2c("c2c2")
						)
					)
				);
			
			return m;
		}
	}
	
	
	@Test
	public void testMutate1() {
	
		final PSOMapping m = new Mapping1().getMapping();
		R2RMutator mutator = new R2RMutator(true) {
			
			@Override
			protected T2<PSOR2R, Element> pick(PSOMapping m,
					boolean lhs) {
				PSOR2R pickedR2R = (PSOR2R) QueryPSOMapping.instance(m)
												.descendants(
													cs(li(
														// R2R to mutate
														r2r("r1","r3")
													)))
													.iterator().next();
				// mutate R2R(r1,r3) to R2R(r2,r3)
				Element unmappedRef = lhsMM.getMetaModel()
										.getReferenceByName("r2");
				
				return Tuples.t(pickedR2R, unmappedRef);
			}
		};
		
		
		// perform R2R mutation
		PSOMapping mutated = mutator.mutate(m);
			
		assertFalse(mutated.equals(m));
		
		assertTrue(QueryPSOMapping.instance(mutated)
						.contains(cs(li(
							r2r("r2","r3")
						))));
		
		assertFalse(QueryPSOMapping.instance(mutated)
				.contains(cs(li(
					r2r("r1","r3")
				))));	
		
		assertEquals(m.descendents().size(), 
				mutated.descendents().size());
		
		assertEquals(m.descendents(PSOA2A.class), 
				mutated.descendents(PSOA2A.class));
		
		assertFalse(m.descendents(PSOR2R.class).equals( 
				mutated.descendents(PSOR2R.class)));
		
	}
	
}
