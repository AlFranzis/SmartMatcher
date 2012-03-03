package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.SmartMatcher.strategy.pso.Tuples.t;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class XMLElementResolverFactory {
	private StdXMLElementResolver resolver = new StdXMLElementResolver();
	private Map<T2<?,?>,XMLResolver<?,?>> resolvers = 
								new HashMap<T2<?,?>, XMLResolver<?,?>>();
	
	private Map<Class<?>,IdGenerator<?>> idGenerators = 
								new HashMap<Class<?>, IdGenerator<?>>();
	
	
	private IdGenerator<XMLElement> idGenerator = 
									new IdGenerator<XMLElement>() {
		private Long classId = 0l;
		private Long attributeId = 0l;
		private Long referenceId = 0l;
		
		@Override
		public String generateId(XMLElement xmlAny) {
			if(xmlAny instanceof XMLClassElement) {
				return "C" + (++classId);
			} else if(xmlAny instanceof XMLAttributeElement) {
				return "A" + (++attributeId);
			} else if(xmlAny instanceof XMLReferenceElement) {
				return "R" + (++referenceId);
			} else {
				throw new ReuseRuntimeException(
							"Unknown element type: " + xmlAny);
			}
		}
	};
	
	
	public XMLElementResolverFactory() {
		// set resolvers
		XMLResolver<?,?> opResolver = new StdXMLOperatorResolver2();
		resolvers.put(
				t(Operator.class,XMLOperator.class), 
				opResolver
				);
		XMLResolver<?,?> fragResolver = new StdXMLFragmentResolver();
		resolvers.put(
				t(Fragment.class,XMLFragment.class), 
				fragResolver
				);
		
		XMLResolver<?,?> fmResolver = new StdXMLFragmentMappingResolver();
		resolvers.put(
				t(FragmentMapping.class,XMLFragmentMapping.class), 
				fmResolver
				);
		
		// set id generators
		IdGenerator<?> opIdGenerator = new StdXMLOperatorIdGenerator();
		idGenerators.put(XMLOperator.class, opIdGenerator);
		IdGenerator<?> fragIdGenerator = new StdXMLFragmentIdGenerator();
		idGenerators.put(XMLFragment.class, fragIdGenerator);
		IdGenerator<?> fmIdGenerator = new StdXMLFragmentMappingIdGenerator();
		idGenerators.put(XMLFragmentMapping.class, fmIdGenerator);
	}


	private static class StdXMLElementResolver implements XMLElementResolver {
		private BiMap<IdentityWrapper<Element>,XMLElement> bindings = 
					new HashBiMap<IdentityWrapper<Element>, XMLElement>();

		@Override
		public void bind(Element e, XMLElement xmlE) {
			IdentityWrapper<Element> idE = new IdentityWrapper<Element>(e);
			if(bindings.containsKey(idE))
				throw new ReuseRuntimeException(
							"Element " + e + " already bound");
			
			bindings.put(idE, xmlE);
		}
		
		
		public void bind(EcoreElement ee, XMLElement xmlE) {
			bind(ee.getRepresents(), xmlE);
		}

		
		@Override
		public XMLElement resolve(Element e) {
			IdentityWrapper<Element> idE = new IdentityWrapper<Element>(e);
			return bindings.get(idE);
		}
		
		
		@Override
		public Element revResolve(XMLElement e) {
			return bindings.inverse().get(e).getWrapped();
		}
		
		
		public XMLElement resolve(EcoreElement ee) {
			return resolve(ee.getRepresents());
		}

		
		public <T extends XMLElement> T resolve(Element e, Class<T> etype) {
			return (T) resolve(e);
		}
		
		
		public <T extends XMLElement> T resolve(EcoreElement ee, 
														Class<T> etype) {
			return resolve(ee.getRepresents(), etype);
		}
		

		@Override
		public boolean isBound(Element e) {
			IdentityWrapper<Element> idE = new IdentityWrapper<Element>(e);
			boolean bound = bindings.containsKey(idE);
			return bound;
		}
		
		
		public boolean isBound(EcoreElement ee) {
			return isBound(ee.getRepresents());
		}
	}
	
	
	public Set<XMLClassElement> resolve2(Collection<Element> classes) {
		return resolve(Helpers.cast2(new ArrayList<Element>(classes), 
						ClassElement.class));
	}
	
	
	public Set<XMLClassElement> resolve(Collection<ClassElement> classes) {
		Set<XMLClassElement> xmlClasses = new HashSet<XMLClassElement>();
		for(ClassElement c : classes) {
			// if class not bound 
			if(!resolver.isBound(c)) {
				XMLClassElement xmlClass = createBoundClass(c);
				xmlClasses.add(xmlClass);
			// class already bound	
			} else {
				xmlClasses.add(resolver.resolve(c, XMLClassElement.class));
			}
		}
		
		return xmlClasses;
	}
	
	
	private XMLClassElement createBoundClass(ClassElement c) {
		XMLClassElement wrapped = new XMLClassElement();
		wrapped.setId(idGenerator.generateId(wrapped));
		wrapped.setName(c.getRepresents().getName());
		
		Set<XMLAttributeElement> attributes = 
							new HashSet<XMLAttributeElement>();
		for(AttributeElement a : c.getAttributes()) {
			XMLAttributeElement xmlAtt = createAttribute(a);
			resolver.bind(a, xmlAtt);
			attributes.add(xmlAtt);
		}
		wrapped.setAttributes(attributes);
		
		resolver.bind(c, wrapped);
		
		Set<XMLReferenceElement> references = 
							new HashSet<XMLReferenceElement>();
		for(ReferenceElement r : c.getReferences()) {
			
			ClassElement pointsTo = r.getPointsTo();
			
			if(!resolver.isBound(pointsTo))
				createBoundClass(pointsTo);	
			
			XMLReferenceElement xmlRef = createReference(r);
			resolver.bind(r, xmlRef);
			references.add(xmlRef);

		}
		wrapped.setReferences(references);
		
		return wrapped;
	}
	
	
	private XMLAttributeElement createAttribute(AttributeElement a) {
		XMLAttributeElement wrapped = new XMLAttributeElement();
		wrapped.setId(idGenerator.generateId(wrapped));
		wrapped.setName(a.getRepresents().getName());
		return wrapped;
	}
	
	
	private XMLReferenceElement createReference(ReferenceElement r) {
		XMLReferenceElement wrapped = new XMLReferenceElement();
		wrapped.setId(idGenerator.generateId(wrapped));
		wrapped.setName(r.getRepresents().getName());
		
		ClassElement container = r.getContainedIn();
		if(!resolver.isBound(container))
			throw new ReuseRuntimeException();
		
		XMLClassElement xmlContainer = resolver.resolve(container, 
											XMLClassElement.class);
		wrapped.setContainedIn(xmlContainer);
		
		ClassElement pointsTo = r.getPointsTo();
		if(!resolver.isBound(pointsTo))
			throw new ReuseRuntimeException();
		
		XMLClassElement xmlPointsTo = resolver.resolve(pointsTo, 
											XMLClassElement.class);
		wrapped.setPointsTo(xmlPointsTo);
		
		return wrapped;
	}
	
	
	public XMLElementResolver getResolver() {
		return resolver;
	}
	
	
	public <T, S extends XMLAny> XMLResolver<T,S> getResolver(Class<T> c1, 
															Class<S> c2) {
		XMLResolver<T,S> resolver = 
			(XMLResolver<T,S>)resolvers.get(t(c1,c2));
		return resolver;
	}
	
	
	public <T extends XMLAny> IdGenerator<T> getIdGenerator(Class<T> ctype) {
		IdGenerator<T> idGenerator =
			(IdGenerator<T>) idGenerators.get(ctype);
		return idGenerator;
	}
	
	
	public static class IdentityWrapper<T> {
		private T obj;
		
		public IdentityWrapper(T obj) {
			this.obj = obj;
		}
		
		public T getWrapped() {
			return obj;
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			
			if(!(other instanceof IdentityWrapper<?>))
				return false;
			
			IdentityWrapper<?> that = (IdentityWrapper<?>) other;
			return obj == that.obj;
		}
		
		@Override
		public int hashCode() {
			return System.identityHashCode(obj);
		}
		
		@Override
		public String toString() {
			return obj.toString();
		}
	}
	
	
	private static class StdXMLOperatorResolver2 
				implements XMLResolver<Operator,XMLOperator> {
		private Map<IdentityWrapper<Operator>,XMLOperator> bindings = 
			new HashMap<IdentityWrapper<Operator>, XMLOperator>();


		@Override
		public void bind(Operator op, XMLOperator xmlOp) {
			IdentityWrapper<Operator> idOp = new IdentityWrapper<Operator>(op);
			
			if(bindings.containsKey(idOp))
				throw new ReuseRuntimeException(
						"Operator " + op + " already bound");

			bindings.put(idOp, xmlOp);
		}


		@Override
		public XMLOperator resolve(Operator op) {
			IdentityWrapper<Operator> idOp = new IdentityWrapper<Operator>(op);
			return bindings.get(idOp);
		}


		@Override
		public boolean isBound(Operator op) {
			IdentityWrapper<Operator> idOp = new IdentityWrapper<Operator>(op);
			boolean bound = bindings.containsKey(idOp);
			return bound;
		}
	}
	
	
	private static class StdXMLOperatorResolver 
				implements XMLResolver<Operator,XMLOperator> {
		private Map<Operator,XMLOperator> bindings = 
						new HashMap<Operator, XMLOperator>();
		

		@Override
		public void bind(Operator op, XMLOperator xmlOp) {
			if(bindings.containsKey(op))
				throw new ReuseRuntimeException(
						"Operator " + op + " already bound");
			
			bindings.put(op, xmlOp);
		}

		
		@Override
		public XMLOperator resolve(Operator op) {
			return bindings.get(op);
		}
		

		@Override
		public boolean isBound(Operator op) {
			return bindings.containsKey(op);
		}
	}
	
	
	private static class StdXMLFragmentResolver 
				implements XMLResolver<Fragment,XMLFragment> {
		private Map<Fragment,XMLFragment> bindings = 
			new HashMap<Fragment, XMLFragment>();

		@Override
		public void bind(Fragment f, XMLFragment xmlFrag) {
			if(bindings.containsKey(f))
				throw new ReuseRuntimeException(
						"Fragment " + f + " already bound");

			bindings.put(f, xmlFrag);
		}


		@Override
		public XMLFragment resolve(Fragment f) {
			return bindings.get(f);
		}


		@Override
		public boolean isBound(Fragment f) {
			return bindings.containsKey(f);
		}
	}
	
	
	private static class StdXMLFragmentMappingResolver 
				implements XMLResolver<FragmentMapping,XMLFragmentMapping> {
		private Map<FragmentMapping,XMLFragmentMapping> bindings = 
			new HashMap<FragmentMapping, XMLFragmentMapping>();

		@Override
		public void bind(FragmentMapping fm, XMLFragmentMapping xmlFm) {
			if(bindings.containsKey(fm))
				throw new ReuseRuntimeException(
						"FragmentMapping " + fm + " already bound");

			bindings.put(fm, xmlFm);
		}


		@Override
		public XMLFragmentMapping resolve(FragmentMapping fm) {
			return bindings.get(fm);
		}


		@Override
		public boolean isBound(FragmentMapping fm) {
			return bindings.containsKey(fm);
		}
	}
	
	
	private static class StdXMLOperatorIdGenerator 
					implements IdGenerator<XMLOperator> {
		private Long opId = 0l;
		
		@Override
		public String generateId(XMLOperator xmlOp) {
			return "OP_" + xmlOp.getName() + "_" + (++opId);
		}
	}
	
	
	private static class StdXMLFragmentIdGenerator 
					implements IdGenerator<XMLFragment> {
		private Long fragId = 0l;

		@Override
		public String generateId(XMLFragment xmlFrag) {
			return "F_" + (++fragId);
		}
	}
	
	
	private static class StdXMLFragmentMappingIdGenerator 
					implements IdGenerator<XMLFragmentMapping> {
		private Long fmId = 0l;

		@Override
		public String generateId(XMLFragmentMapping xmlFM) {
			return "FM_" + (++fmId);
		}
	}

}
