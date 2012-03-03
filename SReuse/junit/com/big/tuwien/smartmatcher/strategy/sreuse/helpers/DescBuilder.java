package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asArraySet;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;

public class DescBuilder {
	/*
	 * Operator descriptions can be bound to names so that these
	 * descriptions can be referenced in child operator descriptions and so on.
	 * This map maintains these bindings.
	 */
	private Map<String,OpDesc> bindings = new HashMap<String, OpDesc>();
	
	
	public MappingDesc ops(OpDesc ...ods) {
		Set<RootOpDesc> rootOps = new CopyOnWriteArraySet<RootOpDesc>();
		for(OpDesc od : ods) {
			if(od instanceof RootOpDesc)
				rootOps.add((RootOpDesc) od);
		}
		
		MappingDesc md = new MappingDesc(rootOps);
		return md;
	}
	
	
	public class Desc {}
	
	
	public class OpDesc extends Desc {
		private Set<OpDesc> children;
		private Set<OpDesc> parents;
		private String name;
		private Map<String,String> lhsRoles;
		private Map<String,String> rhsRoles;
		
		
		public OpDesc(String name, Set<OpDesc> parents,
				Map<String, String> lhsRoles, Map<String, String> rhsRoles,
				Set<OpDesc> children) {
			this.name = name;
			setParents(parents);
			setChildren(children);
			this.lhsRoles = lhsRoles;
			this.rhsRoles = rhsRoles;
		}
		
		
		private void setParents(Set<OpDesc> parents) {
			this.parents = parents;
			for(OpDesc p : parents) {
				p.children.add(this);
			}
		}
		
		
		private void setChildren(Set<OpDesc> children) {
			this.children = children;
			for(OpDesc c : children) {
				c.parents.add(this);
			}
		}
		
		
		public OpDesc as(String name) {
			if(bindings.containsKey(name)) 
				throw new ReuseRuntimeException("");
	
			bindings.put(name, this);
			return this;
		}


		public Set<OpDesc> getChildren() {
			return children;
		}


		public Set<OpDesc> getParents() {
			return parents;
		}


		public String getName() {
			return name;
		}


		public Map<String, String> getLhsRoles() {
			return lhsRoles;
		}


		public Map<String, String> getRhsRoles() {
			return rhsRoles;
		}
		
		
		public Map<String,String> getRoles() {
			Map<String,String> roles = new HashMap<String,String>(lhsRoles);
			roles.putAll(rhsRoles);
			return roles;
		}
		
		
		@Override
		public String toString() {
			return String.format("OpDesc(%s,%s,%s,%s)",
					name, lhsRoles, rhsRoles, children);
		}
	}
	
	
	public class RootOpDesc extends OpDesc {
		public RootOpDesc(String name,
				Map<String, String> lhsRoles, Map<String, String> rhsRoles,
				Set<OpDesc> children) {
			super(name, Collections.<OpDesc>emptySet(),
					lhsRoles, rhsRoles, children);
					
		}
	}
	
	
	public class MappingDesc extends Desc {
		/* ROOT operator descriptions */
		private Set<RootOpDesc> operators;
		
		
		public MappingDesc(Set<RootOpDesc> ops) {
			this.operators = ops;
		}
		
		
		public Set<RootOpDesc> getOperators() {
			return operators;
		}
	}
	
	
	public RootOpDesc c2c(String lhs, String rhs, OpDesc ...children) {
		return new RootOpDesc("C2C",  
				map("lhsFocusElement", lhs), 
				map("rhsFocusElement", rhs),
				asArraySet(children));
	}
	
	
	public OpDesc a2a(String lhs, String rhs) {
		return new OpDesc("A2A", 
				new CopyOnWriteArraySet<OpDesc>(), 
				map("lhsFocusAttribute", lhs), 
				map("rhsFocusAttribute", rhs),
				Collections.<OpDesc>emptySet());
	}
	
	
	public OpDesc a2c(String lhsAttribute, String rhsRef, String rhsClass, 
														String rhsAttribute) {
		return new OpDesc("A2C", 
				new CopyOnWriteArraySet<OpDesc>(), 
				map("lhsFocusAttribute", lhsAttribute), 
				map("rhsContextReference", rhsRef)
				.map("rhsFocusClass", rhsClass)
				.map("rhsContextAttribute", rhsAttribute),
				Collections.<OpDesc>emptySet());
	}
	
	
	public OpDesc r2r(String c2c1, String c2c2, String lhs, String rhs) {
		if(!bindings.containsKey(c2c1)) {
			throw new ReuseRuntimeException("");
		}
		OpDesc p1 = bindings.get(c2c1);
		
		if(!bindings.containsKey(c2c2)) {
			throw new ReuseRuntimeException("");
		}
		OpDesc p2 = bindings.get(c2c2);
		
		return new OpDesc("R2R", 
				asArraySet(p1, p2), 
				map("lhsFocusReference", lhs), 
				map("rhsFocusReference", rhs),
				Collections.<OpDesc>emptySet());
	}
}
