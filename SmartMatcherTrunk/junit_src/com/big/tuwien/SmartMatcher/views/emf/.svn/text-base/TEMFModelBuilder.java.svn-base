package com.big.tuwien.SmartMatcher.views.emf;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.xml.DOMConfigurator;
import org.hamcrest.Matcher;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Before;
import org.junit.Test;

import sm_mm.MappingModel;
import sm_mm.Sm_mmFactory;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;

public class TEMFModelBuilder {
	protected String example;
	protected MetaModelFactory metaModelFactory;
	
	
	public TEMFModelBuilder() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	public void setExample(String example) {
		this.example = example;
	}
	
	
	
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
	}
	
	
	@Test
	public void testSimpleModel() throws Exception {
		setExample(ExampleStore.PERSON_PERSONFAMILY);
		setUp();
		
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		EMFModelBuilder builder = new EMFModelBuilder(factory);
		MetaModel lhsMM = metaModelFactory.getLHSMetaModel();
		MetaModel rhsMM = metaModelFactory.getRHSMetaModel();
		MappingModel mm = builder.build(lhsMM, rhsMM);
		
		List<sm_mm.Class> classes = mm.getClasses();
		assertThat(classes.size(), equalTo(3));
		assertThat(classes, hasItem(HasPropertyWithValue.<sm_mm.Class>hasProperty("name", equalTo("Person"))));
		assertThat(classes, hasItem(HasPropertyWithValue.<sm_mm.Class>hasProperty("name", equalTo("Family"))));
		
		List<sm_mm.Reference> refs = mm.getReferences();
		assertThat(refs.size(), equalTo(1));	
	}
}
