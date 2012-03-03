package com.big.tuwien.smartmatcher.strategy.sreuse.propose;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.strategy.pso.MetaModelBuilder;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElementResolverFactory;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragments;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLMetaModel;
import com.big.tuwien.smartmatcher.strategy.sreuse.dao.FragmentDAO;
import com.big.tuwien.smartmatcher.strategy.sreuse.dao.FragmentMappingDAO;
import com.big.tuwien.smartmatcher.strategy.sreuse.dao.LazyFragmentDAO;
import com.big.tuwien.smartmatcher.strategy.sreuse.dao.MetaModelDAO;
import com.big.tuwien.smartmatcher.strategy.sreuse.dao.UniqueFragmentDAO.UniqueMarshaler;
import com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.TFragmentizer2;
import com.big.tuwien.smartmatcher.strategy.sreuse.helpers.TestHelpers.EasyMM;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TPropose {
	private String container = "TPropose.dbxml";
	
	
	public TPropose() {
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
					c("C4", 
						li(
							a("a8")
						)
					).as("C4"),
					c("C5", 
						li(
							a("a9"),
							a("a10")
						)
					).as("C5"),
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
					c("C1*", 
						li(
							a("a1*"),
							a("a2*")
						)
					).as("C1*"),
					c("C2*", 
						li(
							a("a3*"),
							a("a4*")
						)
					).as("C2*"),
					c("C3*", 
						li(
							a("a5*"),
							a("a6*"),
							a("a7*")
						)
					).as("C3*"),
					c("C4*", 
						li(
							a("a8*")
						)
					).as("C4*"),
					c("C5*", 
						li(
							a("a9*"),
							a("a10*")
						)
					).as("C5*"),
					r("r1*", c("C1*"), c("C2*")),
					r("r2*", c("C2*"), c("C1*")),
					r("r3*", c("C2*"), c("C3*"))
				)
				);
		}
		
		public MetaModel getMetaModel() {
			return mm;
		}
	}
	
	
	
	@Test
	public void testFragmentsSaving() throws ReuseException {
		
		// 12 Fragments on LHS, 12 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet1().getFragments();
		
		Set<Fragment> lhsFrags = fragments.e0;
		TFragmentizer2.printFragments(lhsFrags);
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		XMLFragments lhsXmlFrags = 
						XMLFragments.getInstance(fragments.e0, resolverFac);
		
		TFragmentizer2.printFragments2(lhsXmlFrags.getFragments());
		
		XMLFragments rhsXmlFrags = 
			XMLFragments.getInstance(fragments.e1, resolverFac);
		
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
		
//		for(XMLFragment f : rhsXmlFrags.getFragments()) {
//			for(XMLFragmentMapping fm : f.getMappings()) {
//				fmDao.save(fm);
//			}
//		}
		
		
		MetaModel lhsMM = new LHSMM().getMetaModel();
		XMLMetaModel xmlMM = XMLMetaModel.getInstance(lhsMM, resolverFac);
		MetaModelDAO mmDao = new MetaModelDAO(container);
		mmDao.save(xmlMM);
		
		XMLFragment f1 = fDao.find("F_1");
		assertNotNull(f1);
		
		XMLFragment f19 = fDao.find("F_19");
		assertNotNull(f19);
		
		assertTrue(f19.getParents().isEmpty());	
	}
	
	
	@Test
	public void testSimpleOnlinePropose() throws ReuseException {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		// 5 Fragments on LHS, 5 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet2().getFragments();
		
		Set<Fragment> lhsFrags = fragments.e0;
		Set<Fragment> rhsFrags = fragments.e1;
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		XMLFragments lhsXmlFrags = 
						XMLFragments.getInstance(lhsFrags, resolverFac);
		
		XMLFragments rhsXmlFrags = 
						XMLFragments.getInstance(rhsFrags, resolverFac);
		
		Set<XMLFragment> xmlFrags = 
				new HashSet<XMLFragment>(lhsXmlFrags.getFragments());
		xmlFrags.addAll(rhsXmlFrags.getFragments());
		
		XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		FragmentDAO fDao = new FragmentDAO(container);
		fDao.setMarshaller(marshaller);
		
		FragmentMappingDAO fmDao = new FragmentMappingDAO(container);
		fmDao.setMarshaller(marshaller);
		
		saveFragments(fDao, fmDao, xmlFrags);
		
		// PROPOSAL
		
		LazyFragmentDAO lfmDao = new LazyFragmentDAO(container);
		
		Set<XMLFragment> xmlRootFrags = lfmDao.findRoots();
		
		assertEquals(4, xmlRootFrags.size());

		BubbleFactory bFac = new BubbleFactoryImpl();
		
		ReuseProposer proposer = new ReuseProposer(resolverFac, bFac);
		Set<Bubble<? extends Operator>> bubbles = 
						proposer.propose(lhsMM, rhsMM, xmlRootFrags);
		
		// **********************
		// CHECKS 
		// **********************
		
		assertEquals(3, bubbles.size());
		
		System.out.println("******** RESULT ********");
		System.out.println(bubbles);
		
	}
	
	
	
	@Test
	public void testOnlinePropose() throws ReuseException {
		EasyMM lhsMM = new EasyMM(new LHSMM().getMetaModel());
		EasyMM rhsMM = new EasyMM(new RHSMM().getMetaModel());
		
		// 12 Fragments on LHS, 12 Fragments on RHS
		T2<Set<Fragment>,Set<Fragment>> fragments =
							new FragmentsSet1().getFragments();
		
		Set<Fragment> lhsFrags = fragments.e0;
		Set<Fragment> rhsFrags = fragments.e1;
		
		XMLElementResolverFactory resolverFac = 
							new XMLElementResolverFactory();
		XMLFragments lhsXmlFrags = 
						XMLFragments.getInstance(lhsFrags, resolverFac);
		
		XMLFragments rhsXmlFrags = 
						XMLFragments.getInstance(rhsFrags, resolverFac);
		
		Set<XMLFragment> xmlFrags = 
				new HashSet<XMLFragment>(lhsXmlFrags.getFragments());
		xmlFrags.addAll(rhsXmlFrags.getFragments());
		
		XMLMarshaller marshaller = new XMLMarshallerImpl();
		
		FragmentDAO fDao = new FragmentDAO(container);
		fDao.setMarshaller(marshaller);
		
		FragmentMappingDAO fmDao = new FragmentMappingDAO(container);
		fmDao.setMarshaller(marshaller);
		
		saveFragments(fDao, fmDao, xmlFrags);
		
		// PROPOSAL
		
		LazyFragmentDAO lfmDao = new LazyFragmentDAO(container);
		
		Set<XMLFragment> xmlRootFrags = lfmDao.findRoots();
		
		assertEquals(4, xmlRootFrags.size());

		BubbleFactory bFac = new BubbleFactoryImpl();
		
		ReuseProposer proposer = new ReuseProposer(resolverFac, bFac);
		Set<Bubble<? extends Operator>> bubbles = 
						proposer.propose(lhsMM, rhsMM, xmlRootFrags);
		
		// **********************
		// CHECKS 
		// **********************
		
		assertEquals(4, bubbles.size());
		
		System.out.println("******** RESULT ********");
		System.out.println(bubbles);
		
	}
	
	
	public static void saveFragments(FragmentDAO fDao, 
			FragmentMappingDAO fmDao, Set<XMLFragment> fragments) {
		// save fragments
		for(XMLFragment f : fragments) {
			fDao.save(f);
		}
		
		// store already saved mappings so they are not persisted
		// twice
		Set<XMLFragmentMapping> savedMappings = 
						new HashSet<XMLFragmentMapping>();
		for(XMLFragment f : fragments) {
			for(XMLFragmentMapping fm : f.getMappings()) {
				if(!savedMappings.contains(fm)) {
					fmDao.save(fm);
					savedMappings.add(fm);
				}
			}
		}
	}
		
}
