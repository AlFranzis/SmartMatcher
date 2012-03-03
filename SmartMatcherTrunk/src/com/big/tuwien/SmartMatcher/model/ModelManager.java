package com.big.tuwien.SmartMatcher.model;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.w3c.dom.Document;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.persistance.ModelDeserializer;
import com.big.tuwien.ModelManager.persistance.ModelSerializer;


/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 05-Mai-2008 11:50:04
 */
public class ModelManager {
	private static Logger logger = Logger.getLogger(ModelManager.class);
	
	/* iterator for models */
	private int modelNr = 0;
		
	private String inputModelURI 		= "";
	private String actualModelURI 		= "";
	private String targetModelURI 		= "";
	private String inputMetamodelURI 	= "";
	private String outputMetamodelURI 	= "";
	private String mappingModelURI 		= "";
	private String inputMappingURI		= "";

	private List<Resource> inputModels  = new Vector<Resource>();
	private List<Resource> actualModels = new Vector<Resource>();
	private List<Resource> targetModels = new Vector<Resource>();
	private Resource inputMetaModel = null;
	private Resource outputMetaModel = null;
	private Document mappingModel = null;
	
	private List<EObject> tempOutputModelVector = new Vector<EObject>();
	
	private ModelDeserializer deserializer = null;
	private ModelSerializer serializer = null;
	//private NewMappingModelDeserializer mappingModelDeserializer= null;
	
	private String example;

	
	public ModelManager() {		
		this.deserializer = new ModelDeserializer();
		this.serializer = new ModelSerializer();
		//this.mappingModelDeserializer = new NewMappingModelDeserializer();
	}
	
	
	public void setExample(String example) {
		this.example = example;
	}
	
	
	public void init(){
		Map<String,String> e = ExampleStore.getExample(example);
		
		if(e != null){
			this.mappingModelURI 	= e.get("mappingModelURI");
			this.inputModelURI		= e.get("inputModelURI");
			this.actualModelURI		= e.get("actualModelURI");
			this.inputMetamodelURI	= e.get("inputMetamodelURI");
			this.outputMetamodelURI	= e.get("outputMetamodelURI");
			this.targetModelURI    	= e.get("targetModelURI");
			this.inputMappingURI	= e.get("inputMappingURI");
		}
		if(this.inputMetamodelURI == null || this.outputMetamodelURI == null){
			logger.error("Error reading metamodel !");
		}
		this.loadModels();
	}
	

	public String getLHSMetamodelURI(){
		return this.inputMetaModel.getURI().toFileString();
	}
	
	
	public String getRHSMetamodelURI(){
		return this.outputMetaModel.getURI().toFileString();
	}

	
	public String getInputMapping() {
		return inputMappingURI;
	}
	
	
	public boolean hasNextModels() {
		Map<String,String> e = ExampleStore.getExample(example);
		
		if(e != null){
			this.inputModelURI		= e.get("inputModelURI").replace(".xmi", "_" + (modelNr+1) + ".xmi");
			
			File f = new File(this.inputModelURI);
			if (f.exists()) {
				nextModels();
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	
	private void nextModels() {
		Map<String,String> e = ExampleStore.getExample(example);
		
		if(e != null){
			this.inputModelURI		= e.get("inputModelURI").replace(".xmi", "_" + (modelNr+1) + ".xmi");
			this.actualModelURI		= e.get("actualModelURI").replace(".xmi", "_" + (modelNr+1) + ".xmi");
			this.targetModelURI    	= e.get("targetModelURI").replace(".xmi", "_" + (modelNr+1) + ".xmi");
			
			modelNr++;
		}
	}
	
	
	public boolean next() {
		modelNr++;
		if (this.inputModels.size() > modelNr) {
			return true;
		} else {
			modelNr = 0;
			return false;
		}
	}
	
	
	public void resetModels() {
		modelNr = 0;
	}
	

	public void loadModels() {
		resetModels();
		
		this.inputMetaModel  = this.deserializer.getMetamodelResource(this.inputMetamodelURI);
		this.outputMetaModel = this.deserializer.getMetamodelResource(this.outputMetamodelURI);
		do {
			this.inputModels.add(this.deserializer.getModelResource(this.inputMetamodelURI, this.inputModelURI));
			this.actualModels.add(this.serializer.getOutputResource(this.outputMetaModel, this.actualModelURI));
			this.targetModels.add(this.deserializer.getModelResource(this.outputMetamodelURI, this.targetModelURI));
		} while (hasNextModels());
		
		resetModels();
		//this.mappingModel = this.mappingModelDeserializer.readMappingModelFor(this.mappingModelURI);
	}
	
	
	/**Puts the collected EObjects in the outputModel Resource and saves the Resource to file*/
	public void saveOutputModel(){
		this.actualModels.get(modelNr).getContents().clear();
		this.actualModels.get(modelNr).getContents().addAll(this.tempOutputModelVector);
		this.serializer.saveOutputModel(this.actualModels.get(modelNr));
		this.actualModels.add(this.actualModels.get(modelNr));
	}

	
	public Resource getInputMetaModel(){
		return inputMetaModel;
	}

	
	public Resource getOutputMetaModel(){
		return outputMetaModel;
	}
	
	
	public void loadMappingConfiguration() {
		//Document mappingConfigDoc = this.mappingModelDeserializer.readMappingConfiguration(this.mappingModelURI); // reloading of available mappingModels
		//this.setMOPsInMappingMatrix(mappingConfigDoc);
	}
	
	
	public Resource getOutputModel() {
		return actualModels.get(modelNr);
	}
	
	
	/**
	 * Removes all elements of the output resource
	 * */
	public void resetOutputModel(){
		//TODO: should be made somewhere else
		getTempOutputModelList().clear();
		actualModels.get(modelNr).getContents().clear();
	}

	
	public List<EObject> getTempOutputModelList() {
		return tempOutputModelVector;
	}

	
	public String getMappingModelURI() {
		return mappingModelURI;
	}

	
	public Resource getInputModel() {
		return this.inputModels.get(modelNr);
	}
	
	
	public List<Resource> getInputModels() {
		return this.inputModels;
	}
	
	
	public Resource getTartetModel() {
		return this.targetModels.get(modelNr);
	}
	
	
	public List<Resource> getTargetModels() {
		return this.targetModels;
	}

	
	public Document getMappingModel() {
		return mappingModel;
	}
	
}
