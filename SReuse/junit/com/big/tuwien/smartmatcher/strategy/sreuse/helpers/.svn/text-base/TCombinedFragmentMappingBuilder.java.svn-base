package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.DescBuilder.MappingDesc;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;

public class TCombinedFragmentMappingBuilder extends EasyFragmentMappingBuilder {
	
	
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
	
	
	@Test
	public void testCombinedFragmentMappingBuilding() {
		MetaModel lhsMM = new LhsMM().getMetaModel();
		MetaModel rhsMM = new RhsMM().getMetaModel();
		
		CombinedFragmentBuilder cfb = new CombinedFragmentBuilder(lhsMM, rhsMM);
		
		DescBuilder db = new DescBuilder();
		MappingDesc md = 
		db.ops(
			db.c2c("C1", "C1", 
					db.a2a("C1.a1", "C1.a1")
			).as("C2C1"),
			db.c2c("C2", "C2", 
					db.a2a("C2.a3", "C2.a3")
			).as("C2C2"),
			db.r2r("C2C1", "C2C2", "C1_r1_C2", "C1_r1_C2")
		);
		
		T2<Fragment,Fragment> fragments = cfb.fm(md);
		Fragment lhsFrag = fragments.e0;
		
		// **************************
		// build ORACLE fragments 
		// **************************
		
		FragmentBuilder lhsFb = new FragmentBuilder(lhsMM);
		lhsFb.enableIdGenerator(false);
		Fragment_ lhsFragOracle = lhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		
		FragmentBuilder rhsFb = 
			new FragmentBuilder(rhsMM);
		rhsFb.enableIdGenerator(false);
		Fragment_ rhsFragOracle = rhsFb.f("C1", "C1.a1", "C2", "C2.a3", "C1_r1_C2");
		
		init(lhsFragOracle, rhsFragOracle);
		
		FragmentMapping fm =
			fm(li(
				c2c("C1","C1",
				li(
					a2a("C1.a1", "C1.a1")
			
				)
				).as("C2C1"),
				c2c("C2","C2",
				li(
					a2a("C2.a3", "C2.a3")
				)
				).as("C2C2"),
				r2r(li(op("C2C1"), op("C2C2")), 
					"C1_r1_C2", "C1_r1_C2")
			));
		
		lhsFragOracle.setMappings(asArraySet(fm));
		rhsFragOracle.setMappings(asArraySet(fm));
		
		// **************************
		// CHECKS
		// **************************
		
		assertEquals(lhsFragOracle, lhsFrag);
		
	}	
	
}
