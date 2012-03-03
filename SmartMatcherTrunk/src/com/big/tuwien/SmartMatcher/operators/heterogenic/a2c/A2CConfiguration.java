package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

/**
 * 
 * @author alex
 *
 */
public class A2CConfiguration implements Configuration<A2C> {
	/**
	 * Roles defined for the A2C-Operator
	 */
	public static class Roles {
		public static final Role<A2C> lhsFocusAttribute = new A2CRole("lhsFocusAttribute");
		public static final Role<A2C> rhsFocusClass = new A2CRole("rhsFocusClass");
		public static final Role<A2C> rhsContextAttribute = new A2CRole("rhsContextAttribute");
		public static final Role<A2C> rhsContextReference = new A2CRole("rhsContextReference");
	}
	
	protected Map<Role<A2C>,Element> roles = new HashMap<Role<A2C>, Element>();
	protected Map<Role<A2C>,Set<Element>> blacklisted = new HashMap<Role<A2C>,Set<Element>>();
	protected C2CConfiguration context;
	
	
	public A2CConfiguration() {}
	
	
	public A2CConfiguration(Map<Role<A2C>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<A2C> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<A2C>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<A2C> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<A2C>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public Map<Role<A2C>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<A2C> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<A2C> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<A2C> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<A2C>,Set<Element>> getBlacklistMap() {
		return blacklisted;
	}
	

	public Collection<Element> getElements() {
		return this.roles.values();
	}
	
	
	public boolean isUnderspecified() {
		return this.roles.isEmpty();
	}
	
	
	/**
	 * Returns if the configuration is complete, e.g. if all
	 * roles are set.
	 * @return
	 */
	public boolean isComplete() {
		return this.roles.get(Roles.lhsFocusAttribute) != null
			&& this.roles.get(Roles.rhsContextAttribute) != null
			&& this.roles.get(Roles.rhsContextReference) != null
			&& this.roles.get(Roles.rhsFocusClass) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusAttribute();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusClass();
	}
	
	
	public boolean fixedRHSContextAttribute() {
		return this.roles.get(Roles.rhsContextAttribute) != null;
	}
	
	
	public boolean fixedRHSContextReference() {
		return this.roles.get(Roles.rhsContextReference) != null;
	}
	
	
	public boolean fixedRHSFocusClass() {
		return this.roles.get(Roles.rhsFocusClass) != null;
	}
	
	
	public boolean fixedContext() {
		return this.context != null;
	}
	
	
	public boolean fixedLHSFocusAttribute() {
		return this.roles.get(Roles.lhsFocusAttribute) != null;
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("A2C-Configuration: "); 
		for(Entry<Role<A2C>,Element> e : this.roles.entrySet()) {
			if(!first)
				buf.append(", ");
			Element el = e.getValue();
			buf.append("{Role: " + e.getKey() + " , " + el + "}");
			first = false;
		}
		return buf.toString();
	}


	public String getOperatorName() {
		return "A2C";
	}


	public C2CConfiguration getContext() {
		return context;
	}


	public void setContext(C2CConfiguration context) {
		this.context = context;
	}
	
	
	public Set<Element> getLHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.lhsFocusAttribute));
		return elements;
	}


	public Set<Element> getRHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.rhsFocusClass));
		elements.add(this.roles.get(Roles.rhsContextAttribute));
		elements.add(this.roles.get(Roles.rhsContextReference));
		return elements;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		
		if(!(other instanceof A2CConfiguration)) return false;
		A2CConfiguration that = (A2CConfiguration) other;
		
		if(this.context == null && that.context == null) {
			return this.roles.equals(that.roles);
		} 
		
		return this.roles.equals(that.roles) && this.context.equals(that.context);
	}
	
	
	public int hashCode() {
		return 873383 * A2CConfiguration.class.hashCode() + 
		(this.roles == null ? 984395 : this.roles.hashCode()) + 
		(this.context == null ? 56347 : context.hashCode());
	}
	
}
