package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;

public class CachedFragmentDAO extends FragmentDAO {
	private static Logger logger = Logger.getLogger(CachedFragmentDAO.class);
	
	// fragments cache
	private Map<String,XMLFragment> cachedFragments = 
						new WeakHashMap<String, XMLFragment>();
	
	// cache for parent fragments
	private Map<String,Set<XMLFragment>> cachedParents = 
						new WeakHashMap<String, Set<XMLFragment>>();
	
	// cache for children fragments
	private Map<String,Set<XMLFragment>> cachedChildren = 
						new WeakHashMap<String, Set<XMLFragment>>();
	
	// cache for children fragments
	private Map<String,Set<XMLFragmentMapping>> cachedMappings = 
						new WeakHashMap<String, Set<XMLFragmentMapping>>();
	
	// cached root fragments
	private Set<XMLFragment> cachedRoots = 
						new HashSet<XMLFragment>();
	
	
	public CachedFragmentDAO() {
		super();
	}

	
	public CachedFragmentDAO(IdGenerator<XMLFragment> idGenerator) {
		super(idGenerator);
	}

	
	public CachedFragmentDAO(String fragmentContainer) {
		super(fragmentContainer);
	}
	
	
	@Override
	public XMLFragment find(String fId) throws ReuseException {
		// check cache first
		if(cachedFragments.containsKey(fId)) {
			logger.debug("Cache hit for fragment " + fId);
			return cachedFragments.get(fId);
		}
		
		XMLFragment f = super.find(fId);
		
		// store in cache
		if(f != null)
			cachedFragments.put(fId, f);
		
		return f;
	}
	
	
	@Override
	public Set<XMLFragment> findRoots(ResultHandler<XMLFragment> rh) 
												throws ReuseException {
		// query cache
		if(!cachedRoots.isEmpty()) {
			logger.debug("Cache hit for root fragments");
			return cachedRoots;
		}
		
		Set<XMLFragment> roots = super.findRoots(rh);
		
		// store in cache
		cachedRoots.addAll(roots);
		return roots;
	}
	
	
	@Override
	public Set<XMLFragmentMapping> findMappings(String fId) {
		// query cache
		if(cachedMappings.containsKey(fId)) {
			logger.debug("Cache hit for mappings of fragment " + fId);
			return cachedMappings.get(fId);
		}
		
		Set<XMLFragmentMapping> mappings = super.findMappings(fId); 
		
		// store in cache
		cachedMappings.put(fId, mappings);
		return mappings;
	}
	
	
	@Override
	public Set<XMLFragment> findChildren(String parentId) {
		// query cache
		if(cachedChildren.containsKey(parentId)) {
			logger.debug("Cache hit for children of fragment " + parentId);
			return cachedChildren.get(parentId);
		}
		
		Set<XMLFragment> children = super.findChildren(parentId);
		
		// store in cache
		cachedChildren.put(parentId, children);
		return children;
	}
	
	
	@Override
	public Set<XMLFragment> findParents(String fId) {
		// query cache
		if(cachedParents.containsKey(fId)) {
			logger.debug("Cache hit for parents of fragment " + fId);
			return cachedParents.get(fId);
		}
		
		Set<XMLFragment> parents = super.findParents(fId); 
		
		// store in cache
		cachedParents.put(fId, parents);
		return parents;
	}
}

