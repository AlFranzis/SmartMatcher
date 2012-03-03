package com.big.tuwien.SmartMatcher.views.emf;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import sm_mm.MappingModel;
import sm_mm.Sm_mmFactory;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class EMFModelBuilder {
	private Sm_mmFactory factory;
	private MappingModel mm;
	
	private Map<Element,sm_mm.Element> sm2emf = new HashMap<Element,sm_mm.Element>();
	
	
	public EMFModelBuilder(Sm_mmFactory factory) {
		this.factory = factory;
	}
	
	
	public MappingModel build(MetaModel lhsMM, MetaModel rhsMM) {
		mm = factory.createMappingModel();
		doBuild(lhsMM, rhsMM);
		return mm;
	}
	
	
	public Map<Element,sm_mm.Element> getElementMap() {
		return new HashMap<Element,sm_mm.Element>(sm2emf);
	}
	
	
	public void build(MappingModel mm, MetaModel lhsMM, MetaModel rhsMM) {
		this.mm = mm;
		doBuild(lhsMM, rhsMM);
	}
	
	
	private void doBuild(MetaModel lhsMM, MetaModel rhsMM) {	
		for(Element ce : lhsMM.getClasses()) {
			ClassElement classElement = (ClassElement) ce.getRepresentedBy();
			if(!isParsed(classElement)) {
				sm_mm.Class classNode = generateClassNode(classElement, true);
				mm.getClasses().add(classNode);
			}
		}
		
		for(Element ce : rhsMM.getClasses()) {
			ClassElement classElement = (ClassElement) ce.getRepresentedBy();
			if(!isParsed(classElement)) {
				sm_mm.Class classNode = generateClassNode(classElement, false);
				mm.getClasses().add(classNode);
			}
		}
	}
	
	
	private sm_mm.Class generateClassNode(ClassElement clazz, boolean lhs) {
		EClass c = (EClass) clazz.getEcoreElementReference();
		
		sm_mm.Class clazzNode = factory.createClass();
		
		register(clazz, clazzNode);
		
		clazzNode.setLhs(lhs);
		clazzNode.setName(c.getName());
		//TODO: handle supertypes
		
		for(AttributeElement ae : clazz.getAttributes()) {
			sm_mm.Attribute attributeNode = generateAttributeNode(ae, lhs);
			clazzNode.getAttributes().add(attributeNode);
		}
		
		for(ReferenceElement re : clazz.getReferences()) {
			ClassElement referencedClass = re.getPointsTo();
			
			if(!isParsed(referencedClass)) 
				mm.getClasses().add(generateClassNode(referencedClass, lhs));
			
			sm_mm.Reference referenceNode = generateReferenceLink(re, lhs);
			mm.getReferences().add(referenceNode);
		}
		
		return clazzNode;
	}
	
	
	private sm_mm.Attribute generateAttributeNode(AttributeElement a, boolean lhs) {
		EAttribute ea = (EAttribute) a.getEcoreElementReference();
	
		sm_mm.Attribute attributeNode = factory.createAttribute();
		
		register(a, attributeNode);
		
		attributeNode.setLhs(lhs);
		attributeNode.setName(ea.getName());
		attributeNode.setType(ea.getEAttributeType().getName());
		return attributeNode;
	}
	
	
	private sm_mm.Reference generateReferenceLink(ReferenceElement ref, boolean lhs) {
		EReference er = (EReference) ref.getEcoreElementReference();
		sm_mm.Reference referenceLink = factory.createReference();
		
		register(ref, referenceLink);
		
		referenceLink.setLhs(lhs);
		referenceLink.setName(er.getName());
		referenceLink.setSource((sm_mm.Class) sm2emf.get(ref.getContainedIn()));
		referenceLink.setTarget((sm_mm.Class) sm2emf.get(ref.getPointsTo()));
		return referenceLink;
	}
	
	
	private boolean isParsed(EcoreElement e) {
		return sm2emf.containsKey(e.getRepresents());
	}
	
	
	private void register(EcoreElement ee, sm_mm.Element e) {
		sm2emf.put(ee.getRepresents(), e);
	}
		
}
