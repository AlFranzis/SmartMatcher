package com.big.tuwien.SmartMatcher.operators;

import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;

import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.HomogenicConfiguration;

public class Levenshtein implements Measure {
	private InterfaceStringMetric metric;
	
	
	public Levenshtein() {
		metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein();
	}
	
	
	public float getSimilarity(Configuration<?> c1) {
		if(c1 instanceof HomogenicConfiguration<?>) {
			HomogenicConfiguration<?> c = (HomogenicConfiguration<?>) c1;
			return metric.getSimilarity(c.getLHSFocusElement().getName(), c.getRHSFocusElement().getName());
		} else if(c1 instanceof A2CConfiguration) {
			A2CConfiguration c = (A2CConfiguration) c1;
			return metric.getSimilarity(c.getRoleElement(A2CConfiguration.Roles.lhsFocusAttribute).getName(), 
					c.getRoleElement(A2CConfiguration.Roles.rhsContextAttribute).getName());
		} else if(c1 instanceof A2RConfiguration) {
			A2RConfiguration c = (A2RConfiguration) c1;
			return metric.getSimilarity(c.getRoleElement(A2RConfiguration.Roles.lhsFocusAttribute1).getName(), 
					c.getRoleElement(A2RConfiguration.Roles.lhsFocusAttribute2).getName());
		} else {
			throw new IllegalArgumentException("Levenshtein measure is not implemented for this type " +
					"of configuration: " + c1.getClass().getCanonicalName());
		}
		
	}

}
