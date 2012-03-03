package com.big.tuwien.SmartMatcher.operators.homogenic.r2r;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.ModelManager.imodel.ReferenceInstance;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.operators.homogenic.r2r.R2RConfiguration.Roles;
import com.big.tuwien.transformation.NewTransformationHistory;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationHelpers;
import com.big.tuwien.transformation.TransformationResult;
import com.big.tuwien.transformation.Transformer;

public class R2RTransformer implements Transformer {
	private static Logger logger = Logger.getLogger(R2RTransformer.class);
	
	private TransformationEngine engine;
	private NewTransformationHistory history;
	private R2RConfiguration config;
	
	
	public R2RTransformer(TransformationEngine engine) {
		this.engine = engine;
		this.history = this.engine.getHistory();
	}
	
	
	
	public TransformationResult transform(URI inputModel, Configuration<?> _config) throws TransformationException {
		R2RConfiguration config = (R2RConfiguration) _config;
		TransformationResult tresult = new TransformationResult();
		tresult.setOperator(R2R.NAME);
		tresult.setConfiguration(config);
		// counts the number of generated instances on the rhs
		int generatedSize = 0;
		
		this.config = config;
		
		logger.info("Start R2R transformation for configuration: " + config);
		
		boolean somethingTransformed = false;
		
		ReferenceElement lhsElement =  (ReferenceElement) config.getRoleElement(Roles.lhsFocusReference)
																.getRepresentedBy();
		Element rhsElement = config.getRoleElement(Roles.rhsFocusReference);
															
		
		List<? extends InstanceElement<?,?>> _lhsInstances = lhsElement.getEObjects(inputModel);
		List<ReferenceInstance> lhsInstances = (List<ReferenceInstance>) _lhsInstances;
			
		for(ReferenceInstance lhsInstance : lhsInstances) { 
			//isAlreadyTransformed check performed in transform method
			boolean isTransformed = transform(lhsInstance, rhsElement);
			if(isTransformed) {
				somethingTransformed = true;
				generatedSize++;
			}
			
			logger.info("Transform reference object between: " + lhsElement.getFullPathName() + "_2_" + rhsElement.getRepresentedBy().getFullPathName());
		}
		
		if(somethingTransformed) this.history.addTransformedConfiguration(config);
		tresult.setGeneratedSize(generatedSize);
		return tresult;
	
	}
	
	
	/*
	 * Helper method
	 */
	private boolean transform(ReferenceInstance lhsInstance, Element rhsElement) 
						throws TransformationException {
		boolean isAlreadyTransformed = this.history.isTransformed(lhsInstance);
		
		EObject lhsContainerObj = lhsInstance.getContainer();
		EObject lhsPointToObj = lhsInstance.getInstance();
		
		if(! isAlreadyTransformed){
		
			C2CConfiguration context1 = this.config.getContext1();
			C2CConfiguration context2 = this.config.getContext2();
			
			if(this.history.isTransformed(context1) && this.history.isTransformed(context2)) {
				List<ClassInstance> rhsContainerObjs = this.history.getEntry(lhsContainerObj).getRHSHistoryElements(); 
				assert rhsContainerObjs.size() == 1 : "Reference has more than one container object";
				List<ClassInstance> rhsPointedToObjs = this.history.getEntry(lhsPointToObj).getRHSHistoryElements(); 
				assert rhsPointedToObjs.size() == 1 : "Reference has more than one target object";
				ClassInstance rhsContainerInstance = rhsContainerObjs.get(0);
				ClassInstance rhsPointedToInstance = rhsPointedToObjs.get(0);
				
				ReferenceInstance rhsInstance = null;
				try {
					rhsInstance = TransformationHelpers.createReference(engine, rhsElement, rhsContainerInstance, rhsPointedToInstance);
				// thrown if generated reference must be reversed
				// TODO: better implementation for inverse r2r variation 
				} catch(IllegalArgumentException e) {
					rhsInstance = TransformationHelpers.createReference(engine, rhsElement, rhsPointedToInstance, rhsContainerInstance);
				}
				
				// this.history.traceLink(lhsInstance, rhsInstance);
				return true;
			} else {
				throw new TransformationException("Context C2C mapping(s) are not transformed yet");
			}
			
		} else {
			return false;
		}
	}

}
