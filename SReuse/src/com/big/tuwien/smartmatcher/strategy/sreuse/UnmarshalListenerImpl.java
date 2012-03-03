package com.big.tuwien.smartmatcher.strategy.sreuse;

import org.apache.log4j.Logger;
import org.castor.xml.UnmarshalListener;

public class UnmarshalListenerImpl implements UnmarshalListener {
	private static Logger logger = Logger.getLogger(UnmarshalListenerImpl.class);
	
	public UnmarshalListenerImpl() {}
	
	
	@Override
	public void attributesProcessed(Object target, Object parent) {
		//logger.debug("attributesProcessed() called ");
		
	}

	
	@Override
	public void fieldAdded(String fieldName, Object parent, Object child) {
		//logger.debug("fieldAdded() called ");
		
	}

	
	@Override
	public void initialized(Object target, Object parent) {
		//logger.debug("initialized() called ");
		
	}

	
	@Override
	public void unmarshalled(Object target, Object parent) {
		logger.debug("unmarshalled() called ");
		
		// store every unmarshalled XMLElement in the resolver 
		if(target instanceof XMLElement) {
			XMLElement e = (XMLElement) target;
			UnmarshalledElementResolver resolver = 
				UnmarshalledElementResolver.getInstance();
			resolver.put(e.getId(), e);
		}	
	}

}
