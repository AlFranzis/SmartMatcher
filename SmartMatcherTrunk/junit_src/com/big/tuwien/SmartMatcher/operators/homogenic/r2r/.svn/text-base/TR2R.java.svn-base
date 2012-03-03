package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.DIRECTION;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TR2R extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TR2R.class);
	private Element lhsFocusReference;
	private Element rhsFocusReference;
	private C2CBubble contextC2C1;
	private C2CBubble contextC2C2;
	
	public TR2R() {
		setExample(ExampleStore.ER_2_WEBML);
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		this.contextC2C1 = new C2CBubble();
		C2CConfiguration contextConfig1 = new C2CConfiguration();
		contextConfig1.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		contextConfig1.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		this.contextC2C1.setConfiguration(contextConfig1);
		this.contextC2C1.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, this.contextC2C1);
		
		Element lhsAttribute = metaModelFactory.getLHSMetaModel().getClassByName("Attribute");
		Element rhsAttribute = metaModelFactory.getRHSMetaModel().getClassByName("Attribute");
		
		this.contextC2C2 = new C2CBubble();
		C2CConfiguration contextConfig2 = new C2CConfiguration();
		contextConfig2.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsAttribute);
		contextConfig2.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsAttribute);
		this.contextC2C2.setConfiguration(contextConfig2);
		this.contextC2C1.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, this.contextC2C2);
		
		this.lhsFocusReference = QueryUtil.getClassReferenceByName(lhsEntity, "entityAttribute");
		this.rhsFocusReference = QueryUtil.getClassReferenceByName(rhsEntity, "attribute");
	}
	
	
	
	@Test
	public void rhsFocusReferenceFixedTest() throws Exception {
		logger.debug("**** rhsFocusReferenceFixedTest **** ");
		R2R r2r = new R2R();
		r2r.setDOMView(this.domView);
		
		R2RBubble b = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		config.setRole(Roles.rhsFocusReference, this.rhsFocusReference);
		b.setConfiguration(config);
		
		r2r.init(b);
		r2r.buildTransitionTree();
		r2r.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusReferenceFixedTest() throws Exception {
		logger.debug("**** lhsFocusReferenceFixedTest **** ");
		R2R r2r = new R2R();
		r2r.setDOMView(this.domView);
		
		R2RBubble b = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		config.setRole(Roles.lhsFocusReference, this.lhsFocusReference);
		b.setConfiguration(config);
		
		r2r.init(b);
		r2r.buildTransitionTree();
		r2r.printConfigurations();
	}
	
	
	@Test
	public void bothContextsFixedUndirectedTest() throws Exception {
		logger.debug("**** bothContextsFixedUndirectedTest **** ");
		R2R r2r = new R2R();
		r2r.setDOMView(this.domView);
		
		R2RBubble b = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		b.setConfiguration(config);
		b.setContext1(contextC2C1);
		b.setContext2(contextC2C2);
		
		r2r.init(b);
		r2r.buildTransitionTree();
		r2r.printConfigurations();
	}
	
	
	@Test
	public void bothContextsFixedDirectedTest() throws Exception {
		logger.debug("**** bothContextsFixedDirectedTest **** ");
		R2R r2r = new R2R();
		r2r.setDOMView(this.domView);
		
		R2RBubble b = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		config.setDirectionConstraint(DIRECTION.directed);
		b.setConfiguration(config);
		b.setContext1(contextC2C1);
		b.setContext2(contextC2C2);
		
		r2r.init(b);
		r2r.buildTransitionTree();
		r2r.printConfigurations();
	}
	
	
	
}
