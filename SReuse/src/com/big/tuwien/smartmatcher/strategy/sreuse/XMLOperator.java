package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;

public class XMLOperator implements XMLAny {
	private String id;
	private String name;
	private double similarity = -1.0;
	protected Set<XMLOperator> parents = 
						new CopyOnWriteArraySet<XMLOperator>();
	protected Set<XMLOperator> children = 
						new CopyOnWriteArraySet<XMLOperator>();

	protected Map<String,XMLElement> lhsRoles = new HashMap<String, XMLElement>();
	protected Map<String,XMLElement> rhsRoles = new HashMap<String, XMLElement>();
	
	
	public XMLOperator() {}
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	
	public static XMLOperator getInstance(Operator op, 
								XMLElementResolverFactory resolverFac) {
		XMLResolver<Operator,XMLOperator> opResolver = 
			resolverFac.getResolver(Operator.class, XMLOperator.class);

		if(opResolver == null)
			throw new ReuseRuntimeException(
					"No XMLResolver found for " 
					+ Operator.class + ", " + XMLOperator.class);
		
		IdGenerator<XMLOperator> opIdGenerator = 
			resolverFac.getIdGenerator(XMLOperator.class);
		
		if(opIdGenerator == null)
			throw new ReuseRuntimeException(
					"No IdGenerator found for " 
					+ XMLOperator.class);
		
		if(opResolver.isBound(op))
			return opResolver.resolve(op);
		
		XMLOperator xmlOp = new XMLOperator();
		xmlOp.setName(op.getName());
		
		String id = op.getId();
		// if no id is set -> generate one
		if(id == null) {
			id = opIdGenerator.generateId(xmlOp);
		}
		xmlOp.setId(id);
		
		xmlOp.setSimilarity(op.getSimilarity());
		
		// handle lhs roles
		Map<String,XMLElement> lhsXmlRoles = wrapRoles(op.getLhsRoles(), resolverFac);
		xmlOp.setLhsRoles(lhsXmlRoles);
		
		// handle rhs roles
		Map<String,XMLElement> rhsXmlRoles = wrapRoles(op.getRhsRoles(), resolverFac);
		xmlOp.setRhsRoles(rhsXmlRoles);
		
		// handle child operators
		Set<XMLOperator> xmlChildren = 
							new CopyOnWriteArraySet<XMLOperator>(); 
		for(Operator child : op.getChildren()) {
			XMLOperator xmlChild;
			if(opResolver.isBound(child)) {
				 xmlChild = opResolver.resolve(child);
			} else {
				xmlChild = XMLOperator.getInstance(child, resolverFac);
			}
			xmlChildren.add(xmlChild);	
		}
		xmlOp.setChildren(xmlChildren);
		
		opResolver.bind(op, xmlOp);
		
		return xmlOp;
	}
	
	
	private static Map<String,XMLElement> wrapRoles(Map<String,Element> roles, 
									XMLElementResolverFactory resolverFac) {
		XMLElementResolver resolver = resolverFac.getResolver();
		
		Map<String,XMLElement> xmlRoles = new HashMap<String,XMLElement>();
		for(Entry<String,Element> role : roles.entrySet()) {
			Element e = role.getValue();
			
			/*
			 * TODO: the following element binding/resolving code should be improved:
			 * Currently the XMLResolverFactory only has methods to bind/resolve 
			 * classes but not attributes and references. 
			 * Attributes / references are bound implicitly by binding their container class.
			 */
			
			if(!resolver.isBound(e)) {
				if(e.getRepresentedBy() instanceof ClassElement) {
					resolverFac.resolve2(Arrays.asList(e));
				} else if(e.getRepresentedBy() instanceof AttributeElement) {
					/* If e is an unbound attribute -> resolve (bind) container class
					 * as this will bind the attribute too. 
					 */
					Element containerClass = ((AttributeElement) e.getRepresentedBy())
											.getContainedIn().getRepresents();
					resolverFac.resolve2(Arrays.asList(containerClass));
				} else if(e.getRepresentedBy() instanceof ReferenceElement) {
					/* If e is an unbound reference -> resolve (bind) container class
					 * as this will bind the reference too. 
					 */
					Element containerClass = ((ReferenceElement) e.getRepresentedBy())
											.getContainedIn().getRepresents();
					resolverFac.resolve2(Arrays.asList(containerClass));
				} else {
					throw new ReuseRuntimeException("Unknown element type " + e);
				}
			}
			
			if(!resolver.isBound(e))
				throw new ReuseRuntimeException("Cannot resolve element " + e);
			
			XMLElement xmlE = resolver.resolve(e);
			xmlRoles.put(role.getKey(), xmlE);
		}
		
		return xmlRoles;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public double getSimilarity() {
		return similarity;
	}
	
	
	public void setSimilarity(double sim) {
		similarity = sim;
	}


	public Map<String, XMLElement> getRoles() {
		Map<String, XMLElement> roles = new HashMap<String, XMLElement>(lhsRoles);
		roles.putAll(rhsRoles);
		return roles;
	}
	
	
	public Map<String, XMLElement> getLhsRoles() {
		return lhsRoles;
	}
	
	
	public Map<String, XMLElement> getRhsRoles() {
		return rhsRoles;
	}


	public void setLhsRoles(Map<String, XMLElement> lhsRoles) {
		this.lhsRoles = lhsRoles;
	}
	
	
	public void setRhsRoles(Map<String, XMLElement> rhsRoles) {
		this.rhsRoles = rhsRoles;
	}


	public Set<XMLOperator> getParents() {
		return parents;
	}


	public void setParents(Set<XMLOperator> parents) {
		this.parents = parents;
		
		for(XMLOperator p : parents) {
			p.children.add(this);
		}
	}


	public Set<XMLOperator> getChildren() {
		return children;
	}


	public void setChildren(Set<XMLOperator> children) {
		this.children = children;
		
		for(XMLOperator c : children) {
			c.parents.add(this);
		}
	}
	
	
	@Override
	public String toString() {
		// return "XMLOperator(" + name + "," + parents + ", " + lhsRoles + ", " + rhsRoles + ")";
		return "XMLOperator(" + name + ", " + lhsRoles + ", " + rhsRoles + ")";
	}

}
