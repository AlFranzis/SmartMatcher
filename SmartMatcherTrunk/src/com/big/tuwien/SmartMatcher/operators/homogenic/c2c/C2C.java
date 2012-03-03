package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.PriorityIterator;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfigurationIterator.TYPE;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class C2C implements Operator {
	public final static String NAME = "C2C";
	
	private static Logger logger = Logger.getLogger(C2C.class);
	
	private C2CProperties properties;
	private DOMView domView;
	private C2CConfigurationIterator configIt;
	private C2CBubble init;
	
	
	public C2C() {
		this.properties = new C2CProperties();
	}
	
	
	public C2C(C2CProperties properties) {
		this.properties = properties;
	}
	
	
	public void setProperties(C2CProperties properties) {
		this.properties = properties;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(C2CBubble init) throws C2CException {
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
		C2CConfiguration config = (C2CConfiguration) init.getConfiguration();
		
		if(config.isUnderspecified()) 
			throw new C2CException("C2CConfiguration is underspecified");
		
		if(config.fixedLHSFocusClass() 
			&& config.fixedRHSFocusClass()) {
			
			Element _lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			this.configIt = new C2CConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusClass(_lhsFocusClass);
			this.configIt.setRHSFocusClass(_rhsFocusClass);	
			this.configIt.setType(TYPE.allFixed);
		} else if(config.fixedLHSFocusClass()) {
			Element _lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
			this.configIt = new C2CConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusClass(_lhsFocusClass);
			this.configIt.setType(TYPE.lhsFocusClassFixed);
			
		} else if(config.fixedRHSFocusClass()) {
			Element _rhsFocusClass = config.getRoleElement(Roles.rhsFocusClass);
			this.configIt = new C2CConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setRHSFocusClass(_rhsFocusClass);
			this.configIt.setType(TYPE.rhsFocusClassFixed);
		} else {
			throw new Exception("Initial configuration is underspecified. At least the LHS " +
					"or the RHS focus class must be fixed");
		}
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			C2CConfiguration _config = this.configIt.next();
			
			if( !filter(_config, init)) {
				logger.debug(_config);
			}
		}
	}
	
	
	public Iterator<C2CConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else 	
			return this.configIt;
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<C2CConfiguration> wrap(Iterator<C2CConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<C2CConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	/**
	 * Returns if the given configuration contains a blacklisted element in role.
	 * @param _config
	 * @param b
	 * @return
	 */
	public boolean filter(C2CConfiguration _config, C2CBubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<C2C>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<C2C>,Element> entry : entries) {
			Role<C2C> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: C2C-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}

	
}
