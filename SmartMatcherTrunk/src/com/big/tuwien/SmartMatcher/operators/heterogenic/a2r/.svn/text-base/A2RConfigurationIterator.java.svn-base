package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2RConfigurationIterator implements Iterator<A2RConfiguration>{
	public enum TREE_TYPE {
		rhsFocusReferenceFixed, 
		lhsFocusAttribute1Fixed,
		lhsFocusAttribute2Fixed,
		allFixed,
		bothContextsFixed
		};
	
	private NodeIt<Element> lhsFocusAttributes1;
	private NodeIt<Element> lhsFocusAttributes2;
	private NodeIt<Element> rhsFocusReferences;
	
	private C2CBubble context1;
	private C2CBubble context2;
	
	protected TREE_TYPE type;
	protected Strategy strategy;
	
	
	public A2RConfigurationIterator() {}
	
	
	public A2RConfigurationIterator(Map<Role<A2R>,NodeIt<Element>> nodeIts, TREE_TYPE type) {
		setNodeIterators(nodeIts);
		setType(type);
	}
	
	
	public void setNodeIterators(Map<Role<A2R>,NodeIt<Element>> nodeIts) {
		this.lhsFocusAttributes1 = nodeIts.get(Roles.lhsFocusAttribute1);
		this.lhsFocusAttributes2 = nodeIts.get(Roles.lhsFocusAttribute2);
		this.rhsFocusReferences= nodeIts.get(Roles.rhsFocusReference);
	}
	
	
	public void setType(TREE_TYPE type) {
		this.type = type;
		determineStrategy();
	}
	
	
	public void setContext1(C2CBubble context1) {
		this.context1 = context1;
	}
	
	
	public void setContext2(C2CBubble context2) {
		this.context2 = context2;
	}
	
	
	private void determineStrategy() {
		if(type.equals(TREE_TYPE.lhsFocusAttribute1Fixed)) {
			this.strategy = new LHSFocusAttribute1FixedStrategy();
		} else if(type.equals(TREE_TYPE.allFixed)) {
			this.strategy = new AllFixedStrategy();
		} else if(type.equals(TREE_TYPE.bothContextsFixed)) {
			this.strategy = new BothContextsFixedStrategy();
		} else {
			throw new IllegalArgumentException("Given type is not implemented yet");
		}
			
	}
	
	
	public A2RConfiguration next() {
		Map<Role<A2R>,Element> roles = this.strategy.next();
		A2RConfiguration config = new A2RConfiguration();
		config.setRoles(roles);
		if(context1 != null) config.setContext1((C2CConfiguration) context1.getConfiguration());
		if(context2 != null) config.setContext2((C2CConfiguration) context2.getConfiguration());
		return config;
	}
	
	
	public boolean hasNext() {
		return this.strategy.hasNext();
	}
	
	
	public void remove() {
		throw new UnsupportedOperationException("Method remove() is not supported by " +
				"this configuration iterator");
	}
	
	
	/**
	 * The implemented strategy determines how to iterate over all possible
	 * configurations
	 * @author alex
	 *
	 */
	protected interface Strategy {
		public Map<Role<A2R>,Element> next();
		public boolean hasNext();
	}
	
	
	protected abstract class AbstractStrategy implements Strategy {
		protected Map<Role<A2R>,Element> config = new HashMap<Role<A2R>,Element>();
		protected Map<Role<A2R>,Element> completeConfig;
		protected boolean hasNextComplete = true;
		protected boolean beforeFirst = true;
		
		
		public AbstractStrategy() {
			nextComplete();
		}
		
		
		public Map<Role<A2R>,Element> next() {
			Map<Role<A2R>,Element> next = new HashMap<Role<A2R>,Element>(this.completeConfig);
			nextComplete();
			return next;
		}
		
		
		public boolean hasNext() {
			return hasNextComplete;
		}
		
		
		/**
		 * Checks if the given configuration is complete
		 */
		protected boolean isComplete(Map<Role<A2R>,Element> config) {
			return config.get(Roles.lhsFocusAttribute1) != null
			&& config.get(Roles.lhsFocusAttribute1) != null
			&& config.get(Roles.rhsFocusReference) != null;
		}
		
		
		/**
		 * Returns the next configuration
		 */
		protected abstract Map<Role<A2R>,Element> nextConfig();
		
		
		/**
		 * Returns the first configuration in the tree
		 */
		protected void firstConfig() {
			config.put(Roles.lhsFocusAttribute1, lhsFocusAttributes1.current());
			config.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2.current());
			config.put(Roles.rhsFocusReference, rhsFocusReferences.current());
			beforeFirst = false;
		}
		
		
		/**
		 * Returns the next complete (=all roles set) configuration.
		 */
		protected Map<Role<A2R>,Element> nextComplete() {
			try {
				this.completeConfig = nextConfig();
				if(!isComplete(this.completeConfig)) {
					this.completeConfig = nextComplete();
				}
			} catch (NoSuchElementException e) {
				this.completeConfig = null;
				hasNextComplete = false;
			}
			return this.completeConfig;
		}
	}
	
	
	protected class LHSFocusAttribute1FixedStrategy extends AbstractStrategy {
		
		public LHSFocusAttribute1FixedStrategy() {
			super();
		}

		
		/**
		 * Returns the next configuration
		 */
		protected Map<Role<A2R>,Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(lhsFocusAttributes2.hasNext()) {
					config.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2.next());
				} else if(rhsFocusReferences.hasNext()) {
					config.put(Roles.rhsFocusReference, rhsFocusReferences.next());
					config.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	
	
	protected class BothContextsFixedStrategy extends AbstractStrategy {
		
		public BothContextsFixedStrategy() {
			super();
		}

		
		/**
		 * Returns the next configuration
		 */
		protected Map<Role<A2R>,Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(lhsFocusAttributes1.hasNext()) {
					config.put(Roles.lhsFocusAttribute1, lhsFocusAttributes1.next());
				} else if(lhsFocusAttributes2.hasNext()) {
					lhsFocusAttributes1.reset();
					config.put(Roles.lhsFocusAttribute1, lhsFocusAttributes1.current());
					config.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2.next());
				} else if(rhsFocusReferences.hasNext()) {
					lhsFocusAttributes1.reset();
					config.put(Roles.lhsFocusAttribute1, lhsFocusAttributes1.current());
					lhsFocusAttributes2.reset();
					config.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2.current());
					config.put(Roles.rhsFocusReference, rhsFocusReferences.next());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	
	
	protected class AllFixedStrategy extends AbstractStrategy {
		
		public AllFixedStrategy() {
			super();
		}
		
		
		protected Map<Role<A2R>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				throw new NoSuchElementException("No next configuration in strategy");	
			}
			return config;
		}
	}

}
