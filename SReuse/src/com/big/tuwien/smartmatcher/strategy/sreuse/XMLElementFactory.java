package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
// DEPRECATED and not used anymore
//public class XMLElementFactory {
//	
//	
//	public static Set<XMLClassElement> getInstances2(Collection<Element> classes) {
//		return getInstance(Helpers.cast2(new ArrayList<Element>(classes), ClassElement.class));
//	}
//	
//	
//	public static Set<XMLClassElement> getInstance(Collection<ClassElement> classes) {
//		Map<ClassElement,XMLClassElement> cache = new HashMap<ClassElement, XMLClassElement>();
//		for(ClassElement c : classes) {
//			if(!cache.containsKey(c))
//				createClass(cache, c);
//		}
//		return new HashSet<XMLClassElement>(cache.values());
//	}
//	
//	
//	public static XMLAttributeElement getInstance(AttributeElement a) {
//		XMLAttributeElement wrapped = new XMLAttributeElement();
//		wrapped.setName(a.getRepresents().getName());
//		return wrapped;
//	}
//	
//	
//	private static void createClass(Map<ClassElement,XMLClassElement> cache, ClassElement c) {
//		XMLClassElement wrapped = new XMLClassElement();
//		wrapped.setName(c.getRepresents().getName());
//		
//		Set<XMLAttributeElement> attributes = new HashSet<XMLAttributeElement>();
//		for(AttributeElement a : c.getAttributes()) {
//			attributes.add(getInstance(a));
//		}
//		wrapped.setAttributes(attributes);
//		
//		cache.put(c, wrapped);
//		
//		Set<XMLReferenceElement> references = new HashSet<XMLReferenceElement>();
//		for(ReferenceElement r : c.getReferences()) {
//			
//			ClassElement pointsTo = r.getPointsTo();
//			
//			if(!cache.containsKey(pointsTo))
//				createClass(cache, pointsTo);	
//			
//			
//			XMLReferenceElement xmlRef = createReference(cache, r);
//			references.add(xmlRef);
//
//		}
//		wrapped.setReferences(references);
//	}
//	
//	
//	private static XMLReferenceElement createReference(Map<ClassElement, XMLClassElement> cache, ReferenceElement r) {
//		XMLReferenceElement wrapped = new XMLReferenceElement();
//		
//		wrapped.setName(r.getRepresents().getName());
//		
//		ClassElement container = r.getContainedIn();
//		if(!cache.containsKey(container))
//			throw new ReuseRuntimeException();
//		
//		XMLClassElement xmlContainer = cache.get(container);
//		wrapped.setContainedIn(xmlContainer);
//		
//		ClassElement pointsTo = r.getPointsTo();
//		if(!cache.containsKey(pointsTo))
//			throw new ReuseRuntimeException();
//		
//		XMLClassElement xmlPointsTo = cache.get(pointsTo);
//		wrapped.setPointsTo(xmlPointsTo);
//		
//		return wrapped;
//	}
//
//}
