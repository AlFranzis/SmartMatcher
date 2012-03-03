package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TA2R extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TA2R.class);
	private Element rhsFocusReference;
	private Element lhsFocusAttribute1;
	private Element lhsFocusAttribute2;
	private C2CBubble context1;
	private C2CBubble context2;
	
	public TA2R() {
		setExample(ExampleStore.ER_2_WEBML_BOOKS);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble b1 = new C2CBubble();
		C2CConfiguration c2cConfig1 = new C2CConfiguration();
		c2cConfig1.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig1.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		b1.setConfiguration(c2cConfig1);
		b1.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, b1);
		this.context1 = b1;
		
		
		Element lhsRelationship = metaModelFactory.getLHSMetaModel().getClassByName("Relationship");
		Element rhsRelationship = metaModelFactory.getRHSMetaModel().getClassByName("Relationship");
		
		C2CBubble b2 = new C2CBubble();
		C2CConfiguration c2cConfig2 = new C2CConfiguration();
		c2cConfig2.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsRelationship);
		c2cConfig2.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsRelationship);
		b2.setConfiguration(c2cConfig2);
		b2.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, b2);
		this.context2 = b2;
		
		this.lhsFocusAttribute1 = QueryUtil.getClassAttributeByName(lhsEntity, "name");
		this.lhsFocusAttribute2 = QueryUtil.getClassAttributeByName(lhsRelationship, "name");
		
		this.rhsFocusReference = QueryUtil.getClassReferenceByName(rhsEntity, "relationship");	
	}
	
	
	@Test
	public void lhsFocusAttribute1FixedTest() throws Exception {
		logger.debug("**** lhsFocusAttribute1FixedTest **** ");
		A2R a2r = new A2R();
		a2r.setDOMView(this.domView);
		
		A2RBubble b = new A2RBubble();
		A2RConfiguration config = new A2RConfiguration();
		config.setRole(Roles.lhsFocusAttribute1, this.lhsFocusAttribute1);
		b.setConfiguration(config);
		
		a2r.init(b);
		a2r.buildTransitionTree();
		a2r.printConfigurations();
	}
	
	
	@Test
	public void bothContextsFixedTest() throws Exception {
		logger.debug("**** bothContextsFixedTest **** ");
		A2R a2r = new A2R();
		a2r.setDOMView(this.domView);
		
		A2RBubble b = new A2RBubble();
		A2RConfiguration config = new A2RConfiguration();
		b.setConfiguration(config);
		b.setContext1(context1);
		b.setContext2(context2);
		
		a2r.init(b);
		a2r.buildTransitionTree();
		a2r.printConfigurations();
	}
	
	
	
	
}
