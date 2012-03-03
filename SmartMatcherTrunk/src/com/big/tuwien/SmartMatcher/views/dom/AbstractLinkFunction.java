package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import javax.xml.xpath.XPathFunctionException;

import org.w3c.dom.Node;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public abstract class AbstractLinkFunction implements XPathExtensionFunction {
	protected DOMView view;

	
	public AbstractLinkFunction() {}

	
	public void setDOMView(DOMView view) {
		this.view = view;	
	}

	
	@SuppressWarnings("unchecked")
	public Object evaluate(List args) throws XPathFunctionException {
		if(args == null)
			throw new IllegalArgumentException("Link-Function: Arguments must not be null");
		
		if(args.size() != 3)
			throw new IllegalArgumentException("Link-Function: 3 arguments (source, target, maxLength) needed");
		
		Element src = castToClassElement(args.get(0));
		Element target = castToClassElement(args.get(1));
		
		// source or target is not a meta-model element
		if(src == null || target == null)
			return false;
		else {
			Integer maxLength = ((Number) args.get(2)).intValue();
	
			if(maxLength <= 0) 
				throw new IllegalArgumentException("Link-Function: Max length argument must be greater 0");
	
			return linked(src, target, maxLength);
		}
	}
	
	
	protected abstract boolean linked(Element src, Element target, Integer maxLength);
	

	protected Element castToClassElement(Object arg) {
		Element e = null; 
		Integer id;
		if(arg instanceof Element) {
			e = (Element) arg;
		} else if(arg instanceof Number) {
			id = ((Number) arg).intValue();
			e = this.view.getBridge().getElementForNodeId(id);
		} else if(arg instanceof DTMNodeList) {
			DTMNodeList nodes = (DTMNodeList) arg;
			if( nodes.getLength() < 1)
				throw new IllegalArgumentException("Link-Function: Argument of type Node is empty");
			else {
				assert nodes.getLength() == 1 : "Link-Function: argument NodeList has size > 1";
				Node n = nodes.item(0);
				e = view.getBridge().getElementForNode(n);
			}
		} else {
			throw new IllegalArgumentException("Link-Function: Argument must be of type Node, Element or Integer");
		}
		
		// only class elements are considered
		if( e != null && !(e.getRepresentedBy() instanceof ClassElement) )
			e = null;
		
		return e;
	}

}