package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class RHSContextReferenceNode extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(RHSContextReferenceNode.class);
	

	public RHSContextReferenceNode() {
		super();
	}


	public RHSContextReferenceNode(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent) throws TransitionException {
		Role role = parent.getRole();
		if(role.equals(Roles.rhsFocusClass)) {
			return new AbstractTransitionQuery() {


				@Override
				public Collection<Element> transition(Context context)
						throws TransitionException {
					try {
						Element rhsFocusClass = ((SimpleContext) context).getContextElement();
						
						if(rhsFocusClass == null) {
							logger.debug("Transition: rhsFocusClass: null"  + 
							" -> rhsContextReferences: null");
							return Collections.<Element>emptyList();
						}
						
						List<Element> rhsContextReferences = this.domView.createQuery(
						"//rhs//reference[ext:refend($rhsFocusClass, .)][ext:unmapped(.)]")
						.setElement("rhsFocusClass", rhsFocusClass)
						.execute(RESULT_TYPE.NODE_LIST);
						
						logger.debug("Transition: rhsFocusClass: " + rhsFocusClass.getName() + 
								" -> rhsContextReferences: " + QueryUtil.printElementNames(rhsContextReferences));
						
						return rhsContextReferences;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}
				
			};
			
		} else if(role.equals(Roles.lhsFocusAttribute)) {
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context)
				throws TransitionException {
					try {
						Element lhsFocusAttribute = ((SimpleContext) context).getContextElement();
						
						if(lhsFocusAttribute == null) {
							logger.debug("Transition: rhsFocusAttribute: null"  + 
							" -> rhsContextReferences: null");
							return Collections.<Element>emptyList();
						}
						
						Element lhsContextClass = this.domView.createQuery(
								"//lhs//class[attribute[@id = $lhsFocusAttributeId]]")
								.setElement("lhsFocusAttributeId", lhsFocusAttribute)
								.uniqueResult();
						
						if(lhsContextClass == null) {
							logger.debug("Transition: rhsFocusAttribute: null"  + 
							" -> rhsContextReferences: null");
							return Collections.<Element>emptyList();
						}

						Element rhsReferenceClass = this.domView.createQuery(
						"//rhs//class[ext:mapped(\"C2C\", $lhsContextClass, \"lhsFocusElement\", ., \"rhsFocusElement\")]")
						.setParameter("lhsContextClass", lhsContextClass)
						.uniqueResult();
						
						if(rhsReferenceClass == null) {
							logger.debug("Transition: rhsFocusAttribute: null"  + 
							" -> rhsContextReferences: null");
							return Collections.<Element>emptyList();
						}

						List<Element> rhsContextReferences = 
							this.domView.createQuery(
							"//rhs//reference[@target = $rhsReferenceClassId or @container = $rhsReferenceClassId][ext:unmapped(.)]")
							.setElement("rhsReferenceClassId", rhsReferenceClass)
							.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: lhsFocusAttribute: " + lhsFocusAttribute.getName() + 
								" -> rhsContextReferences: " + QueryUtil.printElementNames(rhsContextReferences));

						return rhsContextReferences;
					} catch (XPathExpressionException e1) {
						throw new TransitionException("Transition failed", e1);
					}
				}

			};

		} else if(role.equals(new Role("ContextC2C"))) {
			
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context)
					throws TransitionException {
					try {
						C2CConfiguration c2cContext = ((C2CContext) context).getContext();
						Element rhsContextClass = c2cContext.getRoleElement(C2CConfiguration.Roles.rhsFocusClass);

						List<Element> rhsContextReferences = 
							this.domView.createQuery(
							"//rhs//reference[@target = $rhsReferenceClassId or @container = $rhsReferenceClassId][ext:unmapped(.)]")
							.setElement("rhsReferenceClassId", rhsContextClass)
							.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: C2CContext: " + c2cContext + 
								" -> rhsContextReferences: " + QueryUtil.printElementNames(rhsContextReferences));

						return rhsContextReferences;
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
		return Roles.rhsContextReference;
	}
}
