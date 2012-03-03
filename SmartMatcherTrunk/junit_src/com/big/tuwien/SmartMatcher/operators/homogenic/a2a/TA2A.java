package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractOperatorTests;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

public class TA2A extends TAbstractOperatorTests {
	private static Logger logger = Logger.getLogger(TA2A.class);
	private Element lhsFocusAttribute;
	private Element rhsFocusAttribute;
	private C2CBubble contextC2C;
	
	public TA2A() {
		setExample(ExampleStore.ER_2_WEBML);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		this.contextC2C = new C2CBubble();
		C2CConfiguration contextConfig = new C2CConfiguration();
		contextConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		contextConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		this.contextC2C.setConfiguration(contextConfig);
		this.contextC2C.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, this.contextC2C);
		
		this.lhsFocusAttribute = QueryUtil.getClassAttributeByName(lhsEntity, "name");
		this.rhsFocusAttribute = QueryUtil.getClassAttributeByName(rhsEntity, "name");
	}
	
	
	
	@Test
	public void rhsFocusAttributeFixedTest() throws Exception {
		logger.debug("**** rhsFocusAttributeFixedTest **** ");
		A2A a2a = new A2A();
		a2a.setDOMView(this.domView);
		
		A2ABubble b = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		config.setRole(Roles.rhsFocusAttribute, this.rhsFocusAttribute);
		b.setConfiguration(config);
		
		a2a.init(b);
		a2a.buildTransitionTree();
		a2a.printConfigurations();
	}
	
	
	@Test
	public void lhsFocusAttributeFixedTest() throws Exception {
		logger.debug("**** lhsFocusAttributeFixedTest **** ");
		A2A a2a = new A2A();
		a2a.setDOMView(this.domView);
		
		A2ABubble b = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		config.setRole(Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		b.setConfiguration(config);
		
		a2a.init(b);
		a2a.buildTransitionTree();
		a2a.printConfigurations();
	}
	
	
	@Test
	public void allFixedTest() throws Exception {
		logger.debug("**** allFixedTest **** ");
		A2A a2a = new A2A();
		a2a.setDOMView(this.domView);
		
		A2ABubble b = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		config.setRole(Roles.rhsFocusAttribute, this.rhsFocusAttribute);
		config.setRole(Roles.lhsFocusAttribute, this.lhsFocusAttribute);
		b.setConfiguration(config);
		
		a2a.init(b);
		a2a.buildTransitionTree();
		a2a.printConfigurations();
	}
	
	
	@Test
	public void ContextFixedTest() throws Exception {
		logger.debug("**** contextFixedTest **** ");
		A2A a2a = new A2A();
		a2a.setDOMView(this.domView);
		
		A2ABubble b = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		b.setConfiguration(config);
		b.setContext(this.contextC2C);
		
		a2a.init(b);
		a2a.buildTransitionTree();
		a2a.printConfigurations();
	}
	
	
	protected boolean assertConfigurations(A2AConfigurationIterator it, 
			int size, Collection<A2AConfiguration> configs) {
		int ct = 0;

		while(it.hasNext()) {
			A2AConfiguration config = it.next();
			ct++;
			if( !configs.remove(config)) return false;
		}
		
		return configs.size() == 0 && ct == size;
	}
	
}
