package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.TAbstractTransformationTest;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.transformation.TransformationEngine;

public class TR2RTransformer extends TAbstractTransformationTest {
	private Element lhsFocusClass1;
	private Element rhsFocusClass1;
	private Element lhsFocusClass2;
	private Element rhsFocusClass2;
	private Element lhsFocusReference;
	private Element rhsFocusReference;
	
	
	@Before
	public void setUp2() {		
		this.lhsFocusClass1 = metaModelFactory.getLHSMetaModel().getClassByName("Attribute");
		this.rhsFocusClass1 = metaModelFactory.getRHSMetaModel().getClassByName("Attribute");
		this.lhsFocusClass2 = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		this.rhsFocusClass2 = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		this.lhsFocusReference = QueryUtil.getClassReferenceByName(this.lhsFocusClass2, "entityAttribute");
		this.rhsFocusReference = QueryUtil.getClassReferenceByName(this.rhsFocusClass2, "attribute");
		
	}
	
	
	@Test
	public void simpleTransformation() throws Exception {
		TransformationEngine engine = new TransformationEngine(modelManager);
		C2CTransformer c2cTransformer = new C2CTransformer(engine);
		R2RTransformer a2aTransformer = new R2RTransformer(engine);
		
		
		C2CConfiguration c2cContextConfig1 = new C2CConfiguration();
		c2cContextConfig1.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsFocusClass1);
		c2cContextConfig1.setRole(C2CConfiguration.Roles.rhsFocusClass,rhsFocusClass1);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), c2cContextConfig1);
		
		C2CConfiguration c2cContextConfig2 = new C2CConfiguration();
		c2cContextConfig2.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsFocusClass2);
		c2cContextConfig2.setRole(C2CConfiguration.Roles.rhsFocusClass,rhsFocusClass2);
		
		c2cTransformer.transform(modelManager.getInputModel().getURI(), c2cContextConfig2);
		
		R2RConfiguration r2rConfig = new R2RConfiguration();
		r2rConfig.setRole(R2RConfiguration.Roles.lhsFocusReference, lhsFocusReference);
		r2rConfig.setRole(R2RConfiguration.Roles.rhsFocusReference,rhsFocusReference);
		r2rConfig.setContext1(c2cContextConfig1);
		r2rConfig.setContext2(c2cContextConfig2);
		
		a2aTransformer.transform(modelManager.getInputModel().getURI(), r2rConfig);	
	}
}
