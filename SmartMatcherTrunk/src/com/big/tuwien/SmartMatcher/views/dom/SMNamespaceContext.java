package com.big.tuwien.SmartMatcher.views.dom;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class SMNamespaceContext implements NamespaceContext {
	private static SMNamespaceContext instance;
	
	private final static String PREFIX = "ext";
	private final static String NAMESPACE = "http://big.tuwien.ac.at/SmartMatcher";
	
	
	private SMNamespaceContext() {}
	
	
	public static SMNamespaceContext getInstance() {
		if(instance == null) {
			instance = new SMNamespaceContext();
		}
		return instance;
	}
	
	
    public String getNamespaceURI(String prefix) {
        if (prefix == null)
          throw new IllegalArgumentException("The prefix cannot be null.");
        
        if (prefix.equals(PREFIX))
            return NAMESPACE;
        else
            return null;
    }
    
    
    public String getPrefix(String namespace) {
        if (namespace == null)
          throw new IllegalArgumentException("The namespace uri cannot be null.");
        if (namespace.equals(NAMESPACE))
          return PREFIX;
        else
          return null;
    }
    
    
    public String getPrefix() {
    	return PREFIX;
    }

    
    public Iterator<String> getPrefixes(String namespace) {
        return null;
    }
}

