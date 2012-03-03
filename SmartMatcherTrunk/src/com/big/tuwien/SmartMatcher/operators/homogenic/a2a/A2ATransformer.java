package com.big.tuwien.SmartMatcher.operators.homogenic.a2a;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EEnumImpl;

import com.big.tuwien.ModelManager.imodel.AttributeInstance;
import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.homogenic.a2a.A2AConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.transformation.NewTransformationHistory;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationResult;
import com.big.tuwien.transformation.Transformer;

public class A2ATransformer implements Transformer {
	private static Logger logger = Logger.getLogger(A2ATransformer.class);
	
	private TransformationEngine engine;
	private NewTransformationHistory history;
	
	
	public A2ATransformer(TransformationEngine engine) {
		this.engine = engine;
		this.history = this.engine.getHistory();
	}
	
	
	public TransformationResult transform(URI inputModel, Configuration<?> _config) throws TransformationException {
		A2AConfiguration config = (A2AConfiguration) _config;
		TransformationResult tresult = new TransformationResult();
		tresult.setOperator(A2A.NAME);
		tresult.setConfiguration(config);
		// counts the number of generated instances on the rhs
		int generatedSize = 0;
		
		logger.info("Start A2A transformation for configuration: " + config);
		
		boolean somethingTransformed = false;
		
		EcoreElement lhsAttribute = config.getRoleElement(Roles.lhsFocusAttribute).getRepresentedBy();
		List<? extends InstanceElement<?,?>> _lhsInstances = lhsAttribute.getEObjects(inputModel);
		List<AttributeInstance> lhsInstances = (List<AttributeInstance>) _lhsInstances;
		tresult.setInputSize(lhsInstances.size());
		
		for(AttributeInstance lhsInstance : lhsInstances) {
			EObject lhsContainerClass = lhsInstance.getContainer();
			EAttribute lhsEAttribute = lhsInstance.getMetaModelElement();
			Object lhsValue = lhsInstance.getInstance();
		
			C2CConfiguration context = config.getContext();
			
			if(context == null) throw new TransformationException("Context C2C configuration is null");
			
			if( history.isTransformed(context) ) {
				List<ClassInstance> rhsContainerObjects = this.history.getEntry(lhsContainerClass).getRHSHistoryElements();
				assert rhsContainerObjects.size() == 1 : "Attribute has more than one container class";
				EObject rhsContainerObject = rhsContainerObjects.get(0).getInstance();
				
				boolean isAlreadyTransformed = this.history.isTransformed(lhsValue);
				
				if(! isAlreadyTransformed) {
					EcoreElement rhsAttribute = config.getRoleElement(Roles.rhsFocusAttribute).getRepresentedBy();
					EAttribute rhsEAttribute = (EAttribute) rhsAttribute.getEcoreElementReference();
					
					boolean set = convert(lhsEAttribute, rhsEAttribute, rhsContainerObject, lhsValue);
					if(set) {
						somethingTransformed = true;
						generatedSize++;
					} else {
						somethingTransformed = false;
					}
				}
			} else {
				throw new TransformationException("Context C2C configuration is not transformed yet: " + context);
			}
		}
		
		if(somethingTransformed) this.history.addTransformedConfiguration(config);
		tresult.setGeneratedSize(generatedSize);
		return tresult;
	}
	
	
	/*
	 * This method sets the rhsAttribute to the value given by the lhsAttribute.
	 * The lhsAttribute and the rhsAttribute can have different types.
	 * If the types of the lhsAttribute and rhsAttribute are compatible (assignable) the
	 * rhsValue is set by converting the lhsValue into the rhsType.
	 * If the types are not compatible then no setting of the rhsAttribute is done.
	 * Returns if the rhsValue was set.
	 */
	private boolean convert(EAttribute lhsAttribute, EAttribute rhsAttribute,EObject rhsContainerObject, Object lhsValue) {
		boolean set = false;
		
		if(isEString(lhsAttribute) && isEString(rhsAttribute)) {
			Object rhsValue = lhsValue;
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEInteger(lhsAttribute) && isEInteger(rhsAttribute)) {
			Object rhsValue = lhsValue;
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEEnum(lhsAttribute) && isEEnum(rhsAttribute)) {
			EEnumImpl en = (EEnumImpl) rhsAttribute.getEAttributeType();
			
			EEnumLiteral literal = en.getEEnumLiteralByLiteral(lhsValue.toString());
			if(literal != null) {
				// there exists a rhs literal value that is equal to the given lhs literal value
				rhsContainerObject.eSet(rhsAttribute, literal);
				Object rhsValue = lhsValue;
				this.history.traceValues(lhsValue, rhsValue);
				set = true;
			} else {
				// no rhs literal with given name known in enumeration
				set = false;
			}
		} else if(isEBoolean(lhsAttribute) && isEBoolean(rhsAttribute)) {
			Object rhsValue = lhsValue;
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEString(lhsAttribute) && isEEnum(rhsAttribute)) {
			EEnumImpl en = (EEnumImpl) rhsAttribute.getEAttributeType();
			
			EEnumLiteral literal = en.getEEnumLiteralByLiteral(lhsValue.toString());
			if(literal != null) {
				// there exists a rhs literal value that is equal to the given lhs attribute value
				rhsContainerObject.eSet(rhsAttribute, literal);
				Object rhsValue = lhsValue;
				this.history.traceValues(lhsValue, rhsValue);
				set = true;
			} else {
				// no rhs literal with given name known in enumeration
				set = false;
			}
		} else if(isEEnum(lhsAttribute) && isEString(rhsAttribute)) {
			String rhsValue = lhsValue.toString();
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEString(lhsAttribute) && isEBoolean(rhsAttribute)) {
			String s = lhsValue.toString();
			if(s.equalsIgnoreCase("true")) {
				rhsContainerObject.eSet(rhsAttribute, true);
				this.history.traceValues(lhsValue, true);
				set = true;
			} else if(s.equalsIgnoreCase("false")) {
				rhsContainerObject.eSet(rhsAttribute, false);
				this.history.traceValues(lhsValue, false);
				set = true;
			} else {
				set = false;
			}
		} else if(isEBoolean(lhsAttribute) && isEString(rhsAttribute)) {
			boolean b = (Boolean) lhsValue;
			String rhsValue = Boolean.toString(b);
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEInteger(lhsAttribute) && isEString(rhsAttribute)) {
			Object rhsValue = lhsValue;
			rhsContainerObject.eSet(rhsAttribute, rhsValue);
			this.history.traceValues(lhsValue, rhsValue);
			set = true;
		} else if(isEString(lhsAttribute) && isEInteger(rhsAttribute)) {
			String s = (String) lhsValue;
			try {
				int rhsValue = Integer.parseInt(s);
				rhsContainerObject.eSet(rhsAttribute, rhsValue);
				this.history.traceValues(lhsValue, rhsValue);
				set = true;
			} catch(NumberFormatException ex) {
				set = false;
			}
		} else if(isEEnum(lhsAttribute) && isEBoolean(rhsAttribute)) {
			// TODO: implement conversion
			set = false;
		} else if(isEBoolean(lhsAttribute) && isEEnum(rhsAttribute)) {
			// TODO: implement conversion
			set = false;
		} else if(isEBoolean(lhsAttribute) && isEInteger(rhsAttribute)) {
			// boolean is not convertible to integer
			set = false;
		} else if(isEInteger(lhsAttribute) && isEBoolean(rhsAttribute)) {
			// integer is not convertible to boolean
			set = false;
		} else if(isEInteger(lhsAttribute) && isEEnum(rhsAttribute)) {
			// integer not convertible to enumeration
			set = false;
		} else if(isEEnum(lhsAttribute) && isEInteger(rhsAttribute)) {
			// enumeration not convertible to integer
			set = false;
		} else {
			set = false;
			EDataType lhsType = lhsAttribute.getEAttributeType();
			EDataType rhsType = rhsAttribute.getEAttributeType();
			logger.warn("No type conversion implemented for Type-Pair: " + lhsType + " / " + rhsType);
		}
		
		return set;
	}
	
	
	private boolean isEEnum(EAttribute a) {
		return a.getEAttributeType() instanceof EEnumImpl;
	}
	
	
	private boolean isEString(EAttribute a) {
		return a.getEAttributeType().getName().equals("EString");
	}
	
	
	private boolean isEInteger(EAttribute a) {
		return a.getEAttributeType().getName().equals("EInteger");
	}
	
	
	private boolean isEBoolean(EAttribute a) {
		return a.getEAttributeType().getName().equals("EBoolean");
	}

}
