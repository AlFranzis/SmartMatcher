package com.big.tuwien.SmartMatcher.strategy;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;
import com.big.tuwien.transformation.TransformationResult;

public class C2CApplication {
	private static Logger logger = Logger.getLogger(C2CApplication.class);
	private BubbleView bubbleView;
	private C2CProperties c2cProps;
	private C2CBubble c2cBubble;
	private Iterator<C2CConfiguration> c2cConfigIt;
	private DepthSearchStrategy3 strategy;
	private NewSimpleFitnessFunction fitnessFunc;
	private List<TransformationResult> tresults;
	private boolean init = false;
	private Element rhsClass;
	
	
	public C2CApplication() {};
	
	
	public C2CApplication(Element rhsClass, BubbleView bubbleView, C2CProperties c2cProps, 
			DepthSearchStrategy3 strategy, 
			NewSimpleFitnessFunction fitnessFunc) {
		this.bubbleView = bubbleView;
		this.c2cProps = c2cProps;
		this.strategy = strategy;
		this.fitnessFunc = fitnessFunc;
		this.rhsClass = rhsClass;
	}


	public boolean isApplyable() throws C2CApplicationException {
		try {
			return rhsClass != null;
		} catch (Exception e) {
			throw new C2CApplicationException(e);
		}
	}
	
	
	public void init() throws C2CApplicationException {
		try {
			if(!init) {
				try2Apply_C2C();
				init = true;
			}
		} catch (Exception e) {
			throw new C2CApplicationException(e);
		}
	}
	
	
	public void reset() throws C2CApplicationException {
		try {
			try2Apply_C2C();
		} catch (Exception e) {
			throw new C2CApplicationException(e);
		}
	}
	
	
	public Application apply() throws C2CApplicationException {
		if(!init) throw new C2CApplicationException("C2CApplication is not initalized. Call init() first");
		
		Application app = new Application();
		try {
			if(c2cConfigFound()) {
				applyC2C();
				app.setConfigured(true);
			} else {
				app.setConfigured(false);
			}
			
			return app;
		} catch (Exception e) {
			throw new C2CApplicationException(e);
		}
	}

	
	public void applyC2C() {
		this.c2cBubble = (C2CBubble) this.bubbleView.getBubbleFactory().createBubbleInstance(C2C.class);			
		this.c2cBubble.setConfiguration(this.c2cConfigIt.next());
		this.bubbleView.addCurrentLevelBubble(this.c2cBubble);
		this.c2cBubble.setState(Bubble.STATE.applied);
		logger.info("Apply C2C-Bubble: " + this.c2cBubble);
	}
	
	
	public boolean c2cConfigFound() {
		return this.c2cConfigIt != null && this.c2cConfigIt.hasNext();
	}
	
	
	
	/**
	 * Evaluates the current evaluation bubble.
	 * @param context
	 */
	public boolean evaluate_C2C() {
		if( fitnessFunc.evaluate(this.c2cBubble)) {	//  evaluated to true
	    	this.c2cBubble.setState(Bubble.STATE.eval2TRUE);
	    	logger.debug("Evaluated to true: " + this.c2cBubble);
	    	return true;
	    } else {									//  evaluated to false
	    	this.c2cBubble.setState(Bubble.STATE.eval2FALSE);
	    	logger.debug("Evaluated to false: " + this.c2cBubble);
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
	
	
	public void try2Apply_C2C() throws Exception {
		C2CBubble initBubble = new C2CBubble();
		C2CConfiguration initConfig = new C2CConfiguration();
		initConfig.setRole(Roles.rhsFocusClass, this.rhsClass);
		initBubble.setConfiguration(initConfig);

		C2C c2c = c2cProps.getOperatorInstance();
		c2c.setDOMView(this.bubbleView.getDOMView());
		c2c.init(initBubble);
		c2c.buildTransitionTree();
		Iterator<C2CConfiguration> c2cConfigIt = c2c.getConfigurationIterator();
		this.c2cConfigIt = c2cConfigIt;
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
		
		
		public C2CBubble getBubble() {
			return c2cBubble;
		}
	
		
		public boolean evaluate() throws C2CApplicationException {
			transform();
			return evaluate_C2C();
		}
		
		
		public String toString() {
			return "C2CApplication :: configured : " + configured + " , bubble : " + c2cBubble; 
		}
	}
	
	
	
	public class C2CApplicationException extends Exception {
		private static final long serialVersionUID = 666524;

		
		public C2CApplicationException() {
			super();
		}

		
		public C2CApplicationException(String message, Throwable cause) {
			super(message, cause);
		}

		
		public C2CApplicationException(String message) {
			super(message);
		}

		
		public C2CApplicationException(Throwable cause) {
			super(cause);
		}
	}
}
