package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Literals.map;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;


public class FragmentDAO extends XmlDAOImpl<XMLFragment> {
	// private static Logger logger = Logger.getLogger(FragmentDAO.class);
	
	
	public FragmentDAO() {
		idGenerator = new IdGenerator<XMLFragment>() {
			@Override
			public String generateId(XMLFragment f) {
				return "Fragment" + System.currentTimeMillis();
			}
			
		};
	}
	
	
	public FragmentDAO(String fragmentContainer) {
		this();
		this.fragmentContainer = fragmentContainer;
	}
	
	
	public FragmentDAO(IdGenerator<XMLFragment> idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	
	public void save(XMLFragment f) {
		super.save(f);
	}
	

	// TODO:implementation not finished
	public Set<XMLFragment> queryByExample(XMLFragment f) 
													throws ReuseException {
		return null;
		
	}
	
	
	public XMLFragment find(String fId) throws ReuseException {
		String query = 
			"collection($container)/fragment[@id=$fId]";
		
		ResultHandler<XMLFragment> rh = new FragmentHandler(marshaller);
		
		Collection<XMLFragment> fs = query(
					query, 
					map("fId", fId),
					rh
					);
		
		if(fs.isEmpty())
			return null;
		else {
			XMLFragment f = fs.iterator().next();
			return f;
		}
	}
	
	
	/**
	 * Returns the root fragments.
	 * @return
	 * @throws ReuseException
	 */
	public Set<XMLFragment> findRoots() throws ReuseException {
		ResultHandler<XMLFragment> rh = new FragmentHandler(marshaller);
		return findRoots(rh);
	}
	
	
	/**
	 * Returns the root fragments.
	 * @return
	 * @throws ReuseException
	 */
	public Set<XMLFragment> findRoots(ResultHandler<XMLFragment> rh) 
												throws ReuseException {	
		String query = 
		"collection($container)/fragment[@root=\"true\"]";
		
		
		Collection<XMLFragment> _roots = abstractQuery(
										query, 
										Collections.<String,String>emptyMap(), 
										rh);
		Set<XMLFragment> roots = (Set<XMLFragment>) _roots; 
		return roots;
	}
	
	
	/**
	 * Returns all fragments that are associated with the given mapping
	 * Associated in this context means linked by a common fragment mapping.
	 * @param fragId
	 * @return
	 * @throws ReuseException
	 */
	public Set<XMLFragment> findAssociated(String fragId) 
											throws ReuseException {
		String query = 
		"import module namespace sreusex=\"http://www.big.tuwien.ac.at/smart_matcher/sreuse\" " +
		"at \"sreusex.xq\"; " +
		"let $fmIds1 := data(collection($container)/fragment[@id=$fragId]/mappings) \n" +
		"for $f in collection($container)/fragment \n" +
		"let $fmIds2 := data($f/mappings) \n" +
		"where $f/@id != $fragId and sreusex:have-common-value($fmIds1, $fmIds2) \n" +
		"return $f";
		
		ResultHandler<XMLFragment> rh = new FragmentHandler(marshaller);
		
		Collection<XMLFragment> associated = abstractQuery(
											query, 
											map("fragId", fragId), 
											rh);
		return (Set<XMLFragment>) associated; 
	}
	
	
	/**
	 * Returns all mappings for a fragment.
	 * @param fragId The id of the fragment whose mappings are retrieved.
	 * @return Set of fragment mappings.
	 */
	public Set<XMLFragmentMapping> findMappings(String fId) {
		String query = 
		"let $fmIds := data(collection($container)/fragment[@id=$fragId]/mappings) \n" +
		"for $fmId in $fmIds \n" +
		"return collection($container)/fragment-mapping[@id=$fmId]"; 
		
		ResultHandler<XMLFragmentMapping> rh = 
							new FragmentMappingHandler(marshaller);
		
		Collection<XMLFragmentMapping> _mappings = abstractQuery2(
											query, 
											map("fragId", fId), 
											rh);
		Set<XMLFragmentMapping> mappings = (Set<XMLFragmentMapping>) _mappings; 
		return mappings;
	}
	
	
	
	public Set<XMLFragmentMapping> findCommonMappings(String fId1, String fId2) {
		String query = 
		"let $fmIds1 := data(collection($container)/fragment[@id=$fId1]/mappings) \n" +
		"let $fmIds2 := data(collection($container)/fragment[@id=$fId2]/mappings) \n" +
		"let $commonFmIds := functx:value-intersect(fmIds1, fmIds2) \n" +
		"for $fmId in $commonFmIds \n" +
		"return collection($container)/fragment-mappings[@id=fmId]"; 
		
		ResultHandler<XMLFragmentMapping> rh = new FragmentMappingHandler(marshaller);
		
		Collection<XMLFragmentMapping> associated = abstractQuery2(
											query, 
											map("fId1", fId1)
											.map("fId2", fId2), 
											rh);
		return (Set<XMLFragmentMapping>) associated; 
	}
	
	
	/**
	 * Returns all children (-fragments) of a fragment.
	 * @param parentId
	 * @return
	 * @throws ReuseException
	 */
	public Set<XMLFragment> findChildren(String parentId) {
		String query = 
		"import module namespace functx=\"http://www.functx.com\" " +
		"at \"functx.xq\"; " +
		"for $frag in collection($container)/fragment " +
		"let $parentIds := tokenize($frag/@parent, $delim) " +
		"where functx:is-value-in-sequence($parentId, $parentIds) " +
		"return $frag";
		
		ResultHandler<XMLFragment> rh = new FragmentHandler(marshaller);
		
		Collection<XMLFragment> _children = abstractQuery2(
				query, 
				map("parentId", parentId)
				.map("delim", " "), 
				rh);
		
		Set<XMLFragment> children = (Set<XMLFragment>) _children;
		return children;
	}
	
	
	/**
	 * Returns all parents (-fragments) of a fragment.
	 * @param fId Id of the fragment whose parents are retrieved.
	 * @return Set of parent fragments.
	 * @throws ReuseException
	 */
	public Set<XMLFragment> findParents(String fId) {
		String query = 
		"let $frag := collection($container)/fragment[@id=$fId] " +
		"for $f in $frag " +
		"let $parentIds := tokenize($f/@parent, $delim) " +
		"for $pId in $parentIds " +
		"return collection($container)/fragment[@id=$pId]";
		
		ResultHandler<XMLFragment> rh = new FragmentHandler(marshaller);
		
		Collection<XMLFragment> _parents = abstractQuery2(
				query, 
				map("fId", fId)
				.map("delim", " "), 
				rh);
		Set<XMLFragment> parents = (Set<XMLFragment>) _parents; 	
		return parents;
	}
	
	
	public Set<String> findChildrenIds(String parentId) throws ReuseException {
		String query = 
			"import module namespace functx=\"http://www.functx.com\" " +
			"at \"functx.xq\"; " +
			"for $frag in collection($container)/fragment " +
			"let $parentIds := tokenize($frag/@parent, $delim) " +
			"where functx:is-value-in-sequence($parentId, $parentIds) " +
			"return data($frag/@id)";
		
		ResultHandler<String> rh = new StringSequenceHandler();
		
		Collection<String> children = abstractQuery(
				query, 
				map("parentId", parentId)
				.map("delim", " "), 
				rh);
		return (Set<String>) children; 
	}
	
	
	public Set<String> findDescendentsIds(String parentId) throws ReuseException {
		Set<String> dcs = new HashSet<String>();
		
		Set<String> childrenIds = findChildrenIds(parentId);
		dcs.addAll(childrenIds);
		for(String cId : childrenIds) {
			Set<String> _dcs = findDescendentsIds(cId);
			dcs.addAll(_dcs);
		}
		return dcs;
	}
	
}
