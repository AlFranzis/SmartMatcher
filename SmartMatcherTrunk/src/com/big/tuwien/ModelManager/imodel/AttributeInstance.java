package com.big.tuwien.ModelManager.imodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.SmartMatcher.Element;

/**
 * Represents an instance of a meta-model attribute.
 * @author alex
 *
 */
public class AttributeInstance implements InstanceElement<EAttribute,Object> {
	protected Element element;
	protected EAttribute metaModelElement;
	protected Object instance;
	protected EObject container;
	protected ClassInstance containerInstance;
	
	
	public AttributeInstance(Element element, EAttribute metaModelElement,
			Object instance, EObject container) {
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

	
	@Override
	public Element getElement() {
		return this.element;
	}

	
	@Override
	public EAttribute getMetaModelElement() {
		return this.metaModelElement;
	}

	
	@Override
	public Object getInstance() {
		return this.instance;
	}

	@Override
	public String getQName() {
		return this.element.getFullPathName();
	}
	
	
	public String toString() {
		return 
		"AttributeInstance :: " +
		" element: " + 
		(element == null ? "null" : element) + 
		", instance: " + 
		(instance == null ? "null" : instance) +
		", metaModelElement: " +
		(metaModelElement == null ? "null" : metaModelElement) + 
		", container: " +
		(container == null ? "null" : container);
	}


	public ClassInstance getContainerInstance() {
		return containerInstance;
	}


	public void setContainerInstance(ClassInstance containerInstance) {
		this.containerInstance = containerInstance;
	}

}
