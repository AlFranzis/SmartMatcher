package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2CConfigurationIterator implements Iterator<A2CConfiguration>{
	public enum TREE_TYPE {
		rhsFocusClassFixed, 
		lhsFocusAttributeFixed, 
		lhsFocusAttributeRhsFocusClassFixed,
		lhsFocusAttributeRhsContextAttributeFixed,
		lhsFocusAttributeRhsFocusClassRhsContextAttributeFixed,
		allFixed,
		contextFixed
		};
	
	private NodeIt<Element> lhsFocusAttributes;
	private NodeIt<Element> rhsContextReferences;
	private NodeIt<Element> rhsContextAttributes;
	private NodeIt<Element> rhsFocusClasses;
	
	// private NodeIt<C2CConfiguration> contextNodeIt;
	private C2CBubble context;
	
	protected TREE_TYPE type;
	protected Strategy strategy;
	
	
	
	
	public A2CConfigurationIterator() {}
	
	
	public A2CConfigurationIterator(Map<Role<A2C>,NodeIt<Element>> nodeIts, TREE_TYPE type) {
		setNodeIterators(nodeIts);
		setType(type);
	}
	
	
	public void setNodeIterators(Map<Role<A2C>,NodeIt<Element>> nodeIts) {
		this.lhsFocusAttributes = nodeIts.get(Roles.lhsFocusAttribute);
		this.rhsFocusClasses = nodeIts.get(Roles.rhsFocusClass);
		this.rhsContextAttributes = nodeIts.get(Roles.rhsContextAttribute);
		this.rhsContextReferences= nodeIts.get(Roles.rhsContextReference);
	}
	
	
//	public void setContextNodeIt(NodeIt<C2CConfiguration> contextNodeIt) {
//		this.contextNodeIt = contextNodeIt;
//	}
	
	
	public void setContext(C2CBubble context) {
		this.context = context;
	}
	
	
	public void setType(TREE_TYPE type) {
		this.type = type;
		determineStrategy();
	}
	
	
	private void determineStrategy() {
		if(type.equals(TREE_TYPE.rhsFocusClassFixed)) {
			this.strategy = new RHSFocusClassFixedStrategy();
		} else if(type.equals(TREE_TYPE.lhsFocusAttributeFixed)) {
			this.strategy = new LHSFocusAttributeFixedStrategy();
		} else if(type.equals(TREE_TYPE.lhsFocusAttributeRhsFocusClassFixed)) {
			this.strategy = new LHSFocusAttributeRHSFocusClassFixedStrategy();
		} else if(type.equals(TREE_TYPE.lhsFocusAttributeRhsFocusClassRhsContextAttributeFixed)) {
			this.strategy = new LHSFocusAttributeRHSFocusClassRHSContextAttributeFixedStrategy();
		} else if(type.equals(TREE_TYPE.allFixed)) {
			this.strategy = new AllFixedStrategy();
		} else if(type.equals(TREE_TYPE.lhsFocusAttributeRhsContextAttributeFixed)) {
			this.strategy = new LHSFocusAttributeRHSContextAttributeFixedStrategy();
		} else if(type.equals(TREE_TYPE.contextFixed)) {
			this.strategy = new ContextFixedStrategy();
		}
	}
	
	
	@Override
	public A2CConfiguration next() {
		Map<Role<A2C>,Element> roles = this.strategy.next();
		A2CConfiguration config = new A2CConfiguration();
		config.setRoles(roles);
		if(context != null) config.setContext((C2CConfiguration) context.getConfiguration());
		return config;
	}
	
	
	@Override
	public boolean hasNext() {
		return this.strategy.hasNext();
	}
	
	
	@Override
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
		public Map<Role<A2C>,Element> next();
		public boolean hasNext();
	}
	
	
	protected abstract class AbstractStrategy implements Strategy {
		protected Map<Role<A2C>,Element> config = new HashMap<Role<A2C>,Element>();
		protected Map<Role<A2C>,Element> completeConfig;
		protected boolean hasNextComplete = true;
		protected boolean beforeFirst = true;
		
		
		public AbstractStrategy() {
			nextComplete();
		}
		
		@Override
		public Map<Role<A2C>,Element> next() {
			Map<Role<A2C>,Element> next = new HashMap<Role<A2C>,Element>(this.completeConfig);
			nextComplete();
			return next;
		}
		
		
		@Override
		public boolean hasNext() {
			return hasNextComplete;
		}
		
		
		/**
		 * Checks if the given configuration is complete
		 */
		protected boolean isComplete(Map<Role<A2C>,Element> config) {
			return config.get(Roles.lhsFocusAttribute) != null
			&& config.get(Roles.rhsContextAttribute) != null
			&& config.get(Roles.rhsContextReference) != null
			&& config.get(Roles.rhsFocusClass) != null;
		}
		
		
		/**
		 * Returns the next configuration
		 */
		protected abstract Map<Role<A2C>,Element> nextConfig();
		
		
		/**
		 * Returns the first configuration in the tree
		 */
		protected void firstConfig() {
			config.put(Roles.rhsFocusClass, rhsFocusClasses.current());
			config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
			config.put(Roles.rhsContextReference, rhsContextReferences.current());
			config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.current());
			beforeFirst = false;
		}
		
		
		/**
		 * Returns the next complete (=all roles set) configuration.
		 */
		protected Map<Role<A2C>,Element> nextComplete() {
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
	
	protected class RHSFocusClassFixedStrategy extends AbstractStrategy {
		
		public RHSFocusClassFixedStrategy() {
			super();
		}

		
		/**
		 * Returns the next configuration
		 */
		protected Map<Role<A2C>,Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsContextAttributes.hasNext()) {
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.next());
				} else if(lhsFocusAttributes.hasNext()) {
					rhsContextAttributes.reset();
					config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
				} else if(rhsContextReferences.hasNext()) {
					rhsContextAttributes.reset();
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
					config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	
	
	protected class LHSFocusAttributeFixedStrategy extends AbstractStrategy {
		
		public LHSFocusAttributeFixedStrategy() {
			super();
		}

		
		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsContextAttributes.hasNext()) {
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.next());
				} else if(rhsFocusClasses.hasNext()) {
					config.put(Roles.rhsFocusClass, rhsFocusClasses.next());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
				} else if(rhsContextReferences.hasNext()) {
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
					config.put(Roles.rhsFocusClass, rhsFocusClasses.current());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
	}
	
	
	protected class ContextFixedStrategy extends AbstractStrategy {
		
		public ContextFixedStrategy() {
			super();
		}

		
		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsContextAttributes.hasNext()) {
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.next());
				} else if(rhsFocusClasses.hasNext()) {
					config.put(Roles.rhsFocusClass, rhsFocusClasses.next());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
				} else if(rhsContextReferences.hasNext()) {
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
					config.put(Roles.rhsFocusClass, rhsFocusClasses.current());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
				} else if(lhsFocusAttributes.hasNext()) {
					rhsContextReferences.reset();
					config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
					config.put(Roles.rhsContextReference, rhsContextReferences.current());
					config.put(Roles.rhsFocusClass, rhsFocusClasses.current());
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
	}

	
	protected class LHSFocusAttributeRHSFocusClassFixedStrategy extends AbstractStrategy {

		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsContextAttributes.hasNext()) {
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.next());
				} else if(rhsContextReferences.hasNext()) {
					rhsContextAttributes.reset();
					config.put(Roles.rhsContextAttribute, rhsContextAttributes.current());
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
	}
	
	
	protected class LHSFocusAttributeRHSContextAttributeFixedStrategy extends AbstractStrategy {
		
		
		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsFocusClasses.hasNext()) {
					config.put(Roles.rhsFocusClass, rhsFocusClasses.next());
				} else if(rhsContextReferences.hasNext()) {
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
	}
	
	
	protected class LHSFocusAttributeRHSFocusClassRHSContextAttributeFixedStrategy extends AbstractStrategy {
	
		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsContextReferences.hasNext()) {
					config.put(Roles.rhsContextReference, rhsContextReferences.next());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
	}
	
	
	protected class AllFixedStrategy extends AbstractStrategy {
		
		@Override
		protected Map<Role<A2C>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				throw new NoSuchElementException("No next configuration in strategy");	
			}
			return config;
		}
	}

}
