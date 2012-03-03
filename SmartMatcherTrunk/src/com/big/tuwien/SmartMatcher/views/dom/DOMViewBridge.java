package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;

public class DOMViewBridge {
	protected DOMView view;
	
	
	public DOMViewBridge(DOMView view) {
		this.view = view;
	}
	
	
	/**
	 * Returns all elements associated with the DOMView-nodes given
	 * in the node-set.
	 * @param it NodeSet. Typically the result of a XPath-Query.
	 * @return
	 */
	public List<Element> getElements(NodeIterator it) {
		List<Element> elements = new Vector<Element>();
		
		Node n;
		while((n = it.nextNode()) != null) {
			elements.add(getElementForNode(n));
		}
		
		return elements;
	}
	
	
	/**
	 * Returns all elements associated with the DOMView-nodes given
	 * in the node list.
	 * @param it NodeList. Typically the result of a XPath-Query.
	 * @return
	 */
	public List<Element> getElements(NodeList nlist) {
		List<Element> elements = new Vector<Element>();
		
		for (int i = 0; i < nlist.getLength(); i++) {
			Node n = nlist.item(i);
			elements.add(getElementForNode(n));
		}
	
		return elements;
	}
	
	
	/**
	 * Returns the element associated with the given DOMView-node.
	 * @param n
	 * @return
	 */
	public Element getElementForNode(Node n) {
		if(n == null || n.getNodeType() != Node.ELEMENT_NODE) {
			throw new IllegalArgumentException("Given parameter node is null or not an element node: " + n);
		}
		NamedNodeMap attributes = n.getAttributes();
		Node idAttributeNode = attributes.getNamedItem(DOMView.ID);
		
		if(idAttributeNode == null) {
			return null;
		} else {
			int id = Integer.parseInt(idAttributeNode.getNodeValue());
			Element e = view.getElement(id);
			return e;
		}
	}
	
	
	public Integer getNodeIdForElement(Element e) {
		return this.view.getElementId(e);
	}
	
	
	public Integer getNodeIdForElement(EcoreElement e) {
		return this.view.getElementId(e.getRepresents());
	}
	
	
	public Element getElementForNodeId(int id) {
		return this.view.getElement(id);
	}
}
