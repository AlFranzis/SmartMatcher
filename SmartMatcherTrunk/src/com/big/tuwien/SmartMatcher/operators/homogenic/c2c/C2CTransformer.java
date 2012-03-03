package com.big.tuwien.SmartMatcher.operators.homogenic.c2c;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration.Roles;
import com.big.tuwien.transformation.NewTransformationHistory;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationHelpers;
import com.big.tuwien.transformation.TransformationResult;
import com.big.tuwien.transformation.Transformer;

public class C2CTransformer implements Transformer {
	private static Logger logger = Logger.getLogger(C2CTransformer.class);
	
	private TransformationEngine engine;
	private NewTransformationHistory history;
	
	
	public C2CTransformer(TransformationEngine engine) {
		this.engine = engine;
		this.history = engine.getHistory();
	}
	
	
	public TransformationResult transform(URI inputModel, Configuration<?> _config) {
		C2CConfiguration config = (C2CConfiguration) _config;
		TransformationResult tresult = new TransformationResult();
		tresult.setOperator(C2C.NAME);
		tresult.setConfiguration(config);
		// counts the number of generated instances on the rhs
		int generatedSize = 0;
		
		logger.info("Start C2C transformation for configuration: " + config);
		
		boolean somethingTransformed = false;
		
		EcoreElement lhsClass = config.getRoleElement(Roles.lhsFocusClass).getRepresentedBy();
		List<? extends InstanceElement<?,?>> _lhsInstances = lhsClass.getEObjects(inputModel);
		List<ClassInstance> lhsInstances = (List<ClassInstance>) _lhsInstances;
		for(ClassInstance lhsInstance : lhsInstances) {
			boolean isAlreadyTransformed = this.history.isTransformed(lhsInstance);
			
			if(! isAlreadyTransformed) {
				ClassElement rhsElement = (ClassElement) config.getRoleElement(Roles.rhsFocusClass).getRepresentedBy();
				EObject rhsObj = TransformationHelpers.createClass(engine.getOutputMetaModel(), rhsElement);
				ClassInstance rhsInstance = new ClassInstance((ClassElement) rhsElement, rhsObj);
				generatedSize++;
				this.history.trace2(lhsInstance, rhsInstance);
				this.engine.addToOutputModel(rhsObj);
				somethingTransformed = true;
				
				logger.info("Transform class object between: " + lhsClass.getFullPathName() + "_2_" 
						+ rhsElement.getFullPathName() + rhsObj.toString());
			}
			
		}
		
		if(somethingTransformed) this.history.addTransformedConfiguration(config);
		tresult.setGeneratedSize(generatedSize);
		return tresult;
	}
	
}
