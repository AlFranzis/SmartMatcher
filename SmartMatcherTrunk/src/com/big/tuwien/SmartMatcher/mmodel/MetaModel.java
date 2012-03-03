package com.big.tuwien.SmartMatcher.mmodel;

import java.util.List;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MetaModel {
	private List<Element> elements = new Vector<Element>();
	private List<Element> classes = new Vector<Element>();
	private List<Element> attributes = new Vector<Element>();
	private List<Element> references = new Vector<Element>();
	private BiMap<Element,Integer> elements2id = new HashBiMap<Element,Integer>();
	
	
	public MetaModel() {}
	
	
	/**
	 * Returns all classes contained in this meta-model.
	 * @return All classes.
	 */
	public List<Element> getClasses() {
		return this.classes;
	}
	
	
	/**
	 * Returns all attributes contained in this meta-model.
	 * @return All attributes.
	 */
	public List<Element> getAttributes() {
		return this.attributes;
	}
	
	
	/**
	 * Returns all references contained in this meta-model.
	 * @return All references.
	 */
	public List<Element> getReferences() {
		return this.references;
	}
	
	
	/**
	 * Sets the elements of the meta-model.
	 * @param elements Elements.
	 */
	public void setElements(List<Element> elements) {
		this.elements.addAll(elements);
		this.elements2id = new HashBiMap<Element,Integer>(elements.size());
		for(Element e : elements) {
			this.elements2id.put(e, e.getId());
		}
		
		this.classes = QueryUtil.filterExceptClasses(this.elements);
		this.attributes = QueryUtil.filterExceptAttributes(this.elements);
		this.references = QueryUtil.filterExceptReferences(this.elements);
	}
	
	
	/**
	 * Returns all elements (Classes, Attributes, References) contained in this meta-model.
	 * @return All elements
	 */
	public List<Element> getElements() {
		return elements;
	}
	
	
	/**
	 * Returns the first class that matches the given name.
	 * @param name Name of the searched class.
	 * @return Class-Element or null if no element with the given name exists.
	 */
	public Element getClassByName(String name) {
		for(Element clazz : classes) {
			if(clazz.getName().equals(name))
				return clazz;
		}
		return null;
	}
	
	
	/**
	 * Returns the first class that matches the given qualified name.
	 * @param name Qualified name of the searched class.
	 * @return Class-Element or null if no element with the given name exists.
	 */
	public Element getClassByQName(String qname) {
		for(Element clazz : classes) {
			if(clazz.getFullPathName().equals(qname))
				return clazz;
		}
		return null;
	}
	
	
	/**
	 * Returns the first attribute that matches the given name.
	 * @param name Name of the searched attribute.
	 * @return Attribute-Element or null if no element with the given name exists.
	 */
	public Element getAttributeByName(String name) {
		for(Element att : attributes) {
			if(att.getName().equals(name))
				return att;
		}
		return null;
	}
	
	
	/**
	 * Returns the first attribute that matches the given qualified name.
	 * @param name Qualified name of the searched attribute.
	 * @return Attribute-Element or null if no element with the given name exists.
	 */
	public Element getAttributeByQName(String qname) {
		for(Element att : attributes) {
			if(att.getFullPathName().equals(qname))
				return att;
		}
		return null;
	}
	
	
	/**
	 * Returns the first reference that matches the given name.
	 * @param name Name of the searched reference.
	 * @return Reference-Element or null if no element with the given name exists.
	 */
	public Element getReferenceByName(String name) {
		for(Element ref : references) {
			if(ref.getName().equals(name))
				return ref;
		}
		return null;
	}
	
	
	/**
	 * Returns the first reference that matches the given qualified name.
	 * @param name Qualified name of the searched reference.
	 * @return Reference-Element or null if no element with the given name exists.
	 */
	public Element getReferenceByQName(String qname) {
		for(Element ref : references) {
			if(ref.getFullPathName().equals(qname))
				return ref;
		}
		return null;
	}
	
	
	/**
	 * Returns the element with the given id;
	 * @param id
	 * @return
	 */
	public Element getElementById(int id) {
		for(Element e : elements) {
			if(e.getId() == id)
				return e;
		}
		return null;
	}
	
	public Element getElementByName(String fullName) {
		for(Element e : elements) {
			if(e.getFullPathName().equals(fullName)) {
				return e;
			}
		}
		return null;
	}
	
	
	/**
	 * Returns if an element with the given ID exists in this meta-model.
	 * @param id The searched ID.
	 * @return
	 */
	public boolean containsElementWithId(int id) {
		return elements2id.inverse().containsKey(id);
	}
	
	
	
	public boolean containsElement(Element e) {
		return elements2id.containsKey(e);
	}
	
	
	/**
	 * Returns a clone of this object.
	 */
	public MetaModel copy() {
		MetaModel clone = new MetaModel();
		clone.setElements(getElements());
		return clone;
	}
	
	
	public String toString() {
		return "MetaModel :: Classes : " + this.classes + 
				" , Attributes : " + this.attributes + 
				", References : " + this.references;
	}
	
}
