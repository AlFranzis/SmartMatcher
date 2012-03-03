package com.big.tuwien.SmartMatcher.operators.heterogenic.a2c;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

public class RHSContextAttributeNode extends AbstractNodeIt {
	private static Logger logger = Logger.getLogger(RHSContextAttributeNode.class);
	
	
	public RHSContextAttributeNode() {
		super();
	}


	public RHSContextAttributeNode(DOMView domView) {
		super(domView);
	}


	@Override
	protected TransitionQuery<Element> getTransitionQuery(NodeIt<Element> parent)
			throws TransitionException {
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
							" -> rhsContextAttributes: null");
							return Collections.<Element>emptyList();
						}
						
						// all attributes of the given rhs-focus-class that are not 
						// mapped 
						String query = "//rhs//attribute[parent::class[@id = $rhsFocusClassId]][ext:unmapped(.)]";
						Query q = this.domView.createQuery(query)
						.setElement("rhsFocusClassId", rhsFocusClass);
						List<Element> rhsContextAttributes = q.execute(RESULT_TYPE.NODE_LIST);

						logger.debug("Transition: rhsFocusClass: " + rhsFocusClass.getName() + 
								" -> rhsContextAttributes: " + QueryUtil.printElementNames(rhsContextAttributes));

						return rhsContextAttributes;
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
		return Roles.rhsContextAttribute;
	}
}
