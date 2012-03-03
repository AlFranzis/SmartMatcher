package com.big.tuwien.SmartMatcher.fitness;

import org.eclipse.emf.ecore.EReference;

import com.big.tuwien.SmartMatcher.Element;

public class ReferenceBridge implements Bridge<EReference,Element> {
	private static ReferenceBridge instance;
	
	
	private ReferenceBridge() {}
	
	
	public static ReferenceBridge getInstance() {
		if(instance == null)
			instance = new ReferenceBridge();
		
		return instance;
	}
	
	
	@Override
	public boolean equals(EReference mmElement, Element element) {
		String mmReferenceName = 
			mmElement.getContainerClass().getName() 
			+"_" + mmElement.getName() 
			+ "_" + mmElement.getEReferenceType().getName();
		
		return mmReferenceName.equals(element.getFullPathName());
	}

	

}
