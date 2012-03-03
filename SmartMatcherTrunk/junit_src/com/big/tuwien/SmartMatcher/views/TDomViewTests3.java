package com.big.tuwien.SmartMatcher.views;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CBubble;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.SmartMatcher.views.dom.Query;

public class TDomViewTests3 extends TAbstractDomViewTests {
	

	public TDomViewTests3() {
		setExample(ExampleStore.ER_2_WEBML);
		
		
	}
	
	@Before
	public void setUp2() {
		Element lhsEntity = metaModelFactory.getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = metaModelFactory.getRHSMetaModel().getClassByName("Entity");
		
		C2CBubble b = new C2CBubble();
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setRole(C2CConfiguration.Roles.lhsFocusClass, lhsEntity);
		c2cConfig.setRole(C2CConfiguration.Roles.rhsFocusClass, rhsEntity);
		b.setConfiguration(c2cConfig);
		b.setState(Bubble.STATE.applied);
		this.view.mappingEvent2(MappingEvent.APPLIED_EVENT, b);
	}
	
	
	@Test
	public void testLinkQuery1() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element dv = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("DomainValue");
		Element d = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Domain");
		
		Query q = new Query("//*[ext:link($dv, $d , 1)]");
		q.setDomView(view);
		q.setParameter("dv", dv);
		q.setParameter("d", d);
		
		NodeList result = q.execute();
		
		printNodeList(result);
	}
	
	
	@Test
	public void testDirectedLinkQuery1()throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element dv = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("DomainValue");
		Element d = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Domain");
		
		Query q = new Query("//*[ext:dlink($dv, $d , 1)]");
		q.setDomView(view);
		q.setParameter("dv", dv);
		q.setParameter("d", d);
		
		NodeList result = q.execute();
		
		
		Assert.assertTrue("There is no directed link from class DomainValue to class Domain", result.getLength() == 0);
	}
	
	
	@Test
	public void testDirectedLinkQuery2() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element dv = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("DomainValue");
		Element e = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		Query q = new Query("//*[ext:dlink($e, $dv , 2)]");
		q.setDomView(view);
		q.setParameter("e", e);
		q.setParameter("dv", dv);
		
		NodeList result = q.execute();
		
		
		Assert.assertTrue("There is no directed link with length 2 from class Entity to class DomainValue", result.getLength() == 0);
	}
	
	
	@Test
	public void testDirectedLinkQuery3() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element dv = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("DomainValue");
		Element e = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		Query q = new Query("//*[ext:dlink($e, $dv , 3)]");
		q.setDomView(view);
		q.setParameter("e", e);
		q.setParameter("dv", dv);
		
		NodeList result = q.execute();
		
		
		Assert.assertTrue("There is a directed link with length 3 from class Entity to class DomainValue", result.getLength() != 0);
	}
	
	
	@Test
	public void testMappedFunctionQuery1() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"C2C\",$lhsEntity, \"lhsFocusElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() > 0);
	}
	
	@Test
	public void testMappedFunctionQuery2() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"A2A\",$lhsEntity, \"lhsFocusElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() == 0);
	}
	
	@Test
	public void testMappedFunctionQuery3() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"C2C\",$lhsEntity, \"rhsFocusElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() == 0);
	}
		
	@Test
	public void testMappedFunctionQuery4() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"C2C\",$lhsEntity, \"lhsFocusElement\", $rhsEntity, \"rhsFocusElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() > 0);
	}	
	
	@Test
	public void testMappedFunctionQuery5() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"C2C\",$lhsEntity, \"lhsFocusElement\", $rhsEntity, \"lhsFocusElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() == 0);
	}	
	
	
	@Test
	public void testMappedFunctionQuery6() throws XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Element lhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getLHSMetaModel().getClassByName("Entity");
		Element rhsEntity = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Entity");
		
		
		Query q = new Query("//*[ext:mapped(\"C2C\",$lhsEntity, \"lhsFocusElement\", $rhsEntity, \"lhsFocusElement\", $lhsEntity, \"lhsContextElement\")]");
		q.setDomView(view);
		q.setParameter("lhsEntity", lhsEntity);
		q.setParameter("rhsEntity", rhsEntity);
		
		NodeList result = q.execute();
		Assert.assertTrue(result.getLength() == 0);
	}	
		
}
