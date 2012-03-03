package com.big.tuwien.transformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;


public class NewNewTransformationHistory {
	private Map<Configuration<?>,Transformation> transformations = new HashMap<Configuration<?>,Transformation>();
	private Map<Element,Transformation> elements = new HashMap<Element,Transformation>();
	private Map<InstanceElement<?,?>,Transformation> objects = new HashMap<InstanceElement<?,?>,Transformation>();
	
	
	public void trace(Transformation t) {
		Configuration<?> c = t.getConfig();
		
		transformations.put(t.getConfig(),t);
		
		Set<Element> lhsElements = c.getLHSElements();
		for(Element e : lhsElements) {
			elements.put(e, t);
		}
		
		Set<Element> rhsElements = c.getRHSElements();
		for(Element e : rhsElements) {
			elements.put(e, t);
		}
		
		Set<InstanceElement<?,?>> lhsObjs = t.getLHSObjects();
		for(InstanceElement<?,?> e : lhsObjs) {
			objects.put(e, t);
		}
		
		Set<InstanceElement<?,?>> rhsObjs = t.getRHSObjects();
		for(InstanceElement<?,?> e : rhsObjs) {
			objects.put(e, t);
		}
	}
	
	
	public boolean isTransformed(Configuration<?> c) {
		return transformations.containsKey(c);
	}
	
	
	public Transformation getTransformation(Configuration<?> c) {
		return transformations.get(c);
	}
	
	
	public Transformation getTransformation(Element e) {
		return elements.get(e);	
	}
	
	
	public Transformation getTransformation(InstanceElement<?,?> ie) {
		return objects.get(ie);
	}
	
	
}
