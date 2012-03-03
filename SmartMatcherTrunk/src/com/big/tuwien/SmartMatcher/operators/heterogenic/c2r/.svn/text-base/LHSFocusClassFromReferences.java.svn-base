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
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.big.tuwien.SmartMatcher.views.dom.Query.RESULT_TYPE;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractNodeIt3;
import com.big.tuwien.SmartMatcher.views.iterator.AbstractTransitionQuery;
import com.big.tuwien.SmartMatcher.views.iterator.Context;
import com.big.tuwien.SmartMatcher.views.iterator.NodeIt;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.big.tuwien.SmartMatcher.views.iterator.SimpleContext2;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionException;
import com.big.tuwien.SmartMatcher.views.iterator.TransitionQuery;

public class LHSFocusClassFromReferences extends AbstractNodeIt3 {
	private static Logger logger = Logger.getLogger(LHSFocusClassFromReferences.class);

	
	public LHSFocusClassFromReferences() {
		super();
	}


	public LHSFocusClassFromReferences(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Set<Element>> parent)
			throws TransitionException {
		
			return new AbstractTransitionQuery() {

				@Override
				public Collection<Element> transition(Context context)
				throws TransitionException {
					try {
						Set<Element> lhsContextReferences = ((SimpleContext2) context).getContextElement();
						
						if(lhsContextReferences == null) {
							logger.debug("Transition: lhsContextReferences: null"  + 
							" -> lhsFocusClasses: null");
							return Collections.<Element>emptyList();
						}
						
						if(lhsContextReferences.size() != 2)
							throw new TransitionException("There must be 2 LHS context references");
						
						Iterator<Element> lhsContextRefPairIt = lhsContextReferences.iterator();
						List<Element> lhsFocusClasses = this.domView.createQuery(
								"//lhs//class[ext:refend(., $lhsContextReference1) and ext:refend(.,$lhsContextReference2)]" +
								"[ext:unmapped(.)]")
								.setElement("lhsContextReference1", lhsContextRefPairIt.next())
								.setElement("lhsContextReference2", lhsContextRefPairIt.next())
								.execute(RESULT_TYPE.NODE_LIST);
						
						if(lhsFocusClasses.size() != 1) return Collections.<Element>emptyList();
	
						return lhsFocusClasses;
					} catch (XPathExpressionException ex) {
						throw new TransitionException("Transition failed", ex);
					}
				}

			}; 
	}

	
	@Override
	public Role<C2R> getRole() {
		return Roles.lhsFocusClass;
	}
}
