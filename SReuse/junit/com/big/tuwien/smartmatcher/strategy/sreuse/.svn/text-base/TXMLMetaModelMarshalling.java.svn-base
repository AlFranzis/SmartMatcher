package com.big.tuwien.smartmatcher.strategy.sreuse;

import static org.junit.Assert.*;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TXMLMetaModelMarshalling {
	
	public TXMLMetaModelMarshalling() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	public class MM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public MM() {
			mm =  mm(li(
					c("C1", 
						li(
							a("a1"),
							a("a2")
						)
					).as("C1"),
					c("C2", 
						li(
							a("a3"),
							a("a4")
						)
					).as("C2"),
					c("C3", 
						li(
							a("a5"),
							a("a6"),
							a("a7")
						)
					).as("C3"),
					r("r1", c("C1"), c("C2")),
					r("r2", c("C2"), c("C1")),
					r("r3", c("C2"), c("C3"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	@Test
	public void testMetaModelMarshalling() throws Exception {
		MetaModel mm = new MM().getMetaModel();
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		XMLMetaModel xmlMM = XMLMetaModel.getInstance(mm, resolverFac);
		XMLMarshallerImpl marshaller = new XMLMarshallerImpl();
		
		String xml = marshaller.marshall(xmlMM);
		System.out.println(xml);
		
		String controlXml =
			"<metamodel>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C2</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a4</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a3</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r2</name>\n" + 
			"        </reference>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r3</name>\n" + 
			"        </reference>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C1</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a2</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a1</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r1</name>\n" + 
			"        </reference>\n" + 
			"    </class>\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C3</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</metamodel>";
		
		AttWildcardIdElementDiff diff = new AttWildcardIdElementDiff(controlXml, xml);
		assertTrue(diff.similar());
		
	}
	
}
