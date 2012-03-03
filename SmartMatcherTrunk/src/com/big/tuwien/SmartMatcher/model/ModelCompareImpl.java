package com.big.tuwien.SmartMatcher.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;


/**
 * @author Horst Kargl
 * @version 1.0
 * @created 14-July-2008 09:36:10
 * @revised by Manuel Wimmer
 * 
 */

public class ModelCompareImpl {
	private static Logger logger = Logger.getLogger(ModelCompareImpl.class);
	
	private Resource actualModel = null;
	private Resource targetModel = null;
	
	private Map<String, ClassEntry> actualAmountOfClassObjects = new HashMap<String, ClassEntry>();
	private Map<String, ClassEntry> targetAmountOfClassObjects = new HashMap<String, ClassEntry>();
	
	private Map<String, List<AttributeEntry>> actualValueOfAttributeObjects = new HashMap<String, List<AttributeEntry>>();
	private Map<String, List<AttributeEntry>> targetValueOfAttributeObjects = new HashMap<String, List<AttributeEntry>>();
	
	private Map<String, List<ReferenceEntry>> actualValueOfReferenceObjects = new HashMap<String, List<ReferenceEntry>>();
	private Map<String, List<ReferenceEntry>> targetValueOfReferenceObjects = new HashMap<String, List<ReferenceEntry>>();
	
	private List<String> diffModel = new Vector<String>();
	private List<EObject> eObjectsToBeChecked = new Vector<EObject>(); //stores the EObjects with the right amount in the actual and target model

	
	public ModelCompareImpl() {}
	
	
	public List<String> compareModels(Resource actualModel, Resource targetModel) {
		this.actualModel = actualModel;
		this.targetModel = targetModel;
		
		this.resetModelCompare();
		
		this.checkClassObjects();
		this.checkAttributeValues();
		this.checkReferenceLinks();
    	
    	return this.diffModel;
	}
	
	
	public boolean isCorrect(Bubble<? extends Operator> bubble) {
		// Collection<Element> lhsElements = bubble.getConfiguration().getLHSElements();
		Collection<Element> rhsElements = bubble.getConfiguration().getRHSElements();
		
		for(Element e : rhsElements) {
			if(this.diffModel.contains(e.getFullPathName())) return false;
		}
		return true;
	}

	
	private void resetModelCompare() {
		this.actualValueOfAttributeObjects.clear();
		this.actualAmountOfClassObjects.clear();
		this.diffModel.clear();
		this.targetValueOfAttributeObjects.clear();
		this.targetAmountOfClassObjects.clear();
		this.targetValueOfReferenceObjects.clear();
		this.actualValueOfReferenceObjects.clear();
		this.eObjectsToBeChecked.clear();
	}

	
	private void checkClassObjects() {
		logger.debug("Count actual #ClassObject");
		this.countClasses(actualModel, actualAmountOfClassObjects);
		logger.debug("Count target # ClassObject");
		this.countClasses(targetModel, targetAmountOfClassObjects);
		
		for(String key : targetAmountOfClassObjects.keySet()) {
			ClassEntry targetEntry =  this.targetAmountOfClassObjects.get(key);
			ClassEntry actualEntry = this.actualAmountOfClassObjects.get(key);
			
			// EClass targetMetamodelElement = (EClass)targetEntry.get("metamodelElement");
			
			if(actualEntry != null) {
				List<EObject> targetAmount = targetEntry.getAmount();
				List<EObject> actualAmount = actualEntry.getAmount();
				// EClass actualMetamodelElement = actualEntry.getMetamodelElement();
				
				if( targetAmount.size() == 0 || (targetAmount.size() != actualAmount.size())){
					this.diffModel.add(key); 
					logger.info("Found differences in EClass: " + key + ":  actualAmount: "+ actualAmount.size() +"  targetAmount should be:" + targetAmount.size());
				} else {
					logger.info("No differences found for ClassType: " + key);
					// no class differences! now check attributes!
					if(! this.eObjectsToBeChecked.addAll(targetAmount) ){
						logger.debug("Error by adding the eObjects to be checked");
					}	
				}
			} else {
				this.diffModel.add(key); 
				logger.info("Found diffrences in EClass: " + key +": No mapping available");
			}
		}		
	}
	
	
	private void countClasses(Resource model, Map<String,ClassEntry> classObjectMap){
		TreeIterator<EObject> it = model.getAllContents();
		while(it.hasNext()) {
			EObject eObject = it.next();
			EClass eClass = eObject.eClass();
			String className = eClass.getName();
			logger.debug("Class in context: " + className);
			if(classObjectMap.get(className) == null){
				ClassEntry entry = new ClassEntry();
				entry.addAmount(eObject);
				entry.setMetamodelElement(eClass);
				classObjectMap.put(className, entry);
			} else {
				ClassEntry entry = classObjectMap.get(className);
				entry.addAmount(eObject);
			}
		}
	}
	
	
	private void checkReferenceLinks() {
		for(EObject targetEObject : this.eObjectsToBeChecked) {
			logger.debug("Determine Links for eObject of type: " + targetEObject.eClass().getName());
			this.determineReferenceLinks(targetEObject, this.targetValueOfReferenceObjects);
		}
		
		//fill actualValueOfReferenceObjects
		TreeIterator<EObject> actualModelElementIt = this.actualModel.getAllContents();
		while(actualModelElementIt.hasNext()){
			EObject actualEObject = actualModelElementIt.next();
			logger.debug("Determine Links for eObject of type: " + actualEObject.eClass().getName());
			this.determineReferenceLinks(actualEObject, this.actualValueOfReferenceObjects);
			
		}		
		
		Set<String> keySet = this.targetValueOfReferenceObjects.keySet();
		for(String key : keySet) {
			List<ReferenceEntry> targetEntries = this.targetValueOfReferenceObjects.get(key);
			List<ReferenceEntry> actualEntries = this.actualValueOfReferenceObjects.get(key);
			
			if(actualEntries != null) {
				for(ReferenceEntry actualEntry : actualEntries) {
					EObject sourceObj = actualEntry.getContainer();
					EReference reference = actualEntry.getMetamodelElement();
					EObject targetObj = actualEntry.getTarget();
					
					for(ReferenceEntry targetEntry : targetEntries) {
						EObject sourceObjT = targetEntry.getContainer();
						EReference referenceT = targetEntry.getMetamodelElement();
						EObject targetObjT = targetEntry.getTarget();
						
						if(! compareObjects(sourceObjT.eClass().getName() + "_" + referenceT.getName() + "_" + targetObjT.eClass().getName(), sourceObj.eClass().getName() + "_" + reference.getName() + "_" + targetObj.eClass().getName())){
							logger.debug("Found differences for: actualLink: '" + sourceObjT.eClass().getName() + "_" + referenceT.getName() + "_" + targetObjT.eClass().getName());
						} else {
							targetEntries.remove(targetEntry);
							logger.debug("Similar Values: " + key + " .");				
							break;
						}
					}
				}
			}
			
			if(targetEntries.size() > 0) {
				this.diffModel.add(key);
				logger.info("Differences for :" + key);
			} else {
				logger.info("No differences found for :" + key);
			}
		}
	}
	
	
	private void determineReferenceLinks(EObject eObject, Map<String,List<ReferenceEntry>> valueOfReferenceObjects) {
		EList<EReference> referenceList  = eObject.eClass().getEAllReferences();
		for(EReference eReference : referenceList) {
			Object refObject = eObject.eGet(eReference);
			String key = "";
			if(refObject instanceof EList ){
				EList<EObject> pointToList = (EList<EObject>) refObject;
				for(EObject targetObject : pointToList) {
					ReferenceEntry entry = new ReferenceEntry();
					entry.setContainer(eObject);
					entry.setMetamodelElement(eReference);
					entry.setTarget(targetObject);
					
					String pointsToName = ((EObject)targetObject).eClass().getName();
					key = eObject.eClass().getName() + "_" + eReference.getName() + "_" + pointsToName;	
					
					if(valueOfReferenceObjects.containsKey(key)) {
						(valueOfReferenceObjects.get(key)).add(entry);
					} else {
						List<ReferenceEntry> v = new Vector<ReferenceEntry>();
						v.add(entry);
						valueOfReferenceObjects.put(key, v);
					}
				}
			} else if (refObject != null) {
				EObject pointTo = (EObject) refObject;
				String pointsToName = pointTo.eClass().getName();
				key = eObject.eClass().getName() + "_"+eReference.getName() + "_" + pointsToName;
				
				ReferenceEntry entry = new ReferenceEntry();
				entry.setContainer(eObject);
				entry.setMetamodelElement(eReference);
				entry.setTarget(pointTo);
				
				if(valueOfReferenceObjects.containsKey(key)) {
					(valueOfReferenceObjects.get(key)).add(entry);
				} else {
					List<ReferenceEntry> v = new Vector<ReferenceEntry>();
					v.add(entry);
					valueOfReferenceObjects.put(key, v);
				}
			}

			logger.debug("determined reference: " + key);
			
		}		
	}
	
	
	private void checkAttributeValues() {
		//search all remembered EObjects in eObjectsToBeChecked
		for(EObject targetEObject : this.eObjectsToBeChecked) {
			logger.debug("target attribute of: " + targetEObject.eClass().getName());
			this.determineAttributeValues(targetEObject, this.targetValueOfAttributeObjects);
		}
		
		//search all EObjects in the actual resource
		TreeIterator<EObject> treeIt = this.actualModel.getAllContents();
		while(treeIt.hasNext()){
			EObject actualEObject = treeIt.next();
			logger.debug("Actual attribute of: " + actualEObject.eClass().getName());
			this.determineAttributeValues(actualEObject, this.actualValueOfAttributeObjects);
		}
	
		Set<String> keySet = this.targetValueOfAttributeObjects.keySet();
		for(String key : keySet) {
			List<AttributeEntry> targetEntries = this.targetValueOfAttributeObjects.get(key);
			List<AttributeEntry> actualEntries = this.actualValueOfAttributeObjects.get(key);
			
			//TODO: there is no checked to which object an attribute value belongs. only the set of attribute values for an attribute must be equal
			if(actualEntries != null){
				for(AttributeEntry actualEntry : actualEntries) {
					Object actualValue = actualEntry.getValue();
					
					for(AttributeEntry targetEntry : targetEntries) {
						Object targetValue = targetEntry.getValue();
						
						if(! compareObjects(targetValue, actualValue)) {
							logger.debug("Found differences for: " + key + ": actualValue: '" + actualValue + "'  targetValue should be: '" + targetValue + "'");
						} else {
							targetEntries.remove(targetEntry);
							logger.debug("Similar Values " + key + ": actualValue: '" + actualValue + "'  ==  '" + targetValue + "'");				
							break;
						}
					}
				}
			}
			
			if(targetEntries.size() > 0){	
				this.diffModel.add(key);
				logger.info("Differences for :" + key);
			}else{
				logger.info("No differences found for :" + key);
			}
		}
	}
	
	
	private void determineAttributeValues(EObject containerEObject, Map<String,List<AttributeEntry>> attributeObjectHashMap){
		EClass eClass = containerEObject.eClass();
		String eClassName = eClass.getName();
		EList<EAttribute> attributeList = eClass.getEAllAttributes();
		
		for(EAttribute eAttribute : attributeList) { 
			String attributeName = eAttribute.getName();
			Object attValue = containerEObject.eGet(eAttribute);
			String key = eClassName + "." + attributeName;

			if(attValue != null) {
				AttributeEntry entry = new AttributeEntry();
				entry.setMetamodelElement(eAttribute);
				entry.setValue(attValue);
				entry.setContainer(containerEObject);
				
				if(attributeObjectHashMap.containsKey(key)) {
					(attributeObjectHashMap.get(key)).add(entry);
				} else {
					List<AttributeEntry> v = new Vector<AttributeEntry>();
					v.add(entry);
					attributeObjectHashMap.put(key, v);
				}
				logger.debug(key + ": " + attValue + " Stored attribute value" );
			} else {
				logger.debug("Is not set: Attribute: " + key + " == null ");
			}
		}
	}
	
	
	/**
	 * returns true if the provided objects are structural identical
	 * */
	private boolean compareObjects(Object targetValue, Object actualValue) {
		String s1 = targetValue.toString();
			String s2 = actualValue.toString();
			
			boolean result = s1.equals(s2);
			
			return result;
	}
}