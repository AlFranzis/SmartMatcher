package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collection;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Measure;

public class A2AProperties {
	private Collection<Measure> priorityMeasures = new Vector<Measure>();
	
	
	public void setPriorityMeasures(Collection<Measure> pMeasures) {
		this.priorityMeasures = pMeasures;
	}
	
	
	public boolean hasPriorityMeasures() {
		return !priorityMeasures.isEmpty();
	}
	
	
	public Collection<Measure> getPriorityMeasures() {
		return priorityMeasures;
	}
	
	
	public A2A getOperatorInstance() {
		return new A2A(this);
	}
	
}
