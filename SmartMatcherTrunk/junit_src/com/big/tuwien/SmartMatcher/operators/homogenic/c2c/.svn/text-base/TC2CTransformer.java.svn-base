package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractTransformationTest;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.transformation.TransformationEngine;

public class TC2CTransformer extends TAbstractTransformationTest {
	private Element lhsFocusClass;
	private Element rhsFocusClass;
	
	
	public TC2CTransformer() {
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
	public void simpleTransformation() throws Exception {
		C2C c2c = new C2C();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.rhsFocusClass, this.rhsFocusClass);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		Iterator<C2CConfiguration> configIt = c2c.getConfigurationIterator();
		
		TransformationEngine engine = new TransformationEngine(modelManager);
		C2CTransformer c2cTransformer = new C2CTransformer(engine);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), configIt.next());
		
	}
}
