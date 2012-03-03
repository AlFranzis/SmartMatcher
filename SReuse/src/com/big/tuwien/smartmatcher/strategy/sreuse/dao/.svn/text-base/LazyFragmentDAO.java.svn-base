package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLClassElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLMetaModel;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLOperator;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlValue;

public class LazyFragmentDAO extends UniqueFragmentDAO {
	private static Logger logger = Logger.getLogger(LazyFragmentDAO.class);
	
	private FragmentWrapperHelper fwHelper = new FragmentWrapperHelper();
	
	
	public LazyFragmentDAO() {
		super();
	}


	public LazyFragmentDAO(IdGenerator<XMLFragment> idGenerator) {
		super(idGenerator);
	}


	public LazyFragmentDAO(String fragmentContainer) {
		super(fragmentContainer);
	}


	@Override
	public Set<XMLFragment> findRoots() throws ReuseException {
		ResultHandler<XMLFragment> rh = 
					new LazyFragmentHandler(this, marshaller);
		return super.findRoots(rh);
	}
	
	
	public static class LazyFragmentHandler 
							implements ResultHandler<XMLFragment> {
		private XMLMarshaller unmarshaller;
		private LazyFragmentDAO dao;
		
		
		public LazyFragmentHandler(LazyFragmentDAO dao, 
									XMLMarshaller unmarshaller) {
			this.dao = dao;
			this.unmarshaller = unmarshaller;
		}
		
		
		@Override
		public Collection<XMLFragment> convert(XmlResults result) 
											throws ReuseException {
			Set<XMLFragment> frags = new HashSet<XMLFragment>();
			try {
				XmlValue value;
				while((value = result.next()) != null) {
					String xml = value.asString();
					XMLFragment frag = (XMLFragment) unmarshaller
											.unmarshall(new StringReader(xml));
					LazyXMLFragment lazyFrag = new LazyXMLFragment(dao, frag);
					frags.add(lazyFrag);
				}
			} catch (XmlException e) {
				throw new ReuseException(
						"Error while processing query result", e);
			}
			
			return frags;
		}
	}
	
	
	public static class LazyXMLFragmentMapping extends XMLFragmentMapping {
		private XMLFragmentMapping fm;
		
		
		public LazyXMLFragmentMapping(XMLFragmentMapping fm) {
			this.fm = fm;
		}


		@Override
		public void addFlattenedOperator(XMLOperator flattenedOperator) {
			fm.addFlattenedOperator(flattenedOperator);
		}

		
		@Override
		public Set<XMLOperator> getFlattenedOperators() {
			return fm.getFlattenedOperators();
		}


		@Override
		public String getId() {
			return fm.getId();
		}


		@Override
		public Set<XMLOperator> getOperators() {
			return fm.getOperators();
		}

		
		@Override
		public void setFlattenedOperators(Set<XMLOperator> flattenedOperators) {
			fm.setFlattenedOperators(flattenedOperators);
		}


		@Override
		public void setId(String id) {
			fm.setId(id);
		}

		
		@Override
		public void setOperators(Set<XMLOperator> operators) {
			fm.setOperators(operators);
		}


		@Override
		public String toString() {
			return fm.toString();
		}
		
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(other == null || !(other instanceof XMLFragmentMapping)) 
				return false;
			
			XMLFragmentMapping that = (XMLFragmentMapping) other;
			 
			if(this.getId() == null || that.getId() == null)
				throw new ReuseRuntimeException(
						"LazyXMLFragmentMapping instances must have set id");
				
			return this.getId().equals(that.getId());
		}
		
		
		@Override
		public int hashCode() {
			return 13 * LazyXMLFragmentMapping.class.hashCode() 
					+ 17 * getId().hashCode(); 
		}
		
		
	}
	
	
	public static class LazyXMLFragment extends XMLFragment {
		private XMLFragment f;
		private LazyFragmentDAO dao;
		
		
		public LazyXMLFragment(LazyFragmentDAO dao, XMLFragment f) {
			this.dao = dao;
			this.f = f;
		}
		
		
		@Override
		public Set<XMLFragment> getParents() {
			Set<XMLFragment> parents = dao.findParents(f.getId());
			
			Set<XMLFragment> wrapped = dao.fwHelper.wrapFragments(parents);
			
			return wrapped;
		}
		
		
		public Set<XMLFragment> getChildren() {
			Set<XMLFragment> children = dao.findChildren(f.getId());
			
			Set<XMLFragment> wrapped = dao.fwHelper.wrapFragments(children);
		
			return wrapped;
		}
		
		
		@Override
		public Set<XMLFragmentMapping> getMappings() {
			Set<XMLFragmentMapping> mappings = dao.findMappings(f.getId());
		
			Set<XMLFragmentMapping> wrapped = 
							dao.fwHelper.wrapFragmentMappings(mappings);
			return wrapped;
		}


		@Override
		public Set<XMLClassElement> getClasses() {
			return f.getClasses();
		}


		@Override
		public String getId() {
			return f.getId();
		}


		@Override
		public Set<XMLMetaModel> getMetamodels() {
			return f.getMetamodels();
		}


		@Override
		public boolean isRoot() {
			return f.isRoot();
		}


		@Override
		public void setClasses(Set<XMLClassElement> classes) {
			f.setClasses(classes);
		}


		@Override
		public void setId(String id) {
			f.setId(id);
		}


		@Override
		public void setMappings(Set<XMLFragmentMapping> mappings) {
			f.setMappings(mappings);
		}


		@Override
		public void setMetamodels(Set<XMLMetaModel> metamodels) {
			f.setMetamodels(metamodels);
		}


		@Override
		public void setParents(Set<XMLFragment> parents) {
			f.setParents(parents);
		}


		@Override
		public void setRoot(boolean root) {
			f.setRoot(root);
		}

		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(other == null || !(other instanceof XMLFragment)) return false;
			
			XMLFragment that = (XMLFragment) other;
			 
			if(this.getId() == null || that.getId() == null)
				throw new ReuseRuntimeException(
						"LazyXMLFragment instances must have set id");
				
			return this.getId().equals(that.getId());
		}
		
		
		@Override
		public int hashCode() {
			return 103 * LazyXMLFragment.class.hashCode() 
					+ 61 * getId().hashCode(); 
		}

		
		@Override
		public String toString() {
			return f.toString();
		}
		
	}
	
	
	public class FragmentWrapperHelper {
		private WeakHashMap<Integer, Set<XMLFragment>> cachedWrappedFrags
			= new WeakHashMap<Integer, Set<XMLFragment>>();
		
		private WeakHashMap<Integer, Set<XMLFragmentMapping>> cachedWrappedFMs
			= new WeakHashMap<Integer, Set<XMLFragmentMapping>>();
		

		public Set<XMLFragment> wrapFragments(Set<XMLFragment> fs) {
			// query cache first
			if(cachedWrappedFrags.containsKey(fs.hashCode())) {
				logger.debug("Cache hit for wrapped lazy fragments " + fs);
				return cachedWrappedFrags.get(fs.hashCode());
			}
				
			
			Set<XMLFragment> wrapped = new HashSet<XMLFragment>();
			for(XMLFragment f : fs) {
				LazyXMLFragment lf = 
					new LazyXMLFragment(LazyFragmentDAO.this, f);
				wrapped.add(lf);
			}
			
			// store in cache
			cachedWrappedFrags.put(fs.hashCode(), wrapped);
			
			return wrapped;
		}
		
		
		public Set<XMLFragmentMapping> wrapFragmentMappings(
										Set<XMLFragmentMapping> fms) {
			// query cache first
			if(cachedWrappedFMs.containsKey(fms.hashCode())) {
				logger.debug(
					"Cache hit for wrapped lazy fragment mappings " + fms);
				return cachedWrappedFMs.get(fms.hashCode());
			}
			
			Set<XMLFragmentMapping> wrapped = 
							new HashSet<XMLFragmentMapping>();
			for(XMLFragmentMapping fm : fms) {
				LazyXMLFragmentMapping lfm = new LazyXMLFragmentMapping(fm);
				wrapped.add(lfm);
			}
			
			// store in cache
			cachedWrappedFMs.put(fms.hashCode(), wrapped);
			
			return wrapped;
		}
	}
	
}
