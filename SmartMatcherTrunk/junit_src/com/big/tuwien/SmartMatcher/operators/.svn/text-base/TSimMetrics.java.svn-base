package com.big.tuwien.SmartMatcher.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;

public class TSimMetrics extends TAbstractOperatorTests {
	private Logger logger = Logger.getLogger(TSimMetrics.class);
	

	public TSimMetrics() {
		setExample(ExampleStore.ER_2_WEBML);
	}
	
	
	@Test
	public void test1() {
		Levenshtein ls = new Levenshtein();
		
		C2CConfiguration att2att = new C2CConfiguration();
		att2att.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Attribute"));
		att2att.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Attribute"));
		float sim = ls.getSimilarity(att2att);
		assertEquals("Unexpected class name similarity", 1, sim);
		
		
		C2CConfiguration re2r = new C2CConfiguration();
		re2r.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("RelationshipEnd"));
		re2r.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Relationship"));
		float sim2 = ls.getSimilarity(re2r);
		assertEquals("Unexpected class name similarity", 0.8, sim2);
	}
	
	
	@Test
	public void test2() throws Exception {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		assertNotNull(lhsEntity);
		
		C2C c2c = new C2C();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.lhsFocusClass, lhsEntity);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		Iterator<C2CConfiguration> configIt = c2c.getConfigurationIterator();
		PriorityIterator p = new PriorityIterator();
		p.addMeasure(new Levenshtein());
		Iterator<C2CConfiguration> pIt = p.getIterator(configIt);
		
		assertTrue(pIt.hasNext());
		
		C2CConfiguration e2e = new C2CConfiguration();
		e2e.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Entity"));
		e2e.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Entity"));
		assertEquals(e2e, pIt.next());
	}	
	
}
