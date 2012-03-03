package com.big.tuwien.SmartMatcher.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.ElementFilter;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class TResourceMetaModelFactoryTests3 extends TAbstractMetaModelFactoryTests2 {
	private Logger logger = Logger.getLogger(TResourceMetaModelFactoryTests3.class);
	
	
	public TResourceMetaModelFactoryTests3() {
		setExample(ExampleStore.ER_2_WEBML);
	}

	
	@Test
	public void testInstanceFiltering() {
		// only a few elements in the example have instances
		
		MetaModel lhsUnfiltered = this.metaModelFactory.getLHSMetaModel();
		
		Element _rs = lhsUnfiltered.getClassByName("Relationship");
		ClassElement rs = (ClassElement) _rs.getRepresentedBy();
		assertEquals("Class relationship should have 2 references", 2, rs.getReferences().size());
		
		Element _fd = lhsUnfiltered.getClassByName("FunctionalDependency");
		ClassElement fd = (ClassElement) _fd.getRepresentedBy();
		assertEquals("Class FunctionalDependency should have 2 references", 2, fd.getReferences().size());
		
		Element _ed = lhsUnfiltered.getClassByName("ExistenceDependency");
		ClassElement ed = (ClassElement) _ed.getRepresentedBy();
		assertEquals("Class FunctionalDependency should have 2 references", 2, ed.getReferences().size());
		
		Element _a = lhsUnfiltered.getClassByName("Attribute");
		ClassElement a = (ClassElement) _a.getRepresentedBy();
		assertEquals("Class Attribute should have 4 references", 4, a.getReferences().size());
		
		Element _e = lhsUnfiltered.getClassByName("Entity");
		ClassElement e = (ClassElement) _e.getRepresentedBy();
		assertEquals("Class Entity should have 3 references", 3, e.getReferences().size());
		
		Element _m = lhsUnfiltered.getClassByName("Model");
		ClassElement m = (ClassElement) _m.getRepresentedBy();
		assertEquals("Class Model should have 1 reference", 1, m.getReferences().size());
			
		this.metaModelFactory.addFilter(new InstanceFilter());
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		_a = lhsFiltered.getClassByName("Attribute");
		a = (ClassElement) _a.getRepresentedBy();
		assertNotNull("Class Attribute should be in the filtered model", a);
		assertEquals("Class Attribute should have 1 references", 1, a.getReferences().size());
		
		_m = lhsFiltered.getClassByName("Model");
		m = (ClassElement) _m.getRepresentedBy();
		assertNotNull("Class Model should be in the filtered model", m);
		assertEquals("Class Model should have 1 references", 1, m.getReferences().size());
		
		_e = lhsFiltered.getClassByName("Entity");
		e = (ClassElement) _e.getRepresentedBy();
		assertNotNull("Class Entity should be in the filtered model", e);
		assertEquals("Class Entity should have 1 references", 1, e.getReferences().size());
		
		assertEquals("Model should have 3 classes", 3, lhsFiltered.getClasses().size());
		assertEquals("Model should have 2 attributes", 2, lhsFiltered.getAttributes().size());
		assertEquals("Model should have 3 references", 3, lhsFiltered.getReferences().size());
		
	}
	
	
	@Test
	public void testAttributeNameFiltering() {
		this.metaModelFactory.addFilter(new ElementFilter() {

			public boolean filter(Element e) {
				// filter all attributes with name "name"
				if(e.getRepresentedBy() instanceof AttributeElement) {
					return ((AttributeElement) e.getRepresentedBy())
						.getRepresents().getName().equals("name"); 
				}
				return false;
			}
			
		});
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		assertEquals("Filtered LHS MetaModel has unexpected amount of classes", 8, lhsFiltered.getClasses().size());
		assertEquals("Filtered LHS MetaModel has unexpected amount of attributes", 2, lhsFiltered.getAttributes().size());
		assertEquals("Filtered LHS MetaModel has unexpected amount of references", 20, lhsFiltered.getReferences().size());
		
	}
	
	
	@Test
	public void testClassNameFiltering() {
		
		this.metaModelFactory.addFilter(new ElementFilter() {

			public boolean filter(Element e) {
				// filter all classes with name "Attribute"
				if(e.getRepresentedBy() instanceof ClassElement) {
					return ((ClassElement) e.getRepresentedBy())
						.getRepresents().getName().equals("Attribute"); 
				}
				return false;
			}
			
		});
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		assertEquals("Filtered LHS MetaModel has unexpected amount of classes", 7, lhsFiltered.getClasses().size());
		assertEquals("Filtered LHS MetaModel has unexpected amount of attributes", 6, lhsFiltered.getAttributes().size());
		assertEquals("Filtered LHS MetaModel has unexpected amount of references", 11, lhsFiltered.getReferences().size());
	}
}
