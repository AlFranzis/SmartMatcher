package com.big.tuwien.SmartMatcher.views.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EReference;

import sm_mm.Operator;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleFactory;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BubbleBuilder {
	public static final String ROLE_ANNOTATION_SOURCE = "role";
	public static final String ROLE_ANNOTATION_ROLENAME_DETAIL = "rolename";
	
	private BubbleFactory bf;
	private BiMap<Element,sm_mm.Element> elements = new HashBiMap<Element,sm_mm.Element>();
	
	
	public BubbleBuilder(BubbleFactory bf, Map<Element,sm_mm.Element> elements) {
		this.bf = bf;
		this.elements.putAll(elements);
	}
	
	
	public Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator> build(Operator op) {
		checkAllRolesSet(op);
		
		Configuration c = new C2CConfiguration();
		copyRoles(op, c);
		
		if (!c.isComplete()) 
			throw new IllegalArgumentException("Not all roles of the configuration can be set");
	
		Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator> bubble = bf.createBubbleInstance(C2C.class);
		bubble.setConfiguration(c);
		
		return bubble;
	}
	
	
	/*
	 * Copies the role from an EMF operator into a bubble configuration
	 */
	private void copyRoles(Operator op, Configuration<?> c) {
		// add all roles of the EMF operator to the bubble configuration
		Map<String,sm_mm.Element> roles = getRoles(op);
		for(Map.Entry<String, sm_mm.Element> en : roles.entrySet()) {
			String rolename = en.getKey();
			sm_mm.Element e = en.getValue();
			
			c.setRole(new Role(rolename), elements.inverse().get(e));
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
	
	
	private void checkAllRolesSet(Operator op) {
		Map<String,sm_mm.Element> roles = getRoles(op);
		for(Map.Entry<String, sm_mm.Element> en : roles.entrySet()) {
			String rolename = en.getKey();
			sm_mm.Element e = en.getValue();
			
			if(e == null) throw new IllegalArgumentException("Role " + rolename + " not set in the given operator " + op);
		}
	}
		
		
	
}
