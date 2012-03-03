package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;

public class Fragment {
	private String id;
	private boolean root;
	/* ROOT classes */
	private Set<Element> classes = new CopyOnWriteArraySet<Element>();
	private Set<FragmentMapping> mappings = 
							new CopyOnWriteArraySet<FragmentMapping>();
	private Set<MetaModel> metamodels = new CopyOnWriteArraySet<MetaModel>();
	private Set<Fragment> parents = new CopyOnWriteArraySet<Fragment>();
	private Set<Fragment> children = new CopyOnWriteArraySet<Fragment>();
	
	
	public Set<Element> getClasses() {
		return classes;
	}
	
	
	public void setClasses(Set<Element> classes) {
		this.classes = classes;
	}
	
	
	public Set<FragmentMapping> getMappings() {
		return mappings;
	}
	
	
	public void setMappings(Set<FragmentMapping> mappings) {
		this.mappings = mappings;
	}
	
	
	public Set<MetaModel> getMetamodels() {
		return metamodels;
	}
	
	
	public void setMetamodels(Set<MetaModel> metamodels) {
		this.metamodels = metamodels;
	}


	public Set<Fragment> getParents() {
		return parents;
	}
	
	
	public Set<Fragment> getChildren() {
		return children;
	}


	public void setParents(Set<Fragment> parents) {
		this.parents = parents;
		
		for(Fragment p : parents) {
			p.children.add(this);
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
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || !(other instanceof Fragment)) return false;
		
		Fragment that = (Fragment) other;
		 
		if(this.id != null && that.id != null)
			return this.id.equals(that.id);
		
		boolean eqClasses = this.classes.equals(that.classes);
		boolean eqMappings =  this.mappings.equals(that.mappings);
		
		return eqClasses && eqMappings;
	}
	
	
	@Override
	public int hashCode() {
		return 99 * Fragment.class.hashCode() 
				+ 67 * mappings.hashCode() 
				+ 17 * classes.hashCode();
	}
	
	
	@Override
	public String toString() {
		return "Fragment(" + id + ", " + classes + ", " + mappings + ")";
	}
	
}
