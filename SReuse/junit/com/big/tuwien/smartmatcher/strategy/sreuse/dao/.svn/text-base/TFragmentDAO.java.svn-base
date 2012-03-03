package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.asSet;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.XMLUnitHelpers.AttWildcardIdElementDiff;

public class TFragmentDAO {
	private String container = "TFragmentDAO.dbxml";
	
	
	public TFragmentDAO() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() {
		// delete existing dbxml container
		File fContainer = new File(container);
		if(fContainer.exists())
			fContainer.delete();
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
	public void testFragmentSave() throws Exception {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		Element c1 = mm.clazz("C1");
		Element c2 = mm.clazz("C2");
		Element c3 = mm.clazz("C3");
		
		Fragment f = new Fragment();
		f.setRoot(true);
		f.setClasses(asSet(c1, c2, c3));
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		XMLFragment xmlFrag = XMLFragment.getInstance(f, resolverFac);
		
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO(container);
		fDao.save(xmlFrag);
		
		String xml = fDao.query("collection(\"" + container + "\")/fragment");
		System.out.println(xml);
		
		String controlXml = 
			"<fragment id=\"*\" root=\"true\">\n" + 
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
			"    <class id=\"*\">\n" + 
			"        <name>C3</name>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a6</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a5</name>\n" + 
			"        </attribute>\n" + 
			"        <attribute id=\"*\">\n" + 
			"            <name>a7</name>\n" + 
			"        </attribute>\n" + 
			"    </class>\n" + 
			"</fragment>";
		
		AttWildcardIdElementDiff diff = new AttWildcardIdElementDiff(controlXml, xml);
		assertTrue(diff.similar());
	}
	
	
	@Test
	public void testFragmentQuery() throws ReuseException {
		
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO();
		String queryString =
			"collection('fragments.dbxml')/fragment/class/attribute";
		
		String result = fDao.query(queryString);
		System.out.println("Query result:\n" + result);
	}
	
	
	@Test
	public void testFragmentAllDocumentsQuery() throws ReuseException {
		
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO();
		String queryString =
			"collection('fragments.dbxml')";
		
		String result = fDao.query(queryString);
		System.out.println("Query result:\n" + result);
	}
	
	
	@Test
	public void testFragmentQueryByExample() throws ReuseException {
		EasyMM mm = new EasyMM(new MM().getMetaModel());
		
		FragmentDAO fDao = new FragmentDAO();
		
		Fragment f = new Fragment();
		f.setId("F1");
		f.setClasses(new HashSet<Element>(asList(mm.clazz("C1"))));
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		XMLFragment xmlF = XMLFragment.getInstance(f, resolverFac);
		
		Set<XMLFragment> resultFrags = fDao.queryByExample(xmlF);
		System.out.println(resultFrags);
		
	}
	
	
	
	@Test
	public void testFragmentQueryAdvanced() throws ReuseException {
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO();
		String queryString =
			"(collection('fragments.dbxml')/fragment/class/name, collection('fragments.dbxml')/fragment/class/*/name)";
		
		
		
		String result = fDao.query(queryString);
		System.out.println("Query result:\n" + result);
		
	}
	
	
	@Test
	public void testLocalUserDefinedFunctionQuery() throws ReuseException {
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO();
		String queryString =
			"declare namespace functx = \"http://www.functx.com\"; " + 
			"declare function functx:escape-for-regex " +
			"( $arg as xs:string? )  as xs:string { " +
		       "replace($arg, " +
		       "'(\\.|\\[|\\]|\\\\|\\||\\-|\\^|\\$|\\?|\\*|\\+|\\{|\\}|\\(|\\))','\\\\$1') " +
		    "}; " +
			"declare function functx:substring-before-last " +
				"( $arg as xs:string? , " +
				"$delim as xs:string )  as xs:string { " +
				"if (matches($arg, functx:escape-for-regex($delim))) " +
				"then replace($arg, " +
					"concat('^(.*)', functx:escape-for-regex($delim),'.*'),'$1') " +
				"else '' " +
            "}; " +
            "functx:substring-before-last(\"alexander\",\"d\")";
		
		
		
		String result = fDao.query(queryString);
		System.out.println("Query result:\n" + result);
		
	}
	
	
	@Test
	public void testUserDefinedFunctionQueryImportedFromModule() 
											throws ReuseException {
		XmlDAOImpl<XMLFragment> fDao = new FragmentDAO();
		
		String queryString2 =
			"import module namespace functx=\"http://www.functx.com\" at \"file:///home/alex/ecworkspace/SReuse/functx.xq\"; " +
            "functx:substring-before-last(\"alexander\",\"d\")";
		
		String queryString3 =
			"import module namespace functx=\"http://www.functx.com\" at \"functx.xq\"; " +
            "functx:substring-before-last(\"alexander\",\"d\")";
		
		
		
		String result = fDao.query(queryString3);
		System.out.println("Query result:\n" + result);
		
	}
	
	
	@Test
	public void testFindChildrenQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<XMLFragment> children = fDao.findChildren("F_1");
		assertEquals(2, children.size());
	}
	
	
	@Test
	public void testFindChildrenIdsQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<String> children = fDao.findChildrenIds("F_1");
		assertEquals(2, children.size());
	}
	
	
	@Test
	public void testFindMappingsQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<XMLFragmentMapping> mappings = fDao.findMappings("F_1");
		assertEquals(1, mappings.size());
	}
	
	
	@Test
	public void testFindDescendentsIdsQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<String> dcs = fDao.findDescendentsIds("F_1");
		assertEquals(9, dcs.size());
	}
	
	
	@Test
	public void testFindRootsQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<XMLFragment> roots = fDao.findRoots();
		assertEquals(4, roots.size());
	}
	
	
	@Test
	public void testFindAssociatedQuery() throws ReuseException {
		FragmentDAO fDao = new FragmentDAO("TPropose.dbxml");
		
		Set<XMLFragment> associated = fDao.findAssociated("F_2");
		assertEquals(1, associated.size());
	}
}
