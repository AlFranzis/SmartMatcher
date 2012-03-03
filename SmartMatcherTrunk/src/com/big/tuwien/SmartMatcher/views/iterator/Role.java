package com.big.tuwien.SmartMatcher.views.iterator;

import com.big.tuwien.SmartMatcher.operators.Operator;

public class Role<T extends Operator> {
	private String name;

	
	public Role() {}
	
	
	public Role(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public boolean equals(Object other) {
		if(other == null) return false;
		if(this == other) return true;
		
		if(!(other instanceof Role)) return false;
		Role<?> that = (Role<?>) other;
		return this.name.equals(that.name);
 	}
	
	
	public int hashCode() {
		return Role.class.hashCode() + this.name.hashCode();
	}
	
	
	public String toString() {
		return this.name;
	}
}
