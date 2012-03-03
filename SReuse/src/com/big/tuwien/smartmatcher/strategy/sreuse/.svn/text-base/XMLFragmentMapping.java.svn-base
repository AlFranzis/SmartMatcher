package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class XMLFragmentMapping implements XMLIdentifiable {
	private static Logger logger = Logger.getLogger(XMLFragmentMapping.class);
	
	private String id;
	
	/*
	 * ROOT operators
	 */
	private Set<XMLOperator> operators = new HashSet<XMLOperator>();
	/*
	 * FLATTENED operators
	 */
	private Set<XMLOperator> flattenedOperators = new HashSet<XMLOperator>();
	
	
	public XMLFragmentMapping() {}
	
	
	public static XMLFragmentMapping getInstance(FragmentMapping fm, XMLElementResolverFactory resolverFac) {
		logger.debug("Resolving fragment-mapping: " + fm);
		
		XMLResolver<FragmentMapping,XMLFragmentMapping> fmResolver = 
			resolverFac.getResolver(FragmentMapping.class, XMLFragmentMapping.class);

		if(fmResolver == null)
			throw new ReuseRuntimeException(
					"No XMLResolver found for " 
					+ FragmentMapping.class + ", " + XMLFragmentMapping.class);
		
		IdGenerator<XMLFragmentMapping> fmIdGenerator = 
			resolverFac.getIdGenerator(XMLFragmentMapping.class);
		
		if(fmIdGenerator == null)
			throw new ReuseRuntimeException(
					"No IdGenerator found for " 
					+ XMLFragmentMapping.class);
		
		if(fmResolver.isBound(fm)) {
			XMLFragmentMapping resolved = fmResolver.resolve(fm);
			logger.debug("Cache hit for fragment-mapping: " + fm + ", in cache: " + resolved);
			return resolved;
		}
		
		//TFragmentizer2.printFragmentMapping(fm);
		Set<Operator> ops = fm.getOperators();
		Set<XMLOperator> xmlOps = new HashSet<XMLOperator>();
		for(Operator op : ops) {
			XMLOperator xmlOp = XMLOperator.getInstance(op, resolverFac);
			xmlOps.add(xmlOp);
		}
		
		XMLFragmentMapping xmlFm = new XMLFragmentMapping();
		
		String id = fm.getId();
		// if no id is set -> generate one
		if(id == null) {
			id = fmIdGenerator.generateId(xmlFm);
		}
		xmlFm.setId(id);
		
		xmlFm.setOperators(xmlOps);
		
		fmResolver.bind(fm, xmlFm);
		
		logger.debug("Resolved fragment-mapping: " + fm + " to: " + xmlFm);
		return xmlFm;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	public Set<XMLOperator> getOperators() {
		return operators;
	}

	
	public void setOperators(Set<XMLOperator> operators) {
		this.operators = operators;
		
		Set<XMLOperator> ops = new HashSet<XMLOperator>();
		for(XMLOperator rootOp : operators) {
			Set<XMLOperator> _ops = flatten(rootOp);
			ops.addAll(_ops);
		}
		
		this.flattenedOperators = ops;
	}
	
	
	/**
	 * Returns the operators of the mapping in a flattened
	 * set.
	 * @return
	 */
	public Set<XMLOperator> getFlattenedOperators() {
		return flattenedOperators;
	}
	
	
	/*
	 * Flattens an operator: Returns the operator and it's
	 * children in a set.
	 */
	private Set<XMLOperator> flatten(XMLOperator op) {
		Set<XMLOperator> ops = new HashSet<XMLOperator>();
		ops.add(op);
		for(XMLOperator child : op.getChildren()) {
			Set<XMLOperator> _ops = flatten(child);
			ops.addAll(_ops);
		}
		return ops;
	}
	
	
	public void setFlattenedOperators(Set<XMLOperator> flattenedOperators) {
		this.flattenedOperators = flattenedOperators;
		
		Set<XMLOperator> rootOps = filterRootOperators(flattenedOperators);
		this.operators = rootOps;
	}
	
	
	public void addFlattenedOperator(XMLOperator flattenedOperator) {
		this.flattenedOperators.add(flattenedOperator);
		
		Set<XMLOperator> rootOps = filterRootOperators(flattenedOperators);
		this.operators = rootOps;
	}
	
	
	/*
	 * Returns all root operators contained in the set.
	 */
	private Set<XMLOperator> filterRootOperators(
					Set<XMLOperator> flattenedOps) {
		Set<XMLOperator> rootOps = new HashSet<XMLOperator>();
		for(XMLOperator op : flattenedOps) {
			if(op.getParents().isEmpty())
				rootOps.add(op);
		}
		return rootOps;
	}
	
	@Override
	public String toString() {
		return "XMLFragmentMapping(" + flattenedOperators + ")";
	}
	
}
