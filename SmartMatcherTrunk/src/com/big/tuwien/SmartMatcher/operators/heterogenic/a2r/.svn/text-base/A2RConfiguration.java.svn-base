package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

/**
 * 
 * @author alex
 *
 */
public class A2RConfiguration implements Configuration<A2R> {
	/**
	 * Roles defined for the A2C-Operator
	 */
	public static class Roles {
		public static final Role<A2R> lhsFocusAttribute1 = new A2RRole("lhsFocusAttribute1");
		public static final Role<A2R> lhsFocusAttribute2 = new A2RRole("lhsFocusAttribute2");
		public static final Role<A2R> rhsFocusReference = new A2RRole("rhsFocusReference");
	}
	
	protected Map<Role<A2R>,Element> roles = new HashMap<Role<A2R>, Element>();
	protected Map<Role<A2R>,Set<Element>> blacklisted = new HashMap<Role<A2R>,Set<Element>>();
	protected C2CConfiguration context1;
	protected C2CConfiguration context2;
	
	
	public A2RConfiguration() {}
	
	
	public A2RConfiguration(Map<Role<A2R>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<A2R> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<A2R>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<A2R> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<A2R>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public Map<Role<A2R>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<A2R> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<A2R> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<A2R> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<A2R>,Set<Element>> getBlacklistMap() {
		return blacklisted;
	}
	

	public boolean isUnderspecified() {
		return this.roles.isEmpty() && this.context1 == null && this.context2 == null;
	}
	
	
	/**
	 * Returns if the configuration is complete, e.g. if all
	 * roles are set.
	 * @return
	 */
	public boolean isComplete() {
		return this.roles.get(Roles.lhsFocusAttribute1) != null
			&& this.roles.get(Roles.lhsFocusAttribute2) != null
			&& this.roles.get(Roles.rhsFocusReference) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusAttribute1() && fixedLHSFocusAttribute2();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusReference();
	}
	
		
	public boolean fixedRHSFocusReference() {
		return this.roles.get(Roles.rhsFocusReference) != null;
	}
	
	
	
	public boolean fixedLHSFocusAttribute1() {
		return this.roles.get(Roles.lhsFocusAttribute1) != null;
	}
	
	
	public boolean fixedLHSFocusAttribute2() {
		return this.roles.get(Roles.lhsFocusAttribute2) != null;
	}
	
	
	public boolean fixedContexts() {
		return this.context1 != null && this.context2 != null;
	}
	
	
	public String getOperatorName() {
		return "A2R";
	}
	
	
	public void setContext1(C2CConfiguration context1) {
		this.context1 = context1;
	}
	
	
	public void setContext2(C2CConfiguration context2) {
		this.context2 = context2;
	}
	
	
	public C2CConfiguration getContext1() {
		return this.context1;
	}
	
	
	public C2CConfiguration getContext2() {
		return this.context2;
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("A2R-Configuration: "); 
		for(Entry<Role<A2R>,Element> e : this.roles.entrySet()) {
			if(!first)
				buf.append(", ");
			Element el = e.getValue();
			buf.append("{Role: " + e.getKey() + " , " + el + "}");
			first = false;
		}
		return buf.toString();
	}


	public Set<Element> getLHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.lhsFocusAttribute1));
		elements.add(this.roles.get(Roles.lhsFocusAttribute2));
		return elements;
	}


	public Set<Element> getRHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.rhsFocusReference));
		return elements;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		
		if(!(other instanceof A2CConfiguration)) return false;
		A2RConfiguration that = (A2RConfiguration) other;
		
		if(this.context1 == null && this.context2 == null && 
				that.context1 == null && that.context2 == null) {
			return this.roles.equals(that.roles);
		} 
		
		return this.roles.equals(that.roles) && 
			this.context1.equals(that.context1) && 
			this.context2.equals(that.context2);
	}
	
	
	public int hashCode() {
		return 873383 * A2CConfiguration.class.hashCode() + 
		(this.roles == null ? 984395 : this.roles.hashCode()) + 
		(this.context1 == null ? 56347 : context1.hashCode()) + 
		(this.context2 == null ? 78388 : context2.hashCode());
	}
	
}
