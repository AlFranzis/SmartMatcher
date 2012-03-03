package com.big.tuwien.SmartMatcher;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 28-Apr-2008 16:22:07
 */
public class ClassElement extends EcoreElement {
	private static Logger logger = Logger.getLogger(ClassElement.class);
	private EClass eClassInstance;
	private Vector<AttributeElement> myAttributes;
	private Vector<ReferenceElement> myReferences;
	private Vector<ReferenceElement> referencedBy;

	
	public ClassElement(Element e) {
		super(e);
		this.myAttributes = new Vector<AttributeElement>();
		this.myReferences = new Vector<ReferenceElement>();
		this.referencedBy = new Vector<ReferenceElement>();
	}
	
	
	public Vector<ReferenceElement> getReferencedByReferences(){
		return this.referencedBy;
	}

	
	@Override
	public ClassElement getContainedIn() {
		// The class is not contained in another class
		return null;
	}

	
	@Override
	public EcoreElement getPointsTo() {
		// This class does not point to another class
		return null;
	}

	
	@Override
	public void setEcoreElementReference(EObject eObject) {
		if(eObject instanceof EClass) {
			this.eClassInstance = (EClass)eObject;
		} else {
			logger.error(eObject.toString() + " is no EClass");
		}
		
	}

	
	public EClass getEClassInstance() {
		return eClassInstance;
	}
	
	
	public List<ReferenceElement> getReferences() {
	    return myReferences;
	}
	
	
	public List<AttributeElement> getAttributes() {
	    return myAttributes;
	}

	
	@Override
	public String getFullPathName() {
		return this.represents.getName();
	}


	@Override
	public EObject getEcoreElementReference() {
		return this.getEClassInstance();
	}


	@Override
	public EcoreElement copy(Element copy) {
		return null;
	}


	public ClassElement shallowCopy() {
		Element representedCopy = this.represents.shallowCopy();
		ClassElement copy = new ClassElement(representedCopy);
		representedCopy.setRepresentedBy(copy);
		copy.setEcoreElementReference(this.eClassInstance);
		copy.setEObjectsMap(this.getEObjectsMap());
		return copy;
	}
	
}