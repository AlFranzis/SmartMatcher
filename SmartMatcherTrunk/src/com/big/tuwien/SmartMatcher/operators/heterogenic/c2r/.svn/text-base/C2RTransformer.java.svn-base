package com.big.tuwien.SmartMatcher.operators.heterogenic.c2r;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;

import com.big.tuwien.ModelManager.imodel.ClassInstance;
import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.ModelManager.imodel.ReferenceInstance;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.Configuration;
import com.big.tuwien.SmartMatcher.operators.heterogenic.c2r.C2RConfiguration.Roles;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.transformation.NewTransformationHistory;
import com.big.tuwien.transformation.TransformationEngine;
import com.big.tuwien.transformation.TransformationException;
import com.big.tuwien.transformation.TransformationHelpers;
import com.big.tuwien.transformation.TransformationResult;
import com.big.tuwien.transformation.Transformer;

public class C2RTransformer implements Transformer {
	private static Logger logger = Logger.getLogger(C2RTransformer.class);
	
	private TransformationEngine engine;
	private NewTransformationHistory history;
	
	
	public C2RTransformer(TransformationEngine engine) {
		this.engine = engine;
		this.history = this.engine.getHistory();
	}
	
	
	public TransformationResult transform(URI inputModel, Configuration<?> _config) throws TransformationException {
		C2RConfiguration config = (C2RConfiguration) _config;
		
		TransformationResult tresult = new TransformationResult();
		tresult.setOperator(C2R.NAME);
		tresult.setConfiguration(config);
		// counts the number of generated instances on the rhs
		int generatedSize = 0;
		
		logger.info("Start C2R transformation for configuration: " + config);
		
		boolean somethingTransformed = false;
		
		Element lhsFocusClass = config.getRoleElement(Roles.lhsFocusClass);
		Element lhsContextRef1 = config.getRoleElement(Roles.lhsContextReference1);
		Element lhsContextRef2 =  config.getRoleElement(Roles.lhsContextReference2);
		Element rhsFocusRef = config.getRoleElement(Roles.rhsFocusReference);
		
		List<? extends InstanceElement<?,?>> _lhsFocusClassInstances = lhsFocusClass.getRepresentedBy().getEObjects(inputModel);
		List<ClassInstance> lhsFocusClassInstances = (List<ClassInstance>) _lhsFocusClassInstances;
		
		C2CConfiguration context1 = config.getContext1();
		C2CConfiguration context2 = config.getContext2();
		
		if(this.history.isTransformed(context1) && this.history.isTransformed(context2)) {
			for(ClassInstance lhsFocusClassInstance : lhsFocusClassInstances) {
				
				Set<ReferenceInstance> lhsCxtRef1Instances = getReferenceInstances(lhsFocusClassInstance, lhsContextRef1);
				assert lhsCxtRef1Instances.size() == 1;
				ReferenceInstance lhsCxtRef1Instance = lhsCxtRef1Instances.iterator().next();
				
				Set<ReferenceInstance> lhsCxtRef2Instances = getReferenceInstances(lhsFocusClassInstance, lhsContextRef2);
				assert lhsCxtRef2Instances.size() == 1;
				ReferenceInstance lhsCxtRef2Instance = lhsCxtRef2Instances.iterator().next();
				
				ClassInstance lhsCxtClass1Instance = getOppEndpointInstance(lhsCxtRef1Instance, lhsFocusClass);
				ClassInstance lhsCxtClass2Instance = getOppEndpointInstance(lhsCxtRef2Instance, lhsFocusClass);
				
				List<ClassInstance> rhsCxtClass1Instances = this.history.getEntry(lhsCxtClass1Instance.getInstance()).getRHSHistoryElements();
				assert rhsCxtClass1Instances.size() == 1;
				ClassInstance rhsCxtClass1Instance = rhsCxtClass1Instances.get(0);
				
				List<ClassInstance> rhsCxtClass2Instances = this.history.getEntry(lhsCxtClass2Instance.getInstance()).getRHSHistoryElements();
				assert rhsCxtClass2Instances.size() == 1;
				ClassInstance rhsCxtClass2Instance = rhsCxtClass2Instances.get(0);
				
				ReferenceInstance rhsInstance = null;
				try {
					rhsInstance = TransformationHelpers.createReference(engine, rhsFocusRef, rhsCxtClass1Instance, rhsCxtClass2Instance);
				// thrown if generated reference must be reversed
				// TODO: better implementation for inverse r2r variation 
				} catch(IllegalArgumentException e) {
					rhsInstance = TransformationHelpers.createReference(engine, rhsFocusRef, rhsCxtClass2Instance, rhsCxtClass1Instance);
				}
				
				somethingTransformed = true;
				generatedSize++;
			}
		} else {
			throw new TransformationException("Context C2C mapping(s) are not transformed yet");
		}
			
		logger.info("Transformed C2R");
		
		if(somethingTransformed) this.history.addTransformedConfiguration(config);
		tresult.setGeneratedSize(generatedSize);
		return tresult;
	}
	
	
	/*
	 * Returns all reference instances of the given type that are contained
	 * or point to the given class.
	 */
	private Set<ReferenceInstance> getReferenceInstances(ClassInstance ci, Element ref) {
		Set<ReferenceInstance> refs = ci.getReferences();
		refs.addAll(ci.getCrossReferences());
		
		Set<ReferenceInstance> ris = new HashSet<ReferenceInstance>();
		for(ReferenceInstance ri : refs) {
			if(ri.getElement().equals(ref)) ris.add(ri);
		}
		
		return ris;
	}
	
	
	/*
	 * Returns the opposite clazz endpoint instance of the given reference instance.
	 */
	private ClassInstance getOppEndpointInstance(ReferenceInstance ri, Element clazz) {
		if(ri.getContainerInstance().getElement().equals(clazz)) return ri.getPointedToInstance();
		if(ri.getPointedToInstance().getElement().equals(clazz)) return ri.getContainerInstance();
		return null;
	}
	
}