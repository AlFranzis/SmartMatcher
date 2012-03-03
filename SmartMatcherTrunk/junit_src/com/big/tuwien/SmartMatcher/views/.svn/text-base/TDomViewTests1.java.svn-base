package com.big.tuwien.SmartMatcher.views;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.dom.Query;

public class TDomViewTests1 extends TAbstractDomViewTests {
	

	public TDomViewTests1() {
		setExample(ExampleStore.PERSON_PERSONFAMILY);
	}
	
	
	
	@Test
	public void testDomViewQuery1() throws ParserConfigurationException, XPathExpressionException {
		System.out.println("DOMView:");
		System.out.println(view.getStringRepresentation());
		System.out.println("\n\n");
		
		Query q = new Query("//*[@id=1]");
		q.setDomView(view);
		NodeList result = q.execute();
		
		printNodeList(result);
	}
	
	
	
	@Test
	public void testLinkQuery1() throws ParserConfigurationException, XPathExpressionException {
		// class Family has id 6, class Person has id 4
		Query q = new Query("//*[ext:link(6,$personId,2)]");
		q.setDomView(view);
		q.setParameter("personId", 4);
		NodeList nl = q.execute();
		
		printNodeList(nl);
	}
	
	
	@Test
	public void testLinkQuery2() throws ParserConfigurationException, XPathExpressionException {
		// Queries all class nodes that have a reference to class Person
		Query q = new Query("//*[ext:link(., $personId, 2)]");
		q.setDomView(view);
		// class Person has id = 4
		q.setParameter("personId", 4);
		NodeList nl = q.execute();
		
		printNodeList(nl);
	}
	
	
	@Test
	public void testLinkQuery3() throws ParserConfigurationException, XPathExpressionException {
		Element person = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Person");
		
		// Queries all class nodes that have a reference to class Person
		Query q = new Query("//*[ext:link(.,$person,2)]");
		q.setDomView(view);
		q.setParameter("person", person);
		NodeList nl = q.execute();
		
		printNodeList(nl);
	}
	
	
	@Test
	public void testLinkQuery4() throws ParserConfigurationException, XPathExpressionException {
		Element person = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Person");
		Element family = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Family");
		
		Query q = new Query("//*[ext:link($family,$person,2)]");
		q.setDomView(view);
		q.setParameter("person", person);
		q.setParameter("family", family);
		NodeList nl = q.execute();
		
		printNodeList(nl);
	}
	
	
	@Test
	public void testDirectedLinkQuery1() throws ParserConfigurationException, XPathExpressionException {
		Element family = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Family");
		
		Query q = new Query("//*[ext:dlink(., $family, 2)]");
		q.setDomView(view);
		q.setParameter("family", family);
		NodeList nl = q.execute();
		
		Assert.assertTrue("Directed link from class person to class family", nl.getLength() == 1);
		
		printNodeList(nl);
	}
	
	@Test
	public void testDirectedLinkQuery2() throws ParserConfigurationException, XPathExpressionException {
		Element family = MetaModelFactoryUtil.getMetaModelFactory().getRHSMetaModel().getClassByName("Family");
		
		Query q = new Query("//*[ext:dlink($family, .,  2)]");
		q.setDomView(view);
		q.setParameter("family", family);
		NodeList nl = q.execute();
		
		Assert.assertTrue("No directed link from class family to any other class", nl.getLength() == 0);
	}
	
	
	
	
		
		
		
}
