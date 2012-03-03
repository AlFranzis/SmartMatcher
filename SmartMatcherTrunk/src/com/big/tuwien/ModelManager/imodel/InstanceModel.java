package com.big.tuwien.ModelManager.imodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InstanceModel {
	protected Map<String, Set<ClassInstance>> classes = new HashMap<String,Set<ClassInstance>>();
	protected Map<String, Set<AttributeInstance>> attributes = new HashMap<String,Set<AttributeInstance>>();
	protected Map<String, Set<ReferenceInstance>> references = new HashMap<String,Set<ReferenceInstance>>();
	
	
	public InstanceModel() {}
	
	
	public Collection<ClassInstance> getClassInstances() {
		Set<ClassInstance> _classes = new HashSet<ClassInstance>();
		for(String qn : classes.keySet()) {
			_classes.addAll(classes.get(qn));
		}
		return _classes;
	}
	
	
	public Collection<AttributeInstance> getAttributeInstances() {
		Set<AttributeInstance> _attributes = new HashSet<AttributeInstance>();
		for(String qn : attributes.keySet()) {
			_attributes.addAll(attributes.get(qn));
		}
		return _attributes;
	}
	
	
	public Collection<ReferenceInstance> getReferenceInstances() {
		Set<ReferenceInstance> _references = new HashSet<ReferenceInstance>();
		for(String qn : references.keySet()) {
			_references.addAll(references.get(qn));
		}
		return _references;
	}
	
	
	public Set<ClassInstance> getClassInstances(String qClassName) {
		if(!classes.containsKey(qClassName)) return Collections.emptySet();
		return classes.get(qClassName);
	}
	
	
	public Set<AttributeInstance> getAttributeInstances(String qAttributeName) {
		if(!attributes.containsKey(qAttributeName)) return Collections.emptySet();
		return attributes.get(qAttributeName);
	}
	
	
	public Set<ReferenceInstance> getReferenceInstances(String qReferenceName) {
		if(!references.containsKey(qReferenceName)) return Collections.emptySet();
		return references.get(qReferenceName);
	}
	
	
	public void addClassInstance(ClassInstance ci) {
		String qname = ci.getQName();
		Set<ClassInstance> _classes = classes.get(qname);
		if(_classes == null) _classes = new HashSet<ClassInstance>();
		_classes.add(ci);
		classes.put(qname,_classes);
	}
	
	
	public void addAttributeInstance(AttributeInstance ai) {
		String qname = ai.getQName();
		Set<AttributeInstance> _attributes = attributes.get(qname);
		if(_attributes == null) _attributes = new HashSet<AttributeInstance>();
		_attributes.add(ai);
		attributes.put(qname,_attributes);
	}
	
	
	public void addReferenceInstance(ReferenceInstance ri) {
		String qname = ri.getQName();
		Set<ReferenceInstance> _references = references.get(qname);
		if(_references == null) _references = new HashSet<ReferenceInstance>();
		_references.add(ri);
		references.put(qname,_references);
	}
	
}
