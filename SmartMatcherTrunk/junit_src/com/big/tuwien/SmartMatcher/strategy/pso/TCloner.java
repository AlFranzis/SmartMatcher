package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class TCloner extends MappingBuilder {
	
	public TCloner() {}

	
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
	
	
	@Test
	public void testAllCloning() {
		
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
		
		Cloner cloner = new Cloner(m, Cloner.all());
		PSOMapping clone = cloner.getClone();
		
		// check correct amount of correspondences in the clone
		assertEquals(m.descendents().size(), clone.descendents().size());
		
		// clone equals original
		assertTrue(clone.equals(m));
	}
	
	
	@Test
	public void testAllCloneMap() {
		
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
		
		Cloner cloner = new Cloner(m, Cloner.all());
		PSOMapping clone = cloner.getClone();

		assertTrue(clone.equals(m));
		
		Map<Correspondence,Correspondence> cloneMap = cloner.getCloneMap();
		System.out.println("Clone map:" + cloneMap);
		
		assertEquals(m.descendents().size(), clone.descendents().size());
		assertEquals(clone.descendents().size(), cloneMap.size());
		
	}
	
	
	@Test
	public void testCloningWithoutC2C() {
		
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
		
		Correspondence c2c1 = m.children.iterator().next();
		int dcs = c2c1.descendents().size();
		
		// clone mapping without c2c1 and it's descendants
		Cloner cloner = new Cloner(m, Cloner.without(c2c1));
		PSOMapping clone = cloner.getClone();
		
		// check correct amount of correspondences in the clone
		assertEquals(m.descendents().size() - dcs - 1, clone.descendents().size());
		
		// check that c2c1 is not in the clone
		assertTrue(!clone.descendents().contains(c2c1));
		
		// check that descendants of c2c1 are not in the clone
		Set<Correspondence> intersect = new HashSet<Correspondence>(clone.descendents());
		intersect.retainAll(c2c1.descendents());
		assertTrue(intersect.isEmpty());
	}
	
	
	@Test
	public void testCloningWithoutA2A() {
		
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
		
		Correspondence a2a1 = m.descendents(PSOA2A.class).iterator().next();
		
		// clone mapping without a2a1
		Cloner cloner = new Cloner(m, Cloner.without(a2a1));
		PSOMapping clone = cloner.getClone();
		
		// check correct amount of correspondences in the clone
		assertEquals(m.descendents().size() - 1, clone.descendents().size());
		
		// check that a2a1 is not in the clone
		assertTrue(!clone.descendents().contains(a2a1));
		
	}
	
	
	@Test
	public void testCloningWithoutR2R() {
		
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
		
		Correspondence r2r1 = m.descendents(PSOR2R.class).iterator().next();
		
		// clone mapping without r2r1
		Cloner cloner = new Cloner(m, Cloner.without(r2r1));
		PSOMapping clone = cloner.getClone();
		
		// check correct amount of correspondences in the clone
		assertEquals(m.descendents().size() - 1, clone.descendents().size());
		
		// check that r2r1 is not in the clone
		assertTrue(!clone.descendents().contains(r2r1));
		
		
	}
	
	
	public static StringBuffer prettyPrint(PSOMapping m) {
		StringBuffer buf = new StringBuffer();
		for(Correspondence child : m.children) {
			buf.append(prettyPrint(child,5));
			buf.append("\n");
		}
		return buf;
	}
	
	
	public static StringBuffer prettyPrint(Correspondence c, int indent) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0 ; i < indent; i++) buf.append(' ');
		buf.append(c);
		buf.append("\n");
		for(Correspondence dc : c.descendents()) {
			buf.append(prettyPrint(dc, indent + 5));
		}
		return buf;
	}
}
