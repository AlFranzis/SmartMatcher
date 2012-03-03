package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public class UnmappedFunction implements XPathExtensionFunction {
	protected DOMView view;
	
	
	@Override
	public void setDOMView(DOMView view) {
		this.view = view;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate(List args) throws XPathFunctionException {
		if(args.size() != 1)
			throw new IllegalArgumentException("Unmapped-Function: Needs 1 arguments: element");
		
		int elementId = castElementToNodeId(args.get(0));
		
		return unmapped(elementId);
	}
	
	
	/*
	 * Checks if a mapping exists in the DOMView which contains the given role.
	 */
	private boolean unmapped(int elementId) {
		org.w3c.dom.Element root = this.view.getDOM().getDocumentElement();
		NodeList mappings = root.getElementsByTagName(DOMView.MAPPING);
		
		for(int i = 0; i < mappings.getLength(); i++) {
			org.w3c.dom.Element mapping = (org.w3c.dom.Element) mappings.item(i);
			
			// mappings that are eval2False are of no interest
			if(mapping.getAttribute(DOMView.MAPPING_STATE).equals(Bubble.STATE.eval2FALSE.toString())) continue;

			NodeList roles = mapping.getElementsByTagName(DOMView.ROLE);

			for(int j = 0; j < roles.getLength(); j++) {
				org.w3c.dom.Element role = (org.w3c.dom.Element) roles.item(j);
				Integer _elementId = new Integer(role.getAttribute("element"));
				if(_elementId.equals(elementId) )
					return false;
			}
		}
		
		// no mapping exists that contains the element
		return true;
	}
	
	
	protected int castElementToNodeId(Object arg) { 
		Integer id;
		if(arg instanceof Element) {
			id = view.getBridge().getNodeIdForElement((Element) arg);
		} else if(arg instanceof Number) {
			id = ((Number) arg).intValue();
		} else if(arg instanceof DTMNodeList) {
			DTMNodeList nodes = (DTMNodeList) arg;
			if( nodes.getLength() < 1)
				throw new IllegalArgumentException("Unmapped-Function: Argument of type Node is empty");
			else {
				assert nodes.getLength() == 1 : "Unmapped-Function: argument NodeList has size > 1";
				Node n = nodes.item(0);
				id = view.getBridge().getNodeIdForElement(view.getBridge().getElementForNode(n));
			}
		} else {
			throw new IllegalArgumentException("Unmapped-Function: Argument must be of type Node, Element or Integer");
		}
		
		return id;
	}

}
