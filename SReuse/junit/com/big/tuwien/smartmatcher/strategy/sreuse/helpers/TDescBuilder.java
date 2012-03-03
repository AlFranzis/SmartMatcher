package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortOpDescsByName;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.MappingDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.OpDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.RootOpDesc;

public class TDescBuilder {

	@Test
	public void testBuildingOfSimpleDescription() {
		DescBuilder db = new DescBuilder();
		
		MappingDesc md = 
		db.ops(
			db.c2c("C1", "C1",
				db.a2a("C1.a1", "C1.a1")
			).as("C2C1"),
			db.c2c("C2", "C2").as("C2C2"),
			db.r2r("C2C1", "C2C2", "C1_r1_C2", "C1_r1_C2")
		);
		
		assertEquals(2, md.getOperators().size());
		
		List<RootOpDesc> sops = sortOpDescsByName(md.getOperators());
		RootOpDesc c2c1 = sops.get(0);
		assertEquals("C2C", c2c1.getName());
		
		assertEquals(0, c2c1.getParents().size());
		Set<OpDesc> c2c1Children = c2c1.getChildren();
		assertEquals(2, c2c1Children.size());
		List<OpDesc> sC2C1Children = sortOpDescsByName(c2c1Children);
		
		OpDesc a2a1 = sC2C1Children.get(0);
		assertEquals("A2A", a2a1.getName());
		assertEquals(0, a2a1.getChildren().size());
		assertEquals(1, a2a1.getParents().size());
		assertSame(c2c1, a2a1.getParents().iterator().next());
		
		OpDesc r2r1 = sC2C1Children.get(1);
		
		RootOpDesc c2c2 = sops.get(1);
		assertEquals("C2C", c2c2.getName());
		
		assertEquals(0, c2c2.getParents().size());
		Set<OpDesc> c2c2Children = c2c2.getChildren();
		assertEquals(1, c2c2Children.size());
		assertSame(r2r1, c2c2Children.iterator().next());
		
		assertEquals("R2R", r2r1.getName());
		assertEquals(0, r2r1.getChildren().size());
		assertEquals(2, r2r1.getParents().size());
		List<OpDesc> sR2R1Parents = sortOpDescsByName(r2r1.getParents());
		assertSame(c2c1, sR2R1Parents.get(0));
		assertSame(c2c2, sR2R1Parents.get(1));
		
	}
	
	
	@Test
	public void testSimplifiedDescBuildingNotation() {
		DescBuilder db = new DescBuilder() {
			public MappingDesc getDesc() {
				return ops(
					c2c("C1", "C1",
						a2a("C1.a1", "C1.a1")
					).as("C2C1"),
					c2c("C2", "C2",
						a2a("C2.a3", "C2.a3")
					).as("C2C2"),
					r2r("C2C1", "C2C2", "C1_r1_C2", "C1_r1_C2")
				);
			}
		};
		

	}
}
