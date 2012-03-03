package com.big.tuwien.SmartMatcher.strategy.pso.mutate;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.MappingBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.QueryPSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;


public class TC2CSwapper extends QueryBuilder {
	
	public TC2CSwapper() {}

	
	public class LhsMM extends MetaModelBuilder {
		public MetaModel_ getMetaModel() {
			return mm(li(
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
	}
	
	
	public class RhsMM extends MetaModelBuilder {
		public MetaModel_ getMetaModel() {
			return mm(li(
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
	}
	
	
	public class Mapping extends MappingBuilder {
		public PSOMapping getMapping() {
			init(new LhsMM().getMetaModel(), new RhsMM().getMetaModel());
			
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
	public void testSimpleSwap() {
	
		PSOMapping m = new Mapping().getMapping();
		
		C2CSwapper swapper = new C2CSwapper() {
			@Override
			public T2<PSOC2C,PSOC2C> pick(PSOMapping m) {
				Set<Correspondence> result = 
					new QueryPSOMapping(m) {
						public Set<Correspondence> query() {
							return descendants(cs(li(
											c2c("C1","C1"),
											c2c("C2","C2")
											)));
						}
					}.query();
				Iterator<Correspondence> it = result.iterator();
				T2<PSOC2C,PSOC2C> t2 = Tuples.t((PSOC2C)it.next(), (PSOC2C)it.next());
				return t2;
			}
		};
		
		// execute swapping
		PSOMapping swapped = swapper.swap(m);
		
		// 13 original mappings
		// after swapping: 12 mappings because R2R(r3,r3) is lost
		assertEquals(12, swapped.descendents().size());
		
		
		// check if swapped contains:
		// C2C(C1,C2) with 2 A2As
		// C2C(C2,C1) with 2 A2As
		// C2C(C3,C3) with 3 A2As
		Set<Correspondence> result1 = QueryPSOMapping.instance(swapped)
						.descendants(cs(li(
									c2c("C1","C2",anyA2A(2)),
									c2c("C2","C1", anyA2A(2)),
									c2c("C3","C3", anyA2A(3))
									)));
		
		assertEquals(3, result1.size());
		
		
		// check if swapped contains:
		// compatible (preserved) R2Rs
		// R2R(r1,r1)
		// R2R(r2,r2)
		Set<Correspondence> result2 = QueryPSOMapping.instance(swapped)
							.descendants(cs(li(
									r2r("r1","r1"),
									r2r("r2","r2")
									)));
				
		assertEquals(2, result2.size());
	}
}
