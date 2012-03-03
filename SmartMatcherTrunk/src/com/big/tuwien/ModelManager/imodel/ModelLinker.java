package com.big.tuwien.ModelManager.imodel;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;

public class ModelLinker {
	private static Logger logger = Logger.getLogger(ModelLinker.class);
	
	
	public static void linkModels(ModelManager modelManager, MetaModelFactory mmFactory) 
							throws ModelParsingException {
		MetaModel lhsMetaModel = mmFactory.getLHSMetaModel();
		for (Resource inputModel: modelManager.getInputModels()) {
			linkModel(inputModel, lhsMetaModel);
		}
		
		MetaModel rhsMetaModel = mmFactory.getRHSMetaModel();
		for (Resource targetModel: modelManager.getTargetModels()) {
			linkModel(targetModel, rhsMetaModel);
		}
	}
	
	
	public static void linkModel(Resource rmodel, MetaModel mmodel) throws ModelParsingException {
		logger.info("Linking model " + rmodel.getURI() + " to the Meta-Model elements");
		InstanceModel model = new ModelParser().parseModel(rmodel,mmodel);
		for(Element element : mmodel.getElements()) {
			String qName = element.getFullPathName();
			logger.debug("Element selected for linking: " + qName);
			
			Set<? extends InstanceElement<?,?>> instances = null;
			if(element.getRepresentedBy() instanceof ClassElement) {
				instances =  model.getClassInstances(qName);
			} else if(element.getRepresentedBy() instanceof AttributeElement) {
				instances =  model.getAttributeInstances(qName);
			} else if(element.getRepresentedBy() instanceof ReferenceElement) {
				instances =  model.getReferenceInstances(qName);
			}
			
			if(instances != null && !instances.isEmpty()) {
				List<InstanceElement<?,?>> _instances = 
					new Vector<InstanceElement<?,?>>(instances);
				element.getRepresentedBy().setEObject(_instances, rmodel.getURI());
				logger.debug("Added instances to meta-model element " + qName + ": " + _instances);
			} else {
				logger.debug("No instances found for element " + qName);
			}
		}
	}
}
