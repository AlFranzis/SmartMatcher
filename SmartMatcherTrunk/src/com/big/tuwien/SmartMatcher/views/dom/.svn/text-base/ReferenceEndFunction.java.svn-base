package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public class ReferenceEndFunction implements XPathExtensionFunction {
	protected DOMView view;
	
	
	@Override
	public void setDOMView(DOMView view) {
		this.view = view;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate(List args) throws XPathFunctionException {
		if(args.size() != 2)
			throw new IllegalArgumentException("ReferenceEnd-Function: Needs 2 arguments: element, reference");
		
		Element classElement = castToElement(args.get(0));
		Element refElement = castToElement(args.get(1));
		return referenceEnd(classElement, refElement);
	}
	
	
	/*
	 * Checks if the given class is an endpoint of the given reference
	 */
	private boolean referenceEnd(Element clazz, Element ref) {
		ReferenceElement _ref = (ReferenceElement) ref.getRepresentedBy();
		if( _ref.getContainedIn().getRepresents().equals(clazz) 
				|| _ref.getPointsTo().getRepresents().equals(clazz))
			return true;
		else
			return false;
	}
	
	
	protected Element castToElement(Object arg) { 
		Element e;
		if(arg instanceof Element) {
			e = (Element) arg;
		} else if(arg instanceof Number) {
			e = this.view.getBridge().getElementForNodeId(((Number) arg).intValue());
		} else if(arg instanceof DTMNodeList) {
			DTMNodeList nodes = (DTMNodeList) arg;
			if( nodes.getLength() < 1)
				throw new IllegalArgumentException("ReferenceEnd-Function: Argument of type Node is empty");
			else {
				assert nodes.getLength() == 1 : "ReferenceEnd-Function: argument NodeList has size > 1";
				Node n = nodes.item(0);
				e = this.view.getBridge().getElementForNode(n);
			}
		} else {
			throw new IllegalArgumentException("ReferenceEnd-Function: Argument must be of type Node, Element or Integer");
		}
		
		return e;
	}

}
