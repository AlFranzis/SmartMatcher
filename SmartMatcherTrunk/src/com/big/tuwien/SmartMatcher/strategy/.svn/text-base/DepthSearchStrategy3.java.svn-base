package com.big.tuwien.SmartMatcher.strategy;



import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.Levenshtein;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CProperties;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2c.A2CTransformer;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RProperties;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RTransformer;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RProperties;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RTransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ATransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RTransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.DIRECTION;
import com.big.tuwien.SmartMatcher.strategy.R2RApplication.Application;
import com.big.tuwien.SmartMatcher.strategy.R2RApplication.R2RApplicationException;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationResult;


public class DepthSearchStrategy3 {	
	private static Logger logger = Logger.getLogger(DepthSearchStrategy3.class);
	
	private ModelManager modelManager;
	private TransformationEngine tEngine;
	private NewSimpleFitnessFunction fitnessFunc;
	private PairManager<C2C> pairManager;
	private BubbleView bubbleView;
	private MetaModelFactory mmFactory;
	private A2AProperties a2aProps;
	private C2CProperties c2cProps;
	private R2RProperties r2rProps;
	private A2CProperties a2cProps;
	private A2RProperties a2rProps;
	private C2RProperties c2rProps;
	
	/*
	 * Stores all rhs classes which have already been tried / processed for the C2C-operator
	 */
	private List<Element> processedRHSClasses = new Vector<Element>();
	
	/*
	 * Stores all c2c-bubbles which are not approved by an a2a or an r2r
	 */
	private List<C2CBubble> greylist = new Vector<C2CBubble>(); 
	
	// counts the number of transformations done
	private int transformations = 0;
	
	
	public static void main(String[] args) {
		try {
			DOMConfigurator.configure("sys_log4j.xml");
			DepthSearchStrategy3 strategy = new DepthSearchStrategy3();
			strategy.map();
		} catch (Exception e) {
			logger.error("Error while executing strategy", e);
		}
	}
	
	
	public DepthSearchStrategy3() {
	    try {
			build();
			initial();
		} catch (Exception e) {
			logger.error("Error while building DepthSearchStrategy", e);
		}
	}
	
	
	private void build() throws Exception {
		this.modelManager = new ModelManager();
		
		//this.modelManager.setExample(ExampleStore.ER_2_WEBML);
		//this.modelManager.setExample(ExampleStore.ER_2_WEBML_BOOKS);
		//this.modelManager.setExample(ExampleStore.TEST_CASE_6);
		//this.modelManager.setExample(ExampleStore.PERSON_PERSONFAMILY);
		// this.modelManager.setExample(ExampleStore.UML14_2_UML20_Light);
		this.modelManager.setExample(ExampleStore.TEST_CASE_7);
		//this.modelManager.setExample(ExampleStore.UML1_2_UML2_BIG_V4);
		this.modelManager.init();
		
		ResourceMetaModelFactory mmFactory = new ResourceMetaModelFactory();
		//ResourceMetaModelFactory mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(modelManager.getInputMetaModel());
		mmFactory.setRHSResource(modelManager.getOutputMetaModel());
		mmFactory.build();
		this.mmFactory = mmFactory;
		
		ModelLinker.linkModels(modelManager, mmFactory);
		
		MetaModelFactoryUtil.setMetaModelFactory(mmFactory);
		// create a factory copy that contains only elements
		// that occur in the instance models;
		MetaModelFactory filtered = mmFactory.copy();
		filtered.addFilter(new InstanceFilter());
		
		this.bubbleView = new BubbleView();
		this.bubbleView.setMetaModelFactory(filtered);
		this.bubbleView.buildView();
		
		this.tEngine = new TransformationEngine(modelManager);
		this.tEngine.addTransformer("C2C", new C2CTransformer(this.tEngine));
		this.tEngine.addTransformer("A2A", new A2ATransformer(this.tEngine));
		this.tEngine.addTransformer("R2R", new R2RTransformer(this.tEngine));
		this.tEngine.addTransformer("A2C", new A2CTransformer(this.tEngine));
		this.tEngine.addTransformer("A2R", new A2RTransformer(this.tEngine));
		this.tEngine.addTransformer("C2R", new C2RTransformer(this.tEngine));
		
		this.fitnessFunc = new NewSimpleFitnessFunction();
		fitnessFunc.setModelManager(this.modelManager);
		
		this.pairManager = new PairManager<C2C>();
		
		this.a2aProps = new A2AProperties();
		this.a2aProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.r2rProps = new R2RProperties();
		this.r2rProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.c2cProps = new C2CProperties();
		this.c2cProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.a2cProps = new A2CProperties();
		this.a2cProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.a2rProps = new A2RProperties();
		this.a2rProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.c2rProps = new C2RProperties();
	}
	
	
	/**
	 * This method implements the global strategy
	 * @throws Exception
	 */
	public void map() throws Exception {	
		Element rhsClass;
		// C2C loop (loop 1)
		while((rhsClass = getUnmappedRHSClass()) != null) {
			
			boolean c2cApproved = false;
			
			C2CApplication c2cApp = new C2CApplication(rhsClass, this.bubbleView, this.c2cProps,this, this.fitnessFunc);
			c2cApp.init();
			C2CApplication.Application c2cApplication = c2cApp.apply();
			boolean c2cCorrect = false;

			while(c2cApplication.isConfigured() && !(c2cCorrect = c2cApplication.evaluate())) {
				c2cApplication = c2cApp.apply();
			}

			if(!c2cCorrect) {
				blacklistClass(rhsClass);
				printBubbleView();
				// continue loop1
				continue;
			} else  {
				C2CBubble c2cBubble = c2cApplication.getBubble();
				A2AApplication a2aApp = new A2AApplication(c2cBubble, 
						this.bubbleView, this.a2aProps,this,this.fitnessFunc);
				// A2A loop (Loop2)
				while(a2aApp.isApplyable()) {
					a2aApp.init();
					A2AApplication.Application a = a2aApp.apply();
					boolean correct = false;
					while(a.isConfigured() && !(correct = a.evaluate())) {
						a = a2aApp.apply();
					}
					if(correct) {
						c2cApproved = true;
					}
					a2aApp.reset();
				}

				pairManager.addBubble(c2cBubble);
				// R2R pair loop (loop3)
				while(pairManager.containsPairs()) {
					Pair<C2C> c2cPair = pairManager.nextPair();

					if(!disjoint(c2cPair)) continue;

					boolean r2rsFound = applyR2RForAllDirections(c2cPair);
					if(r2rsFound) {
						c2cApproved = true;
						removeFromGreylist(c2cPair);
					}
					
					// A2R application !
					A2RApplication a2rApp = new A2RApplication(c2cPair, 
							this.bubbleView, this.a2rProps, this, this.fitnessFunc);
					// A2R loop (loop 5)
					while(a2rApp.isApplyable()) {
						a2rApp.init();
						A2RApplication.Application a = a2rApp.apply();
						boolean correct = false;
						while(a.isConfigured() && !(correct = a.evaluate())) {
							a = a2rApp.apply();
						}
						if(correct) {
							c2cApproved = true;
							removeFromGreylist(c2cPair);
						}
						a2rApp.reset();
					}
					
					
					// C2R application !
					C2RApplication c2rApp = new C2RApplication(c2cPair, 
							this.bubbleView, this.c2rProps, this, this.fitnessFunc);
					// C2R loop (loop 6)
					while(c2rApp.isApplyable()) {
						c2rApp.init();
						C2RApplication.Application a = c2rApp.apply();
						boolean correct = false;
						while(a.isConfigured() && !(correct = a.evaluate())) {
							a = c2rApp.apply();
						}
						if(correct) {
							c2cApproved = true;
							removeFromGreylist(c2cPair);
						}
						c2rApp.reset();
					}
					
				}

				A2CApplication a2cApp = new A2CApplication(c2cBubble, 
						this.bubbleView, this.a2cProps,this,this.fitnessFunc);
				// A2C loop (loop 4)
				while(a2cApp.isApplyable()) {
					a2cApp.init();
					A2CApplication.Application a = a2cApp.apply();
					boolean correct = false;
					while(a.isConfigured() && !(correct = a.evaluate())) {
						a = a2cApp.apply();
					}
					if(correct) {
						c2cApproved = true;
					}
					a2cApp.reset();
				}
				
				
				if(!c2cApproved) {
					logger.debug("C2C-Bubble could not be approved. It will be set to eval2False " +
							"and added to the greylist: " + c2cBubble);
					c2cBubble.setState(Bubble.STATE.eval2FALSE);
					addToGreylist(c2cBubble);
				}
				
				cleanGreylist();
			}	

			blacklistClass(rhsClass);
			printBubbleView();

		}	// end loop1

		printBubbleView();
		logger.info(this.bubbleView.getDOMView().getStringRepresentation());
	}
	
	
	
	private boolean applyR2RForAllDirections(Pair<C2C> c2cPair) throws R2RApplicationException {
		boolean r2rFound = false;
		
		R2RApplication r2rApp = new R2RApplication(c2cPair, DIRECTION.directed, 
				this.bubbleView, this.r2rProps, this, this.fitnessFunc);
		while(r2rApp.isApplyable()) {
			r2rApp.init();
			Application a = r2rApp.apply();
			boolean correct = false;
			while(a.isConfigured() && !(correct = a.evaluate())) {
				a = r2rApp.apply();
			}
			if(correct) {
				r2rFound = true;
			}
			r2rApp.reset();
		}
		
		c2cPair.reverse();
		
		r2rApp = new R2RApplication(c2cPair, DIRECTION.directed, 
				this.bubbleView, this.r2rProps, this, this.fitnessFunc);
		while(r2rApp.isApplyable()) {
			r2rApp.init();
			Application a = r2rApp.apply();
			boolean correct = false;
			while(a.isConfigured() && !(correct = a.evaluate())) {
				a = r2rApp.apply();
			}
			if(correct) {
				r2rFound = true;
			}
			r2rApp.reset();
		}
		
		r2rApp = new R2RApplication(c2cPair, DIRECTION.inverse, 
				this.bubbleView, this.r2rProps, this, this.fitnessFunc);
		while(r2rApp.isApplyable()) {
			r2rApp.init();
			Application a = r2rApp.apply();
			boolean correct = false;
			while(a.isConfigured() && !(correct = a.evaluate())) {
				a = r2rApp.apply();
			}
			if(correct) {
				r2rFound = true;
			}
			r2rApp.reset();
		}
		
		c2cPair.reverse();
		
		r2rApp = new R2RApplication(c2cPair, DIRECTION.inverse, 
				this.bubbleView, this.r2rProps, this, this.fitnessFunc);
		while(r2rApp.isApplyable()) {
			r2rApp.init();
			Application a = r2rApp.apply();
			boolean correct = false;
			while(a.isConfigured() && !(correct = a.evaluate())) {
				a = r2rApp.apply();
			}
			if(correct) {
				r2rFound = true;
			}
			r2rApp.reset();
		}
		
		return r2rFound;
	}
	
	/**
	 * Returns if the members of the pair contain overlapping elements.
	 * @param pair
	 * @return
	 */
	private boolean disjoint(Pair<?> pair) {
		Bubble<?> first = pair.getFirst();
		Bubble<?> second = pair.getSecond();
		
		return Collections.disjoint(first.getConfiguration().getLHSElements(),
					second.getConfiguration().getLHSElements()) && 
				Collections.disjoint(first.getConfiguration().getRHSElements(),
					second.getConfiguration().getRHSElements());
	}
	
	
	public void blacklistClass(Element e) {
		this.processedRHSClasses.add(e);
	}
	
	
	public void removeFromGreylist(Pair<C2C> pair) {
		C2CBubble c2c1 = (C2CBubble) pair.getFirst();
    	C2CBubble c2c2 = (C2CBubble) pair.getSecond();
    	// greylisted c2c1 is approved by this R2R
    	if(this.greylist.contains(c2c1)) {
    		logger.debug("Greylisted C2C " + c2c1 + " is approved by current R2R");
    		c2c1.setState(Bubble.STATE.eval2TRUE);
    		this.greylist.remove(c2c1);
    	}
    	// greylisted c2c2 is approved by this R2R
    	if(this.greylist.contains(c2c2)) {
    		logger.debug("Greylisted C2C " + c2c2 + " is approved by current R2R");
    		c2c2.setState(Bubble.STATE.eval2TRUE);
    		this.greylist.remove(c2c2);
    	}
	}

	
	public void addToGreylist(C2CBubble c2cBubble) {
		this.greylist.add(c2cBubble);
	}
	
	
	public void cleanGreylist() {
		List<Bubble<? extends Operator>> corrects = this.bubbleView.getBubbles(new StdBubbleOrder(),Bubble.STATE.eval2TRUE);
		Set<Element> mapped = new HashSet<Element>();
		for(Bubble<? extends Operator> b : corrects) {
			 mapped.addAll(b.getConfiguration().getLHSElements());
			 mapped.addAll(b.getConfiguration().getRHSElements());
		}
		
		List<C2CBubble> cleanedGreylist = new Vector<C2CBubble>();
		for(C2CBubble c2c : greylist) {
			Element lhsFocusClass = c2c.getConfiguration().getRoleElement(C2CConfiguration.Roles.lhsFocusClass);
			Element rhsFocusClass = c2c.getConfiguration().getRoleElement(C2CConfiguration.Roles.rhsFocusClass);
			
			// all greylisted C2C-mappings that contain already mapped elements are removed from the greylist
			if(!mapped.contains(lhsFocusClass) && !mapped.contains(rhsFocusClass))
				cleanedGreylist.add(c2c);
			else {
				pairManager.removeBubble(c2c);
				logger.debug("Removed from greylist because elements are already mapped: " + c2c);
			}
			
		}
		this.greylist = cleanedGreylist;
	}
	
	
	
	public void printBubbleView() {
		logger.info(this.bubbleView.getStateStringRepresentation());
	}
	

	
	private void initial() {
		
	}
	
	
	private boolean isGreylisted(Element clazz) {
		for(C2CBubble glb : this.greylist) {
			if(glb.getConfiguration().getRHSElements().contains(clazz)) {
				return true;
			}		
		}
		return false;
	}
	
	
	private Element getUnmappedRHSClass() {
	    for(Element element : this.bubbleView.getUnmappedRHSElements()) {
			if(element.getRepresentedBy() instanceof ClassElement) {
				if(!isGreylisted(element)) {
					logger.info("RHSElementList contains possible Class that can be mapped, e.g.: " + element.getFullPathName());
					return element;
				}
	    	}
	    }
	    logger.info("RHSElementListContainsClass: false");
		return null;
	}	


	public List<TransformationResult> transform() {
		List<Bubble<? extends Operator>> transformees = this.bubbleView.getBubbles(new StdBubbleOrder(),Bubble.STATE.applied, Bubble.STATE.eval2TRUE);
		return transform(transformees);		
	}
	
	
	public List<TransformationResult> transform(List<Bubble<? extends Operator>> transformees) {
		transformations++;
		
		// remove duplicates in transformees
		List<Integer> checked = new Vector<Integer>();
		List<Bubble<? extends Operator>> _transformees = new Vector<Bubble<? extends Operator>>();
		for(Bubble<? extends Operator> b : transformees) {
			if(!checked.contains(b.getId()) && b.getId() != 63) {
				_transformees.add(b);
				checked.add(b.getId());
			}
 		}
		transformees = _transformees;
		
		List<TransformationResult> tresults = new Vector<TransformationResult>();
		try {
			do {
				this.modelManager.resetOutputModel();
				logger.info("Transform bubbles: " + transformees);	
				for(Bubble<? extends Operator> b : transformees) {
					this.tEngine.addConfiguration(b.getConfiguration());
				}
				tresults.addAll(this.tEngine.transform());
				this.modelManager.saveOutputModel();
				this.tEngine.clearConfigurations();
			} while(this.modelManager.next());
			return tresults;
		} catch (TransformationException e) {
			logger.error("Error during Transformation", e);
		}
		return null;
	}
	
}
