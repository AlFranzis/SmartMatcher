package com.big.tuwien.SmartMatcher.operators;

import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ATransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RTransformer;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationResult;

public class TOperatorTest1 {
	private Logger logger = Logger.getLogger(TOperatorTest1.class);
	
	protected MetaModelFactory metaModelFactory;
	protected ModelManager modelManager;
	protected NewSimpleFitnessFunction fitness;
	protected DOMView domView;
	protected String example;
	
	
	@Before
	public void setUp() throws ParserConfigurationException, ModelParsingException {
		modelManager = new ModelManager();
		
		modelManager.setExample(ExampleStore.UML1_2_UML2_BIG_V4);
		modelManager.init();
		
		ResourceMetaModelFactory metaModelFactory = new ResourceMetaModelFactory();
		metaModelFactory.setLHSResource(modelManager.getInputMetaModel());
		metaModelFactory.setRHSResource(modelManager.getOutputMetaModel());
		metaModelFactory.build();
		
		this.metaModelFactory = metaModelFactory;
		MetaModelFactoryUtil.setMetaModelFactory(metaModelFactory);
		
		ModelLinker.linkModels(modelManager, metaModelFactory);
		
		MetaModelFactoryUtil.setMetaModelFactory(metaModelFactory);
		
		this.fitness = new NewSimpleFitnessFunction();
		this.fitness.setModelManager(this.modelManager);
		
		this.domView = new DOMView();
		this.domView.setMetaModelFactory(metaModelFactory);
		this.domView.buildView();
	}
	
	
	@Test
	public void test1() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Class");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Class");
		
		C2CBubble class2class = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		class2class.setConfiguration(c2cConfig);
		class2class.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, class2class);
		
		Element lhsGeneralization = metaModelFactory.getLHSMetaModel().getClassByName("Generalization");
		Element rhsGeneralization = metaModelFactory.getRHSMetaModel().getClassByName("Generalization");
		
		C2CBubble gen2gen = new C2CBubble();
		C2CConfiguration c2cConfig2 = new C2CConfiguration();
		c2cConfig2.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsGeneralization);
		c2cConfig2.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsGeneralization);
		gen2gen.setConfiguration(c2cConfig2);
		gen2gen.setState(Bubble.STATE.eval2TRUE);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, gen2gen);
		
		Element lhsName = metaModelFactory.getLHSMetaModel().getAttributeByQName("Class.name");
		Element rhsName = metaModelFactory.getRHSMetaModel().getAttributeByQName("Class.name");
		A2AConfiguration a2aConfig = new A2AConfiguration();
		a2aConfig.setRole(A2AConfiguration.Roles.lhsFocusAttribute, lhsName);
		a2aConfig.setRole(A2AConfiguration.Roles.rhsFocusAttribute, rhsName);
		
		A2ABubble name2name = new A2ABubble();
		name2name.setConfiguration(a2aConfig);
		name2name.setContext(class2class);
		name2name.setState(Bubble.STATE.applied);
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, name2name);
		
		
		TransformationEngine tEngine = new TransformationEngine(modelManager);
		tEngine.addTransformer("C2C", new C2CTransformer(tEngine));
		tEngine.addTransformer("A2A", new A2ATransformer(tEngine));
		tEngine.addTransformer("R2R", new R2RTransformer(tEngine));
		
		
		List<Bubble<? extends Operator>> transformees = new Vector<Bubble<? extends Operator>>();
		transformees.add(class2class);
		transformees.add(name2name);
		transformees.add(gen2gen);
		
		List<TransformationResult> tresults;
		
		try {
			do {
				this.modelManager.resetOutputModel();
				logger.info("Transform bubbles: " + transformees);	
				for(Bubble<? extends Operator> _b : transformees) {
					tEngine.addConfiguration(_b.getConfiguration());
				}
				tresults = tEngine.transform();
				this.modelManager.saveOutputModel();
				tEngine.clearConfigurations();
			} while(this.modelManager.next());
		} catch (TransformationException e) {
			logger.error("Error during Transformation", e);
		}
	}
	
}
