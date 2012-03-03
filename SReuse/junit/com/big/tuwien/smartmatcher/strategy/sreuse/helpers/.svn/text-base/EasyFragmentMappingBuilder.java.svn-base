package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;

import java.util.Collections;
import java.util.List;

import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;

public class EasyFragmentMappingBuilder extends FragmentMappingBuilder {

	public EasyFragmentMappingBuilder() {
		super();
	}

	
	public EasyFragmentMappingBuilder(Fragment_ lhsMM, Fragment_ rhsMM) {
		super(lhsMM, rhsMM);
	}
	
	
	public Operator_ a2a(String lhsFocusElement, String rhsFocusElement) {
		return op("A2A", 
			map("lhsFocusAttribute", lhsFocusElement),
			map("rhsFocusAttribute", rhsFocusElement));
	}
	
	
	public Operator_ c2c(String lhsFocusElement, String rhsFocusElement) {
		return c2c(lhsFocusElement, rhsFocusElement, Collections.<Operator>emptyList());
	}
	
	
	public Operator_ c2c(String lhsFocusElement, String rhsFocusElement,
			 							List<? extends Operator> children) {
		return op("C2C", 
			map("lhsFocusElement", lhsFocusElement),
			map("rhsFocusElement", rhsFocusElement),
			children);
	}
	
	
	public Operator_ r2r(List<? extends Operator> parents,
			String lhsFocusElement, String rhsFocusElement) {
		return op("R2R", 
			parents,
			map("lhsFocusReference", lhsFocusElement),
			map("rhsFocusReference", rhsFocusElement));
	}
	
}
