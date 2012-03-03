package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt2;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery2;
import com.big.tuwien.SmartMatcher.views.iterator.C2CContext2;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class LHSContextReference extends AbstractNodeIt2 {
	private static Logger logger = Logger.getLogger(LHSContextReference.class);

	
	public LHSContextReference() {
		super();
	}


	public LHSContextReference(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Set<Element>> getTransitionQuery(NodeIt<Element> parent)
			throws TransitionException {
		Role role = parent.getRole();
		
		if(role.equals(Roles.rhsFocusReference)) {
			
			return new AbstractTransitionQuery2() {

				@Override
				public Collection<Set<Element>> transition(Context context) throws TransitionException {
					try {
						Element rhsFocusReference = ((SimpleContext) context).getContextElement();
						
						List<Element> rhsContextClasses =
							this.domView.createQuery(
									"//rhs//class[ext:refend(., $rhsFocusReference)]" +
									"[ext:mapped(\"C2C\", ., \"rhsFocusElement\")]")
									.setElement("rhsFocusReference", rhsFocusReference)
									.execute(RESULT_TYPE.NODE_LIST);
						
						if(rhsContextClasses.size() != 2) return Collections.<Set<Element>>emptyList();
						
						Element lhsContextClass1 =  this.domView.createQuery(
								"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", $rhsContextClass, \"rhsFocusElement\")]"
								).setElement("rhsContextClass", rhsContextClasses.get(0))
								.uniqueResult();
						
						Element lhsContextClass2 =  this.domView.createQuery(
								"//lhs//class[ext:mapped(\"C2C\", ., \"lhsFocusElement\", $rhsContextClass, \"rhsFocusElement\")]"
								).setElement("rhsContextClass", rhsContextClasses.get(1))
								.uniqueResult();
						
						List<Element> lhsFocusClasses =  this.domView.createQuery(
								"//lhs//class[ext:link(., $lhsContextClass1, 1) and ext:link(., $lhsContextClass2, 1)]" +
								"[ext:unmapped(.)]"
								).setElement("lhsContextClass1", lhsContextClass1)
								.setElement("lhsContextClass2", lhsContextClass2)
								.execute(RESULT_TYPE.NODE_LIST);
						
						List<Set<Element>> lhsContextRefPairs = new Vector<Set<Element>>();
						
						for(Element lhsFocusClass : lhsFocusClasses) {
							List<Element> lhsContextRefs1 =  this.domView.createQuery(
									"//lhs//reference[ext:refend($lhsFocusClass, .) and ext:refend($lhsContextClass, .)]" +
									"[ext:unmapped(.)]"
									).setElement("lhsFocusClass", lhsFocusClass)
									.setElement("lhsContextClass", lhsContextClass1)
									.execute(RESULT_TYPE.NODE_LIST);
							
							List<Element> lhsContextRefs2 =  this.domView.createQuery(
									"//lhs//reference[ext:refend($lhsFocusClass, .) and ext:refend($lhsContextClass, .)]" +
									"[ext:unmapped(.)]"
									).setElement("lhsFocusClass", lhsFocusClass)
									.setElement("lhsContextClass", lhsContextClass2)
									.execute(RESULT_TYPE.NODE_LIST);
							
							if(lhsContextRefs1.isEmpty() || lhsContextRefs2.isEmpty())
								continue;
							
							for(Element lhsContextRef1 : lhsContextRefs1) {
								for(Element lhsContextRef2 : lhsContextRefs2) {
									Set<Element> lhsContextRefPair = new HashSet<Element>();
									lhsContextRefPair.add(lhsContextRef1);
									lhsContextRefPair.add(lhsContextRef2);
									lhsContextRefPairs.add(lhsContextRefPair);
								}
							}	
						}
						
						return lhsContextRefPairs;
						
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}
				
			};
			
			
			
		} else if(role.equals(Roles.lhsFocusClass)) {
			return new AbstractTransitionQuery2() {

				@Override
				public Collection<Set<Element>> transition(Context context) throws TransitionException {
					try {
						Element lhsFocusClass = ((SimpleContext) context).getContextElement();
						
						List<Element> lhsReferences =
							this.domView.createQuery(
							"//lhs//reference[ext:refend($lhsFocusClass, .)]" +
							"[ext:mapped(\"C2C\", number(@target), \"lhsFocusElement\") " +
							"or ext:mapped(\"C2C\", number(@container), \"lhsFocusElement\")]" +
							"[ext:unmapped(.)]")
							.setElement("lhsFocusClass", lhsFocusClass)
							.execute(RESULT_TYPE.NODE_LIST);
						
						if(lhsReferences.size() < 2) return Collections.<Set<Element>>emptyList();
							
						List<Set<Element>> lhsContextRefPairs = new Vector<Set<Element>>();
						for(int i = 0; i < lhsReferences.size(); i++) {
							Element ref1 = lhsReferences.get(i);
							
							for(int j = i + 1; j < lhsReferences.size(); j++) {	
								Element ref2 = lhsReferences.get(j);
								
								Element lhsContextClass1 =  this.domView.createQuery(
								"//lhs//class[ext:refend(.,$ref)][ext:mapped(\"C2C\",.,\"lhsFocusElement\")]"
								).setElement("ref", ref1)
								.uniqueResult();
								
								Element lhsContextClass2 =  this.domView.createQuery(
										"//lhs//class[ext:refend(.,$ref)][ext:mapped(\"C2C\",.,\"lhsFocusElement\")]"
										).setElement("ref", ref2)
										.uniqueResult();
								
								Element rhsContextClass1 =  this.domView.createQuery(
										"//rhs//class[ext:mapped(\"C2C\",.,\"rhsFocusElement\",$lhsContextClass,\"lhsFocusElement\")]"
										).setElement("lhsContextClass", lhsContextClass1)
										.uniqueResult();
								
								Element rhsContextClass2 =  this.domView.createQuery(
										"//rhs//class[ext:mapped(\"C2C\",.,\"rhsFocusElement\",$lhsContextClass,\"lhsFocusElement\")]"
										).setElement("lhsContextClass", lhsContextClass2)
										.uniqueResult();
								
								/*
								List<Element> rhsFocusReferences =  this.domView.createQuery(
										"//rhs//reference[ext:refend($rhsContextClass1, .) and ext:refend($rhsContextClass2, .)]" +
										"[ext:unmapped(.)]"
								).setElement("rhsContextClass1", rhsContextClass1)
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
								
								if(rhsFocusReferences.size() > 0) {
									Set<Element> lhsContextRefPair = new HashSet<Element>();
									lhsContextRefPair.add(ref1);
									lhsContextRefPair.add(ref2);
									lhsContextRefPairs.add(lhsContextRefPair);
								}
									
							}
						}
						
						return lhsContextRefPairs;
						
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}
				
			};
				
		} else if(role.equals(new Role("ContextC2C"))) { 
			return new AbstractTransitionQuery2() {

				@Override
				public Collection<Set<Element>> transition(Context context)
						throws TransitionException {
					C2CConfiguration context1 = ((C2CContext2) context).getContext1();
					C2CConfiguration context2 = ((C2CContext2) context).getContext2();
					
					if(context1 == null) return Collections.emptyList();
					if(context2 == null) return Collections.emptyList();
					
					Element lhsContextClass1 = context1.getLHSFocusElement();
					Element lhsContextClass2 = context2.getLHSFocusElement();
					
					try {
						List<Element> lhsFocusClasses =  this.domView.createQuery(
								"//lhs//class[ext:link(., $lhsContextClass1, 1) and ext:link(., $lhsContextClass2, 1)]" +
								"[ext:unmapped(.)]"
						).setElement("lhsContextClass1", lhsContextClass1)
						.setElement("lhsContextClass2", lhsContextClass2)
						.execute(RESULT_TYPE.NODE_LIST);

						List<Set<Element>> lhsContextRefPairs = new Vector<Set<Element>>();

						for(Element lhsFocusClass : lhsFocusClasses) {
							List<Element> lhsContextRefs1 =  this.domView.createQuery(
									"//lhs//reference[ext:refend($lhsFocusClass, .) and ext:refend($lhsContextClass, .)]" +
									"[ext:unmapped(.)]"
							).setElement("lhsFocusClass", lhsFocusClass)
							.setElement("lhsContextClass", lhsContextClass1)
							.execute(RESULT_TYPE.NODE_LIST);

							List<Element> lhsContextRefs2 =  this.domView.createQuery(
									"//lhs//reference[ext:refend($lhsFocusClass, .) and ext:refend($lhsContextClass, .)]" +
									"[ext:unmapped(.)]"
							).setElement("lhsFocusClass", lhsFocusClass)
							.setElement("lhsContextClass", lhsContextClass2)
							.execute(RESULT_TYPE.NODE_LIST);

							if(lhsContextRefs1.isEmpty() || lhsContextRefs2.isEmpty())
								continue;

							for(Element lhsContextRef1 : lhsContextRefs1) {
								for(Element lhsContextRef2 : lhsContextRefs2) {
									Set<Element> lhsContextRefPair = new HashSet<Element>();
									lhsContextRefPair.add(lhsContextRef1);
									lhsContextRefPair.add(lhsContextRef2);
									lhsContextRefPairs.add(lhsContextRefPair);
								}
							}	
						}

						return lhsContextRefPairs;

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
	public Role<C2R> getRole() {
		return Roles.lhsContextReference2;
	}
}
