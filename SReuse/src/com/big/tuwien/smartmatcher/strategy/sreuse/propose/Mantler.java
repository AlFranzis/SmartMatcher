package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLOperator;

public class Mantler {
	private Map<XMLElement,Element> elements;
	private BubbleFactory bFactory;
	
	
	public Mantler(Map<XMLElement, Element> elements, BubbleFactory bFactory) {
		this.elements = elements;
		this.bFactory = bFactory;
	}


	public Set<Bubble<? extends Operator>> 
					mantle(Set<XMLOperator> xmlOps) {
		
		Map<XMLOperator,Bubble<? extends Operator>> converted = 
					new HashMap<XMLOperator,Bubble<? extends Operator>>();
		Set<Bubble<? extends Operator>> bubbles = mantle(xmlOps, converted);
		
		return bubbles;
	}
	
	
	private Set<Bubble<? extends Operator>> mantle(Set<XMLOperator> xmlOps, 
					Map<XMLOperator,Bubble<? extends Operator>> converted) {
		
		Set<Bubble<? extends Operator>> ops = 
			new CopyOnWriteArraySet<Bubble<? extends Operator>>();

		for(XMLOperator xmlOp : xmlOps) {
			// operator already converted
			if(converted.containsKey(xmlOp)) {
				ops.add(converted.get(xmlOp));
				continue;
			}

			BInfo bi = extract(xmlOp);

			// create bubble instance using information in BInfo
			Bubble<? extends Operator> bubble = createBubble(bi);
			
			// operator has parents -> mantle parents first
			if(bi.hasContext()) {
				Set<XMLOperator> cxtBubbles = bi.getContext();
				Set<Bubble<? extends Operator>> cxtOps = 
									mantle(cxtBubbles, converted);
				
				List<Bubble<? extends Operator>> _cxt = 
					new ArrayList<Bubble<? extends Operator>>(cxtOps);
				
				// use reflection to set context member fields
				// of bubble object
				switch(cxtBubbles.size()) {
					case 1:
						// set bubble member field 'context'
						setCxtMember(bubble, _cxt.get(0), "context");
						break;
					case 2:
						// set bubble member fields 'context1' and 'context2'
						setCxtMembers(bubble, 
								_cxt.get(0), "context1", 
								_cxt.get(1), "context2");
						break;
					default:
						throw new ReuseRuntimeException(
								"Mantler implementation does not support " +
								"bubbles with context size " 
								+ cxtBubbles.size());
				}
				
				bubble.setCxt(cxtOps);
			}

			converted.put(xmlOp, bubble);
			ops.add(bubble);
		}

		return ops;
	}
	
	
	private boolean setCxtMember(Bubble<? extends Operator> b, 
			Bubble<? extends Operator> cxt, String fieldName) {
		boolean set = false;
		
		try {
			Class<?> bclazz = b.getClass();
			Field f = bclazz.getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(b, cxt);
			set = true;
		} catch (Exception e) {
			throw new ReuseRuntimeException("Reflection error " +
					"while reflecting bubble " + b + 
					" for field " + fieldName, e);
		}
		
		return set;
	}
	
	
	private boolean setCxtMembers(Bubble<? extends Operator> b, 
			Bubble<? extends Operator> cxt1, String fieldName1,
			Bubble<? extends Operator> cxt2, String fieldName2) {
		boolean set1 = setCxtMember(b, cxt1, fieldName1);
		boolean set2 = setCxtMember(b, cxt1, fieldName2);
		return set1 && set2;
	}
	
	
	private Map<Role<?>,Element> roles(Map<String,XMLElement> xmlRoles, 
														String opName) {
		Map<Role<?>,Element> roles = new HashMap<Role<?>, Element>();
		
		for(Entry<String,XMLElement> e : xmlRoles.entrySet()) {
			Role<?> role = bFactory.getRoleInstance(opName, e.getKey());
			Element el = elements.get(e.getValue());
		
			if(el == null) 
				throw new ReuseRuntimeException(
					"XML operator role not bound to a corresponding " +
					"meta-model role");
			
			roles.put(role, el);
		}
		
		return roles;
	}
	
	
	private Bubble<? extends Operator> createBubble(BInfo bi) {
		String opName = bi.getName();
		Bubble<? extends Operator> bubble = bFactory.getBubbleInstance(opName);
		Configuration<? extends Operator> config = 
								bFactory.getConfigInstance(opName);
			
		Map<String,XMLElement> xmlRoles = bi.getRoles();
		Map<Role<? extends Operator>,Element> roles = roles(xmlRoles, opName);
		
		set(config, roles);

		set(bubble, config);
		
		return bubble;
	}
	
	
	private static <T extends Operator> void set(Bubble<?> b, 
											Configuration<?> c) {
		Configuration<T> _c = (Configuration<T>) c;
		Bubble<T> _b = (Bubble<T>) b;
		_b.setConfiguration(_c);
	}
	
	
	private static <T extends Operator> void set(Configuration<?> c, 
									Map<Role<?>,Element> roles) {
		Configuration<T> _c = (Configuration<T>) c;
		
		Map<Role<T>,Element> _roles = new HashMap<Role<T>, Element>();
		for(Role<?> r : roles.keySet()) {
			Role<T> _r = (Role<T>) r;
			_roles.put(_r, roles.get(r));
		}
		_c.setRoles(_roles);
	}
	
	
	private static BInfo extract(XMLOperator b) {
		BInfo bi = new BInfo();
		
		// set operator name
		bi.setName(b.getName());
		
		// set roles
		bi.setLhsRoles(b.getLhsRoles());
		bi.setRhsRoles(b.getRhsRoles());
		
		// set context
		if(b.getParents() != null && !b.getParents().isEmpty()) {
			bi.setContext(b.getParents());
		}
		
		return bi;
	}
	
	
	/**
	 * Data transfer object for all data contained in an operator.
	 * @author alex
	 *
	 */
	private static class BInfo {
		private String name;
		private Set<XMLOperator> context = 
								new HashSet<XMLOperator>();
		private Map<String,XMLElement> lhsRoles = 
			new HashMap<String, XMLElement>();
		private Map<String,XMLElement> rhsRoles = 
			new HashMap<String, XMLElement>();
		
		private Map<String,XMLElement> roles = 
			new HashMap<String, XMLElement>();
		
		private void roles() {
			roles = new HashMap<String, XMLElement>(lhsRoles);
			roles.putAll(rhsRoles);
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Set<XMLOperator> getContext() {
			return context;
		}
		
		public boolean hasContext() {
			return !context.isEmpty();
		}
		
		public void setContext(Set<XMLOperator> context) {
			this.context = context;
		}
		
		
		public Map<String, XMLElement> getLhsRoles() {
			return lhsRoles;
		}
		
		public Map<String, XMLElement> getRhsRoles() {
			return rhsRoles;
		}
		
		public Map<String,XMLElement> getRoles() {
			return roles;
		}

		public void setLhsRoles(Map<String, XMLElement> lhsRoles) {
			this.lhsRoles = lhsRoles;
			roles();
		}
		
		public void setRhsRoles(Map<String, XMLElement> rhsRoles) {
			this.rhsRoles = rhsRoles;
			roles();
		}
		
		@Override
		public String toString() {
			return "BInfo(" + name + "," + lhsRoles + "," + rhsRoles + ")";
		}
	}
	
}
