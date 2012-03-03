package com.big.tuwien.SmartMatcher.fitness;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

public class ModelStatistic {
	private Set<EClass> usedClasses = new HashSet<EClass>();
	private Set<EAttribute> usedAttributes = new HashSet<EAttribute>();
	private Set<EReference> usedReferences = new HashSet<EReference>();
	private Set<EClass> unusedClasses = new HashSet<EClass>();
	private Set<EAttribute> unusedAttributes = new HashSet<EAttribute>();
	private Set<EReference> unusedReferences = new HashSet<EReference>();
	
	private double classUsageCoverage;
	private double attributeUsageCoverage;
	private double referencesUsageCoverage;
	private double totalUsageCoverage;
	
	
	public double getTotalUsageCoverage() {
		return totalUsageCoverage;
	}


	public void setTotalUsageCoverage(double totalUsageCoverage) {
		this.totalUsageCoverage = totalUsageCoverage;
	}


	public Set<EClass> getUsedClasses() {
		return usedClasses;
	}
	
	
	public void setUsedClasses(Set<EClass> usedClasses) {
		this.usedClasses = usedClasses;
	}
	
	
	public void addUsedClass(EClass c) {
		if(! usedClasses.contains(c))
			usedClasses.add(c);
	}
	
	
	public Set<EAttribute> getUsedAttributes() {
		return usedAttributes;
	}
	
	
	public void setUsedAttributes(Set<EAttribute> usedAttributes) {
		this.usedAttributes = usedAttributes;
	}
	
	
	public void addUsedAttribute(EAttribute a) {
		if(! usedAttributes.contains(a))
			usedAttributes.add(a);
	}
	
	
	public Set<EReference> getUsedReferences() {
		return usedReferences;
	}
	
	
	public void setUsedReferences(Set<EReference> usedReferences) {
		this.usedReferences = usedReferences;
	}
	
	
	public void addUsedReference(EReference r) {
		if(! usedReferences.contains(r))
			usedReferences.add(r);
	}
	
	
	public Set<EClass> getUnusedClasses() {
		return unusedClasses;
	}


	public void setUnusedClasses(Set<EClass> unusedClasses) {
		this.unusedClasses = unusedClasses;
	}
	
	
	public void addUnusedClass(EClass c) {
		if(! unusedClasses.contains(c))
			unusedClasses.add(c);
	}


	public Set<EAttribute> getUnusedAttributes() {
		return unusedAttributes;
	}


	public void setUnusedAttributes(Set<EAttribute> unusedAttributes) {
		this.unusedAttributes = unusedAttributes;
	}
	
	
	public void addUnusedAttribute(EAttribute a) {
		if(! unusedAttributes.contains(a))
			unusedAttributes.add(a);
	}


	public Set<EReference> getUnusedReferences() {
		return unusedReferences;
	}


	public void setUnusedReferences(Set<EReference> unusedReferences) {
		this.unusedReferences = unusedReferences;
	}
	
	
	public void addUnusedReference(EReference r) {
		if(! unusedReferences.contains(r))
			unusedReferences.add(r);
	}


	public double getClassUsageCoverage() {
		return classUsageCoverage;
	}


	public void setClassUsageCoverage(double classUsageCoverage) {
		this.classUsageCoverage = classUsageCoverage;
	}


	public double getAttributeUsageCoverage() {
		return attributeUsageCoverage;
	}


	public void setAttributeUsageCoverage(double attributeUsageCoverage) {
		this.attributeUsageCoverage = attributeUsageCoverage;
	}


	public double getReferencesUsageCoverage() {
		return referencesUsageCoverage;
	}


	public void setReferencesUsageCoverage(double referencesUsageCoverage) {
		this.referencesUsageCoverage = referencesUsageCoverage;
	}	

}
