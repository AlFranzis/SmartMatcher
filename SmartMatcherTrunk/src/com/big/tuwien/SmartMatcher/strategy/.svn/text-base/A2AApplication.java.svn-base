package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;
import com.big.tuwien.transformation.TransformationResult;

public class A2AApplication {
	private static Logger logger = Logger.getLogger(A2AApplication.class);
	private BubbleView bubbleView;
	private A2AProperties a2aProps;
	private C2CBubble c2cBubble;
	private A2ABubble a2aBubble;
	private Iterator<A2AConfiguration> a2aConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private List<TransformationResult> tresults;
	private boolean init = false;
	
	
	public A2AApplication() {};
	
	
	public A2AApplication(C2CBubble c2cBubble,
			BubbleView bubbleView, A2AProperties a2aProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cBubble = c2cBubble;
		this.bubbleView = bubbleView;
		this.a2aProps = a2aProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}


	public boolean isApplyable() throws A2AApplicationException {
		try {
			return previouslyMappingHasAttributes();
		} catch (Exception e) {
			throw new A2AApplicationException(e);
		}
	}
	
	
	public void init() throws A2AApplicationException {
		try {
			if(!init) {
				try2Apply_A2A();
				init = true;
			}
		} catch (Exception e) {
			throw new A2AApplicationException(e);
		}
	}
	
	
	public void reset() throws A2AApplicationException {
		try {
			try2Apply_A2A();
		} catch (Exception e) {
			throw new A2AApplicationException(e);
		}
	}
	
	
	public Application apply() throws A2AApplicationException {
		if(!init) throw new A2AApplicationException("A2AApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(a2aConfigFound()) {
				applyA2A();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new A2AApplicationException(e);
		}
	}

	
	public void applyA2A() {
		this.a2aBubble = (A2ABubble) this.bubbleView.getBubbleFactory().createBubbleInstance(A2A.class);
		this.a2aBubble.setConfiguration(this.a2aConfigIt.next());
		this.a2aBubble.setContext(this.c2cBubble);
		this.bubbleView.addCurrentLevelBubble(this.a2aBubble);
		this.a2aBubble.setState(Bubble.STATE.applied);
		logger.info("Apply A2A-Bubble: " + this.a2aBubble);
	}
	
	
	/**
	 * Returns true if the found mapping (C2C) has more unmapped attributes.
	 */
	public boolean previouslyMappingHasAttributes() throws Exception {
		A2ABubble initBubble = new A2ABubble();
		A2AConfiguration initConfig = new A2AConfiguration();
		initBubble.setConfiguration(initConfig);
		initBubble.setContext(this.c2cBubble);

		A2A a2a = a2aProps.getOperatorInstance();
		a2a.setDOMView(this.bubbleView.getDOMView());
		a2a.init(initBubble);
		a2a.buildTransitionTree();
		Iterator<A2AConfiguration> _a2aConfigIt = a2a.getConfigurationIterator();
		
		Set<A2ABubble> a2aBlacklist = this.bubbleView.getBubbles(A2ABubble.class, Bubble.STATE.eval2FALSE);
		List<A2AConfiguration> _a2aBlacklist = new Vector<A2AConfiguration>();
		for(A2ABubble b : a2aBlacklist) {
			_a2aBlacklist.add((A2AConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2A-configurations
		Iterator<A2AConfiguration> a2aConfigIt = new FilteredIterator<A2AConfiguration>(_a2aConfigIt, _a2aBlacklist);
		if(a2aConfigIt.hasNext()) {
			logger.debug("Context C2C mapping has attributes");
			return true;
		} else {
			logger.debug("Context C2C mapping has no attributes");
			return false;
		}	
	}
	
	
	public boolean a2aConfigFound() {
		return this.a2aConfigIt != null && this.a2aConfigIt.hasNext();
	}
	
	
	public void try2Apply_A2A() throws Exception {
		C2CBubble contextC2C = this.c2cBubble;
		logger.info("Find A2A for context C2C-Bubble : " + contextC2C);
		
		A2ABubble initBubble = new A2ABubble();
		A2AConfiguration config = new A2AConfiguration();
		initBubble.setConfiguration(config);
		initBubble.setContext(contextC2C);
		
		A2A a2a = a2aProps.getOperatorInstance();
		a2a.setDOMView(this.bubbleView.getDOMView());
		a2a.init(initBubble);
		a2a.buildTransitionTree();
		Iterator<A2AConfiguration> _a2aConfigIt = a2a.getConfigurationIterator();
		
		Set<A2ABubble> a2aBlacklist = this.bubbleView.getBubbles(A2ABubble.class, Bubble.STATE.eval2FALSE);
		List<A2AConfiguration> _a2aBlacklist = new Vector<A2AConfiguration>();
		for(A2ABubble b : a2aBlacklist) {
			_a2aBlacklist.add((A2AConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2A-configurations
		Iterator<A2AConfiguration> a2aConfigIt = new FilteredIterator<A2AConfiguration>(_a2aConfigIt, _a2aBlacklist);
		
		this.a2aConfigIt = a2aConfigIt;
	}
	
	
	
	
	/**
	 * Evaluates the current bubble.
	 * @param context
	 */
	public boolean evaluate_A2A() {
		for(TransformationResult tr : tresults) {
			// for A2As there must be a generated instance element for each input instance element
			// this constraint might fail for attributes with enumeration type
			if(A2A.NAME.equals(tr.getOperator())) {
				if(!(tr.getInputSize() == tr.getGeneratedSize())) {
					this.a2aBubble.setState(Bubble.STATE.eval2FALSE);
			    	logger.debug("Evaluated to false: " + this.a2aBubble);
			    	return false;
				}
			}
		}
		
		if( fitnessFunc.evaluate(this.a2aBubble)) {	// bubble evaluated to true
	    	this.a2aBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.a2aBubble);
	    	return true;
	    } else {									// focusBubble evaluated to false
	    	this.a2aBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.a2aBubble);
	    	return false;
	    }
	    
	}
	
	
	public void transform() {
		List<Bubble<? extends Operator>> transformees = new Vector<Bubble<? extends Operator>>();
		transformees.addAll(this.bubbleView.getBubbles(new StdBubbleOrder(),
				Bubble.STATE.applied, 
				Bubble.STATE.eval2TRUE));
		this.tresults = strategy.transform(transformees);
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
		
		
		public A2ABubble getBubble() {
			return a2aBubble;
		}
	
		
		public boolean evaluate() throws A2AApplicationException {
			transform();
			return evaluate_A2A();
		}
		
		
		public String toString() {
			return "A2AApplication :: configured : " + configured + " , bubble : " + a2aBubble; 
		}
	}
	
	
	public class A2AApplicationException extends Exception {
		private static final long serialVersionUID = 1834985398;

		
		public A2AApplicationException() {
			super();
		}

		
		public A2AApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public A2AApplicationException(String message) {
			super(message);
		}

		
		public A2AApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
