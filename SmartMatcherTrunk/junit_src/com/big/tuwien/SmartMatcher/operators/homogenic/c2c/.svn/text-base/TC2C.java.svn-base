package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TC2C extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TC2C.class);
	private Element lhsFocusClass;
	private Element rhsFocusClass;
	
	
	public TC2C() {
		setExample(ExampleStore.ER_2_WEBML);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		b.setConfiguration(c2cConfig);
		b.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, b);
		
		this.rhsFocusClass = metaModelFactory.getRHSMetaModel().getClassByName("Attribute");
		this.lhsFocusClass = metaModelFactory.getLHSMetaModel().getClassByName("Attribute");
	}
	
	
	
	@Test
	public void rhsFocusClassFixedTest() throws Exception {
		logger.debug("**** rhsFocusClassFixedTest **** ");
		C2C c2c = new C2C();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.rhsFocusClass, this.rhsFocusClass);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		c2c.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusClassFixedTest() throws Exception {
		logger.debug("**** lhsFocusClassFixedTest **** ");
		C2C c2c = new C2C();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.lhsFocusClass, this.lhsFocusClass);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		c2c.printConfigurations();
	}
	
	
	@Test
	public void allFixedTest() throws Exception {
		logger.debug("**** allFixedTest **** ");
		C2C c2c = new C2C();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.lhsFocusClass, this.lhsFocusClass);
		config.setRole(Roles.rhsFocusClass, this.rhsFocusClass);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		c2c.printConfigurations();
	}
	
	
}
