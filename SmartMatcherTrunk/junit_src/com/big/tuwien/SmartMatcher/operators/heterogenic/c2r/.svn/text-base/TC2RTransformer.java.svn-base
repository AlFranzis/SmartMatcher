package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import static org.junit.Assert.assertTrue;

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

public class TC2RTransformer extends TAbstractTransformationTest {
	private static Logger logger = Logger.getLogger(TC2RTransformer.class);
	private C2CBubble c2cContext1;
	private C2CBubble c2cContext2;
	
	
	public TC2RTransformer() {
		setExample(ExampleStore.ER_2_WEBML_BOOKS);
	}
	
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble c2cContext1 = new C2CBubble();
		C2CConfiguration c2cConfig1 = new C2CConfiguration();
		c2cConfig1.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig1.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		c2cContext1.setConfiguration(c2cConfig1);
		c2cContext1.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2cContext1);
		this.c2cContext1 = c2cContext1;
		
		Element lhsRelationship = metaModelFactory.getLHSMetaModel().getClassByName("Relationship");
		Element rhsRelationship = metaModelFactory.getRHSMetaModel().getClassByName("Relationship");
		
		C2CBubble c2cContext2 = new C2CBubble();
		C2CConfiguration c2cConfig2 = new C2CConfiguration();
		c2cConfig2.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsRelationship);
		c2cConfig2.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsRelationship);
		c2cContext2.setConfiguration(c2cConfig2);
		c2cContext2.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2cContext2);
		this.c2cContext2 = c2cContext2;
	}
	
	
	
	@Test
	public void simpleTransformation() throws Exception {
		TransformationEngine engine = new TransformationEngine(modelManager);
		C2CTransformer c2cTransformer = new C2CTransformer(engine);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), this.c2cContext1.getConfiguration());
		c2cTransformer.transform(modelManager.getInputModel().getURI(), this.c2cContext2.getConfiguration());
		
		this.modelManager.saveOutputModel();
		engine.clearConfigurations();
		
		boolean c2cContext1Correct = this.fitness.evaluate(this.c2cContext1);
		assertTrue(c2cContext1Correct);
		
		boolean c2cContext2Correct = this.fitness.evaluate(this.c2cContext2);
		assertTrue(c2cContext2Correct);
		
		Element lhsFocusClass = metaModelFactory.getLHSMetaModel().getClassByName("RelationshipEnd");
		Element rhsFocusRef = metaModelFactory.getRHSMetaModel().getReferenceByQName("Entity_relationship_Relationship");
		Element lhsContextRef1 = metaModelFactory.getLHSMetaModel().getReferenceByQName("RelationshipEnd_owningRelationship_Relationship");
		Element lhsContextRef2 = metaModelFactory.getLHSMetaModel().getReferenceByQName("RelationshipEnd_entity_Entity");
		
		C2RBubble c2rBubble = new C2RBubble();
		C2RConfiguration c2rConfig = new C2RConfiguration();
		c2rConfig.setRole(C2RConfiguration.Roles.lhsFocusClass, lhsFocusClass);
		c2rConfig.setRole(C2RConfiguration.Roles.rhsFocusReference, rhsFocusRef);
		c2rConfig.setRole(C2RConfiguration.Roles.lhsContextReference1, lhsContextRef1);
		c2rConfig.setRole(C2RConfiguration.Roles.lhsContextReference2, lhsContextRef2);
		c2rBubble.setConfiguration(c2rConfig);
		c2rBubble.setContext1(this.c2cContext1);
		c2rBubble.setContext2(this.c2cContext2);
		c2rBubble.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, c2rBubble);
		
		C2RTransformer c2rTransformer = new C2RTransformer(engine);
		c2rTransformer.transform(modelManager.getInputModel().getURI(), c2rBubble.getConfiguration());
		
		this.modelManager.saveOutputModel();
		engine.clearConfigurations();
		
		boolean c2rCorrect = this.fitness.evaluate(c2rBubble);
		logger.debug(c2rCorrect);
		
	}
}
