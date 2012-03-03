package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.ElementNameQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.custommonkey.xmlunit.exceptions.XpathException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLUnitHelpers {

	public static class AttributeWildcardDifferenceListener implements DifferenceListener{
		public static String DEFAULT_WILDCARD = "*";
		private String wildcard = DEFAULT_WILDCARD;
		
		
		public AttributeWildcardDifferenceListener() {}
		
		
		public AttributeWildcardDifferenceListener(String wildcard) {
			this.wildcard = wildcard;
		}
		
		
		@Override
		public int differenceFound(Difference diff) {
			// Different attribute value
			if(diff.getId() == 3) {
				if(diff.getControlNodeDetail().getValue().equals(wildcard)) {
					// ignore difference
					return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
				}
				
			}
			
			// accept difference
			return 0;
		}
	
		@Override
		public void skippedComparison(Node control, Node test) {}
	}

	
	public static class ElementWildcardDifferenceListener implements DifferenceListener{
		public static String DEFAULT_WILDCARD = "*";
		private String wildcard = DEFAULT_WILDCARD;
		
		
		public ElementWildcardDifferenceListener() {}
		
		
		public ElementWildcardDifferenceListener(String wildcard) {
			this.wildcard = wildcard;
		}
		
		
		@Override
		public int differenceFound(Difference diff) {
			// Different element text value
			if(diff.getId() == 14) {
				if(diff.getControlNodeDetail().getValue().equals(wildcard)) {
					// ignore difference
					return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
				}
				
			}
			
			// accept difference
			return 0;
		}
	
		@Override
		public void skippedComparison(Node control, Node test) {}
	}
	
	
	public static class ElementAttributeWildcardDifferenceListener implements DifferenceListener{
		public static String DEFAULT_WILDCARD = "*";
		private String wildcard = DEFAULT_WILDCARD;
		
		
		public ElementAttributeWildcardDifferenceListener() {}
		
		
		public ElementAttributeWildcardDifferenceListener(String wildcard) {
			this.wildcard = wildcard;
		}
		
		
		@Override
		public int differenceFound(Difference diff) {
			// Different element text value
			if(diff.getId() == 14) {
				if(diff.getControlNodeDetail().getValue().equals(wildcard)) {
					// ignore difference
					return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
				} 
			// Different attribute value	
			} else if(diff.getId() == 3) {
				if(diff.getControlNodeDetail().getValue().equals(wildcard)) {
					// ignore difference
					return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
				}
			}
			
			// accept difference
			return 0;
		}
	
		@Override
		public void skippedComparison(Node control, Node test) {}
	}

	
	public static class ExtElementQualifier extends ElementNameQualifier {
		private String idElement;
		private List<String> elements;
		
		
		public ExtElementQualifier(List<String> elements, String idElement) {
			this.idElement = idElement;
			this.elements = elements;
		}
		
		
		@Override
		public boolean qualifyForComparison(org.w3c.dom.Element control, 
												org.w3c.dom.Element test) {
			if(!elements.contains(control.getTagName()) || 
					!elements.contains(test.getTagName())) {
				return super.qualifyForComparison(control, test);
			}
		
			org.w3c.dom.Element idElement1 = childNode(control, idElement);
			org.w3c.dom.Element idElement2 = childNode(test, idElement);
			
			if(idElement1 == null || idElement2 == null) 
				return super.qualifyForComparison(control, test);
			
			String idElement1Value = idElement1.getTextContent();
			String idElement2Value = idElement2.getTextContent();
			
			return idElement1Value.equals(idElement2Value);
		}
		
		
		private org.w3c.dom.Element childNode(org.w3c.dom.Element e, 
														String name) {
			NodeList nl = e.getChildNodes();
			for(int i = 0; i < nl.getLength(); i++) {
				Node child = nl.item(i);
				if(child.getNodeName().equals("name"))
					return (org.w3c.dom.Element) child;
				
			}
			return null;
		}
	
	}
	
	
	public static class ExtElementNameAndAttributeQualifier extends ElementNameAndAttributeQualifier {
		public ExtElementNameAndAttributeQualifier(String att) {
			super(att);
		}
		
		public ExtElementNameAndAttributeQualifier(String[] atts) {
			super(atts);
		}
		
		@Override
		public boolean qualifyForComparison(org.w3c.dom.Element control, 
												org.w3c.dom.Element test) {
			if(super.qualifyForComparison(control, test)) {
				
				// same number of child nodes ?
				int childs1 = control.getChildNodes().getLength();
				int childs2 = test.getChildNodes().getLength();
				return childs1 == childs2;
			}
			
			return false;
		}
		
	}

	
	public static class MyDiff extends Diff {
		public MyDiff(String control, String test) throws SAXException,
								IOException, ParserConfigurationException {
			super(parse(control), parse(test));
			overrideElementQualifier(
					new RecursiveElementNameAndTextQualifier());
		}
		
		
		private static void removeWhitespaceNodes(org.w3c.dom.Element e) {
			NodeList children = e.getChildNodes();
			for (int i = children.getLength() - 1; i >= 0; i--) {
				Node child = children.item(i);
				if (child instanceof Text && 
					((Text) child).getData().trim().length() == 0) {
					e.removeChild(child);
				}
				else if (child instanceof org.w3c.dom.Element) {
					removeWhitespaceNodes((org.w3c.dom.Element) child);
				}
			}
		}
		
		
		private static Document parse(String xml) 
				throws SAXException, IOException, 
								ParserConfigurationException  {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setIgnoringElementContentWhitespace(true);
			// dbf.setFeature(
			//"http://apache.org/xml/features/dom/include-ignorable-whitespace", 
			//	false);
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new ByteArrayInputStream(xml.getBytes()));
			removeWhitespaceNodes((org.w3c.dom.Element)doc.getFirstChild());
			
			return doc;
		}
	}

	
	public static class AttWildcardIdElementDiff extends MyDiff {
	
		public AttWildcardIdElementDiff(String control, String test) throws SAXException,
				IOException, ParserConfigurationException {
			super(control, test);
			overrideElementQualifier(
					new ExtElementQualifier(
							asList("class", "attribute", "reference"),
							"name"));
			overrideDifferenceListener(
					new AttributeWildcardDifferenceListener());
		}
		
	}

	
	public static class ElementWildcardDiff extends MyDiff {
	
		public ElementWildcardDiff(String control, String test) throws SAXException,
				IOException, ParserConfigurationException {
			super(control, test);
			overrideDifferenceListener(
					new ElementWildcardDifferenceListener());
			overrideElementQualifier(
					new ElementNameAndAttributeQualifier());
		}
		
	}
	
	
	public static class ElementAttributeWildcardDiff extends MyDiff {
		
		public ElementAttributeWildcardDiff(String control, String test) throws SAXException,
				IOException, ParserConfigurationException {
			super(control, test);
			overrideDifferenceListener(
					new ElementAttributeWildcardDifferenceListener());
			overrideElementQualifier(
					new ExtElementNameAndAttributeQualifier("name"));
		}
		
	}

	
	public static void assertXpathNodeSetsEqual(String xpath1, String xml1, 
						String xpath2, String xml2) 
						throws XpathException, SAXException, IOException {
		 XpathEngine xpath = XMLUnit.newXpathEngine();
		 NodeList result = xpath.getMatchingNodes(
				 xpath1, 
				 XMLUnit.buildControlDocument(xml1));
		 
		 NodeList result2 = xpath.getMatchingNodes(
				 xpath2, 
				 XMLUnit.buildTestDocument(xml2));
		 
		 Set<String> textNodes1 = new HashSet<String>();
		 for(int i = 0; i < result.getLength(); i++) {
			 Node n = result.item(i);
			textNodes1.add(n.getTextContent());
		 }
		 
		 Set<String> textNodes2 = new HashSet<String>();
		 for(int i = 0; i < result2.getLength(); i++) {
			 Node n = result2.item(i);
			 textNodes2.add(n.getTextContent());
		 }
		 
		 assertEquals(textNodes1, textNodes2);
	}

}
