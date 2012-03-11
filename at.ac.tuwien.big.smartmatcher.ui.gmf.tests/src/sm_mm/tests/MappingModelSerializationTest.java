package sm_mm.tests;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.XMLAssert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import sm_mm.C2C;
import sm_mm.Class;
import sm_mm.MappingModel;
import sm_mm.Sm_mmFactory;


public class MappingModelSerializationTest {
	protected MappingModel mm;
	protected Class c1;
	protected Class c2;
	protected C2C c2c1;
	
	
	@Before
	public void before() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		
		// build test model
		
		mm = factory.createMappingModel();
		
		c1 = factory.createClass();
		c1.setName("C1");
		mm.getClasses().add(c1);
		
		c2 = factory.createClass();
		c2.setName("C2");
		c2.setLhs(false);
		mm.getClasses().add(c2);
		
		c2c1 = factory.createC2C();
		c2c1.setLhs(c1);
		c2c1.setRhs(c2);
		mm.getOperators().add(c2c1);
	}
	
	
	@Test
	public void testElementToOperatorReference() {
		assertTrue(c1.getOperator() == c2c1);
		assertTrue(c2.getOperator() == c2c1);
	}
	
	
	@Test
	public void testIsMappedFeature() {
		assertTrue(c1.isMapped());
		assertTrue(c2.isMapped());
	}
	
	
	@Test
	public void testSave() throws Exception {
		// serialize test model
		
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().
				put("xmi", new XMIResourceFactoryImpl());
		URI uri = URI.createURI("testSave.xmi");
		Resource res = rs.createResource(uri);
		
		res.getContents().add(mm);
		
		XMIResource xrs = (XMIResource) res;
		// save xmi serialization into DOM object
		Document doc = xrs.save(null, null, null);
		
		// expected xmi structure (without correct attribute and text values)
		String oracle = 
			"<sm_mm:MappingModel xmi:version= \"\" xmlns:xmi=\"http://www.omg.org/XMI\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
			"xmlns:sm_mm=\"http://big.tuwien.ac.at/smartmatcher/ui/gmf/sm_mm1\">" +
				"<classes name=\"\"/>" +
				"<classes lhs=\"false\" name=\"\"/>" +
				"<operators xsi:type=\"\" lhs=\"\" rhs=\"\"/>" +
			"</sm_mm:MappingModel>";
		
		DocumentBuilderFactory docbuilderfac = DocumentBuilderFactory.newInstance();
		docbuilderfac.setNamespaceAware(true);
		
		DocumentBuilder docbuilder = docbuilderfac.newDocumentBuilder();
		Document oracleDoc = docbuilder.parse(new ByteArrayInputStream(oracle.getBytes()));
		
		// the attribute and text values are ignored in the diff
		DifferenceListener dl = new IgnoreTextAndAttributeValuesDifferenceListener();
		Diff diff = new Diff(oracleDoc, doc);
		diff.overrideDifferenceListener(dl);
		
		// check for correct xmi skeleton structure
		assertTrue("generated xmi serialization has unexpected structure", diff.similar());
		
		// check correct serialization of operator roles
		XMLAssert.assertXpathEvaluatesTo("//@classes.0", "//operators/@lhs", doc);
		XMLAssert.assertXpathEvaluatesTo("//@classes.1", "//operators/@rhs", doc);
	}
	
	
}
