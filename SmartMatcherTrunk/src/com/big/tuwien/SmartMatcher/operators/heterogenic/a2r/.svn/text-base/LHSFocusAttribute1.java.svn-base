package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext2;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class LHSFocusAttribute1 extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(LHSFocusAttribute1.class);
	

	public LHSFocusAttribute1() {
		super();
	}


	public LHSFocusAttribute1(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent) throws TransitionException {
		Role role = parent.getRole();
		
		if(role.equals(Roles.rhsFocusReference)) {
			return new AbstractTransitionQuery() {


				@Override
				public Collection<Element> transition(Context context)
						throws TransitionException {
					try {
						Element rhsFocusReference = ((SimpleContext) context).getContextElement();
						
						List<Element> lhsFocusAttributes1 = new Vector<Element>();
						
						if(rhsFocusReference == null) {
							logger.debug("Transition: rhsFocusReference: null"  + 
							" -> lhsFocusAttributes1: null");
							return lhsFocusAttributes1;
						}
						
						// get rhs target class of the given rhs focus reference
						Element rhsContextClass1 = this.domView.createQuery(
								"//rhs//class[@id = //rhs//reference[@id = $rhsFocusReference]/@target]")
									.setElement("rhsFocusReference", rhsFocusReference)
									.uniqueResult();
						
						if(rhsContextClass1 == null)
							// structural error that must not happen
							throw new TransitionException("Given reference " + rhsFocusReference + 
									" has no target class");
						
						Element lhsContextClass1 = this.domView.createQuery(
								"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", " +
								"$rhsContextClass1, \"rhsFocusElement\")]")
									.setElement("rhsContextClass1", rhsContextClass1)
									.uniqueResult();
						
						// no needed C2C mapping
						if(lhsContextClass1 == null)
							return lhsFocusAttributes1;
						
						lhsFocusAttributes1 = this.domView.createQuery(
								"//lhs//class[@id = $lhsContextClass1]/attribute[ext:unmapped(.)]")
							.setElement("lhsContextClass1", lhsContextClass1)
							.execute(RESULT_TYPE.NODE_LIST);
						
						logger.debug("Transition: rhsFocusReference: " + rhsFocusReference.getName() + 
								" -> lhsFocusAttribute1: " + lhsFocusAttributes1);
						
						return lhsFocusAttributes1;
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
						C2CConfiguration context1 = ((C2CContext2) context).getContext1();
						
						if(context1 == null) return Collections.emptyList();
						
						Element lhsContextClass1 = context1.getLHSFocusElement();
						
						List<Element> lhsFocusAttributes1 = this.domView.createQuery(
									"//lhs//class[@id = $lhsContextClass1]/attribute[ext:unmapped(.)]")
									.setElement("lhsContextClass1", lhsContextClass1)
									.execute(RESULT_TYPE.NODE_LIST);
				
						logger.debug("Transition: context1: " + context1 + 
									" -> lhsFocusAttribute1: " + lhsFocusAttributes1);
				
						return lhsFocusAttributes1;
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
	public Role<A2R> getRole() {
		return Roles.lhsFocusAttribute1;
	}
}
