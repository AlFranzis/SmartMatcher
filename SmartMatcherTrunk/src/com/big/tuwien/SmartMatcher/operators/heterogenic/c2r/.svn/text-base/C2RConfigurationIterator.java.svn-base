package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class C2RConfigurationIterator implements Iterator<C2RConfiguration>{
	public enum TREE_TYPE {
		rhsFocusReferenceFixed, 
		lhsFocusClassFixed, 
		lhsFocusClassRhsFocusReferenceFixed,
		allFixed,
		bothContextsFixed
		};
	
	private NodeIt<Element> rhsFocusReferences;
	private NodeIt<Element> lhsFocusClasses;
	private NodeIt<Set<Element>> lhsContextReferences;
	
	private C2CBubble context1;
	private C2CBubble context2;
	
	protected TREE_TYPE type;
	protected Strategy strategy;
	
	
	public C2RConfigurationIterator() {}
	
	
	public void setRHSFocusReferenceNodeIt(NodeIt<Element> nodeIt) {
		this.rhsFocusReferences = nodeIt;
	}
	
	
	public void setLHSFocusClassNodeIt(NodeIt<Element> nodeIt) {
		this.lhsFocusClasses = nodeIt;
	}
	
	
	public void setLHSContextReferencesNodeIt(NodeIt<Set<Element>> nodeIt) {
		this.lhsContextReferences = nodeIt;
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
		if(type.equals(TREE_TYPE.lhsFocusClassFixed)) {
			this.strategy = new LHSFocusClassFixedStrategy();
		} else if(type.equals(TREE_TYPE.allFixed)) {
			this.strategy = new AllFixedStrategy();
		} else if(type.equals(TREE_TYPE.lhsFocusClassRhsFocusReferenceFixed)) {
			this.strategy = new LHSFocusClassRHSFocusReferenceFixedStrategy();
		} else if(type.equals(TREE_TYPE.bothContextsFixed)) {
			this.strategy = new BothContextsFixedStrategy();
		} else {
			throw new IllegalArgumentException("Given type is not implemented yet");
		}
	}
	
	
	@Override
	public C2RConfiguration next() {
		Map<Role<C2R>,Element> roles = this.strategy.next();
		C2RConfiguration config = new C2RConfiguration();
		config.setRoles(roles);
		if(context1 != null) config.setContext1((C2CConfiguration) context1.getConfiguration());
		if(context2 != null) config.setContext2((C2CConfiguration) context2.getConfiguration());
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
		public Map<Role<C2R>,Element> next();
		public boolean hasNext();
	}
	
	
	protected abstract class AbstractStrategy implements Strategy {
		protected Map<Role<C2R>,Element> config = new HashMap<Role<C2R>,Element>();
		protected Map<Role<C2R>,Element> completeConfig;
		protected boolean hasNextComplete = true;
		protected boolean beforeFirst = true;
		
		
		public AbstractStrategy() {
			nextComplete();
		}
		
		@Override
		public Map<Role<C2R>,Element> next() {
			Map<Role<C2R>,Element> next = new HashMap<Role<C2R>,Element>(this.completeConfig);
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
		protected boolean isComplete(Map<Role<C2R>,Element> config) {
			return config.get(Roles.lhsFocusClass) != null
			&& config.get(Roles.lhsContextReference1) != null
			&& config.get(Roles.lhsContextReference2) != null
			&& config.get(Roles.rhsFocusReference) != null;
		}
		
		
		/**
		 * Returns the next configuration
		 */
		protected abstract Map<Role<C2R>,Element> nextConfig();
		
		
		/**
		 * Returns the first configuration in the tree
		 */
		protected void firstConfig() {
			Set<Element> lhsContextRefPair = lhsContextReferences.current();
			if(lhsContextRefPair == null) {
				config.put(Roles.lhsContextReference1, null);
				config.put(Roles.lhsContextReference2, null);
			} else {
				assert lhsContextRefPair.size() == 2 : "Must have 2 lhs context references";
				Iterator<Element> lhsContextRefPairIt = lhsContextRefPair.iterator();
				config.put(Roles.lhsContextReference1, lhsContextRefPairIt.next());
				config.put(Roles.lhsContextReference2, lhsContextRefPairIt.next());
			}
			config.put(Roles.lhsFocusClass, lhsFocusClasses.current());
			config.put(Roles.rhsFocusReference, rhsFocusReferences.current());
			beforeFirst = false;
		}
		
		
		/**
		 * Returns the next complete (=all roles set) configuration.
		 */
		protected Map<Role<C2R>,Element> nextComplete() {
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
	
	protected class LHSFocusClassFixedStrategy extends AbstractStrategy {
		
		/**
		 * Returns the next configuration
		 */
		protected Map<Role<C2R>,Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsFocusReferences.hasNext()) {
					config.put(Roles.rhsFocusReference, rhsFocusReferences.next());
				} else if(lhsContextReferences.hasNext()) {
					Set<Element> lhsContextRefPair = lhsContextReferences.next();
					assert lhsContextRefPair.size() == 2 : "Must have 2 lhs context references";
					
					Iterator<Element> lhsContextRefPairIt = lhsContextRefPair.iterator();
				
					config.put(Roles.lhsContextReference1, lhsContextRefPairIt.next());
					config.put(Roles.lhsContextReference2, lhsContextRefPairIt.next());
					config.put(Roles.rhsFocusReference, rhsFocusReferences.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	
	
	protected class LHSFocusClassRHSFocusReferenceFixedStrategy extends AbstractStrategy {
		
		/**
		 * Returns the next configuration
		 */
		protected Map<Role<C2R>,Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(lhsContextReferences.hasNext()) {
					Set<Element> lhsContextRefPair = lhsContextReferences.next();
					assert lhsContextRefPair.size() == 2 : "Must have 2 lhs context references";
					
					Iterator<Element> lhsContextRefPairIt = lhsContextRefPair.iterator();
				
					config.put(Roles.lhsContextReference1, lhsContextRefPairIt.next());
					config.put(Roles.lhsContextReference2, lhsContextRefPairIt.next());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	
	
	protected class AllFixedStrategy extends AbstractStrategy {
		
		@Override
		protected Map<Role<C2R>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				throw new NoSuchElementException("No next configuration in strategy");	
			}
			return config;
		}
	}
	
	protected class BothContextsFixedStrategy extends AbstractStrategy {

		public BothContextsFixedStrategy() {
			super();
		}
		
		
		@Override
		protected Map<Role<C2R>, Element> nextConfig() {
			if(beforeFirst)
				firstConfig();
			else {
				if(rhsFocusReferences.hasNext()) {
					config.put(Roles.rhsFocusReference, rhsFocusReferences.next());
				} else if(lhsFocusClasses.hasNext()) {
					rhsFocusReferences.reset();
					config.put(Roles.rhsFocusReference, rhsFocusReferences.current());
					config.put(Roles.lhsFocusClass, lhsFocusClasses.next());
				} else if(lhsContextReferences.hasNext()) {
					rhsFocusReferences.reset();
					config.put(Roles.rhsFocusReference, rhsFocusReferences.current());
					
					Set<Element> lhsContextRefPair = lhsContextReferences.next();
					assert lhsContextRefPair.size() == 2 : "Must have 2 lhs context references";
					
					Iterator<Element> lhsContextRefPairIt = lhsContextRefPair.iterator();
				
					config.put(Roles.lhsContextReference1, lhsContextRefPairIt.next());
					config.put(Roles.lhsContextReference2, lhsContextRefPairIt.next());
					
					config.put(Roles.lhsFocusClass, lhsFocusClasses.current());
				} else {
					throw new NoSuchElementException("No next configuration in strategy");
				}
			}
			return config;
		}
		
	}
	

}
