package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

/**
 * 
 * @author alex
 *
 */
public class C2CConfiguration implements HomogenicConfiguration<C2C> {
	/**
	 * Roles defined for the C2C-Operator
	 */
	public static class Roles {
		public static Role<C2C> lhsFocusClass = new C2CRole("lhsFocusElement");
		public static Role<C2C> rhsFocusClass = new C2CRole("rhsFocusElement");
		
	}
		
	protected Map<Role<C2C>,Element> roles = new HashMap<Role<C2C>, Element>();
	protected Map<Role<C2C>,Set<Element>> blacklisted = new HashMap<Role<C2C>,Set<Element>>();
	
	
	public C2CConfiguration() {}
	
	
	public C2CConfiguration(Map<Role<C2C>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<C2C> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<C2C>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<C2C> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<C2C>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public Map<Role<C2C>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<C2C> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<C2C> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<C2C> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<C2C>,Set<Element>> getBlacklistMap() {
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
		return this.roles.get(Roles.lhsFocusClass) != null
			&& this.roles.get(Roles.rhsFocusClass) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusClass();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusClass();
	}
	
	
	public boolean fixedRHSFocusClass() {
		return this.roles.get(Roles.rhsFocusClass) != null;
	}
	
	
	public boolean fixedLHSFocusClass() {
		return this.roles.get(Roles.lhsFocusClass) != null;
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("C2C-Configuration: "); 
		for(Entry<Role<C2C>,Element> e : this.roles.entrySet()) {
			if(!first)
				buf.append(", ");
			Element el = e.getValue();
			buf.append("{Role: " + e.getKey() + " , " + el + "}");
			first = false;
		}
		return buf.toString();
	}


	public String getOperatorName() {
		return "C2C";
	}

	
	public Element getLHSFocusElement() {
		return getRoleElement(Roles.lhsFocusClass);
	}


	public Element getRHSFocusElement() {
		return getRoleElement(Roles.rhsFocusClass);
	}


	public void setLHSFocusElement(Element e) {
		setRole(Roles.lhsFocusClass, e);
		
	}


	public void setRHSFocusElement(Element e) {
		setRole(Roles.rhsFocusClass, e);
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
		
		if(!(other instanceof C2CConfiguration)) return false;
		C2CConfiguration that = (C2CConfiguration) other;
		
		return this.roles.equals(that.roles);
	}
	
	
	public int hashCode() {
		return 898 * C2CConfiguration.class.hashCode() + 
		(this.roles == null ? 27609395 : this.roles.hashCode());
	}
	
}
