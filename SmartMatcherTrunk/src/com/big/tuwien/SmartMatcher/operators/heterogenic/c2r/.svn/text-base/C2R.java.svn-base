package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.PriorityIterator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.FixedContextPairNode;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfigurationIterator.TREE_TYPE;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.strategy.C2CPair;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.iterator.BiFixedNode;
import com.big.tuwien.SmartMatcher.views.iterator.FixedNode;
import com.big.tuwien.SmartMatcher.views.iterator.FixedNode2;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class C2R implements Operator {
	private static Logger logger = Logger.getLogger(C2R.class);
	public final static String NAME = "C2R";
	
	private DOMView domView;
	private C2RConfigurationIterator configIt;
	private C2RBubble init;
	private C2RProperties properties;
	
	public C2R() {
		this.properties = new C2RProperties();
	}
	
	
	public C2R(C2RProperties properties) {
		this.properties = properties;
	}
	
	
	/*
	 * Wraps the given configuration iterator with a priority iterator that uses the
	 * given measures to prioritize configurations.
	 */
	private Iterator<C2RConfiguration> wrap(Iterator<C2RConfiguration> it, Collection<Measure> measures) {
		PriorityIterator p = new PriorityIterator(measures);
		Iterator<C2RConfiguration> pIt = p.getIterator(configIt);
		return pIt;
	}
	
	
	public Iterator<C2RConfiguration> getConfigurationIterator() {
		if(properties.hasPriorityMeasures())
			return wrap(configIt,properties.getPriorityMeasures());
		else
			return this.configIt;
	}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void init(C2RBubble init) throws C2RException {
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
		C2RConfiguration config = init.getConfiguration();
		
		if(config.isUnderspecified()) 
			throw new C2RException("C2RConfiguration is underspecified");
		
		TREE_TYPE type = null;
		
		if(config.fixedRHSFocusReference() 
				&& config.fixedLHSFocusClass() 
				&& config.fixedLHSContextReference1()
				&& config.fixedLHSContextReference2()) {
			type = TREE_TYPE.allFixed;
			
			Element _rhsFocusReference = config.getRoleElement(Roles.rhsFocusReference);
			Element _lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
			Element _lhsContextReference1 = config.getRoleElement(Roles.lhsContextReference1);
			Element _lhsContextReference2 = config.getRoleElement(Roles.lhsContextReference2);
			
			FixedNode rhsFocusReference = new FixedNode();
			rhsFocusReference.setRole(Roles.rhsFocusReference);
			rhsFocusReference.setCurrent(_rhsFocusReference);
			
			FixedNode lhsFocusClass = new FixedNode();
			lhsFocusClass.setRole(Roles.lhsFocusClass);
			lhsFocusClass.setCurrent(_lhsFocusClass);
			
			Set<Element> lhsContextRefPair = new HashSet<Element>();
			lhsContextRefPair.add(_lhsContextReference1);
			lhsContextRefPair.add(_lhsContextReference2);
			BiFixedNode lhsContextReferences = new BiFixedNode();
			lhsContextReferences.setCurrent(lhsContextRefPair);
			
			this.configIt = new C2RConfigurationIterator();
			this.configIt.setLHSContextReferencesNodeIt(lhsContextReferences);
			this.configIt.setLHSFocusClassNodeIt(lhsFocusClass);
			this.configIt.setRHSFocusReferenceNodeIt(rhsFocusReference);
			this.configIt.setType(type);
			
		}  else if(config.fixedLHSFocusClass() && config.fixedRHSFocusReference()) {
			/*
			Tree structure for fixed lhs-focus-class and fixed rhs-focus-reference:
			lhsFocusClass                 rhsFocusReference (roots)
			|----- lhsContextReference1 --------|
			|									|
			|-------- lhsContextReference2 -----|
			*/
			
			type = TREE_TYPE.lhsFocusClassRhsFocusReferenceFixed;
			Element _lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
			Element _rhsFocusReference = config.getRoleElement(Roles.rhsFocusReference);
			
			FixedNode2 lhsFocusClass = new FixedNode2();
			lhsFocusClass.setRole(Roles.lhsFocusClass);
			
			LHSContextReference lhsContextReferences = new LHSContextReference(this.domView);
		
			lhsFocusClass.addDependency(lhsContextReferences);
			lhsFocusClass.setCurrent(_lhsFocusClass);
			
			FixedNode2 rhsFocusReference = new FixedNode2();
			rhsFocusReference.setRole(Roles.rhsFocusReference);
			rhsFocusReference.addDependency(lhsContextReferences);
			rhsFocusReference.setCurrent(_rhsFocusReference);

			this.configIt = new C2RConfigurationIterator();
			this.configIt.setLHSContextReferencesNodeIt(lhsContextReferences);
			this.configIt.setLHSFocusClassNodeIt(lhsFocusClass);
			this.configIt.setRHSFocusReferenceNodeIt(rhsFocusReference);
			this.configIt.setType(type);
		} else if(config.fixedLHSFocusClass()) {
			/*
			Tree structure for fixed lhs-focus-class:
			lhsFocusClass     (root)
			|----- lhsContextReferences1 
			|			|--------------------|---- rhsFocusReferences
			|				|----------------|				
			|-------- lhsContextReferences2
			*/
			
			type = TREE_TYPE.lhsFocusClassFixed;
			Element _lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
			
			FixedNode2 lhsFocusClass = new FixedNode2();
			lhsFocusClass.setRole(Roles.lhsFocusClass);
			
			RHSFocusReferenceFromReferences rhsFocusReferences = new RHSFocusReferenceFromReferences(this.domView);
			
			LHSContextReference lhsContextReferences = new LHSContextReference(this.domView);
			lhsContextReferences.addDependency(rhsFocusReferences);
		
			lhsFocusClass.addDependency(lhsContextReferences);
			lhsFocusClass.setCurrent(_lhsFocusClass);
			
			this.configIt = new C2RConfigurationIterator();
			this.configIt.setLHSContextReferencesNodeIt(lhsContextReferences);
			this.configIt.setLHSFocusClassNodeIt(lhsFocusClass);
			this.configIt.setRHSFocusReferenceNodeIt(rhsFocusReferences);
			this.configIt.setType(type);
		} else if(config.fixedContexts()) {
			type = TREE_TYPE.bothContextsFixed;
			
			C2CConfiguration contextC2C1 = config.getContext1();
			C2CConfiguration contextC2C2 = config.getContext2();
			
			LHSFocusClassFromReferences lhsFocusClasses = new LHSFocusClassFromReferences(this.domView);
			
			LHSContextReference lhsContextReferences = new LHSContextReference(this.domView);
			lhsContextReferences.addDependency(lhsFocusClasses);
			
			RHSFocusReferenceFromReferences rhsFocusReferences = new RHSFocusReferenceFromReferences(this.domView);
			
			FixedContextPairNode contextPairNode = new FixedContextPairNode();
			contextPairNode.addDependency(lhsContextReferences);
			contextPairNode.addDependency(rhsFocusReferences);
			
			contextPairNode.setCurrent(new C2CPair(contextC2C1, contextC2C2));
			
			this.configIt = new C2RConfigurationIterator();
			this.configIt.setContext1(init.getContext1());
			this.configIt.setContext2(init.getContext2());
			this.configIt.setLHSContextReferencesNodeIt(lhsContextReferences);
			this.configIt.setLHSFocusClassNodeIt(lhsFocusClasses);
			this.configIt.setRHSFocusReferenceNodeIt(rhsFocusReferences);
			this.configIt.setType(type);
			
		}
	}
	
	
	public void printConfigurations() {
		while(this.configIt.hasNext()) {
			C2RConfiguration _config = this.configIt.next();
			
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
	public boolean filter(C2RConfiguration _config, C2RBubble b) {
		// filter configurations that contain a blacklisted role element
		Set<Entry<Role<C2R>,Element>> entries = _config.getRoles().entrySet();
		for(Entry<Role<C2R>,Element> entry : entries) {
			Role<C2R> r = entry.getKey();
			Element e = entry.getValue();
			if(b.getConfiguration().blacklistContains(r, e)) {
				logger.debug("Filtering: C2R-Configuration contains blacklisted element " + e.getName() + " in role " + r);
				return true;
			}
		}
		return false;
	}
	
	
	public void reset() {}
	
	
	
}
