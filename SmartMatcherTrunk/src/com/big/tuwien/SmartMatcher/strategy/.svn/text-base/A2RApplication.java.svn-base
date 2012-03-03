package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;

public class A2RApplication {
	private static Logger logger = Logger.getLogger(A2RApplication.class);
	private BubbleView bubbleView;
	private A2RProperties a2rProps;
	private A2RBubble a2rBubble;
	private Iterator<A2RConfiguration> a2rConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private boolean init = false;
	private C2CBubble c2cContext1;
	private C2CBubble c2cContext2;
	
	
	public A2RApplication() {};
	
	
	public A2RApplication(Pair<C2C> c2cPair,
			BubbleView bubbleView, A2RProperties a2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = (C2CBubble) c2cPair.getFirst();
		this.c2cContext2 = (C2CBubble) c2cPair.getSecond();
		this.bubbleView = bubbleView;
		this.a2rProps = a2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}
	
	
	public A2RApplication(C2CBubble c2cContext1, C2CBubble c2cContext2,
			BubbleView bubbleView, A2RProperties a2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = c2cContext1;
		this.c2cContext2 = c2cContext2;
		this.bubbleView = bubbleView;
		this.a2rProps = a2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}


	public boolean isApplyable() throws A2RApplicationException {
		try {
			return applyable();
		} catch (Exception e) {
			throw new A2RApplicationException(e);
		}
	}
	
	
	public void init() throws A2RApplicationException {
		try {
			if(!init) {
				try2Apply_A2R();
				init = true;
			}
		} catch (Exception e) {
			throw new A2RApplicationException(e);
		}
	}
	
	
	public void reset() throws A2RApplicationException {
		try {
			try2Apply_A2R();
		} catch (Exception e) {
			throw new A2RApplicationException(e);
		}
	}
	
	
	public Application apply() throws A2RApplicationException {
		if(!init) throw new A2RApplicationException("A2RApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(a2rConfigFound()) {
				applyA2R();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new A2RApplicationException(e);
		}
	}

	
	
	/**
	 * Returns true if the given c2c-pair allows applications of a2r.
	 */
	private boolean applyable() throws Exception {
		A2RBubble initBubble = new A2RBubble();
		A2RConfiguration initConfig = new A2RConfiguration();
		initBubble.setConfiguration(initConfig);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);

		A2R a2r = a2rProps.getOperatorInstance();
		a2r.setDOMView(this.bubbleView.getDOMView());
		a2r.init(initBubble);
		a2r.buildTransitionTree();
		Iterator<A2RConfiguration> _a2rConfigIt = a2r.getConfigurationIterator();
		
		Set<A2RBubble> a2rBlacklist = this.bubbleView.getBubbles(A2RBubble.class, Bubble.STATE.eval2FALSE);
		List<A2RConfiguration> _a2rBlacklist = new Vector<A2RConfiguration>();
		for(A2RBubble b : a2rBlacklist) {
			_a2rBlacklist.add((A2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2R-configurations
		Iterator<A2RConfiguration> a2rConfigIt = new FilteredIterator<A2RConfiguration>(_a2rConfigIt, _a2rBlacklist);
		
		if(a2rConfigIt.hasNext()) {
			logger.debug("Context C2C-Bubble-Pair allows application of A2R");
			return true;
		} else {
			logger.debug("Context C2C-Bubble-Pair allows no application of A2R");
			return false;
		}	
	}
	
	
	public boolean a2rConfigFound() {
		return this.a2rConfigIt != null && this.a2rConfigIt.hasNext();
	}
	
	
	public void try2Apply_A2R() throws Exception {
		logger.info("Find A2R for context1 C2C-Bubble: " + c2cContext1 + " and context2 C2C-Bubble: " + c2cContext2);
		
		A2RBubble initBubble = new A2RBubble();
		A2RConfiguration config = new A2RConfiguration();
		initBubble.setConfiguration(config);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);
		
		A2R a2r = a2rProps.getOperatorInstance();
		a2r.setDOMView(this.bubbleView.getDOMView());
		a2r.init(initBubble);
		a2r.buildTransitionTree();
		Iterator<A2RConfiguration> _a2rConfigIt = a2r.getConfigurationIterator();
		
		Set<A2RBubble> a2rBlacklist = this.bubbleView.getBubbles(A2RBubble.class, Bubble.STATE.eval2FALSE);
		List<A2RConfiguration> _a2rBlacklist = new Vector<A2RConfiguration>();
		for(A2RBubble b : a2rBlacklist) {
			_a2rBlacklist.add((A2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2R-configurations
		Iterator<A2RConfiguration> a2rConfigIt = new FilteredIterator<A2RConfiguration>(_a2rConfigIt, _a2rBlacklist);
		
		this.a2rConfigIt = a2rConfigIt;
	}
	
	
	public void applyA2R() {
		this.a2rBubble = (A2RBubble) this.bubbleView.getBubbleFactory().createBubbleInstance(A2R.class);
		this.a2rBubble.setConfiguration(this.a2rConfigIt.next());
		this.a2rBubble.setContext1(c2cContext1);
		this.a2rBubble.setContext2(c2cContext2);
		this.bubbleView.addCurrentLevelBubble(this.a2rBubble);
		this.a2rBubble.setState(Bubble.STATE.applied);
		logger.info("Apply A2R-Bubble: " + this.a2rBubble);
	}
	
	
	/**
	 * Evaluates the current bubble.
	 * @param context
	 */
	public boolean evaluate_A2R() {
		if( fitnessFunc.evaluate(this.a2rBubble)) {	// bubble evaluated to true
	    	this.a2rBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.a2rBubble);
	    	return true;
	    } else {									// focusBubble evaluated to false
	    	this.a2rBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.a2rBubble);
	    	return false;
	    }
	    
	}
	
	
	public void transform() {
		List<Bubble<? extends Operator>> transformees = new Vector<Bubble<? extends Operator>>();
		transformees.add(c2cContext1);
		transformees.add(c2cContext2);
		transformees.addAll(this.bubbleView.getBubbles(new StdBubbleOrder(),
				Bubble.STATE.applied, 
				Bubble.STATE.eval2TRUE));
		strategy.transform(transformees);
	}
	
	
	public class Application {
		private boolean configured;
		
		public Application() {}
		
		
		public Application(boolean configured) {
			this.configured = configured;
		}
		

		public boolean isConfigured() {
			return configured;
		}
		
		
		public void setConfigured(boolean configured) {
			this.configured = configured;
		}
		
		
		public A2RBubble getBubble() {
			return a2rBubble;
		}
	
		
		public boolean evaluate() throws A2RApplicationException {
			transform();
			return evaluate_A2R();
		}
		
		
		public String toString() {
			return "A2RApplication :: configured : " + configured + " , bubble : " + a2rBubble; 
		}
	}
	
	
	public class A2RApplicationException extends Exception {
		private static final long serialVersionUID = 8993057994L;

		
		public A2RApplicationException() {
			super();
		}

		
		public A2RApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public A2RApplicationException(String message) {
			super(message);
		}

		
		public A2RApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
