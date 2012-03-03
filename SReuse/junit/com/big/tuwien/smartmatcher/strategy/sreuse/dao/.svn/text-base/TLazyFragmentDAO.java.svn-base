package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragments;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentsSet;
import com.big.tuwien.smartmatcher.strategy.sreuse.propose.FragmentsSet1;

public class TLazyFragmentDAO {
	private String container = "TLazyFragmentDAO.dbxml";
	private FragmentsSet fragmentsSet = new FragmentsSet1();
	
	
	public TLazyFragmentDAO() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() {
		// delete existing dbxml container
		File fContainer = new File(container);
		if(fContainer.exists())
			fContainer.delete();
		
		saveFragmentSet(fragmentsSet, container);
	}
	
	
	private void saveFragmentSet(FragmentsSet fs, String container) {
		T2<Set<Fragment>,Set<Fragment>> fragments =
							fs.getFragments();
		
		XMLElementResolverFactory resolverFac = new XMLElementResolverFactory();
		XMLFragments lhsXmlFrags = XMLFragments.getInstance(fragments.e0, resolverFac);
		XMLFragments rhsXmlFrags = XMLFragments.getInstance(fragments.e1, resolverFac);
		
		XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		FragmentDAO fDao = new FragmentDAO(container);
		fDao.setMarshaller(marshaller);
		
		FragmentMappingDAO fmDao = new FragmentMappingDAO(container);
		fmDao.setMarshaller(marshaller);
		
		for(XMLFragment f : lhsXmlFrags.getFragments()) {
			fDao.save(f);
		}
		
		for(XMLFragment f : rhsXmlFrags.getFragments()) {
			fDao.save(f);
		}
		
		for(XMLFragment f : lhsXmlFrags.getFragments()) {
			for(XMLFragmentMapping fm : f.getMappings()) {
				fmDao.save(fm);
			}
		}
	}
	
	
	@Test
	public void testFindRoots() throws ReuseException {
		//XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		LazyFragmentDAO fDao = new LazyFragmentDAO(container);
		// fDao.setMarshaller(marshaller);
		
		Set<XMLFragment> roots = fDao.findRoots();
		assertEquals(4, roots.size());
		
		XMLFragment root = roots.iterator().next();
		
		// lazy loading of parents
		Set<XMLFragment> parents = root.getParents();
		
		// root fragment has no parents
		assertTrue(parents.isEmpty());
	}
	
	
	@Test
	public void testLazyLoadingOfChildren() throws ReuseException {
		//XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		LazyFragmentDAO fDao = new LazyFragmentDAO(container);
		//fDao.setMarshaller(marshaller);
		
		Set<XMLFragment> roots = fDao.findRoots();
		assertEquals(4, roots.size());
		
		XMLFragment root = roots.iterator().next();
		// lazy loading of children
		Set<XMLFragment> children = root.getChildren();
		assertFalse(children.isEmpty());
		
		
	}
	
	
	@Test
	public void testRecursiveLazyLoadingOfChildren() throws ReuseException {
		//XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		LazyFragmentDAO fDao = new LazyFragmentDAO(container);
		//fDao.setMarshaller(marshaller);
		
		Set<XMLFragment> roots = fDao.findRoots();
		assertEquals(4, roots.size());
		
		XMLFragment root = roots.iterator().next();
		// lazy loading of children
		Set<XMLFragment> children = root.getChildren();
		assertFalse(children.isEmpty());
		
		XMLFragment child = children.iterator().next();
		Set<XMLFragment> childrenChildren = child.getChildren();
		assertFalse(childrenChildren.isEmpty());
	}
	
	
}
