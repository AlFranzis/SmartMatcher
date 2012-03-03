package com.big.tuwien.SmartMatcher.operators.heterogenic.a2r;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.ModelManager.imodel.AttributeInstance;
import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.ModelManager.imodel.ReferenceInstance;
import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.a2r.A2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.transformation.NewTransformationHistory;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationHelpers;
import com.big.tuwien.transformation.TransformationResult;
import com.big.tuwien.transformation.Transformer;

public class A2RTransformer implements Transformer {
	private static Logger logger = Logger.getLogger(A2RTransformer.class);
	
	private TransformationEngine engine;
	private NewTransformationHistory history;
	private A2RConfiguration config;
	
	
	public A2RTransformer(TransformationEngine engine) {
		this.engine = engine;
		this.history = this.engine.getHistory();
	}
	
	
	public TransformationResult transform(URI inputModel, Configuration<?> _config) throws TransformationException {
		A2RConfiguration config = (A2RConfiguration) _config;
		TransformationResult tresult = new TransformationResult();
		tresult.setOperator(A2R.NAME);
		tresult.setConfiguration(config);
		// counts the number of generated instances on the rhs
		int generatedSize = 0;
		
		this.config = config;
		
		logger.info("Start A2R transformation for configuration: " + config);
		
		boolean somethingTransformed = false;
		
		AttributeElement lhsFocusAtt1 = (AttributeElement) config.getRoleElement(Roles.lhsFocusAttribute1)
																.getRepresentedBy();
		AttributeElement lhsFocusAtt2 = (AttributeElement) config.getRoleElement(Roles.lhsFocusAttribute2)
																.getRepresentedBy();
		Element rhsFocusRef = config.getRoleElement(Roles.rhsFocusReference);														
		
		List<? extends InstanceElement<?,?>> _lhsFocusAtt1Instances = lhsFocusAtt1.getEObjects(inputModel);
		List<AttributeInstance> lhsFocusAtt1Instances = (List<AttributeInstance>) _lhsFocusAtt1Instances;
			
		List<? extends InstanceElement<?,?>> _lhsFocusAtt2Instances = lhsFocusAtt2.getEObjects(inputModel);
		List<AttributeInstance> lhsFocusAtt2Instances = (List<AttributeInstance>) _lhsFocusAtt2Instances;
			
		C2CConfiguration context1 = this.config.getContext1();
		C2CConfiguration context2 = this.config.getContext2();
		if(this.history.isTransformed(context1) && this.history.isTransformed(context2)) {
			// try to create references on the rhs for all combination pairs of
			// lhsFocusAttributes1 and lhsFocusAttributes2
			for(AttributeInstance lhsFocusAtt1Instance : lhsFocusAtt1Instances) { 
				for(AttributeInstance lhsFocusAtt2Instance : lhsFocusAtt2Instances) {
					// if the values of the attributes are equal -> create reference on the rhs
					if(lhsFocusAtt1Instance.getInstance().equals(lhsFocusAtt2Instance.getInstance())) {
						createReference(rhsFocusRef, lhsFocusAtt1Instance.getContainer(), 
								lhsFocusAtt2Instance.getContainer());
						somethingTransformed = true;
						generatedSize++;
						
					}
				}
			}
		} else {
			throw new TransformationException("Context C2C mapping(s) are not transformed yet");
		}
			
		logger.info("Transformed A2R");
		
		if(somethingTransformed) this.history.addTransformedConfiguration(config);
		tresult.setGeneratedSize(generatedSize);
		return tresult;
	
	}
	
	
	private static class AttributePair {
		private AttributeInstance first;
		private AttributeInstance second;
		
		
		public AttributePair(AttributeInstance first, AttributeInstance second) {
			this.first = first;
			this.second = second;
		}

		
		public AttributeInstance getFirst() {
			return first;
		}
		
		
		public AttributeInstance getSecond() {
			return second;
		}
	}
	
	
	
	private ReferenceInstance createReference(Element ref, EObject lhsContainerObj1, EObject lhsContainerObj2) {
		List<ClassInstance> rhsContainerObjs1 = this.history.getEntry(lhsContainerObj1).getRHSHistoryElements(); 
		assert rhsContainerObjs1.size() == 1 : "Reference has more than one container object";
		List<ClassInstance> rhsContainerObjs2 = this.history.getEntry(lhsContainerObj2).getRHSHistoryElements(); 
		assert rhsContainerObjs2.size() == 1 : "Reference has more than one target object";
		ClassInstance rhsContainerInstance1 = rhsContainerObjs1.get(0);
		ClassInstance rhsContainerInstance2 = rhsContainerObjs2.get(0);
		
		ReferenceInstance rhsInstance = null;
		try {
			rhsInstance = TransformationHelpers.createReference(engine, ref, rhsContainerInstance1, rhsContainerInstance2);
		// thrown if generated reference must be reversed
		// TODO: better implementation for inverse r2r variation 
		} catch(IllegalArgumentException e) {
			rhsInstance = TransformationHelpers.createReference(engine, ref, rhsContainerInstance2, rhsContainerInstance1);
		}
		return rhsInstance;
	}
}
