package com.big.tuwien.SmartMatcher.fitness;

import org.eclipse.emf.ecore.EAttribute;

import com.big.tuwien.SmartMatcher.Element;

public class AttributeBridge implements Bridge<EAttribute,Element> {
	private static AttributeBridge instance;
	
	
	private AttributeBridge() {}
	
	
	public static AttributeBridge getInstance() {
		if(instance == null)
			instance = new AttributeBridge();
		
		return instance;
	}
	
	
	@Override
	public boolean equals(EAttribute mmElement, Element element) {
		String mmAttributeName = mmElement.getContainerClass().getName()
								+ mmElement.getName();
		
		return mmAttributeName.equals(element.getFullPathName());
	}

	

}
