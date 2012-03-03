package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class RHSFocusClassNode extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(RHSFocusClassNode.class);
	

	public RHSFocusClassNode() {
		super();
	}


	public RHSFocusClassNode(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent) throws TransitionException {
		Role role = parent.getRole();
		if(role.equals(Roles.rhsContextAttribute)) {
			return new AbstractTransitionQuery() {


				@Override
				public Collection<Element> transition(Context context)
						throws TransitionException {
					try {
						Element rhsContextAttribute = ((SimpleContext) context).getContextElement();
						
						List<Element> rhsFocusClasses = new Vector<Element>(1);
						
						if(rhsContextAttribute == null) {
							logger.debug("Transition: rhsContextAttribute: null"  + 
							" -> rhsFocusClass: null");
							return rhsFocusClasses;
						}
						
						// get container-class of the given rhs-context-attribute 
						String query = "//rhs//class[attribute[@id = $rhsContextAttributeId]][ext:unmapped(.)]";
						Query q = this.domView.createQuery(query)
									.setElement("rhsContextAttributeId", rhsContextAttribute);
						Element rhsFocusClass =  q.uniqueResult();
						
						if(rhsFocusClass != null) {
							rhsFocusClasses.add(rhsFocusClass);
							logger.debug("Transition: rhsContextAttribute: " + rhsContextAttribute.getName() + 
									" -> rhsFocusClass: " + rhsFocusClass.getName());
						} else {
							logger.debug("Transition: rhsContextAttribute: " + rhsContextAttribute.getName() + 
									" -> rhsFocusClass: null");
						}
						
						return rhsFocusClasses;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}
				
			};
			
		} else if(role.equals(Roles.rhsContextReference)) {
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						Element rhsContextReference = ((SimpleContext) context).getContextElement();
						
						if(rhsContextReference == null) {
							logger.debug("Transition: rhsContextReference: null"  + 
							" -> rhsFocusClass: null");
							return Collections.<Element>emptyList();
						}
						
						
						List<Element> rhsFocusClasses = this.domView.createQuery(
						"//rhs//class[ext:refend(., $rhsContextReference)][ext:unmapped(.)]")
						.setElement("rhsContextReference", rhsContextReference)
						.execute(RESULT_TYPE.NODE_LIST);
						
						logger.debug("Transition: rhsContextReference: " + rhsContextReference.getName() + 
								" -> rhsFocusClasses: " + QueryUtil.printElementNames(rhsFocusClasses));
						
						
						return rhsFocusClasses;
					} catch (XPathExpressionException e1) {
						throw new TransitionException("Transition failed", e1);
					}
				}

			};

		} else {
			throw new TransitionException("No transition query for parent: " + parent);
		}
	}


	@Override
	public Role<A2C> getRole() {
		return Roles.rhsFocusClass;
	}
}
