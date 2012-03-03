package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortXmlElementsByName;
import static java.util.Arrays.asList;
import static org.custommonkey.xmlunit.XMLAssert.assertXpathValuesEqual;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TXMLFragmentMarshalling {
	
	public TXMLFragmentMarshalling() {
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
	public void testMarshallingOfSingleFragment() throws Exception {
		EasyMM  mm = new EasyMM(new MM().getMetaModel());
		
		Element c1 =  mm.clazz("C1");
		Element c2 =  mm.clazz("C2");
		Element c3 =  mm.clazz("C3");
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(new HashSet<Element>(asList(c1, c2, c3)));
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		XMLFragment xmlFrag1 = XMLFragment.getInstance(f1, resolverFac);
		
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		
		// marshall fragment
		String xml = marshaller.marshall(xmlFrag1);
		
		//CHECKS
		
		String controlXml =
			"<fragment id=\"F1\" root=\"true\">\n" + 
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
			"        <name>C2</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a4</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a3</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r3</name>\n" + 
			"        </reference>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r2</name>\n" + 
			"        </reference>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C3</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</fragment>";
		
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
	
	
	@Test
	public void testMarshallingOfParentAndChildFragment() throws Exception {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		
		Element c1 =  mm.clazz("C1");
		Element c2 =  mm.clazz("C2");
		Element c3 =  mm.clazz("C3");
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(new HashSet<Element>(asList(c1, c2)));
		
		Fragment f2 = new Fragment();
		f2.setId("F2");
		f2.setRoot(false);
		f2.setClasses(new HashSet<Element>(asList(c1, c2, c3)));
		f2.setParents(new HashSet<Fragment>(asList(f1)));
		
		XMLElementResolverFactory resolverFac = 
									new XMLElementResolverFactory();
		XMLFragment xmlFrag1 = XMLFragment.getInstance(f1, resolverFac);
		XMLFragment xmlFrag2 = XMLFragment.getInstance(f2, resolverFac);
		
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		String xml1 = marshaller.marshall(xmlFrag1);
		String xml2 = marshaller.marshall(xmlFrag2);
		
		String controlXml1 = 
			"<fragment id=\"F1\" root=\"true\">\n" + 
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
			"    <children>F2</children>\n" +
			"</fragment>\n";
		
		System.out.println(xml1);
		
		AttWildcardIdElementDiff diff = 
			new AttWildcardIdElementDiff(controlXml1, xml1);
		assertTrue(diff.similar());
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml1);
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml1);
		
		String controlXml2 =
			"<fragment id=\"F2\" parent=\"F1\" root=\"false\">\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C1</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a2</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a1</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r1</name>\n" + 
			"        </reference>\n" + 
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
			"        <name>C3</name>\n" + 
			"        <attribute id=\"A7\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</fragment>";
		
		System.out.println(xml2);
		
		AttWildcardIdElementDiff diff2 = 
			new AttWildcardIdElementDiff(controlXml2, xml2);
		assertTrue("Unexpected Fragment2 marshalling result", 
				diff2.similar());
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml2);
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml2);
		
		// target of reference r3 is class named C3
		assertXpathValuesEqual(
				"//class[name=\"C3\"]/@id", 
				"//class/reference[name=\"r3\"]/@target", 
				xml2);
	}
	
	/*
	 * Same as test case above but marshalls both fragments at once. 
	 */
	@Test
	public void testAtomicMarshallingOfParentAndChildFragment() 
												throws Exception {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		
		Element c1 =  mm.clazz("C1");
		Element c2 =  mm.clazz("C2");
		Element c3 =  mm.clazz("C3");
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(new HashSet<Element>(asList(c1, c2)));
		
		Fragment f2 = new Fragment();
		f2.setId("F2");
		f2.setRoot(false);
		f2.setClasses(new HashSet<Element>(asList(c1, c2, c3)));
		f2.setParents(new HashSet<Fragment>(asList(f1)));
		
		XMLElementResolverFactory resolverFac = 
									new XMLElementResolverFactory();
		XMLFragments xmlFrags = XMLFragments
						.getInstance(asList(f1, f2), resolverFac);
		
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		String xml = marshaller.marshall(xmlFrags);
		
		String controlXml =
			"<fragments>\n" +
			"<fragment id=\"F1\" root=\"true\">\n" + 
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
			"    <children>F2</children>\n" +
			"</fragment>\n" +
			"<fragment id=\"F2\" parent=\"F1\" root=\"false\">\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C1</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a2</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a1</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r1</name>\n" + 
			"        </reference>\n" + 
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
			"        <name>C3</name>\n" + 
			"        <attribute id=\"A7\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</fragment>\n" +
			"</fragments>";
		
		AttWildcardIdElementDiff diff = 
			new AttWildcardIdElementDiff(controlXml, xml);
		assertTrue("Unexpected Fragment2 marshalling result", 
				diff.similar());
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml);
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml);
		
		// target of reference r3 is class named C3
		assertXpathValuesEqual(
				"//class[name=\"C3\"]/@id", 
				"//class/reference[name=\"r3\"]/@target", 
				xml);
	}
	
	
	@Test
	public void testMarshallingOfFragmentWithMultipleParents() throws Exception {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		
		Element c1 =  mm.clazz("C1");
		Element c2 =  mm.clazz("C2");
		Element c3 =  mm.clazz("C3");
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(new HashSet<Element>(asList(c1, c2)));
		
		Fragment f2 = new Fragment();
		f2.setId("F2");
		f2.setRoot(true);
		f2.setClasses(new HashSet<Element>(asList(c3)));
		
		Fragment f3 = new Fragment();
		f3.setId("F3");
		f3.setRoot(false);
		f3.setClasses(new HashSet<Element>(asList(c1, c2, c3)));
		
		f3.setParents(new HashSet<Fragment>(asList(f1, f2)));
		
		XMLElementResolverFactory resolverFac = 
								new XMLElementResolverFactory();
		XMLFragments xmlFrags = XMLFragments
					.getInstance(asList(f1, f2, f3), resolverFac);
		
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		String xml = marshaller.marshall(xmlFrags);
		
		System.out.println(xml);
		
		String controlXml = 
			"<fragments>\n" + 
			"    <fragment id=\"F1\" root=\"true\">\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C2</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a3</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a4</name>\n" + 
			"            </attribute>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r3</name>\n" + 
			"            </reference>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r2</name>\n" + 
			"            </reference>\n" + 
			"        </class>\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C1</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a1</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a2</name>\n" + 
			"            </attribute>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r1</name>\n" + 
			"            </reference>\n" + 
			"        </class>\n" + 
			"        <children>F3</children>\n" +
			"    </fragment>\n" + 
			"    <fragment id=\"F2\" root=\"true\">\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C3</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a7</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a6</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a5</name>\n" + 
			"            </attribute>\n" + 
			"        </class>\n" + 
			"        <children>F3</children>\n" +
			"    </fragment>\n" + 
			"    <fragment id=\"F3\" parent=\"F1 F2\" root=\"false\">\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C3</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a7</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a6</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a5</name>\n" + 
			"            </attribute>\n" + 
			"        </class>\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C2</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a3</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a4</name>\n" + 
			"            </attribute>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r3</name>\n" + 
			"            </reference>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r2</name>\n" + 
			"            </reference>\n" + 
			"        </class>\n" + 
			"        <class id=\"*\">\n" + 
			"            <name>C1</name>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a1</name>\n" + 
			"            </attribute>\n" + 
			"            <attribute id=\"*\">\n" + 
			"                <name>a2</name>\n" + 
			"            </attribute>\n" + 
			"            <reference id=\"*\" target=\"*\">\n" + 
			"                <name>r1</name>\n" + 
			"            </reference>\n" + 
			"        </class>\n" + 
			"    </fragment>\n" + 
			"</fragments>\n";
		
		System.out.println(xml);
		
		AttWildcardIdElementDiff diff = 
			new AttWildcardIdElementDiff(controlXml, xml);
		
		//TODO: if fails this may be due to different document order
		// of fragment elements
		assertTrue("Unexpected marshalling result", 
				diff.similar());
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml);
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml);
		
		// target of reference r3 is class named C3
		assertXpathValuesEqual(
				"//class[name=\"C3\"]/@id", 
				"//class/reference[name=\"r3\"]/@target", 
				xml);
		
	}
	
	
	@Test
	public void testFragmentsUnmarshalling() throws Exception {
		String xml =
			"<fragments>" +
			"<fragment id=\"F1\">" +
				"<class id=\"C3\">" +
					"<name>C3</name>" +
					"<attribute id=\"A5\">" +
						"<name>a5</name>" +
					"</attribute>" +
					"<attribute id=\"A7\">" +
						"<name>a7</name>" +
					"</attribute>" +
					"<attribute id=\"A6\">" +
						"<name>a6</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C1\">" +
					"<name>C1</name>" +
					"<attribute id=\"A8\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<attribute id=\"A9\">" +
						"<name>a1</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C2\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
				"<class id=\"C2\">" +
					"<name>C2</name>" +
					"<attribute id=\"A10\">" +
						"<name>a4</name>" +
					"</attribute>" +
					"<attribute id=\"A11\">" +
						"<name>a3</name>" +
					"</attribute>" +
					"<reference id=\"R2\" target=\"C3\">" +
						"<name>r3</name>" +
					"</reference>" +
					"<reference id=\"R3\" target=\"C1\">" +
						"<name>r2</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>" +
			"<fragment id = \"F2\" parent=\"F1\">" +
				"<class id=\"C4\">" +
					"<name>C1</name>" +
					"<attribute id=\"A12\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<attribute id=\"A13\">" +
						"<name>a1</name>" +
					"</attribute>" +
					"<reference id=\"0\" target=\"2\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
				"<class id=\"C5\">" +
					"<name>C3</name>" +
					"<attribute id=\"A14\">" +
						"<name>a6</name>" +
					"</attribute>" +
					"<attribute id=\"A15\">" +
						"<name>a7</name>" +
					"</attribute>" +
					"<attribute id=\"A16\">" +
						"<name>a5</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C6\">" +
					"<name>C2</name>" +
					"<attribute id=\"A17\">" +
						"<name>a4</name>" +
					"</attribute>" +
					"<attribute id=\"A18\">" +
						"<name>a3</name>" +
					"</attribute>" +
					"<reference id=\"R4\" target=\"C5\">" +
						"<name>r3</name>" +
					"</reference>" +
					"<reference id=\"R5\" target=\"C6\">" +
						"<name>r2</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>" +
			"</fragments>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragments xmlFragments = (XMLFragments) unmarshaller.unmarshall(new StringReader(xml));
		
		assertEquals(2, xmlFragments.getFragments().size());
		
		XMLFragment xmlFragment1 = xmlFragments.getFragments().get(0);
		XMLFragment xmlFragment2 = xmlFragments.getFragments().get(1);
		
		
		assertTrue(!xmlFragment1.getParents().isEmpty() || !xmlFragment2.getParents().isEmpty());
		if(!xmlFragment2.getParents().isEmpty())
			assertTrue(xmlFragment2.getParents().contains(xmlFragment1));
		else 
			assertTrue(xmlFragment1.getParents().contains(xmlFragment2));
		
	}
	
	
	@Test
	public void testFragmentsUnmarshalling2() {
		String xml =
			"<fragment id = \"F1\">" +
				"<class id=\"C3\">" +
					"<name>C3</name>" +
					"<attribute id=\"A1\">" +
						"<name>a5</name>" +
					"</attribute>" +
					"<attribute id=\"A2\">" +
						"<name>a7</name>" +
					"</attribute>" +
					"<attribute id=\"A3\">" +
						"<name>a6</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C1\">" +
					"<name>C1</name>" +
					"<attribute id=\"A4\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<attribute id=\"A5\">" +
						"<name>a1</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C2\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
				"<class id=\"C2\">" +
					"<name>C2</name>" +
					"<attribute id=\"A6\">" +
						"<name>a4</name>" +
					"</attribute>" +
					"<attribute id=\"A7\">" +
						"<name>a3</name>" +
					"</attribute>" +
					"<reference id=\"R2\" target=\"C3\">" +
						"<name>r3</name>" +
					"</reference>" +
					"<reference id=\"R3\" target=\"C1\">" +
						"<name>r2</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragment xmlFragment = (XMLFragment) unmarshaller.unmarshall(new StringReader(xml));
		
		assertEquals(3, xmlFragment.getClasses().size());
		
	}
	
	
	@Test
	public void testUnmarshallingSimpleFragment() {
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
					"<attribute id=\"A2\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C1\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragment xmlFragment = (XMLFragment) unmarshaller
									.unmarshall(new StringReader(xml));
		
		Set<XMLClassElement> classes = xmlFragment.getClasses();
		assertEquals(2, classes.size());
		
		List<XMLClassElement> sclasses = sortXmlElementsByName(classes);
		
		XMLClassElement c1 = sclasses.get(0);
		
		XMLClassElement c2 = sclasses.get(1);
		assertEquals(1, c2.getReferences().size());
		XMLReferenceElement ref1 = c2.getReferences().iterator().next();
		assertTrue(ref1.getPointsTo().equals(c1));
	}
	
	
	@Test
	public void testUnmarshallingSimpleFragment2() {
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
					"<attribute id=\"A2\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C2\">" +
						"<name>r1</name>" +
					"</reference>" +
					"<reference id=\"R2\" target=\"C1\">" +
					"<name>r2</name>" +
				"</reference>" +
				"</class>" +
			"</fragment>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragment xmlFragment = (XMLFragment) unmarshaller
										.unmarshall(new StringReader(xml));
		
		Set<XMLClassElement> classes = xmlFragment.getClasses();
		assertEquals(2, classes.size());
		
		List<XMLClassElement> sclasses = sortXmlElementsByName(classes);
		XMLClassElement c1 = sclasses.get(0);
		
		XMLClassElement c2 = sclasses.get(1);
		assertEquals(2, c2.getReferences().size());	
		
		List<XMLReferenceElement> srefs = sortXmlElementsByName(c2.getReferences());
		XMLReferenceElement r1 = srefs.get(0);
		assertEquals(c2, r1.getPointsTo());
		
		XMLReferenceElement r2 = srefs.get(1);
		assertEquals(c1, r2.getPointsTo());
	}
	
	
	@Test
	public void testUnmarshallingWithDanglingRef() {
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
					"<attribute id=\"A2\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C3\">" +
						"<name>r1</name>" +
					"</reference>" +
					"<reference id=\"R2\" target=\"C1\">" +
					"<name>r2</name>" +
				"</reference>" +
				"</class>" +
			"</fragment>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragment xmlFragment = (XMLFragment) unmarshaller.unmarshall(new StringReader(xml));
		
		Set<XMLClassElement> classes = xmlFragment.getClasses();
		assertEquals(2, classes.size());
		
		List<XMLClassElement> sclasses = sortXmlElementsByName(classes);
		
		XMLClassElement c2 = sclasses.get(1);
		assertEquals(2, c2.getReferences().size());
		
		List<XMLReferenceElement> srefs = sortXmlElementsByName(c2.getReferences());
		XMLReferenceElement r1 = srefs.get(0);
		assertNull(r1.getPointsTo());	
	}
	
	
	@Test
	public void testFragmentsWithMultipleParentsUnmarshalling() throws Exception {
		String xml =
			"<fragments>" +
			"<fragment id=\"F1\">" +
				"<class id=\"C3\">" +
					"<name>C3</name>" +
					"<attribute id=\"A1\">" +
						"<name>a5</name>" +
					"</attribute>" +
					"<attribute id=\"A2\">" +
						"<name>a7</name>" +
					"</attribute>" +
					"<attribute id=\"A3\">" +
						"<name>a6</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C1\">" +
					"<name>C1</name>" +
					"<attribute id=\"A4\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<attribute id=\"A5\">" +
						"<name>a1</name>" +
					"</attribute>" +
					"<reference id=\"R1\" target=\"C2\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
				"<class id=\"C2\">" +
					"<name>C2</name>" +
					"<attribute id=\"A6\">" +
						"<name>a4</name>" +
					"</attribute>" +
					"<attribute id=\"A7\">" +
						"<name>a3</name>" +
					"</attribute>" +
					"<reference id=\"R2\">" +
						"<name>r3</name>" +
					"</reference>" +
					"<reference id=\"R3\" target=\"C1\">" +
						"<name>r2</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>" +
			"<fragment id=\"F2\">" +
			"<class id=\"C4\">" +
				"<name>C3</name>" +
				"<attribute id=\"A8\">" +
					"<name>a5</name>" +
				"</attribute>" +
				"<attribute id=\"A9\">" +
					"<name>a7</name>" +
				"</attribute>" +
				"<attribute id=\"A10\">" +
					"<name>a6</name>" +
				"</attribute>" +
			"</class>" +
			"<class id=\"C5\">" +
				"<name>C1</name>" +
				"<attribute id=\"A11\">" +
					"<name>a2</name>" +
				"</attribute>" +
				"<attribute id=\"A12\">" +
					"<name>a1</name>" +
				"</attribute>" +
				"<reference id=\"R4\">" +
					"<name>r1</name>" +
				"</reference>" +
			"</class>" +
			"<class id=\"C6\">" +
				"<name>C2</name>" +
				"<attribute id=\"A13\">" +
					"<name>a4</name>" +
				"</attribute>" +
				"<attribute id=\"A14\">" +
					"<name>a3</name>" +
				"</attribute>" +
				"<reference id=\"R5\">" +
					"<name>r3</name>" +
				"</reference>" +
				"<reference id=\"R6\">" +
					"<name>r2</name>" +
				"</reference>" +
			"</class>" +
			"</fragment>" +
			"<fragment id = \"F3\" parent=\"F1 F2\">" +
				"<class id=\"C7\">" +
					"<name>C1</name>" +
					"<attribute id=\"A15\">" +
						"<name>a2</name>" +
					"</attribute>" +
					"<attribute id=\"A16\">" +
						"<name>a1</name>" +
					"</attribute>" +
					"<reference id=\"R7\">" +
						"<name>r1</name>" +
					"</reference>" +
				"</class>" +
				"<class id=\"C8\">" +
					"<name>C3</name>" +
					"<attribute id=\"A17\">" +
						"<name>a6</name>" +
					"</attribute>" +
					"<attribute id=\"A18\">" +
						"<name>a7</name>" +
					"</attribute>" +
					"<attribute id=\"A19\">" +
						"<name>a5</name>" +
					"</attribute>" +
				"</class>" +
				"<class id=\"C9\">" +
					"<name>C2</name>" +
					"<attribute id=\"A20\">" +
						"<name>a4</name>" +
					"</attribute>" +
					"<attribute id=\"A21\">" +
						"<name>a3</name>" +
					"</attribute>" +
					"<reference id=\"R8\">" +
						"<name>r3</name>" +
					"</reference>" +
					"<reference id=\"R9\">" +
						"<name>r2</name>" +
					"</reference>" +
				"</class>" +
			"</fragment>" +
			"</fragments>";
		
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragments xmlFragments = (XMLFragments) unmarshaller.unmarshall(new StringReader(xml));
		
		assertEquals(3, xmlFragments.getFragments().size());
		
		XMLFragment xmlFragment1 = xmlFragments.getFragments().get(0);
		XMLFragment xmlFragment2 = xmlFragments.getFragments().get(1);
		XMLFragment xmlFragment3 = xmlFragments.getFragments().get(2);
		
		
		assertTrue(!xmlFragment1.getParents().isEmpty() || !xmlFragment2.getParents().isEmpty()
				|| !xmlFragment3.getParents().isEmpty());
		if(!xmlFragment1.getParents().isEmpty()) {
			assertTrue(xmlFragment1.getParents().contains(xmlFragment2));
			assertTrue(xmlFragment1.getParents().contains(xmlFragment3));
		} else if(!xmlFragment2.getParents().isEmpty()) {
			assertTrue(xmlFragment2.getParents().contains(xmlFragment1));
			assertTrue(xmlFragment2.getParents().contains(xmlFragment3));
		} else {
			assertTrue(xmlFragment3.getParents().contains(xmlFragment1));
			assertTrue(xmlFragment3.getParents().contains(xmlFragment2));
		}
	}
}
