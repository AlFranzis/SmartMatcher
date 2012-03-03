package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.PriorityIterator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfigurationIterator.TREE_TYPE;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.FixedNode;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2C implements Operator {
	public final static String NAME = "A2C";
	
	private static Logger logger = Logger.getLogger(A2C.class);
	
	private A2CProperties properties;
	private DOMView domView;
	private A2CConfigurationIterator configIt;
	private A2CBubble init;
	
	
	public A2C() {
		this.properties = new A2CProperties();
	}
	
	
	public A2C(A2CProperties properties) {
		this.properties = properties;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(A2CBubble init) throws A2CException {
		this.init = init;
	}
	
	public boolean hasMoreConfigurations() {
		return false;
	}
	
	
	public void apply() {
		
	}
	
	public void transform() {
		
	}
	
	
	public void buildTransitionTree() throws Exception {
		A2CConfiguration config = (A2CConfiguration) init.getConfiguration();
		
		if(config.isUnderspecified() && config.getContext() == null) 
			throw new A2CException("A2CConfiguration is underspecified and context is not set");
		
		Map<Role<A2C>,NodeIt<Element>> its = null;
		// NodeIt<C2CConfiguration> contextNodeIt = null;
		TREE_TYPE type = null;
		
		if(config.fixedLHSFocusAttribute() 
				&& config.fixedRHSFocusClass() 
				&& config.fixedRHSContextAttribute()
				&& config.fixedRHSContextReference()) {
			type = TREE_TYPE.allFixed;
			
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			Element _rhsContextAttribute = config.getRoleElement(Roles.rhsContextAttribute);
			Element _rhsContextReference = config.getRoleElement(Roles.rhsContextReference);
			
			FixedNode lhsFocusAttribute = new FixedNode();
			lhsFocusAttribute.setRole(Roles.lhsFocusAttribute);
			lhsFocusAttribute.setCurrent(_lhsFocusAttribute);
			
			FixedNode rhsFocusClass = new FixedNode();
			rhsFocusClass.setRole(Roles.rhsFocusClass);
			rhsFocusClass.setCurrent(_rhsFocusClass);
			
			FixedNode rhsContextAttribute = new FixedNode();
			rhsContextAttribute.setRole(Roles.rhsContextAttribute);
			rhsContextAttribute.setCurrent(_rhsContextAttribute);
			
			FixedNode rhsContextReference = new FixedNode();
			rhsContextReference.setRole(Roles.rhsContextReference);
			rhsContextReference.setCurrent(_rhsContextReference);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
			its.put(Roles.rhsFocusClass, rhsFocusClass);
			its.put(Roles.rhsContextAttribute, rhsContextAttribute);
			its.put(Roles.rhsContextReference, rhsContextReference);
			
		} else if(config.fixedLHSFocusAttribute() 
				&& config.fixedRHSFocusClass() 
				&& config.fixedRHSContextAttribute()) {
			/*
			Tree structure for fixed lhs-focus-attribute, rhs-focus-class, rhs-context-attribute:
			lhsFocusAttribute                  rhsFocusClass 	rhsContextAttribute   (roots)
			|----- rhsContextReferences --------|									
			*/
			
			type = TREE_TYPE.lhsFocusAttributeRhsFocusClassRhsContextAttributeFixed;
			
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			Element _rhsContextAttribute = config.getRoleElement(Roles.rhsContextAttribute);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			
			FixedNode lhsFocusAttribute = new FixedNode();
			lhsFocusAttribute.setRole(Roles.lhsFocusAttribute);
			lhsFocusAttribute.addDependency(rhsContextReferences);
			lhsFocusAttribute.setCurrent(_lhsFocusAttribute);
			
			FixedNode rhsFocusClass = new FixedNode();
			rhsFocusClass.setRole(Roles.rhsFocusClass);
			rhsFocusClass.addDependency(rhsContextReferences);
			rhsFocusClass.setCurrent(_rhsFocusClass);
			
			FixedNode rhsContextAttribute = new FixedNode();
			rhsContextAttribute.setRole(Roles.rhsContextAttribute);
			rhsContextAttribute.setCurrent(_rhsContextAttribute);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
			its.put(Roles.rhsFocusClass, rhsFocusClass);
			its.put(Roles.rhsContextAttribute, rhsContextAttribute);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
		} else if(config.fixedLHSFocusAttribute() && config.fixedRHSFocusClass()) {
			/*
			Tree structure for fixed lhs-focus-attribute and fixed rhs-focus-class:
			lhsFocusAttribute                  rhsFocusClass (roots)
			|----- rhsContextReferences --------|
												|
					   rhsContextAttributes ----|
			*/
			
			type = TREE_TYPE.lhsFocusAttributeRhsFocusClassFixed;
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			RHSContextAttributeNode rhsContextAttributes = new RHSContextAttributeNode(this.domView);
			
			FixedNode lhsFocusAttribute = new FixedNode();
			lhsFocusAttribute.setRole(Roles.lhsFocusAttribute);
			lhsFocusAttribute.addDependency(rhsContextReferences);
			lhsFocusAttribute.setCurrent(_lhsFocusAttribute);
			
			FixedNode rhsFocusClass = new FixedNode();
			rhsFocusClass.setRole(Roles.rhsFocusClass);
			rhsFocusClass.addDependency(rhsContextReferences);
			rhsFocusClass.addDependency(rhsContextAttributes);
			rhsFocusClass.setCurrent(_rhsFocusClass);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
			its.put(Roles.rhsFocusClass, rhsFocusClass);
			its.put(Roles.rhsContextAttribute, rhsContextAttributes);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
		} else if(config.fixedLHSFocusAttribute() && config.fixedRHSContextAttribute()) {
			/*
			Tree structure for fixed lhs-focus-attribute, rhs-context-attribute:
			lhsFocusAttribute						rhsContextAttribute (root)
			|----- rhsContextReferences						|
						|----- rhsContextAttributes  -------|
			*/
			type = TREE_TYPE.lhsFocusAttributeRhsContextAttributeFixed;
			
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			Element _rhsContextAttribute = config.getRoleElement(Roles.rhsContextAttribute);
			
			RHSFocusClassNode rhsFocusClasses = new RHSFocusClassNode(this.domView);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			rhsContextReferences.addDependency(rhsFocusClasses);
			
			FixedNode lhsFocusAttribute = new FixedNode();
			lhsFocusAttribute.setRole(Roles.lhsFocusAttribute);
			lhsFocusAttribute.addDependency(rhsContextReferences);
			lhsFocusAttribute.setCurrent(_lhsFocusAttribute);
			
			FixedNode rhsContextAttribute = new FixedNode();
			rhsContextAttribute.setRole(Roles.rhsContextAttribute);
			rhsContextAttribute.addDependency(rhsFocusClasses);
			rhsContextAttribute.setCurrent(_rhsContextAttribute);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
			its.put(Roles.rhsFocusClass, rhsFocusClasses);
			its.put(Roles.rhsContextAttribute, rhsContextAttribute);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
			
		} else if(config.fixedRHSFocusClass()) {
			/*
			Tree structure for fixed rhs-focus-class:
			rhsFocusClass (root)
			|----- rhsContextReferences
			|			|----- lhsFocusAttributes
			|----- rhsContextAttributes
			*/
			
			type = TREE_TYPE.rhsFocusClassFixed;
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			
			LHSFocusAttributeNode lhsFocusAttributes = new LHSFocusAttributeNode(this.domView);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			rhsContextReferences.addDependency(lhsFocusAttributes);
		
			RHSContextAttributeNode rhsContextAttributes = new RHSContextAttributeNode(this.domView);
			
			FixedNode rhsFocusClass = new FixedNode();
			rhsFocusClass.setRole(Roles.rhsFocusClass);
			
			rhsFocusClass.addDependency(rhsContextReferences);
			rhsFocusClass.addDependency(rhsContextAttributes);
			rhsFocusClass.setCurrent(_rhsFocusClass);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttributes);
			its.put(Roles.rhsFocusClass, rhsFocusClass);
			its.put(Roles.rhsContextAttribute, rhsContextAttributes);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
		} else if(config.fixedLHSFocusAttribute()) {
			/*
			Tree structure for fixed lhs-focus-attribute:
			lhsFocusAttribute (root)
			|----- rhsContextReferences
					|----- rhsFocusClasses
							|----- rhsContextAttributes
			*/
			
			type = TREE_TYPE.lhsFocusAttributeFixed;
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			
			RHSContextAttributeNode rhsContextAttributes = new RHSContextAttributeNode(this.domView);
			
			RHSFocusClassNode rhsFocusClasses = new RHSFocusClassNode(this.domView);
			rhsFocusClasses.addDependency(rhsContextAttributes);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			rhsContextReferences.addDependency(rhsFocusClasses);
			
			FixedNode lhsFocusAttribute = new FixedNode();
			lhsFocusAttribute.setRole(Roles.lhsFocusAttribute);
			lhsFocusAttribute.addDependency(rhsContextReferences);
			lhsFocusAttribute.setCurrent(_lhsFocusAttribute);
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
			its.put(Roles.rhsFocusClass, rhsFocusClasses);
			its.put(Roles.rhsContextAttribute, rhsContextAttributes);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
		} else if(config.fixedContext()) {
			/*
			Tree structure for fixed context:
			context (root)
			|  |----- rhsContextReferences
			|		|----- rhsFocusClasses
			|				|----- rhsContextAttributes
			|
			|-- lhsFocusAttributes
			*/
			
			type = TREE_TYPE.contextFixed;
			
			C2CConfiguration contextC2C = config.getContext();
			
			RHSContextAttributeNode rhsContextAttributes = new RHSContextAttributeNode(this.domView);
			
			RHSFocusClassNode rhsFocusClasses = new RHSFocusClassNode(this.domView);
			rhsFocusClasses.addDependency(rhsContextAttributes);
			
			RHSContextReferenceNode rhsContextReferences = new RHSContextReferenceNode(this.domView);
			rhsContextReferences.addDependency(rhsFocusClasses);
			
			LHSFocusAttributeNode lhsFocusAttributes = new LHSFocusAttributeNode(this.domView);
	
			FixedContextNode contextNode = new FixedContextNode();
			contextNode.addDependency(lhsFocusAttributes);
			contextNode.addDependency(rhsContextReferences);
			contextNode.setCurrent(contextC2C);
			
			// contextNodeIt = contextNode;
			
			its = new HashMap<Role<A2C>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute, lhsFocusAttributes);
			its.put(Roles.rhsFocusClass, rhsFocusClasses);
			its.put(Roles.rhsContextAttribute, rhsContextAttributes);
			its.put(Roles.rhsContextReference, rhsContextReferences);
			
			
			
		}
		
		this.configIt = new A2CConfigurationIterator();
		this.configIt.setContext(init.getContext());
		this.configIt.setNodeIterators(its);
		// this.configIt.setContextNodeIt(contextNodeIt);
		this.configIt.setType(type);
		
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<A2CConfiguration> wrap(Iterator<A2CConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<A2CConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	public Iterator<A2CConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else
			return this.configIt;
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			A2CConfiguration _config = this.configIt.next();
			
			if( !filter(_config, init)) {
				logger.debug(_config);
			}
		}
	}
	
	/**
	 * Returns if the given configuration contains a blacklisted element in role.
	 * @param _config
	 * @param b
	 * @return
	 */
	public boolean filter(A2CConfiguration _config, A2CBubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<A2C>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<A2C>,Element> entry : entries) {
			Role<A2C> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: A2C-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}


	public String getName() {
		return NAME;
	}
		
}
