package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashMap;
import java.util.Map;

import org.exolab.castor.xml.IDResolver;

public class UnmarshalledElementResolver implements IDResolver {
	private static UnmarshalledElementResolver instance;
	
	private Map<String,XMLElement> elements = 
						new HashMap<String, XMLElement>();
	
	
	public static UnmarshalledElementResolver getInstance() {
		return instance;
	}
	
	
	public static void create() {
		instance = new UnmarshalledElementResolver();
	}
	
	
	public void put(String id, XMLElement e) {
		if(elements.containsKey(id))
			throw new ReuseRuntimeException("An element with id " + id + " has been already unmarshalled");
		
		elements.put(id, e);
		
		System.out.println("Putted " + e.getId());
	}
	
	
//	public XMLElement get(String id) {
//		return elements.get(id);
//	}


	@Override
	public Object resolve(String id) {
		XMLElement resolved = elements.get(id);
		return resolved;
	}
}
