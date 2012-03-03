package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class A2AConfigurationIterator implements Iterator<A2AConfiguration>{
	private static Logger logger = Logger.getLogger(A2AConfigurationIterator.class);
	
	public enum TYPE {
		lhsFocusAttributeFixed,
		rhsFocusAttributeFixed,
		allFixed,
		contextFixed
	}
	
	protected Element lhsFocusAttribute;
	protected Element rhsFocusAttribute;
	protected C2CBubble context;

	protected Strategy strategy;
	protected TYPE type;
	protected DOMView domView;

	
	public A2AConfigurationIterator() {}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void setLHSFocusAttribute(Element lhsFocusAttribute) {
		this.lhsFocusAttribute = lhsFocusAttribute;
	}
	
	
	public void setRHSFocusAttribute(Element rhsFocusAttribute) {
		this.rhsFocusAttribute = rhsFocusAttribute;
	}
	
	
	public void setContext(C2CBubble context) {
		this.context = context;
	}
	
	
	public void setType(TYPE type) {
		this.type = type;
		determineStrategy();
	}
	
	
	
	private void determineStrategy() {
		if(this.type.equals(TYPE.lhsFocusAttributeFixed)) {
			this.strategy = new LHSFocusAttributeFixedStrategy();
		} else if(this.type.equals(TYPE.rhsFocusAttributeFixed)) {
			this.strategy = new RHSFocusAttributeFixedStrategy();
		} else if(this.type.equals(TYPE.allFixed)) {
			this.strategy = new AllFixedStrategy();
		} else if(this.type.equals(TYPE.contextFixed)) {
			this.strategy = new ContextFixedStrategy();
		}
	}
	
	
	@Override
	public A2AConfiguration next() {
		Map<Role<A2A>,Element> roles = this.strategy.next();
		A2AConfiguration config = new A2AConfiguration();
		config.setRoles(roles);
		config.setContext((C2CConfiguration) context.getConfiguration());
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
		public Map<Role<A2A>,Element> next();
		public boolean hasNext();
	}
	
	
	protected class RHSFocusAttributeFixedStrategy implements Strategy {
		private Iterator<Element> lhsFocusAttributes;
		
		public RHSFocusAttributeFixedStrategy() {
			try {
				Element lhsContextClass = domView.createQuery(
						"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", " +
					"number(//rhs//class[attribute/@id = $rhsFocusAttribute]/@id), \"rhsFocusElement\")]")
					.setElement("rhsFocusAttribute", rhsFocusAttribute)
					.uniqueResult();
				
				if(lhsContextClass == null) {
					this.lhsFocusAttributes = Collections.<Element>emptyList().iterator();
				} else {
					List<Element> _lhsFocusAttributes = domView.createQuery(
						"//lhs//class[@id = $lhsContextClass]/attribute[ext:unmapped(.)]")
						.setElement("lhsContextClass", lhsContextClass)
						.execute(RESULT_TYPE.NODE_LIST);
					this.lhsFocusAttributes = _lhsFocusAttributes.iterator();
				}
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
		}


		public boolean hasNext() {
			return lhsFocusAttributes.hasNext();
		}


		public Map<Role<A2A>, Element> next() {
			if(!lhsFocusAttributes.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
				config.put(Roles.rhsFocusAttribute, rhsFocusAttribute);
				return config;
			}
		}
	}
	
	
	protected class LHSFocusAttributeFixedStrategy implements Strategy {
		private Iterator<Element> rhsFocusAttributes;
		
		public LHSFocusAttributeFixedStrategy() {
			try {
				Element rhsContextClass = domView.createQuery(
						"//rhs//class[ext:mapped(\"C2C\", ., \"rhsFocusElement\", " +
					"number(//lhs//class[attribute/@id = $lhsFocusAttribute]/@id), \"lhsFocusElement\")]")
					.setElement("lhsFocusAttribute", lhsFocusAttribute)
					.uniqueResult();
				
				if(rhsContextClass == null) {
					this.rhsFocusAttributes = Collections.<Element>emptyList().iterator();
				} else {
					List<Element> _rhsFocusAttributes = domView.createQuery(
						"//rhs//class[@id = $rhsContextClass]/attribute[ext:unmapped(.)]")
						.setElement("rhsContextClass", rhsContextClass)
						.execute(RESULT_TYPE.NODE_LIST);
					this.rhsFocusAttributes = _rhsFocusAttributes.iterator();
				}
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
		}


		public boolean hasNext() {
			return rhsFocusAttributes.hasNext();
		}


		public Map<Role<A2A>, Element> next() {
			if(!rhsFocusAttributes.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				config.put(Roles.rhsFocusAttribute, rhsFocusAttributes.next());
				config.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
				return config;
			}
		}
	}
	
	
	protected class AllFixedStrategy implements Strategy {
		/*
		 * has next() method been called yet ?
		 */
		private boolean beforeFirst = true;
		/*
		 * Context C2C Mapping exists ?
		 */
		private boolean hasC2CContextMapping = true;
		
		
		public AllFixedStrategy() {
			try {
				// check if there exists a lhsContextClass and rhsContextClass 
				// for the given lhsFocusAttribute and rhsFocusAttribute
				Element lhsContextClass = domView.createQuery(
						"//lhs//class[number(attribute/@id) = $lhsFocusAttribute]")
						.setElement("lhsFocusAttribute", lhsFocusAttribute)
					.uniqueResult();
				
				Element rhsContextClass = domView.createQuery(
					"//rhs//class[number(attribute/@id) = $rhsFocusAttribute]" +
					"[ext:mapped(\"C2C\", $lhsContextClass, \"lhsFocusElement\", ., \"rhsFocusElement\")]")
					.setElement("rhsFocusAttribute", rhsFocusAttribute)
					.setElement("lhsContextClass", lhsContextClass)
					.uniqueResult();
				
				if(rhsContextClass == null) hasC2CContextMapping = false;
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
			
		}
	
		public boolean hasNext() {
			return (hasC2CContextMapping && beforeFirst) ? true : false;
		}


		public Map<Role<A2A>, Element> next() {
			if(hasC2CContextMapping && beforeFirst) {
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				config.put(Roles.lhsFocusAttribute, lhsFocusAttribute);
				config.put(Roles.rhsFocusAttribute, rhsFocusAttribute);
				beforeFirst = false;
				return config;
			} else {
				throw new NoSuchElementException("No next configuration in strategy");	
			}
		}
	}
	
	
	
	protected class ContextFixedStrategy implements Strategy {
		/*
		 * has next() method been called yet ?
		 */
		private boolean beforeFirst = true;
		private StatefulIterator<Element> rhsFocusAttributes;
		private StatefulIterator<Element> lhsFocusAttributes;
		
		
		public ContextFixedStrategy() {
			try {
				C2CConfiguration contextConfig = (C2CConfiguration) context.getConfiguration();
				Element lhsContextClass = contextConfig.getRoleElement(
											C2CConfiguration.Roles.lhsFocusClass);
				Element rhsContextClass = contextConfig.getRoleElement(
											C2CConfiguration.Roles.rhsFocusClass);
				
				List<Element> _lhsFocusAttributes = domView.createQuery(
					"//lhs//class[number(@id) = $lhsContextClass]/attribute[ext:unmapped(.)]")
					.setElement("lhsContextClass", lhsContextClass)
					.execute(RESULT_TYPE.NODE_LIST);
				this.lhsFocusAttributes = new StatefulIterator<Element>(_lhsFocusAttributes);
				
				List<Element> _rhsFocusAttributes = domView.createQuery(
					"//rhs//class[number(@id) = $rhsContextClass]/attribute[ext:unmapped(.)]")
					.setElement("rhsContextClass", rhsContextClass)
					.execute(RESULT_TYPE.NODE_LIST);
				this.rhsFocusAttributes = new StatefulIterator<Element>(_rhsFocusAttributes);
				
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
			
		}
	
		
		public boolean hasNext() {
			if(beforeFirst) {
				return lhsFocusAttributes.hasNext() 
				&& rhsFocusAttributes.hasNext();
			} else {
				return lhsFocusAttributes.hasNext() 
					|| rhsFocusAttributes.hasNext();
			}
		}


		public Map<Role<A2A>, Element> next() {
			if(beforeFirst) {
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
				config.put(Roles.rhsFocusAttribute, rhsFocusAttributes.next());
				beforeFirst = false;
				return config;
			} else if(lhsFocusAttributes.hasNext()){
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
				config.put(Roles.rhsFocusAttribute, rhsFocusAttributes.current());
				return config;
			} else if(rhsFocusAttributes.hasNext()) {
				Map<Role<A2A>,Element> config = new HashMap<Role<A2A>, Element>();
				lhsFocusAttributes.reset();
				config.put(Roles.lhsFocusAttribute, lhsFocusAttributes.next());
				config.put(Roles.rhsFocusAttribute, rhsFocusAttributes.next());
				return config;
			} else {
				throw new NoSuchElementException("No next configuration in strategy");
			}
		}
	}

}
