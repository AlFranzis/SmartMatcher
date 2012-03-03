package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.PriorityIterator;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.DIRECTION;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfigurationIterator.TYPE;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class R2R implements Operator {
	public final static String NAME = "R2R";
	
	private static Logger logger = Logger.getLogger(R2R.class);
	
	private R2RProperties properties;
	private DOMView domView;
	private R2RConfigurationIterator configIt;
	private R2RBubble init;
	
	
	public R2R() {
		this.properties = new R2RProperties();
	}
	
	
	public R2R(R2RProperties properties) {
		this.properties = properties;
	}
	
	
	public Iterator<R2RConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else 	
			return this.configIt;
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<R2RConfiguration> wrap(Iterator<R2RConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<R2RConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(R2RBubble init) throws R2RException {
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
		R2RConfiguration config = (R2RConfiguration) init.getConfiguration();
		DIRECTION direction = config.getDirection();
		C2CBubble context1 = init.getContext1();
		C2CBubble context2 = init.getContext2();
		
		if(config.isUnderspecified() && context1 == null && context2 == null) 
			throw new R2RException("A2AConfiguration is underspecified");
		
		if(config.fixedLHSFocusReference() 
			&& config.fixedRHSFocusReference()) {
			
			Element _lhsFocusReference = config.getRoleElement(Roles.lhsFocusReference);
			Element _rhsFocusReference = config.getRoleElement(Roles.rhsFocusReference);
			this.configIt = new R2RConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusReference(_lhsFocusReference);
			this.configIt.setRHSFocusReference(_rhsFocusReference);	
			this.configIt.setDirection(direction);
			this.configIt.setType(TYPE.allFixed);
		} else if(config.fixedLHSFocusReference()) {
			Element _lhsFocusReference = config.getRoleElement(Roles.lhsFocusReference);
			this.configIt = new R2RConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setLHSFocusReference(_lhsFocusReference);
			this.configIt.setDirection(direction);
			this.configIt.setType(TYPE.lhsFocusReferenceFixed);
			
		} else if(config.fixedRHSFocusReference()) {
			Element _rhsFocusReference = config.getRoleElement(Roles.rhsFocusReference);
			this.configIt = new R2RConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setRHSFocusReference(_rhsFocusReference);
			this.configIt.setDirection(direction);
			this.configIt.setType(TYPE.rhsFocusReferenceFixed);
		} else if(context1 != null && context2 != null) {
			this.configIt = new R2RConfigurationIterator();
			this.configIt.setDOMView(this.domView);
			this.configIt.setContext1(context1);
			this.configIt.setContext2(context2);
			this.configIt.setDirection(direction);
			this.configIt.setType(TYPE.bothContextsFixed);
		} else {
			throw new Exception("Initial configuration is underspecified. At least the LHS " +
					"or the RHS focus reference or the contexts must be fixed");
		}
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			R2RConfiguration _config = this.configIt.next();
			
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
	public boolean filter(R2RConfiguration _config, R2RBubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<R2R>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<R2R>,Element> entry : entries) {
			Role<R2R> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: R2R-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}

	
}
