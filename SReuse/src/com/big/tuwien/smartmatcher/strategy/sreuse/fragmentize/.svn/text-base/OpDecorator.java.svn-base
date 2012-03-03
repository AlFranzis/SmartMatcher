/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import java.util.Set;

import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;

class OpDecorator extends Operator {
	private Operator original;
	
	
	public OpDecorator(Operator op, Set<Operator> visibleChildren) {
		original = op;
		setName(op.getName());
		setLhsRoles(op.getLhsRoles());
		setRhsRoles(op.getRhsRoles());
		setChildren(visibleChildren);
	}
	
	
	public Operator getOriginal() {
		return original;
	}
	
	
	@Override
	public String toString() {
		return "OpDecorator(" + name + "," + parents + ", " + lhsRoles + "," 
				+ rhsRoles + ")";
	}
	
}