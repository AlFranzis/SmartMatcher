package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.isAttribute;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.isClass;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.isRef;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ElementDecoratorFactory {
	
	
	public static Set<Element> decorate(Set<Element> es) {
		Map<Element,ElementDecorator> decMap = decoratorMap(es);
		
		Set<ElementDecorator> decs = 
						new HashSet<ElementDecorator>(decMap.values());
		
		// filter class decorators
		Set<Element> classDecs = new HashSet<Element>(
			Collections2.filter(decs, new Predicate<ElementDecorator>() {
				@Override
				public boolean apply(ElementDecorator dec) {
					return dec instanceof ClassDecorator;
				}
		}));
		
		return classDecs;
	}
	
	
	public static Map<Element,ElementDecorator> decoratorMap(Set<Element> es) {
		// contains all elements and their associated decorator
		Map<Element,ElementDecorator> decMap = 
							new HashMap<Element,ElementDecorator>();
		
		// map that contains classes as keys and the the contained
		// elements (attributes/references) as values
		Map<Element,Set<Element>> strucMap = structure(es); 
		
		for(Element clazz : strucMap.keySet()) {
			// class decorator could have been already created implicitly
			// due to reference linkage
			if(!decMap.containsKey(clazz))
				classDec(clazz, strucMap, decMap);
		}
		
		return decMap;
	}
	
	
	private static ClassDecorator classDec(Element clazz, 
					final Map<Element,Set<Element>> strucMap, 
							Map<Element,ElementDecorator> decMap) {	
		ClassDecorator cDec = new ClassDecorator(clazz);
		decMap.put(clazz, cDec);
		
		for(Element child : strucMap.get(clazz)) {
			if(isAttribute(child)) {
				AttributeDecorator aDec = new AttributeDecorator(
							(ClassElement) cDec.getRepresentedBy(), child);
				cDec.addVisibleAttribute(aDec);
				decMap.put(child, aDec);
			} else if(isRef(child)) {
				Element pointsTo = child.getRepresentedBy()
								.getPointsTo().getRepresents();
				
				ClassDecorator pointsToDec;
				// reference target decorator exists
				if(decMap.containsKey(pointsTo)) {
					pointsToDec = (ClassDecorator) decMap.get(pointsTo);
				// if reference target class decorator does not exist
				// -> create it implicitly
				} else {
					pointsToDec = classDec(pointsTo, strucMap, decMap);
				}
				
				ReferenceDecorator rDec = new ReferenceDecorator(
						(ClassElement) cDec.getRepresentedBy(), 
						(ClassElement) pointsToDec.getRepresentedBy(), 
						child);
				cDec.addVisibleRef(rDec);
				decMap.put(child, rDec);
			} else {
				throw new ReuseRuntimeException(
						"Unexpected element type: " + child);
			}	
		}	
		
		return cDec;
	}
	
	
	/*
	 * Builds a structure map from a given set of elements
	 * A structure map is a map whose keys are classes
	 * The values of the map are the contained elements 
	 * (attributes/references) of the key class.
	 */
	private static Map<Element,Set<Element>> structure(Set<Element> es) {
		Map<Element,Set<Element>> strucMap = new HashMap<Element, Set<Element>>();
		
		for(Element e : es) {
			// class element
			if(isClass(e)) {
				if(!strucMap.containsKey(e)) {
					strucMap.put(e, new HashSet<Element>());
				}
			// child element
			} else {
				Element containedIn = e.getRepresentedBy().getContainedIn().getRepresents();
				if(strucMap.containsKey(containedIn)) {
					strucMap.get(containedIn).add(e);
				} else {
					Set<Element> children = new HashSet<Element>();
					children.add(e);
					strucMap.put(containedIn, children);
				}
			}
		}
		
		return strucMap;
	}
}
