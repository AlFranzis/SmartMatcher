package com.big.tuwien.SmartMatcher.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class ReferenceEntry {
	private EReference metamodelElement;
	private EObject container;
	private EObject target;
	
	
	public EObject getTarget() {
		return target;
	}


	public void setTarget(EObject target) {
		this.target = target;
	}


	public EObject getContainer() {
		return container;
	}


	public void setContainer(EObject container) {
		this.container = container;
	}


	
	
	public EReference getMetamodelElement() {
		return metamodelElement;
	}
	
	
	public void setMetamodelElement(EReference metamodelElement) {
		this.metamodelElement = metamodelElement;
	}
}
