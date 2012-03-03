package com.big.tuwien.SmartMatcher.views.dom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.big.tuwien.SmartMatcher.Element;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public class MappedFunction implements XPathExtensionFunction {
	protected DOMView view;
	
	
	@Override
	public void setDOMView(DOMView view) {
		this.view = view;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate(List args) throws XPathFunctionException {
		if(args.size() < 3)
			throw new IllegalArgumentException("Mapped-Function: Needs at least 3 arguments: operator, element, role");
		if( (args.size() % 2) == 0)	// even number of arguments
			throw new IllegalArgumentException("Mapped-Function: Wrong number of arguments");
		
		String operator = (String) args.get(0);
		
		Map<Integer,String> roles = new HashMap<Integer,String>();
		for(int i = 1; i < args.size(); i = i + 2) {
			int elementNodeId = castElementToNodeId(args.get(i));
			String role = (String) args.get(i + 1);
			roles.put(elementNodeId, role);
		}
		
		return mappingExists(operator, roles);
	}
	
	
	/*
	 * Checks if a mapping exists in the DOMView which fulfills the specified argument constraints.
	 * operator ... Mapping operator
	 * elementRoles ... Constraint map which includes the elementIds (Integer) of the elements and 
	 * the roles (String) associated with these elements in the mapping. 
	 */
	private boolean mappingExists(String operator, Map<Integer,String> elementRoles) {
		org.w3c.dom.Element root = this.view.getDOM().getDocumentElement();
		NodeList mappings = root.getElementsByTagName(DOMView.MAPPING);
		
		for(int i = 0; i < mappings.getLength(); i++) {
			org.w3c.dom.Element mapping = (org.w3c.dom.Element) mappings.item(i);

			if(mapping.getAttribute(DOMView.OP).equals(operator)) {
				NodeList roles = mapping.getElementsByTagName(DOMView.ROLE);

				// store elements and their roles in the mapping in map _roles
				Map<Integer,String> _roles = new HashMap<Integer,String>();
				for(int j = 0; j < roles.getLength(); j++) {
					org.w3c.dom.Element role = (org.w3c.dom.Element) roles.item(j);
					_roles.put(new Integer(role.getAttribute("element")),role.getAttribute("name"));
				}

				// check if _roles fulfills all constraints giben in elementRoles
				boolean fulfillsContraints =  _roles.entrySet().containsAll(elementRoles.entrySet());
				if(fulfillsContraints) return true;
			}
		}
		
		// no mapping fulfilled the constraints
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
				throw new IllegalArgumentException("Mapped-Function: Argument of type Node is empty");
			else {
				assert nodes.getLength() == 1 : "Mapped-Function: argument NodeList has size > 1";
				Node n = nodes.item(0);
				id = view.getBridge().getNodeIdForElement(view.getBridge().getElementForNode(n));
			}
		} else {
			throw new IllegalArgumentException("Mapped-Function: Argument must be of type Node, Element or Integer");
		}
		
		return id;
	}

}
