package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


public class FragmentMapping {
	private String id;
	
	/*
	 * ROOT operators
	 */
	private Set<Operator> operators = new CopyOnWriteArraySet<Operator>();
	
	/*
	 * FLATTENED operators
	 */
	private Set<Operator> flattenedOperators = 
					new CopyOnWriteArraySet<Operator>();

	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	
	public Set<Operator> getOperators() {
		return operators;
	}
	
	
	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
		
		Set<Operator> ops = new CopyOnWriteArraySet<Operator>();
		for(Operator rootOp : operators) {
			Set<Operator> _ops = flatten(rootOp);
			ops.addAll(_ops);
		}
		
		this.flattenedOperators = ops;
	}
	
	
	/*
	 * Flattens an operator: Returns the operator and it's
	 * children in a set.
	 */
	private Set<Operator> flatten(Operator op) {
		Set<Operator> ops = new CopyOnWriteArraySet<Operator>();
		ops.add(op);
		for(Operator child : op.getChildren()) {
			Set<Operator> _ops = flatten(child);
			ops.addAll(_ops);
		}
		return ops;
	}
	
	
	public Set<Operator> getFlattenedOperators() {
		return flattenedOperators;
	}
	
	
	/**
	 * Returns the number of operators in the whole tree.
	 * @return
	 */
	public int getSize() {
		return size(0, operators);
	}
	
	
	private int size(int size, Set<Operator> ops) {
		size += ops.size();
		Set<Operator> children = new CopyOnWriteArraySet<Operator>();
		for(Operator op : ops) {
			children.addAll(op.getChildren());
		}
		size += children.size();
		
		if(children.isEmpty())
			return size;
		else
			return size(size, children);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || !(other instanceof FragmentMapping)) return false;
		
		FragmentMapping that = (FragmentMapping) other;
		boolean eqOps = getOperators().equals(that.getOperators());
		return eqOps;
	}
	
	
	@Override
	public int hashCode() {
		return 71 * FragmentMapping.class.hashCode() 
				+ 61 * operators.hashCode();
	}
	
	
	public boolean flatEquals(FragmentMapping other) {
		if(this == other) return true;
		if(other == null) return false;
		
		return false;
	}
	
	
	public int flatHashCode() {
		return FragmentMapping.class.hashCode() 
		+ 71 * this.getOperators().hashCode(); 
	}
	
	
	@Override
	public String toString() {
		return "FragmentMapping(" + flattenedOperators + ")";
	}
	
}
