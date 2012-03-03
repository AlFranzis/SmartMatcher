package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.Role;

public class C2CConfigurationIterator implements Iterator<C2CConfiguration>{
	private static Logger logger = Logger.getLogger(C2CConfigurationIterator.class);
	
	public enum TYPE {
		lhsFocusClassFixed,
		rhsFocusClassFixed,
		allFixed
	}
	
	protected Element lhsFocusClass;
	protected Element rhsFocusClass;

	protected Strategy strategy;
	protected TYPE type;
	protected DOMView domView;

	public C2CConfigurationIterator() {}
	
	
	public void setDOMView(DOMView domView) {
		this.domView = domView;
	}
	
	
	public void setLHSFocusClass(Element lhsFocusClass) {
		this.lhsFocusClass = lhsFocusClass;
	}
	
	
	public void setRHSFocusClass(Element rhsFocusClass) {
		this.rhsFocusClass = rhsFocusClass;
	}
	
	
	public void setType(TYPE type) {
		this.type = type;
		determineStrategy();
	}
	
	
	
	private void determineStrategy() {
		if(this.type.equals(TYPE.lhsFocusClassFixed)) {
			this.strategy = new LHSFocusClassFixedStrategy();
		} else if(this.type.equals(TYPE.rhsFocusClassFixed)) {
			this.strategy = new RHSFocusClassFixedStrategy();
		} else if(this.type.equals(TYPE.allFixed)) {
			this.strategy = new AllFixedStrategy();
		}
	}
	
	
	@Override
	public C2CConfiguration next() {
		Map<Role<C2C>,Element> roles = this.strategy.next();
		C2CConfiguration config = new C2CConfiguration();
		config.setRoles(roles);
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
		public Map<Role<C2C>,Element> next();
		public boolean hasNext();
	}
	
	
	protected class RHSFocusClassFixedStrategy implements Strategy {
		private Iterator<Element> lhsFocusClasses;
		
		public RHSFocusClassFixedStrategy() {
			try {
				List<Element> _lhsFocusClasses = domView.createQuery(
						"//lhs//class[ext:unmapped(.)]")
						.execute(RESULT_TYPE.NODE_LIST);
				this.lhsFocusClasses = _lhsFocusClasses.iterator();
			} catch (XPathExpressionException e) {
				// will not happen
				logger.error("XPathExpressionException", e);
			}
		}


		public boolean hasNext() {
			return lhsFocusClasses.hasNext();
		}


		public Map<Role<C2C>, Element> next() {
			if(!lhsFocusClasses.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<C2C>,Element> config = new HashMap<Role<C2C>, Element>();
				config.put(Roles.lhsFocusClass, lhsFocusClasses.next());
				config.put(Roles.rhsFocusClass, rhsFocusClass);
				return config;
			}
		}
	}
	
	
	protected class LHSFocusClassFixedStrategy implements Strategy {
		private Iterator<Element> rhsFocusClasses;
		
		public LHSFocusClassFixedStrategy() {
			try {
				List<Element> _rhsFocusClasses = domView.createQuery(
						"//rhs//class[ext:unmapped(.)]")
						.execute(RESULT_TYPE.NODE_LIST);
				this.rhsFocusClasses = _rhsFocusClasses.iterator();
			} catch (XPathExpressionException e) {
				// will not happen
				// will not happen
				logger.error("XPathExpressionException", e);
			}
		}


		public boolean hasNext() {
			return rhsFocusClasses.hasNext();
		}


		public Map<Role<C2C>, Element> next() {
			if(!rhsFocusClasses.hasNext()) {
				throw new NoSuchElementException("No next configuration in strategy");
			} else {
				Map<Role<C2C>,Element> config = new HashMap<Role<C2C>, Element>();
				config.put(Roles.rhsFocusClass, rhsFocusClasses.next());
				config.put(Roles.lhsFocusClass, lhsFocusClass);
				return config;
			}
		}
	}
	
	
	protected class AllFixedStrategy implements Strategy {
		private boolean beforeFirst = true;
		
	
		public boolean hasNext() {
			return beforeFirst ? true : false;
		}


		public Map<Role<C2C>, Element> next() {
			if(beforeFirst) {
				Map<Role<C2C>,Element> config = new HashMap<Role<C2C>, Element>();
				config.put(Roles.lhsFocusClass, lhsFocusClass);
				config.put(Roles.rhsFocusClass, rhsFocusClass);
				beforeFirst = false;
				return config;
			} else {
				throw new NoSuchElementException("No next configuration in strategy");	
			}
		}
	}

}
