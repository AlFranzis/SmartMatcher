/**
 * 
 */
package com.big.tuwien.SmartMatcher.views.emf;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;



import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewListener;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;

class EventFilteredBVMediator implements BubbleViewListener, BVMediator {
	private static Logger logger = Logger.getLogger(EventFilteredBVMediator.class);
	private final static int MAX_FILTERS = 10;
	
	private BubbleView bubbleView;
	private List<BubbleViewListener> listeners = new Vector<BubbleViewListener>();
	private List<EventFilter> filters = new Vector<EventFilter>();

	
	public EventFilteredBVMediator(BubbleView bubbleView) {
		this.bubbleView = bubbleView;
		bubbleView.addListener(this);
	}

	
	public void addBubble(Bubble<? extends Operator> b) {
		addFilter(new EventFilter(MappingEvent.APPLIED_EVENT, b));
		
		//TODO is this the right way?
		// add BubbleView to bubble listeners
		// normally this already is done in the BubbleFactory
		// provided by the BubbleView itself (see: bubbleView.getBubbleFactory())
		b.addListener(bubbleView);
		
		bubbleView.addCurrentLevelBubble(b);
	}
	
	
	public void changeState(Bubble<?> b, Bubble.STATE state) {
		addFilter(new EventFilter(MappingEvent.BUBBLE_STATE_CHANGED, b));
		b.setState(state);
	}


	@Override
	public void bubbleViewEvent(int event, Bubble<?> b) {
		logger.debug("bubbleViewEvent() entered, eventId: " + event + ", bubble: " + b);
		
		if (filter(event, b)) {
			logger.debug("Filter event: eventId = " + event + ", bubble = " + b);
			return;
		}
		
		informListeners(event, b);
	}
	
	
	private void informListeners(int eventType, Bubble<?> b ) {
		for(BubbleViewListener bvl : listeners) {
			bvl.bubbleViewEvent(eventType, b);
		}
	}
	
	
	private boolean filter(int event, Bubble<?> b) {
		EventFilter ef = null;
		for(EventFilter _ef : filters) {
			if(_ef.matches(event, b)) {
				ef = _ef;
				break;
			}
		}
		
		if(ef != null) {
			// remove filter instance because it is not needed anymore
			filters.remove(ef);
			return true;
		}
		return false;
	}
	
	
	public void addFilter(EventFilter nf) {
		if(!filters.contains(nf)) {
			filters.add(nf);
			// constrain filters size to MAX_FILTERS
			if(filters.size() > MAX_FILTERS) {
				filters.remove(filters.size() -1 );
				logger.warn("List of filters exceeds MAX_FILTERS -> Removing last filter");
			}
		}
	}
	
	
	public void addListener(BubbleViewListener bvl) {
		if(!listeners.contains(bvl)) listeners.add(bvl);
	}
	
	
	public class EventFilter {
		private int eventId;
		private Bubble<?> bubble;
		
		
		public EventFilter(int eventId, Bubble<?> b) {
			this.eventId = eventId;
			this.bubble = b;
		}
		
		
		public boolean matches(int event, Bubble<?> b) {
			return eventId == event && bubble == b;
		}
	}
	
}