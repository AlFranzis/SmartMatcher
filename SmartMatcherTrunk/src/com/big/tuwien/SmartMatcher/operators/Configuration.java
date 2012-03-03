package com.big.tuwien.SmartMatcher.operators;

import java.util.Map;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public interface Configuration<R extends Operator> {
	
	
	public void setRole(Role<R> role, Element e);
	
	public void setRoles(Map<Role<R>,Element> roles);
	
	public Map<Role<R>,Element> getRoles();
	
	public Element getRoleElement(Role<R> role);
	
	public boolean isUnderspecified();
	
	public boolean isComplete();
	
	public String getOperatorName();
	
	public Set<Element> getRHSElements();
	
	public Set<Element> getLHSElements();
	
	public boolean blacklistContains(Role<R> role, Element e);
}
