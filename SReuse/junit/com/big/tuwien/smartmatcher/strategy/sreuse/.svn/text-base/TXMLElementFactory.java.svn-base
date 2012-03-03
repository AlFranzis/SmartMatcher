package com.big.tuwien.smartmatcher.strategy.sreuse;

import static com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.sortXmlElementsByName;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;

public class TXMLElementFactory {
	
	public TXMLElementFactory() {
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
	public void testXMLClassesConstruction() {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		Element c1 = mm.clazz("C1");
		Element c2 = mm.clazz("C2");
		Element c3 = mm.clazz("C3");
		
		XMLElementResolverFactory resolverFac = 
			new XMLElementResolverFactory();
		Set<XMLClassElement> xmlClasses = resolverFac.resolve2(
												asList(c1, c2, c3)
											);
		
		assertEquals(3, xmlClasses.size());
		
		List<XMLClassElement> sXmlClasses = sortXmlElementsByName(xmlClasses);
		XMLClassElement xmlClassC1 = sXmlClasses.get(0);
		assertEquals(2, xmlClassC1.getAttributes().size());
		assertEquals(1, xmlClassC1.getReferences().size());
		
		XMLClassElement xmlClassC2 = sXmlClasses.get(1);
		assertEquals(2, xmlClassC2.getAttributes().size());
		assertEquals(2, xmlClassC2.getReferences().size());
		
		XMLClassElement xmlClassC3 = sXmlClasses.get(2);
		assertEquals(3, xmlClassC3.getAttributes().size());
		assertEquals(0, xmlClassC3.getReferences().size());
	}
}
