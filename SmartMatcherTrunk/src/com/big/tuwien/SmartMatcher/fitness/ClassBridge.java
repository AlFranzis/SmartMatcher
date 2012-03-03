package com.big.tuwien.SmartMatcher.fitness;

import org.eclipse.emf.ecore.EClass;

import com.big.tuwien.SmartMatcher.Element;

public class ClassBridge implements Bridge<EClass,Element> {
	private static ClassBridge instance;
	
	
	private ClassBridge() {}
	
	
	public static ClassBridge getInstance() {
		if(instance == null)
			instance = new ClassBridge();
		
		return instance;
	}
	
	
	@Override
	public boolean equals(EClass mmElement, Element iElement) {
		return mmElement.getName().equals(iElement.getFullPathName());
	}
	
}
