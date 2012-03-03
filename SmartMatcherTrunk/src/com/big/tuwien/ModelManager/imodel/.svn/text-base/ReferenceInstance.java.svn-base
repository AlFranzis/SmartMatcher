package com.big.tuwien.ModelManager.imodel;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.big.tuwien.SmartMatcher.Element;

/**
 * Represents an instance of a meta-model reference.
 * @author alex
 *
 */
public class ReferenceInstance implements InstanceElement<EReference,EObject> {
	protected Element element;
	protected EReference metaModelElement;
	protected EObject instance;
	protected EObject container;
	protected ClassInstance containerInstance;
	protected ClassInstance pointedToInstance;
	
	
	public ReferenceInstance(Element element, EReference metaModelElement,
			EObject instance, EObject container) {
		super();
		this.element = element;
		this.metaModelElement = metaModelElement;
		this.instance = instance;
		this.container = container;
	}


	@Override
	public EObject getContainer() {
		return this.container;
	}
	
	
	public ClassInstance getContainerInstance() {
		return this.containerInstance;
	}
	
	
	public void setContainerInstance(ClassInstance classInstance) {
		this.containerInstance = classInstance;
	}
	
	
	public ClassInstance getPointedToInstance() {
		return pointedToInstance;
	}


	public void setPointedToInstance(ClassInstance pointedToInstance) {
		this.pointedToInstance = pointedToInstance;
	}
	
		
	@Override
	public Element getElement() {
		return this.element;
	}

	
	@Override
	public EReference getMetaModelElement() {
		return this.metaModelElement;
	}

	
	@Override
	public EObject getInstance() {
		return this.instance;
	}

	
	@Override
	public String getQName() {
		return this.element.getFullPathName();
	}

	
	public String toString() {
		return 
		"ReferenceInstance :: " +
		" element: " + 
		(element == null ? "null" : element) + 
		", instance: " + 
		(instance == null ? "null" : instance) +
		", metaModelElement: " +
		(metaModelElement == null ? "null" : metaModelElement) + 
		", container: " +
		(container == null ? "null" : container);
	}

}
