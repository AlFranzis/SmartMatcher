package com.big.tuwien.SmartMatcher.views.emf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.NotificationFilter;

import sm_mm.MappingModel;
import sm_mm.Operator;
import sm_mm.Sm_mmPackage;


/**
 * This class wraps an underlying {@link MappingModel} instance and provides event notification 
 * about changes on the underlying model. The wrapping is necessary/intended to hide (filter) events
 * from the originator of an event, e.g. the originator (source) of an event should not be informed
 * about events triggered by himself. 
 * @author alex
 *
 */
public class EventFilteredEMFModel extends EContentAdapter implements EMFModel {
	private static Logger logger = Logger.getLogger(EventFilteredEMFModel.class);
	private final static int MAX_FILTERS = 10;
	
	private MappingModel mm;
	/*
	 * Registered event listeners.
	 */
	private List<EMFModelListener> listeners = new Vector<EMFModelListener>();
	/*
	 * Notifications that are matched by one of these filters are not
	 * forwarded to the registered listeners. 
	 */
	private List<NotificationFilter> filters = new ArrayList<NotificationFilter>(MAX_FILTERS);
	
	
	public EventFilteredEMFModel(MappingModel mm) {
		this.mm = mm;
		mm.eAdapters().add(this);
	}
	
	
	@Override
	public NotificationFilter getOperatorNotifications() {
		NotificationFilter filter = 
			NotificationFilter.createNotifierTypeFilter(Operator.class).or(
					NotificationFilter.createFeatureFilter(MappingModel.class, Sm_mmPackage.MAPPING_MODEL__OPERATORS));
		
		return filter;
	}
	

	@Override
	public void addOperator(final Operator op) {
		addFilter(new OperatorAddNotificationFilter(op));
		mm.getOperators().add(op);
	}

	
	@Override
	public void removeOperator(Operator op) {
		addFilter(new OperatorRemoveNotificationFilter(op));
		mm.getOperators().remove(op);
	}
	
	
	public void addListener(EMFModelListener ml) {
		if(!listeners.contains(ml)) listeners.add(ml);
	}
	
	
	public void addFilter(NotificationFilter nf) {
		if(!filters.contains(nf)) {
			filters.add(nf);
			// constrain filters size to MAX_FILTERS
			if(filters.size() > MAX_FILTERS) {
				filters.remove(filters.size() -1);
				logger.warn("List of filters exceeds MAX_FILTERS -> Removing last filter");
			}
		}
	}
	
	
	public void notifyChanged(Notification n) {
		logger.debug("notifyChanged() entered, notification: " + n);
		
		super.notifyChanged(n);
		
		if(filter(n)) {
			logger.debug("Filter notification: " + n);
			return;
		}
		
		informListeners(n);
	}
	
	
	/*
	 * Returns if there is a stored filter that matches the given notification.
	 */
	private boolean filter(Notification n) {
		NotificationFilter nf = null;
		for(NotificationFilter _nf : filters) {
			if(_nf.matches(n)) {
				nf = _nf;
				break;
			}
		}
		
		if(nf != null) {
			// remove filter instance because it is not needed anymore
			filters.remove(nf);
			return true;
		}
		return false;
	}
	
	
	private void informListeners(Notification n) {
		for(EMFModelListener l : listeners) {
			l.notifyChanged(n);
		}
	}
	
	
	/**
	 * Custom NotificationFilter which filters the addition of certain given operator instance
	 * in the EMF model.
	 * @author alex
	 *
	 */
	protected class OperatorAddNotificationFilter extends NotificationFilter.Custom {
		private Operator op;
		
		public OperatorAddNotificationFilter(Operator op){
			this.op = op;
		}

		
		@Override
		public boolean matches(Notification n) {
			NotificationFilter nf = NotificationFilter.createNotifierFilter(mm).
										and(NotificationFilter.createFeatureFilter(
												MappingModel.class, 
												Sm_mmPackage.MAPPING_MODEL__OPERATORS));
			
			if(!nf.matches(n)) return false;
			
			@SuppressWarnings("unchecked") 
			List<Operator> ops = ((EList<Operator>) mm.eGet((EReference) n.getFeature()));
			
			Operator _op = ops.get(n.getPosition());
			return _op == op;
		}
	}
	
	
	/**
	 * Custom NotificationFilter which filters the removal of a given operator instance
	 * in the EMF model.
	 * @author alex
	 *
	 */
	protected class OperatorRemoveNotificationFilter extends NotificationFilter.Custom {
		private Operator op;
		
		public OperatorRemoveNotificationFilter(Operator op){
			this.op = op;
		}

		
		@Override
		public boolean matches(Notification n) {
			NotificationFilter nf = NotificationFilter.createNotifierFilter(mm).
										and(NotificationFilter.createFeatureFilter(
												MappingModel.class, 
												Sm_mmPackage.MAPPING_MODEL__OPERATORS));
			
			if(!nf.matches(n)) return false;
			
			Operator removedOp = (Operator) n.getOldValue();
			return removedOp == op;
		}
	}
	
	
	public interface EMFModelListener {
		public void notifyChanged(Notification n);
	}

}
