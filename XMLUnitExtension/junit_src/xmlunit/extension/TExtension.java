package xmlunit.extension;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.examples.MultiLevelElementNameAndTextQualifier;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.junit.Test;

public class TExtension {

	@Test
	public void testIdenticalXml() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
				"<name>C1</name>" +
				"<attribute id=\"A1\">" +
					"<name>a1</name>" +
				"</attribute>" +
			"</class>" +
		"</fragment>";
		
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class id=\"C1\">" +
					"<name>C1</name>" +
					"<attribute id=\"A1\">" +
						"<name>a1</name>" +
					"</attribute>" +
				"</class>" +
			"</fragment>";
		
		XMLAssert.assertXMLEqual(xmlTest, xml);
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertTrue(myDiff.similar());
		assertTrue(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
				"<name>C1</name>" +
				"<attribute id=\"A1\">" +
					"<name>a1</name>" +
				"</attribute>" +
			"</class>" +
			"<class id=\"C2\">" +
				"<name>C2</name>" +
				"<attribute id=\"A3\">" +
					"<name>a3</name>" +
				"</attribute>" +
			"</class>" +
		"</fragment>";
		
		// document order of class C1 and C2 swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class id=\"C2\">" +
					"<name>C2</name>" +
					"<attribute id=\"A3\">" +
						"<name>a3</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C1\">" +
					"<name>C1</name>" +
					"<attribute id=\"A1\">" +
						"<name>a1</name>" +
					"</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		DifferenceListener dl = new IgnoreTextAndAttributeValuesDifferenceListener();
		myDiff.overrideDifferenceListener(dl);
	
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml2() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
			"</class>" +
			"<class id=\"C2\">" +
			"</class>" +
		"</fragment>";
		
		// document order of class C1 and C2 swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class id=\"C2\">" +
				"</class>" +
				"<class id=\"C1\">" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertFalse(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml3() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class1>" +
			"</class1>" +
			"<class2>" +
			"</class2>" +
		"</fragment>";
		
		// document order of class C1 and C2 swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class2>" +
				"</class2>" +
				"<class1>" +
				"</class1>" +
			"</fragment>";
		
		//XMLAssert.assertXMLEqual(xmlTest, xml);
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml4() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class1>" +
				"<attribute></attribute>" +
			"</class1>" +
			"<class2>" +
			"</class2>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class2>" +
				"</class2>" +
				"<class1>" +
					"<attribute></attribute>" +
				"</class1>" +
			"</fragment>";
		
		//XMLAssert.assertXMLEqual(xmlTest, xml);
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml5() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class1>" +
				"<attribute1>A1</attribute1>" +
				"<attribute2>A2</attribute2>" +
			"</class1>" +
			"<class2>" +
			"</class2>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class2>" +
				"</class2>" +
				"<class1>" +
					"<attribute2>A2</attribute2>" +
					"<attribute1>A1</attribute1>" +
				"</class1>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml6() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class1>" +
				"<attribute>A1</attribute>" +
				"<attribute>A2</attribute>" +
			"</class1>" +
			"<class2>" +
			"</class2>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class2>" +
				"</class2>" +
				"<class1>" +
					"<attribute>A2</attribute>" +
					"<attribute>A1</attribute>" +
				"</class1>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		assertFalse(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml7() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class1>" +
				"<attribute>A1</attribute>" +
				"<attribute>A2</attribute>" +
			"</class1>" +
			"<class2>" +
			"</class2>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class2>" +
				"</class2>" +
				"<class1>" +
					"<attribute>A2</attribute>" +
					"<attribute>A1</attribute>" +
				"</class1>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new MultiLevelElementNameAndTextQualifier(1));

		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml8() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class>" +
				"<name>C1</name>" +
				"<attribute>A1</attribute>" +
				"<attribute>A2</attribute>" +
			"</class>" +
				"<name>C2</name>" +
			"<class>" +
			"</class>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class>" +
					"<name>C2</name>" +
				"</class>" +
				"<class>" +
					"<name>C1</name>" +
					"<attribute>A1</attribute>" +
					"<attribute>A2</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new ExtElementQualifier(
						asList("class", "attribute"), 
						"name"));

		assertFalse(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testRepeatedChildElements() throws Exception {
	    String myControlXML = 
	    	"<suite>" + 
	        	"<test status=\"pass\">FirstTestCase</test>" +
	        	"<test status=\"pass\">SecondTestCase</test>" +
	        "</suite>";
	    String myTestXML = 
	    	"<suite>" +
	        	"<test status=\"pass\">SecondTestCase</test>" +
	        	"<test status=\"pass\">FirstTestCase</test>" +
	        "</suite>";
	    XMLAssert.assertXMLNotEqual("Repeated child elements in different sequence order are not equal by default",
	                      myControlXML, myTestXML);
	    Diff myDiff = new Diff(myControlXML, myTestXML);
	    myDiff.overrideElementQualifier(new ElementNameAndTextQualifier());
	    XMLAssert.assertXMLEqual("But they are equal when an ElementQualifier controls which test element is compared with each control element",
	                    myDiff, true);
	    
	    assertTrue(myDiff.similar());
	}
	
	
	@Test
	public void testSimilarXml9() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class>" +
				"<name>C1</name>" +
				"<attribute>A1</attribute>" +
				"<attribute>A2</attribute>" +
			"</class>" +
			"<class>" +
				"<name>C2</name>" +
			"</class>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class>" +
					"<name>C2</name>" +
				"</class>" +
				"<class>" +
					"<name>C1</name>" +
					"<attribute>A2</attribute>" +
					"<attribute>A1</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new RecursiveElementNameAndTextQualifier());

		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml10() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class>" +
				"<name>C1</name>" +
				"<attribute>" +
					"<name>A1</name>" +
				"</attribute>" +
				"<attribute>" +
					"<name>A2</name>" +
				"</attribute>" +
			"</class>" +
			"<class>" +
				"<name>C2</name>" +
			"</class>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"F1\">" +
				"<class>" +
					"<name>C2</name>" +
				"</class>" +
				"<class>" +
					"<name>C1</name>" +
					"<attribute>" +
						"<name>A2</name>" +
					"</attribute>" +
					"<attribute>" +
						"<name>A1</name>" +
					"</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new RecursiveElementNameAndTextQualifier());

		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml12() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
				"<name>C1</name>" +
				"<attribute id=\"A1\">" +
					"<name>A1</name>" +
				"</attribute>" +
				"<attribute id=\"A2\">" +
					"<name>A2</name>" +
				"</attribute>" +
			"</class>" +
			"<class id=\"C2\">" +
				"<name>C2</name>" +
			"</class>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"*\">" +
				"<class id=\"*\">" +
					"<name>C2</name>" +
				"</class>" +
				"<class id=\"*\">" +
					"<name>C1</name>" +
					"<attribute id=\"*\">" +
						"<name>A2</name>" +
					"</attribute>" +
					"<attribute id=\"*\">" +
						"<name>A1</name>" +
					"</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new RecursiveElementNameAndTextQualifier());
		myDiff.overrideDifferenceListener(
				new IgnoreTextAndAttributeValuesDifferenceListener());
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	@Test
	public void testSimilarXml11() throws Exception {
		String xml = 
		"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
				"<name>C1</name>" +
				"<attribute id=\"A1\">" +
					"<name>A1</name>" +
				"</attribute>" +
				"<attribute id=\"A2\">" +
					"<name>A2</name>" +
				"</attribute>" +
			"</class>" +
			"<class id=\"C2\">" +
				"<name>C2</name>" +
			"</class>" +
		"</fragment>";
		
		// document order of elements swapped
		String xmlTest = 
			"<fragment id=\"*\">" +
				"<class id=\"*\">" +
					"<name>C2</name>" +
				"</class>" +
				"<class id=\"*\">" +
					"<name>C1</name>" +
					"<attribute id=\"*\">" +
						"<name>A2</name>" +
					"</attribute>" +
					"<attribute id=\"*\">" +
						"<name>A1</name>" +
					"</attribute>" +
				"</class>" +
			"</fragment>";
		
		Diff myDiff = new Diff(xmlTest, xml);
		myDiff.overrideElementQualifier(
				new RecursiveElementNameAndTextQualifier());
		myDiff.overrideDifferenceListener(
				new WildcardDifferenceListener("*"));
		assertTrue(myDiff.similar());
		assertFalse(myDiff.identical());
		
	}
	
	
	
}
