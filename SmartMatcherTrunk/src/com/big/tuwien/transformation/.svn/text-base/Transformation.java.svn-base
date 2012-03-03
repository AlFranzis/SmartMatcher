package com.big.tuwien.transformation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.SmartMatcher.operators.Configuration;

public class Transformation {
	private Configuration<?> config;
	private List<AtomicTransformation> atomics;
	
	
	public Transformation(Configuration<?> config) {
		this.config = config;
	}
	
	
	public Configuration<?> getConfig() {
		return config;
	}


	public List<AtomicTransformation> getAtomics() {
		return this.atomics;
	}
	
	
	public Set<InstanceElement<?,?>> getLHSObjects() {
		Set<InstanceElement<?,?>> elems = new HashSet<InstanceElement<?,?>>();
		for(AtomicTransformation at : atomics) {
			elems.addAll(at.getLHSObjects());
		}
		return elems;
	}
	
	
	public Set<InstanceElement<?,?>> getRHSObjects() {
		Set<InstanceElement<?,?>> elems = new HashSet<InstanceElement<?,?>>();
		for(AtomicTransformation at : atomics) {
			elems.addAll(at.getRHSObjects());
		}
		return elems;
	}
	
}
