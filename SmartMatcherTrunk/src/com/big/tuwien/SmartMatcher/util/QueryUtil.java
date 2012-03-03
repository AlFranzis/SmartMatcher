package com.big.tuwien.SmartMatcher.util;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

/**
 * Convenient helper methods.
 * @author alex 
 *
 */
public class QueryUtil {
	private static Logger logger = Logger.getLogger(QueryUtil.class);
	
	/**
	 * Returns all ClassElements that are referenced by the given
	 * ClassElement.
	 * @param clazz The inspected ClassElement.
	 * @return All referenced ClassElements.
	 */
	public static List<Element> getReferenced(Element clazz) {
		List<Element> referenced = new Vector<Element>();
		
		if(! (clazz.getRepresentedBy() instanceof ClassElement)) {
			throw new IllegalArgumentException("Illegal argument. Not a class: " + clazz);	
		} else {
			ClassElement ce = (ClassElement) clazz.getRepresentedBy();
			List<ReferenceElement> refs = ce.getReferences();
			for(ReferenceElement ref : refs) {
				referenced.add(ref.getPointsTo().getRepresents());
			}
		}
		
		return referenced;
	}
	
	
	/**
	 * Filters all elements, except elements that are instances of
	 * the given class.
	 * @param elements List that should be filtered.
	 * @param clazz Instances of this class are not filtered.
	 * @return Filtered list.
	 */
	public static List<Element> filterExcept(List<Element> elements, Class<? extends EcoreElement> clazz) {
		List<Element> filtered = new Vector<Element>();
		
		for(Element e : elements) {
			if(clazz.isInstance(e.getRepresentedBy())) 
				filtered.add(e);
		}
		
		return filtered;
	}
	
	
	/**
	 * Filters all elements, except instances of {@link ClassElement}.
	 * @param elements List to filter.
	 * @return Filtered list.
	 */
	public static List<Element> filterExceptClasses(List<Element> elements) {
		return filterExcept(elements, ClassElement.class);
	}
	
	
	/**
	 * Filters all elements, except instances of {@link AttributeElement}.
	 * @param elements List to filter.
	 * @return Filtered list.
	 */
	public static List<Element> filterExceptAttributes(List<Element> elements) {
		return filterExcept(elements, AttributeElement.class);
	}
	
	
	/**
	 * Filters all elements, except instances of {@link ReferenceElement}.
	 * @param elements List to filter.
	 * @return Filtered list.
	 */
	public static List<Element> filterExceptReferences(List<Element> elements) {
		return filterExcept(elements, ReferenceElement.class);
	}
	
	
	public static Element getClassAttributeByName(Element clazz, String attribute) {
		List<AttributeElement> attributes = ((ClassElement) clazz.getRepresentedBy()).getAttributes();
		for(AttributeElement a : attributes) {
			if(a.getRepresents().getName().equals(attribute)) 
				return a.getRepresents();
		}
		return null;
	}
	
	
	public static Element getClassReferenceByName(Element clazz, String ref) {
		List<ReferenceElement> refs = ((ClassElement) clazz.getRepresentedBy()).getReferences();
		for(ReferenceElement r : refs) {
			if(r.getRepresents().getName().equals(ref)) 
				return r.getRepresents();
		}
		return null;
	}
	
	
	/**
	 * Prints the given list.
	 */ 
	public static String printElementList(Collection<Element> list) {
		Iterator<Element> listIt = list.iterator();
		String listString = "";
		while(listIt.hasNext()){
			Element elem = listIt.next();
			listString = listString + " " + elem.getFullPathName() +", ";
		}
		return listString;
	}
	
	
	
	public static String getStringRepresentation(NodeIterator it) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			Transformer serializer = TransformerFactory.newInstance().newTransformer();
			serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			serializer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			Node n;
			while( (n = it.nextNode()) != null) {
				serializer.transform(new DOMSource(n), new StreamResult(out));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return out.toString();
	}
	

	public static String printElementNames(List<Element> elements) {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		for(Element e : elements) {
			if(!first)
				buf.append(", ");
			buf.append(e.getName());
			first = false;
		}
		return buf.toString();
	}

}


