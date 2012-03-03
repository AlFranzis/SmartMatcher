package com.big.tuwien.SmartMatcher.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public class TSimMetrics2 {
	private Logger logger = Logger.getLogger(TSimMetrics2.class);
	
	protected DOMView domView;
	protected String example;
	protected MetaModelFactory metaModelFactory;
	

	public TSimMetrics2() {
		setExample(ExampleStore.SIMMODEL1_SIMMODEL2);
	}
	
	
	public void setExample(String example) {
		this.example = example;
	}
	
	
	@Before
	public void setUp()throws ParserConfigurationException, ModelParsingException {
		ModelManager modelManager = new ModelManager();
		
		modelManager.setExample(example);
		modelManager.init();
		
		ResourceMetaModelFactory metaModelFactory = new ResourceMetaModelFactory();
		metaModelFactory.setLHSResource(modelManager.getInputMetaModel());
		metaModelFactory.setRHSResource(modelManager.getOutputMetaModel());
		metaModelFactory.build();
		
		this.metaModelFactory = metaModelFactory;
		MetaModelFactoryUtil.setMetaModelFactory(metaModelFactory);
		
		MetaModelFactoryUtil.setMetaModelFactory(metaModelFactory);
		
		this.domView = new DOMView();
		this.domView.setMetaModelFactory(metaModelFactory);
		this.domView.buildView();
	}
	

	
	@Test
	public void testC2CSim1() throws Exception {
		Element lhsClass = metaModelFactory.getLHSMetaModel().getClassByName("Class");
		assertNotNull(lhsClass);
		
		C2CProperties props = new C2CProperties();
		props.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		C2C c2c = props.getOperatorInstance();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.lhsFocusClass, lhsClass);
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		Iterator<C2CConfiguration> configIt = c2c.getConfigurationIterator();
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2cl = new C2CConfiguration();
		cl2cl.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Class"));
		cl2cl.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Clazz"));
		assertEquals(cl2cl, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c2 = new C2CConfiguration();
		cl2c2.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Class"));
		cl2c2.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("ClassSubSub"));
		assertEquals(cl2c2, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c3 = new C2CConfiguration();
		cl2c3.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Class"));
		cl2c3.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("ClazzSub"));
		assertEquals(cl2c3, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c4 = new C2CConfiguration();
		cl2c4.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("Class"));
		cl2c4.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Final"));
		assertEquals(cl2c4, configIt.next());
	}	
	
	
	@Test
	public void testC2CSim2() throws Exception {
		C2CProperties props = new C2CProperties();
		props.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		C2C c2c = props.getOperatorInstance();
		c2c.setDOMView(this.domView);
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration config = new C2CConfiguration();
		config.setRole(Roles.lhsFocusClass, metaModelFactory.getLHSMetaModel().getClassByName("SubClass"));
		b.setConfiguration(config);
		
		c2c.init(b);
		c2c.buildTransitionTree();
		Iterator<C2CConfiguration> configIt = c2c.getConfigurationIterator();
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2cl = new C2CConfiguration();
		cl2cl.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("SubClass"));
		cl2cl.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Clazz"));
		assertEquals(cl2cl, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c2 = new C2CConfiguration();
		cl2c2.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("SubClass"));
		cl2c2.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("ClassSubSub"));
		assertEquals(cl2c2, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c3 = new C2CConfiguration();
		cl2c3.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("SubClass"));
		cl2c3.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("Final"));
		assertEquals(cl2c3, configIt.next());
		
		assertTrue(configIt.hasNext());
		C2CConfiguration cl2c4 = new C2CConfiguration();
		cl2c4.setRole(C2CConfiguration.Roles.lhsFocusClass, 
				metaModelFactory.getLHSMetaModel().getClassByName("SubClass"));
		cl2c4.setRole(C2CConfiguration.Roles.rhsFocusClass, 
				metaModelFactory.getRHSMetaModel().getClassByName("ClazzSub"));
		assertEquals(cl2c4, configIt.next());
	}	
	
	
	@Test
	public void testA2ASim1() throws Exception {
		Element lhsC = metaModelFactory.getLHSMetaModel().getClassByName("SubClass");
		Element rhsC = metaModelFactory.getRHSMetaModel().getClassByName("ClazzSub");
		
		C2CBubble contextC2C = new C2CBubble();
		C2CConfiguration contextConfig = new C2CConfiguration();
		contextConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsC);
		contextConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsC);
		contextC2C.setConfiguration(contextConfig);
		contextC2C.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, contextC2C);
		
		A2AProperties props = new A2AProperties();
		props.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		A2A a2a = props.getOperatorInstance();
		a2a.setDOMView(this.domView);
		
		A2ABubble b = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		b.setConfiguration(config);
		b.setContext(contextC2C);
		
		a2a.init(b);
		a2a.buildTransitionTree();
		Iterator<A2AConfiguration> configIt = a2a.getConfigurationIterator();
		
		assertTrue(configIt.hasNext());
		A2AConfiguration c1 = new A2AConfiguration();
		c1.setRole(A2AConfiguration.Roles.lhsFocusAttribute, 
				metaModelFactory.getLHSMetaModel().getAttributeByQName("SubClass.name"));
		c1.setRole(A2AConfiguration.Roles.rhsFocusAttribute, 
				metaModelFactory.getRHSMetaModel().getAttributeByQName("ClazzSub.name"));
		c1.setContext((C2CConfiguration) contextC2C.getConfiguration());
		assertEquals(c1, configIt.next());
		
		assertTrue(configIt.hasNext());
		A2AConfiguration c2 = new A2AConfiguration();
		c2.setRole(A2AConfiguration.Roles.lhsFocusAttribute, 
				metaModelFactory.getLHSMetaModel().getAttributeByQName("SubClass.attribute_c"));
		c2.setRole(A2AConfiguration.Roles.rhsFocusAttribute, 
				metaModelFactory.getRHSMetaModel().getAttributeByQName("ClazzSub.attribute"));
		c2.setContext((C2CConfiguration) contextC2C.getConfiguration());
		assertEquals(c2, configIt.next());
		
		assertTrue(configIt.hasNext());
		A2AConfiguration c3 = new A2AConfiguration();
		c3.setRole(A2AConfiguration.Roles.lhsFocusAttribute, 
				metaModelFactory.getLHSMetaModel().getAttributeByQName("SubClass.attribute_b"));
		c3.setRole(A2AConfiguration.Roles.rhsFocusAttribute, 
				metaModelFactory.getRHSMetaModel().getAttributeByQName("ClazzSub.attribute"));
		c3.setContext((C2CConfiguration) contextC2C.getConfiguration());
		assertEquals(c3, configIt.next());	
	}	
	
}
