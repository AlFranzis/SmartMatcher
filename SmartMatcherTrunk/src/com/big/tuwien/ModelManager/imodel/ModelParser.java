package com.big.tuwien.ModelManager.imodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class ModelParser {
	private static Logger logger = Logger.getLogger(ModelParser.class);
	
	// Maps: EObject class instance -> wrapper class instance 
	private Map<EObject,ClassInstance> clazzes = new HashMap<EObject,ClassInstance>();
	
	
	public ModelParser() {}
	

	public InstanceModel parseModel(Resource model, MetaModel mmodel) throws ModelParsingException {
		InstanceModel iModel = new InstanceModel();
		
		//get first element
		EObject rootObj = (EObject)model.getContents().get(0);
		
		// parse all classes in the instance model
		for(Iterator<EObject> it = rootObj.eAllContents(); it.hasNext();) {
			EObject clazz = it.next();
			parseClass(clazz, iModel, mmodel);
		}	
		
		// parse root element if it is a class not a package
		if(rootObj.eClass() instanceof EClass) {
			parseClass(rootObj, iModel, mmodel);
		}
		
		// set cross references
		for(ReferenceInstance ri : iModel.getReferenceInstances()) {
			ClassInstance pointedToInstance = clazzes.get(ri.getInstance());
			pointedToInstance.addCrossReference(ri);
			ri.setPointedToInstance(pointedToInstance);
			logger.debug("Set cross reference instance " + ri + " that points to class instance " + pointedToInstance);
		}
		
		return iModel;
	}
	
	
	/*
	 * This model parses a given class instance and adds the class itself and all it's attributes
	 * and contained references to the given instance model ({@link InstanceModel}.
	 * @param clazz The class instance to parse.
	 * @param model The instance model that should be built as a result of parsing.
	 * @param mmodel The meta-model repository to fetch meta-elements from.
	 * @throws ModelParsingException
	 */
	private void parseClass(EObject clazz, InstanceModel model, MetaModel mmodel) throws ModelParsingException {
		String qClassName = clazz.eClass().getName();
		Element cElement = mmodel.getClassByQName(qClassName);
		if(cElement == null) throw new ModelParsingException("Meta-Model does not contain class with name: " + qClassName);
		
		ClassInstance ci = new ClassInstance(cElement, clazz.eClass(), clazz);
		clazzes.put(clazz, ci);
		model.addClassInstance(ci);
		logger.debug("Parsed class: " + qClassName + " object: " + clazz);
		
		// iterate through every attribute of object
		for(EAttribute attribute : clazz.eClass().getEAllAttributes()) {
			String qAttName = qClassName + "." + attribute.getName();
			Element aElement = mmodel.getAttributeByQName(qAttName);
			if(aElement == null) throw new ModelParsingException("Meta-Model does not contain attribute with name: " + qAttName);
			
			Object attributeObjs = clazz.eGet(attribute); // get the value of the attributes
			// attribute does not exist in the instance model
			if(attributeObjs == null) {
				logger.debug("Attribute " + qAttName + " has no model instance");
				// no instances
			} else if(attributeObjs instanceof EList) {
				for(Iterator<Object> it = ((EList<Object>)attributeObjs).iterator(); it.hasNext();) {
					Object attributeObj = it.next();
					AttributeInstance ai = new AttributeInstance(aElement,attribute,attributeObj,clazz);
					model.addAttributeInstance(ai);
					ci.addAttribute(ai);
					ai.setContainerInstance(ci);
					logger.debug("Parsed attribute: " + qAttName + " value: " + attributeObj);
				}
			} else {
				AttributeInstance ai = new AttributeInstance(aElement,attribute,attributeObjs,clazz);
				model.addAttributeInstance(ai);
				ci.addAttribute(ai);
				ai.setContainerInstance(ci);
				logger.debug("Parsed attribute: "+ qAttName +" value: " + attributeObjs);
			}
		}
		
		// iterate through all references for object 
		for(EReference reference : clazz.eClass().getEAllReferences()) {
			EClass referencedClass = reference.getEReferenceType();
			Object pointToObjs = clazz.eGet(reference);
			// reference has not target object -> reference instance does not exist 
			if(pointToObjs == null) {
				String qRefName = qClassName + "_" + reference.getName() + "_" + referencedClass.getName();
				logger.debug("Reference " + qRefName + " has no model instance");
			} else if(pointToObjs instanceof EList) {
				for(Iterator<EObject> it = ((EList<EObject>)pointToObjs).iterator(); it.hasNext();) {
					EObject pointToObj = it.next();

					String qRefName = qClassName + "_" + reference.getName() + "_" +
											pointToObj.eClass().getName();

					Element rElement = mmodel.getReferenceByQName(qRefName);
					if(rElement == null) throw new ModelParsingException("Meta-Model does not contain reference with name: " + qRefName);

					ReferenceInstance ri = new ReferenceInstance(rElement, reference, pointToObj, clazz);
					model.addReferenceInstance(ri);
					ci.addReference(ri);
					ri.setContainerInstance(ci);
					logger.debug("Parsed reference: " + qRefName + ", pointsToObject: " + pointToObj);
				}
			} else {
				EObject pointToObj = (EObject) pointToObjs;

				String qRefName = qClassName + "_" + reference.getName() + "_" +  
									((EObject)pointToObjs).eClass().getName();
				
				Element rElement = mmodel.getReferenceByQName(qRefName);
				if(rElement == null) throw new ModelParsingException("Meta-Model does not contain reference with name: " + qRefName);

				ReferenceInstance ri = new ReferenceInstance(rElement, reference, pointToObj, clazz);
				model.addReferenceInstance(ri);
				ci.addReference(ri);
				ri.setContainerInstance(ci);
				logger.debug("Parsed reference: " + qRefName + ", pointsToObject: " + pointToObj);
			}
		}	
	}

}
