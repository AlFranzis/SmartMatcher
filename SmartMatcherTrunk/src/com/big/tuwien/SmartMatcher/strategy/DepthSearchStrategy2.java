package com.big.tuwien.SmartMatcher.strategy;



import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelLinker;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.InstanceFilter;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.model.NewSimpleFitnessFunction;
import com.big.tuwien.SmartMatcher.operators.FilteredIterator;
import com.big.tuwien.SmartMatcher.operators.Levenshtein;
import com.big.tuwien.SmartMatcher.operators.Measure;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2A;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ABubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2ATransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CTransformer;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2R;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RProperties;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RTransformer;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleFactory;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.StdBubbleOrder;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationResult;


public class DepthSearchStrategy2 {	
	private static Logger logger = Logger.getLogger(DepthSearchStrategy2.class);
	
	/**
	 * RHS element which is mapped in the current matching step
	 */
	private Element eiFocus;
	
	private ModelManager modelManager;
	private TransformationEngine tEngine;
	private NewSimpleFitnessFunction fitnessFunc;
	private PairManager<C2C> pairManager;

	private Iterator<C2CConfiguration> c2cConfigIt;
	private Iterator<A2AConfiguration> a2aConfigIt;
	private Iterator<R2RConfiguration> r2rConfigIt;
	private boolean mapOpEval2True = false;
	private boolean c2cApproved = false;
	private Pair<C2C> c2cPair;
	private C2CBubble c2cBubble;
	private A2ABubble a2aBubble;
	private R2RBubble r2rBubble;
	private List<TransformationResult> tresults;
	private Bubble<? extends Operator> evalBubble;
	private BubbleView bubbleView;
	private BubbleFactory bFactory;
	private List<Bubble<? extends Operator>> appliedBubbles = new Vector<Bubble<? extends Operator>>();
	private MetaModelFactory mmFactory;
	
	private A2AProperties a2aProps;
	private C2CProperties c2cProps;
	private R2RProperties r2rProps;
	
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
			DepthSearchStrategy2 strategy = new DepthSearchStrategy2();
			strategy.map();
		} catch (Exception e) {
			logger.error("Error while executing strategy", e);
		}
	}
	
	
	public DepthSearchStrategy2() {
	    try {
			build();
			initial();
			
			//logger.info(this.bubbleView.getDOMView().getStringRepresentation());
			
			try {
				this.generateBubbleViewFromXML();
			} catch (Exception e) {
				logger.info("Input Mapping not loaded!\n", e);
			}
		} catch (Exception e) {
			logger.error("Error while building DepthSearchStrategy", e);
		}
	}
	
	
	private void build() throws Exception {
		this.modelManager = new ModelManager();
		
		this.modelManager.setExample(ExampleStore.ER_2_WEBML);
		//this.modelManager.setExample(ExampleStore.UML14_2_UML20_Light);
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
		this.bFactory = this.bubbleView.getBubbleFactory();
		
		this.tEngine = new TransformationEngine(modelManager);
		this.tEngine.addTransformer("C2C", new C2CTransformer(this.tEngine));
		this.tEngine.addTransformer("A2A", new A2ATransformer(this.tEngine));
		this.tEngine.addTransformer("R2R", new R2RTransformer(this.tEngine));
		
		this.fitnessFunc = new NewSimpleFitnessFunction();
		fitnessFunc.setModelManager(this.modelManager);
		
		this.pairManager = new PairManager<C2C>();
		
		this.a2aProps = new A2AProperties();
		this.a2aProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.r2rProps = new R2RProperties();
		this.r2rProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
		
		this.c2cProps = new C2CProperties();
		this.c2cProps.setPriorityMeasures(Arrays.<Measure>asList(new Levenshtein()));
	}
	
	
	/**
	 * generate bubble view from XML input
	 * @throws IOException 
	 * @throws Exception 
	 */
	public void generateBubbleViewFromXML () throws Exception {
		File inputMapping = null;
		
		try {
			inputMapping = new File(this.modelManager.getInputMapping());
		} catch (Exception e) {
			return;
		}
		
		if (!inputMapping.exists())
			return;
		
		// read mappings
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();

		logger.debug("Read inputMapping file: " + this.modelManager.getInputMapping());
		
		Document doc = docBuilder.parse(inputMapping);
		NodeList mappings = doc.getElementsByTagName("mapping");
		
		C2CBubble c2cBubbleR1 = null;
		C2CBubble c2cBubbleR2 = null;
		
		// get rhselement/lhselement id iterative
		for (int i = 0; i < mappings.getLength(); ++i) {
			String op = mappings.item(i).getAttributes().getNamedItem("op").getNodeValue();
			NodeList roles = mappings.item(i).getChildNodes();
			String lhsId = "";
			String rhsId = "";
			
			// iterate over roles to get lhs and rhs element
			for (int j = 0; j < roles.getLength(); ++j) {
				if (roles.item(j).getNodeName().equals("role")) {
					String focusEl = roles.item(j).getAttributes().getNamedItem("name").getNodeValue();
					if (focusEl.subSequence(0, 8).equals("rhsFocus")) {
						rhsId = roles.item(j).getAttributes().getNamedItem("element").getNodeValue();
					} else {
						lhsId = roles.item(j).getAttributes().getNamedItem("element").getNodeValue();
					}
				}
			}
			
			Element lhsEntity = this.mmFactory.getLHSMetaModel().getElementByName(lhsId);
			Element rhsEntity = this.mmFactory.getRHSMetaModel().getElementByName(rhsId);
			
			if (op.equals("C2C")) {
				logger.debug("C2C Mapping found");
				logger.debug(" LHS: " + lhsEntity.getFullPathName());
				logger.debug(" RHS: " + rhsEntity.getFullPathName());
				
				// create c2c bubble
				C2CConfiguration c2cConfig = new C2CConfiguration();
				c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
				c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
				
				this.c2cBubble = (C2CBubble) this.bFactory.createBubbleInstance(C2C.class);
				this.c2cBubble.setConfiguration(c2cConfig);
				this.bubbleView.addCurrentLevelBubble(this.c2cBubble);
				this.c2cBubble.setInputBubble(true);
				this.c2cBubble.setState(Bubble.STATE.eval2TRUE);
				
				logger.info("Eval2TRUE C2C-Bubble: " + this.c2cBubble);
				
				this.evalBubble = this.c2cBubble;
				this.appliedBubbles.add(this.c2cBubble);
				
				if (c2cBubbleR1 == null) {
					c2cBubbleR1 = this.c2cBubble;
				} else if (c2cBubbleR2 == null){
					c2cBubbleR2 = this.c2cBubble;
				} else {
					c2cBubbleR1 = this.c2cBubble;
				}
			} else if (op.equals("A2A")) {
				logger.debug("A2A Mapping found");
				logger.debug(" LHS: " + lhsEntity.getFullPathName());
				logger.debug(" RHS: " + rhsEntity.getFullPathName());
									
				// create a2a bubble
				A2AConfiguration a2aConfig = new A2AConfiguration();
				a2aConfig.setRole(A2AConfiguration.Roles.lhsFocusAttribute, lhsEntity);
				a2aConfig.setRole(A2AConfiguration.Roles.rhsFocusAttribute, rhsEntity);
				
				this.a2aBubble = (A2ABubble) this.bFactory.createBubbleInstance(A2A.class);
				this.a2aBubble.setConfiguration(a2aConfig);
				this.a2aBubble.setContext(this.c2cBubble);
				this.bubbleView.addCurrentLevelBubble(this.a2aBubble);
				this.a2aBubble.setInputBubble(true);
				this.a2aBubble.setState(Bubble.STATE.eval2TRUE);
				logger.info("Eval2TRUE A2A-Bubble: " + this.a2aBubble);
				
				this.evalBubble = this.a2aBubble;
				this.appliedBubbles.add(this.a2aBubble);
			} else if (op.equals("R2R")) {
				logger.debug("R2R Mapping found");
				logger.debug(" LHS: " + lhsEntity.getFullPathName());
				logger.debug(" RHS: " + rhsEntity.getFullPathName());
									
				// create c2c bubble
				R2RConfiguration r2rConfig = new R2RConfiguration();
				r2rConfig.setRole(R2RConfiguration.Roles.lhsFocusReference, lhsEntity);
				r2rConfig.setRole(R2RConfiguration.Roles.rhsFocusReference, rhsEntity);
				
				this.r2rBubble = (R2RBubble) this.bFactory.createBubbleInstance(R2R.class);
				this.r2rBubble.setConfiguration(r2rConfig);
				this.r2rBubble.setContext1(c2cBubbleR1);
				this.r2rBubble.setContext2(c2cBubbleR2);
				this.bubbleView.addCurrentLevelBubble(this.r2rBubble);
				this.r2rBubble.setInputBubble(true);
				this.r2rBubble.setState(Bubble.STATE.eval2TRUE);
				logger.info("Eval2TRUE R2R-Bubble: " + this.r2rBubble);
				
				this.evalBubble = this.r2rBubble;
				this.appliedBubbles.add(this.r2rBubble);
			} else {
				logger.debug("Unknown mapping operation in input mapping: " + op);
			}
		}
	}
	
	
	/**
	 * This method implements the global strategy
	 * @throws Exception
	 */
	public void map() throws Exception {		
		Element rhsClass;
		// C2C loop (loop 1)
		while((rhsClass = RHSElementListContainsClass2()) != null) {
			boolean classEvaluated = false;
			setContextBack();
			setElementInFocus(rhsClass);
			logger.info("RHS Class selected: " + rhsClass.getFullPathName() );
			
			for(Bubble<? extends Operator> b : this.bubbleView.getCurrentLevel().getBubblesInState(
							Bubble.STATE.eval2TRUE)) {
				if(b.getConfiguration() != null && b.getConfiguration().getRHSElements().contains(rhsClass)) {
					b.setInputBubble(false);
					classEvaluated = true;
					this.c2cBubble = (C2CBubble) b;
					this.c2cApproved = true;
				}
			}
			
			if (!classEvaluated) {
				try2Apply_C2C();
			}
			
			if(c2cConfigFound() || classEvaluated) {
				if (!classEvaluated) {
					applyC2C();
	
					transform();
					evaluate_C2C();
	
					// try all possible c2c-Configurations until eval2True
					while(!mapOpEval2True() && moreC2CConfigurations()) {
						applyC2C();
	
						transform();
						evaluate_C2C();
					}
				} else {
					this.mapOpEval2True = true;
				}


				if(!mapOpEval2True()) {
					// continue loop1
					continue;
				} else  {

					boolean attributes = previouslyMappingHasAttributes();

					// A2A loop (loop2)
					while(attributes) {

						try2Apply_A2A();
						if(a2aConfigFound()) {
							applyA2A();

							transform();
							evaluate_A2A();

							// try all possible A2A-Configurations until eval2True
							while(!mapOpEval2True() && moreA2AConfigurations()) {
								applyA2A();

								transform();
								evaluate_A2A();
							}

						} 
						attributes = previouslyMappingHasAttributes();
					}

					pairManager.addBubble(this.c2cBubble);
					// R2R pair loop (loop3)
					while(pairManager.containsPairs()) {
						this.c2cPair = pairManager.nextPair();

						if(!disjoint(this.c2cPair)) continue;

						boolean references = c2cPairHasReferences();
						// R2R loop (loop4)
						while(references) {
							try2Apply_R2R();
							if(r2rConfigFound()) {
								applyR2R();

								List<Bubble<? extends Operator>> transformees = new Vector<Bubble<? extends Operator>>(this.c2cPair.toList());
								transformees.addAll(this.bubbleView.getBubbles(new StdBubbleOrder(),
										Bubble.STATE.applied, 
										Bubble.STATE.eval2TRUE));

								transform(transformees);
								evaluate_R2R();

								// try all possible R2R-Configurations until eval2True
								while(!mapOpEval2True() && moreR2RConfigurations()) {
									applyR2R();

									transformees = new Vector<Bubble<? extends Operator>>(this.c2cPair.toList());
									transformees.addAll(this.bubbleView.getBubbles(new StdBubbleOrder(),
											Bubble.STATE.applied, 
											Bubble.STATE.eval2TRUE));
									
									transform(transformees);
									evaluate_R2R();
								}

							} 
							references = c2cPairHasReferences();
						}
					}

					checkGreylist();
					cleanGreylist();
				}	

			}

			blacklistElementInFocus();

		}	// end loop1

		printBubbleView();

		logger.info(this.bubbleView.getDOMView().getStringRepresentation());
	}
	
	
	/**
	 * Returns if the members of the pair contain overlapping elements.
	 * @param pair
	 * @return
	 */
	public boolean disjoint(Pair<?> pair) {
		Bubble<?> first = pair.getFirst();
		Bubble<?> second = pair.getSecond();
		
		return Collections.disjoint(first.getConfiguration().getLHSElements(),
					second.getConfiguration().getLHSElements()) && 
				Collections.disjoint(first.getConfiguration().getRHSElements(),
					second.getConfiguration().getRHSElements());
	}
	
	
	public void blacklistElementInFocus() {
		this.processedRHSClasses.add(this.eiFocus);
	}
	
	
	public void applyC2C() {
		this.c2cBubble = (C2CBubble) this.bFactory.createBubbleInstance(C2C.class);
		while (this.c2cConfigIt.hasNext()) {
			try {
				this.c2cBubble.setConfiguration(this.c2cConfigIt.next());
				this.bubbleView.addCurrentLevelBubble(this.c2cBubble);
				this.c2cBubble.setState(Bubble.STATE.applied);
				logger.info("Apply C2C-Bubble: " + this.c2cBubble);
				break;
			} catch (Exception e) {
				// bubble already added
				if (!this.c2cConfigIt.hasNext()) {
					logger.error("NO MORE CONFIGURATION: " + this.c2cBubble);
					System.exit(1);
				}
			}
		}
		
		this.evalBubble = this.c2cBubble;
		this.appliedBubbles.add(this.c2cBubble);
		printBubbleView();
	}
	
	
	public void applyA2A() {
		this.a2aBubble = (A2ABubble) this.bFactory.createBubbleInstance(A2A.class);
		this.a2aBubble.setConfiguration(this.a2aConfigIt.next());
		this.a2aBubble.setContext(this.c2cBubble);
		this.bubbleView.addCurrentLevelBubble(this.a2aBubble);
		this.a2aBubble.setState(Bubble.STATE.applied);
		logger.info("Apply A2A-Bubble: " + this.a2aBubble);
		
		this.evalBubble = this.a2aBubble;
		this.appliedBubbles.add(this.a2aBubble);
		printBubbleView();
	}
	
	
	public void applyR2R() {
		this.r2rBubble = (R2RBubble) this.bFactory.createBubbleInstance(R2R.class);
		this.r2rBubble.setConfiguration(this.r2rConfigIt.next());
		this.r2rBubble.setContext1((C2CBubble) this.c2cPair.getFirst());
		this.r2rBubble.setContext2((C2CBubble) this.c2cPair.getSecond());
		this.bubbleView.addCurrentLevelBubble(this.r2rBubble);
		this.r2rBubble.setState(Bubble.STATE.applied);
		logger.info("Apply R2R-Bubble: " + this.r2rBubble);
		
		this.evalBubble = this.r2rBubble;
		this.appliedBubbles.add(this.r2rBubble);
		printBubbleView();
	}


	/**
	 * Evaluates the current evaluation bubble.
	 * @param context
	 */
	public void evaluate_C2C() {
		if( fitnessFunc.evaluate(this.evalBubble)) {	// focusBubble evaluated to true
	    	this.evalBubble.setState(Bubble.STATE.eval2TRUE);
	    	
	    	// context C2C-Bubble is approved by A2A- or R2R-Bubble
	    	if(this.evalBubble instanceof A2ABubble || this.evalBubble instanceof R2RBubble) {
	    		this.c2cApproved = true;
	    	}
	    	
	    	this.mapOpEval2True = true;
	    	logger.debug("Evaluated to true: " + this.evalBubble);
	    } else {									// focusBubble evaluated to false
	    	this.evalBubble.setState(Bubble.STATE.eval2FALSE);
	    	this.mapOpEval2True = false;
	    	logger.debug("Evaluated to false: " + this.evalBubble);
	    }
	    
	    this.resetAppliedBubbles();
	}
	
	
	/**
	 * Evaluates the current evaluation bubble.
	 * @param context
	 */
	public void evaluate_A2A() {
		for(TransformationResult tr : tresults) {
			// for A2As there must be a generated instance element for each input instance element
			// this constraint might fail for attributes with enumeration type
			if(A2A.NAME.equals(tr.getOperator())) {
				if(!(tr.getInputSize() == tr.getGeneratedSize())) {
					this.evalBubble.setState(Bubble.STATE.eval2FALSE);
			    	this.mapOpEval2True = false;
			    	logger.debug("Evaluated to false: " + this.evalBubble);
			    	return;
				}
			}
		}
		
		if( fitnessFunc.evaluate(this.evalBubble)) {	// focusBubble evaluated to true
	    	this.evalBubble.setState(Bubble.STATE.eval2TRUE);
	    	
	    	// context C2C-Bubble is approved by A2A- or R2R-Bubble
	    	if(this.evalBubble instanceof A2ABubble || this.evalBubble instanceof R2RBubble) {
	    		this.c2cApproved = true;
	    	}
	    	
	    	this.mapOpEval2True = true;
	    	logger.debug("Evaluated to true: " + this.evalBubble);
	    } else {									// focusBubble evaluated to false
	    	this.evalBubble.setState(Bubble.STATE.eval2FALSE);
	    	this.mapOpEval2True = false;
	    	logger.debug("Evaluated to false: " + this.evalBubble);
	    }
	    
	    this.resetAppliedBubbles();
	}
	
	
	
	/**
	 * Evaluates the current evaluation bubble.
	 * @param context
	 */
	public void evaluate_R2R() {
		if( fitnessFunc.evaluate(this.evalBubble)) {	// focusBubble evaluated to true
	    	this.evalBubble.setState(Bubble.STATE.eval2TRUE);
	    	
	    	this.c2cApproved = true;
	    	this.mapOpEval2True = true;
	    	
	    	logger.debug("Evaluated to true: " + this.evalBubble);
	    	
	    	C2CBubble c2c1 = (C2CBubble) this.c2cPair.getFirst();
	    	C2CBubble c2c2 = (C2CBubble) this.c2cPair.getSecond();
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
	    	
	    } else {									// focusBubble evaluated to false
	    	this.evalBubble.setState(Bubble.STATE.eval2FALSE);
	    	this.mapOpEval2True = false;
	    	logger.debug("Evaluated to false: " + this.evalBubble);
	    }
	    
	    this.resetAppliedBubbles();
	}
	

	
	public void checkGreylist() {
		if(!this.c2cApproved) {
			logger.debug("C2C-Bubble could not be approved and will be added to greylist: " + this.c2cBubble);
			this.c2cBubble.setState(Bubble.STATE.eval2FALSE);
			this.greylist.add(this.c2cBubble);
		}
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
	
	
	
	private void resetAppliedBubbles() {
		this.appliedBubbles.clear();
		
	}

	
	public void initial() {
		
	}

	
	public boolean moreC2CConfigurations() {
		return this.c2cConfigIt != null && this.c2cConfigIt.hasNext();
	}
	
	
	public boolean moreA2AConfigurations() {
		return this.a2aConfigIt != null && this.a2aConfigIt.hasNext();
	}
	
	
	public boolean moreR2RConfigurations() {
		return this.r2rConfigIt != null && this.r2rConfigIt.hasNext();
	}

	
	
	
	public void setElementInFocus(Element e) {
		this.eiFocus = e;
	}
	
	
	public void try2Apply_C2C() throws Exception {
		logger.info("Find LHS Class for RHS : " + this.eiFocus.getFullPathName());
	
		if(this.eiFocus.getRepresentedBy() instanceof ClassElement) {
			// if( !this.processedRHSClasses.contains(this.eiFocus) ) {	
				C2CBubble initBubble = new C2CBubble();
				C2CConfiguration initConfig = new C2CConfiguration();
				initConfig.setRole(Roles.rhsFocusClass, this.eiFocus);
				initBubble.setConfiguration(initConfig);

				C2C c2c = c2cProps.getOperatorInstance();
				c2c.setDOMView(this.bubbleView.getDOMView());
				c2c.init(initBubble);
				c2c.buildTransitionTree();
				Iterator<C2CConfiguration> c2cConfigIt = c2c.getConfigurationIterator();
				this.c2cConfigIt = c2cConfigIt;
			//} else {
				//logger.debug("Focus class is blacklisted");
			//}
		} else {
			logger.error("The passed RHS Element must be an Class to apply an C2C opperator for it!!!");
		}
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
	
	
	public void try2Apply_R2R() throws Exception {
		C2CBubble contextC2C1 = (C2CBubble) this.c2cPair.getFirst();
		C2CBubble contextC2C2 = (C2CBubble) this.c2cPair.getSecond();
		
		logger.info("Find R2R for context1 C2C-Bubble: " + contextC2C1 + " and context2 C2C-Bubble: " + contextC2C2);
		
		R2RBubble initBubble = new R2RBubble();
		R2RConfiguration config = new R2RConfiguration();
		initBubble.setConfiguration(config);
		initBubble.setContext1(contextC2C1);
		initBubble.setContext2(contextC2C2);
		
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
	
	
	public Element RHSElementListContainsClass() {
	    for(Element element : this.bubbleView.getUnmappedRHSElements_dominik()) {
			this.eiFocus = element;
			if(element.getRepresentedBy() instanceof ClassElement && !this.processedRHSClasses.contains(this.eiFocus)) {
				logger.info("RHSElementList contains possible Class that can be mapped, e.g.: " + element.getFullPathName());
				return element;
	    	}
	    }
	    logger.info("RHSElementListContainsClass: false");
		return null;
	}	
	
	
	private boolean greylisted(Element clazz) {
		for(C2CBubble glb : this.greylist) {
			if(glb.getConfiguration().getRHSElements().contains(clazz)) {
				return true;
			}		
		}
		return false;
	}
	
	public Element RHSElementListContainsClass2() {
	    for(Element element : this.bubbleView.getUnmappedRHSElements_dominik()) {
			if(element.getRepresentedBy() instanceof ClassElement) {
				if(!greylisted(element)) {
					logger.info("RHSElementList contains possible Class that can be mapped, e.g.: " + element.getFullPathName());
					this.eiFocus = element;
					return element;
				}
	    	}
	    }
	    logger.info("RHSElementListContainsClass: false");
		return null;
	}	
	
	
	/**
	 * After each iteration set back the context for the parameters affected in one iteration.
	 * 
	 * */
	public void setContextBack(){
		this.c2cApproved = false;
	}


	public void transform() {
		List<Bubble<? extends Operator>> transformees = this.bubbleView.getBubbles(new StdBubbleOrder(),Bubble.STATE.applied, Bubble.STATE.eval2TRUE);
		transform(transformees);		
	}
	
	
	public void transform(List<Bubble<? extends Operator>> transformees) {
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
		
		
		try {
			do {
				this.modelManager.resetOutputModel();
				logger.info("Transform bubbles: " + transformees);	
				for(Bubble<? extends Operator> b : transformees) {
					this.tEngine.addConfiguration(b.getConfiguration());
				}
				this.tresults = this.tEngine.transform();
				this.modelManager.saveOutputModel();
				this.tEngine.clearConfigurations();
			} while(this.modelManager.next());
		} catch (TransformationException e) {
			logger.error("Error during Transformation", e);
		}
	}
	
	
	
	public boolean c2cConfigFound() {
		return this.c2cConfigIt != null && this.c2cConfigIt.hasNext();
	}
	
	
	public boolean a2aConfigFound() {
		return this.a2aConfigIt != null && this.a2aConfigIt.hasNext();
	}
	
	
	public boolean r2rConfigFound() {
		return this.r2rConfigIt != null && this.r2rConfigIt.hasNext();
	}
	
	
	/**
	 * if no mapping operator has been evaluated to true, return true, 
	 * otherwise false.
	 * */
	public boolean mapOpEval2True(){
		return this.mapOpEval2True;
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
	
	
	
	/**
	 * Returns true if the found mapping (C2C) has more unmapped attributes.
	 */
	public boolean c2cPairHasReferences() throws Exception {
		R2RBubble initBubble = new R2RBubble();
		R2RConfiguration initConfig = new R2RConfiguration();
		initBubble.setConfiguration(initConfig);
		initBubble.setContext1((C2CBubble) this.c2cPair.getFirst());
		initBubble.setContext2((C2CBubble) this.c2cPair.getSecond());

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
	
	
	public void eval2FalseGreylisted() {
		logger.info("Eval2False all greylisted C2C-Bubbles");
		for(C2CBubble b : greylist) {
			b.setState(Bubble.STATE.eval2FALSE);
			logger.debug("Eval2False greylisted C2C-Bubble: " + b);
		}
	}
	
	
}
