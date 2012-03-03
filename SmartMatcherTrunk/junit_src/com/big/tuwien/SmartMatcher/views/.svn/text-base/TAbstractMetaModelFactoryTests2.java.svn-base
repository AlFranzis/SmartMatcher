package com.big.tuwien.SmartMatcher.views;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;

import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public abstract class TAbstractMetaModelFactoryTests2 {
	protected DOMView view;
	protected String example;
	protected MetaModelFactory metaModelFactory;
	
	public TAbstractMetaModelFactoryTests2() {}
	
	
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
		
		ModelLinker.linkModels(modelManager, metaModelFactory);
		
		MetaModelFactoryUtil.setMetaModelFactory(metaModelFactory);
		
		this.view = new DOMView();
		this.view.setMetaModelFactory(metaModelFactory);
		this.view.buildView();
	}	
		
}
