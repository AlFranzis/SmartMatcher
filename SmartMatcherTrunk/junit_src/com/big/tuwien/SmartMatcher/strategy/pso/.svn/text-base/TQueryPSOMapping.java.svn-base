package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TQueryPSOMapping extends QueryBuilder {
	private LhsMM lhsMM = new LhsMM();
	private RhsMM rhsMM = new RhsMM();
	private Mapping mapping = new Mapping();
	
	
	public TQueryPSOMapping() {}
	
	
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
						r("r1", c("C1"), c("C2"))
					));
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
						r("r1", c("C1"), c("C2"))
					));
		}
	}
	
	
	public class Mapping extends MappingBuilder {
		public PSOMapping getMapping() {
			init(lhsMM.getMetaModel(),rhsMM.getMetaModel());
			PSOMapping m =
				m(li(
						c2c("C1","C1", 
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
				
						r2r("r1","r1", 
								c2c("c2c1"), 
								c2c("c2c2")
						)
					)
				);
			return m;
		}
	}
	
	
	@Test
	public void simpleC2CQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m)
						.descendants(c2c("C1","C1"));
		
		assertEquals(1, result.size());
		assertEquals("C2C(C1,C1)", result.iterator().next().toString());
	}
	
	
	@Test
	public void simpleA2AQuery() {
		PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m)
									.descendants(a2a("a1","a1"));
				
		assertEquals(1, result.size());
		assertEquals("A2A(C1.a1,C1.a1)", result.iterator().next().toString());
		
	}
	
	
	@Test
	public void simpleR2RQuery() {
		PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(r2r("r1","r1"));
		
		assertEquals(1, result.size());
		assertEquals("R2R(C1_r1_C2,C1_r1_C2)", result.iterator().next().toString());
	}
	
	
	@Test
	public void multipleC2CsQuery() {
		PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = 
			QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1"),
									c2c("C2", "C2")
									)));
				
		assertEquals(2, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
	}
	
	
	@Test
	public void multipleA2AsQuery() {
		PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									a2a("a1","a1"),
									a2a("a3", "a3")
									)));
	
		assertEquals(2, result.size());
		assertTrue(result.toString().contains("A2A(C1.a1,C1.a1)"));
		assertTrue(result.toString().contains("A2A(C2.a3,C2.a3)"));
	}
	
	
	@Test
	public void mixedQuery() {
		PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1"),
									a2a("a3","a3"),
									r2r("r1","r1")
									)));
			
		assertEquals(3, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("A2A(C2.a3,C2.a3)"));
		assertTrue(result.toString().contains("R2R(C1_r1_C2,C1_r1_C2)"));
	}
	
	
	@Test
	public void complexNoResultQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m)
						.descendants(cs(li(
									c2c("C1","C1",li(a2a("a3","a3")))
									)));
			
		assertEquals(0, result.size());
	}
	
	
	@Test
	public void redundantComplexQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1"))),
									c2c("C1","C1",li(a2a("a1","a1")))
									)));
		
		assertEquals(1, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
	}
	
	
	@Test
	public void complexQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1")))
									)));
				
		assertEquals(1, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
	}
	
	
	@Test
	public void complexQuery2() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1"))),
									c2c("C2","C2",li(a2a("a4","a4"))),
									r2r("r1","r1")
									)));
			
		assertEquals(3, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
		assertTrue(result.toString().contains("R2R(C1_r1_C2,C1_r1_C2)"));
	}
	
	
	@Test
	public void complexQuery3() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1"))).as("c2c1"),
									c2c("C2","C2",li(a2a("a4","a4"))).as("c2c2"),
									r2r("r1","r1",c2c("c2c1"),c2c("c2c2"))
									)));
				
		assertEquals(2, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
	}
	
	
	@Test
	public void complexQuery4() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m)
							.descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1"))).as("c2c1"),
									c2c("C2","C2",li(a2a("a4","a4"), a2a("a3","a3"))).as("c2c2"),
									r2r("r1","r1",c2c("c2c1"),c2c("c2c2"))
									)));
				
		assertEquals(2, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
	}
	
	
	@Test
	public void complexQuery5() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m)
							.descendants(cs(li(
									c2c("C1","C1",li(a2a("a1","a1"))).as("c2c1"),
									c2c("C2","C2",li(a2a("a4","a4"), a2a("a3","a3"))).as("c2c2"),
									r2r("r1","r1",c2c("c2c1"),c2c("c2c2")),
									a2a("a2","a2")
									)));
				
		assertEquals(3, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
		assertTrue(result.toString().contains("A2A(C1.a2,C1.a2)"));
	}
	
	
	@Test
	public void anyQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(anyA2A()))
									)));
				
		assertEquals(1, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
	}
	
	
	@Test
	public void anyQuery2() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(anyA2A(), anyA2A()))
									)));
				
		assertEquals(1, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
	}
	
	
	@Test
	public void anyQueryWithNoResult() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(anyA2A(), anyA2A(), anyA2A()))
									)));
			
		assertEquals(0, result.size());
	}
	
	
	@Test
	public void complexAnyQuery() {
		final PSOMapping m = mapping.getMapping();
		Set<Correspondence> result = QueryPSOMapping.instance(m).descendants(cs(li(
									c2c("C1","C1",li(anyA2A())).as("c2c1"),
									c2c("C2","C2",li(a2a("a4","a4"), a2a("a3","a3"))).as("c2c2"),
									r2r("r1","r1",c2c("c2c1"),c2c("c2c2")),
									a2a("a2","a2")
									)));
				
		assertEquals(3, result.size());
		assertTrue(result.toString().contains("C2C(C1,C1)"));
		assertTrue(result.toString().contains("C2C(C2,C2)"));
		assertTrue(result.toString().contains("A2A(C1.a2,C1.a2)"));
	}
	
}
