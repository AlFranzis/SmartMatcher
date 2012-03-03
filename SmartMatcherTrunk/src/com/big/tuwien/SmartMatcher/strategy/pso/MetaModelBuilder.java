package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class MetaModelBuilder {
	private Map<String, Element_> eBindings = new HashMap<String,Element_>();
	private static int idGenerator = 0;
	
	public class Element_ extends Element {
		protected MetaModel_ mm;
		
		public Element_(String name) {
			this.name = name;
			this.id = idGenerator++;
			eBindings.put(name, this);
		}
		
		@Override
		public Element copy() {
			throw new RuntimeException("method not implemented");
		}

		@Override
		public Element shallowCopy() {
			throw new RuntimeException("method not implemented");
		}
		
		public Element_ as(String name) {
			eBindings.put(name, this);
			return this;
		}
		
		
		public void setMetaModel(MetaModel_ mm) {
			this.mm = mm;
		}
		
		
//		@Override
//		public boolean equals(Object other) {
//			if(this == other) return true;
//			
//			if(other == null || !(other instanceof Element_))
//				return false;
//			
//			Element_ that = (Element_) other;
//			if(this.mm != null && that.mm != null) {
//				return mm.equals(that.mm) 
//					&& name.equals(that.name);
//			} else 
//				return name.equals(that.name);
//		}
//		
//		
//		@Override
//		public int hashCode() {
//			return (7 * Element_.class.hashCode()) 
//						+ (19 * name.hashCode())
//						+ (mm == null ? 0 : 21 * mm.hashCode());
//				
//		}
		
	}
	
	
	public class MetaModel_ extends MetaModel {
		private Map<String, Element_> eBindings = new HashMap<String,Element_>();
		
		public Element get(String name) {
			return eBindings.get(name);
		}
		
		public void setEBindings(Map<String,Element_> eb) {
			eBindings.putAll(eb);
		}
	}
	
	
	public MetaModel_ mm(List<Element_> es) {
		MetaModel_ mm = new MetaModel_();
		List<Element> elements = new Vector<Element>();
		for(Element_ e : es) {
			EcoreElement eElement = e.getRepresentedBy();
			if(eElement instanceof ClassElement) {
				for(AttributeElement ae : ((ClassElement) eElement).getAttributes()) {
					elements.add(ae.getRepresents());
				}
			}
			e.setMetaModel(mm);
			elements.add(e);
		}
		mm.setElements(elements);
		mm.setEBindings(this.eBindings);
		return mm;
	}
	
	
	public Element_ c(String name) {
		if(!eBindings.containsKey(name))
			throw new RuntimeException("No binding for: " + name);
		return eBindings.get(name);
	}
	
	
	public Element_ c(String name, List<Element_> as) {
		Element_ c = new Element_(name);
		ClassElement ecoreClass = new ClassElement(c);
		
		// no EObject
		// ecoreClass.setEcoreElementReference(null);
		c.setRepresentedBy(ecoreClass);
		
		for(Element_ _a : as) {
			AttributeElement ecoreAttribute = (AttributeElement)_a.getRepresentedBy();
			ecoreAttribute.setContainedIn(ecoreClass);
			ecoreClass.getAttributes().add(ecoreAttribute);
		}
		
		return c;
	}
	
	
	public Element_ e(String name) {
		if(!eBindings.containsKey(name))
			throw new IllegalArgumentException("No binding for: " + name);
		return eBindings.get(name);
	}
	
	
	public Element_ a(String name) {
		Element_ a = new Element_(name);
		AttributeElement ecoreAttribute = new AttributeElement(a);
		
		// no EObject
		// ecoreAttribute.setEcoreElementReference(null);
		a.setRepresentedBy(ecoreAttribute);
		
		return a;
	}
	
	
	public Element_ r(String name, Element_ c1, Element_ c2) {
		Element_ r = new Element_(name);
		
		ReferenceElement ecoreRef = new ReferenceElement(r);
		
		// no EObject
		// ecoreRef.setEcoreElementReference(null);
		r.setRepresentedBy(ecoreRef);
		
		ecoreRef.setContainedIn((ClassElement) c1.getRepresentedBy()); 
		((ClassElement) c1.getRepresentedBy()).getReferences().add(ecoreRef);
		ecoreRef.setPointsTo((ClassElement)c2.getRepresentedBy());
		((ClassElement)c2.getRepresentedBy()).getReferencedByReferences().add(ecoreRef);
		
		return r;
	}
	
	
	public List<Element_> li(Element_ ...es) {
		return Arrays.asList(es);
	}
	
}
