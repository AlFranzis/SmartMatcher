package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TA2C extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TA2C.class);
	private C2CBubble context;
	private Element rhsFocusClass;
	private Element rhsContextAttribute;
	private Element rhsContextReference;
	private Element lhsFocusAttribute;
	
	
	public TA2C() {
		setExample(ExampleStore.ER_2_WEBML_BOOKS);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble c2c = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		c2c.setConfiguration(c2cConfig);
		c2c.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2c);
		
		this.context = c2c;
		
		this.rhsFocusClass = metaModelFactory.getRHSMetaModel().getClassByName("Relationship");
		this.rhsContextAttribute = QueryUtil.getClassAttributeByName(this.rhsFocusClass, "maxCard");
		this.rhsContextReference = QueryUtil.getClassReferenceByName(this.rhsFocusClass, "to");
		
		Element lhsContextClass = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		this.lhsFocusAttribute = QueryUtil.getClassAttributeByName(lhsContextClass, "name");
	}
	
	
	@Test
	public void rhsFocusClassFixedTest() throws Exception {
		logger.debug("**** rhsFocusClassFixedTest **** ");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(Roles.rhsFocusClass, this.rhsFocusClass);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusAttributeFixedTest() throws Exception {
		logger.debug("**** lhsFocusAttributeFixedTest **** ");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(A2CConfiguration.Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusAttributeRhsFocusClassFixedTest() throws Exception {
		logger.info("**** lhsFocusAttributeRhsFocusClassFixedTest ****");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(A2CConfiguration.Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		config.setRole(A2CConfiguration.Roles.rhsFocusClass, this.rhsFocusClass);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusAttributeRhsFocusClassRhsContextAttributeFixedTest() throws Exception {
		logger.info("**** lhsFocusAttributeRhsFocusClassRhsContextAttributeFixedTest ****");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(A2CConfiguration.Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		config.setRole(A2CConfiguration.Roles.rhsFocusClass, this.rhsFocusClass);
		config.setRole(A2CConfiguration.Roles.rhsContextAttribute, this.rhsContextAttribute);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusAttributeRHSContextAttributeFixed() throws Exception {
		logger.info("**** lhsFocusAttributeRHSContextAttributeFixed ****");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(A2CConfiguration.Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		config.setRole(A2CConfiguration.Roles.rhsContextAttribute, this.rhsContextAttribute);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void allFixedTest() throws Exception {
		logger.info("**** allFixedTest ****");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		config.setRole(A2CConfiguration.Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		config.setRole(A2CConfiguration.Roles.rhsFocusClass, this.rhsFocusClass);
		config.setRole(A2CConfiguration.Roles.rhsContextAttribute, this.rhsContextAttribute);
		config.setRole(A2CConfiguration.Roles.rhsContextReference, this.rhsContextReference);
		b.setConfiguration(config);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
	
	@Test
	public void contextFixedTest() throws Exception {
		logger.info("**** contextFixedTest ****");
		A2C a2c = new A2C();
		a2c.setDOMView(this.domView);
		
		A2CBubble b = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		b.setConfiguration(config);
		b.setContext(this.context);
		
		a2c.init(b);
		a2c.buildTransitionTree();
		a2c.printConfigurations();
	}
	
}
