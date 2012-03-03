package com.big.tuwien.SmartMatcher.views.dom;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;

public class QueryVariableResolver implements XPathVariableResolver {
	private Map<String,Object> bindings = new HashMap<String,Object>();
	
    public Object resolveVariable(QName var) {
        if (var == null)
            throw new NullPointerException("The variable name cannot be null");
        
        String localPart = var.getLocalPart();
        if(localPart == null)
        	throw new NullPointerException("The local part of a variable name cannot be null");
        
        Object value = bindings.get(localPart);
        return value;
    }
    
    
    public void addBinding(String name, Object value) {
    	this.bindings.put(name, value);
    }
}

