package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortXmlElementsByName;
import static java.util.Arrays.asList;
import static org.custommonkey.xmlunit.XMLAssert.assertXpathValuesEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.ElementMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TElementMarshaller {
	
	public TElementMarshaller() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	public class MM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public MM() {
			mm =  mm(li(
					c("C1", 
						li(
							a("a1"),
							a("a2")
						)
					).as("C1"),
					c("C2", 
						li(
							a("a3"),
							a("a4")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a5"),
							a("a6"),
							a("a7")
						)
					).as("C3"),
					c("C4", 
						li(
							a("a8")
						)
					).as("C4"),
					c("C5", 
						li(
							a("a9"),
							a("a10")
						)
					).as("C5"),
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	@Test
	public void testClassElementMarshalling() throws Exception {
		MetaModel mm = new MM().getMetaModel();
		
		Element c1 = mm.getClassByName("C1");
		Element c2 = mm.getClassByName("C2");
		Element c3 = mm.getClassByName("C3");
			
		XMLElementResolverFactory resolverFac = 
								new XMLElementResolverFactory();
		Set<XMLClassElement> xmlClasses = resolverFac.resolve2(
												asList(c1, c2, c3)
												);
		
		ElementMarshaller marshaller = new ElementMarshaller();
		
		String controlXml1 =
			"<class id=\"*\">\n" + 
			"    <name>C1</name>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a2</name>\n" + 
			"    </attribute>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a1</name>\n" + 
			"    </attribute>\n" + 
			"    <reference id=\"*\" target=\"*\">\n" + 
			"        <name>r1</name>\n" + 
			"    </reference>\n" + 
			"</class>";
		
		String controlXml2 =
			"<class id=\"*\">\n" + 
			"    <name>C2</name>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a3</name>\n" + 
			"    </attribute>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a4</name>\n" + 
			"    </attribute>\n" + 
			"    <reference id=\"*\" target=\"*\">\n" + 
			"        <name>r3</name>\n" + 
			"    </reference>\n" + 
			"    <reference id=\"*\" target=\"*\">\n" + 
			"        <name>r2</name>\n" + 
			"    </reference>\n" + 
			"</class>";
		
		String controlXml3 =
			"<class id=\"*\">\n" + 
			"    <name>C3</name>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a6</name>\n" + 
			"    </attribute>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a7</name>\n" + 
			"    </attribute>\n" + 
			"    <attribute id=\"*\">\n" + 
			"        <name>a5</name>\n" + 
			"    </attribute>\n" + 
			"</class>";
		
		// get sorted list to make assertions easy
		List<XMLClassElement> sXmlClasses = sortXmlElementsByName(xmlClasses);
		
		XMLClassElement xmlClassC1 = sXmlClasses.get(0);
		String xml1 = marshaller.marshall(xmlClassC1);
		AttWildcardIdElementDiff diff = new AttWildcardIdElementDiff(controlXml1, xml1);
		assertTrue(diff.similar());
		
		XMLClassElement xmlClassC2 = sXmlClasses.get(1);
		String xml2 = marshaller.marshall(xmlClassC2);
		AttWildcardIdElementDiff diff2 = new AttWildcardIdElementDiff(controlXml2, xml2);
		assertTrue(diff2.similar());
		
		XMLClassElement xmlClassC3 = sXmlClasses.get(2);
		String xml3 = marshaller.marshall(xmlClassC3);
		AttWildcardIdElementDiff diff3 = new AttWildcardIdElementDiff(controlXml3, xml3);
		assertTrue(diff3.similar());
	}
	
	
	@Test
	public void testClassElementUnmarshalling() throws Exception {
		String xml =
			"<class id=\"C1\">" +
				"<name>C2</name>" +
				"<attribute id=\"A3\">" +
					"<name>a3</name>" +
				"</attribute>" +
				"<attribute id=\"A4\">" +
					"<name>a4</name>" +
				"</attribute>" +
			"</class>";	
		
		StringReader sreader = new StringReader(xml);
		ElementMarshaller unmarshaller = new ElementMarshaller();
		XMLClassElement unmarshalled = (XMLClassElement) 
									unmarshaller.unmarshall(sreader);
		
		assertEquals("C2", unmarshalled.getName());
		assertEquals(2, unmarshalled.getAttributes().size());
	}
	
	
	@Test
	public void testMetaModelMarshalling() throws Exception {
		MetaModel mm = new MM().getMetaModel();
		
		XMLElementResolverFactory resolverFac = 
			new XMLElementResolverFactory();
		XMLMetaModel xmlMM = XMLMetaModel.getInstance(mm, resolverFac);
		
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		String xml = marshaller.marshall(xmlMM);
		
		String controlXml =
			"<metamodel>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C4</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a8</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C2</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a3</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a4</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r3</name>\n" + 
			"        </reference>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r2</name>\n" + 
			"        </reference>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C5</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a10</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a9</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C1</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a1</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a2</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r1</name>\n" + 
			"        </reference>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C3</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</metamodel>";
		
		AttWildcardIdElementDiff diff = 
			new AttWildcardIdElementDiff(controlXml, xml);
		assertTrue(diff.similar());
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml);
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml);
		
		// target of reference r3 is class named C3
		assertXpathValuesEqual(
				"//class[name=\"C3\"]/@id", 
				"//class/reference[name=\"r3\"]/@target", 
				xml);	
	}
	
}
