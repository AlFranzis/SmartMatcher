package com.big.tuwien.SmartMatcher.views.bubble;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.dom.DOMView;
import com.google.common.collect.Ordering;

/**
 * The bubble view is a storage to manage the mapping bubbles.
 * The view consists of levels which represent the status of the bubble view
 * at a certain discrete points in time. With this concept the whole history
 * of applied and tried mappings can be stored and queried. 
 * @author alex
 *
 */
public class BubbleView implements BubbleListener {
	protected Map<Integer,Level> 	// maps level-id's to the corresponding level
				id2levels = new HashMap<Integer,Level>();
	protected Map<Integer, Level> bubbleId2level = new HashMap<Integer,Level>(); 
	
	protected int level = 0;		// current level
	
	protected MetaModel lhsMetaModel;
	protected MetaModel rhsMetaModel;
	protected MetaModelFactory mmFactory;
	protected DOMView domView;
	protected BubbleFactory bFactory = new BubbleFactoryImpl(this);
	
	protected List<BubbleViewListener> listeners = new Vector<BubbleViewListener>();
	
	
	public BubbleView() {}
	
	
	public void setMetaModelFactory(MetaModelFactory mmFactory) {
		this.mmFactory = mmFactory;
		this.lhsMetaModel = mmFactory.getLHSMetaModel();
		this.rhsMetaModel = mmFactory.getRHSMetaModel();
		
		this.domView = new DOMView();
		this.domView.setMetaModelFactory(mmFactory);
	}
	
	
	public void buildView() throws BubbleViewException {
		id2levels.put(level, new Level(level));
		try {
			this.domView.buildView();
		} catch (ParserConfigurationException e) {
			throw new BubbleViewException("Exception while building DOMView", e);
		}
	}
	
	
	/**
	 * Returns a {@link BubbleFactory}-Instance which should be used to create
	 * instances of bubbles that are associated with this BubbleView.
	 * @return Instance of {@link BubbleFactory}.
	 */
	public BubbleFactory getBubbleFactory() {
		return this.bFactory;
	}
	
	
	public DOMView getDOMView() {
		return this.domView;
	}
	
	
	public Set<Bubble<? extends Operator>> getBubbles(int levelId) {
		Level level = id2levels.get(levelId);
		if(level == null) return Collections.<Bubble<? extends Operator>>emptySet(); 
		
		return level.getBubbles();
	}
	
	
	public Set<Bubble<? extends Operator>> getBubbles() {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		return level.getBubbles();
	}
	
	
	public Set<Bubble<? extends Operator>> getBubbles(String op, Bubble.STATE... states) {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		return level.getBubblesForOpInState(op, states);
	}
	
	
	public <T extends Bubble<? extends Operator>> Set<T> getBubbles(Class<T> type, Bubble.STATE... states) {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		return level.getBubblesForOpInState(type, states);
	}
	
	
	/**
	 * Returns all bubbles which are in one of the given states.
	 * @param states
	 * @return
	 */
	public Set<Bubble<? extends Operator>> getBubbles(Bubble.STATE... states) {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		return level.getBubblesInState(states);
	}
	
	
	/**
	 * Returns all bubbles which are in one of the given states. The returned list is
	 * in order specified by the given comparator.
	 * @param comp
	 * @param states
	 * @return
	 */
	public List<Bubble<? extends Operator>> getBubbles(Comparator<Bubble<? extends Operator>> comp, Bubble.STATE... states) {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		Set<Bubble<? extends Operator>> bubbles = level.getBubblesInState(states);
		Ordering<Bubble<? extends Operator>> o = Ordering.forComparator(comp);
		return o.sortedCopy(bubbles);
	}
	
	
	
	/**
	 * Returns all associated bubbles for an given element in a given level. 
	 * @param e
	 * @param levelId
	 * @return
	 */
	public Set<Bubble<? extends Operator>> getBubbles(Element e, int levelId) {
		Level level = id2levels.get(levelId);
		if(level == null) return Collections.<Bubble<? extends Operator>>emptySet();
		
		return level.getBubblesForElement(e);
	}
	
	
	/**
	 * Returns all bubbles of a given level that are associated with an given element
	 * and are in one of the given states.
	 * @param e
	 * @param levelId
	 * @param states
	 * @return
	 */
	public Set<Bubble<? extends Operator>> getBubbles(Element e, int levelId, Bubble.STATE... states) {
		List<Bubble.STATE> allowedStates = Arrays.asList(states);
		Set<Bubble<? extends Operator>> bubbles = new HashSet<Bubble<? extends Operator>>();
		
		for(Bubble<? extends Operator> b : getBubbles(e, levelId)) {
			if(allowedStates.contains(b.getState()))
				bubbles.add(b);
		}
		
		return bubbles;
	}
	
	
	/**
	 * Returns all bubbles of the current level that are associated with an given element
	 * and are in one of the given states.
	 * @param e
	 * @param levelId
	 * @param states
	 * @return
	 */
	public Set<Bubble<? extends Operator>> getCurrentLevelBubbles(Element e, Bubble.STATE... states) {
		List<Bubble.STATE> allowedStates = Arrays.asList(states);
		Set<Bubble<? extends Operator>> bubbles = new HashSet<Bubble<? extends Operator>>();
		
		for(Bubble<? extends Operator> b : getCurrentLevelBubbles(e)) {
			if(allowedStates.contains(b.getState()))
				bubbles.add(b);
		}
		
		return bubbles;
	}
	
	
	public Level getLevel(int levelId) {
		if(this.id2levels.containsKey(levelId)) {
			return this.id2levels.get(levelId);
		} else {
			throw new IllegalArgumentException("Level with levelId " + levelId + " does not exist");
		}
	}
	
	
	public Level getCurrentLevel() {
		return getLevel(this.level);
	}
	
	
	public int getCurrentLevelDepth() {
		return this.level;
	}
	
	
	public Set<Bubble<? extends Operator>> getCurrentLevelBubbles(Element e) {
		return getBubbles(e, level);
	}
	
	
	public void addCurrentLevelBubble(Bubble<? extends Operator> b) {
		addLevelBubble(b, level);
	}
	
	
	protected void addLevelBubble(Bubble<? extends Operator> b, int levelId) {
		Level level = id2levels.get(levelId);
		if(level == null) throw new IllegalArgumentException("Unknown level id: " + levelId);
		
		level.addBubble(b);
		this.bubbleId2level.put(b.getId(), level);
		
		// inform DOMView
		this.domView.mappingEvent2(MappingEvent.APPLIED_EVENT, b);
		
		// inform listeners
		informListeners(MappingEvent.APPLIED_EVENT, b);
	}
	
	
	public String getStringRepresentation() {
		StringBuffer buf = new StringBuffer();
		buf.append("BubbleView :: ");
		for(Entry<Integer,Level> en : this.id2levels.entrySet()) {
			buf.append(en.getValue());
		}
		return buf.toString();
	}
	
	public String getStateStringRepresentation() {
		
		Comparator<Bubble<? extends Operator>> c = 
			new Comparator<Bubble<? extends Operator>>() {
				public int compare(Bubble<? extends Operator> b1,
					Bubble<? extends Operator> b2) {
					return b1.getId() - b2.getId();
				
				}
			
		};
		
		StringBuffer buf = new StringBuffer();
		buf.append("BubbleView :: \n");
		buf.append("State APPLIED: \n");
		
		List<Bubble<? extends Operator>> bubbles = getBubbles(c, Bubble.STATE.applied);
		for(Bubble<? extends Operator> b : bubbles) {
			buf.append(b + "\n");
		}
		
		buf.append("State EVAL2TRUE: \n");
		bubbles = getBubbles(c, Bubble.STATE.eval2TRUE);
		for(Bubble<? extends Operator> b : bubbles) {
			buf.append(b + "\n");
		}
		
		buf.append("State EVAL2FALSE: \n");
		bubbles = getBubbles(c, Bubble.STATE.eval2FALSE);
		for(Bubble<? extends Operator> b : bubbles) {
			buf.append(b + "\n");
		}
		
		buf.append("State EMPTY: \n");
		bubbles = getBubbles(c, Bubble.STATE.empty);
		for(Bubble<? extends Operator> b : bubbles) {
			buf.append(b + "\n");
		}
		return buf.toString();
	}
	
	
	public void removeCurrentLevelBubble(Bubble<? extends Operator> b) {
		Level level = id2levels.get(this.level);
		if(level == null) throw new IllegalArgumentException("Current level is null");
		
		level.removeBubble(b);
	}
	
	
	public Collection<Bubble<? extends Operator>> getMarkedBubbles() {
		return null;
	}
	
	
	public Collection<Element> getUnmappedLHSElements() {
		Collection<Element> elements = lhsMetaModel.getElements(); 
		for(Bubble<? extends Operator> b : getLevel(this.level).getBubblesInState(
						Bubble.STATE.applied, Bubble.STATE.eval2TRUE)) {
			if(b.getConfiguration() != null && b.getConfiguration().getLHSElements() != null) {
				elements.removeAll(b.getConfiguration().getLHSElements());
			}
		}
		
		return elements;
	}
	
	
	public Collection<Element> getUnmappedRHSElements_dominik() {
		Collection<Element> elements = rhsMetaModel.getElements(); 
		for(Bubble<? extends Operator> b : getLevel(this.level).getBubblesInState(
						Bubble.STATE.applied, Bubble.STATE.eval2TRUE)) {
			if(b.getConfiguration() != null && b.getConfiguration().getRHSElements() != null && !b.isInputBubble()) {
				elements.removeAll(b.getConfiguration().getRHSElements());
			}
		}
		
		return elements;
	}
	
	
	public Collection<Element> getUnmappedRHSElements() {
		Collection<Element> elements = rhsMetaModel.getElements(); 
		for(Bubble<? extends Operator> b : getLevel(this.level).getBubblesInState(
						Bubble.STATE.applied, Bubble.STATE.eval2TRUE)) {
			if(b.getConfiguration() != null && b.getConfiguration().getRHSElements() != null) {
				elements.removeAll(b.getConfiguration().getRHSElements());
			}
		}
		
		return elements;
	}
	
	
	public Collection<Element> getUnmappedLHSAttributes() {
		return null;
	}
	
	
	public Collection<Element> getUnmappedRHSAttributes() {
		return null;
	}
	
	
	public Collection<Element> getUnmappedLHSReferences() {
		return null;
	}
	
	
	public Collection<Element> getUnmappedRHSReferences() {
		return null;
	}


	/**
	 * Called by a bubble if the state of the bubble has changed, etc.
	 */
	public void bubbleEvent(int eventType, Bubble<? extends Operator> b) {
		switch(eventType) {
		// bubble state changed event
		case MappingEvent.BUBBLE_STATE_CHANGED:
			// inform DOMView
			Level level = this.bubbleId2level.get(b.getId());
			
			if(level == null) throw new IllegalArgumentException("Unknown Bubble: " + b);
			
			level.bubbleEvent(eventType, b);
			this.domView.mappingEvent2(MappingEvent.BUBBLE_STATE_CHANGED, b);
			
			// inform Listeners
			informListeners(MappingEvent.BUBBLE_STATE_CHANGED, b);
			break;
		default:
			throw new IllegalArgumentException("Unknown event type: " + eventType);
		}
		
	}
	
	
	private void informListeners(int eventType, Bubble<? extends Operator> b ) {
		for(BubbleViewListener bvl : listeners) {
			bvl.bubbleViewEvent(eventType, b);
		}
	}
	
	
	public void addListener(BubbleViewListener l) {
		if(!listeners.contains(l)) listeners.add(l);	
	}
	
	
	public void removeListener(BubbleViewListener l) {
		listeners.remove(l);	
	}
	
}
