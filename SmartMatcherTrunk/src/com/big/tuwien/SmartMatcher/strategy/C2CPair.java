package com.big.tuwien.SmartMatcher.strategy;

import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;

public class C2CPair {
	private C2CConfiguration first;
	private C2CConfiguration second;
	
	
	public C2CPair(C2CConfiguration first, C2CConfiguration second) {
		this.first = first;
		this.second = second;
	}
	
	
	public C2CConfiguration getFirst() {
		return this.first;
	}
	
	
	public C2CConfiguration getSecond() {
		return this.second;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || !(other instanceof C2CPair)) return false;
		
		C2CPair that = (C2CPair) other;
		return this.first.equals(that.first) && this.second.equals(that.second);
	}
	
	
	public int hashCode() {
		return this.getClass().hashCode() + 893459 * this.first.hashCode() 
				+  9989435 * this.second.hashCode();
	}
	
	
	public String toString() {
		return "C2CPair :: first : " + first + ", second : " + second; 
	}
}
