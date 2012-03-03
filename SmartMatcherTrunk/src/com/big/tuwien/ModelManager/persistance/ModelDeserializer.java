package com.big.tuwien.ModelManager.persistance;


import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * @author milo
 *
 */
public class ModelDeserializer {
	private static Logger logger = Logger.getLogger(ModelDeserializer.class);
	
	
	public Resource getModelResource(String metamodelName, String modelName) {
	
		Resource metamodel = this.getMetamodelResource(metamodelName);
		
		Resource model = null;
		if(metamodel != null){
			ResourceSet resSet = new ResourceSetImpl(); // create new resource set
			EPackage ep0 = (EPackage) metamodel.getContents().get(0); 
			resSet.getPackageRegistry().put(ep0.getNsURI(), ep0);
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put
			("xmi", new XMIResourceFactoryImpl(){
				public Resource createResource(URI uri){
						XMIResource xmiResource = new XMIResourceImpl(uri);
						return xmiResource;
				}
			}); 
			
			try {
				model = resSet.getResource(URI.createFileURI(modelName), true);
			} catch (RuntimeException e2) {
				logger.error("Loading of model failed: " + e2);
			}
		}
		
		return model;	
	}
	
	
	public Resource getMetamodelResource(String metamodelName){
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE; //factory for resources
		Map<String,Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new EcoreResourceFactoryImpl()); //load ecore
		ResourceSet resSet = new ResourceSetImpl(); // create new resource set
	
		Resource metamodel = null;
		try {
			metamodel = resSet.getResource(URI.createFileURI(metamodelName), true);
		} catch (RuntimeException e1) {
			logger.error("Loading of metamodel failed: " + e1);
		}
		
		return metamodel;			
	}
	
}
