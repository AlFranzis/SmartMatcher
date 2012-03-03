package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.FragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.Operator;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragments;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.OperatorBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.ElementAttributeWildcardDiff;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TFragmentMappingDAO {
	private String container = "TFragmentMappingDAO.dbxml";
	
	
	public TFragmentMappingDAO() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() {
		// delete existing dbxml container
		File fContainer = new File(container);
		if(fContainer.exists())
			fContainer.delete();
	}
	
	
	public class LHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public LHSMM() {
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
	
	
	public class RHSMM extends MetaModelBuilder {
		private MetaModel_ mm;
		
		public RHSMM() {
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
	public void testFragmentMappingSave() throws Exception {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		Element lhsC1 = lhsMM.clazz("C1");
		Element lhsA1 = lhsMM.qatt("C1.a1");
		Element lhsC2 = lhsMM.clazz("C2");
		
		Fragment f1 = new Fragment();
		f1.setId("F1");
		f1.setRoot(true);
		f1.setClasses(asSet(lhsC1,lhsC2));
		
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		Element rhsC1 = rhsMM.clazz("C1");
		Element rhsA1 = rhsMM.qatt("C1.a1");
		Element rhsC2 = rhsMM.clazz("C2");
			
		Fragment f2 = new Fragment();
		f2.setId("F2");
		f2.setRoot(true);
		f2.setClasses(asSet(rhsC1,rhsC2));
		
		FragmentMapping fm = new FragmentMapping();
		fm.setId("FM1");
		
		Operator op1 = OperatorBuilder.c2c(lhsC1, rhsC1);
		op1.setId("OP1");
		Operator op2 = OperatorBuilder.a2a(op1, lhsA1, rhsA1);
		op2.setId("OP2");
		Operator op3 = OperatorBuilder.c2c(lhsC2, rhsC2);
		op3.setId("OP3");
		
		fm.setOperators(asSet(op1, op2, op3));
		
		//f1.setMappings(asSet(fm));
		//f2.setMappings(asSet(fm));
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		
		XMLFragments xmlFrags = XMLFragments.getInstance(asList(f1, f2), resolverFac);
		
		XMLFragmentMapping xmlFM = XMLFragmentMapping.getInstance(fm, resolverFac);
		
		xmlFrags.getFragments().get(0).setMappings(asSet(xmlFM));
		xmlFrags.getFragments().get(1).setMappings(asSet(xmlFM));
		
		XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		FragmentMappingDAO fmDao = new FragmentMappingDAO(container);
		fmDao.setMarshaller(marshaller);
		fmDao.save(xmlFM);
		
		XmlDAOImpl<XMLFragments> fDao = new XmlDAOImpl<XMLFragments>(container);
		fDao.setMarshaller(marshaller);
		fDao.save(xmlFrags);;
		
		
		String xml = fmDao.query("collection(\"" + container + "\")/fragment-mapping");
		String controlXml =
			"<fragment-mapping id=\"FM1\">\n" + 
			"    <operator id=\"*\" name=\"A2A\" sim=\"-1.0\" parents=\"*\">\n" + 
			"        <rhs-role name=\"rhsFocusAttribute\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"attribute\">*</element>\n" + 
			"        </rhs-role>\n" + 
			"        <lhs-role name=\"lhsFocusAttribute\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"attribute\">*</element>\n" + 
			"        </lhs-role>\n" + 
			"    </operator>\n" + 
			"    <operator id=\"*\" name=\"C2C\" sim=\"-1.0\">\n" + 
			"		 <children>*</children>\n" +
			"        <lhs-role name=\"lhsFocusElement\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">*</element>\n" + 
			"        </lhs-role>\n" + 
			"        <rhs-role name=\"rhsFocusElement\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">*</element>\n" + 
			"        </rhs-role>\n" + 
			"    </operator>\n" + 
			"    <operator id=\"*\" name=\"C2C\" sim=\"-1.0\">\n" + 
			"        <lhs-role name=\"lhsFocusElement\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">*</element>\n" + 
			"        </lhs-role>\n" + 
			"        <rhs-role name=\"rhsFocusElement\">\n" + 
			"            <element xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"class\">*</element>\n" + 
			"        </rhs-role>\n" + 
			"    </operator>\n" + 
			"</fragment-mapping>\n";
		
			ElementAttributeWildcardDiff diff3 = new ElementAttributeWildcardDiff(controlXml, xml);
			assertTrue(diff3.similar());
			
			String xml2 = fDao.query("collection(\"" + container + "\")/fragments");
			String controlXml2 =
			"<fragments>\n" +	
			"<fragment id=\"F1\" root=\"true\">\n" + 
			"    <class id=\"*\">\n" + 
			"        <name>C2</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a3</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a4</name>\n" + 
			"        </attribute>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r3</name>\n" + 
			"        </reference>\n" + 
			"        <reference id=\"*\" target=\"*\">\n" + 
			"            <name>r2</name>\n" + 
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
			"    <mappings>FM1</mappings>\n" + 
			"</fragment>\n" + 
			"<fragment id=\"F2\" root=\"true\">\n" + 
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
			"    <mappings>FM1</mappings>\n" + 
			"</fragment>\n" +
			"</fragments>\n";	
			
			AttWildcardIdElementDiff diff2 = 
				new AttWildcardIdElementDiff(controlXml2, xml2);
			assertTrue(diff2.similar());
	}
	
}
