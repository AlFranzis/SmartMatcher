package com.big.tuwien.SmartMatcher.views.emf;

import sm_mm.Operator;

public class GenericEMFFactory {
	
	public Operator createOperatorInstance(String name) {
		return sm_mm.Sm_mmFactory.eINSTANCE.createC2C();
	}
	
}
