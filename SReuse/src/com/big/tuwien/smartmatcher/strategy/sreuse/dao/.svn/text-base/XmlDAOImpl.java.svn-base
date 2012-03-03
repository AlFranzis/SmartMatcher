package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.big.tuwien.SmartMatcher.strategy.pso.Tuples;
import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;
import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.MyResolver;
import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAny;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;
import com.sleepycat.dbxml.XmlContainer;
import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlManager;
import com.sleepycat.dbxml.XmlQueryContext;
import com.sleepycat.dbxml.XmlQueryExpression;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlValue;

public class XmlDAOImpl<T extends XMLAny> {
	private static Logger logger = Logger.getLogger(XmlDAOImpl.class);
	
	protected static String FRAGMENTS_CONTAINER = "fragments.dbxml";
	
	protected String fragmentContainer = FRAGMENTS_CONTAINER;
	
	protected XMLMarshaller marshaller = new XMLMarshallerImpl();
	
	protected IdGenerator<T> idGenerator 
						= new IdGenerator<T>() {
							@Override
							public String generateId(XMLAny mm) {
								return "XMLAny" + System.currentTimeMillis();
							}
						};
					
	// first level cache
//	protected Map<Class<?>, Map<String,XMLAny>> cache =
//		new HashMap<Class<?>, Map<String,XMLAny>>();
	
	
	public XmlDAOImpl() {}
	
	
	public XmlDAOImpl(String fragmentContainer) {
		this.fragmentContainer = fragmentContainer;
	}
	
	
	public XmlDAOImpl(IdGenerator<T> idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	
	public XmlDAOImpl(String fragmentContainer, IdGenerator<T> idGenerator) {
		this(fragmentContainer);
		this.idGenerator = idGenerator;
	}
	
	
	public void setMarshaller(XMLMarshaller marshaller) {
		this.marshaller = marshaller;
	}
	
	
//	protected boolean cacheContains(Class<?> ctype, String id) {
//		Map<String,XMLAny> cachedInstances = cache.get(ctype);
//		if(cachedInstances == null)
//			return false;
//		
//		return cachedInstances.containsKey(id);
//	}
//	
//
//	protected <S> S cacheGet(Class<S> ctype, String id) {
//		Map<String,XMLAny> cachedInstances = cache.get(ctype);
//		if(cachedInstances == null)
//			return null;
//		@SuppressWarnings("unchecked")
//		S cached = (S) cachedInstances.get(id);
//		return cached;
//	}
//	
//	
//	protected void cacheStore(Class<?> ctype, XMLIdentifiable instance) {
//		Map<String,XMLAny> cachedInstances = cache.get(ctype);
//		if(cachedInstances == null)
//			cachedInstances = new HashMap<String, XMLAny>();
//		
//		String id = instance.getId();
//		if(id == null)
//			throw new ReuseRuntimeException(
//					"Instance to be cached has no set ID: " + instance);
//		
//		cachedInstances.put(id, instance);
//		cache.put(ctype, cachedInstances);
//	}
	
	
	public void save(T xmlAny) {
		// marshal object to xml
		String xml = marshaller.marshall(xmlAny);
		
		T2<XmlManager,XmlContainer> dbObjs = getFragmentContainer();
		XmlContainer fragsContainer = dbObjs.e1;
		
		try {
			String fragmentId = idGenerator.generateId(xmlAny);
			fragsContainer.putDocument(fragmentId, xml.toString());
			
		} catch (XmlException e) {
			throw new ReuseRuntimeException(e);
		} finally {
			cleanup(dbObjs.e0, dbObjs.e1);
		}	
	}
	
	
	public String query(String queryString) throws ReuseException {
		Collection<String> result = query(queryString, new ResultHandler<String>() {

			@Override
			public Collection<String> convert(XmlResults result) throws ReuseException {
				StringBuilder sResult = new StringBuilder();
				XmlValue value = new XmlValue();
				try {
					while ((value = result.next()) != null) {
						sResult.append(value.asString());
					}
				
					return Arrays.asList(sResult.toString());
				} catch (XmlException e) {
					throw new ReuseException(e);
				}
			}
			
		});
		
		return result.iterator().next();
	}
	
	
	public <S> Collection<S> query(String queryString, 
									ResultHandler<S> resultHandler) 
												throws ReuseException {
		return query(queryString, 
				Collections.<String,String>emptyMap(), 
				resultHandler);
	}
	
	
	public <S> Collection<S> query(String query, Map<String,String> vars, 
													ResultHandler<S> rh) 
													throws ReuseException {
		return abstractQuery(query, vars, rh);
	}
	
	
	public XMLMarshaller getMarshaller() {
		return marshaller;
	}
	
	
	protected T2<XmlManager,XmlContainer> getFragmentContainer() {
		// declare these here for cleanup
		XmlManager mgr = null;
		XmlContainer cont = null;

		try {
			// All BDB XML programs require an XmlManager instance
			mgr = new XmlManager();
			mgr.registerResolver(new MyResolver());

			if (mgr.existsContainer(fragmentContainer) != 0)
				// open exisiting container
				cont = mgr.openContainer(fragmentContainer);
			else {
				// create container
				cont = mgr.createContainer(fragmentContainer);
			}
		} catch (XmlException e) {
			throw new ReuseRuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new ReuseRuntimeException(e);
		} 

		return Tuples.t(mgr,cont);
	}
	
	
	/**
	 * Shielded version of method 
	 * {@link FragmentDAO#abstractQuery(String, Map, ResultHandler)} to
	 * wrap checked exceptions into unchecked exceptions.
	 * @param <S>
	 * @param query
	 * @param vars
	 * @param rh
	 * @return
	 */
	protected <S> Collection<S> abstractQuery2(String query, 
			Map<String,String> vars, ResultHandler<S> rh) {
		try {
			Collection<S> res = abstractQuery(query, vars, rh);
			return res;
		} catch (ReuseException e) {
			logger.error("Error processing query: " + e.getMessage(), e);
			throw new ReuseRuntimeException(e);
		}
	}
	
	
	protected <S> Collection<S> abstractQuery(String query, 
			Map<String,String> vars, ResultHandler<S> rh)
													throws ReuseException {
				T2<XmlManager,XmlContainer> dbObjs = getFragmentContainer();
				XmlContainer fragsContainer = dbObjs.e1;
				XmlManager mgr = dbObjs.e0;
				
				try {
					XmlQueryContext qc = mgr.createQueryContext();
					qc.setDefaultCollection(fragmentContainer);
					
					// XQuery variable $container is bound implicitly
					qc.setVariableValue(
									"container", 
									new XmlValue(fragmentContainer));
					
					for(Entry<String,String> var : vars.entrySet()) {
						qc.setVariableValue(
									var.getKey(), 
									new XmlValue(var.getValue()));
					}
					
					XmlQueryExpression expr = mgr.prepare(query, qc);
					XmlResults result = expr.execute(qc);
					
					Collection<S> converted = rh.convert(result);
					
					// release resources
					result.delete();
					expr.delete();
					
					return converted;
				} catch (XmlException e) {
					throw new ReuseRuntimeException(e);
				} finally {
					cleanup(mgr, fragsContainer);
				}
			}


	/* 
	 * This function is used to ensure that databases are
	 * properly closed, even on exceptions
	 */
	protected static void cleanup(XmlManager mgr, XmlContainer cont) {
		try {
			if (cont != null)
				cont.delete();
			if (mgr != null)
				mgr.delete();
		} catch (Exception e) {
			// ignore exceptions in cleanup
		}
	}
	
}
