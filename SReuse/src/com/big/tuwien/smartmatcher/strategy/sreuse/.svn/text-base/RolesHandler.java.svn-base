package com.big.tuwien.smartmatcher.strategy.sreuse;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.AbstractFieldHandler;
import org.exolab.castor.mapping.FieldDescriptor;

public class RolesHandler extends AbstractFieldHandler {
	private static Logger logger = Logger.getLogger(RolesHandler.class);
	
	
	public RolesHandler() {}
	

	@Override
	/**
	 * Called during marshalling of XMLOperator to XML.
	 */
	public Object getValue(Object xmlOperator) throws IllegalStateException {
		FieldDescriptor fd = getFieldDescriptor();
		logger.info("getValue() called");
		return "roles";
	}

	
	@Override
	public Object newInstance(Object parent) throws IllegalStateException {
		logger.info("newInstance() called");
		return null;
	}

	
	@Override
	public Object newInstance(Object parent, Object[] constructorArgs)
			throws IllegalStateException {
		logger.info("newInstance2() called");
		return null;
	}

	
	@Override
	public void resetValue(Object obj) throws IllegalStateException,
			IllegalArgumentException {
		logger.info("resetValue() called");
		
	}

	
	@Override
	/**
	 * Called during unmarshalling from XML to an XMLOperator instance.
	 * Value contains the XML string value.
	 */
	public void setValue(Object xmlOperator, Object value)
			throws IllegalStateException, IllegalArgumentException {
		logger.info("setValue() called");
		
	}

}
