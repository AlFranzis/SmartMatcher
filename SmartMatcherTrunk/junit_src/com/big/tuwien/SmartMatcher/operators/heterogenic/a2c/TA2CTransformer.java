package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractTransformationTest;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.transformation.TransformationEngine;

public class TA2CTransformer extends TAbstractTransformationTest {
	private static Logger logger = Logger.getLogger(TA2CTransformer.class);
	private C2CBubble c2cBubble;
	
	
	public TA2CTransformer() {
		setExample(ExampleStore.PERSON_PERSONFAMILY);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Person");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Person");
		
		C2CBubble c2cBubble = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		c2cBubble.setConfiguration(c2cConfig);
		c2cBubble.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2cBubble);
		this.c2cBubble = c2cBubble;
	}
	
	
	@Test
	public void simpleTransformation() throws Exception {
		TransformationEngine engine = new TransformationEngine(modelManager);
		C2CTransformer c2cTransformer = new C2CTransformer(engine);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), this.c2cBubble.getConfiguration());
		
		this.modelManager.saveOutputModel();
		engine.clearConfigurations();
		
		boolean c2cCorrect = this.fitness.evaluate(this.c2cBubble);
		logger.debug(c2cCorrect);
		
		Element lhsFocusAttribute = metaModelFactory.getLHSMetaModel().getAttributeByQName("Person.lastName");
		Element rhsFocusClass = metaModelFactory.getRHSMetaModel().getClassByName("Family");
		Element rhsContextAttribute = metaModelFactory.getRHSMetaModel().getAttributeByQName("Family.lastName");
		Element rhsContextRef = metaModelFactory.getRHSMetaModel().getReferenceByName("memberOf");
		
		A2CBubble a2cBubble = new A2CBubble();
		A2CConfiguration a2cConfig = new A2CConfiguration();
		a2cConfig.setRole(A2CConfiguration.Roles.lhsFocusAttribute, lhsFocusAttribute);
		a2cConfig.setRole(A2CConfiguration.Roles.rhsFocusClass, rhsFocusClass);
		a2cConfig.setRole(A2CConfiguration.Roles.rhsContextAttribute, rhsContextAttribute);
		a2cConfig.setRole(A2CConfiguration.Roles.rhsContextReference, rhsContextRef);
		a2cBubble.setConfiguration(a2cConfig);
		a2cBubble.setContext(this.c2cBubble);
		a2cBubble.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, a2cBubble);
		
		A2CTransformer a2cTransformer = new A2CTransformer(engine);
		a2cTransformer.transform(modelManager.getInputModel().getURI(), a2cBubble.getConfiguration());
		
		this.modelManager.saveOutputModel();
		engine.clearConfigurations();
		
		boolean a2cCorrect = this.fitness.evaluate(a2cBubble);
		logger.debug(a2cCorrect);
		
	}
}
