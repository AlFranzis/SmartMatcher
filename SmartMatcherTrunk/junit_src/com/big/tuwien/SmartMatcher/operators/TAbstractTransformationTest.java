package com.big.tuwien.SmartMatcher.operators;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;

import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public abstract class TAbstractTransformationTest {
	protected MetaModelFactory metaModelFactory;
	protected ModelManager modelManager;
	protected NewSimpleFitnessFunction fitness;
	protected DOMView domView;
	protected String example;
	
	
	public TAbstractTransformationTest() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	public void setExample(String example) {
		this.example = example;
	}
	
	
	@Before
	public void setUp() throws ParserConfigurationException, ModelParsingException {
		modelManager = new ModelManager();
		
		modelManager.setExample(example);
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
}
