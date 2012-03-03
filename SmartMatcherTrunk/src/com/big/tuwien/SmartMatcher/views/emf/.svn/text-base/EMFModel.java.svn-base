/**
 * 
 */
package com.big.tuwien.SmartMatcher.views.emf;

import org.eclipse.emf.transaction.NotificationFilter;

import sm_mm.Operator;

import com.big.tuwien.SmartMatcher.views.emf.EventFilteredEMFModel.EMFModelListener;

public interface EMFModel {
	
	/**
	 * This method returns a filter matching events (notifications) that manipulate operator mappings. 
	 * @return
	 */
	public NotificationFilter getOperatorNotifications();
	
	public void addOperator(Operator op);
	
	public void removeOperator(Operator op);
	
	public void addListener(EMFModelListener ml);
}