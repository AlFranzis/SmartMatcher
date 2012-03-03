package com.big.tuwien.SmartMatcher.mmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.LHS_MMElement;
import com.big.tuwien.SmartMatcher.RHS_MMElement;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.util.QueryUtil;

/**
 * MetaModelFactory which reads the meta-model information 
 * from Eclipse {@link Resource}-Objects.
 * @author alex
 *
 */
public class ResourceMetaModelFactory implements MetaModelFactory {
	private static int idGenerator = 0;
	private enum SIDE {LHS, RHS}
	
	private Resource lhs, rhs;
	private MetaModel lhsMM, rhsMM;
	private List<ElementFilter> filters = new Vector<ElementFilter>();
	
	public ResourceMetaModelFactory() {}
	
	
	public void setLHSResource(Resource lhs) {
		this.lhs = lhs;
	}
	
	
	public void setRHSResource(Resource rhs) {
		this.rhs = rhs;
	}
	
	
	public void build() {
		List<Element> lhsElements = generateElementStructure(lhs, SIDE.LHS);
		List<Element> rhsElements = generateElementStructure(rhs, SIDE.RHS);
		
		generateElementIds(lhsElements);
		generateElementIds(rhsElements);
		
		lhsMM = new MetaModel();
		lhsMM.setElements(lhsElements);
		
		rhsMM = new MetaModel();
		rhsMM.setElements(rhsElements);
		
		applyFilters(this.filters);
	}
	
	
	public MetaModel getLHSMetaModel() {
		return lhsMM;
	}

	
	public MetaModel getRHSMetaModel() {
		return rhsMM;
	}
	
	
	/*
	 * Returns all elements of the list that are subtypes of the given class.
	 */
	protected List<EClass> getSubtypes(EClass clazz, List<Element> classes) {
		List<EClass> subtypes = new Vector<EClass>();
		for(Element c : classes) {
			EClass t = ((ClassElement) c.getRepresentedBy()).getEClassInstance();
			if(t.equals(clazz) && !subtypes.contains(t)) {
				// each class is a subtype of itself (reflexive)
				subtypes.add(t);
			} else {
				List<EClass> supertypes = t.getEAllSuperTypes();
				if(supertypes.contains(clazz) && !subtypes.contains(t))
					subtypes.add(t);
			}
		}
		return subtypes;
	}
	
	
	/*
	 * This method parses the given meta-model and returns a list of all elements
	 * contained in it.
	 */
	private List<Element> generateElementStructure(Resource metamodel, SIDE side){
		List<Element> elements = new Vector<Element>();

		//Load Mapping Model
		EPackage mPackage = (EPackage)metamodel.getContents().get(0);

		TreeIterator<EObject> cententIt = mPackage.eAllContents();
		while (cententIt.hasNext()) {
			EObject object = cententIt.next();

			if(object instanceof EClass){
				EClass eClass = (EClass)object;
				Element _class = this.createElementStructure(eClass, side);
				_class.setName(eClass.getName());
				_class.setAbstract(eClass.isAbstract());
				elements.add( _class ); 
				
				ClassElement clazz = (ClassElement)_class.getRepresentedBy();

				for(EAttribute eAttribute : eClass.getEAllAttributes()) {
					Element _attribute = this.createElementStructure(eAttribute, side);
					_attribute.setName(eAttribute.getName());
					elements.add( _attribute );

					//linking
					AttributeElement att = (AttributeElement)_attribute.getRepresentedBy();
					att.setContainedIn(clazz);
					clazz.getAttributes().add(att);
				}		
			}	
			
		}
		
		List<Element> classes = QueryUtil.filterExceptClasses(elements);
		List<Element> references = createReferences(classes, side);
		
		elements.addAll(references);

		return elements;
	}
	
	
	private List<Element> createReferences(List<Element> classes, SIDE side) {
		List<Element> references = new Vector<Element>();
		
		for(Element clazz : classes) {
			EClass eClass = (EClass) clazz.getRepresentedBy().getEcoreElementReference();
			for(EReference eReference : eClass.getEAllReferences()) {
				EClass pointedTo = eReference.getEReferenceType();
				List<EClass> subPointedTo = getSubtypes(pointedTo, classes);
				
				for(EClass spt : subPointedTo) {
					Element _reference = this.createElementStructure(eReference, side);
					
					_reference.setName(eReference.getName());
					references.add( _reference ); 

					ReferenceElement ref = (ReferenceElement) _reference.getRepresentedBy();
					ref.setContainedIn((ClassElement) clazz.getRepresentedBy()); //ref2ContainingClass
					((ClassElement) clazz.getRepresentedBy()).getReferences().add(ref);//class2ref
					ClassElement cElement = getClassElementFor(spt, classes);
					ref.setPointsTo(cElement);
					cElement.getReferencedByReferences().add(ref); //the ClassElement knows which references points to it !!
					
				}
			}
		}
		return references;
	}
	
	
	/*
	 * This method search for the the ClassElement which is referenced by the pointsTo link from 
	 * an ReferenceElement
	 */
	private ClassElement getClassElementFor(EClass eClass, List<Element> vector){	
		for(Element element : vector) {
			EcoreElement eElement = element.getRepresentedBy();
			if(eElement instanceof ClassElement){
				ClassElement cElement = (ClassElement)eElement;
				EClass refEClass = cElement.getEClassInstance();
				if(eClass.equals(refEClass)){
					return cElement;
				}
			}
		}
		return null;
	}
	
	
	
	/*
	 * This method generates Elements for EObjects
	 */
	private Element createElementStructure(EObject eObject, SIDE side){
		Element newElement = null;
		
		if(side.equals(SIDE.LHS)){
			newElement = new LHS_MMElement();
		}else if(side.equals(SIDE.RHS)){
			newElement = new RHS_MMElement();
		}
		
		EcoreElement ecoreElement = null;
		
		if(eObject instanceof EClass) {
			ecoreElement = new ClassElement(newElement);	
		} else if(eObject instanceof EAttribute) {
			ecoreElement = new AttributeElement(newElement);
		} else if(eObject instanceof EReference) {
			ecoreElement = new ReferenceElement(newElement);
		}
		
		ecoreElement.setEcoreElementReference(eObject);
		newElement.setRepresentedBy(ecoreElement);

		return newElement;
	}
	
	
	private void generateElementIds(List<Element> elements) {
		for(Element e : elements) {
			e.setId(idGenerator++);
		}	
	}
	
//	private void generateElementIds(List<Element> elements) {
//		Random rand = new Random();
//		for(Element e : elements) {
//			e.setId(idGenerator++ + rand.nextInt(1000000));
//		}	
//	}

	
	public void addFilter(ElementFilter filter) {
		if(!filters.contains(filter)) { 
			this.filters.add(filter);
			applyFilters(filters);
		}
	}
	
	
	private void applyFilters(List<ElementFilter> filters) {
		for(ElementFilter f : filters) {
			applyFilter3(f);
		}
	}
	
	
	protected void applyFilter3(ElementFilter f) {
		if(lhsMM != null) {
			List<Element> fLhsElements = applyFilterLhs(f);
			lhsMM = new MetaModel();
			lhsMM.setElements(fLhsElements);
		}
		if(rhsMM != null) {
			List<Element> fRhsElements = applyFilterRhs(f);
			rhsMM = new MetaModel();
			rhsMM.setElements(fRhsElements);
		}
	}
	
	
	private List<Element> applyFilterLhs(ElementFilter f) {
		Map<Integer,Element> filtered = new HashMap<Integer,Element>();
		
		for(Element clazz : lhsMM.getClasses()) {
			filtered = applyFilter(f, clazz, filtered);
		}
		
		return new Vector<Element>(filtered.values());
	}
	
	
	private List<Element> applyFilterRhs(ElementFilter f) {
		Map<Integer,Element> filtered = new HashMap<Integer,Element>();
		
		for(Element clazz : rhsMM.getClasses()) {
			filtered = applyFilter(f, clazz, filtered);
		}
		
		return new Vector<Element>(filtered.values());
	}
	
	
	/*
	 * Applies a filter to a class. The filter is applied to the attributes, references of the given class too. 
	 * On referenced classes the method is invoked recursively, that means classes that are referenced from
	 * the given class are processed / filtered too.
	 * @param f The filter to apply
	 * @param clazz The clazz that the filter should be applied on
	 * @param elems Map(elementId, element) that contains all processed elements before calling this method.
	 * @return Map (elementId, element) that contains all processed (= unfiltered) elements after calling this method.
	 */
	private Map<Integer,Element> applyFilter(ElementFilter f, Element clazz, Map<Integer,Element> elems) {
		Map<Integer,Element> filtered = new HashMap<Integer,Element>();
		filtered.putAll(elems);

		if(!f.filter(clazz) && !elems.containsKey(clazz.getId())) {
			ClassElement _clazz = (ClassElement) clazz.getRepresentedBy();
			
			ClassElement _clazzCopy = _clazz.shallowCopy();
			
			filtered.put(_clazzCopy.getRepresents().getId(),_clazzCopy.getRepresents());
			
			for(ReferenceElement _ref : _clazz.getReferences()) {
				Element ref = _ref.getRepresents();
				if(!f.filter(ref)) {
					ReferenceElement _refCopy = _ref.shallowCopy();
					_refCopy.setContainedIn(_clazzCopy);
					_refCopy.setPointsTo(null);
					_clazzCopy.getReferences().add(_refCopy);
					
					ClassElement _pointedTo = _ref.getPointsTo();
					Element pointedTo = _pointedTo.getRepresents();
					// apply method recursively on pointedTo class
					filtered = applyFilter(f, pointedTo, filtered);
					
					if(filtered.containsKey(pointedTo.getId())) {
						ClassElement _pointToCopy = (ClassElement) filtered.get(pointedTo.getId()).getRepresentedBy();
						assert _pointToCopy != null : "Class copy does not exist";
						_refCopy.setPointsTo(_pointToCopy);	
						
						filtered.put(_refCopy.getRepresents().getId(), _refCopy.getRepresents());
					}
				}
			}

			for(AttributeElement _att : _clazz.getAttributes()) {
				Element att = _att.getRepresents();
				if(!f.filter(att)) {
					AttributeElement _attCopy = _att.shallowCopy();
					_attCopy.setContainedIn(_clazzCopy);
					_clazzCopy.getAttributes().add(_attCopy);
					
					filtered.put(_attCopy.getRepresents().getId(), _attCopy.getRepresents());
				}
			}
		}

		return filtered;
	}


	public MetaModelFactory copy() {
		ResourceMetaModelFactory copy = new ResourceMetaModelFactory();
		copy.lhsMM = this.lhsMM.copy();
		copy.rhsMM = this.rhsMM.copy();
		copy.filters.addAll(this.filters);
		copy.lhs = lhs;
		copy.rhs = rhs;
		return copy;
	}

}
