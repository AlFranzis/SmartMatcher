package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.castor.xml.UnmarshalListener;
import org.exolab.castor.xml.IDResolver;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLIdentifiable;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class UniqueFragmentDAO extends CachedFragmentDAO {
	private static Logger logger = Logger.getLogger(UniqueFragmentDAO.class);
	
	private UniqueCache uc;
	
	
	public UniqueFragmentDAO() {
		super();
		init();
	}

	
	public UniqueFragmentDAO(IdGenerator<XMLFragment> idGenerator) {
		super(idGenerator);
		init();
	}

	
	public UniqueFragmentDAO(String fragmentContainer) {
		super(fragmentContainer);
		init();
	}
	
	
	private void init() {
		UniqueMarshaler um  = new UniqueMarshaler();
		marshaller = um;
		uc = um.getUniqueCache();
	}
	
	
	@Override
	public void setMarshaller(XMLMarshaller m) {
		if( !(m instanceof UniqueMarshaler))
			throw new ReuseRuntimeException("Not a UniqueMarshaler instance");
		
		UniqueMarshaler um = (UniqueMarshaler) m;
		uc = um.getUniqueCache();
		
		super.setMarshaller(m);
	}
	
	
	@Override
	public Set<XMLFragment> findChildren(String parentId) {
		
		Set<XMLFragment> _children = super.findChildren(parentId);
		
		Set<XMLFragment> children = replace(_children);
		return children;
	}
	
	
	@Override
	public Set<XMLFragmentMapping> findMappings(String fId) {
		
		Set<XMLFragmentMapping> _mappings = super.findMappings(fId);
		
		Set<XMLFragmentMapping> mappings = replace(_mappings);
		return mappings;
	}
	
	
	@Override
	public Set<XMLFragment> findParents(String fId) {
		Set<XMLFragment> _parents = super.findParents(fId);
		
		Set<XMLFragment> parents = replace(_parents);
		return parents;
		
	}
	
	
	private <T extends XMLIdentifiable> Set<T> replace(Set<T> ts) {
		Set<T> replaced = new HashSet<T>();
		for(T t : ts) {
			String fId = t.getId();
			// if duplicate -> replace duplicate by original
			if(uc.contains(fId)) {
				T original = (T) uc.resolve(fId);
				replaced.add(original);
			// not a duplicate
			} else {
				replaced.add(t);
			}
		}
		return replaced;
	}
	
	
	public static class UniqueMarshaler extends XMLMarshallerImpl {
		private UniqueCache uc;
		
		public UniqueMarshaler() {
			super();
			uc = new UniqueCache();
			unmarshaller.setUnmarshalListener(uc);
			unmarshaller.setIDResolver(uc);
		}
		
		
		public UniqueCache getUniqueCache() {
			return uc;
		}
	}
	
	
	public static class UniqueCache implements IDResolver, UnmarshalListener {
		private static Logger logger = Logger.getLogger(UniqueCache.class);
		
		private Map<String,XMLIdentifiable> xmlIdentifiables = 
			new HashMap<String, XMLIdentifiable>();
		
		
		@Override
		/**
		 * Called by Castor XMLMarshaller to resolve ids.
		 */
		public Object resolve(String id) {
			XMLIdentifiable idf = xmlIdentifiables.get(id);
			return idf;
		}
		
		
		public boolean contains(String id) {
			return xmlIdentifiables.containsKey(id);
		}

		
		@Override
		public void attributesProcessed(Object arg0, Object arg1) {}

		@Override
		public void fieldAdded(String arg0, Object arg1, Object arg2) {}

		@Override
		public void initialized(Object arg0, Object arg1) {}

		
		@Override
		/**
		 * Called by Castor marshaler if object has been unmarshaled.
		 */
		public void unmarshalled(Object target, Object parent) {
			if(target instanceof XMLIdentifiable) {
				XMLIdentifiable idf = (XMLIdentifiable) target;
				String id = idf.getId();
				
				if(xmlIdentifiables.containsKey(id)) {
					logger.warn("Id " + id + " of XMLIdentifiable " + idf 
										+ " is already bound." 
										+ " Will not rebind it!");
				} else {
					xmlIdentifiables.put(id, idf);
				}
			}
		}
		
	}

}
