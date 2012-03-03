package com.big.tuwien.SmartMatcher.views.emf;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import sm_mm.C2C;
import sm_mm.Class;
import sm_mm.MappingModel;
import sm_mm.Sm_mmFactory;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewException;

public class TEMFAdapter {
	protected ModelManager modelManager;
	protected BubbleView bubbleView;
	protected ResourceMetaModelFactory mmFactory;
	
	public TEMFAdapter() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() throws BubbleViewException, ModelParsingException {
		modelManager = new ModelManager();
		modelManager.setExample(ExampleStore.PERSON_PERSONFAMILY);
		modelManager.init();
		
		mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(modelManager.getInputMetaModel());
		mmFactory.setRHSResource(modelManager.getOutputMetaModel());
		mmFactory.build();
		
		bubbleView = new BubbleView();
		bubbleView.setMetaModelFactory(mmFactory);
		bubbleView.buildView();
	}
	
	
	@Test
	public void testEMFModelEventPropagation() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		
		// build test EMF model
		// ******************************************
		
		MappingModel mm = factory.createMappingModel();
		
		Class lPerson = factory.createClass();
		lPerson.setName("Person");
		mm.getClasses().add(lPerson);
		
		Class rPerson = factory.createClass();
		rPerson.setName("Person");
		rPerson.setLhs(false);
		mm.getClasses().add(rPerson);
		
		Class rFamily = factory.createClass();
		rFamily.setName("Family");
		rFamily.setLhs(false);
		mm.getClasses().add(rPerson);
		
		
		
		// setup adapter
		//********************************************
		
		EMFAdapter adapter = new EMFAdapter();
		
		EMFModel emfModel = new EventFilteredEMFModel(mm);
		adapter.setEMFModel(emfModel);
		
		BVMediator bvMediator = new EventFilteredBVMediator(bubbleView);
		adapter.setBVMediator(bvMediator);
		
		adapter.setBubbleFactory(new GenericBubbleFactory());
		
		Map<Element, sm_mm.Element> elements = new HashMap<Element,sm_mm.Element>();
		elements.put(mmFactory.getLHSMetaModel().getClassByName("Person"), lPerson);
		elements.put(mmFactory.getRHSMetaModel().getClassByName("Person"), rPerson);
		elements.put(mmFactory.getRHSMetaModel().getClassByName("Family"), rFamily);
		
		adapter.setElementMap(elements);
		
		adapter.adapt();
		
		// ************************************************
		// check asserted preconditions
		assertTrue(bubbleView.getBubbles().isEmpty());
		
		// *******************************************
		// change EMF model and check event notification
		
		C2C c2c1 = factory.createC2C();
		c2c1.setLhs(lPerson);
		c2c1.setRhs(rFamily);
		// add operator
		mm.getOperators().add(c2c1);
		
		assertEquals(1, bubbleView.getBubbles(Bubble.STATE.eval2TRUE).size());
		
		// modify op
		c2c1.setRhs(rPerson);
		
		assertEquals(1, bubbleView.getBubbles(Bubble.STATE.eval2FALSE).size());
		assertEquals(1, bubbleView.getBubbles(Bubble.STATE.eval2TRUE).size());
		
		// remove op
		mm.getOperators().remove(c2c1);
		
		assertEquals(2, bubbleView.getBubbles(Bubble.STATE.eval2FALSE).size());
		assertEquals(0, bubbleView.getBubbles(Bubble.STATE.eval2TRUE).size());
	}
	
	
	
	@Test
	public void testBubbleViewEventPropagation() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;

		// *****************************************
		// construct mapping model
		EMFModelBuilder emfBuilder = new EMFModelBuilder(factory);
		MappingModel mm = emfBuilder.build(mmFactory.getLHSMetaModel(), mmFactory.getRHSMetaModel());
		
		// setup adapter
		//********************************************
		EMFAdapter adapter = new EMFAdapter();
		
		EMFModel emfModel = new EventFilteredEMFModel(mm);
		adapter.setEMFModel(emfModel);
		
		BVMediator bvMediator = new EventFilteredBVMediator(bubbleView);
		adapter.setBVMediator(bvMediator);
		
		adapter.setEMFOpFactory(new GenericEMFFactory());
		
		adapter.setElementMap(emfBuilder.getElementMap());
		
		adapter.adapt();
		
		// ************************************************
		// check asserted preconditions
		assertTrue(mm.getOperators().isEmpty());
		
		// ************************************************
		// modify bubble view 
		
		@SuppressWarnings("unchecked")
		Bubble<com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C> bubble = 
			(Bubble<com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C>)
						bubbleView.getBubbleFactory().createBubbleInstance(
								com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C.class);
		
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setLHSFocusElement(mmFactory.getLHSMetaModel().getClassByName("Person"));
		c2cConfig.setRHSFocusElement(mmFactory.getRHSMetaModel().getClassByName("Family"));
		
		bubble.setConfiguration(c2cConfig);
		
		// add bubble
		bubbleView.addCurrentLevelBubble(bubble);
		
		
		assertEquals(1, mm.getOperators().size());
		
		// modify bubble
		bubble.setState(Bubble.STATE.eval2FALSE);
		
		assertEquals(0, mm.getOperators().size());
		
	}
}
