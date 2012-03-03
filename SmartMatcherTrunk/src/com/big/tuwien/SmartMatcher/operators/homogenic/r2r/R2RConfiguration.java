package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

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
public class R2RConfiguration implements HomogenicConfiguration<R2R> {
	/**
	 * Roles defined for the R2R-Operator
	 */
	public static class Roles {
		public static Role<R2R> lhsFocusReference = new R2RRole("lhsFocusReference");
		public static Role<R2R> rhsFocusReference = new R2RRole("rhsFocusReference");
		
	}
	
	public enum DIRECTION {directed, inverse, undirected}; 
	
	protected Map<Role<R2R>,Element> roles = new HashMap<Role<R2R>, Element>();
	protected Map<Role<R2R>,Set<Element>> blacklisted = new HashMap<Role<R2R>,Set<Element>>();
	protected C2CConfiguration context1;
	protected C2CConfiguration context2;
	protected DIRECTION direction = DIRECTION.undirected;
	
	
	public R2RConfiguration() {}
	
	
	public R2RConfiguration(Map<Role<R2R>,Element> roles) {
		setRoles(roles);
	}
	
	
	public void setRole(Role<R2R> role, Element e) {
		this.roles.put(role, e);
	}
	
	
	public void setRoles(Map<Role<R2R>,Element> roles) {
		this.roles.putAll(roles);
	}
	
	
	public void setRoleBlacklist(Role<R2R> role, Set<Element> blacklisted) {
		this.blacklisted.put(role, blacklisted);
	}
	
	
	public void setBlacklistMap(Map<Role<R2R>,Set<Element>> blacklisted) {
		this.blacklisted = blacklisted;
	}
	
	
	public void setDirectionConstraint(DIRECTION direction) {
		this.direction = direction;
	}
	
	
	public DIRECTION getDirection() {
		return direction;
	}
	
	
	public Map<Role<R2R>,Element> getRoles() {
		return this.roles;
	}
	
	
	public Element getRoleElement(Role<R2R> role) {
		return this.roles.get(role);
	}
	
	
	public Set<Element> getBlacklisted(Role<R2R> role) {
		return this.blacklisted.get(role);
	}
	
	
	public boolean blacklistContains(Role<R2R> role, Element e) {
		Set<Element> blist = this.blacklisted.get(role);
		return (blist != null && blist.contains(e)) ?  true : false;
	}
	
	
	public Map<Role<R2R>,Set<Element>> getBlacklistMap() {
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
		return this.roles.get(Roles.lhsFocusReference) != null
			&& this.roles.get(Roles.rhsFocusReference) != null;
	}
	
	
	public boolean fixedLHSFocusElement() {
		return fixedLHSFocusReference();
	}
	
	
	public boolean fixedRHSFocusElement() {
		return fixedRHSFocusReference();
	}
	
	
	public boolean fixedRHSFocusReference() {
		return this.roles.get(Roles.rhsFocusReference) != null;
	}
	
	
	public boolean fixedLHSFocusReference() {
		return this.roles.get(Roles.lhsFocusReference) != null;
	}
	
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append("R2R-Configuration: "); 
		for(Entry<Role<R2R>,Element> e : this.roles.entrySet()) {
			if(!first)
				buf.append(", ");
			Element el = e.getValue();
			buf.append("{Role: " + e.getKey() + " , " + el + "}");
			first = false;
		}
		return buf.toString();
	}


	public String getOperatorName() {
		return "R2R";
	}


	public C2CConfiguration getContext1() {
		return context1;
	}


	public void setContext1(C2CConfiguration context1) {
		this.context1 = context1;
	}


	public C2CConfiguration getContext2() {
		return context2;
	}


	public void setContext2(C2CConfiguration context2) {
		this.context2 = context2;
	}


	public Element getLHSFocusElement() {
		return getRoleElement(Roles.lhsFocusReference);
	}


	public Element getRHSFocusElement() {
		return getRoleElement(Roles.rhsFocusReference);
	}


	public void setLHSFocusElement(Element e) {
		setRole(Roles.lhsFocusReference, e);
	}


	public void setRHSFocusElement(Element e) {
		setRole(Roles.rhsFocusReference, e);
	}


	public Set<Element> getLHSElements() {
		Set<Element> elements = new HashSet<Element>();
		elements.add(this.roles.get(Roles.lhsFocusReference));
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
		
		if(!(other instanceof R2RConfiguration)) return false;
		R2RConfiguration that = (R2RConfiguration) other;
		
		return this.roles.equals(that.roles) && 
			this.context1.equals(that.context1) && 
			this.context2.equals(that.context2);
	}
	
	
	public int hashCode() {
		return 23983 * R2RConfiguration.class.hashCode() + 
		(this.roles == null ? 984395 : this.roles.hashCode()) + 
		(this.context1 == null ? 57347 : context1.hashCode()) +
		(this.context2 == null ? 57787 : context2.hashCode());
	}
	
}
