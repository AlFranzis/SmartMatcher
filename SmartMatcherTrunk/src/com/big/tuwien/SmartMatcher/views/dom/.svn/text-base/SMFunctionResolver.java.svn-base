package com.big.tuwien.SmartMatcher.views.dom;


import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionResolver;

/**
 * The SMFunctionResolver resolves call to the XPath extension functions defined 
 * and used in the DOMView of the Smart Matcher project.
 * @author alex
 *
 */
public class SMFunctionResolver implements XPathFunctionResolver {
	/*
	 * Manages the registered extension functions.
	 */
	private Map<QName,XPathFunction> functions = new HashMap<QName,XPathFunction>();
	
	
	/**
	 * This method resolves the XPath extension function used and defined by
	 * the Smart Matcher project.
	 */
	public XPathFunction resolveFunction(QName fname, int arity) {
	  if (fname == null)
	    throw new NullPointerException("The function name cannot be null.");
	  
	  
	  //SMNamespaceContext context = SMNamespaceContext.getInstance();
	  //String prefix = context.getPrefix();
	  
	  XPathFunction f = functions.get(fname);
	  return f;
	  
	  
//	  if (fname.equals(new QName(context.getNamespaceURI(prefix), "myAdditionFunction", prefix)))
//	    /** 
//	     * Return a customized implementation of XPathFunction. We need
//	     * to implement the evaluate(List) method.
//	     */
//	    return new XPathFunction() {
//	      /**
//	       * The actual implementation of the extension function.
//	       * Just cast two arguments to Double and add them together.
//	       */
//	      public Object evaluate(List args) {
//	        if (args.size() == 2) {
//	          Double arg1 = (Double)args.get(0);
//	          Double arg2 = (Double)args.get(1);
//	          return new Double(arg1.doubleValue() + arg2.doubleValue());
//	        }
//	        else
//	          return null;
//	      }
//	    };
//	  else
//	    return null;
	}
	
	
	/**
	 * Registers a new XPath extension function.
	 * @param name Qualified name of the function.
	 * @param function Function instance which implements the {@link XPathFunction}-Interface.
	 */
	public void addExtensionFunction(QName name, XPathFunction function) {
		functions.put(name, function);
	}
}

