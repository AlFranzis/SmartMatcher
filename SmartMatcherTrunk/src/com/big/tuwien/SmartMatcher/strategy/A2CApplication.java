package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2C;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CBubble;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;
import com.big.tuwien.transformation.TransformationResult;

public class A2CApplication {
	private static Logger logger = Logger.getLogger(A2CApplication.class);
	private BubbleView bubbleView;
	private A2CProperties a2cProps;
	private C2CBubble c2cBubble;
	private A2CBubble a2cBubble;
	private Iterator<A2CConfiguration> a2cConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private List<TransformationResult> tresults;
	private boolean init = false;
	
	
	public A2CApplication() {};
	
	
	public A2CApplication(C2CBubble c2cBubble,
			BubbleView bubbleView, A2CProperties a2cProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.c2cBubble = c2cBubble;
		this.bubbleView = bubbleView;
		this.a2cProps = a2cProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
	}


	public boolean isApplyable() throws A2CApplicationException {
		try {
			return applyable();
		} catch (Exception e) {
			throw new A2CApplicationException(e);
		}
	}
	
	
	public void init() throws A2CApplicationException {
		try {
			if(!init) {
				try2Apply_A2C();
				init = true;
			}
		} catch (Exception e) {
			throw new A2CApplicationException(e);
		}
	}
	
	
	public void reset() throws A2CApplicationException {
		try {
			try2Apply_A2C();
		} catch (Exception e) {
			throw new A2CApplicationException(e);
		}
	}
	
	
	public Application apply() throws A2CApplicationException {
		if(!init) throw new A2CApplicationException("A2CApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(a2cConfigFound()) {
				applyA2C();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new A2CApplicationException(e);
		}
	}

	
	public void applyA2C() {
		this.a2cBubble = (A2CBubble) this.bubbleView.getBubbleFactory().createBubbleInstance(A2C.class);
		this.a2cBubble.setConfiguration(this.a2cConfigIt.next());
		this.a2cBubble.setContext(this.c2cBubble);
		this.bubbleView.addCurrentLevelBubble(this.a2cBubble);
		this.a2cBubble.setState(Bubble.STATE.applied);
		logger.info("Apply A2C-Bubble: " + this.a2cBubble);
		logger.debug(this.bubbleView.getStateStringRepresentation());
	}
	
	
	/**
	 * Returns true if the given context mapping (C2C) allows further A2C mappings.
	 */
	public boolean applyable() throws Exception {
		A2CBubble initBubble = new A2CBubble();
		A2CConfiguration initConfig = new A2CConfiguration();
		initBubble.setConfiguration(initConfig);
		initBubble.setContext(this.c2cBubble);

		A2C a2c = a2cProps.getOperatorInstance();
		a2c.setDOMView(this.bubbleView.getDOMView());
		a2c.init(initBubble);
		a2c.buildTransitionTree();
		Iterator<A2CConfiguration> _a2cConfigIt = a2c.getConfigurationIterator();
		
		Set<A2CBubble> a2cBlacklist = this.bubbleView.getBubbles(A2CBubble.class, Bubble.STATE.eval2FALSE);
		List<A2CConfiguration> _a2cBlacklist = new Vector<A2CConfiguration>();
		for(A2CBubble b : a2cBlacklist) {
			_a2cBlacklist.add((A2CConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2A-configurations
		Iterator<A2CConfiguration> a2cConfigIt = new FilteredIterator<A2CConfiguration>(_a2cConfigIt, _a2cBlacklist);
		if(a2cConfigIt.hasNext()) {
			logger.debug("Context C2C mapping allows A2C mapping");
			return true;
		} else {
			logger.debug("Context C2C mapping allows no A2C mapping");
			return false;
		}	
	}
	
	
	public boolean a2cConfigFound() {
		return this.a2cConfigIt != null && this.a2cConfigIt.hasNext();
	}
	
	
	public void try2Apply_A2C() throws Exception {
		C2CBubble contextC2C = this.c2cBubble;
		logger.info("Find A2C for context C2C-Bubble : " + contextC2C);
		
		A2CBubble initBubble = new A2CBubble();
		A2CConfiguration config = new A2CConfiguration();
		initBubble.setConfiguration(config);
		initBubble.setContext(contextC2C);
		
		A2C a2c = a2cProps.getOperatorInstance();
		a2c.setDOMView(this.bubbleView.getDOMView());
		a2c.init(initBubble);
		a2c.buildTransitionTree();
		Iterator<A2CConfiguration> _a2cConfigIt = a2c.getConfigurationIterator();
		
		Set<A2CBubble> a2cBlacklist = this.bubbleView.getBubbles(A2CBubble.class, Bubble.STATE.eval2FALSE);
		List<A2CConfiguration> _a2cBlacklist = new Vector<A2CConfiguration>();
		for(A2CBubble b : a2cBlacklist) {
			_a2cBlacklist.add((A2CConfiguration) b.getConfiguration());
		}
		
		// filter all blacklisted A2A-configurations
		Iterator<A2CConfiguration> a2cConfigIt = new FilteredIterator<A2CConfiguration>(_a2cConfigIt, _a2cBlacklist);
		
		this.a2cConfigIt = a2cConfigIt;
	}
	
	
	
	
	/**
	 * Evaluates the current bubble.
	 * @param context
	 */
	public boolean evaluate_A2C() {
		for(TransformationResult tr : tresults) {
			// for A2Cs there must be a generated instance element for each input instance element
			// this constraint might fail for attributes with enumeration type
			if(A2C.NAME.equals(tr.getOperator())) {
				if(!(tr.getInputSize() == tr.getGeneratedSize())) {
					this.a2cBubble.setState(Bubble.STATE.eval2FALSE);
			    	logger.debug("Evaluated to false: " + this.a2cBubble);
			    	return false;
				}
			}
		}
		
		if( fitnessFunc.evaluate(this.a2cBubble)) {	// bubble evaluated to true
	    	this.a2cBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.a2cBubble);
	    	return true;
	    } else {									// focusBubble evaluated to false
	    	this.a2cBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.a2cBubble);
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
		
		
		public A2CBubble getBubble() {
			return a2cBubble;
		}
	
		
		public boolean evaluate() throws A2CApplicationException {
			transform();
			return evaluate_A2C();
		}
		
		
		public String toString() {
			return "A2CApplication :: configured : " + configured + " , bubble : " + a2cBubble; 
		}
	}
	
	
	public class A2CApplicationException extends Exception {
		private static final long serialVersionUID = 1834985398;

		
		public A2CApplicationException() {
			super();
		}

		
		public A2CApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public A2CApplicationException(String message) {
			super(message);
		}

		
		public A2CApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
