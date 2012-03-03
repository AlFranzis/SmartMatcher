package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2R;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;

public class C2RApplication {
	private static Logger logger = Logger.getLogger(C2RApplication.class);
	private BubbleView bubbleView;
	private C2RProperties c2rProps;
	private C2RBubble c2rBubble;
	private Iterator<C2RConfiguration> c2rConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private boolean init = false;
	private C2CBubble c2cContext1;
	private C2CBubble c2cContext2;
	
	
	public C2RApplication() {};
	
	
	public C2RApplication(Pair<C2C> c2cPair,
			BubbleView bubbleView, C2RProperties c2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = (C2CBubble) c2cPair.getFirst();
		this.c2cContext2 = (C2CBubble) c2cPair.getSecond();
		this.bubbleView = bubbleView;
		this.c2rProps = c2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}
	
	
	public C2RApplication(C2CBubble c2cContext1, C2CBubble c2cContext2,
			BubbleView bubbleView, C2RProperties c2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = c2cContext1;
		this.c2cContext2 = c2cContext2;
		this.bubbleView = bubbleView;
		this.c2rProps = c2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}


	public boolean isApplyable() throws C2RApplicationException {
		try {
			return applyable();
		} catch (Exception e) {
			throw new C2RApplicationException(e);
		}
	}
	
	
	public void init() throws C2RApplicationException {
		try {
			if(!init) {
				try2Apply_C2R();
				init = true;
			}
		} catch (Exception e) {
			throw new C2RApplicationException(e);
		}
	}
	
	
	public void reset() throws C2RApplicationException {
		try {
			try2Apply_C2R();
		} catch (Exception e) {
			throw new C2RApplicationException(e);
		}
	}
	
	
	public Application apply() throws C2RApplicationException {
		if(!init) throw new C2RApplicationException("C2RApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(c2rConfigFound()) {
				applyC2R();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new C2RApplicationException(e);
		}
	}

	
	
	/**
	 * Returns true if the given c2c-pair allows applications of c2r.
	 */
	private boolean applyable() throws Exception {
		C2RBubble initBubble = new C2RBubble();
		C2RConfiguration initConfig = new C2RConfiguration();
		initBubble.setConfiguration(initConfig);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);

		C2R c2r = c2rProps.getOperatorInstance();
		c2r.setDOMView(this.bubbleView.getDOMView());
		c2r.init(initBubble);
		c2r.buildTransitionTree();
		Iterator<C2RConfiguration> _c2rConfigIt = c2r.getConfigurationIterator();
		
		Set<C2RBubble> c2rBlacklist = this.bubbleView.getBubbles(C2RBubble.class, Bubble.STATE.eval2FALSE);
		List<C2RConfiguration> _c2rBlacklist = new Vector<C2RConfiguration>();
		for(C2RBubble b : c2rBlacklist) {
			_c2rBlacklist.add((C2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted C2R-configurations
		Iterator<C2RConfiguration> c2rConfigIt = new FilteredIterator<C2RConfiguration>(_c2rConfigIt, _c2rBlacklist);
		
		if(c2rConfigIt.hasNext()) {
			logger.debug("Context C2C-Bubble-Pair allows application of C2R");
			return true;
		} else {
			logger.debug("Context C2C-Bubble-Pair allows no application of C2R");
			return false;
		}	
	}
	
	
	public boolean c2rConfigFound() {
		return this.c2rConfigIt != null && this.c2rConfigIt.hasNext();
	}
	
	
	public void try2Apply_C2R() throws Exception {
		logger.info("Find C2R for context1 C2C-Bubble: " + c2cContext1 + " and context2 C2C-Bubble: " + c2cContext2);
		
		C2RBubble initBubble = new C2RBubble();
		C2RConfiguration config = new C2RConfiguration();
		initBubble.setConfiguration(config);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);
		
		C2R c2r = c2rProps.getOperatorInstance();
		c2r.setDOMView(this.bubbleView.getDOMView());
		c2r.init(initBubble);
		c2r.buildTransitionTree();
		Iterator<C2RConfiguration> _c2rConfigIt = c2r.getConfigurationIterator();
		
		Set<C2RBubble> c2rBlacklist = this.bubbleView.getBubbles(C2RBubble.class, Bubble.STATE.eval2FALSE);
		List<C2RConfiguration> _c2rBlacklist = new Vector<C2RConfiguration>();
		for(C2RBubble b : c2rBlacklist) {
			_c2rBlacklist.add((C2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted C2R-configurations
		Iterator<C2RConfiguration> c2rConfigIt = new FilteredIterator<C2RConfiguration>(_c2rConfigIt, _c2rBlacklist);
		
		this.c2rConfigIt = c2rConfigIt;
	}
	
	
	public void applyC2R() {
		this.c2rBubble = (C2RBubble) this.bubbleView.getBubbleFactory().createBubbleInstance(C2R.class);
		this.c2rBubble.setConfiguration(this.c2rConfigIt.next());
		this.c2rBubble.setContext1(c2cContext1);
		this.c2rBubble.setContext2(c2cContext2);
		this.bubbleView.addCurrentLevelBubble(this.c2rBubble);
		this.c2rBubble.setState(Bubble.STATE.applied);
		logger.info("Apply C2R-Bubble: " + this.c2rBubble);
	}
	
	
	/**
	 * Evaluates the current bubble.
	 * @param context
	 */
	public boolean evaluate_C2R() {
		if( fitnessFunc.evaluate(this.c2rBubble)) {	// bubble evaluated to true
	    	this.c2rBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.c2rBubble);
	    	return true;
	    } else {									// focusBubble evaluated to false
	    	this.c2rBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.c2rBubble);
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
		
		
		public C2RBubble getBubble() {
			return c2rBubble;
		}
	
		
		public boolean evaluate() throws C2RApplicationException {
			transform();
			return evaluate_C2R();
		}
		
		
		public String toString() {
			return "C2RApplication :: configured : " + configured + " , bubble : " + c2rBubble; 
		}
	}
	
	
	public class C2RApplicationException extends Exception {
		private static final long serialVersionUID = 8993057994L;

		
		public C2RApplicationException() {
			super();
		}

		
		public C2RApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public C2RApplicationException(String message) {
			super(message);
		}

		
		public C2RApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
