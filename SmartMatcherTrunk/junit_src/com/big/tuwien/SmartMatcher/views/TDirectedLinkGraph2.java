package com.big.tuwien.SmartMatcher.views;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.persistance.ModelDeserializer;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.views.dom.NewDirectedLinkGraph;


public class TDirectedLinkGraph2 {
	private MetaModel mm;
	private NewDirectedLinkGraph directedGraph;
	
	
	@Before
	public void setUp() {
		Map<String,String> example = ExampleStore.getExample(ExampleStore.ER_2_WEBML);
		
		ModelDeserializer deserializer = new ModelDeserializer();
		Resource lhsMMResource = deserializer.getMetamodelResource(example.get("inputMetamodelURI"));
		Resource rhsMMResource = deserializer.getMetamodelResource(example.get("outputMetamodelURI"));
		
		ResourceMetaModelFactory mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(lhsMMResource);
		mmFactory.setRHSResource(rhsMMResource);
		mmFactory.build();
		
		mm = mmFactory.getRHSMetaModel();
		directedGraph = new NewDirectedLinkGraph();
		directedGraph.setNodes(mm.getClasses());
		directedGraph.build();
	}
	
	
	@Test
	public void testDirectedLinkGraph1() {
		Element e1 = mm.getClassByName("Attribute");
		Element e2 = mm.getClassByName("DomainValue");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Attribute to DomainValue", 2, dist);
	}
	
	@Test
	public void testDirectedLinkGraph2() {
		Element e1 = mm.getClassByName("Attribute");
		Element e2 = mm.getClassByName("Domain");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Attribute to Domain", 1, dist);
	}
	
	
	@Test
	public void testDirectedLinkGraph3() {
		Element e1 = mm.getClassByName("Domain");
		Element e2 = mm.getClassByName("Attribute");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Domain to Attribute", -1, dist);
	}
	
	
	@Test
	public void testDirectedLinkGraph4() {
		Element e1 = mm.getClassByName("DomainValue");
		Element e2 = mm.getClassByName("Attribute");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from DomainValue to Attribute", -1, dist);
	}
	
	@Test
	public void testDirectedLinkGraph5() {
		Element e1 = mm.getClassByName("Entity");
		Element e2 = mm.getClassByName("Entity");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Entity to Entity", 1, dist);
	}
	
	@Test
	public void testDirectedLinkGraph6() {
		Element e1 = mm.getClassByName("Model");
		Element e2 = mm.getClassByName("DomainValue");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Model to DomainValue", 4, dist);
	}
	
	
}
