package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import java.util.Collection;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.operators.Measure;

public class R2RProperties {
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
	
	
	public R2R getOperatorInstance() {
		return new R2R(this);
	}
	
}
