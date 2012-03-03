package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortXmlOpsByName;
import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.assertXpathNodeSetsEqual;
import static java.util.Arrays.asList;
import static org.custommonkey.xmlunit.XMLAssert.assertXpathValuesEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.ElementAttributeWildcardDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TXMLFragmentMappingMarshalling {
	
	public TXMLFragmentMappingMarshalling() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	public class LHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LHSMM() {
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
	
	
	public class RHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RHSMM() {
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
	public void testFragmentMappingMarshalling() throws Exception {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		Element lhsC1 =  lhsMM.clazz("C1");
		Element lhsA1 =  lhsMM.qatt("C1.a1");
		Element lhsC2 =  lhsMM.clazz("C2");
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		Element rhsC1 =  rhsMM.clazz("C1");
		Element rhsA1 =  rhsMM.qatt("C1.a1");
		Element rhsC2 =  rhsMM.clazz("C2");

		XMLElementResolverFactory resolverFac = 
						new XMLElementResolverFactory();
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(new HashSet<Element>(asList(lhsC1, lhsC2)));
		
		XMLFragment xmlFrag1 = XMLFragment.getInstance(f1, resolverFac);
		String xml = marshaller.marshall(xmlFrag1);
			
		// xmlFrag1 should look like this
		String controlXml = 
		"<fragment id=\"F1\" root=\"true\">" +
		"<class id=\"*\">" +
			"<name>C2</name>" +
			"<attribute id=\"*\">" +
				"<name>a3</name>" +
			"</attribute>" +
			"<attribute id=\"*\">" +
				"<name>a4</name>" +
			"</attribute>" +
			"<reference id=\"*\" target=\"*\">" +
				"<name>r2</name>" +
			"</reference>" +
			"<reference id=\"*\" target=\"*\">" +
				"<name>r3</name>" +
			"</reference>" +
		"</class>" +
	    "<class id=\"*\">" +
	        "<name>C1</name>" +
	        "<attribute id=\"*\">" +
	            "<name>a1</name>" +
	        "</attribute>" +
	        "<attribute id=\"*\">" +
	            "<name>a2</name>" +
	        "</attribute>" +
	        "<reference id=\"*\" target=\"*\">" +
	            "<name>r1</name>" +
	       "</reference>" +
	    "</class>" +
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
		
		Fragment f2 = new Fragment();
		f2.setId("F2");
		f2.setRoot(true);
		f2.setClasses(new HashSet<Element>(asList(rhsC1, rhsC2)));
		
		XMLFragment xmlFrag2 = XMLFragment.getInstance(f2, resolverFac);
		String xml2 = marshaller.marshall(xmlFrag2);
		
		// xmlFrag2 should look like this
		String controlXml2 = 
			"<fragment id=\"F2\" root=\"true\">" +
			"<class id=\"*\">" +
				"<name>C2</name>" +
				"<attribute id=\"*\">" +
					"<name>a3</name>" +
				"</attribute>" +
				"<attribute id=\"*\">" +
					"<name>a4</name>" +
				"</attribute>" +
				"<reference id=\"*\" target=\"*\">" +
					"<name>r2</name>" +
				"</reference>" +
				"<reference id=\"*\" target=\"*\">" +
					"<name>r3</name>" +
				"</reference>" +
			"</class>" +
		    "<class id=\"*\">" +
		        "<name>C1</name>" +
		        "<attribute id=\"*\">" +
		            "<name>a1</name>" +
		        "</attribute>" +
		        "<attribute id=\"*\">" +
		            "<name>a2</name>" +
		        "</attribute>" +
		        "<reference id=\"*\" target=\"*\">" +
		            "<name>r1</name>" +
		       "</reference>" +
		    "</class>" +
		    "</fragment>";
		
		AttWildcardIdElementDiff diff2 = 
					new AttWildcardIdElementDiff(controlXml2, xml2);
		
		System.out.println(xml2);
		
		assertTrue(diff2.similar());
		
		// target of reference r2 is class named C1
		assertXpathValuesEqual(
				"//class[name=\"C1\"]/@id", 
				"//class/reference[name=\"r2\"]/@target", 
				xml2);
		
		// target of reference r1 is class named C2
		assertXpathValuesEqual(
				"//class[name=\"C2\"]/@id", 
				"//class/reference[name=\"r1\"]/@target", 
				xml2);
		
		FragmentMapping fm = new FragmentMapping();
		fm.setId("FM1");
		
		Operator op1 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op1.setId("OP1");
		Operator op2 = OperatorBuilder.a2a(op1, lhsA1, rhsA1);
		op2.setId("OP2");
		Operator op3 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op3.setId("OP3");
		
		// fm.setOperators(asSet(op1, op2, op3));
		fm.setOperators(asSet(op1, op3));
		
		XMLFragmentMapping xmlFM = 
			XMLFragmentMapping.getInstance(fm, resolverFac);
		// marshall fragment mapping
		String xml3 = marshaller.marshall(xmlFM);
				
		// xmlFM should look like this
		String controlXml3 =
		"<fragment-mapping id=\"FM1\">" +
	    "<operator id=\"*\" name=\"C2C\" sim=\"-1.0\">" +
	        "<lhs-role name=\"lhsFocusElement\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	                "xsi:type=\"class\">*</element>" +
	       " </lhs-role>" +
	        "<rhs-role name=\"rhsFocusElement\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	                "xsi:type=\"class\">*</element>" +
	        "</rhs-role>" +
	    "</operator>" +
	    "<operator id=\"*\" name=\"C2C\" sim=\"-1.0\">" +
	    	"<children>*</children>" +
	        "<lhs-role name=\"lhsFocusElement\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	                "xsi:type=\"class\">*</element>" +
	        "</lhs-role>" +
	        "<rhs-role name=\"rhsFocusElement\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	                "xsi:type=\"class\">*</element>" +
	        "</rhs-role>" +
	    "</operator>" +
	    "<operator id=\"*\" name=\"A2A\" sim=\"-1.0\" parents=\"*\">" +
	        "<rhs-role name=\"rhsFocusAttribute\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	                "xsi:type=\"attribute\">*</element>" +
	        "</rhs-role>" +
	        "<lhs-role name=\"lhsFocusAttribute\">" +
	            "<element " +
	                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
	                " xsi:type=\"attribute\">*</element>" +
	        "</lhs-role>" +
	    "</operator>" +
	    "</fragment-mapping>";
		
		System.out.println(xml3);
		
		ElementAttributeWildcardDiff diff3 = new ElementAttributeWildcardDiff(controlXml3, xml3);
		assertTrue(diff3.similar());
		
		
		// lhsFocusElement of the 2 C2Cs is either C1 or C2
		assertXpathNodeSetsEqual(
		"//class[name=\"C1\"]/@id | //class[name=\"C2\"]/@id", xml, 
		"//operator[@name = \"C2C\"]/lhs-role[@name=\"lhsFocusElement\"]/element", 
		xml3
		);
		
		// rhsFocusElement of the 2 C2Cs is either C1 or C2
		assertXpathNodeSetsEqual(
		"//class[name=\"C1\"]/@id | //class[name=\"C2\"]/@id", xml2, 
		"//operator[@name = \"C2C\"]/rhs-role[@name=\"rhsFocusElement\"]/element", 
		xml3);
		 
		// lhsFocusElement of the A2A is a1
		assertXpathValuesEqual(
		"//class/attribute[name=\"a1\"]/@id", xml, 
		"//operator[@name = \"A2A\"]/lhs-role[@name=\"lhsFocusAttribute\"]/element",
		xml3);
		
		// rhsFocusElement of the A2A is a1
		assertXpathValuesEqual(
		"//class/attribute[name=\"a1\"]/@id", xml2, 
		"//operator[@name = \"A2A\"]/rhs-role[@name=\"rhsFocusAttribute\"]/element", 
		xml3);
	}
	
	
	@Test
	public void testUnmarshallingOfSimpleFragmentMapping() {
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		
		String marshalledFrag = 
			"<fragment id=\"F1\">" +
			"<class id=\"C2\">" +
				"<name>C2</name>" +
				"<attribute id=\"A3\">" +
					"<name>a3</name>" +
				"</attribute>" +
				"<attribute id=\"A4\">" +
					"<name>a4</name>" +
				"</attribute>" +
				"<reference id=\"R2\" target=\"C1\">" +
					"<name>r2</name>" +
				"</reference>" +
			"</class>" +
		    "<class id=\"C1\">" +
		        "<name>C1</name>" +
		        "<attribute id=\"A1\">" +
		            "<name>a1</name>" +
		        "</attribute>" +
		        "<attribute id=\"A2\">" +
		            "<name>a2</name>" +
		        "</attribute>" +
		        "<reference id=\"R1\" target=\"C2\">" +
		            "<name>r1</name>" +
		       "</reference>" +
		    "</class>" +
		    "</fragment>";
		
		XMLFragment xmlFrag = (XMLFragment) unmarshaller
					.unmarshall(new StringReader(marshalledFrag));
		
		String marshalledFM =
		"<fragment-mapping id=\"FM10\">\n" + 
		"    <operator id=\"OP3\" name=\"C2C\" sim=\"-1.0\">\n" + 
		"        <lhs-role name=\"lhsFocusElement\">\n" + 
		"            <element\n" + 
		"            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">C1</element>\n" + 
		"        </lhs-role>\n" + 
		"        <rhs-role name=\"rhsFocusElement\">\n" + 
		"            <element\n" + 
		"                xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">C1</element>\n" + 
		"        </rhs-role>\n" + 
		"    </operator>\n" + 
		"</fragment-mapping>";
		
		XMLFragmentMapping xmlFM = (XMLFragmentMapping) unmarshaller
								.unmarshall(new StringReader(marshalledFM));
		
		assertEquals(1, xmlFM.getFlattenedOperators().size());
		
		assertEquals(1, xmlFM.getOperators().size()); 
		
		Map<String,XMLElement> c2cRhsRoles = xmlFM.getOperators()
								.iterator().next().getRhsRoles();
		assertEquals(1, c2cRhsRoles.size());
		Entry<String,XMLElement> rhsRole = c2cRhsRoles.entrySet().iterator().next();
		assertEquals( "rhsFocusElement", rhsRole.getKey());
		assertEquals("C1", rhsRole.getValue().getName());
		
		Map<String,XMLElement> c2cLhsRoles = xmlFM.getOperators()
								.iterator().next().getLhsRoles();
		assertEquals(1, c2cLhsRoles.size());
		Entry<String,XMLElement> lhsRole = c2cLhsRoles.entrySet().iterator().next();
		assertEquals( "lhsFocusElement", lhsRole.getKey());
		assertEquals("C1", lhsRole.getValue().getName());
		
		// same XMLElementClass instance
		assertTrue(lhsRole.getValue() == rhsRole.getValue());
	}
	
	
	@Test
	public void testUnmarshallingOfComplexFragmentMapping() {
		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		
		String marshalledFrag = 
			"<fragment id=\"F1\">" +
			"<class id=\"C2\">" +
				"<name>C2</name>" +
				"<attribute id=\"A3\">" +
					"<name>a3</name>" +
				"</attribute>" +
				"<attribute id=\"A4\">" +
					"<name>a4</name>" +
				"</attribute>" +
				"<reference id=\"R2\" target=\"C1\">" +
					"<name>r2</name>" +
				"</reference>" +
			"</class>" +
		    "<class id=\"C1\">" +
		        "<name>C1</name>" +
		        "<attribute id=\"A1\">" +
		            "<name>a1</name>" +
		        "</attribute>" +
		        "<attribute id=\"A2\">" +
		            "<name>a2</name>" +
		        "</attribute>" +
		        "<reference id=\"R1\" target=\"C2\">" +
		            "<name>r1</name>" +
		       "</reference>" +
		    "</class>" +
		    "</fragment>";
		
		XMLFragment xmlFrag = (XMLFragment) unmarshaller
					.unmarshall(new StringReader(marshalledFrag));
		
		String marshalledFM =
		"<fragment-mapping id=\"FM1\">\n" + 
		"    <operator id=\"OP1\" name=\"C2C\" sim=\"-1.0\">\n" + 
		"		<children>OP2</children>\n" +
		"        <lhs-role name=\"lhsFocusElement\">\n" + 
		"            <element\n" + 
		"            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">C1</element>\n" + 
		"        </lhs-role>\n" + 
		"        <rhs-role name=\"rhsFocusElement\">\n" + 
		"            <element\n" + 
		"                xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">C1</element>\n" + 
		"        </rhs-role>\n" + 
		"    </operator>\n" + 
		"    <operator id=\"OP2\" name=\"A2A\" sim=\"-1.0\" parents=\"OP1\">\n" + 
		"        <lhs-role name=\"lhsFocusElement\">\n" + 
		"            <element\n" + 
		"            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"attribute\">A1</element>\n" + 
		"        </lhs-role>\n" + 
		"        <rhs-role name=\"rhsFocusElement\">\n" + 
		"            <element\n" + 
		"                xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"attribute\">A1</element>\n" + 
		"        </rhs-role>\n" + 
		"    </operator>\n" + 
		"</fragment-mapping>";
		
		XMLFragmentMapping xmlFM = (XMLFragmentMapping) unmarshaller
								.unmarshall(new StringReader(marshalledFM));
		
		Set<XMLOperator> rootOps = xmlFM.getOperators();
		
		assertEquals(1, rootOps.size()); 
		
		Set<XMLOperator> flattenedOps = xmlFM.getFlattenedOperators();
		
		assertEquals(2, flattenedOps.size()); 
			
		List<XMLOperator> sOps = sortXmlOpsByName(flattenedOps);
		
		// C2C CHECKS
		XMLOperator c2c = sOps.get(1);
		Map<String,XMLElement> c2cRhsRoles = c2c.getRhsRoles();
		assertEquals(1, c2cRhsRoles.size());
		Entry<String,XMLElement> c2cRhsRole = c2cRhsRoles.entrySet().iterator().next();
		assertEquals( "rhsFocusElement", c2cRhsRole.getKey());
		assertEquals("C1", c2cRhsRole.getValue().getName());

		Map<String,XMLElement> c2cLhsRoles = c2c.getLhsRoles();
		assertEquals(1, c2cLhsRoles.size());
		Entry<String,XMLElement> c2cLhsRole = c2cLhsRoles.entrySet().iterator().next();
		assertEquals( "lhsFocusElement", c2cLhsRole.getKey());
		assertEquals("C1", c2cLhsRole.getValue().getName());
		
		// same XMLElementClass instance
		assertTrue(c2cLhsRole.getValue() == c2cRhsRole.getValue());
		
		// A2A checks
		XMLOperator a2a = sOps.get(0);
		Map<String,XMLElement> a2aRhsRoles = a2a.getRhsRoles();
		assertEquals(1, a2aRhsRoles.size());
		Entry<String,XMLElement> a2aRhsRole = a2aRhsRoles.entrySet().iterator().next();
		assertEquals( "rhsFocusElement", a2aRhsRole.getKey());
		assertEquals("a1", a2aRhsRole.getValue().getName());

		Map<String,XMLElement> a2aLhsRoles = a2a.getLhsRoles();
		assertEquals(1, a2aLhsRoles.size());
		Entry<String,XMLElement> a2aLhsRole = a2aLhsRoles.entrySet().iterator().next();
		assertEquals( "lhsFocusElement", a2aLhsRole.getKey());
		assertEquals("a1", a2aLhsRole.getValue().getName());
		
		// same XMLElementAttribute instance
		assertTrue(a2aLhsRole.getValue() == a2aRhsRole.getValue());
	}

}
