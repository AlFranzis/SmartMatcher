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
import com.big.tuwien.SmartMatcher.views.dom.Query;
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

public class RHSFocusReferenceNode extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(RHSFocusReferenceNode.class);
	

	public RHSFocusReferenceNode() {
		super();
	}


	public RHSFocusReferenceNode(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent) throws TransitionException {
		Role role = parent.getRole();
		if(role.equals(Roles.lhsFocusAttribute1)) {
			return new AbstractTransitionQuery() {


				@Override
				public Collection<Element> transition(Context context)
						throws TransitionException {
					try {
						Element lhsFocusAttribute1 = ((SimpleContext) context).getContextElement();
						
						List<Element> rhsFocusReferences = new Vector<Element>();
						
						if(lhsFocusAttribute1 == null) {
							logger.debug("Transition: lhsFocusAttribute1: null"  + 
							" -> rhsFocusReference: null");
							return rhsFocusReferences;
						}
						
						// get container-class of the given lhs focus attribute1
						String query = "//lhs//class[attribute[@id = $lhsFocusAttribute1]]";
						Query q = this.domView.createQuery(query)
									.setElement("lhsFocusAttribute1", lhsFocusAttribute1);
						Element lhsContainerClass =  q.uniqueResult();
						
						if(lhsContainerClass == null)
							// structural error that must not happen
							throw new TransitionException("Given attribute " + lhsFocusAttribute1 + 
									" has no container class");
						
						Element rhsContextClass1 = this.domView.createQuery(
								"//rhs//class[ext:mapped(\"C2C\", ., \"rhsFocusElement\", " +
								"$lhsContainerClass, \"lhsFocusElement\")]")
									.setElement("lhsContainerClass", lhsContainerClass)
									.uniqueResult();
						
						// no needed C2C mapping
						if(rhsContextClass1 == null)
							return rhsFocusReferences;
						
						rhsFocusReferences = this.domView.createQuery(
								"//rhs//reference[ext:refend($rhsContextClass1, .)]" +
								"[ext:unmapped(.)]")
							.setElement("rhsContextClass1", rhsContextClass1)
							.execute(RESULT_TYPE.NODE_LIST);
						
						
						if(rhsFocusReferences.size() > 0) {
							logger.debug("Transition: lhsFocusAttribute1: " + lhsFocusAttribute1.getName() + 
									" -> rhsFocusReference: " + rhsFocusReferences);
						} else {
							logger.debug("Transition: lhsFocusAttribute1: " + lhsFocusAttribute1.getName() + 
									" -> rhsFocusReference: null");
						}
						
						return rhsFocusReferences;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}
				
			};
			
		} else if(role.equals(Roles.lhsFocusAttribute2)) {
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						Element lhsFocusAttribute2 = ((SimpleContext) context).getContextElement();
						
						List<Element> rhsFocusReferences = new Vector<Element>();
						
						if(lhsFocusAttribute2 == null) {
							logger.debug("Transition: lhsFocusAttribute2: null"  + 
							" -> rhsFocusReference: null");
							return rhsFocusReferences;
						}
						
						// get container-class of the given lhs focus attribute1
						String query = "//lhs//class[attribute[@id = $lhsFocusAttribute2]]";
						Query q = this.domView.createQuery(query)
									.setElement("lhsFocusAttribute2", lhsFocusAttribute2);
						Element lhsContainerClass =  q.uniqueResult();
						
						if(lhsContainerClass == null)
							// structural error that must not happen
							throw new TransitionException("Given attribute " + lhsFocusAttribute2 + 
									" has no container class");
						
						Element rhsContextClass2 = this.domView.createQuery(
								"//rhs//class[ext:mapped(\"C2C\", ., \"rhsFocusElement\", " +
								"$lhsContainerClass, \"lhsFocusElement\")]")
									.setElement("lhsContainerClass", lhsContainerClass)
									.uniqueResult();
						
						// no needed C2C mapping
						if(rhsContextClass2 == null)
							return rhsFocusReferences;
						
						rhsFocusReferences = this.domView.createQuery(
								"//rhs//reference[ext:refend($rhsContextClass2, .)]" +
								"[ext:unmapped(.)]")
							.setElement("rhsContextClass2", rhsContextClass2)
							.execute(RESULT_TYPE.NODE_LIST);
						
						
						if(rhsFocusReferences.size() > 0) {
							logger.debug("Transition: lhsFocusAttribute2: " + lhsFocusAttribute2.getName() + 
									" -> rhsFocusReference: " + rhsFocusReferences);
						} else {
							logger.debug("Transition: lhsFocusAttribute2: " + lhsFocusAttribute2.getName() + 
									" -> rhsFocusReference: null");
						}
						
						return rhsFocusReferences;
					} catch (XPathExpressionException e1) {
						throw new TransitionException("Transition failed", e1);
					}
				}

			};

		} else if(role.equals(new Role("ContextC2C"))) {
			return new AbstractTransitionQuery() {
			
				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						C2CConfiguration context1 = ((C2CContext2) context).getContext1();
						C2CConfiguration context2 = ((C2CContext2) context).getContext2();
						
						if(context1 == null || context2 == null) return Collections.emptyList();
						
						Element rhsContextClass1 = context1.getRHSFocusElement();
						Element rhsContextClass2 = context2.getRHSFocusElement();
						
						List<Element> _rhsFocusReferences = domView.createQuery(
								"//rhs//reference[ext:refend($rhsContextClass1, .) and ext:refend($rhsContextClass2, .)]" +
								"[ext:unmapped(.)]")
							.setElement("rhsContextClass1", rhsContextClass1)
							.setElement("rhsContextClass2", rhsContextClass2)
							.execute(RESULT_TYPE.NODE_LIST);

						return _rhsFocusReferences;
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
		return Roles.rhsFocusReference;
	}
}
