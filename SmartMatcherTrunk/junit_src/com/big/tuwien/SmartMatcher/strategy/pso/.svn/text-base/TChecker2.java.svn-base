package com.big.tuwien.SmartMatcher.strategy.pso;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder.MetaModel_;

public class TChecker2  extends MappingBuilder {
	
	public TChecker2() {
		DOMConfigurator.configure("junit_log4j.xml");
	}

	
	public class LhsMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LhsMM() {
			mm = mm(li(
					c("Person", 
						li(
							a("firstname"),
							a("age")
						)
					).as("C1"),
					c("Family", 
						li(
							a("lastname"),
							a("members")
						)
					).as("C2"),
					c("Address", 
						li(
							a("street"),
							a("city"),
							a("country")
						)
					).as("C3"),
					r("belongsTo", c("C1"), c("C2")),
					r("member", c("C2"), c("C1")),
					r("livesAt", c("C2"), c("C3"))
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
					c("Person", 
						li(
							a("firstname"),
							a("age")
						)
					).as("C1"),
					c("Family", 
						li(
							a("lastname"),
							a("members")
						)
					).as("C2"),
					c("Address", 
						li(
							a("street"),
							a("city"),
							a("country")
						)
					).as("C3"),
					r("belongsTo", c("C1"), c("C2")),
					r("member", c("C2"), c("C1")),
					r("livesAt", c("C2"), c("C3"))
				)
				);
		}
		public MetaModel_ getMetaModel() {
			return mm;
		}
	}

	
	@Test
	public void testCompleteMapping() {
		MetaModel_ lhsMM = new LhsMM().getMetaModel();
		MetaModel_ rhsMM = new RhsMM().getMetaModel();
		
		init(lhsMM, rhsMM);
		
		PSOMapping m =
			m(li(
					c2c("Person", "Person", 
							li(
								a2a("age","firstname"), 
								a2a("firstname","age")
							)
						).as("c2c1"),
					c2c("Family", "Family", 
							li(
								a2a("members","members"), 
								a2a("lastname","lastname")
							)
						).as("c2c2"),
					c2c("Address", "Address",
							li(
								a2a("city", "city"),
								a2a("street", "street"),
								a2a("country", "country")
							)
						).as("c2c3"),
					r2r("member","belongsTo", 
							c2c("c2c1"), 
							c2c("c2c2")
					),
			
					r2r("belongsTo","member", 
							c2c("c2c1"), 	
							c2c("c2c2")
							),
					r2r("livesAt","livesAt", 
							c2c("c2c2"), 	
							c2c("c2c3")
					)
				)
			);
		
		assertTrue(Checker.isComplete(m, lhsMM, rhsMM));
	}
}
