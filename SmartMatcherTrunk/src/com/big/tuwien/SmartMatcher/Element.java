package com.big.tuwien.SmartMatcher;


/**
 * @author Milo Nanuk
 * @version 1.0
 * @created 28-Apr-2008 14:21:38
 */
public abstract class Element {
	protected int id;
	protected String name;
	protected boolean isAbstract;
	protected EcoreElement representedBy;
	

	public Element() {}


	public String getName(){
		return name;
	}
	
	
	public String getFullPathName(){
		return this.representedBy.getFullPathName();
	}

	
	public void setName(String name){
		this.name = name;
	}
	
	
	public EcoreElement getRepresentedBy(){
		return representedBy;
	}


	public void setRepresentedBy(EcoreElement representedBy) {
		this.representedBy = representedBy;
	}
	
	
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || !(other instanceof Element)) 
			return false;
		
		Element that = (Element) other;
		return this.id == that.id;	
	}
	
	
	public int hashCode() {
		return this.getClass().hashCode() + this.id;
	}
	


	public boolean isAbstract() {
		return isAbstract;
	}


	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	
	public String toString() {
		return getFullPathName();
	}
	
	public abstract Element copy();
	
	public abstract Element shallowCopy();

}