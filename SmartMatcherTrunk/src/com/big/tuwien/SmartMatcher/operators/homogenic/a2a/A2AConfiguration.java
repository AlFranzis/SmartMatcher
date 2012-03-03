package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

/**
 * 
 * @author alex
 *
 */
public class A2AConfiguration implements HomogenicConfiguration<A2A> {
	/**
	 * Roles defined for the A2A-Operator
	 */
	public static class Roles {
		public static Role<A2A> lhsFocusAttribute = new A2ARole("lhsFocusAttribute");
		public static Role<A2A> rhsFocusAttribute = new A2ARole("rhsFocusAttribute");
		
	}

	protected Map<Role<A2A>,Element> roles = new HashMap<Role<A2A>, Element>();
	protected Map<Role<A2A>,Set<Element>> blacklisted = new HashMap<Role<A2A>,Set<Element>>();
	protected Set<A2AConfiguration> blacklistedConfigs = new HashSet<A2AConfiguration>();
	protected C2CConfiguration context;
	
	
	public A2AConfiguration() {}
	
	
	public A2AConfiguration(Map<Role<A2A>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<A2A> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<A2A>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<A2A> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setConfigurationBlacklist(Collection<A2AConfiguration> blacklisted) {
		this.blacklistedConfigs.addAll(blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<A2A>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public Map<Role<A2A>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<A2A> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<A2A> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<A2A> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<A2A>,Set<Element>> getBlacklistMap() {
		return blacklisted;
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
			&& this.roles.get(Roles.rhsFocusAttribute) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusAttribute();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusAttribute();
	}
	
	
	public boolean fixedRHSFocusAttribute() {
		return this.roles.get(Roles.rhsFocusAttribute) != null;
	}
	
	
	public boolean fixedLHSFocusAttribute() {
		return this.roles.get(Roles.lhsFocusAttribute) != null;
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("A2A-Configuration: "); 
		for(Entry<Role<A2A>,Element> e : this.roles.entrySet()) {
			if(!first)
				buf.append(", ");
			Element el = e.getValue();
			buf.append("{Role: " + e.getKey() + " , " + el + "}");
			first = false;
		}
		return buf.toString();
	}


	public String getOperatorName() {
		return "A2A";
	}


	public C2CConfiguration getContext() {
		return context;
	}


	public void setContext(C2CConfiguration context) {
		this.context = context;
	}


	public Element getLHSFocusElement() {
		return getRoleElement(Roles.lhsFocusAttribute);
	}


	public Element getRHSFocusElement() {
		return getRoleElement(Roles.rhsFocusAttribute);
	}


	public void setLHSFocusElement(Element e) {
		setRole(Roles.lhsFocusAttribute, e);	
	}


	public void setRHSFocusElement(Element e) {
		setRole(Roles.rhsFocusAttribute, e);
	}


	public Set<Element> getLHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(getLHSFocusElement());
		return elements;
	}


	public Set<Element> getRHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(getRHSFocusElement());
		return elements;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		
		if(!(other instanceof A2AConfiguration)) return false;
		A2AConfiguration that = (A2AConfiguration) other;
		
		if(this.context == null && that.context == null) {
			return this.roles.equals(that.roles);
		} 
		
		return this.roles.equals(that.roles) && this.context.equals(that.context);
	}
	
	
	public int hashCode() {
		return 998983 * A2AConfiguration.class.hashCode() + 
		(this.roles == null ? 984395 : this.roles.hashCode()) + 
		(this.context == null ? 56347 : context.hashCode());
	}
	
}
