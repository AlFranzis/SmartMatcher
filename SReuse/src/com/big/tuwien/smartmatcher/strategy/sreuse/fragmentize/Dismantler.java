package com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;

public class Dismantler {
	
	
	public Set<com.big.tuwien.smartmatcher.strategy.sreuse.Operator> 
					dismantle2(Set<Bubble<? extends Operator>> bubbles) {
		return dismantle(bubble(bubbles));
	}
	
	
	public Set<com.big.tuwien.smartmatcher.strategy.sreuse.Operator> 
								dismantle(Set<Bubble<Operator>> bubbles) {
		Map<Bubble<Operator>,Op> converted = 
					new HashMap<Bubble<Operator>, Op>();
		Set<Op> ops = dismantle(bubbles, converted);
		
		Set<com.big.tuwien.smartmatcher.strategy.sreuse.Operator> roots =
		new CopyOnWriteArraySet
		<com.big.tuwien.smartmatcher.strategy.sreuse.Operator>();
		for(Op op : ops) {
			if(!op.hasParents())
				roots.add(op);
		}
		
		return roots;
	}
	
	
	private Set<Op> dismantle(Set<Bubble<Operator>> bubbles, 
									Map<Bubble<Operator>,Op> converted) {
		Set<Op> ops = new CopyOnWriteArraySet<Op>();
		
		for(Bubble<Operator> b : bubbles) {
			// bubble already converted
			if(converted.containsKey(b)) {
				ops.add(converted.get(b));
				continue;
			}
			
			BInfo bi = extract(b);
			
			Op op = new Op();
			op.setName(bi.getName());
			op.setLhsRoles(bi.getLhsRoles());
			op.setRhsRoles(bi.getRhsRoles());
			
			// operator has context -> dismantle context first
			if(bi.hasContext()) {
				Set<Bubble<Operator>> cxtBubbles = bi.getContext();
				Set<Op> cxtOps = dismantle(cxtBubbles, converted);
				
				op.setParents2(cxtOps);
				
				// add op itself as context child
//				for(Op cxtOp : cxtOps) {
//					cxtOp.addChild(op);
//				}
			}
			
			converted.put(b, op);
			ops.add(op);
		}
		
		return ops;
	}
	
	
	private BInfo extract(Bubble<Operator> b) {
		BInfo bi = new BInfo();
		
		// set operator name
		bi.setName(b.getOperatorName());
		
		//TODO: tempory hack to split up LHS roles
		// and RHS roles assuming name prefix
		// set roles
		Configuration<Operator> config = b.getConfiguration();
		Map<Role<Operator>,Element> roles = config.getRoles();
		Map<String,Element> opLhsRoles = new HashMap<String,Element>();
		Map<String,Element> opRhsRoles = new HashMap<String,Element>();
		for(Entry<Role<Operator>,Element> e : roles.entrySet()) {
			String roleName = e.getKey().getName();
			Element roleE = e.getValue();
			if(roleName.startsWith("lhs"))
				opLhsRoles.put(roleName, roleE);
			else if(roleName.startsWith("rhs"))
				opRhsRoles.put(roleName, roleE);
			else
				throw new ReuseRuntimeException("Unknown rolename prefix");
		}
		bi.setLhsRoles(opLhsRoles);
		bi.setRhsRoles(opRhsRoles);
		
		// set context
		if(b.getCxt() != null && !b.getCxt().isEmpty()) {
			Set<Bubble<Operator>> cxt = bubble(b.getCxt());
			bi.setContext(cxt);
		}
		
		return bi;
	}
	
	
	/**
	 * Just to allow fake renaming.
	 */
	private static class Op 
				extends com.big.tuwien.smartmatcher.strategy.sreuse.Operator {
		
//		@SuppressWarnings("unchecked")
//		public void setParents2(Set
//			<? extends com.big.tuwien.smartmatcher.strategy.sreuse.Operator> 
//																	parents) {
//			this.parents = 
//				(Set<com.big.tuwien.smartmatcher.strategy.sreuse.Operator>) 
//						parents;
//		}
		
		
		@SuppressWarnings("unchecked")
		public void setParents2(Set
			<? extends com.big.tuwien.smartmatcher.strategy.sreuse.Operator> 
																	parents) {
				setParents((Set<com.big.tuwien.smartmatcher.strategy.sreuse.Operator>) 
						parents);
		}
	
		
//		public void addChild(
//					com.big.tuwien.smartmatcher.strategy.sreuse.Operator c) {
//			children.add(c);
//		}
		
	}
	
	
	/**
	 * Data transfer object for all data contained in a Bubble.
	 * @author alex
	 *
	 */
	private static class BInfo {
		private String name;
		private Set<Bubble<Operator>> context = 
								new CopyOnWriteArraySet<Bubble<Operator>>();
		private Map<String,Element> lhsRoles = 
			new HashMap<String, Element>();
		private Map<String,Element> rhsRoles = 
			new HashMap<String, Element>();
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Set<Bubble<Operator>> getContext() {
			return context;
		}
		
		public boolean hasContext() {
			return !context.isEmpty();
		}
		
		public void setContext(Set<Bubble<Operator>> context) {
			this.context = context;
		}
		
		public Map<String, Element> getLhsRoles() {
			return lhsRoles;
		}
		
		public Map<String, Element> getRhsRoles() {
			return rhsRoles;
		}
		
		public void setLhsRoles(Map<String, Element> lhsRoles) {
			this.lhsRoles = lhsRoles;
		}
		
		public void setRhsRoles(Map<String, Element> rhsRoles) {
			this.rhsRoles = rhsRoles;
		}
		
		@Override
		public String toString() {
			return "BInfo(" + name + "," + lhsRoles + "," + rhsRoles + ")";
		}
	}
	

	@SuppressWarnings("unchecked")
	private Bubble<Operator> bubble(Bubble<? extends Operator> b) {
		return (Bubble<Operator>) b;
	}
	
	
	private Set<Bubble<Operator>> 
				bubble(Set<Bubble<? extends Operator>> bubbles) {
		Set<Bubble<Operator>> _bubbles = new CopyOnWriteArraySet<Bubble<Operator>>();
		for(Bubble<? extends Operator> b : bubbles) {
			_bubbles.add(bubble(b));;
		}
		return _bubbles;
	}
}
