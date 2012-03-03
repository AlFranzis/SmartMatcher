package com.big.tuwien.SmartMatcher.views.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import sm_mm.MappingModel;
import sm_mm.Operator;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleFactory;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewListener;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.SmartMatcher.views.emf.EventFilteredEMFModel.EMFModelListener;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class EMFAdapter {
	public static final String ROLE_ANNOTATION_SOURCE = "role";
	public static final String ROLE_ANNOTATION_ROLENAME_DETAIL = "rolename";
	
	private static Logger logger = Logger.getLogger(EMFAdapter.class);
	
	protected BubbleFactory bf;
	protected GenericEMFFactory of;
	
	protected EMFModel emfModel;
	protected BVMediator bvMediator;
	protected Map<Element,sm_mm.Element> elements = new HashMap<Element,sm_mm.Element>();
	protected BubbleBuilder bb;
	protected EMFOperatorBuilder ob;
	protected BiMap<Bubble<?>, sm_mm.Operator> mappings = new HashBiMap<Bubble<?>, sm_mm.Operator>();
	

	public EMFAdapter() {}
	
	
	public void setBVMediator(BVMediator bvMediator) {
		this.bvMediator = bvMediator;
	}
	
	
	public void setEMFModel(EMFModel emfModel) {
		this.emfModel = emfModel;
	}
	
	
	public void setBubbleFactory(BubbleFactory bf) {
		this.bf = bf;
	}
	
	
	public void setEMFOpFactory(GenericEMFFactory of) {
		this.of = of;
	}
	
	
	public void setElementMap(Map<Element, sm_mm.Element> m) {
		if (!elements.isEmpty()) elements.clear();
		elements.putAll(m);
	}
	
	
	public void adapt() {
		bb = new BubbleBuilder(bf, elements);
		ob = new EMFOperatorBuilder(of, elements);
		
		// add adapter to EMFModel 
		emfModel.addListener(new EMFModelAdapter());
		// add adapter to BubbleView
		bvMediator.addListener(new BubbleViewAdapter());
	}
	
	
	public Bubble<?> getAssociatedBubble(Operator op) {
		return mappings.inverse().get(op);
	}
	
	
	public sm_mm.Operator getAssociatedOperator(Bubble<?> b) {
		return mappings.get(b);
	}
	
	
	public void disassociate(Operator op) {
		mappings.inverse().remove(op);
	}
	
	
	public void disassociate(Bubble<?> b) {
		mappings.remove(b);
	}
	
	
	public void associate(Bubble<?> b, Operator op) {
		mappings.put(b, op);
	}
	
	
	/**
	 * Adapter which is notified about events at the EMFModel. The adapter
	 * forwards these events to the BubbleView.
	 * @author alex
	 *
	 */
	protected class EMFModelAdapter implements EMFModelListener {
		
		public void notifyChanged(Notification notification) {
			logger.debug("notifyChanged() called, notification: " + notification);
			
			// super.notifyChanged(notification);
			
			// ignore non-state-changing events
			if(notification.isTouch()) return;
			
			// only propagate events (notifications) that manipulate mappings
			if(!emfModel.getOperatorNotifications().matches(notification))
				return;
				
			switch(notification.getEventType()) {
			
				// notification that an operator was added in the EMF model 
				// -> new operator was added to the reference named 'operators' 
				// which is a feature of the MappingModel element (= notifier)
				case Notification.ADD:
					// strategy for added operators
					// 1) add (create) assoc. bubble
					MappingModel mm = checkNotifier(notification, sm_mm.MappingModel.class);
					EReference operators = checkFeature(notification, EReference.class, "operators");
					Operator op = getAffectedOperator(notification, operators, mm);
					
					logger.debug("Added EMF Operator found => create associated bubble; operator: " + op);
					
					Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator> bubble = 
											bb.build(op);
					
					//bubbleView.addCurrentLevelBubble(bubble);
					bubble.setState(Bubble.STATE.eval2TRUE);
					bvMediator.addBubble(bubble);
					associate(bubble, op);
					logger.debug("Created associated bubble: " + bubble);
					break;
					
				// notification that am operator was removed from the EMF model
				// -> operator was removed from reference 'operators'
				case Notification.REMOVE:
					// strategy for removed operators
					// 1) remove assoc. bubble
					Object oldValue = notification.getOldValue();
					if(!(oldValue instanceof Operator))
						throw new IllegalArgumentException("Expected operator type for old value");
					
					Operator remOp = (Operator) oldValue;
					
					logger.debug("Removed EMF Operator found => set associated bubble to eval false; removed op: " + remOp);
					
					Bubble<?> invalidBubble = getAssociatedBubble(remOp);
					//bubble2.setState(Bubble.STATE.eval2FALSE);
					bvMediator.changeState(invalidBubble, Bubble.STATE.eval2FALSE);
					disassociate(remOp);
					logger.debug("Associated bubble set to eval false; bubble: " + invalidBubble);
					break;
					
				// notification that a role of an operator was modified
				case Notification.SET:
					// strategy for modified operators:
					// 1) remove assoc. bubble
					// 2) add new bubble with new roles settings
					
					Operator modOp = checkNotifier(notification, Operator.class);
					
					logger.debug("Modified EMF Operator found => set old associated bubble to eval false and create new" +
							"associated bubble; modified op:" + modOp);
					
					Bubble<?> modBubble = getAssociatedBubble(modOp);
					// bubble3.setState(Bubble.STATE.eval2FALSE);
					bvMediator.changeState(modBubble, Bubble.STATE.eval2FALSE);
					disassociate(modOp);
					
					logger.debug("Old assoc. bubble set to eval false; bubble: " + modBubble);
					
					Bubble<? extends com.big.tuwien.SmartMatcher.operators.Operator> repBubble = 
												bb.build(modOp);
					
					//bubbleView.addCurrentLevelBubble(bubble4);
					repBubble.setState(Bubble.STATE.eval2TRUE);
					bvMediator.addBubble(repBubble);
					associate(repBubble, modOp);
					logger.debug("Created new associated bubble: " + repBubble);
					break;
					
				default:
					logger.warn("EMFModel notification type not implemented: " + notification);
					break;
			}		
		}
		

		private Operator getAffectedOperator(Notification n, EReference operators, sm_mm.MappingModel mm) {
			// position of the affected operator in the 'operators' reference list 
			int pos = n.getPosition();
			@SuppressWarnings("unchecked")
			List<Operator> ops = ((EList<Operator>) mm.eGet(operators));
			Operator op = ops.get(pos);
			return op;
		}
		
		
		/*
		 * Checks that the feature contained in the notification has the given type and name.
		 */
		private <T extends EStructuralFeature> T checkFeature(Notification n, Class<T> c, String featureName) {
			Object feature = n.getFeature();
			if(!c.isInstance(feature))
				throw new IllegalArgumentException("Expected feature type " + c + 
						" but found feature: " + feature);
			@SuppressWarnings("unchecked")  T nf = (T) feature;
			if (! nf.getName().equals(featureName))
				throw new IllegalArgumentException("Expected feature with name " + featureName + 
						" but found feature: " + feature);
			return nf;
		}
		
		
		/*
		 * Checks that the notifier contained in the notification has the given type.
		 */
		private <T> T checkNotifier(Notification n, Class<T> c) {
			Object notifier = n.getNotifier();
			if(!c.isInstance(notifier))
				throw new IllegalArgumentException("Expected notifier type " + c + 
						" but found notifier: " + notifier);
			@SuppressWarnings("unchecked")  T nf = (T) notifier;
			return nf;	
		}
	}
	
		
	/**
	 * Adapter which is notified about events at the BubbleView. The adapter forwards
	 * these events to the EMF model.
	 * @author alex
	 *
	 */
	protected class BubbleViewAdapter implements BubbleViewListener {

		@Override
		public void bubbleViewEvent(int event, Bubble<?> bubble) {
			logger.debug("bubbleViewEvent() called, event: " + event + " , bubble: " + bubble);
			
			switch(event) {
			
				case MappingEvent.APPLIED_EVENT:
					// new bubble -> create according operator for EMF model
					Operator emfOp2 = ob.build(bubble);
					
					emfModel.addOperator(emfOp2);
					
					associate(bubble, emfOp2);
					logger.debug("Created associated operator: " + emfOp2);
					break;
				
				case MappingEvent.BUBBLE_STATE_CHANGED:
					// if bubble was evaluated to false -> remove according operator from EMF model
					if(bubble.getState() == Bubble.STATE.eval2FALSE) {
						sm_mm.Operator emfOp = getAssociatedOperator(bubble);
						emfModel.removeOperator(emfOp);
						disassociate(bubble);
					} else if(bubble.getState() == Bubble.STATE.eval2TRUE) {
						// nothing to do on the EMF model
					} else {
						logger.warn("Bubble state change event not implemented");
					}	
					break;
			
				default:
					logger.warn("BubbleView event type not implemented: " + event);
					break;
			}
			
		}
	}
}
