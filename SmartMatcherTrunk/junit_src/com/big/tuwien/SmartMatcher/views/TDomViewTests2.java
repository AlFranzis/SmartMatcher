package com.big.tuwien.SmartMatcher.views;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;
import com.big.tuwien.SmartMatcher.views.dom.Query;

public class TDomViewTests2 extends TAbstractDomViewTests {
	

	public TDomViewTests2() {
		setExample(ExampleStore.ER_2_WEBML);
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
	
	
	
	
		
		
		
}
