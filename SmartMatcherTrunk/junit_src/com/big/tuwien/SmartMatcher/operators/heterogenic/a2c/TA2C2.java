package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TA2C2 extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TA2C2.class);
	private C2CBubble context;
	private Element rhsFocusClass;
	private Element rhsContextAttribute;
	private Element rhsContextReference;
	private Element lhsFocusAttribute;
	
	public TA2C2() {
		setExample(ExampleStore.UML14_2_UML20_Light);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsClass = metaModelFactory.getLHSMetaModel().getClassByName("Attribute");
		Element rhsClass = metaModelFactory.getRHSMetaModel().getClassByName("Property");
		
		C2CBubble c2c = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsClass);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsClass);
		c2c.setConfiguration(c2cConfig);
		c2c.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2c);
		
		this.context = c2c;
		
		this.rhsFocusClass = metaModelFactory.getRHSMetaModel().getClassByName("Class");
		this.rhsContextAttribute = metaModelFactory.getRHSMetaModel().getAttributeByQName("Class.visibility");
		this.rhsContextReference = metaModelFactory.getRHSMetaModel().getReferenceByQName("Property_Class_Class");
		this.lhsFocusAttribute = metaModelFactory.getRHSMetaModel().getAttributeByQName("Attribute.visibility");
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
		Iterator<A2CConfiguration> configIt = a2c.getConfigurationIterator();
		List<A2CConfiguration> configs = new Vector<A2CConfiguration>();
		while(configIt.hasNext()) {
			A2CConfiguration c = configIt.next();
			configs.add(c);	
		}
		// LHS class 'Attribute' has 6 attributes
		// LHS class 'Property' has 5 references to the only other RHS class 'Class'
		// RHS class 'Class' is the only possible context class
		// RHS class 'Class' has 3 attributes
		// 6 * 5 * 1 * 3 = 90 configurations
		Assert.assertEquals("Unexpected amount of configurations", 90, configs.size());
	}
	
}
