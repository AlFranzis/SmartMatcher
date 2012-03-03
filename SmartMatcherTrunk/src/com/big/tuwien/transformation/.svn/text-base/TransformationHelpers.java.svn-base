package com.big.tuwien.transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.ReferenceInstance;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;

public class TransformationHelpers {
	
	
	/**
	 * Creates a reference in the instance model.
	 * @param engine The transformation engine.
	 * @param reference The meta-model reference element the instance belongs to.
	 * @param containerInstance The container class instance of the reference.
	 * @param pointedToInstance The point-to (target) class instance of the reference.
	 * @return The created references instance.
	 */
	@SuppressWarnings("unchecked")
	public static ReferenceInstance createReference(TransformationEngine engine, Element reference, ClassInstance containerInstance, 
			ClassInstance pointedToInstance) {	
		
		EReference referenceElement = (EReference) reference.getRepresentedBy().getEcoreElementReference();
		EObject rhsContainerObj = containerInstance.getInstance();
		EObject rhsPointedToObj = pointedToInstance.getInstance();
		
		// if the reference is a containment reference, then the pointedTo-Element must
		// be removed from the serialization list, because otherwise the element is 2 times
		// in the serialization
		boolean isContainment = referenceElement.isContainment();
		if(isContainment) {
			// TODO: not sure why this is necessary
			engine.removeFromOutputModel(rhsPointedToObj);
		}
		
		Object referencedObjects = rhsContainerObj.eGet(referenceElement); // the referenced Objects
		
		if(referencedObjects instanceof EList) {
			((EList) referencedObjects).add(rhsPointedToObj); //add the link to EList
		} else {
			rhsContainerObj.eSet(referenceElement, rhsPointedToObj); //set link
		}
		
		ReferenceInstance rhsInstance = new ReferenceInstance(reference, referenceElement,
													rhsPointedToObj, rhsContainerObj);
		
		return rhsInstance;
	}
	
	
	
	/**
	 * Creates a class in the instance model.
	 * @param mmodel The meta-model the given class belongs to.
	 * @param clazz The meta-model element class.
	 * @return The created class instance.
	 */
	public static EObject createClass(Resource mmodel, ClassElement clazz) {
		EClass rhsEcoreElement = clazz.getEClassInstance();

		EPackage myPackage = (EPackage) mmodel.getContents().get(0);
		EFactory factory = myPackage.getEFactoryInstance();
		EObject classObj = factory.create(rhsEcoreElement);
		
		return classObj;
	}
}
