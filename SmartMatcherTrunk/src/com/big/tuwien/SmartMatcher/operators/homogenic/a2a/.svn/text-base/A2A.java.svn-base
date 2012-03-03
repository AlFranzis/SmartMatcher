package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.PriorityIterator;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfigurationIterator.TYPE;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2A implements Operator {
	public final static String NAME = "A2A";
	
	private static Logger logger = Logger.getLogger(A2A.class);
	
	private A2AProperties properties;
	private DOMView domView;
	private A2AConfigurationIterator configIt;
	private A2ABubble init;
	
	
	public A2A() {
		this.properties = new A2AProperties();
	}
	
	
	public A2A(A2AProperties properties) {
		this.properties = properties;
	}
	
	
	public Iterator<A2AConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else 	
			return this.configIt;
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<A2AConfiguration> wrap(Iterator<A2AConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<A2AConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(A2ABubble init) throws A2AException {
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
		A2AConfiguration config = (A2AConfiguration) init.getConfiguration();
		C2CBubble context = init.getContext();
		
		if(config.isUnderspecified() && context == null) 
			throw new A2AException("A2AConfiguration is underspecified");
		
		if(config.fixedLHSFocusAttribute() 
			&& config.fixedRHSFocusAttribute()) {
			
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			Element _rhsFocusAttribute = config.getRoleElement(Roles.rhsFocusAttribute);
			this.configIt = new A2AConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusAttribute(_lhsFocusAttribute);
			this.configIt.setRHSFocusAttribute(_rhsFocusAttribute);	
			this.configIt.setType(TYPE.allFixed);
		} else if(config.fixedLHSFocusAttribute()) {
			Element _lhsFocusAttribute = config.getRoleElement(Roles.lhsFocusAttribute);
			this.configIt = new A2AConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusAttribute(_lhsFocusAttribute);
			this.configIt.setType(TYPE.lhsFocusAttributeFixed);
			
		} else if(config.fixedRHSFocusAttribute()) {
			Element _rhsFocusAttribute = config.getRoleElement(Roles.rhsFocusAttribute);
			this.configIt = new A2AConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setRHSFocusAttribute(_rhsFocusAttribute);
			this.configIt.setType(TYPE.rhsFocusAttributeFixed);
		} else if(context != null) {
			this.configIt = new A2AConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setContext(context);
			this.configIt.setType(TYPE.contextFixed);
		} else {
			throw new Exception("Initial configuration is underspecified. At least the LHS " +
					"or the RHS focus attribute or the C2C-context must be fixed");
		}
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			A2AConfiguration _config = this.configIt.next();
			
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
	public boolean filter(A2AConfiguration _config, A2ABubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<A2A>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<A2A>,Element> entry : entries) {
			Role<A2A> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: A2A-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}

	
}
