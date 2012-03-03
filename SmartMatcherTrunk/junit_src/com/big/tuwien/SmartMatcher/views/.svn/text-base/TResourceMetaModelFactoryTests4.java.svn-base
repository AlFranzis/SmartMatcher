package com.big.tuwien.SmartMatcher.views;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class TResourceMetaModelFactoryTests4 extends TAbstractMetaModelFactoryTests2 {
	private Logger logger = Logger.getLogger(TResourceMetaModelFactoryTests4.class);
	
	
	public TResourceMetaModelFactoryTests4() {
		setExample(ExampleStore.UML14_2_UML20_Light);
	}

	
	@Test
	public void testInstanceFiltering() {
		// only a few elements in the example have instances
		
		MetaModel lhsUnfiltered = this.metaModelFactory.getLHSMetaModel();
		
		Element _sf = lhsUnfiltered.getClassByName("StructuralFeature");
		ClassElement sf = (ClassElement) _sf.getRepresentedBy();
		assertEquals("Class StructuralFeature should have 27 references", 27, sf.getReferences().size());

		Element _cl = lhsUnfiltered.getClassByName("Classifier");
		ClassElement cl = (ClassElement) _cl.getRepresentedBy();
		assertEquals("Class Classifier should have 26 references", 26, cl.getReferences().size());
		
		Element _c = lhsUnfiltered.getClassByName("Class");
		ClassElement c = (ClassElement) _c.getRepresentedBy();
		assertEquals("Class Class should have 26 references", 26, c.getReferences().size());
		
		
		Element _a = lhsUnfiltered.getClassByName("Attribute");
		ClassElement a = (ClassElement) _a.getRepresentedBy();
		assertEquals("Class Attribute should have 28 references", 28, a.getReferences().size());
		
		
			
		this.metaModelFactory.addFilter(new InstanceFilter());
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		
//		_a = lhsFiltered.getClassByName("Attribute");
//		a = (ClassElement) _a.getRepresentedBy();
//		assertNotNull("Class Attribute should be in the filtered model", a);
//		assertEquals("Class Attribute should have 1 references", 1, a.getReferences().size());
//		
//		_m = lhsFiltered.getClassByName("Model");
//		m = (ClassElement) _m.getRepresentedBy();
//		assertNotNull("Class Model should be in the filtered model", m);
//		assertEquals("Class Model should have 1 references", 1, m.getReferences().size());
//		
//		_e = lhsFiltered.getClassByName("Entity");
//		e = (ClassElement) _e.getRepresentedBy();
//		assertNotNull("Class Entity should be in the filtered model", e);
//		assertEquals("Class Entity should have 1 references", 1, e.getReferences().size());
//		
//		assertEquals("Model should have 3 classes", 3, lhsFiltered.getClasses().size());
//		assertEquals("Model should have 2 attributes", 2, lhsFiltered.getAttributes().size());
//		assertEquals("Model should have 3 references", 3, lhsFiltered.getReferences().size());
//		
	}
	
	
}
