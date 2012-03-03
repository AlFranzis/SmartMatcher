package com.big.tuwien.transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.Configuration;

public class TransformationEngine {
	protected ModelManager modelManager;
	protected List<Configuration<?>> configs = new Vector<Configuration<?>>();
	protected Map<String,Transformer> transformers = new HashMap<String,Transformer>(); 
	protected NewTransformationHistory history = new NewTransformationHistory();
	
	
	public TransformationEngine(ModelManager modelManager) {
		this.modelManager = modelManager;
	}
	
	
	public List<TransformationResult> transform() throws TransformationException {
		this.history.clear();
		List<TransformationResult> tresults = new Vector<TransformationResult>();
		for(Configuration<?> config : configs) {
			Transformer transformer = transformers.get(config.getOperatorName());
			TransformationResult tr = transformer.transform(this.modelManager.
											getInputModel().getURI(), config);
			tresults.add(tr);

		}
		return tresults;
	}
	
	
	public void addConfiguration(Configuration<?> config) throws TransformationException {
		if(!config.isComplete())
			throw new TransformationException("Configuration is not complete: " + config);
		
		this.configs.add(config);
	}
	
	
	public void clearConfigurations() {
		this.configs.clear();
	}
	
	
	public void addToOutputModel(EObject obj) {
		modelManager.getTempOutputModelList().add(obj);
	}
	
	
	public void removeFromOutputModel(EObject obj) {
		modelManager.getTempOutputModelList().remove(obj);
	}
	
	
	public NewTransformationHistory getHistory() {
		return this.history;
	}
	
	
	public Resource getOutputMetaModel() {
		return modelManager.getOutputMetaModel();
	}
	
	
	public void addTransformer(String op, Transformer t) {
		this.transformers.put(op, t);
	}
	
}
