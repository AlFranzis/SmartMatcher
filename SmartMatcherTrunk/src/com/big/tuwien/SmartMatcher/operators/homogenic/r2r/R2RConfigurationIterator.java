package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.DIRECTION;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class R2RConfigurationIterator implements Iterator<R2RConfiguration>{
	private static Logger logger = Logger.getLogger(R2RConfigurationIterator.class);
	
	public enum TYPE {
		lhsFocusReferenceFixed,
		rhsFocusReferenceFixed,
		allFixed,
		contextFixed,
		bothContextsFixed
	}
	
	protected Element lhsFocusReference;
	protected Element rhsFocusReference;
	protected C2CBubble context1;
	protected C2CBubble context2;
	protected DIRECTION direction;
	
	protected Strategy strategy;
	protected TYPE type;
	protected DOMView domView;
	

	
	public R2RConfigurationIterator() {}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void setLHSFocusReference(Element lhsFocusReference) {
		this.lhsFocusReference = lhsFocusReference;
	}
	
	
	public void setRHSFocusReference(Element rhsFocusReference) {
		this.rhsFocusReference = rhsFocusReference;
	}
	
	
	public void setContext1(C2CBubble context1) {
		this.context1 = context1;
	}
	
	
	public void setContext2(C2CBubble context2) {
		this.context2 = context2;
	}
	
	
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	
	public void setType(TYPE type) {
		this.type = type;
		determineStrategy();
	}
	
	
	
	private void determineStrategy() {
		if(this.type.equals(TYPE.lhsFocusReferenceFixed)) {
			this.strategy = new LHSFocusReferenceFixedStrategy();
		} else if(this.type.equals(TYPE.rhsFocusReferenceFixed)) {
			this.strategy = new RHSFocusReferenceFixedStrategy();
		} else if(this.type.equals(TYPE.allFixed)) {
			throw new IllegalArgumentException("Not implemented yet");
		} else if(this.type.equals(TYPE.contextFixed)) {
			throw new IllegalArgumentException("Not implemented yet");
		} else if(this.type.equals(TYPE.bothContextsFixed)) {
			this.strategy = new BothContextsFixedStrategy();
		}
	}
	
	
	@Override
	public R2RConfiguration next() {
		Map<Role<R2R>,Element> roles = this.strategy.next();
		R2RConfiguration config = new R2RConfiguration();
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
		public Map<Role<R2R>,Element> next();
		public boolean hasNext();
	}
	
	
	protected class RHSFocusReferenceFixedStrategy implements Strategy {
		private Iterator<Element> lhsFocusReferences;
		
		public RHSFocusReferenceFixedStrategy() {
			try {
				List<Element> rhsContextClasses = domView.createQuery(
						"//rhs//class[ext:refend(., $rhsFocusReference)]")
						.setElement("rhsFocusReference", rhsFocusReference)
						.execute(RESULT_TYPE.NODE_LIST);
				
				if(rhsContextClasses.size() != 2) 
					throw new IllegalArgumentException("Reference has more than 2 endpoints");
				
				Element lhsContextClass1 = domView.createQuery(
					"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", $rhsContextClass1, \"rhsFocusElement\")]")
					.setElement("rhsContextClass1", rhsContextClasses.get(0))
					.uniqueResult();
				
				Element lhsContextClass2 = domView.createQuery(
					"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", $rhsContextClass2, \"rhsFocusElement\")]")
					.setElement("rhsContextClass2", rhsContextClasses.get(1))
					.uniqueResult();
				
				// missing C2C context mapping(s)
				if(lhsContextClass1 == null || lhsContextClass2 == null)
					this.lhsFocusReferences = Collections.<Element>emptyList().iterator();
				
				List<Element> _lhsFocusReferences = domView.createQuery(
					"//lhs//reference[ext:refend($lhsContextClass1, .) and ext:refend($lhsContextClass2, .)]" +
					"[ext:unmapped(.)]")
					.setElement("lhsContextClass1", lhsContextClass1)
					.setElement("lhsContextClass2", lhsContextClass2)
					.execute(RESULT_TYPE.NODE_LIST);
				
				this.lhsFocusReferences = _lhsFocusReferences.iterator();
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
		}


		public boolean hasNext() {
			return lhsFocusReferences.hasNext();
		}


		public Map<Role<R2R>, Element> next() {
			if(!lhsFocusReferences.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<R2R>,Element> config = new HashMap<Role<R2R>, Element>();
				config.put(Roles.lhsFocusReference, lhsFocusReferences.next());
				config.put(Roles.rhsFocusReference, rhsFocusReference);
				return config;
			}
		}
	}
	
	
	protected class LHSFocusReferenceFixedStrategy implements Strategy {
		private Iterator<Element> rhsFocusReferences;
		
		public LHSFocusReferenceFixedStrategy() {
			try {
				List<Element> lhsContextClasses = domView.createQuery(
					"//lhs//class[ext:refend(., $lhsFocusReference)]")
					.setElement("lhsFocusReference", lhsFocusReference)
					.execute(RESULT_TYPE.NODE_LIST);
		
				if(lhsContextClasses.size() != 2) 
					throw new IllegalArgumentException("Reference has more than 2 endpoints");
		
				Element rhsContextClass1 = domView.createQuery(
					"//rhs//class[ext:mapped(\"C2C\", ., \"rhsFocusElement\", $lhsContextClass1, \"lhsFocusElement\")]")
					.setElement("lhsContextClass1", lhsContextClasses.get(0))
					.uniqueResult();
		
				Element rhsContextClass2 = domView.createQuery(
					"//rhs//class[ext:mapped(\"C2C\", ., \"rhsFocusElement\", $lhsContextClass2, \"lhsFocusElement\")]")
					.setElement("lhsContextClass2", lhsContextClasses.get(1))
					.uniqueResult();
		
				// missing C2C context mapping(s)
				if(rhsContextClass1 == null || rhsContextClass2 == null)
					this.rhsFocusReferences = Collections.<Element>emptyList().iterator();
		
				List<Element> _rhsFocusReferences = domView.createQuery(
						"//rhs//reference[ext:refend($rhsContextClass1, .) and ext:refend($rhsContextClass2, .)]" +
					"[ext:unmapped(.)]")
					.setElement("rhsContextClass1", rhsContextClass1)
					.setElement("rhsContextClass2", rhsContextClass2)
					.execute(RESULT_TYPE.NODE_LIST);
		
				this.rhsFocusReferences = _rhsFocusReferences.iterator();
				
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
		}


		public boolean hasNext() {
			return rhsFocusReferences.hasNext();
		}


		public Map<Role<R2R>, Element> next() {
			if(!rhsFocusReferences.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<R2R>,Element> config = new HashMap<Role<R2R>, Element>();
				config.put(Roles.rhsFocusReference, rhsFocusReferences.next());
				config.put(Roles.lhsFocusReference, lhsFocusReference);
				return config;
			}
		}
	}
	
	
	protected class BothContextsFixedStrategy implements Strategy {
		private StatefulIterator<Element> rhsFocusReferences;
		private StatefulIterator<Element> lhsFocusReferences;
		
		private Iterator<Map<Role<R2R>,Element>> configs;
		
		private Element lhsContextClass1;
		private Element lhsContextClass2;
		private Element rhsContextClass1;
		private Element rhsContextClass2;
		
		
		public BothContextsFixedStrategy() {
			try {
				C2CConfiguration contextConfig1 = (C2CConfiguration) context1.getConfiguration();
				lhsContextClass1 = contextConfig1.getRoleElement(
											C2CConfiguration.Roles.lhsFocusClass);
				rhsContextClass1 = contextConfig1.getRoleElement(
											C2CConfiguration.Roles.rhsFocusClass);
				
				C2CConfiguration contextConfig2 = (C2CConfiguration) context2.getConfiguration();
				lhsContextClass2 = contextConfig2.getRoleElement(
											C2CConfiguration.Roles.lhsFocusClass);
				rhsContextClass2 = contextConfig2.getRoleElement(
											C2CConfiguration.Roles.rhsFocusClass);
				
				if(direction != null && 
					(direction.equals(DIRECTION.directed) || 
					direction.equals(DIRECTION.inverse))) {
					directed(direction);	
				} else {
					undirected();
				}
				
				List<Map<Role<R2R>,Element>> _configs = build(lhsFocusReferences, rhsFocusReferences);
				configs = _configs.iterator();
			} catch (Exception e) {
				// will not happen
				logger.error(e.getMessage(), e);
			}
		}
		
		
		private void directed(DIRECTION dir) throws R2RException, XPathExpressionException {
			
				Element lhsContainer, lhsTarget, rhsContainer, rhsTarget;
				if(dir.equals(DIRECTION.directed)) {
					lhsContainer = lhsContextClass1;
					lhsTarget = lhsContextClass2;
					rhsContainer = rhsContextClass1;
					rhsTarget = rhsContextClass2;
				} else if(dir.equals(DIRECTION.inverse)) {
					lhsContainer = lhsContextClass1;
					lhsTarget = lhsContextClass2;
					rhsContainer = rhsContextClass2;
					rhsTarget = rhsContextClass1;
				} else {
					throw new R2RException("Direction must not be undirected");
				}
				
				List<Element> _lhsFocusReferences = domView.createQuery(
						"//lhs//reference[@container = $lhsContainer and @target = $lhsTarget]" +
						"[ext:unmapped(.)]")
					.setElement("lhsContainer", lhsContainer)
					.setElement("lhsTarget", lhsTarget)
					.execute(RESULT_TYPE.NODE_LIST);
				
				lhsFocusReferences = new StatefulIterator<Element>(_lhsFocusReferences);
				
				List<Element> _rhsFocusReferences = domView.createQuery(
						"//rhs//reference[@container = $rhsContainer and @target = $rhsTarget]" +
						"[ext:unmapped(.)]")
					.setElement("rhsContainer", rhsContainer)
					.setElement("rhsTarget", rhsTarget)
					.execute(RESULT_TYPE.NODE_LIST);
				
				rhsFocusReferences = new StatefulIterator<Element>(_rhsFocusReferences);
		}
		
		
		private void undirected() throws XPathExpressionException {
			List<Element> _lhsFocusReferences = domView.createQuery(
					"//lhs//reference[ext:refend($lhsContextClass1, .) and ext:refend($lhsContextClass2, .)]" +
					"[ext:unmapped(.)]")
				.setElement("lhsContextClass1", lhsContextClass1)
				.setElement("lhsContextClass2", lhsContextClass2)
				.execute(RESULT_TYPE.NODE_LIST);
				
				lhsFocusReferences = new StatefulIterator<Element>(_lhsFocusReferences);
				
				List<Element> _rhsFocusReferences = domView.createQuery(
						"//rhs//reference[ext:refend($rhsContextClass1, .) and ext:refend($rhsContextClass2, .)]" +
						"[ext:unmapped(.)]")
					.setElement("rhsContextClass1", rhsContextClass1)
					.setElement("rhsContextClass2", rhsContextClass2)
					.execute(RESULT_TYPE.NODE_LIST);
				
				rhsFocusReferences = new StatefulIterator<Element>(_rhsFocusReferences);
		}
		
		
		public boolean hasNext() {
			return configs.hasNext();
		}
		
		
		private List<Map<Role<R2R>,Element>> build(StatefulIterator<Element> lhss, StatefulIterator<Element> rhss) {
			List<Map<Role<R2R>,Element>> configs = new Vector<Map<Role<R2R>,Element>>();
			for(Element lhs : lhss) {
				rhss.reset();
				for(Element rhs : rhss) {
					Map<Role<R2R>,Element> config = new HashMap<Role<R2R>, Element>();
					config.put(Roles.lhsFocusReference, lhs);
					config.put(Roles.rhsFocusReference, rhs);
					configs.add(config);
				}
			}
			return configs;
		}
		

		public Map<Role<R2R>, Element> next() {
			if(configs.hasNext()) {
				return configs.next();
			} else {
				throw new NoSuchElementException("No next configuration in strategy");
			}
		}
		
	}

}
