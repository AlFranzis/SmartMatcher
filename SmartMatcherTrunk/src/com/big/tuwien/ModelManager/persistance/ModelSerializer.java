package com.big.tuwien.ModelManager.persistance;


import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ModelSerializer  {
	private static Logger logger = Logger.getLogger(ModelSerializer.class);

	
	public ModelSerializer() {}
	
	
	public EPackage getRootPackage4Metamodel(String outputMetamodelURI){
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String,Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new EcoreResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		
		EPackage rootPackage = null;
		try {
			Resource metamodel = resSet.getResource(URI.createFileURI(outputMetamodelURI), true);
			rootPackage = (EPackage) metamodel.getContents().get(0); 
			resSet.getPackageRegistry().put(rootPackage.getNsURI(), rootPackage);
		} catch (RuntimeException e1) {
			logger.error("Error during loading metamodel: " + e1.toString());
		}
		
		return rootPackage;
	}
	
	
	public Resource getOutputResource(Resource rhsMetamodelResource, String outputModelURI){
		Resource modelRes = null;
		try {
			ResourceSet rs = rhsMetamodelResource.getResourceSet();
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
			URI modelURI2 = URI.createFileURI(outputModelURI);
			modelRes = rs.createResource(modelURI2);
			
			if(modelRes == null){
				logger.error("Resource is null: no factory is registered.");
			}
		} catch (RuntimeException e1) {
			logger.error("Error during creation of model resource: " + e1);
		}
		
		return modelRes;
	}
	
	
	public void saveOutputModel(Resource modelRes){
        try {
            modelRes.save(null);
            logger.debug("RHS Model saved: " + modelRes.getURI().path());
        } catch (IOException e) {
        	logger.error("Error saving RHS Model: " + modelRes.getURI().path(), e);
        }		
	}
	
	
	public void saveOutputModel(EObject rootObject, String outputModelURI, String outputMetamodelURI){
		Resource modelRes = null;
		try {
			ResourceSet modelRS = new ResourceSetImpl(); 
			modelRS.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
			URI modelURI2 = URI.createURI(outputModelURI);
			modelRes = modelRS.createResource(modelURI2);
		} catch (RuntimeException e1) {
			logger.error("Error during creation of model resource" + e1);
		}
        modelRes.getContents().add(rootObject);
        try {
            modelRes.save(null);
            logger.debug("RHS Model saved: " + outputModelURI.toString());
        } catch (IOException e) {
        	logger.error("Error saving RHS Model: " + outputModelURI.toString(), e);
        }
	}
	
}
