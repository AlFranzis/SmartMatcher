package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

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
public class C2RConfiguration implements Configuration<C2R> {
	/**
	 * Roles defined for the A2C-Operator
	 */
	public static class Roles {
		public static final Role<C2R> rhsFocusReference = new C2RRole("rhsFocusReference");
		public static final Role<C2R> lhsFocusClass = new C2RRole("lhsFocusClass");
		public static final Role<C2R> lhsContextReference1 = new C2RRole("lhsContextReference1");
		public static final Role<C2R> lhsContextReference2 = new C2RRole("lhsContextReference2");
	}
	
	protected Map<Role<C2R>,Element> roles = new HashMap<Role<C2R>, Element>();
	protected Map<Role<C2R>,Set<Element>> blacklisted = new HashMap<Role<C2R>,Set<Element>>();
	protected C2CConfiguration context1;
	protected C2CConfiguration context2;
	
	
	public C2RConfiguration() {}
	
	
	public C2RConfiguration(Map<Role<C2R>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<C2R> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<C2R>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<C2R> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<C2R>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public Map<Role<C2R>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<C2R> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<C2R> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<C2R> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<C2R>,Set<Element>> getBlacklistMap() {
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
		return this.roles.get(Roles.lhsFocusClass) != null
			&& this.roles.get(Roles.rhsFocusReference) != null
			&& this.roles.get(Roles.lhsContextReference1) != null
			&& this.roles.get(Roles.lhsContextReference2) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusClass();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusReference();
	}
	
	
	public boolean fixedRHSFocusReference() {
		return this.roles.get(Roles.rhsFocusReference) != null;
	}
	
	
	public boolean fixedLHSFocusClass() {
		return this.roles.get(Roles.lhsFocusClass) != null;
	}
	
	
	public boolean fixedLHSContextReference1() {
		return this.roles.get(Roles.lhsContextReference1) != null;
	}
	
	
	public boolean fixedLHSContextReference2() {
		return this.roles.get(Roles.lhsContextReference2) != null;
	}
	
	
	public boolean fixedContexts() {
		return this.context1 != null && this.context2 != null;
	}
	
	
	public String getOperatorName() {
		return "C2R";
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("C2R-Configuration: "); 
		for(Entry<Role<C2R>,Element> e : this.roles.entrySet()) {
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
		elements.add(this.roles.get(Roles.lhsContextReference1));
		elements.add(this.roles.get(Roles.lhsContextReference2));
		elements.add(this.roles.get(Roles.lhsFocusClass));
		return elements;
	}


	public Set<Element> getRHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.rhsFocusReference));
		return elements;
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
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		
		if(!(other instanceof C2RConfiguration)) return false;
		C2RConfiguration that = (C2RConfiguration) other;
		
		if(this.context1 == null && this.context2 == null && 
				that.context1 == null && that.context2 == null) {
			return this.roles.equals(that.roles);
		} 
		
		return this.roles.equals(that.roles) && 
			this.context1.equals(that.context1) && 
			this.context2.equals(that.context2);
	}
	
	
	public int hashCode() {
		return 873495 * C2RConfiguration.class.hashCode() + 
		(this.roles == null ? 7893452 : this.roles.hashCode()) + 
		(this.context1 == null ? 222923 : context1.hashCode()) + 
		(this.context2 == null ? 99843 : context2.hashCode());
	}
	
	
	
}
