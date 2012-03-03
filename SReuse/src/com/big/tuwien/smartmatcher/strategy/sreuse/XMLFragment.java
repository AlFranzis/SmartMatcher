package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class XMLFragment implements XMLIdentifiable {
	private String id;
	private boolean root = false;
	private Set<XMLClassElement> classes = new HashSet<XMLClassElement>();
	private Set<XMLFragmentMapping> mappings = 
										new HashSet<XMLFragmentMapping>();
	private Set<XMLMetaModel> metamodels = new HashSet<XMLMetaModel>();
	private Set<XMLFragment> parents = new CopyOnWriteArraySet<XMLFragment>();
	private Set<XMLFragment> children = new CopyOnWriteArraySet<XMLFragment>();
	
	
	public static XMLFragment getInstance(Fragment f, 
									XMLElementResolverFactory resolverFac) {
		XMLResolver<Fragment,XMLFragment> fragResolver = 
			resolverFac.getResolver(Fragment.class, XMLFragment.class);

		if(fragResolver == null)
			throw new ReuseRuntimeException(
					"No XMLResolver found for " 
					+ Fragment.class + ", " + XMLFragment.class);
		
		IdGenerator<XMLFragment> fragIdGenerator = 
			resolverFac.getIdGenerator(XMLFragment.class);
		
		if(fragIdGenerator == null)
			throw new ReuseRuntimeException(
					"No IdGenerator found for " 
					+ XMLFragment.class);
		
		if(fragResolver.isBound(f))
			return fragResolver.resolve(f);
		
		Set<Element> classes = f.getClasses();
		Set<XMLClassElement> xmlClasses = resolverFac.resolve2(classes);
		
		Set<MetaModel> mms = f.getMetamodels();
		Set<XMLMetaModel> xmlMMs = new HashSet<XMLMetaModel>();
		for(MetaModel mm : mms) {
			XMLMetaModel xmlMM = XMLMetaModel.getInstance(mm, resolverFac);
			xmlMMs.add(xmlMM);
		}
		
		Set<FragmentMapping> fms = f.getMappings();
		Set<XMLFragmentMapping> xmlFms = new HashSet<XMLFragmentMapping>();
		for(FragmentMapping fm : fms) {
			XMLFragmentMapping xmlFm = XMLFragmentMapping.getInstance(fm, 
																resolverFac);
			xmlFms.add(xmlFm);
		}
		
		
		Set<Fragment> parents = f.getParents();
		Set<XMLFragment> xmlParents = new HashSet<XMLFragment>();
		for(Fragment parent : parents) {
			XMLFragment xmlParent = getInstance(parent, resolverFac);
			xmlParents.add(xmlParent);
		}
			
		XMLFragment xmlFragment = new XMLFragment();
		
		String id = f.getId();
		// if no id is set -> generate one
		if(id == null) {
			id = fragIdGenerator.generateId(xmlFragment);
		}
		xmlFragment.setId(id);
		
		xmlFragment.setRoot(f.isRoot());
		xmlFragment.setClasses(xmlClasses);
		xmlFragment.setMetamodels(xmlMMs);
		xmlFragment.setMappings(xmlFms);
		xmlFragment.setParents(xmlParents);
		
		fragResolver.bind(f, xmlFragment);
		
		return xmlFragment;
	}
	
	
	public Set<XMLClassElement> getClasses() {
		return classes;
	}
	
	
	public void setClasses(Set<XMLClassElement> classes) {
		this.classes = classes;
	}
	
	
	public Set<XMLFragmentMapping> getMappings() {
		return mappings;
	}
	
	
	public void setMappings(Set<XMLFragmentMapping> mappings) {
		this.mappings = mappings;
	}
	
	
	public Set<XMLMetaModel> getMetamodels() {
		return metamodels;
	}
	
	
	public void setMetamodels(Set<XMLMetaModel> metamodels) {
		this.metamodels = metamodels;
	}


	public Set<XMLFragment> getParents() {
		return parents;
	}


	public void setParents(Set<XMLFragment> parents) {
		this.parents = parents;
		
		for(XMLFragment p : parents) {
			p.children.add(this);
		}
	}
	
	
	public Set<XMLFragment> getChildren() {
		return children;
	}
	
	
	public void setChildren(Set<XMLFragment> children) {
		this.children = children;
		
		for(XMLFragment c : children) {
			c.parents.add(this);
		}
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	public boolean isRoot() {
		return root;
	}
	
	
	public void setRoot(boolean root) {
		this.root = root;
	}
	
	
	@Override
	public String toString() {
		return "XMLFragment(" + classes + ")";
	}
	
	
	
}
