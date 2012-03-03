package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;

public class Operator {
	private String id;
	protected String name;
	/*
	 * Default -1.0 means not set
	 */
	private double similarity = -1.0;
	protected Set<Operator> parents = new CopyOnWriteArraySet<Operator>();
	protected Set<Operator> children = new CopyOnWriteArraySet<Operator>();
	protected Map<String,Element> lhsRoles = new HashMap<String, Element>();
	protected Map<String,Element> rhsRoles = new HashMap<String, Element>();
	
	
	public Operator() {}
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	
	public Operator(String name, Map<String, Element> lhsRoles, Map<String, Element> rhsRoles) {
		this.name = name;
		this.lhsRoles = lhsRoles;
		this.rhsRoles = rhsRoles;
	}
	
	
	public void setSimilarity(double sim) {
		similarity = sim;
	}
	
	
	public double getSimilarity() {
		return similarity;
	}


	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Map<String, Element> getRoles() {
		Map<String, Element> roles = new HashMap<String, Element>(lhsRoles);
		roles.putAll(rhsRoles);
		return roles;
	}
	
	
	public Map<String, Element> getLhsRoles() {
		return lhsRoles;
	}
	
	
	public Map<String, Element> getRhsRoles() {
		return rhsRoles;
	}
	
	
	public void setLhsRoles(Map<String, Element> lhsRoles) {
		this.lhsRoles = lhsRoles;
	}
	
	
	public void setRhsRoles(Map<String, Element> rhsRoles) {
		this.rhsRoles = rhsRoles;
	}


	public Set<Operator> getParents() {
		return parents;
	}


	public void setParents(Set<Operator> parents) {
		this.parents = parents;
		
		for(Operator p : parents) {
			p.children.add(this);
		}
	}
	
	
	public boolean hasParents() {
		return !parents.isEmpty();
	}


	public Set<Operator> getChildren() {
		return children;
	}


	public void setChildren(Set<Operator> children) {
		this.children = children;
		
		for(Operator c : children) {
			c.parents.add(this);
		}
	}
	
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		if(!(other instanceof Operator)) return false;
		
		Operator that = (Operator) other;
		
		return this.getName().equals(that.getName()) 
			&& this.getRoles().equals(that.getRoles())
			&& this.getChildren().equals(that.getChildren());
	}
	
	
	@Override
	public int hashCode() {
		return Operator.class.hashCode() 
			+ 78 * this.getName().hashCode() 
			+ 51 * this.getRoles().hashCode()
			+ 67 * this.getChildren().hashCode();
	}
	
	
	public boolean flatEquals(Operator other) {
		if(this == other) return true;
		if(other == null) return false;
		
		return this.getName().equals(other.getName()) 
			&& this.getRoles().equals(other.getRoles());
	}
	
	
	public int flatHashCode() {
		return Operator.class.hashCode() 
		+ 78 * this.getName().hashCode() 
		+ 51 * this.getRoles().hashCode();
	}
	
	
	@Override
	public String toString() {
		return "Operator(" + name + "," + parents + ", " + lhsRoles + "," + rhsRoles + ")";
	}
	
}
