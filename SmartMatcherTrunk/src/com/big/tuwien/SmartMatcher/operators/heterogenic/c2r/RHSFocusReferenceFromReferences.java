package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt3;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext2;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext2;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class RHSFocusReferenceFromReferences extends AbstractNodeIt3 {
	private static Logger logger = Logger.getLogger(RHSFocusReferenceFromReferences.class);

	
	public RHSFocusReferenceFromReferences() {
		super();
	}


	public RHSFocusReferenceFromReferences(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Set<Element>> parent)
			throws TransitionException {
		Role role = parent.getRole();
		
		if(role.equals(Roles.lhsContextReference2)) {
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context) throws TransitionException {
					try {
						Set<Element> lhsContextReferences = ((SimpleContext2) context).getContextElement();
						
						if(lhsContextReferences.size() != 2)
							throw new TransitionException("There must be 2 LHS context references");
						
						Iterator<Element> lhsContextRefPairIt = lhsContextReferences.iterator();
						Element lhsContextRef1 = lhsContextRefPairIt.next();
						Element lhsContextRef2 = lhsContextRefPairIt.next();
						
						List<Element> lhsContextClass1 = this.domView.createQuery(
						"//lhs//class[ext:refend(., $lhsContextReference1)][ext:mapped(\"C2C\", ., \"lhsFocusElement\")]")
						.setElement("lhsContextReference1", lhsContextRef1)
						.execute(RESULT_TYPE.NODE_LIST);
						
						if(lhsContextClass1.size() != 1) return Collections.<Element>emptyList();
							
						List<Element> lhsContextClass2 = this.domView.createQuery(
						"//lhs//class[ext:refend(., $lhsContextReference2)][ext:mapped(\"C2C\", ., \"lhsFocusElement\")]")
						.setElement("lhsContextReference2", lhsContextRef2)
						.execute(RESULT_TYPE.NODE_LIST);
						
						if(lhsContextClass2.size() != 1) return Collections.<Element>emptyList();

						Element rhsContextClass1 = this.domView.createQuery(
								"//rhs//class[ext:mapped(\"C2C\", $lhsContextClass1, \"lhsFocusElement\", ., \"rhsFocusElement\")]")
								.setElement("lhsContextClass1", lhsContextClass1.get(0))
								.uniqueResult();
						
						Element rhsContextClass2 = this.domView.createQuery(
						"//rhs//class[ext:mapped(\"C2C\", $lhsContextClass2, \"lhsFocusElement\", ., \"rhsFocusElement\")]")
						.setElement("lhsContextClass2", lhsContextClass2.get(0))
						.uniqueResult();
						
						/*
						List<Element> rhsFocusReferences = this.domView.createQuery(
								"//rhs//reference[ext:refend($rhsContextClass1, .) " +
								"and ext:refend($rhsContextClass2, .)]" +
								"[ext:unmapped(.)]")
								.setElement("rhsContextClass1", rhsContextClass1)
								.setElement("rhsContextClass2", rhsContextClass2)
								.execute(RESULT_TYPE.NODE_LIST);
						*/
						
						List<Element> rhsFocusReferences = this.domView.createQuery(
								"//rhs//reference[(@container = $rhsContextClass1 and @target = $rhsContextClass2) " +
								"or (@container = $rhsContextClass2 and @target = $rhsContextClass1)]" +
								"[ext:unmapped(.)]")
								.setElement("rhsContextClass1", rhsContextClass1)
								.setElement("rhsContextClass2", rhsContextClass2)
								.execute(RESULT_TYPE.NODE_LIST);
							

						logger.debug("Transition: lhsContextReferences: " + QueryUtil.printElementList(lhsContextReferences) + 
								" -> rhsFocusReference: " + QueryUtil.printElementNames(rhsFocusReferences));

						return rhsFocusReferences;
						
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
						C2CConfiguration context2 = ((C2CContext2) context).getContext2();

						if(context1 == null) return Collections.emptyList();
						if(context2 == null) return Collections.emptyList();

						Element rhsContextClass1 = context1.getRHSFocusElement();
						Element rhsContextClass2 = context2.getRHSFocusElement();

						List<Element> rhsFocusReferences = this.domView.createQuery(
								"//rhs//reference[(@container = $rhsContextClass1 and @target = $rhsContextClass2) " +
								"or (@container = $rhsContextClass2 and @target = $rhsContextClass1)]" +
						"[ext:unmapped(.)]")
						.setElement("rhsContextClass1", rhsContextClass1)
						.setElement("rhsContextClass2", rhsContextClass2)
						.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: context1: " + context1 + ", context2 : " + context2 + 
								" -> rhsFocusReference: " + QueryUtil.printElementNames(rhsFocusReferences));

						return rhsFocusReferences;
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
	public Role<C2R> getRole() {
		return Roles.rhsFocusReference;
	}
}
