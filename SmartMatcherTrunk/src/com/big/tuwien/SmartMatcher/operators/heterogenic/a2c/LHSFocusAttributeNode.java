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

public class LHSFocusAttributeNode extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(LHSFocusAttributeNode.class);

	
	public LHSFocusAttributeNode() {
		super();
	}


	public LHSFocusAttributeNode(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent)
			throws TransitionException {
		Role role = parent.getRole();
		
		if(role.equals(Roles.rhsContextReference)) {
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						Element rhsContextReference = ((SimpleContext) context).getContextElement();
						
						Element rhsReferenceClass = this.domView.createQuery(
								"//rhs//class[ext:refend(., $rhsContextReference)][ext:mapped(\"C2C\", ., \"rhsFocusElement\")]")
								.setElement("rhsContextReference", rhsContextReference)
								.uniqueResult();
						
						if(rhsReferenceClass == null) return Collections.<Element>emptyList();

						List<Element> lhsFocusAttributes = 
							this.domView.createQuery(
							"//lhs//attribute[parent::class[ext:mapped(\"C2C\",., \"lhsFocusElement\", $rhsReferenceClass, \"rhsFocusElement\")]]" +
							"[ext:unmapped(.)]")
							.setParameter("rhsReferenceClass", rhsReferenceClass)
							.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: rhsContextReference: " + rhsContextReference.getName() + 
								" -> lhsFocusAttributes: " + QueryUtil.printElementNames(lhsFocusAttributes));

						return lhsFocusAttributes;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}

			};

		} else if(role.equals(new Role("ContextC2C"))) {
		
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						C2CConfiguration c2cContext = ((C2CContext) context).getContext();
						Element lhsContextClass = c2cContext.getRoleElement(C2CConfiguration.Roles.lhsFocusClass);

						List<Element> lhsFocusAttributes = 
							this.domView.createQuery(
							"//lhs//attribute[parent::class[@id = $lhsContextClass]][ext:unmapped(.)]")
							.setElement("lhsContextClass", lhsContextClass)
							.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: C2CContext: " + c2cContext + 
								" -> lhsFocusAttributes: " + QueryUtil.printElementNames(lhsFocusAttributes));

						return lhsFocusAttributes;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}

			};
			
		} else {
			throw new TransitionException("No transition query for parent: " + parent);
		}
	}

	
	@Override
	public Role<A2C> getRole() {
		return Roles.lhsFocusAttribute;
	}
}
