package com.big.tuwien.SmartMatcher.model;

import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;

public class NewSimpleFitnessFunction {
    private ModelCompareImpl modelCompare = new ModelCompareImpl();
    private ModelManager modelManager;
    
    
    public NewSimpleFitnessFunction() {}
    
    
    public void setModelManager(ModelManager modelManager) {
    	this.modelManager = modelManager;
    }
    
    
    public boolean evaluate(Bubble<? extends Operator> bubble) {
    	//the evaluation is based on model comparison
    	do {
    		Resource actualModel = modelManager.getOutputModel();
    		Resource targetModel = modelManager.getTartetModel();
    		modelCompare.compareModels(actualModel, targetModel);
    		if (!modelCompare.isCorrect(bubble))
    			return false;
    	} while(this.modelManager.next());
    	
    	return true;
    }
}
