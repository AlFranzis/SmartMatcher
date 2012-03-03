package com.big.tuwien.SmartMatcher.views.dom;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

import com.big.tuwien.SmartMatcher.Element;

/**
 * This class implements an XPath query on the DOMView.
 * @author alex
 *
 */
public class Query {
	public final static String LINK_FUNCTION_NAME = "link";
	public final static String DLINK_FUNCTION_NAME = "dlink";
	public final static String MAPPED_FUNCTION_NAME = "mapped";
	public final static String UNMAPPED_FUNCTION_NAME = "unmapped";
	public final static String MAPPEDINROLE_FUNCTION_NAME = "role";
	public final static String REFERENCEEND_FUNCTION_NAME = "refend";
	
	public enum RESULT_TYPE { NODE_LIST };
 	
	private String xPathExp;
	private XPathFactory xPathFactory;
	private QueryVariableResolver resolver = new QueryVariableResolver();
	private DOMView view;
	
	
	public Query() {}
	
	
	public Query(String xPathExp) {
		this();
		this.xPathExp = xPathExp;
	}
	
	
	public void setXPathExpression(String exp) {
		this.xPathExp = exp;
	}
	
	public Query setParameter(String name, String value) {
		this.resolver.addBinding(name, value);
		return this;
	}
	
	
	public Query setParameter(String name, Integer value) {
		this.resolver.addBinding(name, value);
		return this;
	}
	
	
	public Query setParameter(String name, Double value) {
		this.resolver.addBinding(name, value);
		return this;
	}
	
	
	public Query setParameter(String name, Element value) {
		this.resolver.addBinding(name, value);
		return this;
	}
	
	
	public Query setElement(String name, Element e) {
		int elementId = this.view.getBridge().getNodeIdForElement(e);
		this.resolver.addBinding(name, elementId);
		return this;
	}
	
	public void setDomView(DOMView view) {
		this.view = view;
		initContext();
	}
	
	
	protected void initContext() {
		this.xPathFactory = XPathFactory.newInstance();
		
		SMNamespaceContext ncontext = SMNamespaceContext.getInstance();
		SMFunctionResolver fresolver = new SMFunctionResolver();
		
		registerExtensionFunctions(ncontext, fresolver);
		
		this.xPathFactory.setXPathFunctionResolver(fresolver);
	}
	
	
	public NodeList execute() throws XPathExpressionException {
		XPath xpath = this.xPathFactory.newXPath();
		SMNamespaceContext ncontext = SMNamespaceContext.getInstance();
		
		xpath.setNamespaceContext(ncontext);
		xpath.setXPathVariableResolver(resolver);
		
		XPathExpression expression = xpath.compile(xPathExp);

		return (NodeList) expression.evaluate(this.view.getDOM(),XPathConstants.NODESET);
	}
	
	
	/**
	 * Executes the query and returns the query result as a list of meta-model 
	 * elements ({@link Element}).
	 * @param rtype The result type of the query.
	 * @return List of elements.
	 * @throws XPathExpressionException
	 */
	public List<Element> execute(RESULT_TYPE rtype) throws XPathExpressionException {
		//TODO: Implement method for other result types
		assert rtype.equals(RESULT_TYPE.NODE_LIST) : "Unsupported result type";
		
		XPath xpath = this.xPathFactory.newXPath();
		SMNamespaceContext ncontext = SMNamespaceContext.getInstance();
		
		xpath.setNamespaceContext(ncontext);
		xpath.setXPathVariableResolver(resolver);
		
		XPathExpression expression = xpath.compile(xPathExp);

		NodeList nlist = (NodeList) expression.evaluate(this.view.getDOM(),XPathConstants.NODESET);
		
		return this.view.getBridge().getElements(nlist);
	}
	
	
	public Element uniqueResult() throws XPathExpressionException {
		XPath xpath = this.xPathFactory.newXPath();
		SMNamespaceContext ncontext = SMNamespaceContext.getInstance();
		
		xpath.setNamespaceContext(ncontext);
		xpath.setXPathVariableResolver(resolver);
		
		XPathExpression expression = xpath.compile(xPathExp);

		NodeList nlist = (NodeList) expression.evaluate(this.view.getDOM(),XPathConstants.NODESET);
		
		if(nlist.getLength() == 0)
			return null;
		else
			return this.view.getBridge().getElements(nlist).get(0);
	}
	
	
	/*
	 * Registers the extension functions, so they can be used
	 * in the XPath queries.
	 */
	private void registerExtensionFunctions(SMNamespaceContext ncontext, SMFunctionResolver fresolver) {
		String prefix = ncontext.getPrefix();
		
		// add link function
		QName linkFctName = new QName(ncontext.getNamespaceURI(prefix), LINK_FUNCTION_NAME, prefix);
		LinkFunction linkFunction = new LinkFunction();
		linkFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(linkFctName, linkFunction);
		
		// add dlink function
		QName dlinkFctName = new QName(ncontext.getNamespaceURI(prefix), DLINK_FUNCTION_NAME, prefix);
		DLinkFunction dlinkFunction = new DLinkFunction();
		dlinkFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(dlinkFctName, dlinkFunction);
		
		// add mapped function
		QName mappedFctName = new QName(ncontext.getNamespaceURI(prefix), MAPPED_FUNCTION_NAME, prefix);
		MappedFunction mappedFunction = new MappedFunction();
		mappedFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(mappedFctName, mappedFunction);
		
		// add unmapped function
		QName unmappedFctName = new QName(ncontext.getNamespaceURI(prefix), UNMAPPED_FUNCTION_NAME, prefix);
		UnmappedFunction unmappedFunction = new UnmappedFunction();
		unmappedFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(unmappedFctName, unmappedFunction);
		
		// add role function
		QName mappedInRoleFctName = new QName(ncontext.getNamespaceURI(prefix), MAPPEDINROLE_FUNCTION_NAME, prefix);
		MappedInRoleFunction mappedInRoleFunction = new MappedInRoleFunction();
		mappedInRoleFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(mappedInRoleFctName, mappedInRoleFunction);
		
		// add refend function
		QName refEndFctName = new QName(ncontext.getNamespaceURI(prefix), REFERENCEEND_FUNCTION_NAME, prefix);
		ReferenceEndFunction refEndFunction = new ReferenceEndFunction();
		refEndFunction.setDOMView(this.view);
		fresolver.addExtensionFunction(refEndFctName, refEndFunction);
	}
	
}
