package com.big.tuwien.SmartMatcher.views.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EReference;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import sm_mm.Operator;

public class EMFOperatorBuilder {
	public static final String ROLE_ANNOTATION_SOURCE = "role";
	public static final String ROLE_ANNOTATION_ROLENAME_DETAIL = "rolename";
	
	private GenericEMFFactory of;
	private BiMap<Element,sm_mm.Element> elements = new HashBiMap<Element,sm_mm.Element>();
	
	
	public EMFOperatorBuilder(GenericEMFFactory of, Map<Element,sm_mm.Element> elements) {
		this.of = of;
		this.elements.putAll(elements);
	}
	
	
	public Operator build(Bubble b) {
		Operator op = of.createOperatorInstance(b.getOperatorName());
		
		Configuration<? extends com.big.tuwien.SmartMatcher.operators.Operator> c = b.getConfiguration();
		
		if(!c.isComplete()) throw new IllegalArgumentException("Incomplete configuration");
		
		Map rolesMap = c.getRoles();
		Set<Map.Entry> eSet = rolesMap.entrySet();
		for(Map.Entry<Role<?>,Element> en : eSet) {
			String rolename = en.getKey().getName();
			Element e = en.getValue();
			
			sm_mm.Element emfE = elements.get(e);
			setRole(op, rolename, emfE);
		}
		
		checkAllRolesSet(op);
		
		return op;
	}
		
		
	/*
	 * Sets a role for the given EMF operator
	 */
	private void setRole(sm_mm.Operator op, String role, sm_mm.Element e) {
		List<EReference> refs = op.eClass().getEAllReferences();
		for(EReference ref : refs) {
			EAnnotation roleAnnotation = ref.getEAnnotation(ROLE_ANNOTATION_SOURCE);
			if(roleAnnotation == null) continue;

			Map<String,String> details = roleAnnotation.getDetails().map();
			String rolename = details.get(ROLE_ANNOTATION_ROLENAME_DETAIL);
			if(rolename.equals(role)) {
				op.eSet(ref, e);
				break;
			}
		}
	}
	
	
	private void checkAllRolesSet(Operator op) {
		Map<String,sm_mm.Element> roles = getRoles(op);
		for(Map.Entry<String, sm_mm.Element> en : roles.entrySet()) {
			String rolename = en.getKey();
			sm_mm.Element e = en.getValue();
			
			if(e == null) throw new IllegalArgumentException("Role " + rolename + " not set in the built operator " + op);
		}
	}
	
	
	/*
	 * Returns a role map (rolename -> element) for the given EMF operator. 
	 */
	private Map<String, sm_mm.Element> getRoles(sm_mm.Operator op) {
		Map<String, sm_mm.Element> rolesMap = new HashMap<String, sm_mm.Element>();
		
		List<EReference> refs = op.eClass().getEAllReferences();
		for(EReference ref : refs) {
			EAnnotation roleAnnotation = ref.getEAnnotation(ROLE_ANNOTATION_SOURCE);
			if(roleAnnotation == null) continue;
			
			Map<String,String> details = roleAnnotation.getDetails().map();
			String rolename = details.get(ROLE_ANNOTATION_ROLENAME_DETAIL);
			sm_mm.Element e = (sm_mm.Element) op.eGet(ref);
			rolesMap.put(rolename, e);	
		}
		return rolesMap;
	}
}
