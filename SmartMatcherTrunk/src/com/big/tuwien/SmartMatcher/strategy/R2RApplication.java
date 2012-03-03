package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.DIRECTION;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;

public class R2RApplication {
	private static Logger logger = Logger.getLogger(R2RApplication.class);
	private DIRECTION direction;
	private BubbleView bubbleView;
	private R2RProperties r2rProps;
	private R2RBubble r2rBubble;
	private Iterator<R2RConfiguration> r2rConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private boolean init = false;
	private C2CBubble c2cContext1;
	private C2CBubble c2cContext2;
	
	
	public R2RApplication() {};
	
	
	public R2RApplication(Pair<C2C> c2cPair, DIRECTION direction,
			BubbleView bubbleView, R2RProperties r2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = (C2CBubble) c2cPair.getFirst();
		this.c2cContext2 = (C2CBubble) c2cPair.getSecond();
		this.direction = direction;
		this.bubbleView = bubbleView;
		this.r2rProps = r2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}
	
	
	public R2RApplication(C2CBubble c2cContext1, C2CBubble c2cContext2, DIRECTION direction,
			BubbleView bubbleView, R2RProperties r2rProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cContext1 = c2cContext1;
		this.c2cContext2 = c2cContext2;
		this.direction = direction;
		this.bubbleView = bubbleView;
		this.r2rProps = r2rProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}


	public boolean isApplyable() throws R2RApplicationException {
		try {
			return c2cPairHasReferences();
		} catch (Exception e) {
			throw new R2RApplicationException(e);
		}
	}
	
	
	public void init() throws R2RApplicationException {
		try {
			if(!init) {
				try2Apply_R2R();
				init = true;
			}
		} catch (Exception e) {
			throw new R2RApplicationException(e);
		}
	}
	
	
	public void reset() throws R2RApplicationException {
		try {
			try2Apply_R2R();
		} catch (Exception e) {
			throw new R2RApplicationException(e);
		}
	}
	
	
	public Application apply() throws R2RApplicationException {
		if(!init) throw new R2RApplicationException("R2RApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(r2rConfigFound()) {
				applyR2R();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new R2RApplicationException(e);
		}
	}

	
	
	/**
	 * Returns true if the given c2c-pair has more unmapped references.
	 */
	private boolean c2cPairHasReferences() throws Exception {
		R2RBubble initBubble = new R2RBubble();
		R2RConfiguration initConfig = new R2RConfiguration();
		initConfig.setDirectionConstraint(direction);
		initBubble.setConfiguration(initConfig);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);

		R2R r2r = r2rProps.getOperatorInstance();
		r2r.setDOMView(this.bubbleView.getDOMView());
		r2r.init(initBubble);
		r2r.buildTransitionTree();
		Iterator<R2RConfiguration> _r2rConfigIt = r2r.getConfigurationIterator();
		
		Set<R2RBubble> r2rBlacklist = this.bubbleView.getBubbles(R2RBubble.class, Bubble.STATE.eval2FALSE);
		List<R2RConfiguration> _r2rBlacklist = new Vector<R2RConfiguration>();
		for(R2RBubble b : r2rBlacklist) {
			_r2rBlacklist.add((R2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted R2R-configurations
		Iterator<R2RConfiguration> r2rConfigIt = new FilteredIterator<R2RConfiguration>(_r2rConfigIt, _r2rBlacklist);
		
		if(r2rConfigIt.hasNext()) {
			logger.debug("Context C2C-Bubble-Pair has references");
			return true;
		} else {
			logger.debug("Context C2C-Bubble-Pair has no references");
			return false;
		}	
	}
	
	
	public boolean r2rConfigFound() {
		return this.r2rConfigIt != null && this.r2rConfigIt.hasNext();
	}
	
	
	public void try2Apply_R2R() throws Exception {
		logger.info("Find R2R for context1 C2C-Bubble: " + c2cContext1 + " and context2 C2C-Bubble: " + c2cContext2);
		
		R2RBubble initBubble = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		config.setDirectionConstraint(direction);
		initBubble.setConfiguration(config);
		initBubble.setContext1(c2cContext1);
		initBubble.setContext2(c2cContext2);
		
		R2R r2r = r2rProps.getOperatorInstance();
		r2r.setDOMView(this.bubbleView.getDOMView());
		r2r.init(initBubble);
		r2r.buildTransitionTree();
		Iterator<R2RConfiguration> _r2rConfigIt = r2r.getConfigurationIterator();
		
		Set<R2RBubble> r2rBlacklist = this.bubbleView.getBubbles(R2RBubble.class, Bubble.STATE.eval2FALSE);
		List<R2RConfiguration> _r2rBlacklist = new Vector<R2RConfiguration>();
		for(R2RBubble b : r2rBlacklist) {
			_r2rBlacklist.add((R2RConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted R2R-configurations
		Iterator<R2RConfiguration> r2rConfigIt = new FilteredIterator<R2RConfiguration>(_r2rConfigIt, _r2rBlacklist);
		
		this.r2rConfigIt = r2rConfigIt;
	}
	
	
	public void applyR2R() {
		this.r2rBubble = (R2RBubble) this.bubbleView.getBubbleFactory().createBubbleInstance(R2R.class);
		this.r2rBubble.setConfiguration(this.r2rConfigIt.next());
		this.r2rBubble.setContext1(c2cContext1);
		this.r2rBubble.setContext2(c2cContext2);
		this.bubbleView.addCurrentLevelBubble(this.r2rBubble);
		this.r2rBubble.setState(Bubble.STATE.applied);
		logger.info("Apply R2R-Bubble: " + this.r2rBubble);
	}
	
	
	/**
	 * Evaluates the current bubble.
	 * @param context
	 */
	public boolean evaluate_R2R() {
		if( fitnessFunc.evaluate(this.r2rBubble)) {	// bubble evaluated to true
	    	this.r2rBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.r2rBubble);
	    	return true;
	    } else {									// focusBubble evaluated to false
	    	this.r2rBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.r2rBubble);
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
		
		
		public R2RBubble getBubble() {
			return r2rBubble;
		}
	
		
		public boolean evaluate() throws R2RApplicationException {
			transform();
			return evaluate_R2R();
		}
		
		
		public String toString() {
			return "R2RApplication :: configured : " + configured + " , bubble : " + r2rBubble; 
		}
	}
	
	
	public class R2RApplicationException extends Exception {
		private static final long serialVersionUID = 8993057994L;

		
		public R2RApplicationException() {
			super();
		}

		
		public R2RApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public R2RApplicationException(String message) {
			super(message);
		}

		
		public R2RApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
