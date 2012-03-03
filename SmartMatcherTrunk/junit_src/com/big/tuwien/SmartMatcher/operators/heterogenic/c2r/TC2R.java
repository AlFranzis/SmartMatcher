package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TC2R extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TC2R.class);
	private Element lhsFocusClass;
	private Element lhsContextReference1;
	private Element lhsContextReference2;
	private Element rhsFocusReference;
	private C2CBubble context1;
	private C2CBubble context2;
	
	
	public TC2R() {
		setExample(ExampleStore.ER_2_WEBML_BOOKS);
	}
	
	
	@Before
	public void setUp2() {
		this.lhsFocusClass = metaModelFactory.getLHSMetaModel().getClassByName("RelationshipEnd");
		
		Element lhsContextClass1 = metaModelFactory.getLHSMetaModel().getClassByName("Relationship");
		this.lhsContextReference1 = QueryUtil.getClassReferenceByName(lhsContextClass1, "role");
		
		this.lhsContextReference2 = QueryUtil.getClassReferenceByName(this.lhsFocusClass, "entity");
		
		Element rhsContextClass = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		this.rhsFocusReference = QueryUtil.getClassReferenceByName(rhsContextClass, "relationship");
		
		
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble b1 = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		b1.setConfiguration(c2cConfig);
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
	}
	
	
	@Test
	public void lhsFocusClassFixedTest() throws Exception {
		logger.debug("**** lhsFocusClassFixedTest **** ");
		C2R c2r = new C2R();
		c2r.setDOMView(this.domView);
		
		C2RBubble b = new C2RBubble();
		C2RConfiguration config = new C2RConfiguration();
		config.setRole(Roles.lhsFocusClass, this.lhsFocusClass);
		b.setConfiguration(config);
		
		c2r.init(b);
		c2r.buildTransitionTree();
		c2r.printConfigurations();
	}
	
	
	@Test
	public void allFixedTest() throws Exception {
		logger.debug("**** allElementsFixedTest **** ");
		C2R c2r = new C2R();
		c2r.setDOMView(this.domView);
		
		C2RBubble b = new C2RBubble();
		C2RConfiguration config = new C2RConfiguration();
		config.setRole(Roles.lhsFocusClass, this.lhsFocusClass);
		config.setRole(Roles.lhsContextReference1, this.lhsContextReference1);
		config.setRole(Roles.lhsContextReference2, this.lhsContextReference2);
		config.setRole(Roles.rhsFocusReference, this.rhsFocusReference);
		b.setConfiguration(config);
		
		c2r.init(b);
		c2r.buildTransitionTree();
		c2r.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusClassRhsFocusReferenceFixedTest() throws Exception {
		logger.debug("**** lhsFocusClassRhsFocusReferenceFixedTest **** ");
		C2R c2r = new C2R();
		c2r.setDOMView(this.domView);
		
		C2RBubble b = new C2RBubble();
		C2RConfiguration config = new C2RConfiguration();
		config.setRole(Roles.lhsFocusClass, this.lhsFocusClass);
		config.setRole(Roles.rhsFocusReference, this.rhsFocusReference);
		b.setConfiguration(config);
		
		c2r.init(b);
		c2r.buildTransitionTree();
		c2r.printConfigurations();
	}
	
	
	@Test
	public void bothContextsFixedTest() throws Exception {
		logger.debug("**** bothContextsFixedTest **** ");
		C2R c2r = new C2R();
		c2r.setDOMView(this.domView);
		
		C2RBubble b = new C2RBubble();
		C2RConfiguration config = new C2RConfiguration();
		b.setConfiguration(config);
		b.setContext1(context1);
		b.setContext2(context2);
		
		c2r.init(b);
		c2r.buildTransitionTree();
		c2r.printConfigurations();
	}
	
}
