package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.List;
import java.util.Set;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class XMLMetaModel implements XMLAny {
	private Set<XMLClassElement> classes;
	
	
	public XMLMetaModel() {}
	
	
//	public static XMLMetaModel getInstance(MetaModel mm) {
//		List<Element> classes = mm.getClasses();
//		Set<XMLClassElement> xmlClasses = XMLElementFactory.getInstances2(classes);
//		
//		XMLMetaModel xmlMM = new XMLMetaModel();
//		xmlMM.setClasses(xmlClasses);
//		return xmlMM;
//	}
	
	
	public static XMLMetaModel getInstance(MetaModel mm, XMLElementResolverFactory resolverFactory) {
		List<Element> classes = mm.getClasses();
		Set<XMLClassElement> xmlClasses = resolverFactory.resolve2(classes);
		
		XMLMetaModel xmlMM = new XMLMetaModel();
		xmlMM.setClasses(xmlClasses);
		return xmlMM;
	}
	
	
	public Set<XMLClassElement> getClasses() {
		return classes;
	}


	public void setClasses(Set<XMLClassElement> classes) {
		this.classes = classes;
	}
	
}
