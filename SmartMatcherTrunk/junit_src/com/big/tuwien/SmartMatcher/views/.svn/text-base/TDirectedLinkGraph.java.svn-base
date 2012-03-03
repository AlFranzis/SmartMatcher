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
import com.big.tuwien.SmartMatcher.views.dom.DirectedLinkGraph;


public class TDirectedLinkGraph {
	private MetaModel mm;
	private DirectedLinkGraph directedGraph;
	
	
	@Before
	public void setUp() {
		Map<String,String> example = ExampleStore.getExample(ExampleStore.PERSON_PERSONFAMILY);
		
		ModelDeserializer deserializer = new ModelDeserializer();
		Resource lhsMMResource = deserializer.getMetamodelResource(example.get("inputMetamodelURI"));
		Resource rhsMMResource = deserializer.getMetamodelResource(example.get("outputMetamodelURI"));
		
		ResourceMetaModelFactory mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(lhsMMResource);
		mmFactory.setRHSResource(rhsMMResource);
		mmFactory.build();
		
		mm = mmFactory.getRHSMetaModel();
		directedGraph = new DirectedLinkGraph();
		directedGraph.setNodes(mm.getClasses());
		directedGraph.build();
	}
	
	
	@Test
	public void testDirectedLinkGraph1() {
		Element e1 = mm.getClassByName("Person");
		Element e2 = mm.getClassByName("Family");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Person to Family", 1, dist);
	}
	
	@Test
	public void testDirectedLinkGraph2() {
		Element e1 = mm.getClassByName("Family");
		Element e2 = mm.getClassByName("Person");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Family to Person", -1, dist);
	}
	
	@Test
	public void testDirectedLinkGraph3() {
		Element e1 = mm.getClassByName("Person");
		Element e2 = mm.getClassByName("Person");
		int dist = directedGraph.getMinDistance(e1, e2);
		assertEquals("Shortest directed path from Person to Person", -1, dist);
	}
	
	
	
}
