package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.big.tuwien.SmartMatcher.Element;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public class MappedInRoleFunction implements XPathExtensionFunction {
	protected DOMView view;
	
	
	@Override
	public void setDOMView(DOMView view) {
		this.view = view;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate(List args) throws XPathFunctionException {
		if(args.size() != 2)
			throw new IllegalArgumentException("MappedInRole-Function: Needs 2 arguments: element, role");
		
		int elementId = castElementToNodeId(args.get(0));
		String roleName = (String) args.get(1);
		
		return mappedInRole(elementId, roleName);
	}
	
	
	/*
	 * Checks if a mapping exists in the DOMView which contains the given role.
	 */
	private boolean mappedInRole(int elementId, String roleName) {
		org.w3c.dom.Element root = this.view.getDOM().getDocumentElement();
		NodeList mappings = root.getElementsByTagName(DOMView.MAPPING);
		
		for(int i = 0; i < mappings.getLength(); i++) {
			org.w3c.dom.Element mapping = (org.w3c.dom.Element) mappings.item(i);

			NodeList roles = mapping.getElementsByTagName(DOMView.ROLE);

			for(int j = 0; j < roles.getLength(); j++) {
				org.w3c.dom.Element role = (org.w3c.dom.Element) roles.item(j);
				Integer _elementId = new Integer(role.getAttribute("element"));
				String _roleName = role.getAttribute("name");
				
				if(_elementId.equals(elementId) && _roleName.equals(roleName))
					return true;
			}
		}
		
		// no mapping with a matching role
		return false;
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
				throw new IllegalArgumentException("MappedInRole-Function: Argument of type Node is empty");
			else {
				assert nodes.getLength() == 1 : "MappedInRole-Function: argument NodeList has size > 1";
				Node n = nodes.item(0);
				id = view.getBridge().getNodeIdForElement(view.getBridge().getElementForNode(n));
			}
		} else {
			throw new IllegalArgumentException("MappedInRole-Function: Argument must be of type Node, Element or Integer");
		}
		
		return id;
	}

}
