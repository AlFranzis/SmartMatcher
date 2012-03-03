package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.FragmentBuilder.Fragment_;

public class FragmentMappingBuilder { 
	private Map<String, Operator> cBindings = new HashMap<String,Operator>();
	private Fragment_ lhsFrag;
	private Fragment_ rhsFrag;
	
	
	public FragmentMappingBuilder() {}
	
	
	public FragmentMappingBuilder(Fragment_ lhs, Fragment_ rhs) {
		init(lhs, rhs);
	}
	
	
	public void init(Fragment_ lhs, Fragment_ rhs) {
		lhsFrag = lhs;
		rhsFrag = rhs;
	}

	
	public class Operator_ extends Operator {
		public Operator_ as(String name) {
			cBindings.put(name, this);
			return this;
		}
	}

	
	public FragmentMapping fm(List<? extends Operator> ops) {
		Set<Operator> rootOps = new CopyOnWriteArraySet<Operator>();
		for(Operator op : ops) {
			if(op.getParents().isEmpty())
				rootOps.add(op);
		}
		
		FragmentMapping m = new FragmentMapping();
		m.setOperators(new CopyOnWriteArraySet<Operator>(rootOps));
		return m;
	}
	
	
	public Operator_ op_(String opName, List<Operator> parents, 
			Map<String,Element> lhsRoles, Map<String,Element> rhsRoles) {
		Operator_ op = new Operator_();
		op.setName(opName);
		op.setLhsRoles(lhsRoles);
		op.setRhsRoles(rhsRoles);
		op.setParents(new CopyOnWriteArraySet<Operator>(parents));
		
		return op;
	}
	
	
	public Operator op_(String opName, Map<String,Element> lhsRoles, 
			Map<String,Element> rhsRoles, List<Operator> children) {
		Operator op = new Operator();
		op.setName(opName);
		op.setLhsRoles(lhsRoles);
		op.setRhsRoles(rhsRoles);
		op.setChildren(new CopyOnWriteArraySet<Operator>(children));
		
		return op;
	}
	
	
	public Operator op_(String opName, Map<String,Element> lhsRoles, 
			Map<String,Element> rhsRoles) {
		return op_(opName, lhsRoles, rhsRoles, 
				Collections.<Operator>emptyList());
	}
	
	
	public Operator_ op(String opName, List<? extends Operator> parents, 
					Map<String,String> lhsRoles, Map<String,String> rhsRoles) {
		Operator_ op = new Operator_();
		op.setName(opName);
		
		Map<String,Element> _lhsRoles = new HashMap<String, Element>();
		for(Entry<String,String> lhsRole : lhsRoles.entrySet()) {
			Element e = lhsFrag.getElementByName(lhsRole.getValue());
			if(e == null)
				throw new ReuseRuntimeException(
				"LHS Fragment does not contain element " + lhsRole.getValue());
			_lhsRoles.put(lhsRole.getKey(), e);
		}
		op.setLhsRoles(_lhsRoles);
		
		Map<String,Element> _rhsRoles = new HashMap<String, Element>();
		for(Entry<String,String> rhsRole : rhsRoles.entrySet()) {
			Element e = rhsFrag.getElementByName(rhsRole.getValue());
			if(e == null)
				throw new ReuseRuntimeException(
				"RHS Fragment does not contain element " + rhsRole.getValue());
			_rhsRoles.put(rhsRole.getKey(), e);
		}
		op.setRhsRoles(_rhsRoles);
		
		op.setParents(new CopyOnWriteArraySet<Operator>(parents));
		
		return op;
	}
	
	
	public Operator_ op(String opName, Map<String,String> lhsRoles, 
			Map<String,String> rhsRoles, List<? extends Operator> children) {
		Operator_ op = new Operator_();
		op.setName(opName);
		
		Map<String,Element> _lhsRoles = new HashMap<String, Element>();
		for(Entry<String,String> lhsRole : lhsRoles.entrySet()) {
			_lhsRoles.put(
					lhsRole.getKey(), 
					lhsFrag.getElementByName(lhsRole.getValue()));
		}
		op.setLhsRoles(_lhsRoles);
		
		
		Map<String,Element> _rhsRoles = new HashMap<String, Element>();
		for(Entry<String,String> rhsRole : rhsRoles.entrySet()) {
			_rhsRoles.put(
					rhsRole.getKey(), 
					rhsFrag.getElementByName(rhsRole.getValue()));
		}
		op.setRhsRoles(_rhsRoles);
		
		op.setChildren(new CopyOnWriteArraySet<Operator>(children));
		
		return op;
	}
	
	
	public Operator_ op(String opName, Map<String,String> lhsRoles, 
			Map<String,String> rhsRoles) {
		return op(opName, lhsRoles, rhsRoles, 
				Collections.<Operator>emptyList());
	}
	

	public Operator op(String name) {
		if(!cBindings.containsKey(name))
			throw new IllegalArgumentException("No binding for: " + name);
		return cBindings.get(name);
	}
	
	
	public <T extends Operator> List<T> li(T ...cs) {
		return Arrays.<T>asList(cs);
	}
	
}
