package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.Collection;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Measure;

public class C2CProperties {
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
	
	
	public C2C getOperatorInstance() {
		return new C2C(this);
	}
	
}
