package com.big.tuwien.SmartMatcher.model;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class AttributeEntry {
	private List<EObject> amount = new Vector<EObject>();
	private EAttribute metamodelElement;
	private Object value;
	private EObject container;
	
	
	public EObject getContainer() {
		return container;
	}


	public void setContainer(EObject container) {
		this.container = container;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public List<EObject> getAmount() {
		return amount;
	}
	
	
	public void setAmount(List<EObject> amount) {
		this.amount = amount;
	}
	
	
	public void addAmount(EObject eObject) {
		this.amount.add(eObject);
	}
	
	
	public EAttribute getMetamodelElement() {
		return metamodelElement;
	}
	
	
	public void setMetamodelElement(EAttribute metamodelElement) {
		this.metamodelElement = metamodelElement;
	}
}
