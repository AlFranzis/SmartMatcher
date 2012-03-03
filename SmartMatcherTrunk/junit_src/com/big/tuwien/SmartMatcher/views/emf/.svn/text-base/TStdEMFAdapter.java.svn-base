package com.big.tuwien.SmartMatcher.views.emf;

import static org.junit.Assert.assertTrue;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.emf.common.notify.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;

import sm_mm.C2C;
import sm_mm.Class;
import sm_mm.MappingModel;
import sm_mm.Sm_mmFactory;

import com.big.tuwien.SmartMatcher.views.emf.EventFilteredEMFModel.EMFModelListener;

@RunWith(JMockit.class)
public class TStdEMFAdapter {
	@Mocked 
	protected EMFModelListener listenerMock;
	
	
	public TStdEMFAdapter() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Test
	public void testExternalEMFModelEventPropagation() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		
		// build test EMF model
		// ******************************************
		
		MappingModel mm = factory.createMappingModel();
		
		Class c1 = factory.createClass();
		c1.setName("C1");
		mm.getClasses().add(c1);
		
		Class c2 = factory.createClass();
		c2.setName("C2");
		c2.setLhs(false);
		mm.getClasses().add(c2);
		
		// setup adapter
		//********************************************
		
		
		EMFModel emfModel = new EventFilteredEMFModel(mm);
		emfModel.addListener(listenerMock);
		
		new Expectations() {
			{
				// the events generated through adding or removing an operator EXTERNALLY
				// are not filtered
				listenerMock.notifyChanged((Notification) withNotNull());repeats(4);
			}
		};
		
		// *******************************************
		// externally change EMF model and check correct event notification / filtering
		
		C2C c2c1 = factory.createC2C();
		c2c1.setLhs(c1);
		c2c1.setRhs(c2);
		// add operator
		mm.getOperators().add(c2c1);
		
		// modify operator
		c2c1.setLhs(null);
		
		// remove operator
		mm.getOperators().remove(c2c1);
	}
	
	
	@Test
	public void testInternalModelEventPropagation() {
		Sm_mmFactory factory = Sm_mmFactory.eINSTANCE;
		
		// build test EMF model
		// ******************************************
		
		MappingModel mm = factory.createMappingModel();
		
		Class c1 = factory.createClass();
		c1.setName("C1");
		mm.getClasses().add(c1);
		
		Class c2 = factory.createClass();
		c2.setName("C2");
		c2.setLhs(false);
		mm.getClasses().add(c2);
		
		// setup adapter
		//********************************************
		
		EMFModel emfModel = new EventFilteredEMFModel(mm);
		emfModel.addListener(listenerMock);
		
		// record JMockit expectations
		// ********************************************
		
		new Expectations() {
			{
				// the events generated through adding or removing an operator INTERNALLY
				// are filtered !
				listenerMock.notifyChanged((Notification) withNotNull());repeats(2);
			}
		};
		
		
		// *******************************************
		// internally change EMF model and check correct event notification / filtering
		
		C2C c2c1 = factory.createC2C();
		c2c1.setLhs(c1);
		c2c1.setRhs(c2);
		
		assertTrue(mm.getOperators().size() == 0);
		
		// add operator -> event FILTERED
		emfModel.addOperator(c2c1);
		
		assertTrue(mm.getOperators().size() == 1);
		
		// modify operator
		c2c1.setLhs(null);
		
		// remove operator -> event FILTERED
		emfModel.removeOperator(c2c1);
		
		assertTrue(mm.getOperators().size() == 0);
	}
	
	
}
