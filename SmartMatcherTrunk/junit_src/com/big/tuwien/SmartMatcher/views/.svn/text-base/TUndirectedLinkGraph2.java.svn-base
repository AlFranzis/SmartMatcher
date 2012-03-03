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
import com.big.tuwien.SmartMatcher.views.dom.NewUndirectedLinkGraph;


public class TUndirectedLinkGraph2 {
	private MetaModel mm;
	private NewUndirectedLinkGraph graph;
	
	
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
		graph = new NewUndirectedLinkGraph();
		graph.setNodes(mm.getClasses());
		graph.build();
	}
	
	
	@Test
	public void testUndirectedLinkGraph1() {
		Element e1 = mm.getClassByName("DomainValue");
		Element e2 = mm.getClassByName("Attribute");
		int dist = graph.getMinDistance(e1, e2);
		assertEquals("Shortest undirected path from DomainValue to Atribute", 2, dist);
	}
	
	@Test
	public void testUndirectedLinkGraph2() {
		Element e1 = mm.getClassByName("Attribute");
		Element e2 = mm.getClassByName("DomainValue");
		int dist = graph.getMinDistance(e1, e2);
		assertEquals("Shortest undirected path from Attribute to DomainValue", 2, dist);
	}
	
	@Test
	public void testUndirectedLinkGraph3() {
		Element e1 = mm.getClassByName("Attribute");
		Element e2 = mm.getClassByName("Relationship");
		int dist = graph.getMinDistance(e1, e2);
		assertEquals("Shortest undirected path from Attribute to Relationship", 2, dist);
	}
	
	
	
}
