package com.big.tuwien.ModelManager.imodel;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;

/**
 * Represents an instance of a meta-model class.
 * @author alex
 *
 */
public class ClassInstance implements InstanceElement<EClass,EObject> {
	protected Element element;
	protected EClass metaModelElement;
	protected EObject instance;
	protected Set<ReferenceInstance> references = new HashSet<ReferenceInstance>();
	protected Set<ReferenceInstance> crossReferences = new HashSet<ReferenceInstance>();
	protected Set<AttributeInstance> attributes = new HashSet<AttributeInstance>();
	
	
	public ClassInstance(Element element, EClass metaModelElement,
			EObject instance) {
		super();
		this.element = element;
		this.metaModelElement = metaModelElement;
		this.instance = instance;
	}
	
	
	public ClassInstance(ClassElement ce, EObject instance) {
		super();
		this.element = ce.getRepresents();
		this.metaModelElement = ce.getEClassInstance();
		this.instance = instance;
	}


	public EObject getContainer() {
		// a class has no container object
		return null;
	}

	
	public Element getElement() {
		return element;
	}

	
	public EClass getMetaModelElement() {
		return metaModelElement;
	}
	
	
	public EObject getInstance() {
		return instance;
	}


	public String getQName() {
		return this.element.getFullPathName();
	}
	
	
	public ClassInstance getContainerInstance() {
		// a class instance has no container
		return null;
	}
	
	
	public void addReference(ReferenceInstance ri) {
		this.references.add(ri);
	}
	
	
	public void addCrossReference(ReferenceInstance ri) {
		this.crossReferences.add(ri);
	}
	
	
	public Set<ReferenceInstance> getReferences() {
		return this.references;
	}
	
	
	public Set<ReferenceInstance> getCrossReferences() {
		return this.crossReferences;
	}
	
	
	public void addAttribute(AttributeInstance ai) {
		this.attributes.add(ai);
	}
	
	
	public Set<AttributeInstance> getAttributes() {
		return this.attributes;
	}


	public String toString() {
		return 
		"ClassInstance :: " +
		" element: " + 
		(element == null ? "null" : element) + 
		", instance: " + 
		(instance == null ? "null" : instance) +
		", metaModelElement: " +
		(metaModelElement == null ? "null" : metaModelElement);
	}
	
}
