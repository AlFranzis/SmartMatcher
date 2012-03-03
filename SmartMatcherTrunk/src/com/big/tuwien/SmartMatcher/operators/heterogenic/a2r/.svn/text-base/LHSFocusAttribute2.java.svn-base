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

public class LHSFocusAttribute2 extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(LHSFocusAttribute2.class);
	

	public LHSFocusAttribute2() {
		super();
	}


	public LHSFocusAttribute2(DOMView domView) {
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
						
						List<Element> lhsFocusAttributes2 = new Vector<Element>();
						
						if(rhsFocusReference == null) {
							logger.debug("Transition: rhsFocusReference: null"  + 
							" -> lhsFocusAttributes2: null");
							return lhsFocusAttributes2;
						}
						
						// get rhs container class of the given rhs focus reference
						Element rhsContextClass2 = this.domView.createQuery(
								"//rhs//class[@id = //rhs//reference[@id = $rhsFocusReference]/@container]")
									.setElement("rhsFocusReference", rhsFocusReference)
									.uniqueResult();
						
						if(rhsContextClass2 == null)
							// structural error that must not happen
							throw new TransitionException("Given reference " + rhsFocusReference + 
									" has no container class");
						
						Element lhsContextClass2 = this.domView.createQuery(
								"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", " +
								"$rhsContextClass2, \"rhsFocusElement\")]")
									.setElement("rhsContextClass2", rhsContextClass2)
									.uniqueResult();
						
						// no needed C2C mapping
						if(lhsContextClass2 == null)
							return lhsFocusAttributes2;
						
						lhsFocusAttributes2 = this.domView.createQuery(
								"//lhs//class[@id = $lhsContextClass2]/attribute[ext:unmapped(.)]")
							.setElement("lhsContextClass2", lhsContextClass2)
							.execute(RESULT_TYPE.NODE_LIST);
						
						logger.debug("Transition: rhsFocusReference: " + rhsFocusReference.getName() + 
								" -> lhsFocusAttribute2: " + lhsFocusAttributes2);
						
						return lhsFocusAttributes2;
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
						C2CConfiguration context2 = ((C2CContext2) context).getContext2();
						
						if(context2 == null) return Collections.emptyList();
						
						Element lhsContextClass2 = context2.getLHSFocusElement();
						
						List<Element> lhsFocusAttributes2 = this.domView.createQuery(
									"//lhs//class[@id = $lhsContextClass2]/attribute[ext:unmapped(.)]")
									.setElement("lhsContextClass2", lhsContextClass2)
									.execute(RESULT_TYPE.NODE_LIST);
				
						logger.debug("Transition: context2: " + context2 + 
									" -> lhsFocusAttribute1: " + lhsFocusAttributes2);
				
						return lhsFocusAttributes2;
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
		return Roles.lhsFocusAttribute2;
	}
}
