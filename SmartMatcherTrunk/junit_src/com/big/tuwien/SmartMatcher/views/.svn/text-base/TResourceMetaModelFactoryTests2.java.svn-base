package com.big.tuwien.SmartMatcher.views;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.ElementFilter;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class TResourceMetaModelFactoryTests2 extends TAbstractMetaModelFactoryTests {
	private Logger logger = Logger.getLogger(TResourceMetaModelFactoryTests2.class);
	
	
	public TResourceMetaModelFactoryTests2() {
		setExample(ExampleStore.ER_2_WEBML);
	}
	
	
	@Test
	public void testInstanceFiltering() {
		// only a few elements in the example have instances
		
		MetaModel lhsUnfiltered = this.metaModelFactory.getLHSMetaModel();
		
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of classes", lhsUnfiltered.getClasses().size() == 8);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of attributes", lhsUnfiltered.getAttributes().size() == 7);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of references", lhsUnfiltered.getReferences().size() == 16);
		
		MetaModel rhsUnfiltered = this.metaModelFactory.getRHSMetaModel();
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of classes", rhsUnfiltered.getClasses().size() == 7);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of attributes", rhsUnfiltered.getAttributes().size() == 12);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of references", rhsUnfiltered.getReferences().size() == 8);
		
		this.metaModelFactory.addFilter(new InstanceFilter());
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		// Classes Model, Attribute, Entity
		assertTrue("Filtered LHS MetaModel has unexpected amount of classes", lhsFiltered.getClasses().size() == 3);
		assertTrue("Filtered LHS MetaModel has unexpected amount of attributes", lhsFiltered.getAttributes().size() == 2);
		assertTrue("Filtered LHS MetaModel has unexpected amount of references", lhsFiltered.getReferences().size() == 3);
		
		
		MetaModel rhsFiltered = this.metaModelFactory.getRHSMetaModel();
		
		assertTrue("Filtered RHS MetaModel has unexpected amount of classes", rhsFiltered.getClasses().size() == 3);
		assertTrue("Filtered RHS MetaModel has unexpected amount of attributes", rhsFiltered.getAttributes().size() == 4);
		assertTrue("Filtered RHS MetaModel has unexpected amount of references", rhsFiltered.getReferences().size() == 2);
	}
	
	
	@Test
	public void testAttributeNameFiltering() {
		
		MetaModel lhsUnfiltered = this.metaModelFactory.getLHSMetaModel();
		
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of classes", lhsUnfiltered.getClasses().size() == 8);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of attributes", lhsUnfiltered.getAttributes().size() == 7);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of references", lhsUnfiltered.getReferences().size() == 16);
		
		MetaModel rhsUnfiltered = this.metaModelFactory.getRHSMetaModel();
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of classes", rhsUnfiltered.getClasses().size() == 7);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of attributes", rhsUnfiltered.getAttributes().size() == 12);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of references", rhsUnfiltered.getReferences().size() == 8);
		
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
		
		assertTrue("Filtered LHS MetaModel has unexpected amount of classes", lhsFiltered.getClasses().size() == 1);
		assertTrue("Filtered LHS MetaModel has unexpected amount of attributes", lhsFiltered.getAttributes().size() == 2);
		assertTrue("Filtered LHS MetaModel has unexpected amount of references", lhsFiltered.getReferences().size() == 16);
		
		
		MetaModel rhsFiltered = this.metaModelFactory.getRHSMetaModel();
		
		assertTrue("Filtered RHS MetaModel has unexpected amount of classes", rhsFiltered.getClasses().size() == 2);
		assertTrue("Filtered RHS MetaModel has unexpected amount of attributes", rhsFiltered.getAttributes().size() == 1);
		assertTrue("Filtered RHS MetaModel has unexpected amount of references", rhsFiltered.getReferences().size() == 1);
	}
	
	
	@Test
	public void testClassNameFiltering() {
		
		MetaModel lhsUnfiltered = this.metaModelFactory.getLHSMetaModel();
		
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of classes", lhsUnfiltered.getClasses().size() == 1);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of attributes", lhsUnfiltered.getAttributes().size() == 2);
		assertTrue("Unfiltered LHS MetaModel has unexpected amount of references", lhsUnfiltered.getReferences().size() == 0);
		
		MetaModel rhsUnfiltered = this.metaModelFactory.getRHSMetaModel();
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of classes", rhsUnfiltered.getClasses().size() == 2);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of attributes", rhsUnfiltered.getAttributes().size() == 2);
		assertTrue("Unfiltered RHS MetaModel has unexpected amount of references", rhsUnfiltered.getReferences().size() == 1);
		
		this.metaModelFactory.addFilter(new ElementFilter() {

			public boolean filter(Element e) {
				// filter all attributes with name "firstName"
				if(e.getRepresentedBy() instanceof ClassElement) {
					return ((ClassElement) e.getRepresentedBy())
						.getRepresents().getName().equals("Person"); 
				}
				return false;
			}
			
		});
		
		MetaModel lhsFiltered = this.metaModelFactory.getLHSMetaModel();
		
		// Person class is filtered -> both attributes of Person are gone too
		assertTrue("Filtered LHS MetaModel has unexpected amount of classes", lhsFiltered.getClasses().size() == 0);
		assertTrue("Filtered LHS MetaModel has unexpected amount of attributes", lhsFiltered.getAttributes().size() == 0);
		assertTrue("Filtered LHS MetaModel has unexpected amount of references", lhsFiltered.getReferences().size() == 0);
		
		
		MetaModel rhsFiltered = this.metaModelFactory.getRHSMetaModel();
		
		assertTrue("Filtered RHS MetaModel has unexpected amount of classes", rhsFiltered.getClasses().size() == 1);
		// Person class is filtered -> "firstName" attribute of Person is gone too
		assertTrue("Filtered RHS MetaModel has unexpected amount of attributes", rhsFiltered.getAttributes().size() == 1);
		// Person class is filtered -> reference contained in Person is gone too
		assertTrue("Filtered RHS MetaModel has unexpected amount of references", rhsFiltered.getReferences().size() == 0);
	}
}
