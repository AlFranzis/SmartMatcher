package com.big.tuwien.SmartMatcher;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 28-Apr-2008 16:22:16
 */
public class ReferenceElement extends EcoreElement {
	private static Logger logger = Logger.getLogger(ReferenceElement.class);
	private EReference eReferenceInstance;
	public ClassElement pointsTo;
	public ClassElement containedIn;

	
	public ReferenceElement(Element e){
		super(e);
	}

	
	public ClassElement getPointsTo(){
		return pointsTo;
	}

	
	public void setPointsTo(ClassElement pointsTo){
		this.pointsTo = pointsTo;
	}

	
	public ClassElement getContainedIn(){
		return containedIn;
	}

	
	public void setContainedIn(ClassElement containedIn){
		this.containedIn = containedIn;
	}

	
	@Override
	public void setEcoreElementReference(EObject eObject) {
		if(eObject instanceof EReference) {
			this.eReferenceInstance = (EReference) eObject;
		} else {
			logger.error(eObject.toString() + " is no EReference");
		}
		
	}

	
	public EReference getEReferenceInstance(){
		return eReferenceInstance;
	}

	
	@Override
	public String getFullPathName() {
		String container = this.containedIn.represents.getName();
		String referencedElement = this.pointsTo.represents.getName();
		String name = this.represents.getName();
		return container + "_" + name + "_" + referencedElement;
	}


	@Override
	public EObject getEcoreElementReference() {
		return this.getEReferenceInstance();
	}


	@Override
	public EcoreElement copy(Element copy) {
		ReferenceElement ref = new ReferenceElement(copy);
		ref.containedIn = this.containedIn;
		ref.pointsTo = this.pointsTo;
		ref.eReferenceInstance = this.eReferenceInstance;
		return ref;
	}
	
	
	public ReferenceElement shallowCopy() {
		Element representedCopy = this.represents.shallowCopy();
		ReferenceElement copy = new ReferenceElement(representedCopy);
		representedCopy.setRepresentedBy(copy);
		copy.setEcoreElementReference(this.eReferenceInstance);
		copy.setEObjectsMap(this.getEObjectsMap());
		return copy;
	}

}
