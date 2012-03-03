package com.big.tuwien.SmartMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.big.tuwien.ModelManager.imodel.InstanceElement;

/**
 * @author Milo Nanuk
 */
public abstract class EcoreElement {
	protected Element represents;
	protected Map<URI,List<InstanceElement<?,?>>> eObjects = new HashMap<URI, List<InstanceElement<?,?>>>();
	
	
	public EcoreElement(Element e){
		this.represents = e;
	}

	
	public Map<URI,List<InstanceElement<?,?>>> getEObjectsMap() {
		return this.eObjects;
	}
	
	
	public void setEObjectsMap(Map<URI,List<InstanceElement<?,?>>> eObjects) {
		this.eObjects = eObjects;
	}
	
	
	public void setEObject(List<InstanceElement<?,?>> eObjects, URI file){
		this.eObjects.put(file, eObjects);
	}
	
	
	public List<? extends InstanceElement<?,?>> getEObjects(URI file) {
		return this.eObjects.get(file);
	}

	
	public Element getRepresents(){
		return this.represents;
	}
	
	
	public abstract ClassElement getContainedIn();

	
	public abstract EcoreElement getPointsTo();

	
	public abstract void setEcoreElementReference(EObject eObject);
	
	
	public abstract EObject getEcoreElementReference();


	public abstract String getFullPathName();
	
	
	public abstract EcoreElement copy(Element copy);
	
}