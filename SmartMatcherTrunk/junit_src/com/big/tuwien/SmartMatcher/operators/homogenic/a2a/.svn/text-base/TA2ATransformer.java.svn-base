package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractTransformationTest;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.transformation.TransformationEngine;

public class TA2ATransformer extends TAbstractTransformationTest {
	private Element lhsFocusClass;
	private Element rhsFocusClass;
	private Element lhsFocusAttribute;
	private Element rhsFocusAttribute;
	
	
	@Before
	public void setUp2() {		
		this.lhsFocusClass = metaModelFactory.getLHSMetaModel().getClassByName("Attribute");
		this.rhsFocusClass = metaModelFactory.getRHSMetaModel().getClassByName("Attribute");
		this.lhsFocusAttribute = QueryUtil.getClassAttributeByName(this.lhsFocusClass, "name");
		this.rhsFocusAttribute = QueryUtil.getClassAttributeByName(this.rhsFocusClass, "name");
		
	}
	
	
	@Test
	public void simpleTransformation() throws Exception {
		TransformationEngine engine = new TransformationEngine(modelManager);
		C2CTransformer c2cTransformer = new C2CTransformer(engine);
		A2ATransformer a2aTransformer = new A2ATransformer(engine);
		
		
		C2CConfiguration c2cContextConfig = new C2CConfiguration();
		c2cContextConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsFocusClass);
		c2cContextConfig.setRole(C2CConfiguration.Roles.rhsFocusClass,rhsFocusClass);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), c2cContextConfig);
		
		A2AConfiguration a2aConfig = new A2AConfiguration();
		a2aConfig.setRole(A2AConfiguration.Roles.lhsFocusAttribute, lhsFocusAttribute);
		a2aConfig.setRole(A2AConfiguration.Roles.rhsFocusAttribute,rhsFocusAttribute);
		a2aConfig.setContext(c2cContextConfig);
		
		a2aTransformer.transform(modelManager.getInputModel().getURI(), a2aConfig);	
	}
}
