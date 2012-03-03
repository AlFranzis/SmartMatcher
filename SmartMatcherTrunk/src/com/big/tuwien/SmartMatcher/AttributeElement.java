package com.big.tuwien.SmartMatcher;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 28-Apr-2008 16:22:05
 */
public class AttributeElement extends EcoreElement {
	private static Logger logger = Logger.getLogger(AttributeElement.class);
	private EAttribute eAttributeInstance;
	private ClassElement containedIn;
	private EObject containingObject;

	
	public AttributeElement(Element e){
		super(e);
	}

	
	public ClassElement getContainedIn(){
		return containedIn;
	}

	
	public void setContainedIn(ClassElement containedIn){
		this.containedIn = containedIn;
	}

	
	@Override
	public EcoreElement getPointsTo() {
		// The attribute does not point to another class
		return null;
	}

	
	@Override
	public void setEcoreElementReference(EObject eObject) {
		if(eObject instanceof EAttribute) { 
			this.eAttributeInstance = (EAttribute)eObject;
		} else {
			logger.error("ERROR: " + eObject.toString() + " is no EAttribute");
		}
	}

	
	@Override
	public String getFullPathName() {
		String name = this.represents.getName();
		String container = this.containedIn.represents.getName();
		return container + "." + name;
	}


	
	@Override
	public EObject getEcoreElementReference() {
		return this.eAttributeInstance;
	}

	
	public EObject getContainingObject() {
		return containingObject;
	}
	
	
	@Override
	public EcoreElement copy(Element copy) {
		AttributeElement ref = new AttributeElement(copy);
		ref.containedIn = this.containedIn;
		ref.eAttributeInstance = this.eAttributeInstance;
		return ref;
	}
	
	
	public AttributeElement shallowCopy() {
		Element representedCopy = this.represents.shallowCopy();
		AttributeElement copy = new AttributeElement(representedCopy);
		representedCopy.setRepresentedBy(copy);
		copy.setEcoreElementReference(this.eAttributeInstance);
		copy.setEObjectsMap(this.getEObjectsMap());
		return copy;
	}

}
