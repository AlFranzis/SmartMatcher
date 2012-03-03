package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

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
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfigurationIterator.TREE_TYPE;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.strategy.C2CPair;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.FixedNode;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2R implements Operator {
	private static Logger logger = Logger.getLogger(A2R.class);
	public final static String NAME = "A2R";
	
	private DOMView domView;
	private A2RConfigurationIterator configIt;
	private A2RBubble init;
	private A2RProperties properties;
	
	
	public A2R() {
		this.properties = new A2RProperties();
	}
	
	
	public A2R(A2RProperties properties) {
		this.properties = properties;
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<A2RConfiguration> wrap(Iterator<A2RConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<A2RConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	public Iterator<A2RConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else
			return this.configIt;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(A2RBubble init) throws A2RException {
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
		A2RConfiguration config = init.getConfiguration();
		
		if(config.isUnderspecified()) 
			throw new A2RException("A2RConfiguration is underspecified");
		
		Map<Role<A2R>,NodeIt<Element>> its = null;
		TREE_TYPE type = null;
		
		if(config.fixedLHSFocusAttribute1() 
				&& config.fixedLHSFocusAttribute2()  
				&& config.fixedRHSFocusReference()) {
			
			type = TREE_TYPE.allFixed;
			
			Element _lhsFocusAttribute1 = config.getRoleElement(Roles.lhsFocusAttribute1);
			Element _lhsFocusAttribute2 = config.getRoleElement(Roles.lhsFocusAttribute2);
			Element _rhsFocusReference = config.getRoleElement(Roles.rhsFocusReference);
			
			FixedNode lhsFocusAttribute1 = new FixedNode();
			lhsFocusAttribute1.setRole(Roles.lhsFocusAttribute1);
			lhsFocusAttribute1.setCurrent(_lhsFocusAttribute1);
			
			FixedNode lhsFocusAttribute2 = new FixedNode();
			lhsFocusAttribute2.setRole(Roles.lhsFocusAttribute2);
			lhsFocusAttribute2.setCurrent(_lhsFocusAttribute2);
			
			FixedNode rhsFocusReference = new FixedNode();
			rhsFocusReference.setRole(Roles.rhsFocusReference);
			rhsFocusReference.setCurrent(_rhsFocusReference);
			
			its = new HashMap<Role<A2R>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute1, lhsFocusAttribute1);
			its.put(Roles.lhsFocusAttribute2, lhsFocusAttribute2);
			its.put(Roles.rhsFocusReference, rhsFocusReference);
			
		} else if(config.fixedLHSFocusAttribute1()) {
			
			type = TREE_TYPE.lhsFocusAttribute1Fixed;
			
			Element _lhsFocusAttribute1 = config.getRoleElement(Roles.lhsFocusAttribute1);
			
			LHSFocusAttribute2 lhsFocusAttributes2 = new LHSFocusAttribute2(this.domView);
			
			RHSFocusReferenceNode rhsFocusReferences = new RHSFocusReferenceNode(this.domView);
			rhsFocusReferences.addDependency(lhsFocusAttributes2);
			
			FixedNode lhsFocusAttribute1 = new FixedNode();
			lhsFocusAttribute1.setRole(Roles.lhsFocusAttribute1);
			lhsFocusAttribute1.addDependency(rhsFocusReferences);
			lhsFocusAttribute1.setCurrent(_lhsFocusAttribute1);
			
			its = new HashMap<Role<A2R>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute1, lhsFocusAttribute1);
			its.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2);
			its.put(Roles.rhsFocusReference, rhsFocusReferences);
		} else if(config.fixedContexts()) {
			type = TREE_TYPE.bothContextsFixed;
			
			C2CConfiguration contextC2C1 = config.getContext1();
			C2CConfiguration contextC2C2 = config.getContext2();
			
			
			RHSFocusReferenceNode rhsFocusReferences = new RHSFocusReferenceNode(this.domView);
			LHSFocusAttribute1 lhsFocusAttributes1 = new LHSFocusAttribute1(this.domView);
			LHSFocusAttribute2 lhsFocusAttributes2 = new LHSFocusAttribute2(this.domView);
			
			
			FixedContextPairNode contextPairNode = new FixedContextPairNode();
			contextPairNode.addDependency(lhsFocusAttributes1);
			contextPairNode.addDependency(lhsFocusAttributes2);
			contextPairNode.addDependency(rhsFocusReferences);
			
			
			contextPairNode.setCurrent(new C2CPair(contextC2C1, contextC2C2));
			its = new HashMap<Role<A2R>,NodeIt<Element>>();
			its.put(Roles.lhsFocusAttribute1, lhsFocusAttributes1);
			its.put(Roles.lhsFocusAttribute2, lhsFocusAttributes2);
			its.put(Roles.rhsFocusReference, rhsFocusReferences);
		}
			
		this.configIt = new A2RConfigurationIterator();
		this.configIt.setContext1(init.getContext1());
		this.configIt.setContext2(init.getContext2());
		this.configIt.setNodeIterators(its);
		this.configIt.setType(type);
	
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			A2RConfiguration _config = this.configIt.next();
			
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
	public boolean filter(A2RConfiguration _config, A2RBubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<A2R>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<A2R>,Element> entry : entries) {
			Role<A2R> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: A2R-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}
	
	
}
